package com.tangshengbo.service;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/12/22.
 */
public class BigdecimalTests {

    public static void main(String[] args) {

        Long total = 10000L;
        double totalYuan = 100.0;
        System.out.println("totalYuan" + totalYuan);
        BigDecimal totalBigDecimal = BigDecimal.valueOf(totalYuan);
        double settlementAmountYuan = 50.0;
        System.out.println("settlementAmountYuan" + settlementAmountYuan);
        BigDecimal settlementBigDecimal = BigDecimal.valueOf(settlementAmountYuan);

        BigDecimal calcResultBigDecimal = totalBigDecimal.subtract(settlementBigDecimal).divide(totalBigDecimal, 2, BigDecimal.ROUND_UP);
        double calcResult = (totalYuan - settlementAmountYuan) / totalYuan;
        System.out.println(calcResult + "\t");
        System.out.println(calcResultBigDecimal.doubleValue() + "calcResultBigDecimal");

    }
}
