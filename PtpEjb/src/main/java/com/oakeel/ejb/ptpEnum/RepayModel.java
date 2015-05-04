package com.oakeel.ejb.ptpEnum;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author root
 */
public enum RepayModel {
    定额本息("每月还相同的数额，作为贷款人，操作相对简单。每月承担相同的款项也方便安排收支。","由于利息不会随本金数额归还而减少，银行资金占用时间长，还款总利息较以下要介绍的等额本金还款法高。","等额本息每月的还款额度相同，所以比较适宜有正常开支计划的家庭，特别是年青人，经济条件不允许前期投入过大，可以选择这种方式，如公务员、教师等收入和工作机会相对稳定的群体。","等额本息还款法是指在还款期内，每月偿还同等数额的贷款(包括本金和利息)"),
    定额本金("总体算下来利息低，后期轻松","前期利息高负担重","这种方式较适合于已经有一定的积蓄，但预期收入可能逐渐减少的借款人，如中老年职工家庭，其现有一定的积蓄，但今后随着退休临近收入将递减。","定额本金还款法是在还款期内按期等额归还贷款本金，并同时还清当期未归还的本金所产生的利息。"),
    到期支付("借款时间长，资金周转期长","所需支付的利息最高","需要资金周转时间长，投资收益比较高的行业","到期一次性支付本金利息"),
    每期还息到期还本("借款时间长，资金周转期长","所需支付的利息最高","需要资金周转时间长，投资收益比较高的行业","到期一次性支付本金利息"),
    天天宝宝("借款时间长，资金周转期长","所需支付的利息最高","需要资金周转时间长，投资收益比较高的行业","到期一次性支付本金利息");
    private String description;
    private String good;
    private String bad;
    private String suitable;
    private RepayModel(String good,String bad,String suitable,String description)
    {
        this.description=description;
        this.good=good;
        this.bad=bad;
        this.suitable=suitable;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the good
     */
    public String getGood() {
        return good;
    }

    /**
     * @param good the good to set
     */
    public void setGood(String good) {
        this.good = good;
    }

    /**
     * @return the bad
     */
    public String getBad() {
        return bad;
    }

    /**
     * @param bad the bad to set
     */
    public void setBad(String bad) {
        this.bad = bad;
    }

    /**
     * @return the suitable
     */
    public String getSuitable() {
        return suitable;
    }

    /**
     * @param suitable the suitable to set
     */
    public void setSuitable(String suitable) {
        this.suitable = suitable;
    }
}
