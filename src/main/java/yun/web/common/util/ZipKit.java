package yun.web.common.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by larry on 16/1/20.
 */
public class ZipKit {
    public static File zipFile(String targetPath, List<File> list) {
        String pathname = String.valueOf(Calendar.getInstance().getTimeInMillis());
        File zipFile = new File(targetPath + File.separator + pathname + ".zip");
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));

            InputStream inputStream = null;
            for (File file : list) {
                inputStream = new FileInputStream(file);
                zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                int temp = 0;
                while((temp = inputStream.read()) != -1){
                    zipOutputStream.write(temp);
                }
                inputStream.close();
            }
            zipOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zipFile;
    }

    public static File zipFile(String targetPath, String dir) {
        File directry = new File(dir);
        if(!directry.isDirectory()) {
            System.out.println("dir is not zip directry");
            return null;
        }
        List<File> list = new ArrayList<File>();
        for (File file : directry.listFiles()) {
            if(file.isDirectory()) {
                // 如果是文件夹另做处理
            } else {
                list.add(file);
            }
        }
        return zipFile(targetPath, list);
    }

    public static void unZipFile(String targetPath, File file) {
        if(!file.getName().endsWith(".zip")) {
            System.out.println("file is not zip file");
            return;
        }
        File targetDir = new File(targetPath);
        if(!targetDir.exists()) {
            System.out.println("target directry is not exist");
            return;
        }
        try {
            ZipFile zipFile = new ZipFile(file);
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
            ZipEntry entry = null;
            InputStream input = null;
            OutputStream output = null;
            File outFile = null;
            while((entry = zipInputStream.getNextEntry()) != null){
                System.out.println("解压缩" + entry.getName() + "文件");
                outFile = new File(targetPath + File.separator + entry.getName());
                if(!outFile.getParentFile().exists()){
                    outFile.getParentFile().mkdir();
                }
                if(!outFile.exists()){
                    outFile.createNewFile();
                }
                input = zipFile.getInputStream(entry);
                output = new FileOutputStream(outFile);
                int temp = 0;
                while((temp = input.read()) != -1){
                    output.write(temp);
                }
                input.close();
                output.close();
            }
            zipInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
