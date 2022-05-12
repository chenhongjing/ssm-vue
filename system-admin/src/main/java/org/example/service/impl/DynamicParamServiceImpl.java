package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.constant.Constants;
import org.example.dao.DynamicParameterDao;
import org.example.dao.SubstituteDao;
import org.example.dao.UserDao;
import org.example.entity.*;
import org.example.service.DynamicParamService;
import org.example.utils.RequestUtil;
import org.example.utils.TimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
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
    public Boolean addDynamicParamRecord(DynamicParameter dynamicParam) {
        String username = RequestUtil.getUsername();

//        UserExample userEx = new UserExample();
//        userEx.createCriteria().andUsernameEqualTo(username);
//        List<User> user = userDao.selectByExample(userEx);

//        SubstituteExample substituteEx = new SubstituteExample();
//        substituteEx.createCriteria().andMaterialNameEqualTo(dynamicParam.getMaterialName());
//        List<Substitute> substitute = substituteDao.selectByExample(substituteEx);
//
//        if(substitute.size() == 1){
//            dynamicParam.setUserName(username);
//            dynamicParam.setMaterialName(substitute.get(0).getMaterialId());
//            // 增加时间戳
//            Date javaDate = new Date();
//            java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
//            dynamicParam.setUpdatedTime(sqlDate);
//            dynamicParamDao.insert(dynamicParam);
//        }
//        else{
//            log.info("error to save dynamic params: cannot find valid material");
//            return false;
//        }

        // 增加时间戳
        Timestamp timeStamp = TimeUtil.getTimeStamp();

        // 如果动物组织器官在数据库中找不到，自动增加
        SubstituteExample substituteEx = new SubstituteExample();
        substituteEx.createCriteria().andMaterialNameEqualTo(dynamicParam.getMaterialName()).andUserNameEqualTo(username);
        List<Substitute> substitutes = substituteDao.selectByExample(substituteEx);
        if(substitutes.isEmpty()){
            Substitute substitute = new Substitute();
            substitute.setUserName(username);
            substitute.setMaterialName(dynamicParam.getMaterialName());
            substitute.setUpdatedTime(timeStamp);
            substituteDao.insertSelective(substitute);
        }

        dynamicParam.setUserName(username);

        dynamicParam.setUpdatedTime(timeStamp);
        dynamicParamDao.insertSelective(dynamicParam);
        return true;
    }

    @Override
    public List<DynamicParameter> getAllRecords(String query) {
        String username = RequestUtil.getUsername();
//        List<User> user = getUser(username);
//        if(user.isEmpty()){
//            log.info("[error]cannot find user!");
//            return null;
//        }
//        else if(user.size() > 1){
//            log.info("[error]duplicate user");
//        }

        List<DynamicParameter> records = null;
        DynamicParameterExample dynamicParamEx = new DynamicParameterExample();
        if(query!= null && !query.isEmpty()){
            // select * from dynamic_organ where username == 'xxx' and (material_name like '%query%' or info like '%query%')
            dynamicParamEx.or().andMaterialNameLike("%"+query+"%").andUserNameEqualTo(username);
            dynamicParamEx.or().andInfoLike("%" + query + "%").andUserNameEqualTo(username);
        }
        else{
            dynamicParamEx.createCriteria().andUserNameEqualTo(username);
        }
        records = dynamicParamDao.selectByExampleWithBLOBs(dynamicParamEx);
//        for(DynamicParameter r: records){
//            System.out.println(r.getParamData());
//        }
        return records;
    }

    @Override
    public Boolean deleteDynamicParamRecord(Integer id) {
        return dynamicParamDao.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public Boolean editDynamicParamRecord(Integer id, DynamicParameter dynamicParam) {
        // 更新时间戳
        Timestamp timeStamp = TimeUtil.getTimeStamp();
        dynamicParam.setUpdatedTime(timeStamp);
        DynamicParameterExample ex = new DynamicParameterExample();
        ex.createCriteria().andParamIdEqualTo(id);
        dynamicParamDao.updateByExampleWithBLOBs(dynamicParam, ex);
        return true;
    }

    private List<User> getUser(String username){
        UserExample userEx = new UserExample();
        userEx.createCriteria().andUsernameEqualTo(username);
        return userDao.selectByExample(userEx);
    }
}
