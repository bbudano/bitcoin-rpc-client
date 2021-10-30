package bbudano.bitcoinrpcclient.controller;

import bbudano.bitcoinrpcclient.service.BitcoinRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/bitcoin-rpc")
public class BitcoinRpcRestController {

    @Autowired
    private BitcoinRpcService bitcoinRpcService;

    @GetMapping("/block-count")
    public ResponseEntity<Object> getBlockCount() {
        return bitcoinRpcService.getBlockCount();
    }

}
