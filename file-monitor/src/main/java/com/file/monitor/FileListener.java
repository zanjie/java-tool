package com.file.monitor;

import com.ftp.upload.MeizuPhone;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

/**
 * Created by zanjie on 2017/3/25.
 */
public class FileListener implements FileAlterationListener {
    public void onStart(FileAlterationObserver observer) {
    }

    public void onDirectoryCreate(File directory) {
    }

    public void onDirectoryChange(File directory) {
    }

    public void onDirectoryDelete(File directory) {
    }

    public void onFileCreate(final File file) {
        System.out.println("onFileCreate:" + file.getName());
        System.out.print("uploading=======" + file.getName() + "==========");
        MeizuPhone.upFile(file);
    }

    public void onFileChange(File file) {
    }

    public void onFileDelete(File file) {
    }

    public void onStop(FileAlterationObserver observer) {
    }

}
