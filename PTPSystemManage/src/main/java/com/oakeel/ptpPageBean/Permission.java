/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oakeel.ptpPageBean;

import com.oakeel.ejb.entityAndEao.operation.OperationEaoLocal;
import com.oakeel.ejb.entityAndEao.operation.OperationEntity;
import com.oakeel.ejb.entityAndEao.permission.PermissionEaoLocal;
import com.oakeel.ejb.entityAndEao.permission.PermissionEntity;
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
public class Permission {

    /**
     * Creates a new instance of RoleToResource
     */
    public Permission() {
    }
    @EJB
    UserEaoLocal userEaoLocal;
    @EJB
    RoleEaoLocal roleEaoLocal;
    @EJB
    ResourceEaoLocal resourceEaoLocal;
    @EJB
    PermissionEaoLocal permissionEaoLocal;
    @EJB
    OperationEaoLocal operationEaoLocal;
    private List<RoleEntity> roleEntitys;//角色
    private List<RoleEntity> roleFilter;//角色筛选
    private RoleEntity selectRole;//选择的角色
    private RoleEntity deleteRole;
    private RoleEntity newRole = new RoleEntity();
    private List<ResourceEntity> resourceEntitys;
    private List<ResourceEntity> resourceFilter;
    private Set<PermissionEntity> permissions;//角色资源
    private ResourceEntity newResource = new ResourceEntity();//新建的资源
    private ResourceTypeEnum[] resourceTypeEnums;
    private PermissionEntity targetPermission;//准备删除的角色资源
    private ResourceEntity delResource;
    private List<OperationEntity> opereations;
    private PermissionEntity selectPermission;
    private List<OperationEntity> selectOperations;
    private OperationEntity deleteOperation;
    
    @PostConstruct
    public void init() {
        setResourceTypeEnums(ResourceTypeEnum.values());
        roleEntitys = roleEaoLocal.getAllRole();
        resourceEntitys = resourceEaoLocal.getAllResource();
        setOpereations(operationEaoLocal.getAllOperation());
        
    }
    //为角色的权限添加操作
    public void addPermissionOperation()
    {
        for(OperationEntity item:selectOperations)
        {
            selectPermission.getOperationEntitys().add(item);
        }
        permissionEaoLocal.updatePermission(selectPermission);
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
            setPermissions(selectRole.getPermissions());
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
                    PermissionEntity permission=new PermissionEntity(selectRole,item);
                    permissionEaoLocal.addPermission(permission);
                }
            }
            else
            {
                for(ResourceEntity item:resourceEntitys)
                {
                    PermissionEntity permission=new PermissionEntity(selectRole,item);
                    permissionEaoLocal.addPermission(permission);
                }
            }
        }
    }
    //删除资源
    public String deleteResource() {
        if(getDelResource()!=null)
        {
            resourceEaoLocal.deleteReource(getDelResource());
            resourceEntitys.remove(getDelResource());
        }
        return null;
    }
    //删除角色资源
    public String deletePermission() {
        permissionEaoLocal.deletePermission(targetPermission);//权限与角色的关系中，权限是关系的维护者，删除权限即可
        permissions.remove(targetPermission);
        return null;
    }
    //拖放赋值
    public void onResourceDrop(DragDropEvent event) {
        ResourceEntity role = ((ResourceEntity) event.getData());
        if (selectRole != null) {
            PermissionEntity permission=new PermissionEntity(selectRole,role);
            permissionEaoLocal.addPermission(permission);
            permissions.add(permission);
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(SysInfo.错误.toString(), "没有选择操作的角色"));
        }
    }
    //删除角色
    public void deleteRole() {
        
        //如果delete和select相同，则清空
        if (deleteRole != null) {
            roleEaoLocal.deleteRole(deleteRole);
            roleEntitys.remove(deleteRole);
            //如果删除的和选择的不同，这个时候清空roleResources,会导致选择的角色的角色资源不出现，具体原因未知
            if(selectRole!=null)
            {
                if(selectRole.getRoleUuid().equals(deleteRole.getRoleUuid()))
                {
                    permissions.clear();
                }
            }
        }
    }
    //删除权限的操作
    public void deletePermissionOperation()
    {
        selectPermission.getOperationEntitys().remove(deleteOperation);
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

    /**
     * @return the deleteRole
     */
    public RoleEntity getDeleteRole() {
        return deleteRole;
    }

    /**
     * @param deleteRole the deleteRole to set
     */
    public void setDeleteRole(RoleEntity deleteRole) {
        this.deleteRole = deleteRole;
    }

    /**
     * @return the permissions
     */
    public Set<PermissionEntity> getPermissions() {
        return permissions;
    }

    /**
     * @param permissions the permissions to set
     */
    public void setPermissions(Set<PermissionEntity> permissions) {
        this.permissions = permissions;
    }

    /**
     * @return the targetPermission
     */
    public PermissionEntity getTargetPermission() {
        return targetPermission;
    }

    /**
     * @param targetPermission the targetPermission to set
     */
    public void setTargetPermission(PermissionEntity targetPermission) {
        this.targetPermission = targetPermission;
    }

    /**
     * @return the opereations
     */
    public List<OperationEntity> getOpereations() {
        return opereations;
    }

    /**
     * @param opereations the opereations to set
     */
    public void setOpereations(List<OperationEntity> opereations) {
        this.opereations = opereations;
    }

    /**
     * @return the selectPermission
     */
    public PermissionEntity getSelectPermission() {
        return selectPermission;
    }

    /**
     * @param selectPermission the selectPermission to set
     */
    public void setSelectPermission(PermissionEntity selectPermission) {
        this.selectPermission = selectPermission;
    }

    /**
     * @return the selectOperations
     */
    public List<OperationEntity> getSelectOperations() {
        return selectOperations;
    }

    /**
     * @param selectOperations the selectOperations to set
     */
    public void setSelectOperations(List<OperationEntity> selectOperations) {
        this.selectOperations = selectOperations;
    }

    /**
     * @return the deleteOperation
     */
    public OperationEntity getDeleteOperation() {
        return deleteOperation;
    }

    /**
     * @param deleteOperation the deleteOperation to set
     */
    public void setDeleteOperation(OperationEntity deleteOperation) {
        this.deleteOperation = deleteOperation;
    }

}
