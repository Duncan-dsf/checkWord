package dsf.checkWord.entity;

import dsf.checkWord.dto.AbstractWordMap;
import dsf.checkWord.util.MyThreadPoolExecutor;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Picture;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 董少飞
 * @date 2018/10/15
 */
public abstract class AbstractWord extends AbstractWordMap implements Word {

    private String fileName;

    public AbstractWord(String file) {

        fileName = file;
    }

    @Override
    public String getName() {

        return fileName;
    }

    protected AbstractWord() {

        initParagraphMap();
    }

    protected void initParagraphMap() {

        MyThreadPoolExecutor.getMyThreadPoolExecutor().execute(() -> {

            WordParagraph paragraph = null;
            int numParagraphs = getNumParagraphs();
            for(int i=0; i < numParagraphs; i++) {

                if((paragraph = getParagraph(i)) != null) {

//                    paragraph.merge();
                    addParagraphAtTitleLevel(paragraph.getLvl(), paragraph);
                }
            }
            isMapReady = true;
            synchronized (isMapReady) {

                isMapReady.notifyAll();
            }
        });
    }

}
