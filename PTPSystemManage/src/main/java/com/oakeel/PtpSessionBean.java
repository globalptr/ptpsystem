/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.TreeNode;

/**
 *
 * @author root
 */
@ManagedBean
@SessionScoped
public class PtpSessionBean implements Serializable{

    //对于一个登录用户的session数据，统一使用此bean
    /**
     * Creates a new instance of PtpSessionBean
     */
    public PtpSessionBean() {
    }
    
    private TreeNode organizationSelectNode;//组织页面组织树选择的节点

    /**
     * @return the organizationSelectNode
     */
    public TreeNode getOrganizationSelectNode() {
        return organizationSelectNode;
    }

    /**
     * @param organizationSelectNode the organizationSelectNode to set
     */
    public void setOrganizationSelectNode(TreeNode organizationSelectNode) {
        this.organizationSelectNode = organizationSelectNode;
    }

}
