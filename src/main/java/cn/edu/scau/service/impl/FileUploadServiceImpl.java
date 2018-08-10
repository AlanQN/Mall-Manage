package cn.edu.scau.service.impl;

import cn.edu.scau.file.FilePathConfig;
import cn.edu.scau.component.Icon;
import cn.edu.scau.service.IFileUploadService;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements IFileUploadService {

    public Map<String, Object> saveIcon(Icon icon, String realPath) {
        Map<String, Object> response = new HashMap<String, Object>();   //结果
        String url = null;  //访问路径
        try {
            //转码
            String data = URLDecoder.decode(icon.getFile(), "UTF-8");
            System.out.println(data);
            //获取实际路径
            realPath = realPath + "/" + icon.getId();
            System.out.println("实际上传路径 = " + realPath);
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            //获取文件名称
            String uuid = UUID.randomUUID().toString();
            uuid = uuid.replaceAll("-", "");
            String fileName = uuid + ".png";
            //上传
            base64CodeToimage(data,realPath + "/" + fileName);
            //访问路径
            url = FilePathConfig.VISIT_BASE_PATH + FilePathConfig.USER_ICON_BASE_PATH + "/" + icon.getId() + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            //封装失败结果
            response.put("result", false);
            response.put("url", url);
            return response;
        }
        //封装结果
        response.put("result", true);
        response.put("url", url);
        return response;
    }

    /**
     * base64字符串转换为图片，并保存
     * @Description
     * @Param
     * @Return
     */
    public static void base64CodeToimage(String data,String url) throws IOException {
        //对字节数组字符串进行Base64解码并生成图片
        BASE64Decoder decoder = new BASE64Decoder();
        //Base64解码
        System.out.println("-------------");
        String str1 = data.split(",")[1];//去掉头取base64内容
        str1 = str1.replaceAll(" ", "+");
        System.out.println("=============");
        byte[] bytes =decoder.decodeBuffer(str1);//获取到内容做解码
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] < 0) {//调整异常数据
                bytes[i] += 256;
            }
            System.out.print(bytes[i]);
        }
        //生成jpeg图片
        OutputStream out = new FileOutputStream(url);
        out.write(bytes);
        out.flush();
        out.close();
    }

}
