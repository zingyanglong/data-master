package com.yl.cas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtilsDemo2 {
    // 解决方法四：DateTimeFormatter 代替 SimpleDateFormat
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String format(LocalDateTime localDateTime) {
        return DATE_TIME_FORMAT.format(localDateTime);
    }

    public static LocalDateTime parse(String dateString) {
        return LocalDateTime.parse(dateString, DATE_TIME_FORMAT);
    }

    public static void main(String[] args) throws Exception {
        dateTimeFormatter();
    }

    private static void dateTimeFormatter() {
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                try {
                    System.out.println(DateUtilsDemo2.format(DateUtilsDemo2.parse("2022-06-01 11:11:11")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
