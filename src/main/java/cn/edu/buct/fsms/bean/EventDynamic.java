package cn.edu.buct.fsms.bean;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;

/**
 * Created by jinha on 2018/4/23.
 */
public class EventDynamic {
    private int dynamicId;
    private int eventId;
    private String updateTime;
    private int weiboNum;
    private int repostNum;
    private int commentNum;
    private int uniqueUserNum;
    private int vUserNum;
    private String sentimentNum;
    private double sentimentTendency;
    private double activityDegree;

    public int getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(int dynamicId) {
        this.dynamicId = dynamicId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getWeiboNum() {
        return weiboNum;
    }

    public void setWeiboNum(int weiboNum) {
        this.weiboNum = weiboNum;
    }

    public int getRepostNum() {
        return repostNum;
    }

    public void setRepostNum(int repostNum) {
        this.repostNum = repostNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getUniqueUserNum() {
        return uniqueUserNum;
    }

    public void setUniqueUserNum(int uniqueUserNum) {
        this.uniqueUserNum = uniqueUserNum;
    }

    public int getvUserNum() {
        return vUserNum;
    }

    public void setvUserNum(int vUserNum) {
        this.vUserNum = vUserNum;
    }

    public String getSentimentNum() {
        return sentimentNum;
    }

    public void setSentimentNum(String sentimentNum) {
        this.sentimentNum = sentimentNum;
    }

    public double getSentimentTendency() {
        return sentimentTendency;
    }

    public void setSentimentTendency(double sentimentTendency) {
        this.sentimentTendency = sentimentTendency;
    }

    public double getActivityDegree() {
        return activityDegree;
    }

    public void setActivityDegree(double activityDegree) {
        this.activityDegree = activityDegree;
    }

    @Override
    public String toString() {
        return "EventDynamic{" +
                "dynamicId=" + dynamicId +
                ", eventId=" + eventId +
                ", updateTime='" + updateTime + '\'' +
                ", weiboNum=" + weiboNum +
                ", repostNum=" + repostNum +
                ", commentNum=" + commentNum +
                ", uniqueUserNum=" + uniqueUserNum +
                ", vUserNum=" + vUserNum +
                ", sentimentNum='" + sentimentNum + '\'' +
                ", sentimentTendency=" + sentimentTendency +
                ", activityDegree=" + activityDegree +
                '}';
    }

    public JSONObject toJson(){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("dynamicId",dynamicId);
            jsonObject.put("eventId",eventId);
            jsonObject.put("updateTime",updateTime);
            jsonObject.put("weiboNum",weiboNum);
            jsonObject.put("repostNum",repostNum);
            jsonObject.put("commentNum",commentNum);
            jsonObject.put("uniqueUserNum",uniqueUserNum);
            jsonObject.put("vUserNum",vUserNum);
            jsonObject.put("sentimentNum",sentimentNum);
            jsonObject.put("sentimentTendency",sentimentTendency);
            jsonObject.put("activityDegree",activityDegree);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
