package org.example.service;

import org.example.entity.DynamicParameter;

import java.util.List;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.05.04
 */
public interface DynamicParamService {
    Boolean addDynamicParamRecord(Boolean category, DynamicParameter dynamicParam);

    List<DynamicParameter> getAllRecords(Boolean category, String query);

    Boolean deleteDynamicParamRecord(Integer id);

    Boolean editDynamicParamRecord(Integer id, DynamicParameter dynamicParam);
}
