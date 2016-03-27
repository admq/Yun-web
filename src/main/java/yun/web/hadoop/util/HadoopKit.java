package yun.web.hadoop.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by larry on 16/1/18.
 */
public class HadoopKit {
    private static Configuration config = new Configuration();
    private static FileSystem fileSystem = null;
    private static String defaultPath = "/default/";
    private static Path tempPath = null;

    static {
        try {
            fileSystem = FileSystem.get(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static boolean upload(File file) {
        FSDataOutputStream fsDataOutputStream = null;
        FileInputStream fileInputStream = null;
        try {
            if (tempPath == null) tempPath = new Path(defaultPath + file.getName());
            fsDataOutputStream = fileSystem.create(tempPath);
            fileInputStream = new FileInputStream(file);
            IOUtils.copyBytes(fileInputStream, fsDataOutputStream, config, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) fileInputStream.close();
                if (fsDataOutputStream != null) fsDataOutputStream.close();
                tempPath = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static boolean upload(File file, Path outPath) {
        tempPath = outPath;
        upload(file);
        return true;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (fileSystem != null) fileSystem.close();
    }
}
