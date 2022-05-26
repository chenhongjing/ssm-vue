package org.example.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.Constants;
import org.example.entity.Organ;
import org.example.service.OrganService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.05.26
 */
@RestController
@Slf4j
public class OrganController {

    @Resource
    private OrganService organService;

    @PostMapping("organ")
    public ResponseEntity<Boolean> addOrgan(@RequestBody Organ organ){
        if(organ != null){
            if(organService.addOrgan(organ)){
                log.info("insert organ succeed!");
                return ResponseEntity.ok().build();
            }
            else{
                log.info("fail to insert organ.");
                return ResponseEntity.status(500).build();
            }
        }
        else{
            log.info("[error]organ is null.");
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("organ")
    public ResponseEntity<PageInfo> getOrgans(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                              @RequestParam(value="query", required = false) String query){
        PageHelper.startPage(pageNum, pageSize);
        List<Organ> records = organService.getAllOrgans(query);
        PageInfo page = new PageInfo(records, Constants.NAVIGATE_PAGES);
        log.info("get organ records");
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping("organ/{id}")
    public ResponseEntity<Boolean> deleteOrgan(@PathVariable Integer id){
        return ResponseEntity.ok().body(organService.deleteOrgan(id));
    }

    @PutMapping("organ/{id}")
    public ResponseEntity<Boolean> editOrgan(@PathVariable Integer id, @RequestBody Organ organ){
        if(organ != null){
            if(organService.editOrgan(id, organ)){
                log.info("update organ succeed!");
                return ResponseEntity.ok().build();
            }
        }
        log.info("[error]organ is null.");
        return ResponseEntity.status(500).build();
    }
}
