package com.izhengyin.test.other.image;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-06-11 17:41
 */
import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.LineMetrics;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by zengrenyuan on 18/5/11.
 */
public class ImageTest {

    public static void main(String[] args) throws Exception {
        Font font = Font.createFont(Font.TRUETYPE_FONT,new File("/Users/zhengyin/Desktop/fonts/laomoshouji.ttf"))
                .deriveFont(Font.BOLD)
                .deriveFont((float)64);

       // Font font = new Font("微软雅黑", Font.BOLD, 32);
        String content = "你好Java!";
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        //计算图片的宽
        int width = getWordWidth(font, content);
        //计算高
        int height = metrics.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        //设置背影为白色
    //    graphics.setColor(Color.WHITE);
    //    graphics.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        //透明背景
        bufferedImage = graphics.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        graphics.dispose();
        graphics = bufferedImage.createGraphics();
        //设置字体
        graphics.setFont(font);
        graphics.setColor(Color.BLACK);
        //图片上写文字
        graphics.drawString(content, 0, metrics.getAscent());
        graphics.dispose();
        write(bufferedImage, "/Users/zhengyin/Desktop/test.png");


    }

    public static int getWordWidth(Font font, String content) {
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        int width = 0;
        for (int i = 0; i < content.length(); i++) {
            width += metrics.charWidth(content.charAt(i));
        }
        return width;
    }

    public static void write(BufferedImage bufferedImage, String target) throws IOException {
        File file = new File(target);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try (OutputStream os = new FileOutputStream(target)) {
            ImageIO.write(bufferedImage, "PNG", os);
        }
    }
}