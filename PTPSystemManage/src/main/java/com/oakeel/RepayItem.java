/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author root
 */
public class RepayItem {
    private Date repayDate;//还款时间节点
    private BigDecimal principal;//本金大数据类型
    private BigDecimal interest;//利息
    private BigDecimal subtotal;//小计
    private BigDecimal additional;//额外还款金额 这个字段在做预算的时候没用，在做实际还款的时候有用
    private BigDecimal beforeBalance;//期初余额
    private BigDecimal afterBalance;//期末余额
    private int periodNum;//还款期数

    /**
     * @return the principal
     */
    public BigDecimal getPrincipal() {
        return principal;
    }

    /**
     * @param principal the principal to set
     */
    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    /**
     * @return the interest
     */
    public BigDecimal getInterest() {
        return interest;
    }

    /**
     * @param interest the interest to set
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    /**
     * @return the subtotal
     */
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

 

    /**
     * @return the repayDate
     */
    public Date getRepayDate() {
        return repayDate;
    }

    /**
     * @param repayDate the repayDate to set
     */
    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
    }

    /**
     * @return the periodNum
     */
    public int getPeriodNum() {
        return periodNum;
    }

    /**
     * @param periodNum the periodNum to set
     */
    public void setPeriodNum(int periodNum) {
        this.periodNum = periodNum;
    }

    /**
     * @return the beforeBalance
     */
    public BigDecimal getBeforeBalance() {
        return beforeBalance;
    }

    /**
     * @param beforeBalance the beforeBalance to set
     */
    public void setBeforeBalance(BigDecimal beforeBalance) {
        this.beforeBalance = beforeBalance;
    }

    /**
     * @return the additional
     */
    public BigDecimal getAdditional() {
        return additional;
    }

    /**
     * @param additional the additional to set
     */
    public void setAdditional(BigDecimal additional) {
        this.additional = additional;
    }

    /**
     * @return the afterBalance
     */
    public BigDecimal getAfterBalance() {
        return afterBalance;
    }

    /**
     * @param afterBalance the afterBalance to set
     */
    public void setAfterBalance(BigDecimal afterBalance) {
        this.afterBalance = afterBalance;
    }
}
