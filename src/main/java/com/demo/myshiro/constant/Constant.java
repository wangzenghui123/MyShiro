package com.demo.myshiro.constant;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import org.apache.shiro.authz.SimpleAuthorizationInfo;

public class Constant {

    //存放在Claims中的，当前用户的用户名key,通过此key可以获得用户名（value）
    public static final String JWT_USER_NAME = "jwt-user-name-key";

    //角色信息key
    public static final String ROLES_INFOS_KEY = "roles-infos-key";

    //权限信息key
    public static final String PERMISSIONS_INFOS_KEY = "permissions-infos-key";

    //业务token key,存放在request的Header中
    public static final String ACCESS_TOKEN = "authorization";

    //
    public static final String JWT_REFRESH_KEY = "jwt-refresh-key_";

    //redis中，用户账户被锁定，”ACCOUNT_LOCK_KEY  + 用户id“
    public static final String ACCOUNT_LOCK_KEY = "account-lock-key_";

    //redis中，用户账户被删除，”JWT_REFRESH_KEY + 用户id“
    public static final String DELETED_USER_KEY = "deleted-user-key_";

    public static final String IDENTITY_CACHE_KEY= "com.demo.myshiro.SimpleAuthorizationInfo_";




}
