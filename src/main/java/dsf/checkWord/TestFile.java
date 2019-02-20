package dsf.checkWord;

import java.io.*;
import java.util.Date;

/**
 * @author 董少飞
 * @date 2018/11/18
 */
public class TestFile {

    public static void main(String[] a) throws IOException, InterruptedException {

        Date date = new Date();
        String fileName = date.getMinutes() + "-" + date.getSeconds();
        File file = new File(fileName);
        file.createNewFile();
        FileInputStream fileInputStream = new FileInputStream(file);
        FileDescriptor fd = fileInputStream.getFD();
        fileInputStream.close();
        fd.sync();
        Thread.sleep(100000);
    }


}
