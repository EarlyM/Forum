package ua.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.forum.model.Authorities;
import ua.forum.model.User;
import ua.forum.service.AccountService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAuthenticationService implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = accountService.findUser(username);
        if(user == null){
            throw new UsernameNotFoundException("User " + username + "was not found");
        }

        List<GrantedAuthority> grantList = new ArrayList<>();

        List<Authorities> authoritiesList = user.getGroup().getAuthorities();

        for(Authorities authorities : authoritiesList){
            grantList.add(new SimpleGrantedAuthority(authorities.getAuthorities()));
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantList);

        return userDetails;
    }
}
