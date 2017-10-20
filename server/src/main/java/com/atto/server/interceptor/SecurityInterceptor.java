package com.atto.server.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atto.server.exception.NotPermittedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.atto.server.model.AccessLog;
import com.atto.server.service.SecurityService;
import com.atto.server.util.HttpUtil;
import com.atto.server.util.SecurityContext;

/**
 * Security Check (Authorization) before Controller
 *
 * Created by dhjung on 2017. 8. 29..
 */
@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityService securityService;

    private AccessLog accessLog;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.trace("[SecurityInterceptor] preHandle request = " + request.getRequestURI() + " " + request.getMethod());

//    	  accessLog = new AccessLog();
//        accessLog.setRequest_dtm(String.valueOf(TimeUtil.getCurrentTimeMillis()));
//        accessLog.setRemote_ip(request.getRemoteAddr());
//        accessLog.setRemote_host(request.getRemoteHost());
//        //accessLog.setRequest_dtm();

        try {
            securityService.authorize(request);
        } catch (NotPermittedException npe){
            logger.warn("[SecurityInterceptor] Not Permission : uri = " + request.getRequestURI() + " method = " + request.getMethod() + ". " + npe.getLocalizedMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }  catch (SecurityException se) {
            logger.warn("[SecurityInterceptor] Invalid Request : uri = " + request.getRequestURI() + " method = " + request.getMethod() + ". " + se.getLocalizedMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // NOTE: For adding Authorization Header to Response, Controller should return Model except ResponseEntity
        // update token with updated token expiration date
        response.addHeader(HttpUtil.AUTH_HEADER, HttpUtil.getAuthorizationHttpHeaderValueFromSecuirtyContext());

        logger.debug("[SecurityInterceptor] Vaild Request : uri = " + request.getRequestURI() + " method = " + request.getMethod() +
                " userUid = " + SecurityContext.get().getUserUid() + " loginId = " + SecurityContext.get().getLoginId());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	logger.trace("[SecurityInterceptor] postHandle request = " + request.getRequestURI() + " " + request.getMethod());
        logger.trace("[SecurityInterceptor] postHandle response = " + response.getHeaders(HttpUtil.AUTH_HEADER));

//        accessLog.setResult_code(String.valueOf(response.getStatus()));
//        //accessLog.setResult_message();
//        //accessLog.setResponse_dtm();
//        accessLogService.addAccessLog(accessLog);
//        String requestUrl = HttpUtil.getRequestUrl(request);
//        response.setHeader(AUTH_HEADER, ThreadContext.local.get().getToken_key());

    }
}