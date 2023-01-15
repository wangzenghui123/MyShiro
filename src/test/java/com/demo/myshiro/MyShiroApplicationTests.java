package com.demo.myshiro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.myshiro.constant.Constant;
import com.demo.myshiro.entity.*;
import com.demo.myshiro.service.*;
import com.demo.myshiro.shiro.realm.CustomRealm;
import com.demo.myshiro.util.JwtTokenUtil;
import com.demo.myshiro.util.SaltUtil;
import com.demo.myshiro.util.SpringUtil;

import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringBootTest
class MyShiroApplicationTests {

    @Autowired
    private SysUserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashIterations(1024);
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        securityManager.setRealm(customRealm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken("wzh","123"));
        System.out.println(subject.hasAllRoles(Arrays.asList("admin","user")));
        System.out.println(subject.isPermitted("order:*:*"));
    }

    @Test
    void testMd5(){
        Md5Hash md5Hash = new Md5Hash("123","QWER",1024);
        System.out.println(md5Hash.toHex());
    }

    @Test
    void testUserService(){
        User user = new User("1","wzh","123","QWER");
//        userService.register(user);
//        System.out.println(userService.queryUserByName("wzh").toString());
    }

    @Test
    void testSaltUtil(){
//        String salt = SaltUtil.getSalt(5);
//        System.out.println(salt);
        String md5Password = SaltUtil.getMD5Password("+Q0q1XfJ", "123");
        System.out.println(md5Password);
    }

    @Test
    void testRoleService(){
        Role role = roleService.queryRoleById("1");
        System.out.println(role.toString());
    }
    @Test
    void testPermissionService(){
        Permission permission = permissionService.queryPermissionById("1");
        System.out.println(permission.toString());
    }
    @Test
    void testUserRoleService(){
        List<UserRole> userRoles = userRoleService.queryUserRoleByUserId("81c00141-95eb-429c-be40-2b4d7e3dac64");
        for (UserRole userRole : userRoles) {
            System.out.println(userRole.toString());
        }
    }
    @Test
    void testRolePermissionService(){
        List<RolePermission> rolePermissions = rolePermissionService.queryRolePermissionByRoleId("1");
        for (RolePermission rolePermission : rolePermissions) {
            System.out.println(rolePermission.toString());
        }
    }

    @Test
    void testRedis(){
        User user = new User();
        user.setUsername("wzh");
        user.setPassword("123");
        redisTemplate.opsForValue().set("user1",user);

//        String  o = (String) redisTemplate.opsForValue().get("user1");
//        User user = JSON.parseObject(o,User.class);
//        System.out.println(user);

    }

    @Test
    void testRedisTemplate(){
//        Object redisTemplate = SpringUtil.getBean("redisTemplate");
//        System.out.println(redisTemplate == null);
        Claims claimsFromToken = JwtTokenUtil.getClaimsFromToken("eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkNzFjMmYxOC03ODVlLTQyYTktOTUyYi0xMzNhOGE4MzA3ZGYiLCJyb2xlcy1pbmZvcy1rZXkiOlsiYWRtaW4iLCJkZXYiXSwicGVybWlzc2lvbnMtaW5mb3Mta2V5IjpbInN5czp1c2VyOmRlbGV0ZSIsInN5czp1c2VyOnF1ZXJ5Iiwic3lzOnVzZXI6dXBkYXRlIiwic3lzOnVzZXI6YWRkIl0sImlzcyI6Inlpbmd4dWUuY29tIiwiand0LXVzZXItbmFtZS1rZXkiOiJ3emgiLCJleHAiOjE2NzM4MTIxODV9._jT67lBkQ1KofDpas59exeZzwxoOZAoreHdKIpoMUEg");
        if(claimsFromToken.get(Constant.ROLES_INFOS_KEY) != null){
            Object o = claimsFromToken.get(Constant.ROLES_INFOS_KEY);
            System.out.println(o.toString());
        }
    }
}
