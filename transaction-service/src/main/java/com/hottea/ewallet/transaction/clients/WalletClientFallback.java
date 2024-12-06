package com.hottea.ewallet.transaction.clients;

import com.hottea.ewallet.transaction.dto.Request.WalletRequest;
import com.hottea.ewallet.transaction.dto.Respond.WalletInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class WalletClientFallback implements WalletClient {
    @Override
    public void walletDeposit(WalletRequest request) {
        throw new RuntimeException("Wallet service is unavailable for deposit");
    }

    @Override
    public WalletInfo getBalanceById(@PathVariable("id") String walletId){
        throw new RuntimeException("cannot get id");
    }

    @Override
    public void walletWithdraw(WalletRequest request) {
        throw new RuntimeException("Wallet service is unavailable for withdraw");
    }
}