package com.moon;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {
    private static HttpURLConnection httpURLConnection = null;

    public static void main(String[] args) throws IOException {
        ArrayList<String[]> list = readCSV("D:\\MyDocs\\Programming\\Projects\\Squad.com\\Project Source Code\\SquadDataCSVReader\\resources\\csv\\SquadCSV.csv");
        sendData(list);
    }

    private static void sendData(ArrayList<String[]> list) {
        try{
            list.forEach(strings -> {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("{ ");
                stringBuffer.append("email:");
                stringBuffer.append(strings[0]);
                stringBuffer.append(',');
                stringBuffer.append("name:");
                stringBuffer.append(strings[1]);
                stringBuffer.append("jobTitle:");
                stringBuffer.append(strings[2]);
                stringBuffer.append(',');
                stringBuffer.append("phoneNumber:");
                stringBuffer.append(strings[3]);
                stringBuffer.append(',');
                stringBuffer.append("password:");
                stringBuffer.append(strings[4]);
                stringBuffer.append(',');
                stringBuffer.append("type:");
                stringBuffer.append(strings[5]);
                try {
                    URL url = new URL("http://localhost:8081/users/");
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setRequestProperty("Content-Type" , "application/json");
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.writeBytes(stringBuffer.toString());
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuffer respone = new StringBuffer();
                    String line;
                    while ((line = bufferedReader.readLine()) != null){
                        respone.append(line);
                        respone.append('\r');
                    }
                    bufferedReader.close();
                    System.out.println(respone.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(httpURLConnection != null) httpURLConnection.disconnect();
        }
    }

    @NotNull
    private static ArrayList<String[]> readCSV(String path) throws IOException {
        ArrayList<String[]> strings = new ArrayList<>();
        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        bufferedReader.lines().forEach(s -> {
            Random random = new Random();
            String[] data = s.split(",");
            strings.add(data);
            System.out.println(Arrays.toString(data));
        });
        bufferedReader.close();
        inputStream.close();
        return strings;
    }
}
