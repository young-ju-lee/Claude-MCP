package com.ktds.egov.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ktds.egov.controller.admin.AdminController;

public class CSRFHandlerInterceptor extends HandlerInterceptorAdapter {
	
	protected final Logger log = LogManager.getLogger(CSRFHandlerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String refererURL = "";
		String requestURL = "";
		String sessionToken = "";
		String requestToken = "";

		if(request.getRequestURI() != null && (request.getRequestURI().contains("error.do") || request.getRequestURI().contains("popup.do"))) {
			log.info("Call " + request.getRequestURI() + "!");
			return true;
		}

		/*
		if(request.getHeader("Referer") != null) {
			refererURL = (String)request.getHeader("Referer");
		}
		if(request.getParameter("request_url") != null) {
			requestURL = request.getParameter("request_url");
		}
		log.info("Referer URL : " + refererURL);
		log.info("Request URL : " + requestURL);
		
		if(request.getSession().getAttribute("CSRF_TOKEN") != null) {
			sessionToken = (String)request.getSession().getAttribute("CSRF_TOKEN");
		}
		if(request.getParameter("request_token") != null) {
			requestToken = request.getParameter("request_token");
		}
		log.info("Session TOKEN : " + sessionToken);
		log.info("Request TOKEN : " + requestToken);
		
		return true;
		
		*/
		/*
		if(!request.getMethod().equalsIgnoreCase("POST")) {
			log.info("Not POST sessionToken : " + sessionToken);
			log.info("Not POST sessionToken : " + sessionToken);
			return true;
		} else {
			// POST 방식의 요청에 대해서만 CSRF TOKEN을 Check한다.
			sessionToken = (String)request.getSession().getAttribute("CSRF_TOKEN");
			requestToken = request.getParameter("request_token");
			log.info("POST sessionToken : " + sessionToken);
			log.info("POST requestToken : " + requestToken);
			if(sessionToken.equals(requestToken)) {
				return true;
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing CSRF value");
				return false;
			}
		}
		*/
		//Login 상태 체크.
		if(request.getSession().getAttribute("user_id") == null) {
			log.info("CSRF_TOKEN No Check.");
			return true;
		}
		
		if(request.getHeader("Referer") != null) {
			refererURL = (String)request.getHeader("Referer");
			if(refererURL.contains("#none")) {
				refererURL = refererURL.replace("#none", "");
			} else if(refererURL.contains("#")) {
				refererURL = refererURL.replace("#", "");
			}
		}
		if(request.getParameter("request_url") != null) {
			requestURL = request.getParameter("request_url");
			if(requestURL.contains("#none")) {
				requestURL = requestURL.replace("#none", "");
			} else if(requestURL.contains("#")) {
				requestURL = requestURL.replace("#", "");
			}
		}
		
		log.info("Referer URL : " + refererURL);
		log.info("Request URL : " + requestURL);

		// URL이 localhost 인지 운영인지 비교.
		if(requestURL.contains("localhost")) {
			log.info("URL is localhost!");			
			return true;
		}

		if(request.getRequestURI() != null && (requestURL.contains("traffic1.do") || requestURL.contains("traffic2.do") || requestURL.contains("traffic3.do"))) {
			refererURL = "";
			requestURL = "";
		}
		// Referer URL 과 요청 URL이 일치하는지 비교.
		if(!refererURL.equals(requestURL) && refererURL != "") {
			log.info("Referer URL Check Fail!");
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath()+"/main.do");
			return false;
		}
		
		// POST/GET 모든 방식의 요청에 대해서 CSRF TOKEN을 Check한다.
		if(request.getSession().getAttribute("CSRF_TOKEN") != null) {
			sessionToken = (String)request.getSession().getAttribute("CSRF_TOKEN");
		}
		if(request.getParameter("request_token") != null) {
			requestToken = request.getParameter("request_token");
		}
		
		if(sessionToken.equals(requestToken)) {
			log.info("Session TOKEN : " + sessionToken);
			log.info("Request TOKEN : " + requestToken);
			log.info("CSRF_TOKEN Check Success!");
			return true;
		} else {
			log.info("Session TOKEN : " + sessionToken);
			log.info("Request TOKEN : " + requestToken);
			log.info("CSRF_TOKEN Check Fail!");
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath()+"/main.do");
			
			return false;
		}
	}
}
