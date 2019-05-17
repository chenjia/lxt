package com.lxt.ms.common.utils;

import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CaptchaUtils {
	private static int width = 100;
	private static int height = 36;
	private static int codeCount = 4;
	private static int lineCount = 20;
	private static Random random = new Random();

	public static Map<String, String> getCaptcha() {
		Map<String, String> map = new HashMap<String, String>();

		String code = randomStr(codeCount);
		BufferedImage buffImg = null;
		buffImg = creatImage(buffImg, code);

		String uuid = "CAPTCHA_"+UUIDUtils.UUID();
		CacheUtils.set(uuid, code, 60);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ImageIO.write(buffImg, "png", out);
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] bytes = out.toByteArray();
		String base64Img = Base64Utils.encodeToString(bytes).trim();
		map.put("captchaToken", uuid);
		map.put("base64Img", base64Img);

		return map;
	}

	// 生成图片
	private static BufferedImage creatImage(BufferedImage buffImg, String code) {
		int fontWidth = width / codeCount;// 字体的宽度
		int fontHeight = height - 5;// 字体的高度
		int codeY = height - 8;

		// 图像buffer
		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = buffImg.getGraphics();
		//Graphics2D g = buffImg.createGraphics();
		// 设置背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		// 设置字体
		//Font font1 = getFont(fontHeight);
		Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
		g.setFont(font);

		// 设置干扰线
		for (int i = 0; i < lineCount; i++) {
			int xs = random.nextInt(width);
			int ys = random.nextInt(height);
			int xe = xs + random.nextInt(width);
			int ye = ys + random.nextInt(height);
			g.setColor(getRandColor(1, 255));
			g.drawLine(xs, ys, xe, ye);
		}

		// 添加噪点
		float yawpRate = 0.01f;
		int area = (int) (yawpRate * width * height);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);

			buffImg.setRGB(x, y, random.nextInt(255));
		}

		for (int i = 0; i < codeCount; i++) {
			String strRand = code.substring(i, i + 1);
			g.setColor(getRandColor(1, 255));
			g.drawString(strRand, i*fontWidth+3, codeY);
		}

		return buffImg;
	}

	// 得到随机字符
	private static String randomStr(int n) {
		String str1 = "1234567890";
		String str2 = "";
		int len = str1.length() - 1;
		double r;
		for (int i = 0; i < n; i++) {
			r = (Math.random()) * len;
			str2 = str2 + str1.charAt((int) r);
		}
		return str2;
	}

	// 得到随机颜色
	private static Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 产生随机字体
	 */
	private Font getFont(int size) {
		Random random = new Random();
		Font font[] = new Font[5];
		font[0] = new Font("Ravie", Font.PLAIN, size);
		font[1] = new Font("Antique Olive Compact", Font.PLAIN, size);
		font[2] = new Font("Fixedsys", Font.PLAIN, size);
		font[3] = new Font("Wide Latin", Font.PLAIN, size);
		font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, size);
		return font[random.nextInt(5)];
	}

	// 扭曲方法
	private void shear(Graphics g, int w1, int h1, Color color) {
		shearX(g, w1, h1, color);
		shearY(g, w1, h1, color);
	}

	private void shearX(Graphics g, int w1, int h1, Color color) {

		int period = random.nextInt(2);

		boolean borderGap = true;
		int frames = 1;
		int phase = random.nextInt(2);

		for (int i = 0; i < h1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period
					+ (6.2831853071795862D * (double) phase)
					/ (double) frames);
			g.copyArea(0, i, w1, 1, (int) d, 0);
			if (borderGap) {
				g.setColor(color);
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}

	}

	private void shearY(Graphics g, int w1, int h1, Color color) {

		int period = random.nextInt(40) + 10;

		boolean borderGap = true;
		int frames = 20;
		int phase = 7;
		for (int i = 0; i < w1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period
					+ (6.2831853071795862D * (double) phase)
					/ (double) frames);
			g.copyArea(i, 0, 1, h1, 0, (int) d);
			if (borderGap) {
				g.setColor(color);
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + h1, i, h1);
			}
		}

	}
}
