package com.beini.drds.config;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TreeMap;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.aliyuncs.utils.Base64Helper;
/**
 * DRDS相关配置
 * @author lb_chen
 */
public class DrdsConfig {
	public static void drdsOpenAPI() throws NoSuchAlgorithmException, IOException, InvalidKeyException {
		// AK 与 SK
		String accessKey = "testid";
		String accessSecret = "testsecret";
		// 公共参数
		Map<String, String> parameters = new TreeMap<String, String>();
		parameters.put("Format", "JSON");
		parameters.put("Action", "DescribeDrdsInstances");// 调用 DescribeDrdsInstances 接口
		parameters.put("Version", "2018-05-09");
		parameters.put("AccessKeyId", accessKey);
		parameters.put("SignatureMethod", "HMAC-SHA1");
		parameters.put("Timestamp", getISO8601Time());
		parameters.put("SignatureVersion", "1.0");
		parameters.put("SignatureNonce", UUID.randomUUID().toString());
		parameters.put("RegionId", "cn-hangzhou");
		StringBuilder paramStr = new StringBuilder();
		// 拼接请求参数
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			paramStr.append(percentEncode(entry.getKey())).append("=").append(percentEncode(entry.getValue()))
					.append("&");
		}
		paramStr.deleteCharAt(paramStr.length() - 1);
		// 计算签名
		StringBuilder stringToSign = new StringBuilder();
		stringToSign.append("GET").append("&").append(percentEncode("/")).append("&")
				.append(percentEncode(paramStr.toString()));
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(new SecretKeySpec((accessSecret + "&").getBytes("UTF-8"), "HmacSHA1"));
		byte[] signData = mac.doFinal(stringToSign.toString().getBytes("UTF-8"));
		String signStr = Base64Helper.encode(signData);
		// 拼接 URL
		String requestUrl = "http://drds.aliyuncs.com/?" + paramStr.toString() + "&Signature=" + percentEncode(signStr);
		// 准备发送 HTTP 请求
		URL url = new URL(requestUrl);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("GET");
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		httpConn.setUseCaches(false);
		httpConn.connect();
		InputStream content = httpConn.getInputStream();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buff = new byte[1024];
		while (true) {
			final int read = content.read(buff);
			if (read == -1)
				break;
			outputStream.write(buff, 0, read);
		}
		System.out.println(new String(outputStream.toByteArray()));

		outputStream.close();
		content.close();
		httpConn.disconnect();
	}

	/**
	 * 特殊字符进行编码替换
	 * @param value 需要转换的字符串
	 * @return 特殊字符经过转换的字符串结果
	 * @throws UnsupportedEncodingException 不支持编码异常
	 */
	public static String percentEncode(String value) throws UnsupportedEncodingException {
		return value != null
				? URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~")
				: null;
	}
	/**
	 * 日期格式转换
	 * @return 日期时间字符串
	 */
	static String getISO8601Time() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		df.setTimeZone(new SimpleTimeZone(0, "GMT"));
		return df.format(new Date());
	}
}