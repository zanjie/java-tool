package com.file.monitor;

import com.java.util.Config;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zanjie on 2017/3/25.
 */
public class FileMonitor {
    protected static String[] monitorDirs = Config.getValue("file.monitor.dirs").toString().split(";");

    List<FileAlterationMonitor> monitors = new ArrayList<FileAlterationMonitor>();

    public void monitoring(String... dirs) {
        // 轮询间隔 5 秒
        long interval = TimeUnit.SECONDS.toMillis(Config.getValueInt("file.monitor.time"));
        for (String dir : dirs) {
            FileAlterationObserver observer = new FileAlterationObserver(dir, null, null);
            observer.addListener(new FileListener());
            FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
            try {
                System.out.println("add monitor on " + dir);
                monitor.start();
                monitors.add(monitor);
                Thread.sleep(200);
            } catch (Exception e) {
                System.err.println("some thing wrong when monitor " + dir);
                e.printStackTrace();
            }
        }

    }


    public void stop() throws Exception {
        for (FileAlterationMonitor monitor : monitors)
            monitor.stop();
    }

    public static void main(String[] args) {
        boolean breaked = false;
        if (monitorDirs.length > 5) {
            System.err.println("需要监测的目录文件数应该限制在五个以内");
            breaked = true;
        } else
            new FileMonitor().monitoring(monitorDirs);
    }


}
