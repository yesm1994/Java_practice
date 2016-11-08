package com.steven.io; /**
 * Created by Steven on 2016/11/7.
 */
import java.io.*;
public class CharStream {
    private static FileReader fileReader = null;
    private static PrintWriter printWriter = null;
    public static void run(){
        CharStream cs = new CharStream();
        cs.charRead();
    }


    public void charRead(){
        char[] buffer = new char[1024];
        printWriter = new PrintWriter(System.out);
        int tmp = 0;
        try {
            fileReader = new FileReader(Constants.PATH);
            while((tmp = fileReader.read(buffer))!=-1){
                printWriter.write(buffer,0,tmp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileReader.close();
                printWriter.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


}
