package com.wangxr.textanlysis.util;

import com.wangxr.textanlysis.entity.DataEntity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 词频统计，和字词出现次数统计
 * @author xueren.wang
 * @email wangxr_it@sina.com
 * @date 2018/5/29.
 */
public class TextAnalysisUtil {

    /**
     * 从文件中统计词频和指定字词的出现次数
     * @param path 文件路径
     * @param key 被统计的字词
     * @return
     */
    public DataEntity getOccurrences(String path, String key){
        try {
            FileReader fileReader = new FileReader(URLDecoder.decode(path, "UTF-8"));
            int length = 500;
            char[] arr = new char[length];
            int num = 0;//统计的数量
            int charLength;//本次读取的字符数
            Map<String, Integer> cpMap = new HashMap<String, Integer>();
            while (length ==(charLength=fileReader.read(arr))){
                System.out.println(arr);
//                if(key.length()==1){
//                    for(int i=0;i<charLength;i++){
//                        if(arr[i] == key.toCharArray()[0])
//                            num++;
//                    }
//                }else{
                    int textIndex = 0;//字词当前遍历下标
                    int contentIndex = 0;//数组内容当前下标
                    while (contentIndex < length){

                        //统计词频开始
                        String contentChar = String.valueOf(arr[contentIndex]);
                        Integer count = cpMap.get(contentChar);
                        if(count == null){
                            cpMap.put(contentChar, 1);
                        }else{
                            cpMap.put(contentChar, count+1);
                        }
                        //结束

                        for(int j=0;j<key.length();j++){
                            if(contentIndex == length) break;
                            if(arr[contentIndex]!= key.charAt(j)){
                                break;
                            }else{
                                textIndex = j;
                                contentIndex++;
                                if(j == key.length()-1){
                                    num++;
                                }
                            }
                        }
                        contentIndex++;

                    }
                    System.out.println("num=" + num);
                    if(textIndex != 0){//处理临界情况，即匹配文字分两部分存在，一部分存在于前一次读取的尾部，一部分存在于后一次读取的首部
                        char[] _arr = new char[key.length() -textIndex];
                        fileReader.read(_arr);
                        for(int i= textIndex; i<_arr.length; i++){
                            if(_arr[i] != key.charAt(i)){
                                continue;
                            }
                            if(i == key.length()-1){
                                num++;
                            }
                        }
                    }

//                }
            }
            DataEntity dataEntity = new DataEntity();
            dataEntity.setCount(num);
            dataEntity.setCpMap(cpMap);
            System.out.println("总数为=" + num);
            System.out.println(cpMap);
            return dataEntity;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new DataEntity();
    }


    public static void main(String[] args) {
//        new TextAnalysisUtil().getOccurrences("结");
//        String content = "我是谁字,aa";
//        System.out.println(content.length());
//            new TextAnalysisUtil().getCp("我是谁，你睡水库水库的字速度快大块大块间谍打交道石榴树老爹,alll,/;'[]d[];'`1748593403%$#*&()", "字");
    }

    /**
     * 从文本中统计字词出现字数
     * @param content 文本内容
     * @param key 被统计的字词
     * @return
     */
    public int getCp(String content, String key){
        int length = content.length();
        int index = 0;
        Map<String, Integer> cpMap = new HashMap<String, Integer>();
        int num = 0;
        while (index < length){
            char c = content.charAt(index);
            Integer count = cpMap.get(String.valueOf(c));
            if(count == null){
                cpMap.put(String.valueOf(c), 0);
            }else{
                cpMap.put(String.valueOf(c), count+1);
            }
            if(key.equals(String.valueOf(c))){
                num++;
            }
            index++;
        }
        System.out.println(cpMap);
        System.out.println(num);
        return 0;
    }

}
