package com.movitech.framework.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Oct 21, 2014, 7:44:06 PM
 * @Description: ViewPagerAdapter是PagerAdapter的子类，它是View的PagerAdapter
 *
 **/

public class ViewPagerAdapter extends PagerAdapter {

	protected List<View> views;

	public ViewPagerAdapter() {
		this.views = new ArrayList<View>();
	}

	public void addAll(List<View> datas) {
		this.views.clear();
		this.views.addAll(datas);

		this.notifyDataSetChanged();
	}

	public void addView(View view) {
		this.views.add(view);

		this.notifyDataSetChanged();
	}

	public void removeAll() {
		this.views.clear();
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return this.views.size();
	}

	public View getView(int position) {
		return this.views.get(position);
	}

	public List<View> getViews() {
		return this.views;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(views.get(position));

		return this.views.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(this.views.get(position));
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}
}
