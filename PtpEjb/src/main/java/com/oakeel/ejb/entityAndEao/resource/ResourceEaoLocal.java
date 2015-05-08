/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndEao.resource;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface ResourceEaoLocal {
    public List<ResourceEntity> getAllResource();
    public void addNewResource(ResourceEntity resource);
    public void updateResource(ResourceEntity resource);
    public void deleteReource(ResourceEntity resource);
}
