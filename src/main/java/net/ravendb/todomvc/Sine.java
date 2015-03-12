package net.ravendb.todomvc;

import com.mysema.query.annotations.QueryEntity;

@QueryEntity
public class Sine {

	private Double x;
	private Double y;

	public Sine() {

	}

	public Sine(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Sine [x=" + x + ", sin(x)=" + y + "]";
	}

}
