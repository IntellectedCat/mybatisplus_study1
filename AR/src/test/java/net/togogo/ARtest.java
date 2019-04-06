package net.togogo;

import net.togogo.bean.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;

public class ARtest {

    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("spring-context.xml");

    //获取数据源
    @Test
    public void DataSourcesTest() throws Exception {
        DataSource ds = context.getBean("dataSource", DataSource.class);
        System.out.println("获取到的数据源是：" + ds);

        Connection connection = ds.getConnection();
        System.out.println("connection = " + connection);
    }

    @Test
    public void ARinserTest(){
        User user = new User();
        user.setLastname("随便");
        user.setEmail("sb@qq.com");
        user.setAge(18);
        boolean result = user.insert();
        System.out.println("result = " + result);
    }

}
