package com.xiaoluomo.config;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/** 全局异常处理*/

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(AuthorizationException.class)
    public ModelAndView error(AuthorizationException e){

        ModelAndView vm = new ModelAndView("unauthorized");
        vm.addObject("error",e.getMessage());

        return vm;

    }
}
