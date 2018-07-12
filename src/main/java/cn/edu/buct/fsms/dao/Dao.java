package cn.edu.buct.fsms.dao;

import cn.edu.buct.fsms.bean.Article;
import cn.edu.buct.fsms.bean.Event;
import cn.edu.buct.fsms.bean.EventDynamic;
import cn.edu.buct.fsms.dbclient.DataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by jinha on 2018/4/23.
 */
public class Dao {
    private SqlSession sqlSession = DataSource.getSqlMapClient();

    public List<EventDynamic> getEventDynamic(int Id){
        List<EventDynamic> eventDynamicList= null;
        if(sqlSession  !=null){
            eventDynamicList = sqlSession.selectList("getEventDynamic",Id);
        }
        return eventDynamicList;
    }

    public List<Event> getEventList(){
        List<Event> eventList= null;
        if(sqlSession!=null){
            eventList = sqlSession.selectList("getEventList");
        }
        return eventList;
    }

    public Article getArticle(int Id) {
        Article article = null;
        if (sqlSession  != null) {
            article = sqlSession.selectOne("getArticle",Id);
        }
        return article;
    }

    public List<Article> getArticleList(int Id){
        List<Article> article = null;
        if (sqlSession != null) {
            article = sqlSession.selectList("getArticleList",Id);
        }
        return article;
    }

    public Event getEvent(int Id){
        Event event = null;
        if(sqlSession !=null){
            event = sqlSession.selectOne("getEvent",Id);
        }
        return event;
    }

    public int getAllEventCount(){
        int count = 0;
        if(sqlSession !=null){
            count = sqlSession.selectOne("getAllEventCount");
        }
        return count;
    }

    public int getAllArticalCount(){
        int count = 0;
        if(sqlSession!=null){
            count = sqlSession.selectOne("getAllArticalCount");
        }
        return count;
    }

    public int getArticalCount(int Id){
        int count = 0;
        if(sqlSession!=null){
            count = sqlSession.selectOne("getArticalCount",Id);
        }
        return count;
    }
}
