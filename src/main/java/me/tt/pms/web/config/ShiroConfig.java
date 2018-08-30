package me.tt.pms.web.config;

import me.tt.pms.service.authentication.DefaultUserRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: ShiroConfig
 * @Description: Shiro配置
 * @author: tongjianjun@itiaoling.com
 * @date 2018/7/6 11:28
 */
@Configuration
public class ShiroConfig {
    /**
     * aes key 16位
     */
    private final static String CIPHER_KEY = "a;dsf/dvpxz;erma";


    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        chainDefinition.addPathDefinition("/css/**", "anon");
        chainDefinition.addPathDefinition("/img/**", "anon");
        chainDefinition.addPathDefinition("/js/**", "anon");
        chainDefinition.addPathDefinition("/pages/**", "anon");
        chainDefinition.addPathDefinition("/vendor/**", "anon");

        chainDefinition.addPathDefinition("/user/login", "anon");

        chainDefinition.addPathDefinition("/404", "anon");
        chainDefinition.addPathDefinition("/500", "anon");
        chainDefinition.addPathDefinition("/error", "anon");

        chainDefinition.addPathDefinition("/dashboard", "user");
        chainDefinition.addPathDefinition("/index", "user");
        chainDefinition.addPathDefinition("/", "user");

        chainDefinition.addPathDefinition("/**", "authc");

        return chainDefinition;
    }

    @Bean
    public Realm realm(){
        return new DefaultUserRealm();
    }

    @Bean
    public Cookie rememberMeCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(30 * 24 * 3600);

        return simpleCookie;
    }

    @Bean
    public RememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(CIPHER_KEY.getBytes());

        return cookieRememberMeManager;
    }

    @Bean
    public CacheManager cacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");

        return cacheManager;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionIdCookieEnabled(true);
        defaultWebSessionManager.setGlobalSessionTimeout(21600000);
        defaultWebSessionManager.setDeleteInvalidSessions(true);
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);

        return defaultWebSessionManager;
    }
}