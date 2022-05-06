package org.example.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.example.constant.Constants;
import org.example.entity.DynamicParameter;
import org.example.service.DynamicParamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.05.03
 */
@RestController
@Slf4j
public class DynamicParameterController {

    @Resource
    private DynamicParamService dynamicParamService;

    @PostMapping("dynamic_param")
    public ResponseEntity<Boolean> addDynamicParamRecord(@RequestBody DynamicParameter dynamicParam){
        if(dynamicParam != null){
            dynamicParamService.addDynamicParamRecord(dynamicParam);
            log.info("insert dynamic params succeed!");
            return ResponseEntity.ok().build();
        }
        else{
            log.info("error: dynamic params is null");
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("dynamic_param")
    public ResponseEntity<PageInfo> getDynamicParamRecords(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                           @RequestParam(value="query", required = false) String query){
        PageHelper.startPage(pageNum, pageSize);
        // 紧跟的查询会被分页
        List<DynamicParameter> records = dynamicParamService.getAllRecords(query);
        PageInfo page = new PageInfo(records, Constants.NAVIGATE_PAGES);
        log.info("get dynamic param records");
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping("dynamic_param/{id}")
    public ResponseEntity<Boolean> deleteDynamicParamRecord(@PathVariable Integer id){
        return ResponseEntity.ok().body(dynamicParamService.deleteDynamicParamRecord(id));
    }

    @PutMapping("dynamic_param/{id}")
    public ResponseEntity<Boolean> editDynamicParamRecord(@PathVariable Integer id, @RequestBody DynamicParameter dynamicParam){
        if(dynamicParam != null) {
            if (dynamicParamService.editDynamicParamRecord(id, dynamicParam)) {
                log.info("insert dynamic params succeed!");
                return ResponseEntity.ok().build();
            }
        }
        log.info("error: dynamic params is null");
        return ResponseEntity.status(500).build();
    }
}
