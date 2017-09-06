/**
 * 蓝网科技股份有限公司
 */
package com.lanwon.config;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author dzb	
 * @date 2017年8月23日 
 * @see 数据源注入
 */
@Configuration
@MapperScan(basePackages="com.lanwon.wechart.system.mapper",sqlSessionFactoryRef="devSqlSessionFactory")
public class devDataSourceConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String dbDriver;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    
    @Resource
    private GlobalDbConfig globalDbConfig;//配置参数类

    private static final String MAPPER_PATH = "classpath*:/mapper/wechart/*.xml";

    private static final String ENTITY_PACKAGE = "com.lanwon.wechart.system.entity";

    
    @Bean(name = "devDataSource")
    @Primary
    public DataSource devDataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dbDriver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setFilters(globalDbConfig.getDbFilters());
        dataSource.setMaxActive(globalDbConfig.getDbMaxActive());
        dataSource.setMaxWait(globalDbConfig.getDbMaxWait());
        dataSource.setMinIdle(globalDbConfig.getDbMinIdle());
        dataSource.setTimeBetweenEvictionRunsMillis(globalDbConfig
                .getDbTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(globalDbConfig
                .getDbMinEvictableIdleTimeMillis());
        dataSource.setTestWhileIdle(globalDbConfig.isDbtestWhileIdle());
        dataSource.setValidationQuery(globalDbConfig.getDbValidationQuery());
        dataSource.setTestOnBorrow(globalDbConfig.isDbTestOnBorrow());
        dataSource.setTestOnReturn(globalDbConfig.isDbTestOnReturn());
        dataSource.setPoolPreparedStatements(globalDbConfig
                .isDbPoolPreparedStatements());
        dataSource.setMaxOpenPreparedStatements(globalDbConfig
                .getDbMaxOpenPreparedStatements());
        dataSource.init();
        return dataSource;
    }

    @Bean(name = "devTransactionManager")
    @Primary
    public DataSourceTransactionManager devTransactionManager()
            throws SQLException {
        return new DataSourceTransactionManager(devDataSource());
    }
    
    /***
     * 
     * 注册sessionFactory
     * ***/
    @Bean(name = "devSqlSessionFactory")
    @Primary //默认
    public SqlSessionFactory devSqlSessionFactory(
            @Qualifier("devDataSource") DataSource ddataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(ddataSource);//数据源
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources(MAPPER_PATH));//xml路径
        sessionFactory.setTypeAliasesPackage(ENTITY_PACKAGE);//实体类
        return sessionFactory.getObject();
    }

}
