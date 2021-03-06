package day6Task;

import java.io.*;

public class WordCounter implements Runnable {
    private static class Lock {};
    public static final Object lock = new Lock();
    volatile static int wordCount;
    private final File srcFile;
    private final PrintWriter dstFile;
    private final String searchWord;

    public WordCounter(File srcFile, PrintWriter dstFile, String searchWord) {
        this.srcFile = srcFile;
        this.dstFile = dstFile;
        this.searchWord = searchWord;
    }

    public static int getWordCount() {
        return wordCount;
    }

    @Override
    public void run() {
        try (BufferedReader src = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(srcFile), "windows-1251"))) {
            String line;
            int oldWordCount = wordCount;
            while ((line = src.readLine()) != null) {
                int index = -1;
                while ((index = line.indexOf(searchWord, index + 1)) != -1) {
                    synchronized (lock) {
                        wordCount++;
                    }
                    if (wordCount % 5 == 0) {
                        dstFile.println(wordCount);
                    }
                }
            }
            System.out.println(srcFile.getName() + " end. " + (wordCount - oldWordCount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
