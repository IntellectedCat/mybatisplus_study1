package net.togogo;

import com.baomidou.mybatisplus.plugins.Page;
import net.togogo.bean.User;
import net.togogo.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloWordTest {

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

    //插入测试
    @Test
    public void mpInsertTest(){
        UserMapper userMapper = context.getBean(UserMapper.class);
        User user = new User();
        //user.setId(6);
        user.setLastname("钱多多3");
        user.setEmail("qdd3@qq.com");
        user.setAge(48);
        Integer result = userMapper.insert(user);
        System.out.println("result = " + result);
    }

    //插入测试
    @Test
    public void mpUpdateTest(){
        UserMapper userMapper = context.getBean(UserMapper.class);
        User user = new User();
        user.setId(6);
        user.setLastname("钱多多4");
        user.setEmail("qdd43@qq.com");
        user.setAge(39);
        Integer result = userMapper.updateById(user);
        System.out.println("result = " + result);
    }

    @Test
    public void mpSelectByIdTest(){
        UserMapper userMapper = context.getBean(UserMapper.class);
        User user = userMapper.selectById(6);
        System.out.println("user = " + user);
    }

    @Test
    public void mpSelectOneTest(){
        UserMapper userMapper = context.getBean(UserMapper.class);
        User user = new User();
        user.setLastname("钱多多4");
        user.setEmail("qdd43@qq.com");
        User one = userMapper.selectOne(user);
        System.out.println("one = " + one);
    }

    @Test
    public void mpSelecByListTest(){
        UserMapper userMapper = context.getBean(UserMapper.class);
        List<Integer> userList = new ArrayList<>();
        userList.add(1);
        userList.add(2);
        List<User> userList1 = userMapper.selectBatchIds(userList);
        userList1.stream().forEach(System.out::println);
    }

    @Test
    public void mpSelecByMapTest(){
        UserMapper userMapper = context.getBean(UserMapper.class);
        Map<String,Object> colunm = new HashMap<>();
        colunm.put("last_name","qdd");
        colunm.put("age",18);
        List<User> userList = userMapper.selectByMap(colunm);
        userList.stream().forEach(System.out::println);
    }

    @Test
    public void mpSelecAllTest(){
        UserMapper userMapper = context.getBean(UserMapper.class);
        List<User> userList = userMapper.selectList(null);
        userList.stream().forEach(System.out::println);
    }

    @Test
    public void mpSelecAllByPageTest(){
        UserMapper userMapper = context.getBean(UserMapper.class);
        List<User> userList = userMapper.selectPage(new Page<>(0, 2), null);
        userList.stream().forEach(System.out::println);
    }

}
