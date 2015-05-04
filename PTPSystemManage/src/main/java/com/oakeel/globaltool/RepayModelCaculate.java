/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oakeel.globaltool;

import com.oakeel.RepayItem;
import com.oakeel.ejb.ptpEnum.RepayModel;
import com.oakeel.ejb.ptpEnum.SplitUnit;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author root
 */
public class RepayModelCaculate {


    //计算还款模型输入还款模型、还款单元、贷款总额、年利率、还款周期、开始时间
    public static void caculateRepayModel(RepayModel repayModel, SplitUnit splitUnit, BigDecimal totalLoan, BigDecimal yearRate, int repayPeriod, Date startDate, List<RepayItem> repayItemList) {
        if (repayModel == RepayModel.定额本息) {
            caculateRepayModel_a(splitUnit, totalLoan, yearRate, repayPeriod, startDate, repayItemList);
        } else if (repayModel == RepayModel.定额本金) {
            caculateRepayModel_b(splitUnit, totalLoan, yearRate, repayPeriod, startDate, repayItemList);
        } else if (repayModel == RepayModel.到期支付) {
            caculateRepayModel_c(splitUnit, totalLoan, yearRate, repayPeriod, startDate, repayItemList);
        }

    }

    //定额本息
    private static void caculateRepayModel_a(SplitUnit splitUnit, BigDecimal totalLoan, BigDecimal yearRate, int repayPeriod, Date startDate, List<RepayItem> repayItemList) {
        //[贷款本金×月利率×（1+利率）^还款月数]÷[（1+月利率）^还款月数－1];^是乘方
        if (splitUnit == SplitUnit.年) {

            BigDecimal currentprincipal;//本金
            BigDecimal currentinterest;//利息
            BigDecimal currentsubtotal;//小计
            BigDecimal currentbalance = totalLoan;//贷款余额
            //1+利率
            BigDecimal ratex = yearRate.add(BigDecimal.valueOf(1), MathContext.DECIMAL64);
            BigDecimal resultrate = ratex;
            //(1+季度利率）^还款月数
            for (int i = 0; i < (repayPeriod - 1); i++) {
                resultrate = resultrate.multiply(ratex, MathContext.DECIMAL64);
            }
            //贷款本金×季度利率×（1+利率）^还款月数
            BigDecimal upmoney = totalLoan.multiply(yearRate).multiply(resultrate);
            //（1+季度利率）^还款月数－1
            BigDecimal downmoney = resultrate.subtract(BigDecimal.valueOf(1), MathContext.DECIMAL64);
            currentsubtotal = upmoney.divide(downmoney, MathContext.DECIMAL64);//每季度还款总额
            Calendar calender = Calendar.getInstance();
            Date repayDate = startDate;
            for (int i = 0; i < repayPeriod; i++) {
                RepayItem item = new RepayItem();
                calender.setTime(repayDate);
                calender.add(Calendar.YEAR, 1);//一个季度三个月
                repayDate = calender.getTime();
                item.setRepayDate(repayDate);//设置还款时间节点
                currentinterest = currentbalance.multiply(yearRate, MathContext.DECIMAL64);//计算利息
                item.setInterest(currentinterest);
                currentprincipal = currentsubtotal.subtract(currentinterest, MathContext.DECIMAL64);//计算所还本金
                item.setPrincipal(currentprincipal);
                item.setBeforeBalance(currentbalance);//计算期初余额
                currentbalance = currentbalance.subtract(currentprincipal, MathContext.DECIMAL64);
                item.setAfterBalance(currentbalance);//计算期末余额
                item.setSubtotal(currentsubtotal);//每月还款总额
                item.setPeriodNum(i + 1);
                repayItemList.add(item);
            }
        } else if (splitUnit == SplitUnit.季度) {

            BigDecimal currentprincipal;//本金
            BigDecimal currentinterest;//利息
            BigDecimal currentsubtotal;//小计
            BigDecimal currentbalance = totalLoan;//贷款余额
            //计算季度利率
            BigDecimal quarterlyRate = yearRate.divide(BigDecimal.valueOf(4), MathContext.DECIMAL64);//一年四个季度
            //1+利率
            BigDecimal ratex = quarterlyRate.add(BigDecimal.valueOf(1), MathContext.DECIMAL64);
            BigDecimal resultrate = ratex;
            //(1+季度利率）^还款季度数
            for (int i = 0; i < (repayPeriod - 1); i++) {
                resultrate = resultrate.multiply(ratex, MathContext.DECIMAL64);
            }
            //贷款本金×季度利率×（1+利率）^还款季度数
            BigDecimal upmoney = totalLoan.multiply(quarterlyRate).multiply(resultrate);
            //（1+季度利率）^还款季度数－1
            BigDecimal downmoney = resultrate.subtract(BigDecimal.valueOf(1), MathContext.DECIMAL64);
            currentsubtotal = upmoney.divide(downmoney, MathContext.DECIMAL64);//每季度还款总额
            Calendar calender = Calendar.getInstance();
            Date repayDate = startDate;
            for (int i = 0; i < repayPeriod; i++) {
                RepayItem item = new RepayItem();
                calender.setTime(repayDate);
                calender.add(Calendar.MONTH, 3);//一个季度三个月
                repayDate = calender.getTime();
                item.setRepayDate(repayDate);//设置还款时间节点
                currentinterest = currentbalance.multiply(quarterlyRate, MathContext.DECIMAL64);//计算利息
                item.setInterest(currentinterest);
                currentprincipal = currentsubtotal.subtract(currentinterest, MathContext.DECIMAL64);//计算所还本金
                item.setPrincipal(currentprincipal);
                item.setBeforeBalance(currentbalance);//计算期初余额
                currentbalance = currentbalance.subtract(currentprincipal, MathContext.DECIMAL64);
                item.setAfterBalance(currentbalance);//计算期末余额
                item.setSubtotal(currentsubtotal);//每月还款总额
                item.setPeriodNum(i + 1);
                repayItemList.add(item);
            }
        } else if (splitUnit == SplitUnit.月) {
            BigDecimal currentprincipal;//本金
            BigDecimal currentinterest;//利息
            BigDecimal currentsubtotal;//小计
            BigDecimal currentbalance = totalLoan;//贷款余额
            //计算月利率
            BigDecimal monthRate = yearRate.divide(BigDecimal.valueOf(12), MathContext.DECIMAL64);
            //1+利率
            BigDecimal ratex = monthRate.add(BigDecimal.valueOf(1), MathContext.DECIMAL64);
            BigDecimal resultrate = ratex;
            //(1+月利率）^还款月数
            for (int i = 0; i < (repayPeriod - 1); i++) {
                resultrate = resultrate.multiply(ratex, MathContext.DECIMAL64);
            }
            //贷款本金×月利率×（1+利率）^还款月数
            BigDecimal upmoney = totalLoan.multiply(monthRate).multiply(resultrate);
            //（1+月利率）^还款月数－1
            BigDecimal downmoney = resultrate.subtract(BigDecimal.valueOf(1), MathContext.DECIMAL64);
            currentsubtotal = upmoney.divide(downmoney, MathContext.DECIMAL64);//每月还款总额
            Calendar calender = Calendar.getInstance();
            Date repayDate = startDate;
            for (int i = 0; i < repayPeriod; i++) {
                RepayItem item = new RepayItem();
                calender.setTime(repayDate);
                calender.add(Calendar.MONTH, 1);//加一个月
                repayDate = calender.getTime();
                item.setRepayDate(repayDate);//设置还款时间节点
                currentinterest = currentbalance.multiply(monthRate, MathContext.DECIMAL64);//计算利息
                item.setInterest(currentinterest);
                currentprincipal = currentsubtotal.subtract(currentinterest, MathContext.DECIMAL64);//计算所还本金
                item.setPrincipal(currentprincipal);
                item.setBeforeBalance(currentbalance);//计算期初余额
                currentbalance = currentbalance.subtract(currentprincipal, MathContext.DECIMAL64);
                item.setAfterBalance(currentbalance);//计算期末余额
                item.setSubtotal(currentsubtotal);//每月还款总额
                item.setPeriodNum(i + 1);
                repayItemList.add(item);
            }
        } else if (splitUnit == SplitUnit.日) {

            BigDecimal currentprincipal;//本金
            BigDecimal currentinterest;//利息
            BigDecimal currentsubtotal;//小计
            BigDecimal currentbalance = totalLoan;//贷款余额
            //计算天利率
            BigDecimal dayRate = yearRate.divide(BigDecimal.valueOf(365), MathContext.DECIMAL64);//一年365天
            //1+利率
            BigDecimal ratex = dayRate.add(BigDecimal.valueOf(1), MathContext.DECIMAL64);
            BigDecimal resultrate = ratex;
            //(1+天利率）^还款月数
            for (int i = 0; i < (repayPeriod - 1); i++) {
                resultrate = resultrate.multiply(ratex, MathContext.DECIMAL64);
            }
            //贷款本金×天利率×（1+利率）^还款天数
            BigDecimal upmoney = totalLoan.multiply(dayRate).multiply(resultrate);
            //（1+天利率）^还款天数－1
            BigDecimal downmoney = resultrate.subtract(BigDecimal.valueOf(1), MathContext.DECIMAL64);
            currentsubtotal = upmoney.divide(downmoney, MathContext.DECIMAL64);//每天还款总额
            Calendar calender = Calendar.getInstance();
            Date repayDate = startDate;
            for (int i = 0; i < repayPeriod; i++) {
                RepayItem item = new RepayItem();
                calender.setTime(repayDate);
                calender.add(Calendar.DATE, 1);//加一天
                repayDate = calender.getTime();
                item.setRepayDate(repayDate);//设置还款时间节点
                currentinterest = currentbalance.multiply(dayRate, MathContext.DECIMAL64);//计算利息
                item.setInterest(currentinterest);
                currentprincipal = currentsubtotal.subtract(currentinterest, MathContext.DECIMAL64);//计算所还本金
                item.setPrincipal(currentprincipal);
                item.setBeforeBalance(currentbalance);//计算期初余额
                currentbalance = currentbalance.subtract(currentprincipal, MathContext.DECIMAL64);
                item.setAfterBalance(currentbalance);//计算期末余额
                item.setSubtotal(currentsubtotal);//每天还款总额
                item.setPeriodNum(i + 1);
                repayItemList.add(item);
            }
        }
    }

