package com.poplar.fancyindexer.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
	
	/**
	 * 根据汉字获取对应的拼音
	 * @param str
	 * @return
	 */
	public static String getPinyin(String str) {
		// 黑马 -> HEIMA
		// 设置输出配置
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		// 设置大写
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		// 设置不需要音调
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		StringBuilder sb = new StringBuilder();

		// 获取字符数组
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			// 如果是空格, 跳过当前的循环
			if (Character.isWhitespace(c)) {
				continue;
			}

			if (c > 128 || c < -127) {
				// 可能是汉字
				try {
					// 根据字符获取对应的拼音. 黑 -> HEI , 单 -> DAN , SHAN
					String s = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
					sb.append(s);

				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				// *&$^*@654654LHKHJ
				// 不需要转换, 直接添加
				sb.append(c);
			}
		}

		return sb.toString();
	}
}
