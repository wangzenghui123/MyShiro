package com.demo.myshiro.shiro.realm;

import com.demo.myshiro.constant.Constant;
import com.demo.myshiro.service.*;
import com.demo.myshiro.shiro.token.CustomUsernamePasswordToken;
import com.demo.myshiro.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class CustomRealm extends AuthorizingRealm {

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

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof  CustomUsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("数据库拿权限。。。");
//        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        SysUser user = userService.queryUserByName(primaryPrincipal);
//        if(user != null){
//            List<UserRole> userRoles = userRoleService.queryUserRoleByUserId(user.getId());
//            for (UserRole userRole : userRoles) {
//                Role role = roleService.queryRoleById(userRole.getRoleId());
//                info.addRole(role.getName());
//                List<RolePermission> rolePermissions = rolePermissionService.queryRolePermissionByRoleId(role.getId());
//                for (RolePermission rolePermission : rolePermissions) {
//                    Permission permission = permissionService.queryPermissionById(rolePermission.getPermissionId());
//                    info.addStringPermission(permission.getName());
//                }
//            }
//        }
//        return info;
        String accessToken = (String) principalCollection.getPrimaryPrincipal();
        Claims claimsFromToken = JwtTokenUtil.getClaimsFromToken(accessToken);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if(claimsFromToken.get(Constant.ROLES_INFOS_KEY) != null){
            info.addRoles((Collection<String>) claimsFromToken.get(Constant.ROLES_INFOS_KEY));
        }
        if(claimsFromToken.get(Constant.PERMISSIONS_INFOS_KEY) !=null){
            info.addStringPermissions((Collection<String>) claimsFromToken.get(Constant.PERMISSIONS_INFOS_KEY));
        }
        return  info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        System.out.println("认证中。。。");
//        Object principal = authenticationToken.getPrincipal();
//        String username = principal.toString();
//       SysUser user = userService.queryUserByName(username);
//        if (user != null){
//            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,user.getPassword(),new CustomByteSource(user.getSalt()),this.getName());
//            return info;
//        }
//        return null;
        CustomUsernamePasswordToken customUsernamePasswordToken = (CustomUsernamePasswordToken) authenticationToken;
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(customUsernamePasswordToken.getPrincipal(),customUsernamePasswordToken.getCredentials(),this.getName());
        return info;
    }
}
