package com.fhalo.application.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.fhalo.application.R;
import com.fhalo.application.activity.MySettingActivity;
import com.fhalo.application.adapter.NewsAdapter;
import com.fhalo.application.base.BaseInterface;
import com.fhalo.application.bean.InfoNews;

import java.util.ArrayList;
import java.util.List;

public class InfoFragment extends Fragment implements BaseInterface, OnClickListener {
	
	private ImageView setInfoImageView;
	private ListView infoNewsListView;
	private List<InfoNews> infoNewsList;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_info, null);
	}
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initView();
		initData();
		DataOper();
	}
	@Override
	public void initView() {
		setInfoImageView = (ImageView) getActivity().findViewById(R.id.info_setting);
		infoNewsListView = (ListView) getActivity().findViewById(R.id.info_news_listview);
	}
	@Override
	public void initData() {
		infoNewsList=new ArrayList<>();
		setInfoImageView.setOnClickListener(this);
	}
	@Override
	public void DataOper() {
		newsListOper();
		infoNewsListView.setAdapter(new NewsAdapter(getActivity(),infoNewsList));
		infoNewsListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
			}
		});
	}
	/**
	 * 向List中填充数据
	 * @description:
	 * @methodName: newsListOper
	 * @return: void
	 * @throws:
	 */
	private void newsListOper() {
		// TODO Auto-generated method stub
		for(int i=0;i<10;i++){
			InfoNews news=new InfoNews();
			news.setNewsName("景区新引进高端的安全报警机制");
			news.setNewsContent("商用宽带一般指的是专线宽带，和一般家用宽带有如下区别：" +
					"1、专线宽带指的是：固定ip、带宽独享、光纤专线宽带，" +
					"从用户处光纤直接接到运营商宽带服务器。" +
					"2、专线宽带对应的是共享宽带。共享宽带是小区或楼宇共同享用一定的带宽。" +
					"用户办理的宽带速率只是到小区或到楼宇交换机端口之间的连接最大速率。" +
					"如果其他人都不上网，可以享用办理的宽带速率。如果上网人多了，总出口容量不够，" +
					"网速就会变慢。");
			news.setNewsDate("2015-05-12");
			infoNewsList.add(news);
		}
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.info_setting:
			Intent intent=new Intent(getActivity(), MySettingActivity.class);
			startActivity(intent);
			break;
		}
	}
}
