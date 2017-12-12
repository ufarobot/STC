package day6Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {
    private static final String FILES_FOLDER = "src/day6Task/files/";

    public static void main(String[] args) throws FileNotFoundException {

        File srcFilesFolder = new File(FILES_FOLDER);
        PrintWriter dstFile = new PrintWriter (new File("result.txt"));
        File[] srcFiles = srcFilesFolder.listFiles();
        int filesCount = 0;
        if (srcFiles == null) {
            System.out.println(0);
            return;
        }

        filesCount = srcFiles.length;

        Thread[] threads = new Thread[filesCount];
        for (int i = 0; i < srcFiles.length; i++) {
            threads[i] = new Thread(new WordCounter(srcFiles[i], dstFile, "страдание"));
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
