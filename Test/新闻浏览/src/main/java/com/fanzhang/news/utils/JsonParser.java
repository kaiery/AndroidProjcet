package com.fanzhang.news.utils;

import com.fanzhang.news.bean.NewsBean;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 范张 on 2015-11-22.
 */
public class JsonParser {

    public static List<NewsBean> getNewsList(InputStream is){
        String text = StreamUtils.readStreamToString(is);
        List<NewsBean> list = new ArrayList<NewsBean>();
        try {
            JSONArray jsonArray = new JSONArray(text);
            for (int i = 0; i < jsonArray.length(); i++) {
                NewsBean nb = new NewsBean();
                nb.setCommentcount((String) ((JSONObject) jsonArray.get(i)).get("commentcount"));
                nb.setDescr((String) ((JSONObject) jsonArray.get(i)).get("descr"));
                nb.setImage((String) ((JSONObject) jsonArray.get(i)).get("image"));
                nb.setShowname((String) ((JSONObject) jsonArray.get(i)).get("showname"));
                nb.setStamp((String) ((JSONObject) jsonArray.get(i)).get("stamp"));
                nb.setTitle((String) ((JSONObject) jsonArray.get(i)).get("title"));
                nb.setUserid((String) ((JSONObject) jsonArray.get(i)).get("userid"));
                nb.setTypeid((String) ((JSONObject) jsonArray.get(i)).get("typeid"));
                nb.setTypename((String) ((JSONObject) jsonArray.get(i)).get("typename"));
                list.add(nb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
