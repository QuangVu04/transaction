package com.hottea.ewallet.transaction.clients;


import com.hottea.ewallet.transaction.common.config.FeignConfig;
import com.hottea.ewallet.transaction.dto.Request.WalletRequest;
import com.hottea.ewallet.transaction.dto.Respond.WalletInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "wallet-service", url = "http://localhost:8083/api/v1/users/wallet", configuration = FeignConfig.class)
public interface WalletClient {
    @GetMapping("/{id}")
    WalletInfo getBalanceById(@PathVariable("id") String walletId);

    @PutMapping("/deposit")
    void walletDeposit(@RequestBody WalletRequest request);

    @PutMapping("/withdraw")
    void walletWithdraw(@RequestBody WalletRequest request);

    
}
