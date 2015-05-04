/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndDao.resource;

import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface ResourceDaoLocal {
  
    public void addNewResource(ResourceEntity resource);
}
