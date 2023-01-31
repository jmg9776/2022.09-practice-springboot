package com.sandbox.server.config.security.Auth;

import com.sandbox.server.model.entity.Account;
import com.sandbox.server.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {
    final AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Account account = accountRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("cannot find id"));
        return new AuthDetails(account);
    }
}
