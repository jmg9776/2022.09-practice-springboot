package com.sandbox.server.api.thirdParty;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ip", url="http://ip-api.com")
public interface IpAPI {
    @GetMapping(value = "/json/{ip}")
    Object sendMessage(@PathVariable("ip") String ip);
}
