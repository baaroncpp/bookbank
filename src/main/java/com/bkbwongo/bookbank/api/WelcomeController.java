package com.bkbwongo.bookbank.api;

import com.bkbwongo.bookbank.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project
 * @Author bkaaron
 * @Date 22/03/2022 11:03
 **/
@RestController
public class WelcomeController {

    private WelcomeService welcomeService;

    @Autowired
    public WelcomeController(WelcomeService welcomeService) {
        this.welcomeService = welcomeService;
    }

    @GetMapping("/welcome")
    public String welcome(@RequestParam(defaultValue = "Stranger") String name){
        return welcomeService.getWelcomeMessage(name);
    }
}
