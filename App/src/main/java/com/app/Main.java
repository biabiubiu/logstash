package com.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 描述:
 * 版本: 1.0.0
 * 版权: apache2.0
 *
 * @author anle5 --- 2022/5/16 22:48
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\anle5\\Desktop\\logstash_2.zip";
        File file = new File(path);
//        split(file);
        merge();
    }

    public static void split(File file) {
        String path = "C:\\Users\\anle5\\Desktop\\logstash\\App\\src\\main\\resources\\files\\";
        try (FileInputStream inputStream = new FileInputStream(file)) {
            byte[] b = new byte[1024 * 20000];
            int i = 0;
            while (inputStream.read(b) > 0) {
                String name = "file" + i;
                System.out.println(name);
                try (FileOutputStream outputStream = new FileOutputStream(path + name)) {
                    outputStream.write(b);
                } catch (Exception ignored) {
                }
                i++;
            }
        } catch (Exception ignored) {

        }

    }

    public static void merge() throws FileNotFoundException {
        String path = "C:\\Users\\anle5\\Desktop\\logstash\\App\\src\\main\\resources\\files\\";
        String out = "C:\\Users\\anle5\\Desktop\\logstash\\App\\src\\main\\resources\\logstash\\logstash.tar";
        File file = new File(path);

        try (FileOutputStream outputStream = new FileOutputStream(out);){
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                assert files != null;
                Arrays.stream(files).sorted(Comparator.comparingInt(q -> Integer.parseInt(q.getName().substring(4))))
                        .forEach(f -> {
                    try (FileInputStream inputStream = new FileInputStream(f);
                         ) {
                        System.out.println(f.getName());

                        byte[] b = new byte[1024 * 20000];
                        while (inputStream.read(b) > 0) {
                            outputStream.write(b);
                        }
                        outputStream.flush();
                    } catch (Exception ignore) {
                    }
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
