package me.tt.pms.service.security.impl;

import me.tt.pms.core.AdviceException;
import me.tt.pms.core.utils.StringUtils;
import me.tt.pms.service.security.EncryptionService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @ClassName: EncryptionServiceImpl
 * @Description: 加密服务
 * @author: tongjianjun@itiaoling.com
 * @date 2018/8/9 15:36
 */
@Service("encryptionService")
public class EncryptionServiceImpl implements EncryptionService {

    /**
     * 创建密码盐
     * @param size 长度
     * @return 密码盐
     */
    @Override
    public String createSaltKey(int size)
    {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[size];
        random.nextBytes(bytes);

        return Base64.encodeBase64String(bytes);
    }

    /**
     * 创建密码hash
     * @param password 密码明文
     * @param saltkey 密码盐
     * @return 密码hash
     */
    @Override
    public String createPasswordHash(String password, String saltkey) {
        return createPasswordHash(password, saltkey, "MD5");
    }

    /**
     * 创建密码hash
     * @param password 密码明文
     * @param saltkey 密码盐
     * @param algorithm 算法名称
     * @return 密码hash
     */
    @Override
    public String createPasswordHash(String password, String saltkey, String algorithm) {
        try {
            String plaintext = password + saltkey;
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(plaintext.getBytes());

            StringBuilder hashHex = new StringBuilder();
            byte[] hashBuffer = md.digest();
            for (byte hashByte : hashBuffer) {
                hashHex.append(StringUtils.padLeft(Integer.toHexString(hashByte & 0xff), 2, '0'));
            }

            return hashHex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new AdviceException("不支持的加密算法(%s)", algorithm);
        }
    }
}