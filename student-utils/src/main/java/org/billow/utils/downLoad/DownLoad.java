package org.billow.utils.downLoad;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件下载
 */
public class DownLoad {

    public static void downLoad(String fileName, InputStream in, HttpServletResponse response) {
        //设置文件MIME类型
        response.setContentType("application/zip");
        //设置Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            //写文件
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
