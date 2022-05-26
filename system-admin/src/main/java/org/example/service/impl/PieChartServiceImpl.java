package org.example.service.impl;

import org.example.constant.Constants;
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

    static final String HUMAN_ORGAN = "人体组织器官";

    static final String HUMAN_DYNAMIC_PARAM = "人体组织器官动态参数";

    static final String ANIMAL_INFO = "动物基本信息";

    static final String ANIMAL_ORGAN = "动物组织器官";

    static final String ANIMAL_DYNAMIC_PARAM = "动物组织器官动态参数";

    @Resource
    private DynamicParameterDao dynamicParameterDao;

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
        organDyEx.createCriteria().andCategoryEqualTo(Constants.HUMAN).andUsernameEqualTo(username);

        AnimalExample animalEx = new AnimalExample();
        animalEx.createCriteria().andUsernameEqualTo(username);

        SubstituteExample substituteEx = new SubstituteExample();
        substituteEx.createCriteria().andUserNameEqualTo(username);

        DynamicParameterExample substituteDyEx = new DynamicParameterExample();
        substituteDyEx.createCriteria().andCategoryEqualTo(Constants.ANIMAL).andUsernameEqualTo(username);

        pieChartData.add(PieChartData.builder().name(HUMAN_ORGAN).value(organDao.countByExample(organEx)).build());
        pieChartData.add(PieChartData.builder().name(HUMAN_DYNAMIC_PARAM).value(dynamicParameterDao.countByExample(organDyEx)).build());
        pieChartData.add(PieChartData.builder().name(ANIMAL_INFO).value(animalDao.countByExample(animalEx)).build());
        pieChartData.add(PieChartData.builder().name(ANIMAL_ORGAN).value(substituteDao.countByExample(substituteEx)).build());
        pieChartData.add(PieChartData.builder().name(ANIMAL_DYNAMIC_PARAM).value(dynamicParameterDao.countByExample(substituteDyEx)).build());

        return pieChartData;
    }
}
