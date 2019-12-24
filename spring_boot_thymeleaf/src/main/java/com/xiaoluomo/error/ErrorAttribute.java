package com.xiaoluomo.error;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
@Component
public class ErrorAttribute extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {

        Map<String,Object> errorAttributes = super.getErrorAttributes(webRequest,includeStackTrace);
        System.out.println(errorAttributes);
        if(errorAttributes.get("status").equals(404)){

            errorAttributes.put("message","404错误");
        }else {
            errorAttributes.put("message","未知错误");
        }

        return errorAttributes;

    }
}
