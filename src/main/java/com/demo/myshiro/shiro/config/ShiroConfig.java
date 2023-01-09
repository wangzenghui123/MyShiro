package com.demo.myshiro.shiro.config;

import com.demo.myshiro.shiro.cache.CustomCacheManager;
import com.demo.myshiro.shiro.filter.CustomShiroFilter;
import com.demo.myshiro.shiro.realm.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public CustomCacheManager customCacheManager(){
        return new CustomCacheManager();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        Map<String, Filter> filterMap = new HashMap<>();
       // filterMap.put("token",new CustomShiroFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        Map<String,String> map = new HashMap<>();
        map.put("/layui/**","anon");
        map.put("/swagger/**", "anon");
        map.put("/v2/api-docs", "anon");
        map.put("/swagger-ui.html", "anon");
        map.put("/swagger-resources/**", "anon");
        map.put("/webjars/**", "anon");
        map.put("/user/login","anon");
        map.put("/user/loginhtml","anon");
        map.put("/user/register","anon");
        map.put("/register.jsp","anon");
        map.put("/login.html","anon");

        map.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);


        shiroFilterFactoryBean.setLoginUrl("/login.html");
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    @Bean
    public Realm realm(CustomCacheManager customCacheManager){
        CustomRealm customRealm = new CustomRealm();
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        customRealm.setCacheManager(new CustomCacheManager());
        customRealm.setCachingEnabled(true);
        customRealm.setAuthorizationCachingEnabled(true);
       // customRealm.setAuthenticationCachingEnabled(true);
        return customRealm;
    }
}
