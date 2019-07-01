package com.zjzc.manage.utils.others;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

// 简化参数封装的类
public class NvPair {
    private List<NameValuePair> params = new ArrayList<NameValuePair>();

    public NvPair() {}

    public NvPair(String key, String value) {
        NameValuePair pair = new BasicNameValuePair(key, value);
        params.add(pair);
    }

    public NvPair addPair(String key, String value) {
        NameValuePair pair = new BasicNameValuePair(key, value);
        params.add(pair);
        return this;
    }

    public UrlEncodedFormEntity returnEntity() {
        return new UrlEncodedFormEntity(params, Consts.UTF_8);
    }
}
