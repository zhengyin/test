package com.izhengyin.test.other.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-06-11 16:46
 */
public class FontImage {
    public static void main(String[] args) throws Exception {
        float size = 32;
        String name = "你好Java";
        Font font = Font.createFont(Font.TRUETYPE_FONT,new FileInputStream(new File("/Users/zhengyin/Desktop/fonts/laomoshouji.ttf")));
        Font newFont = font.deriveFont(size)
                .deriveFont(Font.BOLD);
        newFont = new Font("微软雅黑", Font.BOLD, 32);
        BufferedImage image = createImage(name,newFont ,  new Double(size * name.length()).intValue(), new Double(size).intValue());
        //BufferedImage image = drawTranslucentStringPic(font,new Double(size * name.length()).intValue() + 300, new Double(size).intValue() + 300,0,name);
        // 输出png图片
        ImageIO.write(image, "png", new File("/Users/zhengyin/Desktop/a.png"));
    }




    public static BufferedImage drawTranslucentStringPic(Font font , int width, int height, Integer fontHeight,String drawStr) {
        try {
            BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D gd = buffImg.createGraphics();
            //设置透明  start
            buffImg = gd.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
            gd = buffImg.createGraphics();
            //设置字体
            gd.setFont(font);
            //消除锯齿状
      //      gd.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            //设置颜色
          //  gd.setColor(Color.BLACK);
            //画边框
            gd.drawRect(0, 0, width - 1, height - 1);
            //输出文字（中文横向居中）
 //           gd.drawString(drawStr, width/2-fontHeight*drawStr.length()/2,fontHeight);
            Rectangle clip = gd.getClipBounds();
            FontMetrics fm = gd.getFontMetrics(font);
            int ascent = fm.getAscent();
            int descent = fm.getDescent();
            int y = (clip.height - (ascent + descent)) / 2 + ascent;
            for (int i = 0; i < 6; i++) {// 256 340 0 680
                gd.drawString(drawStr, i * 680, y);// 画出字符串
            }
            return buffImg;
        } catch (Exception e) {
            return null;
        }
    }


    // 根据str,font的样式以及输出文件目录
    public static BufferedImage createImage(String str, Font font, Integer width, Integer height) throws Exception {
        // 创建图片
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();

        g.setClip(0, 0, width, height);
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);// 先用黑色填充整张图片,也就是背景
        g.setColor(Color.black);// 在换成黑色
        g.setFont(font);// 设置画笔字体
        /** 用于获得垂直居中y */
        Rectangle clip = g.getClipBounds();
        FontMetrics fm = g.getFontMetrics(font);
        int ascent = fm.getAscent();
        int descent = fm.getDescent();
        int y = (clip.height - (ascent + descent)) / 2 + ascent;
        for (int i = 0; i < 6; i++) {// 256 340 0 680
            g.drawString(str, i * 680, y);// 画出字符串
        }
        g.dispose();
        return image;
    }

}
