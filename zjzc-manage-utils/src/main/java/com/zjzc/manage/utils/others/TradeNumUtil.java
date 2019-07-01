package com.zjzc.manage.utils.others;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 内部订单号生成算法
 * @author qinzihao
 */
public class TradeNumUtil {

	private static Logger logger = LoggerFactory.getLogger(TradeNumUtil.class);

	/**
	 * 生成订单编号，17位以上
	 * @param sysType - 系统编号
	 * @param tradeType - 交易类型
	 * @param random - 尾部随机数位数
	 * @return
	 */
	public static String getPsTradeNum(int sysType, int tradeType, int random) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
			return "" + sysType + tradeType + sdf.format(new Date()) + RandomUtils.getRandomNumber(random);
		} catch (Exception e) {
			logger.error("生成订单编号异常，异常信息：", e);
			return null;
		}
	}
	
}
