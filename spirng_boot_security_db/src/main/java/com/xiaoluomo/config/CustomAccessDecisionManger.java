package com.xiaoluomo.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAccessDecisionManger implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication,
                       Object object, Collection<ConfigAttribute> ca)
            throws AccessDeniedException, InsufficientAuthenticationException {
        Collection<? extends GrantedAuthority> auths = authentication.getAuthorities();

        for(ConfigAttribute configAttribute:ca){
            if("ROLE_LOGIN".equals(configAttribute.getAttribute())){
                return;
            }
            for(GrantedAuthority auth:auths){
                if(configAttribute.getAttribute().equals(auth.getAuthority())){
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足");

    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
