package com.fhalo.application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fhalo.application.R;
import com.fhalo.application.bean.InfoNews;

import java.util.List;

public class NewsAdapter extends BaseAdapter {
	
	private Context context;
	private List<InfoNews> infoNewsList;
	private boolean[] isItemLineMax;  //判断Item项的内容行数是否是完整的,初始化false
	public NewsAdapter(Context context,List<InfoNews> infoNewsList){
		this.context=context;
		this.infoNewsList=infoNewsList;
		isItemLineMax=new boolean[infoNewsList.size()];
		for (int i = 0; i < isItemLineMax.length; i++) {
			isItemLineMax[i]=false;
		}
	}
	@Override
	public int getCount() {
		return infoNewsList.size();
	}

	@Override
	public Object getItem(int position) {
		return infoNewsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		if (convertView==null) {
			viewHolder=new ViewHolder();
			convertView=LayoutInflater.from(context).inflate(R.layout.listview_fragment_info_news, null);
			viewHolder.newsName=(TextView) convertView.findViewById(R.id.txt_news_name);
			viewHolder.newsContent=(TextView) convertView.findViewById(R.id.txt_news_content);
			viewHolder.newsDate=(TextView) convertView.findViewById(R.id.txt_news_date);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		viewHolder.newsContent.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(!isItemLineMax[position]){
					/*for (int i = 0; i < isItemLineMax.length; i++) {
						if(i!=position){
							Log.i("shenquanxia", "position"+position);
							isItemLineMax[i]=false;
							viewHolder.newsContent.setMaxLines(2);
						}
					}*/
					viewHolder.newsContent.setMaxLines(100);
					isItemLineMax[position]=true;
				}else{
					viewHolder.newsContent.setMaxLines(2);
					isItemLineMax[position]=false;
				}
			}
		}); 
		viewHolder.newsName.setText(infoNewsList.get(position).getNewsName());
		viewHolder.newsContent.setText(infoNewsList.get(position).getNewsContent());
		viewHolder.newsDate.setText(infoNewsList.get(position).getNewsDate());
		return convertView;
	}
	static class ViewHolder{
		TextView newsName;
		TextView newsContent;
		TextView newsDate;
	}
}
