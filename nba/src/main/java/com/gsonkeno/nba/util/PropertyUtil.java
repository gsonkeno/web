package com.gsonkeno.nba.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by gaosong on 2017-12-20
 */
public class PropertyUtil {
    private static Properties props;

    static {
        Resource resource = new ClassPathResource("app.properties");
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProps() {
        return props;
    }
}
