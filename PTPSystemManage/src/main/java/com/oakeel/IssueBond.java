/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author root
 */
@ManagedBean
@ViewScoped
public class IssueBond {

    private int bondIndex=0;
    /**
     * Creates a new instance of IssueBond
     */
    public IssueBond() {
    }
    public void stepBack()
    {
        if((bondIndex-1)>-1)
            bondIndex--;
    }
    public void stepFront()
    {
        if((bondIndex+1)<4)
            bondIndex++;
    }
    /**
     * @return the bondIndex
     */
    public int getBondIndex() {
        return bondIndex;
    }

    /**
     * @param bondIndex the bondIndex to set
     */
    public void setBondIndex(int bondIndex) {
        this.bondIndex = bondIndex;
    }
    
}
