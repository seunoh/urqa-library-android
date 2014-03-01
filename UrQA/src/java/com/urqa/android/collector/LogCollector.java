package com.urqa.android.collector;

import android.content.Context;
import android.util.Log;

import com.urqa.android.UrQAHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public final class LogCollector {

    public static String getLog(Context context) {
        try {
            LogCollector collector = new LogCollector();
            Process logCatProcess = Runtime.getRuntime().exec(collector.commandLogCat());
            return collector.convertStreamToString(logCatProcess.getInputStream());
        } catch (IOException e) {
            Log.e("LogCollector", e.getMessage(), e);
            return "";
        }
    }


    private String convertStreamToString(InputStream is) throws IOException {
        return listToString(convertStreamToList(is));
    }

    private String commandLogCat() {
        return "logcat" + " -d" + " -v" + " time" + " tags" + " *:V";
    }

    private String listToString(List<String> list) {
        final int end = list.size();
        final int logLine = UrQAHelper.getInstance().getLogLine();
        final int start = (end - logLine) < 0 ? 0 : (end - logLine);

        StringBuilder output = new StringBuilder();
        for (int i = start; i < end; i++) {
            output.append(list.get(i)).append("\n");
        }

        return output.toString();
    }


    private List<String> convertStreamToList(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        List<String> list = new ArrayList<String>();

        String line;
        while ((line = reader.readLine()) != null) {
            list.add(line + "\n");
        }
        is.close();
        return list;
    }

}
