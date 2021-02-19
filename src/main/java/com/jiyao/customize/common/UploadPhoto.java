package com.jiyao.customize.common;

import com.alibaba.fastjson.JSON;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

import java.io.*;

public class UploadPhoto {

    public static void main(String[] args) throws IOException {

        // 创建File对象
        File dir  = new File("D:\\docsify\\introduction");

        //调用打印目录方法
        printDir(dir);
    }
    public static void printDir(File dir) throws IOException {

        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = "AKIDTt32kC3mjPq1OXr5VfbJm21mZ7QT6o8E";
        String secretKey = "1nPlupa9TXOzKOAhAMPGqJg4JPL1NZYb";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-nanjing");
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);

        // 指定要上传到的存储桶
        String bucketName = "mybook-1259323290";

        // 获取子文件和目录
        File[] files = dir.listFiles();

        //基础路径
        String basePath = "D:\\docsify\\introduction\\";

        // 循环打印
        for (File file : files) {
            if (file.isFile()) {
                // 是文件，判断文件名并输出文件绝对路径
                if (file.getName().endsWith(".md")) {
                    BufferedReader fr=new BufferedReader(new FileReader(file));

                    String line=null;
                    //按行读取文件
                    while((line=fr.readLine())!=null){
                        int i = line.indexOf("(");
                        if(i>-1){
                            int j = line.indexOf(")");
                            //获取文件路径
                            String substring = line.substring(i+1, j);
                            File imageFile = new File(basePath+substring);
                            //获取新路径
                            String newFilePath = substring.replaceAll(" |\r|\n", "").replace("\\","/");
                            imageFile.renameTo(new File(basePath+newFilePath));
                            System.out.println(basePath+newFilePath);
                            //上传文件
                            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, newFilePath, new File("D:\\docsify\\introduction\\"+newFilePath));
                            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
                            System.out.println(JSON.toJSON(putObjectResult));
                        }
                    }
                    fr.close();
                }
            } else {
                // 是目录，继续遍历,形成递归
                printDir(file);
            }
        }
    }
}
