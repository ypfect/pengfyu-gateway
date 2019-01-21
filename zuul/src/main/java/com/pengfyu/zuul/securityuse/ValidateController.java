package com.pengfyu.zuul.securityuse;

import com.pengfyu.zuul.springsecurity.properties.SecurityConstants;
import com.pengfyu.zuul.springsecurity.validate.ValidateCodeProcessorHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author stanley.yu
 * @Description
 * @Date 2019/1/21 22:15
 */
@RestController
public class ValidateController {

    @Autowired
    private ValidateCodeProcessorHolder holder;

    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/{validateType}")
    public void generateValidateCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String validateType) throws Exception {
        holder.findValidateCodeProcessor(validateType).create(new ServletWebRequest(request,response));
    }
}
