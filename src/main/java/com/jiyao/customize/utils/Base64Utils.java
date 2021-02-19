package com.jiyao.customize.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Base64Utils {
    /**
     * 将网络图片进行Base64位编码
     *
     * @param link 图片的url路径，如http://.....xx.jpg
     * @return
     */
    public static String urlImgageToBase64(String link) {
        byte[] data = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            inputStream = conn.getInputStream();
            //这里为分界上面代码是拿到网络图片，下面代码是编码
            data = new byte[inputStream.available()];
            if (inputStream != null){
                inputStream.read(data);
            }
            if (inputStream != null){
                inputStream.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        System.out.println("data:image/jpg;base64,"+encoder.encode(data).replaceAll("\r|\n",""));
        return "data:image/jpg;base64,"+encoder.encode(data).replaceAll("\r|\n","");
    }

    /**
     * 将本地图片进行Base64位编码
     *
     * @param imgPath
     *            图片的url路径，如http://.....xx.jpg
     * @return
     */
    public static String fileImageToBase64(String imgPath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        System.out.println("data:image/jpg;base64,"+encoder.encode(data).replaceAll("\r|\n",""));
        return "data:image/jpg;base64,"+encoder.encode(data).replaceAll("\r|\n","");
    }

    /**
     * 将Base64位编码的图片进行解码，并保存到指定目录
     *
     * @param base64 base64编码的图片信息
     * @return
     */
    public static void Base64ToImage(String base64, String path,
                                           String imgName) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            FileOutputStream write = new FileOutputStream(new File(path+"\\"
                    + imgName));
            byte[] decoderBytes = decoder.decodeBuffer(base64);
            write.write(decoderBytes);
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
