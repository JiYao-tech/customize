package com.jiyao.customize.utils;

import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Base64Utils {
    public static void main(String[] args) {
        //下面是本地图片转换Base64的方法
        String imagePath = "C:\\Users\\36536\\Desktop\\FLAMING MOUNTAIN.JPG";
        ImageToBase64(imagePath);
    }

    /**
     * 本地图片转换Base64的方法
     *
     * @param imgPath
     */

    private static void ImageToBase64(String imgPath) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgPath);
            int i = in.available();
            System.out.println(i);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        System.out.println(encoder.encode(Objects.requireNonNull(data)));
    }

}
