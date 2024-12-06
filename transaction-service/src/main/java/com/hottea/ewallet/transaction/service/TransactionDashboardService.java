package com.hottea.ewallet.transaction.service;

import com.hottea.ewallet.transaction.dto.Request.DashboardStatRequest;
import com.hottea.ewallet.transaction.dto.Respond.DashboardStatRespond;
import com.hottea.ewallet.transaction.dto.Respond.RespondData;
import com.hottea.ewallet.transaction.dto.Respond.TransactionStatSendRespond;

import java.util.List;

public interface TransactionDashboardService {
    RespondData<DashboardStatRespond> getDashboardMetrics(DashboardStatRequest request);

    RespondData<List<TransactionStatSendRespond>> TransactionStatSend(String fromWalletId);

    RespondData<List<TransactionStatSendRespond>> TransactionStatReceived(String fromWalletId);
}
