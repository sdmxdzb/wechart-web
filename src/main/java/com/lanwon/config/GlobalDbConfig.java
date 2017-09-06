package com.lanwon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;



/***
 * @author dzb
 * @DATE 2017/08/30
 * @see druid 配置文件 
 * @url http://www.cnblogs.com/niejunlei/p/5977895.html
 * **/
@Configuration
public class GlobalDbConfig {

    @Value("${spring.datasource.filters}")
    private String dbFilters;

    @Value("${spring.datasource.maxActive}")
    private int dbMaxActive;

    @Value("${spring.datasource.initialSize}")
    private int dbInitialSize;

    @Value("${spring.datasource.maxWait}")
    private int dbMaxWait;

    @Value("${spring.datasource.minIdle}")
    private int dbMinIdle;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int dbTimeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int dbMinEvictableIdleTimeMillis;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean dbtestWhileIdle;

    @Value("${spring.datasource.validationQuery}")
    private String dbValidationQuery;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean dbTestOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean dbTestOnReturn;

    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean dbPoolPreparedStatements;

    @Value("${spring.datasource.maxOpenPreparedStatements}")
    private int dbMaxOpenPreparedStatements;

    public String getDbFilters() {
        return dbFilters;
    }

    public int getDbMaxActive() {
        return dbMaxActive;
    }

    public int getDbInitialSize() {
        return dbInitialSize;
    }

    public int getDbMaxWait() {
        return dbMaxWait;
    }

    public int getDbMinIdle() {
        return dbMinIdle;
    }

    public int getDbTimeBetweenEvictionRunsMillis() {
        return dbTimeBetweenEvictionRunsMillis;
    }

    public int getDbMinEvictableIdleTimeMillis() {
        return dbMinEvictableIdleTimeMillis;
    }

    public boolean isDbtestWhileIdle() {
        return dbtestWhileIdle;
    }

    public String getDbValidationQuery() {
        return dbValidationQuery;
    }

    public boolean isDbTestOnBorrow() {
        return dbTestOnBorrow;
    }

    public boolean isDbTestOnReturn() {
        return dbTestOnReturn;
    }

    public boolean isDbPoolPreparedStatements() {
        return dbPoolPreparedStatements;
    }

    public int getDbMaxOpenPreparedStatements() {
        return dbMaxOpenPreparedStatements;
    }

}
