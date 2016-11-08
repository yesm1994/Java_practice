package com.steven.zip;
import javax.swing.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

/**
 * Created by Steven on 2016/11/8.
 */

public class ZipApp {
    private static final String PATH = "F:/待压缩文件夹";
    private ZipApp(){
        File files = new File(PATH);
    }

    public static void run(){
        ZipApp zipApp = new ZipApp();
    }

    private void zipFiles(String zipFilePath, File file) throws IOException{
        System.out.println("压缩 ing ： ");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFilePath));
        BufferedOutputStream bo = new BufferedOutputStream(out);
        zipFiles(out, zipFilePath, file, bo);
        out.close();
        bo.close();
        System.out.println("压缩 End ： ");
    }

    private void zipFiles(ZipOutputStream out, String path, File file, BufferedOutputStream bo) throws IOException{
        File[] f = file.listFiles();
        for (int i = 0; i < f.length; i++) {
            zipFiles(out, path, f[i], bo);
        }
    }
}
