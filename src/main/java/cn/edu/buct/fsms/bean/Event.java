package cn.edu.buct.fsms.bean;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * Created by jinha on 2018/4/24.
 */
public class Event {
    private int eventId ;
    private String starttime ;
    private String location;
    private String category;
    private int centuralWeiboID;
    private String centuralTF;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCenturalWeiboID() {
        return centuralWeiboID;
    }

    public void setCenturalWeiboID(int centuralWeiboID) {
        this.centuralWeiboID = centuralWeiboID;
    }

    public String getCenturalTF() {
        return centuralTF;
    }

    public void setCenturalTF(String centuralTF) {
        this.centuralTF = centuralTF;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", starttime='" + starttime + '\'' +
                ", location='" + location + '\'' +
                ", category='" + category + '\'' +
                ", centuralWeiboID=" + centuralWeiboID +
                ", centuralTF=" + centuralTF +
                '}';
    }

    public JSONObject toJson(){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("eventId",eventId);
            jsonObject.put("starttime",starttime);
            jsonObject.put("location",location);
            jsonObject.put("category",category);
            jsonObject.put("centuralWeiboID",centuralWeiboID);
            jsonObject.put("centuralTF",centuralTF);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return  jsonObject;
    }
}
