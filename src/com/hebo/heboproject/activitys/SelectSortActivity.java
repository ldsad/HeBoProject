package com.hebo.heboproject.activitys;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hebo.heboproject.R;
import com.hebo.heboproject.adapter.SelectSecondMenuAdapter;
import com.hebo.heboproject.adapter.SelectFristMenuAdapter;
import com.hebo.heboproject.utils.Pull_ListView;

public class SelectSortActivity extends PublicActivity {

	public ListView secondListView;
	Pull_ListView fristListView;
	SelectSecondMenuAdapter selectSecondMenuAdapter;
	SelectFristMenuAdapter selectFristMenuAdapter;
	public int foodposition;
	public String leixingname;

	private int last_item = -1;

	private int firstIndex = 0;

	// private TextView oldView;
	// private List<String> lst = new ArrayList<String>();
	// private List<String> lst1 = new ArrayList<String>();

	String secondMenu[][] = new String[][] {
			new String[] {"全部","湘菜","川菜", "粤菜", "湘菜", "东北菜", "台湾菜",
					"新疆/清真", "素菜", "火锅", "自助餐", "小吃快餐", "日本", "韩国料理", "东南亚菜",
					"西餐", "面包甜点", "其他" },
			new String[] { "全部休闲娱乐", "咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺",
					"公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术", "DIY手工坊", "桌球馆",
					"桌面游戏", "更多休闲娱乐" },
			new String[] { "22全部购物", "综合商场", "服饰鞋包", "运动户外", "珠宝饰品", "化妆品",
					"数码家电", "亲子购物", "家居建材", "书店", "书店", "眼镜店", "特色集市",
					"更多购物场所", "食品茶酒", "超市/便利店", "药店" },
			new String[] { "33全部休闲娱乐", "咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺",
					"公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术", "DIY手工坊", "桌球馆",
					"桌面游戏", "更多休闲娱乐" },
			new String[] { "44全", "咖啡厅", "酒吧", "茶馆", "KTV", "游乐游艺", "公园",
					"景点/郊游", "洗浴", "足浴按摩", "文化艺术", "DIY手工坊", "桌球馆", "桌面游戏",
					"更多休闲娱乐" },
			new String[] { "55全部", "咖啡厅", "酒吧", "茶馆", "电影院", "游乐游艺", "公园",
					"景点/郊游", "洗浴", "足浴按摩", "文化艺术", "DIY手工坊", "桌球馆", "桌面游戏",
					"更多休闲娱乐" },
			new String[] { "66全部休", "咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺",
					"公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术", "DIY手工坊", "桌球馆",
					"桌面游戏", "更多休闲娱乐" },
	/*
	 * new String[] { "全部休闲", "咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺", "公园",
	 * "景点/郊游", "洗浴", "足浴按摩", "文化艺术", "DIY手工坊", "桌球馆", "桌面游戏", "更多休闲娱乐" }, new
	 * String[] { "全部休闲娱", "咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺", "公园",
	 * "景点/郊游", "洗浴", "足浴按摩", "文化艺术", "DIY手工坊", "桌球馆", "桌面游戏" }, new String[] {
	 * "全部休闲娱乐", "咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺", "公园", "景点/郊游", "洗浴",
	 * "足浴按摩", "文化艺术", "DIY手工坊", "桌球馆", "桌面游戏", "更多休闲娱乐" }, new String[] {
	 * "全部休闲aaa", "咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺", "公园", "景点/郊游", "洗浴",
	 * "足浴按摩", "文化艺术", "DIY手工坊", "桌球馆", "桌面游戏" },
	 */};
	/*
	 * String food[] = new String[] { "全部频道", "美食", "休闲娱乐", "购物", "酒店", "丽人",
	 * "运动健身", "结婚", "亲子", "爱车", "生活服务" };
	 */
	String firstMenu[] = new String[] { "美食", "休闲娱乐", "休闲娱乐", "购物", "酒店", "丽人",
			"运动健身" };

