package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.annotation.Log;
import org.example.dao.AnimalDao;
import org.example.entity.Animal;
import org.example.entity.AnimalExample;
import org.example.service.AnimalService;
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
@Service("AnimalService")
public class AnimalServiceImpl implements AnimalService {

    @Resource
    private AnimalDao animalDao;

    @Override
    @Log(title = "添加动物信息", logType = "添加")
    public Boolean addAnimal(Animal animal) {
        String username = RequestUtil.getUsername();
        Timestamp timestamp = TimeUtil.getTimeStamp();
        animal.setUsername(username);
        animal.setUpdatedTime(timestamp);

        animalDao.insertSelective(animal);
        return true;
    }

    @Override
    public List<Animal> getAllAnimals(String query) {
        String username = RequestUtil.getUsername();
        List<Animal> records = null;
        AnimalExample animalEx = new AnimalExample();
        if(query != null && !query.isEmpty()){
            animalEx.or().andAnimalNameLike("%" + query + "%").andUsernameEqualTo(username);
            animalEx.or().andSciNameLike("%" + query + "%").andUsernameEqualTo(username);
            animalEx.or().andInfoLike("%" + query + "%").andUsernameEqualTo(username);
        }
        else{
            animalEx.createCriteria().andUsernameEqualTo(username);
        }
        records = animalDao.selectByExample(animalEx);
        return records;
    }

    @Override
    @Log(title = "删除动物信息", logType = "删除")
    public Boolean deleteAnimal(Integer id) {
        return animalDao.deleteByPrimaryKey(id) > 0;
    }

    @Override
    @Log(title = "修改动物信息", logType = "修改")
    public Boolean editAnimal(Integer id, Animal animal) {
        Timestamp timestamp = TimeUtil.getTimeStamp();
        animal.setUpdatedTime(timestamp);
        animalDao.updateByPrimaryKeySelective(animal);
        return true;
    }
}
