package com.sandbox.server.service.persist;

import com.sandbox.server.exception.SandBoxServerForbiddenException;
import com.sandbox.server.model.entity.Account;
import com.sandbox.server.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public Account sign(Account account) {
        Optional<Account> accountOptional = accountRepository.findById(account.getId());
        if(accountOptional.isEmpty()){
            String password = account.getPw();
            account.setPw(bCryptPasswordEncoder.encode(password));
            return accountRepository.save(account);
        }
        else {
            throw new SandBoxServerForbiddenException(account.getId() + " is already exist");
        }
    }

    public Account withdrawal(Account account) {
        Optional<Account> accountOptional = accountRepository.findById(account.getUid());
        if(accountOptional.isEmpty()){
            throw new SandBoxServerForbiddenException(account.getUid() + " is not exist");
        }
        else {
            accountOptional.get().setCash(
                    accountOptional.get().getCash()+account.getCash());
            return accountRepository.save(accountOptional.get());
        }
    }

    public Account getUid(Long uid) {
        Optional<Account> accountOptional = accountRepository.findById(uid);
        if (accountOptional.isEmpty())
            throw new SandBoxServerForbiddenException(uid + "is not exist");
        return accountOptional.get();
    }

    public Account login(String id, String pw) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if(accountOptional.isEmpty()){
            return new Account();
        }
        else {
            Account account = accountOptional.get();
            return account;
        }
    }
}
