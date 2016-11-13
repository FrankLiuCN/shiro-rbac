package com.frank.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

//捕获全局异常
public class OverallExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView mv = new ModelAndView();
		boolean ajax = "XMLHttpRequest".equals(request.getHeader("x-requested-with"));
		if (ajax) {
			response.setStatus(HttpStatus.OK.value()); // 设置状态码
			response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 设置ContentType
			response.setCharacterEncoding("UTF-8"); // 避免乱码
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			try {
				response.getWriter().write("{\"code\":-1,\"msg\":\"" + ex.getMessage() + "\"}");
			} catch (IOException e) {
			}
		}else {
			mv.setViewName("login");
		}
		return mv;
	}

}
