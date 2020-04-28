package org.wxz.nconfsyscommon.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author xingze Wang
 * @create 2020/4/28 16:22
 */
public class FileUtil {

        public static void download(String filePath, HttpServletResponse res) throws IOException {
            // 发送给客户端的数据
            OutputStream outputStream = res.getOutputStream();
            byte[] buff = new byte[1024];
            BufferedInputStream bis = null;
            // 读取filename
            bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
            int i = bis.read(buff);
            while (i != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                i = bis.read(buff);
            }
        }
}
