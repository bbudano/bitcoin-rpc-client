package bbudano.bitcoinrpcclient.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class BitcoinRpcService {

    @Value("${bitcoin.rpc.username}")
    private String username;

    @Value("${bitcoin.rpc.password}")
    private String password;

    @Value("${bitcoin.rpc.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<Object> getBlockCount() {
        Map<String, Object> body = new HashMap<>();
        body.put("jsonrpc", "2.0");
        body.put("id", 1);
        body.put("method", "getblockcount");
        body.put("params", new ArrayList<>());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, getHeaders());

        return ResponseEntity.status(HttpStatus.OK).body(restTemplate.postForObject(url, request, Object.class));
    }

    public HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(username, password);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

}
