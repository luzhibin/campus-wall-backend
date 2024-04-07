package com.sqx.modules.sys.oauth2;

import com.sqx.modules.sys.entity.SysUserEntity;
import com.sqx.modules.sys.entity.SysUserTokenEntity;
import com.sqx.modules.sys.service.ShiroService;
import com.sqx.modules.user.entity.UserEntity;
import com.sqx.modules.user.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 认证
 *
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUserEntity user = (SysUserEntity)principals.getPrimaryPrincipal();
        Long userId = user.getUserId();

        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();
        System.out.println("accessToken: ===========================");
        System.out.println(accessToken);
        //根据accessToken，查询用户信息
        SysUserTokenEntity tokenEntity = shiroService.queryByToken(accessToken);
        //token失效
        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

        //查询用户信息
        UserEntity userEntity = userService.getUserById(tokenEntity.getUserId());
        //SysUserEntity user = shiroService.queryUser(tokenEntity.getUserId());

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userEntity, accessToken, getName());
        return info;
    }
}
