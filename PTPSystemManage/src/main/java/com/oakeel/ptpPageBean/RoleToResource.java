/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oakeel.ptpPageBean;

import com.oakeel.ejb.entityAndEao.resource.ResourceEaoLocal;
import com.oakeel.ejb.entityAndEao.resource.ResourceEntity;
import com.oakeel.ejb.entityAndEao.resource.ResourceTypeEnum;
import com.oakeel.ejb.entityAndEao.role.RoleEaoLocal;
import com.oakeel.ejb.entityAndEao.role.RoleEntity;
import com.oakeel.ejb.entityAndEao.user.UserEaoLocal;
import com.oakeel.ejb.ptpEnum.SysInfo;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author root
 */
@ManagedBean
@ViewScoped
public class RoleToResource {

    /**
     * Creates a new instance of RoleToResource
     */
    public RoleToResource() {
    }
    @EJB
    UserEaoLocal userEaoLocal;
    @EJB
    RoleEaoLocal roleEaoLocal;
    @EJB
    ResourceEaoLocal resourceEaoLocal;
    private List<RoleEntity> roleEntitys;//角色
    private List<RoleEntity> roleFilter;//角色筛选
    private RoleEntity selectRole;//选择的角色
    private RoleEntity newRole = new RoleEntity();
    private List<ResourceEntity> resourceEntitys;
    private List<ResourceEntity> resourceFilter;
    private Set<ResourceEntity> roleResources;
    private ResourceEntity newResource = new ResourceEntity();//新建的资源
    private ResourceTypeEnum[] resourceTypeEnums;
    private ResourceEntity deleteRoleResource;
    private String message;
    private ResourceEntity delResource;

