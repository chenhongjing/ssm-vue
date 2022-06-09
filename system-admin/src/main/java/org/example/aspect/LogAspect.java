package org.example.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.example.annotation.Log;
import org.example.entity.SystemLog;
import org.example.service.SystemLogService;
import org.example.utils.RequestUtil;
import org.example.utils.TimeUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.06.07
 */
@Aspect
@Component
public class LogAspect {

    @Resource
    private SystemLogService systemLogService;

    @AfterReturning("@annotation(log)")
    public void afterReturning(JoinPoint joinPoint, Log log){
        HttpServletRequest request =  ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        SystemLog operLog = createLog(joinPoint, request, log, null);
        systemLogService.addLog(operLog);

    }

    @AfterThrowing(value="@annotation(log)", throwing="exception")
    public void afterThrowing(JoinPoint joinPoint, Log log, Exception exception){
        HttpServletRequest request =  ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        SystemLog operLog = createLog(joinPoint, request, log, exception);
        systemLogService.addLog(operLog);
    }

    private SystemLog createLog(JoinPoint joinPoint, HttpServletRequest request, Log log, Exception exception){
        SystemLog operLog = new SystemLog();
        operLog.setTitle(log.title());
        operLog.setLogType(log.logType());
        if(exception != null){
            operLog.setErrorMsg(exception.getMessage().substring(0,1000));
            operLog.setOperStatus(500);
        }
        else{
            operLog.setOperStatus(200);
        }
        operLog.setOperIp(request.getRemoteAddr());
        operLog.setRequestMethod(request.getMethod());
        String username = "";
        username = RequestUtil.getUsername();
        operLog.setUserName(username.equals("null") ? "unknown": username);
        operLog.setRequestUrl(request.getRequestURI());
        // 获取api方法
        operLog.setApiMethod(joinPoint.getSignature().getName());
        operLog.setOperTime(TimeUtil.getTimeStamp());
        return operLog;
    }
}
