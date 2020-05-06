package com.ruan.log.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author XY
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Log {

    /**
     * 日志ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 日志日期，格式YYYYMMDD
     */
    private String logDate;

    /**
     * 日志时间
     */
    private Date logTime;

    /**
     * 日志信息
     */
    private String logInfo;

    /**
     * 备注
     */
    private String remarks;
}
