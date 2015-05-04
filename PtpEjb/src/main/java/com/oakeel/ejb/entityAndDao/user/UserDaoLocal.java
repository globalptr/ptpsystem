/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndDao.user;

import com.oakeel.ejb.entityAndDao.organization.OrganizationEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface UserDaoLocal {
  
    public void addUser(UserEntity user);
    public Boolean  validateUser(UserEntity user);
    public List<UserEntity> getAllUser();
    public void updateUser(UserEntity user);
    public List<UserEntity> getUsersByOrganization(OrganizationEntity org);
    public  List<UserEntity> getUnclassedUser();
    public void deleteUser(UserEntity user);
    
}
