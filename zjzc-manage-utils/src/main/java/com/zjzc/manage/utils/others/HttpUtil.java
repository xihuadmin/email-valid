package com.zjzc.manage.utils.others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Future;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http请求工具类（POST）
 *
 * @author zhongwen ho
 * @createDate 2015-2-9
 */
public class HttpUtil {

	/**
	 * 日志打印
	 */
	protected static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	private final static int TIME_OUT = 60000;

	private static final int MAX_CONNECTION_TOTAL = 300;
	private static final String DEFAULT_CHARSET = "UTF-8";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String APPLICATION_JSON = "application/json";

	/**
	 * 向指定 URL 发送POST方法的请求(表单形式，非json)
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer();
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setConnectTimeout(TIME_OUT);
            conn.setReadTimeout(TIME_OUT);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 设置超时时间
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			logger.error("请求发送错误，异常信息：" + e);
			return null;
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
			}
		}
		return sb.toString();
	}
	
	
	public static String sendPost(String url, Map<String, String> map) {
        StringBuilder param = new StringBuilder();
        if (map != null) {
            Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                if (param.length() > 0) {
                    param.append('&');
                }
                Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                String value = entry.getValue();
                try {
                    param.append(key).append('=').append(URLEncoder.encode(value,
                                                                           "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    logger.info(e.getMessage(), e);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader in = null;
        try {
            logger.info("get data url :" + url + "?" + param.toString());
            URL postURL = new URL(url);
            HttpURLConnection con = (HttpURLConnection) postURL.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setConnectTimeout(40000);
            con.setReadTimeout(40000);
            con.setRequestMethod("POST");
            PrintWriter out = new PrintWriter(con.getOutputStream());
            out.print(param.toString());
            out.close();
            in = new BufferedReader(
                                    new InputStreamReader(
                                                          con.getInputStream(),
                                                          "UTF-8"));
            String aline;
            while (null != (aline = in.readLine())) {
                sb.append(aline).append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(url + "|" + e.getMessage(), e);
            return "";
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.info(e.getMessage(), e);
                }
                in = null;
            }
        }
        return sb.toString();
    }
	

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost2(String url, String param) throws Exception {
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer();
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setConnectTimeout(TIME_OUT);
            conn.setReadTimeout(TIME_OUT);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
		}
		// 使用finally块来关闭输出流、输入流
		finally {

			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			}

		}
		return sb.toString();
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendGet(String url) throws Exception {
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer();
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setConnectTimeout(TIME_OUT);
            conn.setReadTimeout(TIME_OUT);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			sb = new StringBuffer();
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static String sendPostGBK(String url, String param) throws Exception {
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer();
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setConnectTimeout(TIME_OUT);
            conn.setReadTimeout(TIME_OUT);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			// out = new PrintWriter(conn.getOutputStream());
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
			}
		}
		return sb.toString();
	}

	/**
	 * 提现向指定 URL 发送POST方法的请求
	 * 民生代付专用
	 *
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	 
	public static String sendPostWtNowPay(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer();
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setConnectTimeout(TIME_OUT);
            conn.setReadTimeout(TIME_OUT);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			param=java.net.URLDecoder.decode(param,"utf-8");
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			logger.error("请求发送错误，异常信息：" + e);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
			}
		}
		return sb.toString();
	}
	
	
	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 json的数据格式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendJsonBody(String url, String param, String appId, String appKey) throws Exception {
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer();
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setConnectTimeout(TIME_OUT);
            conn.setReadTimeout(TIME_OUT);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			if (appId != null && !"".equals(appId)) {
				conn.setRequestProperty("APP_ID", appId);
			}
			if (appKey != null && !"".equals(appKey)) {
				conn.setRequestProperty("APP_KEY", appKey);
			}
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
		}
		// 使用finally块来关闭输出流、输入流
		finally {

			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			}

		}
		return sb.toString();
	}
	
	/**
	 * 获取请求中的body参数
	 * @param request
	 * @return
	 */
	public static String getRequestBody(HttpServletRequest request) {
		String body = "";
		try {
			ServletInputStream inputStream = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			while (true) {
				String info = br.readLine();
				if (info == null) {
					break;
				}
				if (body == null || "".equals(body)) {
					body = info;
				} else {
					body += info;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return body;
	}
	/**
	 * JSON post请求
	 * @throws Exception
	 */
	public static String postJson(String url,String jsonStr) throws Exception{
		AsyncHttpClient http = getHttpClientInstance();
		String result = null;
		try {
			AsyncHttpClient.BoundRequestBuilder requestBuilder = http.preparePost(url);
			requestBuilder.setBodyEncoding(DEFAULT_CHARSET);
			requestBuilder.setHeader(CONTENT_TYPE,APPLICATION_JSON);

			requestBuilder.setBody(jsonStr);
			Future<Response> f = requestBuilder.execute();
			result = f.get().getResponseBody(DEFAULT_CHARSET);
		}catch (Exception e){
			throw new Exception("http访问异常，url:"+url+",请求参数："+jsonStr,e);
		}finally {
			http.close();
		}
		return result;
	}

	private static AsyncHttpClient getHttpClientInstance(){
		AsyncHttpClientConfig.Builder builder = new AsyncHttpClientConfig.Builder();
		builder.setMaxConnections(MAX_CONNECTION_TOTAL);
		builder.setRequestTimeout(TIME_OUT);
		return new AsyncHttpClient(builder.build());
	}
}
