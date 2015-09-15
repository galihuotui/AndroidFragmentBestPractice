package com.example.fragmentbestpractice;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class NewsTitleFragment extends Fragment implements OnItemClickListener{

	private ListView newsTitleListView;
	
	private List<News> newsList;
	
	private NewsAdapter adapter;
	
	private boolean isTwoPane;

	@Override
	public void onAttach(Activity activity) {
				super.onAttach(activity);
				newsList = getNews();
				adapter = new NewsAdapter(activity, R.layout.news_item, newsList);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.news_title_frag, container, false);
		newsTitleListView = (ListView)view.findViewById(R.id.news_title_list_view);
		newsTitleListView.setAdapter(adapter);
		newsTitleListView.setOnItemClickListener(this);
		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
				
		News news = newsList.get(position);
		
		if(isTwoPane)
		{
			NewsContentFragment newsContentFragement = (NewsContentFragment)getFragmentManager()
					.findFragmentById(R.id.news_content_fragment);
			newsContentFragement.refresh(news.getTitle(), news.getContent());
			
		}
		else
		{
			NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
		}
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if(getActivity().findViewById(R.id.news_content_layout) != null)
		{
			isTwoPane = true;
		}
		else
		{
			isTwoPane = false;
		}
	}
	
	private List<News> getNews()
	{
		List<News> newsList = new ArrayList<News>();
		
		News news1 = new News();
		
		news1.setTitle("错误解决办法");
		news1.setContent("在搞android应用时，有时会碰到“android.content.ActivityNotFoundException”错误，"
				+ "			奇怪的是在一台机子上运行没问题，"
				+ "			而换到另一台机器就报这个错误了。网上大部分都是说AndroidManifest.xml中没有声明这个activity");
			
		newsList.add(news1);
		
		News news2 = new News();
		news2.setTitle("R文件丢失的解决方法");
		news2.setContent("一般来说,Android项目是的R文件是由系统生成的资源的定义,"
						+"但是偶尔会出现R文件不同步或是丢失的情况."

						+"不同步的情况,即在RES文件里面已经存在了一项,但是在R.java文件中却还没有定义,"
						+"这里可以点击,Project->Clean, 选择项目,然后选中''Start a build immediately'',即可重建R文件");
		newsList.add(news2);
		
		return newsList;
	}


	
}
