package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.annotation.Log;
import org.example.constant.Constants;
import org.example.dao.DynamicParameterDao;
import org.example.dao.OrganDao;
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
    private OrganDao organDao;

    @Resource
    private SubstituteDao substituteDao;

    @Override
    @Log(title = "添加动态参数", logType = "添加")
    public Boolean addDynamicParamRecord(Boolean category, DynamicParameter dynamicParam) {
        String username = RequestUtil.getUsername();
        // 增加时间戳
        Timestamp timeStamp = TimeUtil.getTimeStamp();

        // 先检查人体/动物的组织器官是否已经存在属于该用户的记录，若不存在，则先插入数据
        String materialName = dynamicParam.getMaterialName();
        if(materialName != null){
            if(category == Constants.HUMAN){
                OrganExample organEx = new OrganExample();
                organEx.createCriteria().andUserNameEqualTo(username).andOrganNameEqualTo(materialName);
                if(organDao.countByExample(organEx) == 0){
                    Organ organ = new Organ();
                    organ.setOrganName(materialName);
                    organ.setUserName(username);
                    organ.setUpdatedTime(timeStamp);
                    organDao.insertSelective(organ);
                }
            }
            else{
                SubstituteExample substituteEx = new SubstituteExample();
                substituteEx.createCriteria().andUserNameEqualTo(username).andSubstituteNameEqualTo(materialName);
                if(substituteDao.countByExample(substituteEx) == 0){
                    Substitute substitute = new Substitute();
                    substitute.setSubstituteName(materialName);
                    substitute.setUserName(username);
                    substitute.setUpdatedTime(timeStamp);
                    substituteDao.insertSelective(substitute);
                }
            }
        }

        dynamicParam.setUsername(username);
        dynamicParam.setCategory(category);
        dynamicParam.setUpdatedTime(timeStamp);
        dynamicParamDao.insertSelective(dynamicParam);
        return true;
    }

    @Override
    public List<DynamicParameter> getAllRecords(Boolean category, String query) {
        String username = RequestUtil.getUsername();

        List<DynamicParameter> records = null;
        DynamicParameterExample dynamicParamEx = new DynamicParameterExample();
        if(query!= null && !query.isEmpty()){
            // select * from dynamic_organ where username == 'xxx' and (material_name like '%query%' or info like '%query%')
            dynamicParamEx.or().andMaterialNameLike("%"+query+"%").andUsernameEqualTo(username).andCategoryEqualTo(category);
            dynamicParamEx.or().andInfoLike("%" + query + "%").andUsernameEqualTo(username).andCategoryEqualTo(category);
        }
        else{
            dynamicParamEx.createCriteria().andUsernameEqualTo(username).andCategoryEqualTo(category);
        }
        records = dynamicParamDao.selectByExampleWithBLOBs(dynamicParamEx);
        return records;
    }

    @Override
    @Log(title = "删除动态参数", logType = "删除")
    public Boolean deleteDynamicParamRecord(Integer id) {
        return dynamicParamDao.deleteByPrimaryKey(id) > 0;
    }

    @Override
    @Log(title = "修改动态参数", logType = "修改")
    public Boolean editDynamicParamRecord(Integer id, DynamicParameter dynamicParam) {
        // 更新时间戳
        Timestamp timeStamp = TimeUtil.getTimeStamp();

        // 先检查人体/动物的组织器官是否已经存在属于该用户的记录，若不存在，则先插入数据
        String materialName = dynamicParam.getMaterialName();
        Boolean category = dynamicParam.getCategory();
        String username = RequestUtil.getUsername();
        if(materialName != null){
            if(category == Constants.HUMAN){
                OrganExample organEx = new OrganExample();
                organEx.createCriteria().andUserNameEqualTo(username).andOrganNameEqualTo(materialName);
                if(organDao.countByExample(organEx) == 0){
                    Organ organ = new Organ();
                    organ.setOrganName(materialName);
                    organ.setUserName(username);
                    organ.setUpdatedTime(timeStamp);
                    organDao.insertSelective(organ);
                }
            }
            else{
                SubstituteExample substituteEx = new SubstituteExample();
                substituteEx.createCriteria().andUserNameEqualTo(username).andSubstituteNameEqualTo(materialName);
                if(substituteDao.countByExample(substituteEx) == 0){
                    Substitute substitute = new Substitute();
                    substitute.setSubstituteName(materialName);
                    substitute.setUserName(username);
                    substitute.setUpdatedTime(timeStamp);
                    substituteDao.insertSelective(substitute);
                }
            }
        }

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
