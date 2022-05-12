package org.example.service.impl;

import org.example.dao.DynamicParameterDao;
import org.example.dao.OrganDao;
import org.example.dao.OtherParameterDao;
import org.example.dao.SubstituteDao;
import org.example.entity.DynamicParameter;
import org.example.entity.PieChartData;
import org.example.service.PieChartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.05.12
 */
@Service("PieChartService")
public class PieChartServiceImpl implements PieChartService {

    @Resource
    private DynamicParameterDao dynamicParameterDao;

    @Resource
    private OtherParameterDao otherParameterDao;

    @Resource
    private OrganDao organDao;

    @Resource
    private SubstituteDao substituteDao;

    @Override
    public List<PieChartData> getPieChartData() {
        List<PieChartData> pieChartData = new ArrayList<>();
        pieChartData.add(PieChartData.builder().name("dynamic param").value(dynamicParameterDao.countByExample(null)).build());
        pieChartData.add(PieChartData.builder().name("other param").value(otherParameterDao.countByExample(null)).build());
        pieChartData.add(PieChartData.builder().name("organ").value(organDao.countByExample(null)).build());
        pieChartData.add(PieChartData.builder().name("substitute").value(substituteDao.countByExample(null)).build());
        pieChartData.add(PieChartData.builder().name("new substitute").value(substituteDao.countByExample(null)).build());
        return pieChartData;
    }
}
