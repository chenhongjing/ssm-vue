package org.example.service;

import org.example.entity.Organ;

import java.util.List;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.05.26
 */
public interface OrganService {
    Boolean addOrgan(Organ organ);

    List<Organ> getAllOrgans(String query);

    Boolean deleteOrgan(Integer id);

    Boolean editOrgan(Integer id, Organ organ);
}
