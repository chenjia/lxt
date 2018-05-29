package com.lxt.chenjia.base.utils;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.patchca.background.SingleColorBackgroundFactory;
import org.patchca.color.ColorFactory;
import org.patchca.filter.predefined.WobbleRippleFilterFactory;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.AbstractCaptchaService;
import org.patchca.text.renderer.BestFitTextRenderer;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

import sun.misc.BASE64Encoder;

public class CaptchaUtils {
	private static CaptchaUtils utils = new CaptchaUtils();
	private static MyCaptchaService cs = null;
	private static Random random = new Random();
	private static BASE64Encoder encoder = new BASE64Encoder();
	
	

	public static Map<String, String> getCaptcha() {
		if (cs == null) {
			cs = utils.new MyCaptchaService();
		}
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		cs.setFilterFactory(new WobbleRippleFilterFactory());
		String captcha = null;
		try {
			captcha = EncoderHelper.getChallangeAndWriteImage(cs, "png", out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] bytes = out.toByteArray();
		String base64Img = encoder.encodeBuffer(bytes).trim();
		Map<String, String> map = new HashMap<String, String>();
		
		String uuid = "CAPTCHA_"+UUIDUtils.UUID();
		CacheUtils.set(uuid, captcha, 120);
		map.put("captchaToken", uuid);
		map.put("base64Img", base64Img);
		
		return map;
	}

	private class MyWordFactory extends RandomWordFactory {
		public MyWordFactory() {
			// 文本范围和长度
			characters = "1234567890";
			minLength = 4;
			maxLength = 4;
		}
	}

	private class MyCaptchaService extends AbstractCaptchaService {
		public MyCaptchaService() {
			// 文本内容
			wordFactory = new MyWordFactory();
			// 字体
			fontFactory = new RandomFontFactory(30, new String[] { "宋体" });
			// 效果
			textRenderer = new BestFitTextRenderer();
			// 背景
			backgroundFactory = new SingleColorBackgroundFactory();
			// 字体颜色
			colorFactory = new ColorFactory() {
				@Override
				public Color getColor(int x) {
					int[] c = new int[3];
					int i = random.nextInt(c.length);
					for (int fi = 0; fi < c.length; fi++) {
						if (fi == i) {
							c[fi] = random.nextInt(71);
						} else {
							c[fi] = random.nextInt(256);
						}
					}
					return new Color(c[0], c[1], c[2]);
				}
			};

			// 图片长宽
			width = 100;
			height = 36;
		}
	}
}
