package com.salvatore.cinemates.services;

import com.salvatore.cinemates.dao.CinematesUserRepository;
import com.salvatore.cinemates.model.CinematesUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private CinematesUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CinematesUser> user = repository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.get().getUsername(), user.get().getPassword(), Collections.emptyList());
    }
}
