package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.dao.OrganDao;
import org.example.entity.Organ;
import org.example.entity.OrganExample;
import org.example.service.OrganService;
import org.example.utils.RequestUtil;
import org.example.utils.TimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.05.26
 */
@Slf4j
@Service("OrganService")
public class OrganServiceImpl implements OrganService {

    @Resource
    private OrganDao organDao;

    @Override
    public Boolean addOrgan(Organ organ) {
        String username = RequestUtil.getUsername();
        Timestamp timestamp = TimeUtil.getTimeStamp();
        organ.setUserName(username);
        organ.setUpdatedTime(timestamp);
        organDao.insertSelective(organ);
        return true;
    }

    @Override
    public List<Organ> getAllOrgans(String query) {
        String username = RequestUtil.getUsername();
        List<Organ> records = null;
        OrganExample organEx = new OrganExample();
        if(query != null && !query.isEmpty()){
            organEx.or().andOrganNameLike("%" + query + "%").andUserNameEqualTo(username);
            organEx.or().andInfoLike("%" + query + "%").andUserNameEqualTo(username);
        }
        else{
            organEx.createCriteria().andUserNameEqualTo(username);
        }
        records = organDao.selectByExample(organEx);
        return records;
    }

    @Override
    public Boolean deleteOrgan(Integer id) {
        return organDao.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public Boolean editOrgan(Integer id, Organ organ) {
        Timestamp timestamp = TimeUtil.getTimeStamp();
        organ.setUpdatedTime(timestamp);
        organDao.updateByPrimaryKeySelective(organ);
        return true;
    }
}
