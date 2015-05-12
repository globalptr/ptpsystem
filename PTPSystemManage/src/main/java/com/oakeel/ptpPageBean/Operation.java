/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ptpPageBean;

import com.oakeel.ejb.entityAndEao.operation.OperationEaoLocal;
import com.oakeel.ejb.entityAndEao.operation.OperationEntity;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author root
 */
@ManagedBean
@ViewScoped
public class Operation {

    @EJB
    OperationEaoLocal operationEaoLocal;
    /**
     * Creates a new instance of Operation
     */
    private List<OperationEntity> allOperations;//所有操作列表
    private List<OperationEntity> operationFilter;//filter
    private OperationEntity delOperation;//准备删除的操作
    private OperationEntity newOperation=new OperationEntity();//新建的操作
    private OperationEntity selectOperation;
    public Operation() {
    }
    @PostConstruct
    public void init()
    {
        allOperations=operationEaoLocal.getAllOperation();
    }
    public void addNewOperation()
    {
        operationEaoLocal.addNewOperation(newOperation);
        allOperations.add(newOperation);
        if(operationFilter!=null)
            operationFilter.add(newOperation);
        newOperation=new OperationEntity();
    }
    public void deleteOperation()
    {
        if(delOperation!=null)
        {
            operationEaoLocal.deleteOperation(delOperation);
            allOperations.remove(delOperation);
            if(operationFilter!=null)
                operationFilter.remove(delOperation);
        }
    }
    public void updateOperation()
    {
        if(selectOperation!=null)
            operationEaoLocal.updateOperation(selectOperation);
    }
    /**
     * @return the allOperations
     */
    public List<OperationEntity> getAllOperations() {
        return allOperations;
    }

    /**
     * @param allOperations the allOperations to set
     */
    public void setAllOperations(List<OperationEntity> allOperations) {
        this.allOperations = allOperations;
    }

    /**
     * @return the operationFilter
     */
    public List<OperationEntity> getOperationFilter() {
        return operationFilter;
    }

    /**
     * @param operationFilter the operationFilter to set
     */
    public void setOperationFilter(List<OperationEntity> operationFilter) {
        this.operationFilter = operationFilter;
    }

    /**
     * @return the delOperation
     */
    public OperationEntity getDelOperation() {
        return delOperation;
    }

    /**
     * @param delOperation the delOperation to set
     */
    public void setDelOperation(OperationEntity delOperation) {
        this.delOperation = delOperation;
    }

    /**
     * @return the newOperation
     */
    public OperationEntity getNewOperation() {
        return newOperation;
    }

    /**
     * @param newOperation the newOperation to set
     */
    public void setNewOperation(OperationEntity newOperation) {
        this.newOperation = newOperation;
    }

    /**
     * @return the selectOperation
     */
    public OperationEntity getSelectOperation() {
        return selectOperation;
    }

    /**
     * @param selectOperation the selectOperation to set
     */
    public void setSelectOperation(OperationEntity selectOperation) {
        this.selectOperation = selectOperation;
    }
    
}
