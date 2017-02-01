package org.lifebook.configuration;

import org.lifebook.models.User;
import org.lifebook.models.UserRole;
import org.lifebook.repositories.UserRepository;
import org.lifebook.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monju on 01-Feb-17.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        List<UserRole> userRoles = userRoleRepository.findByUser(user);
        List<String> roles = new ArrayList<>();
        for(UserRole role: userRoles){
            roles.add(role.getRole().getRoleName());
        }

        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(String.join(",", roles)));
    }
}
