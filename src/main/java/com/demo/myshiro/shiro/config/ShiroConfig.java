package com.demo.myshiro.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.demo.myshiro.shiro.cache.CustomCacheManager;
import com.demo.myshiro.shiro.credential.CustomHashCredentialMatcher;
import com.demo.myshiro.shiro.filter.CustomShiroFilter;
import com.demo.myshiro.shiro.realm.CustomRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    @Bean
    public CustomCacheManager customCacheManager(){
        return new CustomCacheManager();
    }

    @Bean
    public CustomHashCredentialMatcher customHashCredentialMatcher(){
        return  new CustomHashCredentialMatcher();
    }

    @Bean("customRealm")
    public CustomRealm customRealm(){
        CustomRealm customRealm = new CustomRealm();
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher("MD5");
//        hashedCredentialsMatcher.setHashIterations(1024);
//        customRealm.setCredentialsMatcher(hashedCredentialsMatcher);
//        customRealm.setCacheManager(new CustomCacheManager());
        customRealm.setCredentialsMatcher(customHashCredentialMatcher());
        //customRealm.setCacheManager(customCacheManager());
      //  customRealm.setCachingEnabled(true);
       //customRealm.setAuthorizationCachingEnabled(true);
        // customRealm.setAuthenticationCachingEnabled(true);
        return customRealm;
    }


    @Bean("securityManager")
    public SecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(customRealm());
        return defaultWebSecurityManager;
    }
//
//    @EventListener
//    public void handleContextRefresh(ContextRefreshedEvent event) {
//        ApplicationContext context = event.getApplicationContext();
//        DefaultWebSecurityManager manager = (DefaultWebSecurityManager) context.getBean("securityManager");
//        CustomRealm customRealm = (CustomRealm) context.getBean("customRealm");
//        manager.setRealm(customRealm);
//    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());


        Map<String,String> map = new LinkedHashMap<>();
        //在 filterChainDefinitionMap 下面最多可以配置 12 条验证规则
        map.put("/webjars/**", "anon");
        map.put("/layui/**","anon");
        map.put("/js/**","anon");
        map.put("/swagger/**", "anon");
        map.put("/index/**", "anon");
        map.put("/login/**", "anon");
        map.put("/api/home", "anon");
        map.put("/v2/api-docs", "anon");
        map.put("/swagger-ui.html", "anon");
        map.put("/swagger-resources/**", "anon");
        map.put("/user/**","anon");
       // map.put("/register.jsp","anon");
        map.put("/login.html","anon");
        map.put("/home.html","anon");
        map.put("/**","token,authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("token",new CustomShiroFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        shiroFilterFactoryBean.setLoginUrl("/login.html");
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }


}
