package org.example.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.Constants;
import org.example.entity.DynamicParameter;
import org.example.entity.Substitute;
import org.example.service.SubstituteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.05.02
 */
@RestController
@Slf4j
public class SubstituteController {

    @Resource
    private SubstituteService substituteService;

    @PostMapping("substitute")
    public ResponseEntity<Boolean> addSubstituteRecord(@RequestBody Substitute substitute){
        if(substitute != null){
            if(substituteService.addSubstituteRecord(substitute)){
                log.info("insert substitute succeed!");
                return ResponseEntity.ok().build();
            }
            else{
                log.info("fail to insert substitute.");
                return ResponseEntity.status(500).build();
            }
        }
        else{
            log.info("[error]substitute is null");
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("substitute")
    public ResponseEntity<PageInfo> getSubstituteRecords(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                       @RequestParam(value="query", required = false) String query){
        PageHelper.startPage(pageNum, pageSize);
        // 紧跟的查询会被分页
        List<Substitute> records = substituteService.getAllRecords(query);
        PageInfo page = new PageInfo(records, Constants.NAVIGATE_PAGES);
        log.info("get substitute records");
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping("substitute/{id}")
    public ResponseEntity<Boolean> deleteSubstituteRecord(@PathVariable Integer id){
        return ResponseEntity.ok().body(substituteService.deleteSubstituteRecord(id));
    }

    @PutMapping("substitute/{id}")
    public ResponseEntity<Boolean> editSubstituteRecord(@PathVariable Integer id, @RequestBody Substitute substitute){
        if(substitute != null){
            if(substituteService.editSubstituteRecord(id, substitute)){
                log.info("update substitute succeed!");
                return ResponseEntity.ok().build();
            }
        }
        log.info("[error] substitute is null");
        return ResponseEntity.status(500).build();
    }
}
