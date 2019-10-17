package com.zjzc.manage.utils.emailUtils;

import com.zjzc.manage.utils.exception.EmailException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.xbill.DNS.*;

import javax.net.ssl.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * @author JLICC
 * @Package com.small.notice.utils.emailUtil
 * @Description:
 * @date 2019/05/16 15:46:51
 */
@Slf4j
public class CheckEmailAddress {

    //发出验证请求的域名(是当前站点的域名，可以任意指定)
    private final static String DOADMIN = "www.zjac.org";


    public static String[] emails = {
            "15801234888@qq.com",
            "2168768177@163.com",
            "18798026160@qq.com",
            "13765676516@qq.com",
            "13195167333@qq.com",
            "13638132765@qq.com",
            "3514461891@qq.com",
            "2627564021@qq.com",
            "5525286116@qq.com",
            "3361743604@qq.com",
            "15520868222@163.com",
            "37411079@qq.com",
            "13178979523@163.com",
            "2905309405@qq.com",
            "2835254675@qq.com",
            "3484689857@qq.com",
            "13997936179@163.com",
            "7903455314@qq.com",
            "783459252@qq.com",
            "15837701038@163.com",
            "3316642722@qq.com",
            "18652856506@163.com",
            "13607637966@163.com",
            "13956607623@163.com",
            "13838770426@163.com",
            "1159701693@qq.com",
            "13966236590@163.com",
            "13866155600@163.com",
            "15956386550@163.com",
            "13305632166@163.com",
            "15856933050@163.com",
            "18362907180@163.com",
            "18936010736@163.com",
            "15297203800@163.com",
            "13997208709@163.com",
            "18097401029@163.com",
            "13997108003@163.com",
            "13204880418@163.com",
            "1876015280@qq.com",
            "15248246668@163.com",
            "13947488525@163.com",
            "13781448398@163.com",
            "18161590160@163.com",
            "13223909908@163.com",
            "13995106257@163.com",
            "342747552@qq.com",
            "15926929637@126.com",
            "13781383718@163.com",
            "6357358253@qq.com",
            "13669348991@163.com",
            "13919982516@168.com",
            "2182572239@qq.com",
            "cxg19610520@163.com",
            "15853975222@163.com",
            "13609387812@163.com",
            "13518696366@163.com",
            "13792968127@163.com",
            "18560682978@163.com",
            "18053215766@168.com",
            "26934726@qq.com",
            "13513517467@163.com",
            "15632226809@163.com",
            "15033035071@163.com",
            "13934025881@163.com",
            "15303427872@163.com",
            "15176099272@163.com",
            "15845911838@136.com",
            "13834307536@163.com",
            "18545777629@163.com",
            "1531668865q@qq.com",
            "5727666766@qq.com",
            "18602639375@163.com",
            "3285180642@qq.com",
            "13763416312@163.com",
            "13212271864@163.com",
            "15022001988@163.com",
            "13943399265@163.com",
            "18844748080@163.com",
            "15143379395@163.com",
            "13792743199@163.com",
            "13942282681@163.com",
            "18842578222@163.com"
    };

    public static void main(String[] args) {
        System.out.println(valid("13183313366@139.com"));
    }


    /**
     * 拦截无效邮箱
     * @param msgTo
     * @return
     */
    public static String formatErrorEmail(String msgTo){
        log.debug("【拦截前邮箱】{}", msgTo);
        if(StringUtils.startsWithIgnoreCase(msgTo,"www.")){
            msgTo = msgTo.replaceAll("(?i)www\\.","");
        }
        if(StringUtils.endsWithIgnoreCase(msgTo,".con")){
            msgTo = msgTo.replaceAll("(?i)\\.con","\\.com");
        }
        if(StringUtils.endsWithIgnoreCase(msgTo,".comm")){
            msgTo = msgTo.replaceAll("(?i)\\.comm","\\.com");
        }
        if(StringUtils.endsWithIgnoreCase(msgTo,".cm")){
            msgTo = msgTo.replaceAll("(?i)\\.cm","\\.com");
        }
        if(StringUtils.endsWithIgnoreCase(msgTo,"pp.com")){
            msgTo = msgTo.replaceAll("(?i)pp\\.com","qq\\.com");
        }
        if(StringUtils.endsWithIgnoreCase(msgTo,".cpm")){
            msgTo = msgTo.replaceAll("(?i)\\.cpm","\\.com");
        }
        if(StringUtils.endsWithIgnoreCase(msgTo,".conm")){
            msgTo = msgTo.replaceAll("(?i)\\.conm","\\.com");
        }
        if(StringUtils.endsWithIgnoreCase(msgTo,"qq.cn")){
            msgTo = msgTo.replaceAll("(?i)qq\\.cn","qq\\.com");
        }
        if(StringUtils.endsWithIgnoreCase(msgTo,".co")){
            msgTo = msgTo.replaceAll("(?i)\\.co","\\.com");
        }
        if(StringUtils.endsWithIgnoreCase(msgTo,".om")){
            msgTo = msgTo.replaceAll("(?i)\\.om","\\.com");
        }
        if(StringUtils.endsWithIgnoreCase(msgTo,"qqqq.com")){
            msgTo = msgTo.replaceAll("(?i)qqqq\\.com","qq\\.com");
        }
        if(StringUtils.endsWithIgnoreCase(msgTo,"qqc.om")){
            msgTo = msgTo.replaceAll("(?i)qqc\\.om","qq\\.com");
        }

        log.debug("【拦截后邮箱】{}", msgTo);
        return msgTo;
    }

