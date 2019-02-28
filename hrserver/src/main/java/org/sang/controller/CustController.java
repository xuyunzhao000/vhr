package org.sang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sang on 2017/12/29.
 */
@RestController
@RequestMapping("/cust")
public class CustController {
    @RequestMapping("/basic")
    public String basic() {
        return "basic";
    }
    @RequestMapping("/")
    public String  hello() {
        return "Hello";
    }
}
