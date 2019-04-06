package net.togogo;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import net.togogo.bean.User;
import net.togogo.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

public class EntityWrapperTest {

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

    //查询表数据库 年龄30-40之间 邮箱为swk@qq.com所有用户
    @Test
    public void EntityWarpperTest01() {
        UserMapper userMapper = context.getBean(UserMapper.class);
        List<User> userList = userMapper.selectPage(new Page<>(1, 5),
                new EntityWrapper<User>()
                        .between("age", 20, 40)
                        .eq("email", "swk@qq.com")
                        .eq("last_name", "孙悟空")
        );
        userList.stream().forEach(System.out::println);
    }

    //模糊查询 年龄为22 并且名字中带“孙” 或者 邮箱带“s” 的所有用户
    @Test
    public void EntityWarpperTest02() {
        UserMapper userMapper = context.getBean(UserMapper.class);
        List<User> userList = userMapper.selectList(new EntityWrapper<User>()
                .eq("age", 22)
                .like("last_name", "孙")
                //.or() //SELECT id AS id,last_name AS lastname,email,age FROM t_user WHERE (age = ? AND last_name LIKE ? OR email LIKE ?)
                .orNew() //SELECT id AS id,last_name AS lastname,email,age FROM t_user WHERE (age = ? AND last_name LIKE ?) OR (email LIKE ?)
                .like("email", "s")
        );
        userList.stream().forEach(System.out::println);
    }

    //排序 查询名字=“孙悟空” 根据年龄排序
    @Test
    public void EntityWarpperTest03() {
        UserMapper userMapper = context.getBean(UserMapper.class);
        List<User> userList = userMapper.selectList(new EntityWrapper<User>()
                .eq("last_name", "孙悟空")
                .orderBy("age") //顺序
                //.orderDesc(Arrays.asList(new String[]{"age"}))
                .last("desc limit 0,9") //sql注入风险
        );
        userList.stream().forEach(System.out::println);
    }

    //Condition
    @Test
    public void ConditionTest(){
        UserMapper userMapper = context.getBean(UserMapper.class);
        List userList = userMapper.selectPage(new Page<User>(0, 3), Condition.create()
                .between("age", 20, 30)
                .eq("last_name", "孙悟空")
                .eq("email", "swk@qq.com")
        );
        userList.stream().forEach(System.out::println);
    }
}
