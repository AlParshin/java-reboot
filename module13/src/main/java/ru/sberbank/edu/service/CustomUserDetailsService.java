package ru.sberbank.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UserCache userCache;

    @Autowired
    public void setUserCache(UserCache userCache) {
        this.userCache = userCache;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo info = userCache.get(username);

        if(info == null) {
            throw new UsernameNotFoundException("User name= " + username + " not found");
        }

        Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
        return new User(info.getLogin(), info.getPassword(), authorities);
    }
}
