package com.wangxr.textanlysis.entity;

import java.util.Map;

/**
 * @author xueren.wang
 * @email wangxr_it@sina.com
 * @date 2018/5/30.
 */
public class DataEntity {

    private int count;

    private Map<String, Integer> cpMap;

    public void setCount(int count) {
        this.count = count;
    }

    public void setCpMap(Map<String, Integer> cpMap) {
        this.cpMap = cpMap;
    }

    public int getCount() {
        return count;
    }

    public Map<String, Integer> getCpMap() {
        return cpMap;
    }
}
