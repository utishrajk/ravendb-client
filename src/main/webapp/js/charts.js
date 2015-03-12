$(document).ready(function(){

    console.log('Calling datavizpatient.js');

    var chart;

    // load the data file
    d3.json("/raven/sine.json", function (data) {

      console.log("data : " + data);
      var chart = nv.models.lineChart().useInteractiveGuideline(true);

      chart.xAxis.axisLabel('Time (ms)').tickFormat(d3.format(',r'));

      chart.yAxis.axisLabel('Voltage (v)').tickFormat(d3.format('.02f'));
      
      var dataformat = [{values : data, key : 'Sine Wave', color: '#ff7f0e'}];

      d3.select('#chart svg')
        .datum(dataformat)
        .transition().duration(500)
        .call(chart);

      nv.utils.windowResize(chart.update);

    });

});