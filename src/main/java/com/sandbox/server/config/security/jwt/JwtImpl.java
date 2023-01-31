package com.sandbox.server.config.security.jwt;

import com.sandbox.server.model.entity.Account;
import com.sandbox.server.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Profile("jwt")
public class JwtImpl implements UserDetailsService
{
    final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Account account = accountRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("cannot find id"));
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        return new org
                .springframework.security.core.userdetails.User(account.getId(), account.getPw(), grantedAuthorities);
    }
}
