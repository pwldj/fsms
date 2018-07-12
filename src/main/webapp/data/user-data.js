
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
        var user = [0,0];
        var num=[];
        json.forEach(function(value,index,array){
            user[0] += parseInt(value.uniqueUserNum);
            user[1] += parseInt(value.vUserNum);

            var n={
                "y": value.updateTime,
                "a":value.weiboNum,
                "b":value.repostNum,
                "c":value.commentNum
            };
            num.push(n);
        });

        $('#dataTables-example').DataTable({
            data:json,
            columns: [
                { data: 'updateTime' },
                { data: 'weiboNum' },
                { data: 'repostNum' },
                { data: 'commentNum' }
            ]
        });

        Morris.Bar({
            element: 'morris-bar-chart',
            data: num ,
            xkey: 'y',
            ykeys: ['a', 'b' , 'c'],
            labels: ['weibonum', 'reportnum' , "commentnum"],
            hideHover: 'auto',
            resize: true
        });

        Morris.Donut({
            element: 'morris-donut-chart',
            data: [{
                label: "普通用户",
                value: user[0]
            }, {
                label: "大v",
                value: user[1]
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