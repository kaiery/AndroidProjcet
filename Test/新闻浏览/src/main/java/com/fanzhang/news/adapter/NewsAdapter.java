package com.fanzhang.news.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanzhang.news.R;
import com.fanzhang.news.bean.NewsBean;

import java.util.List;

/**
 * Created by 范张 on 2015-11-22.
 */
public class NewsAdapter extends BaseAdapter {
    private final Context context;
    private final List<NewsBean> list;

    public NewsAdapter(List<NewsBean> list,Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View inflate = View.inflate(context, R.layout.news_item, null);

        ImageView iv = (ImageView) inflate.findViewById(R.id.news_item_iv);
        TextView descr = (TextView) inflate.findViewById(R.id.news_item_descr);
        TextView showname = (TextView) inflate.findViewById(R.id.news_item_showname);
        TextView stamp = (TextView) inflate.findViewById(R.id.news_item_stamp);
        TextView title = (TextView) inflate.findViewById(R.id.news_item_title);
        TextView typename = (TextView) inflate.findViewById(R.id.news_item_typename);

        NewsBean newsBean = list.get(position);
        //TODO
        //iv.setImageURI();
        descr.setText(newsBean.getDescr());
        showname.setText(newsBean.getShowname());
        stamp.setText(newsBean.getStamp());
        title.setText(newsBean.getTitle());

        if(newsBean.getTypeid().equals("0")){
            typename.setBackgroundResource(R.drawable.border_blue);
            typename.setTextColor(0xff0D73FF);
        }else if(newsBean.getTypeid().equals("1")){
            typename.setBackgroundResource(R.drawable.border_orange);
            typename.setTextColor(0xffFF7F0D);
        }else{
            typename.setBackgroundResource(R.drawable.border_green);
            typename.setTextColor(0xff156F06);
        }
        typename.setText(newsBean.getTypename());

        return inflate;
    }
}
