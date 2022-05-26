package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
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
    public Boolean deleteAnimal(Integer id) {
        return animalDao.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public Boolean editAnimal(Integer id, Animal animal) {
        Timestamp timestamp = TimeUtil.getTimeStamp();
        animal.setUpdatedTime(timestamp);
        animalDao.updateByPrimaryKeySelective(animal);
        return true;
    }
}
