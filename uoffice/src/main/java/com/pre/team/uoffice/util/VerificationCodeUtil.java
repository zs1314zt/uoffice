/**
 * 
 */
package com.pre.team.uoffice.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.pre.team.uoffice.domain.Verification;

/**
 * Description:验证码操作工具类
 * @author xuejiahao  2015年7月13日
 * 
 */
public class VerificationCodeUtil {
	
	private static Logger LOG = Logger.getLogger(VerificationCodeUtil.class);
	
	public static Map<String,Verification> map = new ConcurrentHashMap<String,Verification>();
	
	/**
	 * 
	 * Description:存储验证信息  2015年7月13日
	 * 
	 * @author xuejiahao
	 * @param phone 手机号码
	 * @param verificationCode 验证码
	 */
	public synchronized static void addVerificationCode(String phone,String verificationCode){
		map.put(phone, new Verification(verificationCode, System.currentTimeMillis()));
		LOG.info("用户:"+phone+" 验证码:"+verificationCode);
	}
	/**
	 * 
	 * Description:移除存储验证  2015年7月13日
	 * 
	 * @author xuejiahao
	 * @param phone 手机号
	 */
	public static void removeVerificationCode(String phone){
		map.remove(phone);
	}
	/**
	 * 
	 * Description:验证验证码是否相等  2015年7月13日
	 * 
	 * @author xuejiahao
	 * @param phone 手机号
	 * @param verificationCode 验证码
	 * @return
	 */
	public static boolean equals(String phone,String verificationCode){
		boolean result = false;
		Verification ver = map.get(phone);
		if(ver!=null){
			//验证码相等
			if(verificationCode.equals(ver.getVerificationCode())){
				result = true;
			}
		}
		return result;
	}
	
	/**
	 * 
	 * Description:随机生成要求长度的验证码  2015年8月30日
	 * 
	 * @author xuejiahao
	 * @param length
	 * @return
	 */
	public static String creatVerifyCode(int length) {
		StringBuffer val = new StringBuffer();
		Random random = new Random();
		Random random1 = new Random();
		for (int i = 0; i < length; i++) {
			int num = random1.nextInt(2) % 2;
			if(num == 0){
				appendRandomNum(val, random);
			}else {
				appendRandomChar(val, random);
			}
		}
		return StringUtil.toString(val);
	}
	
	/**
	 * 
	 * Description:返回组装后的随机数字  2015年8月30日
	 * 
	 * @author xuejiahao
	 * @param val
	 * @param random
	 */
	private static void appendRandomNum(StringBuffer val, Random random) {
		val.append(String.valueOf(random.nextInt(10)));
	}
	
	/**
	 * 
	 * Description:返回组装后的随机字符串  2015年8月30日
	 * 
	 * @author xuejiahao
	 * @param val
	 * @param random
	 */
	private static void appendRandomChar(StringBuffer val, Random random) {
		int tempNum = random.nextInt(2) % 2;
		int choice = tempNum == 0 ? 65 : 97; // 取得大写字母还是小写字母
		val.append((char) (choice + random.nextInt(26)));
	}
	
	/**
	 * 
	 * Description:生成验证码图片  2015年8月30日
	 * 
	 * @author xuejiahao
	 * @param veriryCode
	 * @return
	 */
	public static BufferedImage createImgVerifyCode(String veriryCode) {
		BufferedImage img = new BufferedImage(250, 100, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) img.getGraphics();// 调用画笔
		// g.setColor(Color.WHITE);// 设置画笔的颜色
//		g.fillRect(0, 0, 250, 100);// 填充背景
		g.setBackground(Color.GRAY);
		g.setColor(Color.GREEN);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 50));// 设置字体
		g.drawString(veriryCode, 45, 70);// 50*i+10是x坐标，38是y坐标。y坐标是朝下的
		return img;
	}
	
	
	/**
	 * 
	 * Description:生成二进制验证码  2015年8月30日
	 * 
	 * @author xuejiahao
	 * @param veriryCode
	 * @return
	 */
	public static byte[] byteVerifyCode(String veriryCode) {
		BufferedImage image = createImgVerifyCode(veriryCode);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "png", out);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		byte[] verifyCodeData = out.toByteArray();
		if (null == verifyCodeData) {
			return byteVerifyCode(veriryCode);
		}
		return verifyCodeData;
	}
}
