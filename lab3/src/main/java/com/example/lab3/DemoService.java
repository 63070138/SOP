package com.example.lab3;

import org.springframework.web.bind.annotation.*;

@RestController
public class DemoService {

    @RequestMapping(value = "/hello/{name}/{surname}", method = RequestMethod.GET)
    public String helloWorld(@PathVariable("name") String n,
                             @PathVariable("surname") String sname){
        return "Hello " + n + " " + sname;
    }
    @RequestMapping(value = "add/{a}/{b}", method = RequestMethod.GET)
    public String add(@PathVariable("a") Double a, @PathVariable("b") Double b){
        double ans;
        ans = a+b;
        return ans+"";
    }

    @RequestMapping(value = "minus/{a}/{b}", method = RequestMethod.GET)
    public String minus(@PathVariable("a") Double a, @PathVariable("b") Double b){
        double ans;
        ans = a-b;
        return ans+"";
    }

    @RequestMapping(value = "/multiply", method = RequestMethod.GET)
    public String multiply(@RequestParam("num1") Double num1, @RequestParam("num2") Double num2){
        double ans;
        ans = num1*num2;
        return ans+"";
    }

    @RequestMapping(value = "/divide", method = RequestMethod.GET)
    public String divide(@RequestParam("num1") Double num1, @RequestParam("num2") Double num2){
        double ans;
        ans = num1/num2;
        return ans+"";
    }

}
