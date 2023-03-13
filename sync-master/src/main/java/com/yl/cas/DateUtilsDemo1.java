package com.yl.cas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilsDemo1 {
    public static final ThreadLocal<SimpleDateFormat> sdfThreadLocal = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static final Date parseByThreadLocal(String stringDate) throws ParseException {
        return sdfThreadLocal.get().parse(stringDate);
    }

    public static void main(String[] args) throws Exception {
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                try {
                    System.out.println(DateUtilsDemo1.parseByThreadLocal("2022-06-01 11:11:11"));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    sdfThreadLocal.remove();
                }
            }, String.valueOf(i)).start();
        }
    }
}
