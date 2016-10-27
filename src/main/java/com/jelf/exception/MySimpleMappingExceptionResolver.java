package com.jelf.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class MySimpleMappingExceptionResolver extends
		SimpleMappingExceptionResolver {

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

		// Expose ModelAndView for chosen error view.
		String viewName = determineViewName(ex, request);
		if (viewName != null) {// JSP格式返回
			if ((request.getHeader("X-Requested-With") != null
					&& request.getHeader("X-Requested-With").indexOf(
							"XMLHttpRequest") > -1 )|| (request
					.getHeader("accept") != null
					&& request.getHeader("accept").indexOf("application/json") > -1)) {
				System.out.println("页面返回异常");
				// Apply HTTP status code for error views, if specified.
				// Only apply it if we're processing a top-level request.
				Integer statusCode = determineStatusCode(request, viewName);
				if (statusCode != null) {
					applyStatusCodeIfPossible(request, response, statusCode);
					return getModelAndView(viewName, ex, request);
				}
			} else {// JSON格式返回
				Map<String, Object> model = new HashMap<String, Object>();
				if (this.logger.isDebugEnabled()) {
					model.put("debug", true);
				}// exception
				model.put("success", false);
				model.put("msg", ex.getMessage());
				try {
					response.getWriter().write("出错");
				} catch (IOException e) {
					e.printStackTrace();
				}
				return new ModelAndView();
			}
			return null;
		} else {
			return null;
		}
	}
}
