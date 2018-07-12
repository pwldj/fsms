package cn.edu.buct.fsms.bean;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * Created by jinha on 2018/4/25.
 */
public class Article {
    private int event_id;
    private int article_id;
    private String media_type_code;
    private String  article_url;
    private String article_title;
    private String article_abstract;
    private String article_content;
    private String article_author;
    private String article_source;

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getMedia_type_code() {
        return media_type_code;
    }

    public void setMedia_type_code(String media_type_code) {
        this.media_type_code = media_type_code;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_abstract() {
        return article_abstract;
    }

    public void setArticle_abstract(String article_abstract) {
        this.article_abstract = article_abstract;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public String getArticle_author() {
        return article_author;
    }

    public void setArticle_author(String article_author) {
        this.article_author = article_author;
    }

    public String getArticle_source() {
        return article_source;
    }

    public void setArticle_source(String article_source) {
        this.article_source = article_source;
    }

    @Override
    public String toString() {
        return "Article{" +
                "event_id=" + event_id +
                ", article_id=" + article_id +
                ", media_type_code='" + media_type_code + '\'' +
                ", article_url='" + article_url + '\'' +
                ", article_title='" + article_title + '\'' +
                ", article_abstract='" + article_abstract + '\'' +
                ", article_content='" + article_content + '\'' +
                ", article_author='" + article_author + '\'' +
                ", article_source='" + article_source + '\'' +
                '}';
    }

    public JSONObject toJson(){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("event_id",event_id);
            jsonObject.put("article_id",article_id);
            jsonObject.put("media_type_code",media_type_code);
            jsonObject.put("article_url",article_url);
            jsonObject.put("article_title",article_title);
            jsonObject.put("article_abstract",article_abstract);
            jsonObject.put("article_content",article_content);
            jsonObject.put("article_author",article_author);
            jsonObject.put("article_source",article_source);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return  jsonObject;
    }
}
