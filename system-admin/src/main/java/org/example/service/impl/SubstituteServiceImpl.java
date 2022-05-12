package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.dao.SubstituteDao;
import org.example.entity.Substitute;
import org.example.entity.SubstituteExample;
import org.example.service.SubstituteService;
import org.example.utils.RequestUtil;
import org.example.utils.TimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.05.12
 */
@Slf4j
@Service("SubstituteService")
public class SubstituteServiceImpl implements SubstituteService {

    @Resource
    private SubstituteDao substituteDao;

    @Override
    public Boolean addSubstituteRecord(Substitute substitute) {
        String username = RequestUtil.getUsername();
        Timestamp timeStamp = TimeUtil.getTimeStamp();
        substitute.setUpdatedTime(timeStamp);
        substitute.setUserName(username);
        substituteDao.insertSelective(substitute);
        return true;
    }

    @Override
    public List<Substitute> getAllRecords(String query) {
        String username = RequestUtil.getUsername();
        List<Substitute> records = null;
        SubstituteExample substituteEx = new SubstituteExample();
        if(query != null && !query.isEmpty()){
            substituteEx.or().andMaterialNameLike("%"+query+"%").andUserNameEqualTo(username);
            substituteEx.or().andInfoLike("%" + query + "%").andUserNameEqualTo(username);
        }
        else{
            substituteEx.createCriteria().andUserNameEqualTo(username);
        }
        records = substituteDao.selectByExample(substituteEx);
        return records;
    }

    @Override
    public Boolean deleteSubstituteRecord(Integer id) {
        return substituteDao.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public Boolean editSubstituteRecord(Integer id, Substitute substitute) {
        Timestamp timeStamp = TimeUtil.getTimeStamp();
        substitute.setUpdatedTime(timeStamp);
        SubstituteExample ex = new SubstituteExample();
        ex.createCriteria().andMaterialIdEqualTo(id);
        substituteDao.updateByExampleSelective(substitute, ex);
        return true;
    }
}
