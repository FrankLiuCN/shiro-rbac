package com.frank.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.expr.Instanceof;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
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
				String msg = "";
				if (ex instanceof AuthorizationException) {
					msg = "{\"code\":-2,\"msg\":\"无权限访问此功能。\"}";
				} else {

					msg = "{\"code\":-1,\"msg\":\"" + ex.getMessage() + "\"}";
				}
				response.getWriter().write(msg);
			} catch (IOException e) {
			}
		} else {
			mv.setViewName("login");
		}
		return mv;
	}

}
