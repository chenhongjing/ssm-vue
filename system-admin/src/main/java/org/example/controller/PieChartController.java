package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.PieChartData;
import org.example.service.PieChartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.05.12
 */
@RestController
@Slf4j
public class PieChartController {
    @Resource
    private PieChartService pieChartService;

    @GetMapping("pie_chart")
    public ResponseEntity<List<PieChartData>> getPieChartData(){
        List<PieChartData> pieChartData = pieChartService.getPieChartData();
        return ResponseEntity.ok().body(pieChartData);
    }

}
