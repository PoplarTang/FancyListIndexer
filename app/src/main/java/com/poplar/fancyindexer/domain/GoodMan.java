package com.poplar.fancyindexer.domain;

import com.poplar.fancyindexer.utils.PinyinUtil;

public class GoodMan implements Comparable<GoodMan>{

	private String name;
	private String pinyin;
	
	
	public GoodMan(String name) {
		super();
		this.name = name;
		this.pinyin = PinyinUtil.getPinyin(name);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPinyin() {
		return pinyin;
	}
	
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	@Override
	public int compareTo(GoodMan another) {
		return pinyin.compareTo(another.pinyin);
	}

}
