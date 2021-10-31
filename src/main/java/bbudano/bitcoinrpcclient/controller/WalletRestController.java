package bbudano.bitcoinrpcclient.controller;

import bbudano.bitcoinrpcclient.service.BitcoinRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/wallet")
public class WalletRestController {

    @Autowired
    private BitcoinRpcService bitcoinRpcService;

    @GetMapping("/create-wallet")
    public ResponseEntity<Object> createWallet(
            final @RequestParam(name = "walletName") String walletName,
            final @RequestParam(name = "disablePrivateKeys", required = false, defaultValue = "false") Boolean disablePrivateKeys,
            final @RequestParam(name = "blank", required = false, defaultValue = "false") Boolean blank,
            final @RequestParam(name = "passphrase") String passphrase,
            final @RequestParam(name = "avoidReuse", required = false, defaultValue = "false") Boolean avoidReuse,
            final @RequestParam(name = "descriptors", required = false, defaultValue = "false") Boolean descriptors,
            final @RequestParam(name = "loadOnStartup", required = false) Boolean loadOnStartup) {
        return bitcoinRpcService.createWallet(walletName, disablePrivateKeys, blank, passphrase, avoidReuse,
                descriptors, loadOnStartup);
    }

    @GetMapping("/get-new-address")
    public ResponseEntity<Object> getNewAddress(
            final @RequestParam(name = "label", required = false) String label,
            final @RequestParam(name = "addressType", required = false) String addressType) {
        return bitcoinRpcService.getNewAddress(label, addressType);
    }

    @GetMapping("/get-address-info")
    public ResponseEntity<Object> getAddressInfo(final @RequestParam(name = "address") String address) {
        return bitcoinRpcService.getAddressInfo(address);
    }

    @GetMapping("/get-balances")
    public ResponseEntity<Object> getBalances() {
        return bitcoinRpcService.getBalances();
    }

}
