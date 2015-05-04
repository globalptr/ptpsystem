/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel;

import com.oakeel.ejb.ptpEnum.RepayModel;
import com.oakeel.ejb.ptpEnum.SplitUnit;
import com.oakeel.globaltool.RepayModelCaculate;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped
public class RepayModelBean {
    private List<RepayModel> repayModelList;//还款模型枚举列表
    private RepayModel selectRepayModel;//选择的还款模型
    private List<SplitUnit> splitUnit;//还款单元（年月日）
    private SplitUnit selectSplitUnit;//选择的还款单元
    private List<RepayItem> repayItemList;//计算得出还款的清单
    //贷款总额 年利率 还款周期 还款期数 开始时间 结束时间
    private BigDecimal totalloan;//贷款总额
    private BigDecimal yearRate;//年利率
    private int repayPeriod;//还款周期
    private Date startDate;//开始时间
    private Date endDate;//结束时间
    @PostConstruct
    public void init()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request;
        request = ( HttpServletRequest ) context.getExternalContext().getRequest();
        String rootPath = request.getRequestURI();
        repayItemList=new ArrayList<>();
        setSplitUnit(Arrays.asList(SplitUnit.values()));
        setRepayModelList(Arrays.asList(RepayModel.values()));
    }
    /**
     * @return the splitUnit
     */
    public List<SplitUnit> getSplitUnit() {
        return splitUnit;
    }

    /**
     * @param splitUnit the splitUnit to set
     */
    public void setSplitUnit(List<SplitUnit> splitUnit) {
        this.splitUnit = splitUnit;
    }

    /**
     * @return the selectSplitUnit
     */
    public SplitUnit getSelectSplitUnit() {
        return selectSplitUnit;
    }

    /**
     * @param selectSplitUnit the selectSplitUnit to set
     */
    public void setSelectSplitUnit(SplitUnit selectSplitUnit) {
        this.selectSplitUnit = selectSplitUnit;
    }
    public void openBids()
    {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentHeight", 350);
        RequestContext.getCurrentInstance().openDialog("index", options, null);
    }
    public void closeBids()
    {
        RequestContext.getCurrentInstance().closeDialog("bids");
    }
    public void caculateModel()
    {
        //根据输入和选择的还款模型计算还款列表
        try
        {
            repayItemList.clear();
            //计算还款模型输入还款模型、还款单元、贷款总额、年利率、还款周期、开始时间
            RepayModelCaculate.caculateRepayModel(selectRepayModel, selectSplitUnit, getTotalloan(), yearRate.multiply(BigDecimal.valueOf(0.01),MathContext.DECIMAL32), repayPeriod, startDate, repayItemList);
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
    }
    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }
    /**
     * @return the repayItemList
     */
    public List<RepayItem> getRepayItemList() {
        return repayItemList;
    }

    /**
     * @param repayItemList the repayItemList to set
     */
    public void setRepayItemList(List<RepayItem> repayItemList) {
        this.repayItemList = repayItemList;
    }

    /**
     * @return the selectRepayModel
     */
    public RepayModel getSelectRepayModel() {
        return selectRepayModel;
    }

    /**
     * @param selectRepayModel the selectRepayModel to set
     */
    public void setSelectRepayModel(RepayModel selectRepayModel) {
        this.selectRepayModel = selectRepayModel;
    }


    /**
     * @return the yearRate
     */
    public BigDecimal getYearRate() {
        return yearRate;
    }

    /**
     * @param yearRate the yearRate to set
     */
    public void setYearRate(BigDecimal yearRate) {
        this.yearRate = yearRate;
    }

    /**
     * @return the repayPeriod
     */
    public int getRepayPeriod() {
        return repayPeriod;
    }

    /**
     * @param repayPeriod the repayPeriod to set
     */
    public void setRepayPeriod(int repayPeriod) {
        this.repayPeriod = repayPeriod;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the totalloan
     */
    public BigDecimal getTotalloan() {
        return totalloan;
    }

    /**
     * @param totalloan the totalloan to set
     */
    public void setTotalloan(BigDecimal totalloan) {
        this.totalloan = totalloan;
    }
        
    /**
     * @return the repayModelList
     */
    public List<RepayModel> getRepayModelList() {
        return repayModelList;
    }

    /**
     * @param repayModelList the repayModelList to set
     */
    public void setRepayModelList(List<RepayModel> repayModelList) {
        this.repayModelList = repayModelList;
    }
}
