package com.urqa.android.collector;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * @author seunoh on 2014. 2. 11..
 */
public final class MemoryManager extends AbstractManager {

    public MemoryManager(Context context) {
        super(context);
    }

    public boolean externalMemoryAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    public long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }


    public int getAvailableExternalMemorySizeMegaByte() {
        return byteToMegaByte(getAvailableExternalMemorySize());
    }

    public long getAvailableExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        } else {
            return ERROR;
        }
    }

    public long getTotalExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks * blockSize;
        } else {
            return ERROR;
        }
    }

    public int getTotalMemoryMegaByte() {
        return byteToMegaByte(getTotalMemory());
    }

    public long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    public int getFreeMemoryMegaByte() {
        return byteToMegaByte(getFreeMemory());
    }

    public long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }


    public int getMaxMemoryMegaByte() {
        return byteToMegaByte(getMaxMemory());
    }

    public long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    public boolean getSystemLowMemory() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        return memoryInfo.lowMemory;
    }

    private int byteToMegaByte(long byteValue) {
        return (int) ((byteValue / 1024) / 1024);
    }
}
