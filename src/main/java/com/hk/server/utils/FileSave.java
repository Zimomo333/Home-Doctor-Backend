package com.hk.server.utils;

import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

/**
 * @program: gg2020
 * @description: 文件保存工具类
 * @author: 头微凉
 * @create: 2020-09-12 10:29
 */
public class FileSave {
    public static boolean saveFile(MultipartFile file, String path) {
        String os = System.getProperty("os.name");//判断操作系统
        if (os.toLowerCase().startsWith("win"))//window系统
        {
            path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + path;
        } else {
            path = "/usr/local/upload/" + path;
        }
        if (!file.isEmpty()) {
            //创建文件对象
            File saveDir = new File(new File(path).getAbsolutePath());
            //如果文件的父目录不存在则创建
            if (!saveDir.getParentFile().exists())
                saveDir.getParentFile().mkdirs();
            try {
                file.transferTo(saveDir);
            } catch (IOException e) {
                System.out.println(e);
                return false;
            }
        }
        return true;
    }

    public static int fileNums(String path) {
        String os = System.getProperty("os.name");//判断操作系统
        if (os.toLowerCase().startsWith("win"))//window系统
        {
            path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + path;
        } else {
            path = "/usr/local/upload/" + path;
        }
        File file = new File(path);
        return file.listFiles().length;
    }

    /**
     * Description:清除 path 目录下中不包换在 fileNames下的所有文件，已节约磁盘空间
     *
     * @param path:      需要清理的目录
     * @param fileNames: 需要保留的文件名，如果为空，则清理所有
     * @return void
     * @author 头微凉
     * 创建时间: 2020/10/4
     */
    public static void CleanPicture(String path, HashSet<String> fileNames) {
        String os = System.getProperty("os.name");//判断操作系统
        if (os.toLowerCase().startsWith("win"))//window系统
        {
            path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + path;
        } else {
            path = "/usr/local/upload/" + path;
        }
        File parent = new File(path);
        if (!parent.isDirectory()) return;
        File[] files = parent.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (fileNames == null || !fileNames.contains(files[i].getName()))
                files[i].delete();
        }
        //没有需要保留的图片，整个文件夹删除
        if (fileNames == null)
            parent.delete();
    }


}