    //定额本金
    private static void caculateRepayModel_b(SplitUnit splitUnit, BigDecimal totalLoan, BigDecimal yearRate, int repayPeriod, Date startDate, List<RepayItem> repayItemList) {
        BigDecimal currentprincipal;//本金
        BigDecimal currentinterest;//利息
        BigDecimal currentsubtotal;//小计
        BigDecimal currentbalance = totalLoan;//贷款余额
        //每月应还本金：a/n
        //每月应还利息：an*i/30*dn
        //注：a贷款本金 i贷款月利率 n贷款月数 an第n个月贷款剩余本金
        currentprincipal = currentbalance.divide(BigDecimal.valueOf(repayPeriod), MathContext.DECIMAL64);//每个还款周期应还本金
        if (splitUnit == SplitUnit.月) {
            for (int i = 0; i < repayPeriod; i++) {
                RepayItem item = new RepayItem();
                //得到一个还款月的总天数
                GregorianCalendar gCalendar = new GregorianCalendar();
                gCalendar.setTime(startDate);
                gCalendar.add(Calendar.MONTH, 1);//加一月
                Date endDate = gCalendar.getTime();
                long allDayNum = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);//总天数
                //计算月利率
                BigDecimal monthRate = yearRate.divide(BigDecimal.valueOf(12), MathContext.DECIMAL64);
                currentinterest = currentbalance.multiply(monthRate, MathContext.DECIMAL64).divide(BigDecimal.valueOf(30), MathContext.DECIMAL64).multiply(BigDecimal.valueOf(allDayNum), MathContext.DECIMAL64);//得到每个月应还利息
                currentsubtotal = currentprincipal.add(currentinterest);//一个还款月的总还款数
                item.setBeforeBalance(currentbalance);//期前余额
                currentbalance = currentbalance.subtract(currentprincipal);
                item.setAfterBalance(currentbalance);//期末余额
                item.setPrincipal(currentprincipal);//设置本金
                item.setInterest(currentinterest);//设置利息
                item.setSubtotal(currentsubtotal);//设置小计
                item.setPeriodNum(i + 1);//设置期数
                item.setRepayDate(endDate);//设置时间
                startDate = endDate;
                repayItemList.add(item);
            }
        } else if (splitUnit == SplitUnit.年) {
            for (int i = 0; i < repayPeriod; i++) {
                RepayItem item = new RepayItem();
                //得到一个还款月的总天数
                GregorianCalendar gCalendar = new GregorianCalendar();
                gCalendar.setTime(startDate);
                gCalendar.add(Calendar.YEAR, 1);//加一年
                Date endDate = gCalendar.getTime();
                long allDayNum = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);//总天数
                //计算月利率
                currentinterest = currentbalance.multiply(yearRate, MathContext.DECIMAL64).divide(BigDecimal.valueOf(30), MathContext.DECIMAL64).multiply(BigDecimal.valueOf(allDayNum), MathContext.DECIMAL64);//得到每个月应还利息
                currentsubtotal = currentprincipal.add(currentinterest);//一个还款月的总还款数
                item.setBeforeBalance(currentbalance);//期前余额
                currentbalance = currentbalance.subtract(currentprincipal);
                item.setAfterBalance(currentbalance);//期末余额
                item.setPrincipal(currentprincipal);//设置本金
                item.setInterest(currentinterest);//设置利息
                item.setSubtotal(currentsubtotal);//设置小计
                item.setPeriodNum(i + 1);//设置期数
                item.setRepayDate(endDate);//设置时间
                startDate = endDate;
                repayItemList.add(item);
            }
        } else if (splitUnit == SplitUnit.季度) {
            for (int i = 0; i < repayPeriod; i++) {
                RepayItem item = new RepayItem();
                //得到一个还款季度的总天数
                GregorianCalendar gCalendar = new GregorianCalendar();
                gCalendar.setTime(startDate);
                gCalendar.add(Calendar.MONTH, 3);//加三个月
                Date endDate = gCalendar.getTime();
                long allDayNum = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);//总天数
                //计算季度利率
                BigDecimal quarterlyRate = yearRate.divide(BigDecimal.valueOf(4), MathContext.DECIMAL64);
                currentinterest = currentbalance.multiply(quarterlyRate, MathContext.DECIMAL64).divide(BigDecimal.valueOf(30), MathContext.DECIMAL64).multiply(BigDecimal.valueOf(allDayNum), MathContext.DECIMAL64);//得到每个月应还利息
                currentsubtotal = currentprincipal.add(currentinterest);//一个还款季度的总还款数
                item.setBeforeBalance(currentbalance);//期前余额
                currentbalance = currentbalance.subtract(currentprincipal);
                item.setAfterBalance(currentbalance);//期末余额
                item.setPrincipal(currentprincipal);//设置本金
                item.setInterest(currentinterest);//设置利息
                item.setSubtotal(currentsubtotal);//设置小计
                item.setPeriodNum(i + 1);//设置期数
                item.setRepayDate(endDate);//设置时间
                startDate = endDate;
                repayItemList.add(item);
            }
        } else if (splitUnit == SplitUnit.日) {
            for (int i = 0; i < repayPeriod; i++) {
                RepayItem item = new RepayItem();
                //得到一个还款的总天数
                GregorianCalendar gCalendar = new GregorianCalendar();
                gCalendar.setTime(startDate);
                gCalendar.add(Calendar.DATE, 1);//加一天
                Date endDate = gCalendar.getTime();
                long allDayNum = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);//总天数
                //计算天利率
                BigDecimal quarterlyRate = yearRate.divide(BigDecimal.valueOf(365), MathContext.DECIMAL64);
                currentinterest = currentbalance.multiply(quarterlyRate, MathContext.DECIMAL64).divide(BigDecimal.valueOf(30), MathContext.DECIMAL64).multiply(BigDecimal.valueOf(allDayNum), MathContext.DECIMAL64);//得到每个月应还利息
                currentsubtotal = currentprincipal.add(currentinterest);//一个还款周期的总还款数
                item.setBeforeBalance(currentbalance);//期前余额
                currentbalance = currentbalance.subtract(currentprincipal);
                item.setAfterBalance(currentbalance);//期末余额
                item.setPrincipal(currentprincipal);//设置本金
                item.setInterest(currentinterest);//设置利息
                item.setSubtotal(currentsubtotal);//设置小计
                item.setPeriodNum(i + 1);//设置期数
                item.setRepayDate(endDate);//设置时间
                startDate = endDate;
                repayItemList.add(item);
            }
        }
    }

    //到期支付
    private static void caculateRepayModel_c(SplitUnit splitUnit, BigDecimal totalLoan, BigDecimal yearRate, int repayPeriod, Date startDate, List<RepayItem> repayItemList) {
        //根据开始时间、还款周期、还款期数计算一共多少天，根据年利率得到日利率，从而得到一共需要支付的利息
        Date endDate = null;
        if (splitUnit == SplitUnit.年) {
            GregorianCalendar gCalendar = new GregorianCalendar();
            gCalendar.setTime(startDate);
            gCalendar.add(Calendar.YEAR, repayPeriod);//加指定的年数
            endDate = gCalendar.getTime();
            RepayItem item = new RepayItem();
            item.setRepayDate(endDate);
            item.setBeforeBalance(totalLoan);
            item.setAfterBalance(BigDecimal.ZERO);
            item.setPrincipal(totalLoan);
            BigDecimal interest = totalLoan.multiply(yearRate).multiply(BigDecimal.valueOf(repayPeriod));//利息
            item.setInterest(interest);
            item.setSubtotal(totalLoan.add(interest));
            item.setPeriodNum(1);
            repayItemList.add(item);
        } else if (splitUnit == SplitUnit.季度) {
            GregorianCalendar gCalendar = new GregorianCalendar();
            gCalendar.setTime(startDate);
            gCalendar.add(Calendar.MONTH, repayPeriod * 3);//加指定的月数
            endDate = gCalendar.getTime();
            RepayItem item = new RepayItem();
            item.setRepayDate(endDate);
            item.setBeforeBalance(totalLoan);
            item.setAfterBalance(BigDecimal.ZERO);
            item.setPrincipal(totalLoan);
            //计算季度利率
            BigDecimal quarterlyRate = yearRate.divide(BigDecimal.valueOf(4), MathContext.DECIMAL64);
            BigDecimal interest = totalLoan.multiply(quarterlyRate).multiply(BigDecimal.valueOf(repayPeriod));//利息
            item.setInterest(interest);
            item.setSubtotal(totalLoan.add(interest));
            item.setPeriodNum(1);
            repayItemList.add(item);

        } else if (splitUnit == SplitUnit.月) {
            GregorianCalendar gCalendar = new GregorianCalendar();
            gCalendar.setTime(startDate);
            gCalendar.add(Calendar.MONTH, repayPeriod);//加指定的月
            endDate = gCalendar.getTime();
            RepayItem item = new RepayItem();
            item.setRepayDate(endDate);
            item.setBeforeBalance(totalLoan);
            item.setAfterBalance(BigDecimal.ZERO);
            item.setPrincipal(totalLoan);
            //计算季度利率
            BigDecimal monthRate = yearRate.divide(BigDecimal.valueOf(12), MathContext.DECIMAL64);
            BigDecimal interest = totalLoan.multiply(monthRate).multiply(BigDecimal.valueOf(repayPeriod));//利息
            item.setInterest(interest);
            item.setSubtotal(totalLoan.add(interest));
            item.setPeriodNum(1);
            repayItemList.add(item);

        } else if (splitUnit == SplitUnit.日) {
            GregorianCalendar gCalendar = new GregorianCalendar();
            gCalendar.setTime(startDate);
            gCalendar.add(Calendar.DATE, repayPeriod);//加指定的月
            endDate = gCalendar.getTime();
            RepayItem item = new RepayItem();
            item.setRepayDate(endDate);
            item.setBeforeBalance(totalLoan);
            item.setAfterBalance(BigDecimal.ZERO);
            item.setPrincipal(totalLoan);
            //计算季度利率
            BigDecimal dayRate = yearRate.divide(BigDecimal.valueOf(365), MathContext.DECIMAL64);
            BigDecimal interest = totalLoan.multiply(dayRate).multiply(BigDecimal.valueOf(repayPeriod));//利息
            item.setInterest(interest);
            item.setSubtotal(totalLoan.add(interest));
            item.setPeriodNum(1);
            repayItemList.add(item);

        }
    }
}
