package com.zjzc.manage.utils.others;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class InvokeUtils {

    static Logger logger = LoggerFactory.getLogger(InvokeUtils.class);
    public static final String APPLICATION_JSON = "application/json;charset=UTF-8";
    public static final String APPLICATION_X_WWW_FORM_URLENCODED =
            "application/x-www-form-urlencoded;charset=UTF-8";

    public static JSONObject invokeGetRequest(String strUrl) {
        URI uri = buildUri(strUrl);
        HttpGet httpget = new HttpGet(uri);
        return parse2JSONStr(InvokeUtils.invoke(httpget));
    }

    private static URI buildUri(String strUrl) {
        try {
            logger.info("request url :{}", strUrl);
            URL url = new URL(strUrl);
            URI uri =
                    new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(),
                            url.getPath(), url.getQuery(), null);
            return uri;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject invokeFormRequest(String url, Map<String, String> paramMap) {
        return invokeFormRequest(url, paramMap, null);
    }

    public static JSONObject invokeFormRequest(String url, Map<String, String> paramMap,
            String contentType) {
        return parse2JSONStr(invokeFormRequest2Str(url, paramMap, contentType));
    }

    public static String invokeFormRequest2Str(String url, Map<String, String> paramMap,
            String contentType) {
        URI uri = buildUri(url);
        HttpPost httpPost = new HttpPost(uri);
        if (paramMap != null) {
            NvPair nvPair = new NvPair();
            for (Entry<String, String> entry : paramMap.entrySet()) {
                nvPair.addPair(entry.getKey(), entry.getValue());
            }
            httpPost.setEntity(nvPair.returnEntity());
        }

        if (!StringUtils.isBlank(contentType)) {
            httpPost.setHeader(HTTP.CONTENT_TYPE, contentType);
        } else {
            httpPost.setHeader(HTTP.CONTENT_TYPE, InvokeUtils.APPLICATION_JSON);
        }
        return InvokeUtils.invoke(httpPost, true);
    }

    public static JSONObject invokePostRequest(String url) {
        return invokePostRequest(url, null);
    }

    public static JSONObject invokePostRequest(String strUrl, JSONObject json) {
        URI uri = buildUri(strUrl);
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setHeader(HTTP.CONTENT_TYPE, InvokeUtils.APPLICATION_JSON);
        if (json != null) {
            StringEntity entity = new StringEntity(json.toJSONString(), Consts.UTF_8);
            httpPost.setEntity(entity);
        }

        return parse2JSONStr(InvokeUtils.invoke(httpPost));
    }

    public static String invoke(HttpUriRequest request) {
        return invoke(request, false);
    }

    public static String invoke(HttpUriRequest request, boolean debugLevel) {
        try {
            HttpClient client = HttpClients.createDefault();
            HttpResponse rs = client.execute(request);
            InputStream input = rs.getEntity().getContent();
//            String jsonStr = IOUtils.toString(input, Consts.UTF_8);
            String jsonStr = IOUtils.toString(input, Charset.forName("GBK"));
            if (debugLevel) {
                logger.debug("response body : {}", jsonStr);
            } else {
                logger.info("response body : {}", jsonStr);
            }
            return jsonStr;

        } catch (ClientProtocolException e) {
            logger.error("ClientProtocolException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        }
        return "";
    }

    private static JSONObject parse2JSONStr(String jsonStr) {
        JSONObject json = null;
        try {
            json = (JSONObject) JSONObject.parse(jsonStr);
        } catch (Exception e) {
            logger.error("json trans error: {}", jsonStr);
            logger.error("error detail ", e);
            return null;
        }
        if (json.containsKey("error")) {
            logger.error("rsp error: {}", jsonStr);
            return null;
        } else {
            return json;
        }
    }



}
