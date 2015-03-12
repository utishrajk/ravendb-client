package net.ravendb.todomvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ravendb.abstractions.json.linq.RavenJArray;
import net.ravendb.client.IDocumentSession;
import net.ravendb.client.IDocumentStore;
import net.ravendb.client.linq.IRavenQueryable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sine.json")
public class SineController {

	@Autowired
	private IDocumentStore store;

	@RequestMapping(method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("test...");

		try (IDocumentSession session = store.openSession()) {
			QSine t = QSine.sine;

			IRavenQueryable<Sine> query = session.query(Sine.class).orderBy(t.x.asc());

			List<Sine> sineList = query.toList();

			response.getWriter().write(RavenJArray.fromObject(sineList).toString());
			response.getWriter().close();

		} catch (Exception e) {
			throw new RuntimeException(e);

		}
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	protected void doPost(@RequestBody Sine sine) {

		try (IDocumentSession session = store.openSession()) {
			session.store(sine);
			session.saveChanges();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
