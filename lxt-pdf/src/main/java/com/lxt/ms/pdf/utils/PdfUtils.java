package com.lxt.ms.pdf.utils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.util.ResourceUtils;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class PdfUtils {

    private static final String inputFile = "/Users/farben/project/lxt/lxt-pdf/src/main/resources/pdf/build.html";
    private static final String outputFile = "/Users/farben/project/lxt/lxt-pdf/src/main/resources/pdf/build.pdf";

    public static void buildPdf(String template, String json) throws Exception{
        long start = System.currentTimeMillis();
    	final WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_11);
        final HtmlPage page = webClient.getPage("http://localhost:8341/lxt-pdf/"+template);
        page.executeJavaScript("window.build("+json+")");
		webClient.waitForBackgroundJavaScript(100);
		long end = System.currentTimeMillis();
		System.out.println("time:"+(end-start)/1000);
        File f = new File(inputFile);
        if(f.exists()){
        	f.delete();
        }
        f.createNewFile();
        BufferedWriter output = new BufferedWriter(new FileWriter(f));
        output.write(page.asXml());
        output.close();
        PdfUtils ih = new PdfUtils();
        ih.html2pdf(inputFile, outputFile);
        end = System.currentTimeMillis();
		System.out.println("time:"+(end-start)/1000);
    }

    public void html2pdf(String inputFile,String outputFile){
    	try {
			String url = new File(inputFile).toURI().toURL().toString();
			OutputStream os = new FileOutputStream(outputFile);
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(url);
			ITextFontResolver fontResolver = renderer.getFontResolver();
            String simsunPath = ResourceUtils.getFile("/Users/farben/project/lxt/lxt-pdf/src/main/resources/pdf/fonts/simsun.ttc").getAbsolutePath();
            String simheiPath = ResourceUtils.getFile("/Users/farben/project/lxt/lxt-pdf/src/main/resources/pdf/fonts/simhei.ttf").getAbsolutePath();
            String microsoftYaHeiPath = ResourceUtils.getFile("/Users/farben/project/lxt/lxt-pdf/src/main/resources/pdf/fonts/MicrosoftYaHei.ttf").getAbsolutePath();
			fontResolver.addFont("simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			fontResolver.addFont("simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			fontResolver.addFont("MicrosoftYaHei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			renderer.getSharedContext().setBaseURL("file:///Users/farben/project/lxt/lxt-pdf/src/main/resources/pdf/");
			renderer.layout();
			renderer.createPDF(os);
			os.close();
			System.out.println("pdf完成");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    }
    
}