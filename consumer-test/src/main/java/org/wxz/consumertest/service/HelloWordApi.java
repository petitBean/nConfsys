package org.wxz.consumertest.service;

import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("client-test1")
public interface HelloWordApi extends org.wxz.confsysserviceapi.confsystest.HelloWordApi {

}
