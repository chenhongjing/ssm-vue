package org.example.service;

import org.example.entity.Substitute;

import java.util.List;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.05.12
 */
public interface SubstituteService {
    Boolean addSubstituteRecord(Substitute substitute);

    List<Substitute> getAllRecords(String query);

    Boolean deleteSubstituteRecord(Integer id);

    Boolean editSubstituteRecord(Integer id, Substitute substitute);
}
