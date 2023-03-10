package com.yl.completableFuture;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CompletableFutureMallDemo {
    static List<NetMall> netMallList =
            Arrays.asList(new NetMall("jd"), new NetMall("dangdang"), new NetMall("taobao"));

    /**
     * step by step 一家家搜
     */
    public static List<String> getPrice(List<NetMall> netMallList, String productName) {
        // 《mysql》 in jd price is 88.05
        return netMallList.stream()
                .map(
                        netMall ->
                                String.format(
                                        productName + " in %s price is %.2f",
                                        netMall.getNetMallName(),
                                        netMall.calculatePrice(productName)))
                .collect(Collectors.toList());
    }


    public static List<String> getPriceCompletableFuture(
            List<NetMall> netMallList, String productName) {
        return netMallList.stream()
                .map(
                        netMall ->
                                CompletableFuture.supplyAsync(
                                        () ->
                                                String.format(
                                                        productName + " in %s price is %.2f",
                                                        netMall.getNetMallName(),
                                                        netMall.calculatePrice(productName))))
                .collect(Collectors.toList())
                .stream()
                // 可替换成 .map(CompletableFuture::join)
                .map(s -> s.join())
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> list = getPrice(netMallList, "mysql从入门到放弃");

        List<String> list1 = getPriceCompletableFuture(netMallList, "mysql从入门到放弃");
        for (String element : list) {
            System.out.println("element = " + element);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("---costTime：" + (endTime - startTime) + "毫秒");
    }
}

class NetMall {
    @Getter
    private final String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    public double calculatePrice(String productName) {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}
