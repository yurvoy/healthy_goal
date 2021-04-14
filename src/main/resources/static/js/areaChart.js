google.charts.load('current', {'packages': ['corechart']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
    var data = google.visualization.arrayToDataTable([
        ['Date', 'Your weight', 'Max healthy', 'Min healthy'],

        /*[# th:each="weight: ${user.weights}"]*/
        [ [[${#dates.format(new java.util.Date(weight.key))}]], [[${weight.value}]],
            [[${user.healthyMaxWeight}]], [[${user.healthyMinWeight}]] ],
        /*[/]*/
    ]);

    var options = {
        title: 'Your progress:',
        hAxis: {title: 'Timeline', titleTextStyle: {color: '#333'}},
        vAxis: {
            minValue: 40,
            viewWindowMode: "explicit",
            viewWindow: {
                min: 50
            }
        },
        isStacked: 'false',
        ticks: [50, 60, 70, 80, 90, 100],
        series: {
            0: { color: 'blue' },
            1: { color: 'green' },
            2: { color: 'red' },
        }
    };

    var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
    chart.draw(data, options);
}