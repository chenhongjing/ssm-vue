package org.example.service.impl;

import org.example.dao.*;
import org.example.entity.*;
import org.example.service.PieChartService;
import org.example.utils.RequestUtil;
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

    static final Boolean HUMAN = Boolean.FALSE;

    static final Boolean ANIMAL = Boolean.TRUE;

    @Resource
    private DynamicParameterDao dynamicParameterDao;

    @Resource
    private OtherParameterDao otherParameterDao;

    @Resource
    private OrganDao organDao;

    @Resource
    private SubstituteDao substituteDao;

    @Resource
    private AnimalDao animalDao;

    @Override
    public List<PieChartData> getPieChartData() {

        String username = RequestUtil.getUsername();

        List<PieChartData> pieChartData = new ArrayList<>();

        OrganExample organEx = new OrganExample();
        organEx.createCriteria().andUserNameEqualTo(username);

        DynamicParameterExample organDyEx = new DynamicParameterExample();
        organDyEx.createCriteria().andCategoryEqualTo(HUMAN).andUsernameEqualTo(username);

        AnimalExample animalEx = new AnimalExample();
        animalEx.createCriteria().andUsernameEqualTo(username);

        SubstituteExample substituteEx = new SubstituteExample();
        substituteEx.createCriteria().andUserNameEqualTo(username);

        DynamicParameterExample substituteDyEx = new DynamicParameterExample();
        substituteDyEx.createCriteria().andCategoryEqualTo(ANIMAL).andUsernameEqualTo(username);

        pieChartData.add(PieChartData.builder().name("人体组织器官").value(organDao.countByExample(organEx)).build());
        pieChartData.add(PieChartData.builder().name("人体组织器官动态参数").value(dynamicParameterDao.countByExample(organDyEx)).build());
        pieChartData.add(PieChartData.builder().name("动物基本信息").value(animalDao.countByExample(animalEx)).build());
        pieChartData.add(PieChartData.builder().name("动物组织器官").value(substituteDao.countByExample(substituteEx)).build());
        pieChartData.add(PieChartData.builder().name("动物组织器官动态参数").value(dynamicParameterDao.countByExample(substituteDyEx)).build());

        return pieChartData;
    }
}