    /**
     * 验证邮箱是否存在
     * <br>
     * 由于要读取IO，会造成线程阻塞
     *
     * @param toMail 要验证的邮箱
     * @return 邮箱是否可达
     */
    public static boolean valid(String toMail) {
        if (StringUtils.isBlank(toMail) || StringUtils.isBlank(DOADMIN)) return false;
        if (!StringUtils.contains(toMail, '@')) return false;
        String host = toMail.substring(toMail.indexOf('@') + 1);
        log.info(host);
        if (host.equals(DOADMIN)) return false;
        Socket socket = new Socket();
        try {
            // 查找mx记录，若查询结果为空，30秒内重试
            Record[] mxRecords = null;
            long startTimeMillis = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTimeMillis < 10 * 1000) {
                mxRecords = new Lookup(host, Type.MX).run();
                if (!ArrayUtils.isEmpty(mxRecords)) {
                    log.info("查找mx记录，查询有结果{}", mxRecords.toString());
                    break;
                }
                log.info("查找mx记录，查询结果为空,重试并暂停两秒");
                Thread.sleep(2000);
            }
            if (ArrayUtils.isEmpty(mxRecords)) {
                log.info("查找mx记录，查询结果为空");
                return false;
            }
            // 邮件服务器地址
            String mxHost = ((MXRecord) mxRecords[0]).getTarget().toString();
            List<Record> arrRecords = new ArrayList<Record>();
            if (mxRecords.length > 1) { // 优先级排序
                Collections.addAll(arrRecords, mxRecords);
                Collections.sort(arrRecords, new Comparator<Record>() {

                    public int compare(Record o1, Record o2) {
                        return new CompareToBuilder().append(((MXRecord) o1).getPriority(), ((MXRecord) o2).getPriority()).toComparison();
                    }

                });
            }
            // 开始smtp
            //139,163邮箱使用倒序排列
            if("139.com".equalsIgnoreCase(host) || "163.com".equalsIgnoreCase(host)){
                Collections.reverse(arrRecords);
            }

            for(Record record : arrRecords){
                mxHost = ((MXRecord) record).getTarget().toString();
                try {
                    socketConnect(socket, mxHost);
                    break;
                } catch (EmailException e) {
                    socket = new Socket();
                    continue;
                }
            }


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // 超时时间(毫秒)
            long timeout = 6000;
            // 睡眠时间片段(60毫秒)
            int sleepSect = 60;

            Integer code = 0;
            // 连接(服务器是否就绪)
            code = getResponseCode(timeout, sleepSect, bufferedReader);
            log.info("连接(服务器是否就绪)：{}", code);
            if (code != 220) {
                return false;
            }

            // 握手
            bufferedWriter.write("HELO " + DOADMIN + "\r\n");
            bufferedWriter.flush();
            code = getResponseCode(timeout, sleepSect, bufferedReader);
            log.info("连接(握手)：{}", code);
            if (code != 250) {
            /*if (getResponseCode(timeout, sleepSect, bufferedReader) != 250) {*/
                return false;
            }
            // 身份
            bufferedWriter.write("MAIL FROM: <check@" + DOADMIN + ">\r\n");
            bufferedWriter.flush();
            /*if (getResponseCode(timeout, sleepSect, bufferedReader) != 250) {*/
            code = getResponseCode(timeout, sleepSect, bufferedReader);
            log.info("连接(身份)：{}", code);
            if (code != 250) {
                return false;
            }
            // 验证
            bufferedWriter.write("RCPT TO: <" + toMail + ">\r\n");
            bufferedWriter.flush();
            code = getResponseCode(timeout, sleepSect, bufferedReader);
            log.info("连接(验证)：{}", code);
            if (code != 250) {
            /*if (getResponseCode(timeout, sleepSect, bufferedReader) != 250) {*/
                return false;
            }
            // 断开
            bufferedWriter.write("QUIT\r\n");
            bufferedWriter.flush();
            return true;
        } catch (NumberFormatException e) {
            log.error("【NumberFormatException】:", e);
        } catch (TextParseException e) {
            log.error("【TextParseException】:", e);
        } catch (IOException e) {
            log.error("【IOException】:", e);
        } catch (InterruptedException e) {
            log.error("【InterruptedException】:", e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                log.error("【socket.InterruptedException】:", e);
            }
        }
        return false;
    }

    private static void socketConnect(Socket socket, String mxHost) throws EmailException {
        log.info("mxHost：{}", mxHost);
        try {
        /*X509TrustManager x509m = new X509TrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        };
        // 获取一个SSLContext实例
        SSLContext s = SSLContext.getInstance("SSL");
        // 初始化SSLContext实例
        s.init(null, new TrustManager[] { x509m },
                new java.security.SecureRandom());
        SSLSocket sslSocket = (SSLSocket) SSLSocketFactory
                .getDefault().createSocket(mxHost, 465);
            //socket = sslSocket;*/
            socket.connect(new InetSocketAddress(mxHost, 25));
        } catch (IOException e) {
            log.error("【socket链接】异常:{}",e.getMessage());
            throw new EmailException("【socket链接】" + e.getMessage());
        }
    }

    private static int getResponseCode(long timeout, int sleepSect, BufferedReader bufferedReader) throws InterruptedException, NumberFormatException, IOException {
        int code = 0;
        for (long i = sleepSect; i < timeout; i += sleepSect) {
            Thread.sleep(sleepSect);
            if (bufferedReader.ready()) {
                String outline = bufferedReader.readLine();
                // FIXME 读完……
                while (bufferedReader.ready()) {
                    log.info("outline：{}", bufferedReader.readLine());
                }
                log.info("outline2：{}", outline);
                code = Integer.parseInt(outline.substring(0, 3));
                break;
            }
        }
        return code;
    }
}
