package com.fancy.mvvmdemo;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pengkuanwang
 * @date 2019-08-28
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";
    private Context mContext;
    //CrashHandler实例
    private static CrashHandler INSTANCE = new CrashHandler();
    /**
     * 用来存储异常信息和设备信息
     */
    private Map<String, String> info = new HashMap<>();

    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        mContext = context;
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable ex) {
        collectDeviceInfo();
        //保存日志文件
        saveCrashInfoFile(ex);
        SystemClock.sleep(3000);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    /**
     * 手机设备信息
     */
    private void collectDeviceInfo() {
        info.put("VERSION_NAME", BuildConfig.VERSION_NAME);
        int sdkInt = Build.VERSION.SDK_INT;
        String model = Build.MODEL;
        info.put("sdkInt", "系统版本:" + sdkInt);
        info.put("model", "手机型号:" + model);
    }

    /**
     * 将保存信息保存到本地
     *
     * @param ex
     */
    private void saveCrashInfoFile(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : info.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        long timestamp = System.currentTimeMillis();
        String time = formatter.format(new Date());
        String absolutePath = Environment.getExternalStorageDirectory().getAbsoluteFile().getAbsolutePath() + File.separator + "crash" + File.separator;
        String fileName = "crash-" + time + "-" + timestamp + ".log";
        FileOutputStream fos = null;
        try {
            File dir = new File(absolutePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            fos = new FileOutputStream(absolutePath + fileName);
            fos.write(sb.toString().getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
