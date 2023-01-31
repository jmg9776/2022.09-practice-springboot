package com.sandbox.server.repository;

import com.sandbox.server.model.entity.Ip;

import java.util.Optional;

public interface IpRepository extends BaseRepository<Ip, Long>{
    Optional<Ip> findByIp(String ip);
}
