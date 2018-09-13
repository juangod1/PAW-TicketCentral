package ar.edu.itba.paw2018b.webapp.auth;

import ar.edu.itba.paw2018b.interfaces.service.UserService;
import ar.edu.itba.paw2018b.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

@Component
public class AtlasUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService us;
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = us.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username+"not found"));

        final Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);

    }
}
