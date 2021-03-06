package com.ruixing.vehicle.manager.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.util.ResourceUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class QRCodeUitl {

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	private QRCodeUitl() {
	};

	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format " + format + " to " + file);
		}
	}

	public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format " + format);
		}
	}

	/**
	 * 根据内容，生成指定宽高、指定格式的二维码图片
	 *
	 * @param text
	 *            内容
	 * @param width
	 *            宽
	 * @param height
	 *            高
	 * @param format
	 *            图片格式
	 * @return 生成的二维码图片路径
	 * @throws Exception
	 */
	public static void generateQRCode(String text, int width, int height, String format, String fileName)
			throws Exception {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
		String pathName = ResourceUtils.getURL("classpath:").getPath() + Constants.QR_GENERATE_PATH;

		File dirFile = new File(pathName);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		File outputFile = new File(pathName + File.separator + fileName);
		writeToFile(bitMatrix, format, outputFile);
	}

	/**
	 * 随机生成指定长度的验证码
	 *
	 * @param length
	 *            验证码长度
	 * @return 生成的验证码
	 */
	public static String generateNumCode(int length) {
		String val = "";
		String charStr = "char";
		String numStr = "num";
		Random random = new Random();

		// 参数length，表示生成几位随机数
		for (int i = 0; i < length; i++) {

			String charOrNum = random.nextInt(2) % 2 == 0 ? charStr : numStr;
			// 输出字母还是数字
			if (charStr.equalsIgnoreCase(charOrNum)) {
				// 输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (random.nextInt(26) + temp);
			} else if (numStr.equalsIgnoreCase(charOrNum)) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	/**
	 * 解析指定路径下的二维码图片
	 *
	 * @param filePath
	 *            二维码图片路径
	 * @return
	 */
	public static String parseQRCode(String filePath) {
		String content = "";
		try {
			File file = new File(filePath);
			BufferedImage image = ImageIO.read(file);
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			Map<DecodeHintType, Object> hints = new HashMap<>();
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			MultiFormatReader formatReader = new MultiFormatReader();
			Result result = formatReader.decode(binaryBitmap, hints);

			System.out.println("result 为：" + result.toString());
			System.out.println("resultFormat 为：" + result.getBarcodeFormat());
			System.out.println("resultText 为：" + result.getText());
			// 设置返回值
			content = result.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

}
