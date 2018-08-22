package me.tt.pms.service.security;

/**
 * @ClassName: EncryptionServiceImpl
 * @Description: 加密服务接口
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/9 15:35
 */
public interface EncryptionService {
    /**
     * 创建密码盐
     * @param size 长度
     * @return 密码盐
     */
    String createSaltKey(int size);

    /**
     * 创建密码hash
     * @param password 密码明文
     * @param saltkey 密码盐
     * @return 密码hash
     */
    String createPasswordHash(String password, String saltkey);

    /**
     * 创建密码hash
     * @param password 密码明文
     * @param saltkey 密码盐
     * @param algorithm 算法名称
     * @return 密码hash
     */
    String createPasswordHash(String password, String saltkey, String algorithm);
}
