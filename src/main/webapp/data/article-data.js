
$(function() {
    $.ajax({
        url: "/api/fsms/getarticlelist?eventId="+ GetQueryString("eventId"),
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
        var source = [0,0,0,0,0,0,0,0];
        json.forEach(function(value,index,array){
            if(value.media_type_code == "A")
                source[0]++;
            else if(value.media_type_code == "B")
                source[1]++;
            else if(value.media_type_code == "F")
                source[2]++;
            else if(value.media_type_code == "G")
                source[3]++;
            else if(value.media_type_code == "M")
                source[4]++;
            else if(value.media_type_code == "V")
                source[5]++;
            else if(value.media_type_code == "X")
                source[6]++;
            else
                source[7]++;
            value.article_url = '<button type="button" onclick="window.location.href='+value.article_url+
                ' " class="btn btn-primary">原文</button>'
        });

        Morris.Donut({
            element: 'morris-donut-chart',
            data: [{
                label: "百度知道",
                value: source[0]
            }, {
                label: "新浪博客",
                value: source[1]
            }, {
                label: "论坛",
                value: source[2]
            }, {
                label: "贴吧",
                value: source[3]
            }, {
                label: "微博",
                value: source[4]
            }, {
                label: "视频",
                value: source[5]
            }, {
                label: "微信",
                value: source[6]
            }, {
                label: "其他",
                value: source[7]
            }],
            resize: true
        });



        $('#dataTables-example').DataTable({
            data:json,
            columns: [
                { data: 'article_id' },
                { data: 'article_title' },
                { data: 'article_source' },
                { data: 'article_url' }
            ]
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