package com.pre.team.uoffice.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeUtil {


	/**
	 * 
	 * Description:生成二维码  2015年9月16日
	 * 
	 * @author xuejiahao
	 * @param content
	 */
	public static byte[] createQRCode(String content) {
		int width = 100;// 设置图片宽，单位：像素
		int height = 100;// 设置图片高，单位：像素
		String format = "png";// 设置图片扩展名
		// 用于设置QR二维码参数
		Hashtable<EncodeHintType, Object> qrParam = new Hashtable<EncodeHintType, Object>();
		// 设置QR二维码的纠错级别——这里选择最高H级别，以达到尽可能高的兼容性
		qrParam.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 设置编码方式
		qrParam.put(EncodeHintType.CHARACTER_SET, "utf-8");

		// 存放生成的二维码数据(一系列的布尔值)
		BitMatrix bitMatrix;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			// 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
			bitMatrix = new MultiFormatWriter().encode(content,
					BarcodeFormat.QR_CODE, width, height, qrParam);
			// 根据二维码数据(一系列的布尔值)生成图片文件
			MatrixToImageWriter.writeToStream(bitMatrix, format, out);// 写入文件
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] QRCodeData = out.toByteArray();
		return QRCodeData;
	}
}
