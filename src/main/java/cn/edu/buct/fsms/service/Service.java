package cn.edu.buct.fsms.service;


import cn.edu.buct.fsms.bean.Article;
import cn.edu.buct.fsms.bean.Event;
import cn.edu.buct.fsms.bean.EventDynamic;
import cn.edu.buct.fsms.dao.Dao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.EventListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by jinha on 2017/5/2.
 */
@Path("/fsms")
@Produces("application/json;charset=gbk")
@Consumes("application/json")
public class Service {

    @GET
    @Path("/geteventdynamic")
    public Response getEventDynamic(@QueryParam("eventId") int Id){
        Dao dao = new Dao();
        JSONArray jsonArray = new JSONArray();
        List<EventDynamic> eventDynamic = dao.getEventDynamic(Id);
        for (EventDynamic event:eventDynamic) {
            jsonArray.add(event.toJson());
        }
        return Response.status(200).entity(jsonArray.toString()).build();
    }

    @GET
    @Path("/getevent")
    public  Response getEvent(){
        Dao dao = new Dao();
        JSONArray jsonArray = new JSONArray();
        List<Event> events = dao.getEventList();
        for(Event event :events){
            event.setLocation(event.getLocation().split(" | ")[0]);
            JSONObject jsonObject = event.toJson();
            String s = dao.getArticle(event.getCenturalWeiboID()).getArticle_content();
            if(s.length()>45)
                s = s.substring(0,45)+"...";
            jsonObject.put("article",s);
            jsonObject.discard("centuralWeiboID");
            jsonArray.add(jsonObject);
        }
        return Response.status(200).entity(jsonArray.toString()).build();
    }

    @GET
    @Path("/getarticlelist")
    public  Response getArticleList(@QueryParam("eventId") int Id){
        Dao dao = new Dao();
        JSONArray jsonArray = new JSONArray();
        List<Article> articleList = dao.getArticleList(Id);
        for (Article article:articleList) {
            jsonArray.add(article.toJson());
        }
        return Response.status(200).entity(jsonArray.toString()).build();
    }

    @GET
    @Path("/gettf")
    public Response getTF(@QueryParam("eventId") int Id){
        Dao dao = new Dao();
        JSONArray tf = new JSONArray();
        String s = dao.getEvent(Id).getCenturalTF();
        JSONObject jsonObject = JSONObject.fromObject(s);
        for (Iterator it = jsonObject.keys(); it.hasNext(); ) {
            String key = (String) it.next();
            JSONObject j = new JSONObject();
            j.put("key",key);
            j.put("value",jsonObject.get(key));
            tf.add(j);
        }
        return Response.status(200).entity(tf.toString()).build();
    }

    @GET
    @Path("/getinfo")
    public Response getInfo(@QueryParam("eventId") int Id){
        Dao dao = new Dao();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("alleventcount",dao.getAllEventCount());
        jsonObject.put("allarticalcount",dao.getAllArticalCount());
        jsonObject.put("articalcount",dao.getArticalCount(Id));

        List<EventDynamic> eventDynamics = dao.getEventDynamic(Id);
        int hot = 0;
        for (EventDynamic e:eventDynamics) {
            hot+=e.getActivityDegree();
        }
        if(hot/eventDynamics.size()>1)
            jsonObject.put("hot","hot");
        else if(hot/eventDynamics.size()<0.5)
            jsonObject.put("hot","cold");
        else jsonObject.put("hot","mid");
        return Response.status(200).entity(jsonObject.toString()).build();
    }
}
