package com.lxt.ms.pdf.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImagePreProcess3 {

    private static Map<BufferedImage, String> trainMap = null;
    private static int index = 0;

    public static int isBlack(int colorInt) {
        Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() <= 100) {
            return 1;
        }
        return 0;
    }

    public static int isWhite(int colorInt) {
        Color color = new Color(colorInt);
        if (color.getRed() + color.getGreen() + color.getBlue() > 600) {
            return 1;
        }
        return 0;
    }

    public static BufferedImage removeBlank(BufferedImage img) throws Exception {
        int width = img.getWidth();
        int height = img.getHeight();
        int start = 0;
        int end = 0;
        Label1: for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                if (isBlack(img.getRGB(x, y)) == 1) {
                    start = y;
                    break Label1;
                }
            }
        }
        Label2: for (int y = height - 1; y >= 0; --y) {
            for (int x = 0; x < width; ++x) {
                if (isBlack(img.getRGB(x, y)) == 1) {
                    end = y;
                    break Label2;
                }
            }
        }
        return img.getSubimage(0, start, width, end - start + 1);
    }

    public static List<BufferedImage> splitImage(BufferedImage img)
            throws Exception {
        List<BufferedImage> subImgs = new ArrayList<BufferedImage>();
        int width = img.getWidth();
        int height = img.getHeight();
        List<Integer> weightlist = new ArrayList<Integer>();
        for (int x = 0; x < width; ++x) {
            int count = 0;
            for (int y = 0; y < height; ++y) {
                if (isBlack(img.getRGB(x, y)) == 1) {
                    count++;
                }
            }
            weightlist.add(count);
        }
        for (int i = 0; i < weightlist.size();i++) {
            int length = 0;
            while (i < weightlist.size() && weightlist.get(i) > 0) {
                i++;
                length++;
            }
            if (length > 2) {
                subImgs.add(removeBlank(img.getSubimage(i - length, 0,
                        length, height)));
            }
        }
        return subImgs;
    }

    public static Map<BufferedImage, String> loadTrainData() throws Exception {
        if (trainMap == null) {
            Map<BufferedImage, String> map = new HashMap<BufferedImage, String>();
            File dir = new File("train3");
            File[] files = dir.listFiles();
            for (File file : files) {
                map.put(ImageIO.read(file), file.getName().charAt(0) + "");
            }
            trainMap = map;
        }
        return trainMap;
    }

    public static String getSingleCharOcr(BufferedImage img,
                                          Map<BufferedImage, String> map) {
        String result = "#";
        int width = img.getWidth();
        int height = img.getHeight();
        int min = width * height;
        for (BufferedImage bi : map.keySet()) {
            int count = 0;
            if (Math.abs(bi.getWidth()-width) > 2)
                continue;
            int widthmin = width < bi.getWidth() ? width : bi.getWidth();
            int heightmin = height < bi.getHeight() ? height : bi.getHeight();
            Label1: for (int x = 0; x < widthmin; ++x) {
                for (int y = 0; y < heightmin; ++y) {
                    if (isBlack(img.getRGB(x, y)) != isBlack(bi.getRGB(x, y))) {
                        count++;
                        if (count >= min)
                            break Label1;
                    }
                }
            }
            if (count < min) {
                min = count;
                result = map.get(bi);
            }
        }
        return result;
    }

    public static Color pointColor = new Color(200,200,200);
    public static Color bgColor = new Color(108,122,149);

    public static boolean close(int colorInt, int rgbInt){
        System.out.println(colorInt+":"+rgbInt);
        return colorInt>rgbInt-900000 && colorInt < rgbInt+900000;
    }

    public static int isGanrao(BufferedImage img, int x, int y) {
        int colorInt = img.getRGB(x, y);
        Color color = new Color(colorInt);
        int total = color.getRed() + color.getGreen() + color.getBlue();
        if (total == 379 || total == 600) {
            return 1;
        }else{
            return 0;
        }
    }

    public static BufferedImage zoomInImage(BufferedImage originalImage, int width, int height) {
        BufferedImage newImage = new BufferedImage(width, height, originalImage.getType());

        Graphics g = newImage.getGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return newImage;
    }

    public static BufferedImage resize(BufferedImage source, int targetW, int targetH) {
        int width = source.getWidth();// 图片宽度
        int height = source.getHeight();// 图片高度
        return zoomInImage(source, targetW, targetH);
    }

    public static BufferedImage removeBackgroud2(String picFile)
            throws Exception {
        BufferedImage img = ImageIO.read(new File(picFile));
        img = img.getSubimage(1, 1, img.getWidth() - 2, img.getHeight() - 2);
        int width = img.getWidth();
        int height = img.getHeight();

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                if (isGanrao(img, x,y) == 1){
                    img.setRGB(x, y, Color.BLACK.getRGB());
                }else{
                    img.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }

        return img;
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        for (int i = 1; i <= 3; ++i) {
            BufferedImage bi = removeBackgroud2("/Users/farben/Downloads/yzm" + i + ".png");
            bi = resize(bi, 50*10, 28*10);
            File outputfile = new File("/Users/farben/Downloads/newyzm" + i + ".png");
            ImageIO.write(bi, "png", outputfile);
        }
    }
}
