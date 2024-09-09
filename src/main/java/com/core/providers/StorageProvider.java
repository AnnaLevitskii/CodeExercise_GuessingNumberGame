package com.core.providers;

import com.core.models.User;
import com.core.utils.Path;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StorageProvider implements Path {

    public static List<User> getStorage(){
       Map<String, String> dataMap;
       try {
        dataMap = readCsvToHashMap();
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
       return dataMap.entrySet().stream()
                .map(entry -> new User(entry.getKey(), Integer.parseInt(entry.getValue())))
                .collect(Collectors.toList());
    }
    public static void setStorage(List<User> list){
        Map<String, String> map = list.stream()
                .collect(Collectors.toMap(User::getName,
                        user -> String.valueOf((user.getScore())
                        )));
        try {
            writeMapToCsv(map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, String> readCsvToHashMap() throws IOException {
        Map<String, String> map = new HashMap<>();
        try (CSVReader reader = new CSVReader(new FileReader(Path.STORAGE_PATH))) {
            String[] header = reader.readNext();
            if (header == null) {
                return map;
            }
            String[] row;
            boolean hasData = false;

            while ((row = reader.readNext()) != null) {
                if (row.length < 2) {
                    System.err.println("Invalid row: " + String.join(",", row));
                    continue;
                }
                map.put(row[0], row[1]);
                hasData = true;
            }

            if (!hasData) {
                System.out.println("The storage.csv contains no data.");
            }
        }
        return map;
    }

    private static void writeMapToCsv(Map<String, String> map) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(Path.STORAGE_PATH))) {
            writer.writeNext(new String[]{"Name", "Score"});

            for (Map.Entry<String, String> entry : map.entrySet()) {
                writer.writeNext(new String[]{entry.getKey(), entry.getValue()});
            }
        }
    }
}
