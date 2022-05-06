package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.05.02
 */
@RestController
@Slf4j
public class SubstituteController {

    @RequestMapping("substitute")
    public String getSubstitutes(){
        return "substitute";
    }

}
