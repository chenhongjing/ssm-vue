package org.example.service.impl;

import org.example.dao.SystemLogDao;
import org.example.entity.Organ;
import org.example.entity.OrganExample;
import org.example.entity.SystemLog;
import org.example.entity.SystemLogExample;
import org.example.service.SystemLogService;
import org.example.utils.RequestUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.06.07
 */
@Service("SystemLogService")
public class SystemLogServiceImpl implements SystemLogService {

    @Resource
    private SystemLogDao systemLogDao;

    @Override
    @Async
    public void addLog(SystemLog operLog) {
        systemLogDao.insertSelective(operLog);
    }

    @Override
    public List<SystemLog> getAllLogs(String query) {
        List<SystemLog> records = null;
        SystemLogExample logEx = new SystemLogExample();
        if(query != null && !query.isEmpty()){
            logEx.or().andTitleLike("%" + query + "%");
            logEx.or().andLogTypeLike("%" + query + "%");
            logEx.or().andRequestMethodLike("%" + query + "%");
            logEx.or().andUserNameLike("%" + query + "%");
            logEx.or().andOperIpLike("%" + query + "%");
        }
        else{
            logEx.createCriteria();
        }
        records = systemLogDao.selectByExample(logEx);
        return records;
    }
}
