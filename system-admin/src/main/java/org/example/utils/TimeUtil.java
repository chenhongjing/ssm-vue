package org.example.utils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.05.12
 */
public class TimeUtil {
    static public Timestamp getTimeStamp(){
        // 获取时间戳
        Date javaDate = new Date();
        return new Timestamp(javaDate.getTime());
    }
}