    @PostConstruct
    public void init() {
        setResourceTypeEnums(ResourceTypeEnum.values());
        roleEntitys = roleEaoLocal.getAllRole();
        resourceEntitys = resourceEaoLocal.getAllResource();
        int i = 0;
    }
    //增加角色
    public void addRole()
    {
        roleEaoLocal.addNewRole(newRole);
        roleEntitys.add(newRole);
        newRole=new RoleEntity();
    }
    //列出所有角色
    public void viewAllRole()
    {
        roleEntitys=roleEaoLocal.getAllRole();
        roleFilter=roleEaoLocal.getAllRole();
    }
    //浏览角色资源
    public String viewRoleResource() {
        if (selectRole != null) {
            roleResources = selectRole.getResourceEntity();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(SysInfo.提示.toString(), "选择角色：" + selectRole.getName()));
        }
        return null;
    }
    //添加资源
    public void addResource() {
        resourceEaoLocal.addNewResource(newResource);
        resourceEntitys.add(newResource);
        newResource = new ResourceEntity();
    }
    //查看所有的资源
    public void viewAllResource()
    {
        resourceFilter=resourceEaoLocal.getAllResource();
        resourceEntitys=resourceEaoLocal.getAllResource();
    }
    
    //一键赋值
    public void oneClickEva()
    {
        //如果filter为空，则取resourceEntitys的值，如果不为空，则取filter的值
        if(selectRole!=null)
        {
            if(resourceFilter!=null)
            {
                for(ResourceEntity item:resourceFilter)
                {
                    selectRole.getResourceEntity().add(item);
                    roleResources.add(item);
                }
            }
            else
            {
                for(ResourceEntity item:resourceEntitys)
                {
                    selectRole.getResourceEntity().add(item);
                    roleResources.add(item);
                }
            }
        }
    }
    //删除资源
    public String deleteResource() {
        if(delResource!=null)
        {
            resourceEaoLocal.deleteReource(delResource);
            resourceEntitys.remove(delResource);
        }
        return null;
    }
    //删除角色资源
    public String deleteRoleResource() {
        roleEaoLocal.deleteRoleResource(selectRole, deleteRoleResource);
        roleResources.remove(deleteRoleResource);
        return null;
    }
    //拖放赋值
    public void onResourceDrop(DragDropEvent event) {
        ResourceEntity role = ((ResourceEntity) event.getData());
        if (selectRole != null) {
            try {
                selectRole.getResourceEntity().add(role);
                roleEaoLocal.updateRole(selectRole);
            } catch (Exception ex) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(SysInfo.错误.toString(), "资源已存在，不能重复加入"));
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(SysInfo.错误.toString(), "没有选择操作的角色"));
        }
    }
    //删除角色
    public void deleteRole() {
        if (selectRole != null) {
            roleEaoLocal.deleteRole(selectRole);
            roleEntitys.remove(selectRole);
        }
    }
    //更新角色
    public void updateRole(RowEditEvent event) {
        RoleEntity temp = (RoleEntity) event.getObject();
        roleEaoLocal.updateRole(temp);
    }
    //更新资源
    public void updateResource(RowEditEvent event) {
        ResourceEntity temp = (ResourceEntity) event.getObject();
        resourceEaoLocal.updateResource(temp);
    }

    /**
     * @return the roleEntitys
     */
    public List<RoleEntity> getRoleEntitys() {
        return roleEntitys;
    }

    /**
     * @param roleEntitys the roleEntitys to set
     */
    public void setRoleEntitys(List<RoleEntity> roleEntitys) {
        this.roleEntitys = roleEntitys;
    }

    /**
     * @return the roleFilter
     */
    public List<RoleEntity> getRoleFilter() {
        return roleFilter;
    }

    /**
     * @param roleFilter the roleFilter to set
     */
    public void setRoleFilter(List<RoleEntity> roleFilter) {
        this.roleFilter = roleFilter;
    }

    /**
     * @return the selectRole
     */
    public RoleEntity getSelectRole() {
        return selectRole;
    }

    /**
     * @param selectRole the selectRole to set
     */
    public void setSelectRole(RoleEntity selectRole) {
        this.selectRole = selectRole;
    }

    /**
     * @return the resourceEntitys
     */
    public List<ResourceEntity> getResourceEntitys() {
        return resourceEntitys;
    }

    /**
     * @param resourceEntitys the resourceEntitys to set
     */
    public void setResourceEntitys(List<ResourceEntity> resourceEntitys) {
        this.resourceEntitys = resourceEntitys;
    }

    /**
     * @return the roleResources
     */
    public Set<ResourceEntity> getRoleResources() {
        return roleResources;
    }

    /**
     * @param roleResources the roleResources to set
     */
    public void setRoleResources(Set<ResourceEntity> roleResources) {
        this.roleResources = roleResources;
    }

    /**
     * @return the newResource
     */
    public ResourceEntity getNewResource() {
        return newResource;
    }

    /**
     * @param newResource the newResource to set
     */
    public void setNewResource(ResourceEntity newResource) {
        this.newResource = newResource;
    }



    /**
     * @return the resourceTypeEnums
     */
    public ResourceTypeEnum[] getResourceTypeEnums() {
        return resourceTypeEnums;
    }

    /**
     * @param resourceTypeEnums the resourceTypeEnums to set
     */
    public void setResourceTypeEnums(ResourceTypeEnum[] resourceTypeEnums) {
        this.resourceTypeEnums = resourceTypeEnums;
    }

    /**
     * @return the deleteRoleResource
     */
    public ResourceEntity getDeleteRoleResource() {
        return deleteRoleResource;
    }

    /**
     * @param deleteRoleResource the deleteRoleResource to set
     */
    public void setDeleteRoleResource(ResourceEntity deleteRoleResource) {
        this.deleteRoleResource = deleteRoleResource;
    }

    /**
     * @return the resourceFilter
     */
    public List<ResourceEntity> getResourceFilter() {
        return resourceFilter;
    }

    /**
     * @param resourceFilter the resourceFilter to set
     */
    public void setResourceFilter(List<ResourceEntity> resourceFilter) {
        this.resourceFilter = resourceFilter;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the newRole
     */
    public RoleEntity getNewRole() {
        return newRole;
    }

    /**
     * @param newRole the newRole to set
     */
    public void setNewRole(RoleEntity newRole) {
        this.newRole = newRole;
    }

    /**
     * @return the delResource
     */
    public ResourceEntity getDelResource() {
        return delResource;
    }

    /**
     * @param delResource the delResource to set
     */
    public void setDelResource(ResourceEntity delResource) {
        this.delResource = delResource;
    }


}
