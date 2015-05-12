/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndEao.operation;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface OperationEaoLocal {
    public void addNewOperation(OperationEntity operation);
    public void deleteOperation(OperationEntity operation);
    public void updateOperation(OperationEntity operation);
    public List<OperationEntity> getAllOperation();
}
