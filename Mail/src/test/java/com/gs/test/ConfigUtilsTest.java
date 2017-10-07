package com.gs.test;

import com.gs.mail.ConfigUtils;
import org.junit.Test;

import java.util.Properties;

/**
 * Created by Administrator on 2017/9/22.
 */
public class ConfigUtilsTest {
    @Test
    public void testBuild() {
        Properties properties = ConfigUtils.build("src/main/resources/mail.properties");
        System.out.println(ConfigUtils.getString("username"));
        System.out.println(ConfigUtils.getString("password"));
        System.out.println(ConfigUtils.getInteger("mail.smtp.port"));
    }
}
