package bbudano.bitcoinrpcclient.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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
        Map<String, Object> body = getRequestBody(1L, "getblockcount");
        body.put("params", new ArrayList<>());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, getHeaders());

        return ResponseEntity.status(HttpStatus.OK).body(restTemplate.postForObject(url, request, Object.class));
    }

    public ResponseEntity<Object> getBlockchainInfo() {
        Map<String, Object> body = getRequestBody(1L, "getblockchaininfo");
        body.put("params", new ArrayList<>());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, getHeaders());

        return ResponseEntity.status(HttpStatus.OK).body(restTemplate.postForObject(url, request, Object.class));
    }

    public ResponseEntity<Object> getBlock(final String blockhash, final Integer verbosity) {
        Map<String, Object> body = getRequestBody(1L, "getblock");

        Map<String, Object> params = new HashMap<>();
        params.put("blockhash", blockhash);
        if (verbosity != null) {
            params.put("verbosity", verbosity);
        }
        body.put("params", params);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, getHeaders());

        return ResponseEntity.status(HttpStatus.OK).body(restTemplate.postForObject(url, request, Object.class));
    }

    public ResponseEntity<Object> createWallet(final String walletName, final Boolean disablePrivateKeys,
                                               final Boolean blank, final String passphrase, final Boolean avoidReuse,
                                               final Boolean descriptors, final Boolean loadOnStartup) {
        Map<String, Object> body = getRequestBody(1L, "createwallet");

        Map<String, Object> params = new HashMap<>();
        params.put("wallet_name", walletName);
        params.put("disable_private_keys", disablePrivateKeys);
        params.put("blank", blank);
        params.put("passphrase", passphrase);
        params.put("avoid_reuse", avoidReuse);
        params.put("descriptors", descriptors);
        params.put("load_on_startup", loadOnStartup);
        body.put("params", params);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, getHeaders());

        return ResponseEntity.status(HttpStatus.OK).body(restTemplate.postForObject(url, request, Object.class));
    }

    public ResponseEntity<Object> getNewAddress(final String label, final String addressType) {
        Map<String, Object> body = getRequestBody(1L, "getnewaddress");

        Map<String, Object> params = new HashMap<>();
        if (label != null) {
            params.put("label", label);
        }
        if (addressType != null) {
            params.put("address_type", addressType);
        }
        body.put("params", params);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, getHeaders());

        return ResponseEntity.status(HttpStatus.OK).body(restTemplate.postForObject(url, request, Object.class));
    }

    public ResponseEntity<Object> getAddressInfo(final String address) {
        Map<String, Object> body = getRequestBody(1L, "getaddressinfo");

        Map<String, Object> params = new HashMap<>();
        params.put("address", address);
        body.put("params", params);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, getHeaders());

        return ResponseEntity.status(HttpStatus.OK).body(restTemplate.postForObject(url, request, Object.class));
    }

    public ResponseEntity<Object> getBalances() {
        Map<String, Object> body = getRequestBody(1L, "getbalances");
        body.put("params", new ArrayList<>());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, getHeaders());

        return ResponseEntity.status(HttpStatus.OK).body(restTemplate.postForObject(url, request, Object.class));
    }

    public ResponseEntity<Object> getRpcInfo() {
        Map<String, Object> body = getRequestBody(1L, "getrpcinfo");
        body.put("params", new ArrayList<>());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, getHeaders());

        return ResponseEntity.status(HttpStatus.OK).body(restTemplate.postForObject(url, request, Object.class));
    }

    // helper methods

    public Map<String, Object> getRequestBody(final long id, final String method) {
        Map<String, Object> body = new HashMap<>();
        body.put("jsonrpc", "2.0");
        body.put("id", id);
        body.put("method", method);
        return body;
    }

    public HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(username, password);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

}
