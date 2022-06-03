package cn.anntek.springbootverificationcode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerificationCodeUtil {
    public static final int AUTHCODE_LENGTH=5;//验证码字符数
    public static final int SINGLECODE_WIDTH=10;//字符宽度
    public static final int SINGLECODE_HEIGHT=15;//字符高度
    public static final int SINGLECODE_GAP = 5;//字符间间隔
    public static final int PADDING_TOP_BOT = 10;//上下边界
    public static final int PADDING_LEFT_RIGHT=10;//左右边界
    public static final int IMG_WIDTH = AUTHCODE_LENGTH * (SINGLECODE_WIDTH + SINGLECODE_GAP)+PADDING_LEFT_RIGHT;
    public static final int IMG_HEIGHT = SINGLECODE_HEIGHT+PADDING_TOP_BOT;
    public static final String[] CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
    static Random random = new Random();
    public static String getVerificationCode(){
        StringBuffer buffer = new StringBuffer();
        //随机生成验证码字符
        for (int i = 0;i<AUTHCODE_LENGTH;i++){
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return buffer.toString();
    }
    public static BufferedImage getVerificationImage(String authCode){
        //设置图像宽度高度
        BufferedImage img  = new BufferedImage(IMG_WIDTH,IMG_HEIGHT,BufferedImage.TYPE_INT_BGR);
        Graphics g = img.getGraphics();
        //设置白颜色
        g.setColor(new Color(255,255,255));
        //填充背景
        g.fillRect(0,0,IMG_WIDTH,IMG_HEIGHT);
        //设置黑颜色
        g.setColor(Color.BLACK);
        //设置字体
        g.setFont(new Font("Arial",Font.PLAIN,SINGLECODE_HEIGHT + 5));
        //图像绘制验证码字符串
        char c;
        for (int i =0;i<authCode.toCharArray().length;i++){
            c=authCode.charAt(i);
                    g.drawString(c+"",i * (SINGLECODE_WIDTH + SINGLECODE_GAP)+SINGLECODE_GAP / 2+PADDING_LEFT_RIGHT/2,IMG_HEIGHT-PADDING_TOP_BOT/2);
        }
        Random random = new Random();
        //添加干扰线段
        for (int i = 0;i<10;i++){
            int x = random.nextInt(IMG_WIDTH);
            int y  = random.nextInt(IMG_HEIGHT);
            int x2 = random.nextInt(IMG_WIDTH);
            int y2 = random.nextInt(IMG_HEIGHT);
            g.drawLine(x,y,x2,y2);
        }
        //添加干扰点
        for (int i =0; i<50;i++){
            int x = random.nextInt(IMG_WIDTH);
            int y = random.nextInt(IMG_HEIGHT);
            g.drawOval(x,y,2,2);
        }
        return img;
    }
}
