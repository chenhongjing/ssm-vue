package org.example.service;

import org.example.entity.SystemLog;

import java.util.List;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.06.07
 */
public interface SystemLogService {
    void addLog(SystemLog operLog);

    List<SystemLog> getAllLogs(String query);
}
