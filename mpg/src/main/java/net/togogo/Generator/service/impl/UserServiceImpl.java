package net.togogo.Generator.service.impl;

import net.togogo.Generator.bean.User;
import net.togogo.Generator.mapper.UserMapper;
import net.togogo.Generator.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qdd
 * @since 2019-04-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
