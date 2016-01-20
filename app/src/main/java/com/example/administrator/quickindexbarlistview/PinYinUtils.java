package com.example.administrator.quickindexbarlistview;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by Administrator on 2016/1/20.
 * 汉字转换拼音
 */
public class PinYinUtils {
    public static String getPinyin(String hanzi){
        String result="";
        HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
        hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);//设置大写
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//去除音标

        char[] chars = hanzi.toCharArray();//将汉字转换为char类型数组
        for (int i = 0; i < chars.length; i++) {
            char cha = chars[i];
            if (Character.isWhitespace(cha)) continue;//如果是空格 就进行下一次
            if (cha>127){//理论上认为可以是个汉字
                try {
                    String[] strings = PinyinHelper.toHanyuPinyinStringArray(cha, hanyuPinyinOutputFormat);//解析拼音
                    if (strings==null){//如果是全角字符
                        result += cha;
                    }else{
                        result+=strings[0];
                    }
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    result+=cha;//如果转换失败  那么也加入到返回结果里面
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            }else{
                result+=cha;//如果不是汉字  直接加入
            }
        }


        return result;
    }
}