	int image[] = new int[] { R.drawable.ic_category_0,
			R.drawable.ic_category_10, R.drawable.ic_category_30,
			R.drawable.ic_category_20, R.drawable.ic_category_60,
			R.drawable.ic_category_50, R.drawable.ic_category_45,
			R.drawable.ic_category_50, R.drawable.ic_category_70,
			R.drawable.ic_category_65, R.drawable.ic_category_80 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_sort);

		selectFristMenuAdapter = new SelectFristMenuAdapter(
				SelectSortActivity.this, firstMenu, image, last_item);
		fristListView = (Pull_ListView) findViewById(R.id.fristListView);
		fristListView.setAdapter(selectFristMenuAdapter);
		fristListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				foodposition = position;
				selectFristMenuAdapter.setSelectedPosition(position);
				selectFristMenuAdapter.notifyDataSetInvalidated();
				selectSecondMenuAdapter = new SelectSecondMenuAdapter(
						SelectSortActivity.this, secondMenu, position); // 这个position决定了第一级菜单的下标

				firstIndex = position;

				secondListView = (ListView) findViewById(R.id.secondListView);
				secondListView.setDivider(null);
				secondListView.setAdapter(selectSecondMenuAdapter);
				secondListView
						.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								Log.i("===================", "=============");
								switch (firstIndex) {
								case 0:
									switch (position) {
									case 0:
										Toast.makeText(SelectSortActivity.this, firstIndex + "...." + position, 3).show();
										onBackPressed();
										//MyMapActivity.searchByKey(secondMenu[firstIndex][1]);
										//MyMapActivity.searchByKey(new String [] {"餐馆","饭店","炒菜","小吃","快餐","自助","清真","西餐","料理"});
										MyMapActivity.searchByKey(new String [] {"餐馆","饭店"});
										break;
									case 1:
										Toast.makeText(SelectSortActivity.this, firstIndex + "...." + position, 3).show();
										onBackPressed();
										MyMapActivity.searchByKey(secondMenu[firstIndex][position]);
										break;
									case 2:
										Toast.makeText(SelectSortActivity.this, firstIndex + "...." + position, 3).show();
										onBackPressed();
										MyMapActivity.searchByKey(secondMenu[firstIndex][position]);
										break;
									case 3:
										Toast.makeText(SelectSortActivity.this, firstIndex + "...." + position, 3).show();
										onBackPressed();
										MyMapActivity.searchByKey(secondMenu[firstIndex][position]);
										break;
									case 4:
										Toast.makeText(SelectSortActivity.this, firstIndex + "...." + position, 3).show();
										onBackPressed();
										MyMapActivity.searchByKey(secondMenu[firstIndex][position]);
										break;
									default:
										break;
									}
									break;
								case 1:
									switch (position) {
									case 0:
										Toast.makeText(SelectSortActivity.this, firstIndex + "...." + position, 3).show();
										break;
									case 1:
										Toast.makeText(SelectSortActivity.this, firstIndex + "...." + position, 3).show();
										break;
									case 2:
										Toast.makeText(SelectSortActivity.this, firstIndex + "...." + position, 3).show();
										break;
									case 3:
										Toast.makeText(SelectSortActivity.this, firstIndex + "...." + position, 3).show();
										break;
									case 4:
										Toast.makeText(SelectSortActivity.this, firstIndex + "...." + position, 3).show();
										break;
									default:
										break;
									}
									break;
								case 2:

									break;
								case 3:

									break;
								case 4:

									break;
								case 5:

									break;
								case 6:

									break;

								default:
									break;
								}
							}
						});
			}
		});
	}

	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}

	final Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// adapter.notifyDataSetChanged();

		}

	};
	Runnable runnable = new Runnable() {

		@Override
		public void run() {
			handler.sendMessage(new Message());
		}
	};

	public void update() {
		handler.postDelayed(runnable, 5000);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
	}

}
