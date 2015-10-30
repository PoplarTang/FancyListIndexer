package com.poplar.fancyindexer;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.poplar.fancyindexer.adapter.MyAdapter;
import com.poplar.fancyindexer.domain.Cheeses;
import com.poplar.fancyindexer.domain.GoodMan;
import com.poplar.fancyindexer.ui.FancyIndexer;
import com.poplar.fancyindexer.ui.FancyIndexer.OnTouchLetterChangedListener;

public class MainActivity extends Activity {

	private TextView tv_index_center;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		tv_index_center = (TextView) findViewById(R.id.tv_index_center);
		
		final ListView lv_content = (ListView) findViewById(R.id.lv_content);
		
		final ArrayList<GoodMan> persons = new ArrayList<GoodMan>();
		
		// 填充数据, 并排序
		fillAndSortData(persons);
		
		lv_content.setAdapter(new MyAdapter(persons, this));
		
		
		FancyIndexer mFancyIndexer = (FancyIndexer) findViewById(R.id.bar);
		mFancyIndexer.setOnTouchLetterChangedListener(new OnTouchLetterChangedListener() {
			
			@Override
			public void onTouchLetterChanged(String letter) {
				System.out.println("letter: " + letter);
//				Util.showToast(getApplicationContext(), letter);
				
//				showLetter(letter);
				
				// 从集合中查找第一个拼音首字母为letter的索引, 进行跳转
				for (int i = 0; i < persons.size(); i++) {
					GoodMan goodMan = persons.get(i);
					String s = goodMan.getPinyin().charAt(0) + "";
					if(TextUtils.equals(s, letter)){
						// 匹配成功, 中断循环, 跳转到i位置
						lv_content.setSelection(i);
						break;
					}
				}
			}
		});
		
	}
	
	private Handler mHandler = new Handler();

	/**
	 * 显示字母提示
	 * @param letter
	 */
	protected void showLetter(String letter) {
		tv_index_center.setVisibility(View.VISIBLE);
		tv_index_center.setText(letter);
		
		// 取消掉刚刚所有的演示操作
		mHandler.removeCallbacksAndMessages(null);
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// 隐藏
				tv_index_center.setVisibility(View.GONE);
			}
		}, 1000);
		
	}

	/**
	 * 填充,排序
	 * @param persons
	 */
	private void fillAndSortData(ArrayList<GoodMan> persons) {
		String[] datas = null;
		boolean china = getResources().getConfiguration().locale.getCountry().equals("CN");
		datas = china ? Cheeses.NAMES : Cheeses.sCheeseStrings;
		for (int i = 0; i < datas.length; i++) {
			persons.add(new GoodMan(datas[i]));
		}
		// 排序
		Collections.sort(persons);
	}

}
