package com.hendi.main;

import static com.hendi.main.Quickstart.input;
import static com.hendi.main.Quickstart.prop;
import com.hendi.model.Shinobi;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Senju Hashirama
 */
public class ReadCSV {

    public List<NBP> ReadCSV() throws FileNotFoundException, IOException {
        input = new FileInputStream("conf/config.properties");
        // load a properties file
        prop.load(input);
        
        int i = 0;
        int counter = 0;
        List<Shinobi> arr = new ArrayList<>();
//        String Dir = "E:/Testing/CSV";
        String Dir = prop.getProperty("dir");
        File file = new File(Dir);
        File[] listFiles = file.listFiles();
        
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS"); // NBP_yyyymmddhhmm
        Date now = new Date();
        String strDate = sdfDate.format(now);

        for (File listFile : listFiles) {
            FileInputStream fstream = new FileInputStream(listFile);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(fstream))) {
                String name1 = listFile.getName();
                    String filename = name1.substring(0, name1.lastIndexOf("."));
                String line;
                br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] nbp = line.split("\\|");
                    // process the line.
                    //System.out.println(line);
                    String[] raw = line.split("\\|");
                    String id = raw[0];
                    String nama = raw[1];
                    String level = raw[2];
                    String jutsu = raw[3];
                    String desa = raw[4];
                    int columnCount = raw.length;
                    if(!id.equalsIgnoreCase("id")){ 
                         arr.add(new csvObject(id, nama, level, jutsu, desa, columnCount)); }


//                    printnbpList(arr);
                } // Block While

            } // Block Try
            if (counter == 99) {
                new Request(arr).start();
                counter = 0;// counter direset
                arr.clear(); // List direset
            }
            counter++;
            i++;
            if (i == arr.size()) { // Bila sudah end of file --> break (i == 100000)
                break;
            }

        } // Block For

        int rowCOunt = arr.size();
        int columnCount = arr.get(rowCOunt - 1).getColumnCount();
        int x = 0;

        System.out.println("Jumlah File : " + listFiles.length);
        System.out.println("Jumlah Baris : " + rowCOunt);
        System.out.println("Jumlah Kolom : " + columnCount);

        return arr;
    }
    
    

}
