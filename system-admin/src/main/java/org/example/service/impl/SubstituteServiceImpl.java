package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.annotation.Log;
import org.example.dao.AnimalDao;
import org.example.dao.SubstituteDao;
import org.example.entity.Animal;
import org.example.entity.AnimalExample;
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

    @Resource
    private AnimalDao animalDao;

    @Override
    @Log(title = "添加动物组织器官信息", logType = "添加")
    public Boolean addSubstituteRecord(Substitute substitute) {
        String username = RequestUtil.getUsername();
        Timestamp timeStamp = TimeUtil.getTimeStamp();
        substitute.setUpdatedTime(timeStamp);
        substitute.setUserName(username);
        // 先检查属于该用户的动物信息存不存在，若不存在，则先插入一个动物数据项再插入动物组织器官数据
        String animalName = substitute.getAnimalName();
        if(animalName != null){
            AnimalExample animalEx = new AnimalExample();
            animalEx.createCriteria().andAnimalNameEqualTo(animalName).andUsernameEqualTo(username);
            if(animalDao.countByExample(animalEx) == 0){ // 不存在动物
                Animal animal = new Animal();
                animal.setAnimalName(animalName);
                animal.setUsername(username);
                animal.setUpdatedTime(timeStamp);
                animalDao.insertSelective(animal);
            }
        }
        substituteDao.insertSelective(substitute);
        return true;
    }

    @Override
    public List<Substitute> getAllRecords(String query) {
        String username = RequestUtil.getUsername();
        List<Substitute> records = null;
        SubstituteExample substituteEx = new SubstituteExample();
        if(query != null && !query.isEmpty()){
            substituteEx.or().andSubstituteNameLike("%"+query+"%").andUserNameEqualTo(username);
            substituteEx.or().andInfoLike("%" + query + "%").andUserNameEqualTo(username);
            substituteEx.or().andAnimalNameLike("%" + query + "%").andUserNameEqualTo(username);
        }
        else{
            substituteEx.createCriteria().andUserNameEqualTo(username);
        }
        records = substituteDao.selectByExample(substituteEx);
        return records;
    }

    @Override
    @Log(title = "删除动物组织器官信息", logType = "删除")
    public Boolean deleteSubstituteRecord(Integer id) {
        return substituteDao.deleteByPrimaryKey(id) > 0;
    }

    @Override
    @Log(title = "编辑动物组织器官信息", logType = "编辑")
    public Boolean editSubstituteRecord(Integer id, Substitute substitute) {
        Timestamp timeStamp = TimeUtil.getTimeStamp();

        String username = RequestUtil.getUsername();
        // 先检查属于该用户的动物信息存不存在，若不存在，则先插入一个动物数据项再插入动物组织器官数据
        String animalName = substitute.getAnimalName();
        if(animalName != null){
            AnimalExample animalEx = new AnimalExample();
            animalEx.createCriteria().andAnimalNameEqualTo(animalName).andUsernameEqualTo(username);
            if(animalDao.countByExample(animalEx) == 0){ // 不存在动物
                Animal animal = new Animal();
                animal.setAnimalName(animalName);
                animal.setUsername(username);
                animal.setUpdatedTime(timeStamp);
                animalDao.insertSelective(animal);
            }
        }

        substitute.setUpdatedTime(timeStamp);
        SubstituteExample ex = new SubstituteExample();
        ex.createCriteria().andSubstituteIdEqualTo(id);
        substituteDao.updateByExampleSelective(substitute, ex);
        return true;
    }
}
