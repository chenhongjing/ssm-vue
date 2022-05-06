package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.constant.Constants;
import org.example.dao.DynamicParameterDao;
import org.example.dao.SubstituteDao;
import org.example.dao.UserDao;
import org.example.entity.*;
import org.example.service.DynamicParamService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.05.04
 */
@Slf4j
@Service("DynamicParamService")
public class DynamicParamServiceImpl implements DynamicParamService {

    @Resource
    private DynamicParameterDao dynamicParamDao;

    @Resource
    private UserDao userDao;

    @Resource
    private SubstituteDao substituteDao;

    @Override
    public void addDynamicParamRecord(DynamicParameter dynamicParam) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        String username = request.getHeader(Constants.HEAD_USERNAME);

        UserExample userEx = new UserExample();
        userEx.createCriteria().andUsernameEqualTo(username);
        List<User> user = userDao.selectByExample(userEx);

        SubstituteExample substituteEx = new SubstituteExample();
        substituteEx.createCriteria().andMaterialNameEqualTo(dynamicParam.getMaterialName());
        List<Substitute> substitute = substituteDao.selectByExample(substituteEx);

        if(user.size() == 1 && substitute.size() == 1){
            dynamicParam.setUserId(user.get(0).getUserId());
            dynamicParam.setMaterialId(substitute.get(0).getMaterialId());
            // 增加时间戳
            Date javaDate = new Date();
            java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
            dynamicParam.setUpdatedTime(sqlDate);
            dynamicParamDao.insert(dynamicParam);
        }
        else{
            log.info("error to save dynamic params: cannot find valid user/material");
        }
    }

    @Override
    public List<DynamicParameter> getAllRecords(String query) {
        List<DynamicParameter> records = null;
        if(query!= null && !query.isEmpty()){
            // select * from dynamic_organ where material_name like '%query%' or info like '%query%'
            DynamicParameterExample dynamicParamEx = new DynamicParameterExample();
            dynamicParamEx.or().andMaterialNameLike("%"+query+"%");
            dynamicParamEx.or().andInfoLike("%" + query + "%");
            records = dynamicParamDao.selectByExampleWithBLOBs(dynamicParamEx);
        }
        else{
            records = dynamicParamDao.selectByExampleWithBLOBs(null);
        }
        for(DynamicParameter r: records){
            System.out.println(r.getParamData());
        }
        return records;
    }

    @Override
    public Boolean deleteDynamicParamRecord(Integer id) {
        return dynamicParamDao.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public Boolean editDynamicParamRecord(Integer id, DynamicParameter dynamicParam) {
        // 更新时间戳
        Date javaDate = new Date();
        java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
        dynamicParam.setUpdatedTime(sqlDate);
        DynamicParameterExample ex = new DynamicParameterExample();
        ex.createCriteria().andParamIdEqualTo(id);
        dynamicParamDao.updateByExampleWithBLOBs(dynamicParam, ex);
        return true;
    }
}
