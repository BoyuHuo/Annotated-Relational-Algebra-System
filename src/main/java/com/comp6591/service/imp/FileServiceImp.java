package com.comp6591.service.imp;

import com.comp6591.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImp implements FileService {

    private final static String filePath = "src/main/resources/data";

    public static List<Map<String, String>> readData(String filename, String regex, String encoding) {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        File file = new File(filePath+"/"+filename);
        String[] columnName;
        try {
            FileInputStream inStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    inStream, encoding));
            String line = new String();
            // read the column name
            columnName = reader.readLine().split(regex);
            //get basket num and min support
            while ((line = reader.readLine()) != null) {
                Map<String, String> dataLine = new HashMap<>();
                String[] vals = line.split(regex);
                for (int i = 0; i < vals.length; i++) {
                    dataLine.put(columnName[i], vals[i]);
                }
                data.add(dataLine);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


    public String saveFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (!file.isEmpty()) {
            try {
                System.out.println(file.getSize());
                BufferedOutputStream out = new BufferedOutputStream(
                        //Paths.get()
                        new FileOutputStream(new File(filePath + "/" + fileName)));
                //logger
                System.out.println(filePath + file.getOriginalFilename());
                out.write(file.getBytes());
                out.flush();
                out.close();
                //logger : file name + status
                System.out.println("finish write");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                fileName = null;
            } catch (IOException e) {
                //child class of IO exception
                e.printStackTrace();
            }
        } else {
            fileName = null;
        }
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

}
