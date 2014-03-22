package com.urqa.android.collector;

import android.util.Log;

import com.urqa.android.UrQAHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public final class LogCollector {

    public static String getLog() {
        try {
            LogCollector collector = new LogCollector();
            Process process = Runtime.getRuntime().exec(collector.commandLogCat());
            return collector.convertStreamToString(process.getInputStream());
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
        final int line = UrQAHelper.getInstance().getLogLine();
        final int start = (end - line) < 0 ? 0 : (end - line);

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
