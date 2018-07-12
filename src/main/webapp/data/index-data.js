
$(function() {
    var eventId = GetQueryString(eventId);
    $.ajax({
        url: "/api/fsms/geteventdynamic?eventId="+ GetQueryString("eventId"),
        success: successCallback,
        error: errorCallback
    });

    $.ajax({
        url: "/api/fsms/getinfo?eventId="+ GetQueryString("eventId"),
        success: infoCallback,
        error: errorCallback
    });
    function infoCallback(json){
        document.getElementById("allevent").innerHTML = json.alleventcount;
        document.getElementById("allarticle").innerHTML = json.allarticalcount;
        document.getElementById("article").innerHTML = json.articalcount;
        document.getElementById("hot").innerHTML = json.hot;
        document.getElementById("articleurl").href = "articletable.html?eventId="+ GetQueryString("eventId");
        document.getElementById("userurl").href = "user.html?eventId="+ GetQueryString("eventId");
        document.getElementById("emourl").href = "emotion.html?eventId="+ GetQueryString("eventId");
        document.getElementById("list1").href = "index.html?eventId="+ GetQueryString("eventId");
        document.getElementById("list2").href = "articletable.html?eventId="+ GetQueryString("eventId");
        document.getElementById("list3").href = "user.html?eventId="+ GetQueryString("eventId");
        document.getElementById("list4").href = "emotion.html?eventId="+ GetQueryString("eventId");
    }

    function successCallback(json){
        var ad = [];
        var emo = [0,0,0];
        var num = [];
        json.forEach(function(value,index,array){
            var a={
                "updateTime":value.updateTime,
                "activityDegree":value.activityDegree
            };
            emo[0] += parseInt(value.sentimentNum.split(",")[0]);
            emo[1] += parseInt(value.sentimentNum.split(",")[1]);
            emo[2] += parseInt(value.sentimentNum.split(",")[2]);

            var n={
                "y": value.updateTime,
                "a":value.uniqueUserNum,
                "b":value.vUserNum,
                "c":value.weiboNum
            };
            num.push(n);

            ad.push(a);
        });
        Morris.Area({
            element : 'morris-area-chart',
            data : ad,
            xkey: 'updateTime',
            ykeys: ['activityDegree'],
            labels: ['activityDegree'],
            pointSize: 2,
            hideHover: 'auto',
            resize: true
        });


        Morris.Donut({
            element: 'morris-donut-chart',
            data: [{
                label: "正面",
                value: emo[0]
            }, {
                label: "中立",
                value: emo[1]
            }, {
                label: "反面",
                value: emo[2]
            }],
            resize: true
        });

        Morris.Bar({
            element: 'morris-bar-chart',
            data: num ,
            xkey: 'y',
            ykeys: ['a', 'b' , 'c'],
            labels: ['普通用户', '大v' , "微博总数"],
            hideHover: 'auto',
            resize: true
        });

        // $('#dataTables-example').DataTable({
        //     data:json,
        //     columns: [
        //         { data: 'updateTime' },
        //         { data: 'weiboNum' },
        //         { data: 'repostNum' },
        //         { data: 'commentNum' }
        //     ]
        // });

    }
    function errorCallback() {
        alert("查询出现错误: ");
    }

    $.ajax({
        url: "/api/fsms/gettf?eventId="+ GetQueryString("eventId"),
        success: tfCallback,
        error: errorCallback
    });
    function tfCallback(json) {
        var fill = d3.scale.category20b();

        var w = document.getElementById('vis').parentNode.offsetWidth,
            h = 400;

        var max,
            fontSize,
            tags = json;


        var layout = d3.layout.cloud()
            .timeInterval(Infinity)
            .size([w, h])
            .fontSize(function(d) {
                return fontSize(+d.value);
            })
            .text(function(d) {
                return d.key;
            })
            .on("end", draw);

        var svg = d3.select("#vis").append("svg")
            .attr("width", w)
            .attr("height", h);

        var vis = svg.append("g").attr("transform", "translate(" + [w >> 1, h >> 1] + ")");

        update();

        window.onresize = function(event) {
            update();
        };

        function draw(data, bounds) {
            var w = document.getElementById('vis').parentNode.offsetWidth,
                h = 400;

            svg.attr("width", w).attr("height", h);

            scale = bounds ? Math.min(
                w / Math.abs(bounds[1].x - w / 2),
                w / Math.abs(bounds[0].x - w / 2),
                h / Math.abs(bounds[1].y - h / 2),
                h / Math.abs(bounds[0].y - h / 2)) / 2 : 1;

            var text = vis.selectAll("text")
                .data(data, function(d) {
                    return d.text.toLowerCase();
                });
            text.transition()
                .duration(1000)
                .attr("transform", function(d) {
                    return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
                })
                .style("font-size", function(d) {
                    return d.size + "px";
                });
            text.enter().append("text")
                .attr("text-anchor", "middle")
                .attr("transform", function(d) {
                    return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
                })
                .style("font-size", function(d) {
                    return d.size + "px";
                })
                .style("opacity", 1e-6)
                .transition()
                .duration(1000)
                .style("opacity", 1);
            text.style("font-family", function(d) {
                return d.font;
            })
                .style("fill", function(d) {
                    return fill(d.text.toLowerCase());
                })
                .text(function(d) {
                    return d.text;
                });

            vis.transition().attr("transform", "translate(" + [w >> 1, h >> 1] + ")scale(" + scale + ")");
        }

        function update() {
            layout.font('impact').spiral('archimedean');
            fontSize = d3.scale['sqrt']().range([10, 100]);
            var min = tags[0].value,max = tags[0].value;
            tags.forEach(function(value,index,array){
               if(value.value<min)
                   min = value.value;
               if(value.value>max)
                   max = value.value;
            });
            if (tags.length){
                fontSize.domain([min, max]);
            }
            layout.stop().words(tags).start();
        }
    }

    function GetQueryString(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!==null)return  decodeURI(r[2]); return null;
    }



});