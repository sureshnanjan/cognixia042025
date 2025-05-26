/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 100.0, "KoPercent": 0.0};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.5455882352941176, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.535, 500, 1500, "Checkout-1"], "isController": false}, {"data": [0.585, 500, 1500, "Ordering-0"], "isController": false}, {"data": [0.975, 500, 1500, "Entering Login Details"], "isController": false}, {"data": [0.525, 500, 1500, "Checkout-0"], "isController": false}, {"data": [0.58, 500, 1500, "Ordering-1"], "isController": false}, {"data": [1.0, 500, 1500, "Login"], "isController": false}, {"data": [0.72, 500, 1500, "Logout-1"], "isController": false}, {"data": [0.665, 500, 1500, "Logout-0"], "isController": false}, {"data": [0.5, 500, 1500, "HomePage"], "isController": false}, {"data": [0.235, 500, 1500, "Checkout"], "isController": false}, {"data": [0.0, 500, 1500, "Test"], "isController": true}, {"data": [0.525, 500, 1500, "success"], "isController": false}, {"data": [0.295, 500, 1500, "Logout"], "isController": false}, {"data": [0.235, 500, 1500, "Ordering"], "isController": false}, {"data": [0.355, 500, 1500, "Adding Item to Basket"], "isController": false}, {"data": [0.785, 500, 1500, "Adding Item to Basket-0"], "isController": false}, {"data": [0.76, 500, 1500, "Adding Item to Basket-1"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 1600, 0, 0.0, 948.2393749999997, 271, 3833, 753.5, 1966.9, 2574.6499999999987, 3419.8100000000004, 42.813946643118996, 233.4012074452784, 50.680382181370575], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["Checkout-1", 100, 0, 0.0, 940.5399999999998, 311, 2560, 840.0, 1716.4000000000003, 1862.599999999999, 2554.069999999997, 7.222824124232575, 47.18817713976164, 6.658540989526904], "isController": false}, {"data": ["Ordering-0", 100, 0, 0.0, 858.09, 291, 2087, 668.5, 1707.9, 1868.5499999999995, 2086.91, 7.971303308090873, 1.4245590872857712, 6.671295834994021], "isController": false}, {"data": ["Entering Login Details", 100, 0, 0.0, 320.09000000000003, 271, 544, 300.5, 384.30000000000007, 505.6999999999995, 543.91, 10.309278350515465, 65.86007973582474, 11.732039304123711], "isController": false}, {"data": ["Checkout-0", 100, 0, 0.0, 877.2499999999999, 324, 2219, 746.0, 1814.8000000000004, 1908.0, 2217.3899999999994, 7.186489399928135, 1.2843042579949695, 8.393595041322314], "isController": false}, {"data": ["Ordering-1", 100, 0, 0.0, 896.23, 285, 2286, 837.5, 1726.5000000000005, 1853.0499999999995, 2283.2499999999986, 7.217610970768676, 47.1541185492602, 6.30836115120895], "isController": false}, {"data": ["Login", 100, 0, 0.0, 296.24, 272, 405, 290.0, 315.9, 362.5999999999999, 404.7499999999999, 10.51745898190997, 68.53808963504417, 8.422183950357594], "isController": false}, {"data": ["Logout-1", 100, 0, 0.0, 721.6500000000003, 276, 1859, 434.0, 1624.7, 1728.1999999999998, 1858.5599999999997, 7.80579189758801, 51.07305245492155, 7.27219284208883], "isController": false}, {"data": ["Logout-0", 100, 0, 0.0, 839.5100000000003, 280, 2127, 543.0, 1853.5000000000005, 2005.6, 2126.73, 7.726781023025807, 1.4563171264101376, 8.43607537474888], "isController": false}, {"data": ["HomePage", 100, 0, 0.0, 812.86, 765, 947, 803.0, 856.9, 902.4499999999998, 947.0, 9.847365829640571, 132.19896294928608, 5.673775233874939], "isController": false}, {"data": ["Checkout", 100, 0, 0.0, 1818.2199999999998, 705, 3776, 1585.0, 3006.2000000000003, 3284.2499999999995, 3775.1199999999994, 6.975446428571429, 46.81859697614397, 14.577593122209821], "isController": false}, {"data": ["Test", 100, 0, 0.0, 8802.829999999998, 5928, 10712, 8957.5, 9821.8, 10024.9, 10707.229999999998, 5.357334190506804, 322.14503266299687, 59.61248057966356], "isController": true}, {"data": ["success", 100, 0, 0.0, 1002.6000000000005, 292, 2463, 973.5, 1877.2000000000005, 1998.9999999999998, 2462.3399999999997, 7.342143906020558, 49.2583287444934, 5.872281112334802], "isController": false}, {"data": ["Logout", 100, 0, 0.0, 1561.5399999999993, 556, 3721, 1001.5, 3022.0, 3412.5999999999985, 3719.7699999999995, 7.547169811320755, 50.8033608490566, 15.27122641509434], "isController": false}, {"data": ["Ordering", 100, 0, 0.0, 1754.8, 577, 3833, 1586.0, 3250.3, 3604.65, 3831.049999999999, 7.019514249613927, 47.114376403902845, 12.009950161448828], "isController": false}, {"data": ["Adding Item to Basket", 100, 0, 0.0, 1236.4800000000002, 604, 3326, 837.5, 2594.3, 2823.199999999999, 3325.3999999999996, 8.813678829543452, 61.14489687995769, 17.52407236030319], "isController": false}, {"data": ["Adding Item to Basket-0", 100, 0, 0.0, 593.2800000000001, 304, 2214, 400.0, 1218.9000000000005, 1618.1499999999999, 2211.1299999999983, 9.714396735962698, 2.2198914416164754, 10.805369025646007], "isController": false}, {"data": ["Adding Item to Basket-1", 100, 0, 0.0, 642.45, 290, 2598, 398.0, 1252.0000000000002, 1657.7999999999995, 2597.5199999999995, 9.064539521392312, 60.813854015591005, 7.940324170594634], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": []}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 1600, 0, "", "", "", "", "", "", "", "", "", ""], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
