package com.blackwell.aop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {

    @Getter @Setter
    @Value("${exception.handler.on}")
    private boolean isOn;
	
	@ExceptionHandler
	public ModelAndView handleException(Exception ex) throws Exception {
	    if (!isOn) {
            throw ex;
        }
        ModelAndView modelAndView = new ModelAndView("errorpage");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
	}
	
}
