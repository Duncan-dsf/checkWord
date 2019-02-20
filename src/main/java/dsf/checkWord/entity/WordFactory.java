package dsf.checkWord.entity;

import dsf.checkWord.entity.doc.Doc;
import dsf.checkWord.entity.docx.Docx;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.poifs.property.DirectoryProperty;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

/**
 * @author 董少飞
 * @date 2018/10/6
 */
public class WordFactory {

    /**
     * 新生成文件用来显示校验结果
     * 新文件的文件名为 fileRealName + RESULT_TAG + i + suffix
     */
    private static final String RESULT_TAG = "_result";

    public static AbstractWord getWord(String file) {

        InputStream inputStream = null;
//        File wordCopy = getWordCopy(file);
        try {

            inputStream = new BufferedInputStream(new FileInputStream(file));
            FileMagic fileMagic = FileMagic.valueOf(inputStream);
            if(fileMagic == FileMagic.OLE2) {

                return new Doc(file);
            } else if(fileMagic == FileMagic.OOXML) {

                System.out.println("this file is docx");
                return new Docx(file);
            }
        } catch (FileNotFoundException e) {

            System.out.println("file not found");
        } catch (IOException e) {

            e.printStackTrace();
        } finally {

            if(inputStream != null) {

                try {

                    inputStream.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    protected static File getWordCopy(String fileName) {

        String newFileName;
        int index;
        File newFile = null;
        if((index = fileName.lastIndexOf('.')) >= 0) {

            String name = fileName.substring(0, index);
            String suffix = fileName.substring(index);
            newFileName = name + RESULT_TAG + suffix;

            newFile = new File(newFileName);
            for(int i=1; newFile.exists(); i++) {
                newFileName = name + RESULT_TAG + i + suffix;
                newFile = new File(newFileName);
            }
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                System.out.println("创建文件失败");
                e.printStackTrace();
            }
        }

        try (FileInputStream fileInputStream = new FileInputStream(fileName); FileOutputStream fileOutputStream = new FileOutputStream(newFile)) {

            byte[] bytes = new byte[1000];
            while(fileInputStream.read(bytes) > 0) {

                fileOutputStream.write(bytes);
            }
        } catch (IOException e) {

            System.out.println("ioException");
            e.printStackTrace();
        }

        return newFile;
    }

}
