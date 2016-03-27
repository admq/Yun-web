package yun.web.filesystem.controller;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yun.web.common.exception.ParameterNotMatchException;
import yun.web.filesystem.domain.FS;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by larry on 16/2/8.
 */

@Controller
@RequestMapping(value = "/fileSystemController/")
public class FileSystemController {

    Logger logger = Logger.getLogger(FileSystemController.class);

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public void upload(HttpServletRequest request) {
        System.out.println("upload");
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public void hello(HttpServletRequest request) {
        System.out.println("upload");
    }

    @RequestMapping(value = "/say/{word}", method = RequestMethod.GET)
    public void say(@PathVariable String word) {
        System.out.println(word);
    }

    @RequestMapping(value = "filelist")
    @ResponseBody
    public Map<String, Object> fileList() {
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }

    @ExceptionHandler(ParameterNotMatchException.class)
    @RequestMapping(value = "param")
    public void param(String name, int age) {
        logger.info(name + ":" + age);
    }


    /**
     * Json
     */
    @RequestMapping(value = "json")
    @ResponseBody
    public FS getJson() {
        logger.info("json");
        FS fs = new FS("test.sh", 12);
        return fs;
    }
}
