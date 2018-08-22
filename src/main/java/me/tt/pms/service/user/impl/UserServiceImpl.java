package me.tt.pms.service.user.impl;

import me.tt.pms.core.domain.User;
import me.tt.pms.core.domain.constants.UserLoginResult;
import me.tt.pms.data.UserMapper;
import me.tt.pms.service.security.EncryptionService;
import me.tt.pms.service.user.UserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import javax.annotation.Resource;

/**
 * @ClassName: UserServiceImpl
 * @Description: 用户服务实现
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/9 14:51
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private EncryptionService encryptionService;


    /**
     * 根据用户名，查找用户
     * @param username 用户名
     * @return 用户
     */
    @Override
    public User getUserByUsername(String username){

        WeekendSqls<User> sqls = WeekendSqls.<User>custom()
                .andEqualTo(User::getUsername, username);
        Example example = Example.builder(User.class)
                .where(sqls)
                .build();

        return userMapper.selectOneByExample(example);
    }

    /**
     * 验证用户
     * @param username 用户名
     * @param password 密码
     * @return 用户登录结果
     */
    @Override
    public UserLoginResult validateUser(String username, String password){
        User user = getUserByUsername(username);

        if(user == null){
            return UserLoginResult.NotExist;
        }
        if(user.getDeleted()){
            return UserLoginResult.Deleted;
        }
        if(!user.getActivated()){
            return UserLoginResult.NotActive;
        }
        if(!user.getEnabled()){
            return UserLoginResult.Disabled;
        }

        String encryptPassword = encryptionService.createPasswordHash(password, user.getPasswordSalt());
        if(!encryptPassword.equals(user.getPassword())){
            return UserLoginResult.WrongPassword;
        }

        return UserLoginResult.Success;
    }
}