package com.yl.cas;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilsDemo {
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date parseDate(String stringDate) throws Exception {
        return sdf.parse(stringDate);
    }

    public static void main(String[] args) throws Exception {
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                try {
                    System.out.println(DateUtilsDemo.parseDate("2022-06-01 11:11:11"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
