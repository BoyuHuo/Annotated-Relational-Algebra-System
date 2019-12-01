package com.comp6591.service.imp;

import com.comp6591.entity.Record;
import com.comp6591.entity.Table;
import com.comp6591.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FileServiceImp implements FileService {

    private final static String filePath = "src/main/resources/data";

    public Table readData(String filename, String regex, String encoding) {
        Table data = new Table();
        File file = new File(filePath + "/" + filename);
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
                Record dataLine = new Record();
                String[] vals = line.split(regex);
                for (int i = 0; i < vals.length; i++) {
                    dataLine.getFields().put(columnName[i], vals[i].trim());
                }
                data.getRecords().add(dataLine);
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
                        new FileOutputStream(new File(Paths.get(filePath, fileName).toString())));
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

    public void writeData(String fileName, Table data) {

        try {

            FileOutputStream fos = new FileOutputStream(filePath + "/"+ fileName);
            OutputStreamWriter isr = new OutputStreamWriter(fos, "UTF-8");
            final BufferedWriter writer = new BufferedWriter(isr);

            Set<String> columns = data.getColumnSet();
            String lineStr = String.join(",", columns);

            writer.write(lineStr);
            writer.write("\n");

            data.getRecords().forEach(record -> {
                List<String> lineVal = new ArrayList<>();

                columns.forEach(c -> {
                    lineVal.add(record.getFields().get(c));
                });

                try {
                    writer.write(String.join(",", lineVal));
                    writer.write("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            writer.close();

            System.out.println("finish write");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
