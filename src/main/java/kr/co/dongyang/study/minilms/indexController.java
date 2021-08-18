package kr.co.dongyang.study.minilms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class indexController {


    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/common/denied")
    public String commonDenied(){

        return "common/denied";
    }

}
