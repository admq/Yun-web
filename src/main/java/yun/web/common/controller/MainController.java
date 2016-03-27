package yun.web.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by larry on 16/3/1.
 */

@RequestMapping(value = "/")
@Controller
public class MainController {

    @RequestMapping(value = "/go/{path}")
    public String go(@PathVariable String path) {
        return "redirect:/"+path;
    }
}
