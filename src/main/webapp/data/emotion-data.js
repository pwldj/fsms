
$(function() {
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
        document.getElementById("list1").href = "index.html?eventId="+ GetQueryString("eventId");
        document.getElementById("list2").href = "articletable.html?eventId="+ GetQueryString("eventId");
        document.getElementById("list3").href = "user.html?eventId="+ GetQueryString("eventId");
        document.getElementById("list4").href = "emotion.html?eventId="+ GetQueryString("eventId");
    }

    function successCallback(json){
        var emo = []
        var num=[0,0,0];
        json.forEach(function(value,index,array){
            num[0] += parseInt(value.sentimentNum.split(",")[0]);
            num[1] += parseInt(value.sentimentNum.split(",")[1]);
            num[2] += parseInt(value.sentimentNum.split(",")[2]);

            var a={
                "updateTime":value.updateTime,
                "sentimentTendency":value.sentimentTendency
            };
            emo.push(a)
        });

        Morris.Area({
            element : 'morris-area-chart',
            data : emo,
            xkey: 'updateTime',
            ykeys: ['sentimentTendency'],
            labels: ['sentimentTendency'],
            pointSize: 2,
            hideHover: 'auto',
            resize: true
        });


        Morris.Donut({
            element: 'morris-donut-chart',
            data: [{
                label: "正面",
                value: num[0]
            }, {
                label: "中立",
                value: num[1]
            }, {
                label: "反面",
                value: num[2]
            }],
            resize: true
        });


    }
    function errorCallback() {
        alert("查询出现错误: ");
    }


    function GetQueryString(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!==null)return  decodeURI(r[2]); return null;
    }



});