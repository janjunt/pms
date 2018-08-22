package me.tt.pms.core.domain.constants;

/**
 * @ClassName: UserLoginResult
 * @Description: 用户登录结果
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/15 10:46
 */
public enum UserLoginResult {
    Success(1, "成功"),
    NotExist(1, "用户不存在"),
    WrongPassword(1, "密码错误"),
    NotActive(1, "用户未激活"),
    Disabled(1, "用户已禁用"),
    Deleted(1, "用户已删除");

    /**
     * 编码
     */
    private int code;
    /**
     * 名称
     */
    private String name;

    /**
     * 构造函数
     * @param code 编码
     * @param name 名称
     */
    UserLoginResult(int code, String name){
        this.code = code;
        this.name = name;
    }

    /**
     * 获取编码
     * @return 编码
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置编码
     * @param code 编码
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 获取名称
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 根据编码，获取名称
     * @param code 编码
     * @return 名称
     */
    public static String getName(Integer code) {
        if(code == null){
            return null;
        }

        for (UserLoginResult item : UserLoginResult.values()) {
            if (item.getCode() == code) {
                return item.name;
            }
        }

        return null;
    }
}
