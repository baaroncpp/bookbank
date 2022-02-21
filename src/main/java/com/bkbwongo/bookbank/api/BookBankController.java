package com.bkbwongo.bookbank.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author bkaaron
 * @created 21/02/2022 - 9:59 AM
 * @project bookbank
 */
@Controller
public class BookBankController {
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index(){
        return "search.html";
    }
}
