package me.tt.pms.web.config;

import me.tt.pms.service.authentication.DefaultUserRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
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
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        chainDefinition.addPathDefinition("/css/**", "anon");
        chainDefinition.addPathDefinition("/img/**", "anon");
        chainDefinition.addPathDefinition("/js/**", "anon");
        chainDefinition.addPathDefinition("/pages/**", "anon");
        chainDefinition.addPathDefinition("/vendor/**", "anon");

        chainDefinition.addPathDefinition("/login", "anon");

        chainDefinition.addPathDefinition("/404", "anon");
        chainDefinition.addPathDefinition("/500", "anon");
        chainDefinition.addPathDefinition("/error", "anon");

        chainDefinition.addPathDefinition("/**", "authc");

        return chainDefinition;
    }

    @Bean
    public Realm realm(){
        return new DefaultUserRealm();
    }
}