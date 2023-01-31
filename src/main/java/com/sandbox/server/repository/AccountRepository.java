package com.sandbox.server.repository;

import com.sandbox.server.model.entity.Account;

import java.util.Optional;

public interface AccountRepository  extends BaseRepository<Account, Long>{
    Optional<Account> findById(String id);
}
