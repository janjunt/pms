package me.tt.pms.service.user;

import me.tt.pms.core.domain.User;
import me.tt.pms.core.domain.constants.UserLoginResult;
import me.tt.pms.core.domain.dto.MenuDto;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName: UserService
 * @Description: 用户服务接口
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/9 14:50
 */
public interface UserService {
    /**
     * 根据用户名，查找用户
     * @param username 用户名
     * @return 用户
     */
    User getUserByUsername(@NotNull(message = "用户名不能为空") String username);

    /**
     * 验证用户
     * @param username 用户名
     * @param password 密码
     * @return 用户登录结果
     */
    UserLoginResult validateUser(@NotNull(message = "用户名不能为空") String username,
                                 @NotNull(message = "密码不能为空") String password);
}
