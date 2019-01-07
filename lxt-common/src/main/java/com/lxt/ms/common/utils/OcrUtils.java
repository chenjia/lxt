package com.lxt.ms.common.utils;

//import net.sourceforge.tess4j.ITesseract;
//import net.sourceforge.tess4j.Tesseract;
//import net.sourceforge.tess4j.TesseractException;
//import org.springframework.util.StringUtils;

import java.io.File;

public class OcrUtils {
//    private final String LANGUAGE_PATH = "/usr/local/share/tessdata";
//    private final String CHI_SIM = "chi_sim";
//    private final String ENG = "eng";
//
//
//    public String ocr(String path, String dataPath, String lang){
//        String result = null;
//
//        if(StringUtils.isEmpty(dataPath)){
//            dataPath = this.LANGUAGE_PATH;
//        }
//
//        if(StringUtils.isEmpty(lang)){
//            lang = this.CHI_SIM;
//        }
//
//        File file = new File(path);
//        System.out.println(file.exists());
//        ITesseract instance = new Tesseract();
//
//        instance.setDatapath(dataPath);
//
//        instance.setLanguage(lang);
//        try {
//            long startTime = System.currentTimeMillis();
//            result =  instance.doOCR(file);
//            long endTime = System.currentTimeMillis();
//            System.out.println("Time is：" + (endTime - startTime) + " 毫秒");
//        } catch (TesseractException e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
}
