package com.fanzhang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanzhang.bean.DataBean;
import com.fanzhang.recyclerview.R;

import java.util.List;


public class StraggerdAdpater extends RecyclerView.Adapter<StraggerdAdpater.StraggerHolder> {
    private List<DataBean> mlist;
    private Context mcontext;

    /***传入初始化数据
     * @param context
     * @param list
     */
    public StraggerdAdpater(Context context,List<DataBean> list){
        this.mcontext = context;
        this.mlist = list;
    }

    /***
     * 当Itemview创建时的回调
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public StraggerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //参数：上下文、布局文件,null
        View v = View.inflate(mcontext, R.layout.item_straggered, null);
        //返回一个StraggerHolder
        return new StraggerHolder(v);
    }

    /****
     * 将要渲染新的Item的时候的回调
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(StraggerHolder holder, int position) {
        //传入将要渲染的item位置
        holder.setData(mlist.get(position));
    }

    /***
     * 返回list的数据量
     * @return
     */
    @Override
    public int getItemCount() {
        if(mlist!=null){
            return mlist.size();
        }
        return 0;
    }
    /***
     * 给Item布局内的组件赋值
     */
    public class StraggerHolder extends RecyclerView.ViewHolder {
        ImageView iv ;
        TextView tv;
        /***
         * 找到组件
         * @param itemView
         */
        public StraggerHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.item_straggered_iv);
            tv = (TextView) itemView.findViewById(R.id.item_straggered_tv);
        }
        /***
         * 给组件赋值
         * @param dataBean
         */
        public void setData(DataBean dataBean) {
            iv.setImageResource(dataBean.getIcon());
            tv.setText(dataBean.getContent());
        }
    }
}
