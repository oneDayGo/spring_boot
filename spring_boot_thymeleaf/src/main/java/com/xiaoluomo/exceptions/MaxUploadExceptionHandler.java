package com.xiaoluomo.exceptions;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MaxUploadExceptionHandler {
    //全局异常处理
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView uploadException(MaxUploadSizeExceededException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "上传文件大小超过限制");
        mv.setViewName("ex");
        return mv;

    }
}
