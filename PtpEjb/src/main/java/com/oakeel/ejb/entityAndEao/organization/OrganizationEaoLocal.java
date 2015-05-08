/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel.ejb.entityAndEao.organization;

import com.oakeel.ejb.entityAndEao.resource.ResourceEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface OrganizationEaoLocal {
    public OrganizationEntity AddNewOrganization(OrganizationEntity organization,OrganizationEntity parent);
    public List<OrganizationEntity> getAllOrganization();
    public OrganizationEntity getRoot();
    public List<OrganizationEntity> getOrganizationEntityByName(String name);
    public void deleteOrganization(OrganizationEntity org);
    public OrganizationEntity getOrganizationByUuid(String uuid);
    public void updateOrganizationEntity(OrganizationEntity org);
}
