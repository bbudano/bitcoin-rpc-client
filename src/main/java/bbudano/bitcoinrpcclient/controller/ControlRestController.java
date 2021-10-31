package bbudano.bitcoinrpcclient.controller;

import bbudano.bitcoinrpcclient.service.BitcoinRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/control")
public class ControlRestController {

    @Autowired
    private BitcoinRpcService bitcoinRpcService;

    @GetMapping("/get-rpc-info")
    public ResponseEntity<Object> getRpcInfo() {
        return bitcoinRpcService.getRpcInfo();
    }

}
