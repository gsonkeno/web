package com.gsonkeno.nba.config;

import com.gsonkeno.nba.util.PropertyUtil;
import com.mysql.jdbc.Driver;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * Created by gaosong on 2017-12-01
 */
@Configuration
@ComponentScan(basePackages = {"com.gsonkeno.nba"},
        excludeFilters = {
                @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
        })
public class RootConfig {
    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(Driver.class.getName());
        ds.setUrl(PropertyUtil.getProps().getProperty("db.url"));
        ds.setUsername(PropertyUtil.getProps().getProperty("db.user"));
        ds.setPassword(PropertyUtil.getProps().getProperty("db.password"));
        ds.setInitialSize( Integer.valueOf(PropertyUtil.getProps().getProperty("db.initialSize")));
        ds.setMaxActive(Integer.valueOf(PropertyUtil.getProps().getProperty("db.maxActive")));
        ds.setMaxIdle(Integer.valueOf(PropertyUtil.getProps().getProperty("db.maxIdle")));
        ds.setMaxWait(Integer.valueOf(PropertyUtil.getProps().getProperty("db.maxWait")));
        ds.setDefaultAutoCommit(Boolean.valueOf(PropertyUtil.getProps().getProperty("db.defaultAutoCommit")));
        return ds;
    }

    @Bean
    public JdbcOperations jdbc(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}
