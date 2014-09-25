/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendi.main;

import com.hendi.model.NBP;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Senju Hashirama
 */
public class Quickstart {

    /**
     * @throws java.io.IOException
     */
    public static Properties prop = new Properties();
    public static InputStream input;

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        NumberFormat nf = NumberFormat.getNumberInstance();
        Calendar calendar = Calendar.getInstance();
        DateFormat df = DateFormat.getTimeInstance(DateFormat.LONG);

        long lStartTime = System.currentTimeMillis();
        ReadCSV baca = new ReadCSV();

        List<Shinobi> hasil = baca.ReadCSV();

        Request send = new Request();
        send.PostData(hasil);

        long lEndTime = System.currentTimeMillis();
        long milliseconds = lEndTime - lStartTime;

        int seconds = (int) ((milliseconds / 1000) % 60);
        int minutes = (int) ((milliseconds / 1000) / 60);
        int hours = (int) (minutes / 60);

        System.out.println("Start : " + df.format(calendar.getTime()));
        System.out.println("=============================================");
        System.out.println("\tJumlah Record : " + nf.format(hasil.size()));
        System.out.println("=============================================");
        System.out.println("End : " + df.format(calendar.getTime()));

        System.out.println("Elapsed Times : " + hours + " hours " + minutes + " minutes " + seconds + " seconds");
        System.out.println("");
    }

}
