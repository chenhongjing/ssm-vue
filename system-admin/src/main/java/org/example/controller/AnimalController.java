package org.example.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.Constants;
import org.example.entity.Animal;
import org.example.service.AnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.05.25
 */
@RestController
@Slf4j
public class AnimalController {

    @Resource
    private AnimalService animalService;

    @PostMapping("animal")
    public ResponseEntity<Boolean> addAnimal(@RequestBody Animal animal){
        if(animal != null){
            if(animalService.addAnimal(animal)){
                log.info("insert animal succeed!");
                return ResponseEntity.ok().build();
            }
            else{
                log.info("fail to insert animal.");
                return ResponseEntity.status(500).build();
            }
        }
        else {
            log.info("[error]animal is null.");
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("animal")
    public ResponseEntity<PageInfo> getAnimals(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value="query", required = false) String query){
        PageHelper.startPage(pageNum, pageSize);
        List<Animal> records = animalService.getAllAnimals(query);
        PageInfo page = new PageInfo(records, Constants.NAVIGATE_PAGES);
        log.info("get animal records");
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping("animal/{id}")
    public ResponseEntity<Boolean> deleteAnimal(@PathVariable Integer id){
        return ResponseEntity.ok().body(animalService.deleteAnimal(id));
    }

    @PutMapping("animal/{id}")
    public ResponseEntity<Boolean> editAnimal(@PathVariable Integer id, @RequestBody Animal animal){
       if(animal != null){
           if(animalService.editAnimal(id, animal)){
               log.info("update animal succeed!");
               return ResponseEntity.ok().build();
           }
       }
       log.info("[error]animal is null");
       return ResponseEntity.status(500).build();
    }
}
