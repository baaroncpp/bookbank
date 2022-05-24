package com.bkbwongo.bookbank.service.imp;

import com.bkbwongo.bookbank.service.WelcomeService;
import org.springframework.stereotype.Service;

/**
 * @Project
 * @Author bkaaron
 * @Date 22/03/2022 11:04
 **/
@Service
public class WelcomeServiceImp implements WelcomeService {
    @Override
    public String getWelcomeMessage(String name) {
        return String.format("Welcome %s"+name);
    }
}
