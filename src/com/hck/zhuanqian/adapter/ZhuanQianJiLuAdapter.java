package com.hck.zhuanqian.adapter;

import java.util.List;

import com.hck.kedouzq.R;
import com.hck.zhuanqian.bean.OrderBean;
import com.hck.zhuanqian.data.ZhuanQianJiLu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ZhuanQianJiLuAdapter extends BaseAdapter {
	private ZhuanQianJiLu zhuanQianJiLu;
	private Context context;

	public ZhuanQianJiLuAdapter(ZhuanQianJiLu zhuanQianJiLu, Context context) {
		this.zhuanQianJiLu = zhuanQianJiLu;
		this.context = context;
	}

	@Override
	public int getCount() {
		return zhuanQianJiLu.getOrderBeans().size();
	}

	@Override
	public Object getItem(int arg0) {
		return zhuanQianJiLu.getOrderBeans().get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		if (view == null) {
			view = LayoutInflater.from(context).inflate(
					R.layout.zhuanqi_list_item, null);
			viewHolder = new ViewHolder();
			viewHolder.contentTextView = (TextView) view
					.findViewById(R.id.zhuanqian_content);
			viewHolder.timeTextView = (TextView) view
					.findViewById(R.id.zhuanqian_time);
			viewHolder.stateTextView =(TextView) view.findViewById(R.id.state);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		OrderBean bean = null;
		bean = zhuanQianJiLu.getOrderBeans().get(arg0);
		boolean state =bean.isChuLi();
		String addString =null;
		if (state) {
			addString=" 已处理";
			viewHolder.stateTextView.setText(addString);
			viewHolder.stateTextView.setTextColor(context.getResources().getColor(R.color.text_9999));
		}
		else {
			addString=" 处理中";
			viewHolder.stateTextView.setText(addString);
			viewHolder.stateTextView.setTextColor(context.getResources().getColor(R.color.red));
		}
		
		viewHolder.contentTextView.setText(bean.getContent().replaceAll("h", ""));
		String timeString = bean.getTime();
		viewHolder.timeTextView.setText(timeString.substring(0, 11));
		return view;
	}

	static class ViewHolder {
		TextView contentTextView,stateTextView;
		TextView timeTextView;
	}
	
	public void notifyDataSetChanged(ZhuanQianJiLu zhuanQianJiLu){
		this.zhuanQianJiLu.setOrderBeans(zhuanQianJiLu.getOrderBeans());
		this.notifyDataSetChanged();
	}

}
