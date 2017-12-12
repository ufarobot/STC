package day6Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {
    public static final String FILES_FOLDER = "src/day6Task/files/";
    public static final String RESULT_FILE = "result.txt";
    public static final String SEARCHED_WORD = "страдание";

    public static void main(String[] args) throws FileNotFoundException {

        File srcFilesFolder = new File(FILES_FOLDER);
        PrintWriter dstFile = new PrintWriter (new File(RESULT_FILE));
        File[] srcFiles = srcFilesFolder.listFiles();
        int filesCount = 0;
        if (srcFiles == null) {
            System.out.println(0);
            return;
        }

        filesCount = srcFiles.length;

        Thread[] threads = new Thread[filesCount];
        for (int i = 0; i < srcFiles.length; i++) {
            threads[i] = new Thread(new WordCounter(srcFiles[i], dstFile, SEARCHED_WORD));
            threads[i].start();
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(WordCounter.getWordCount());
        dstFile.close();
    }
}
