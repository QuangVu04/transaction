package com.hottea.ewallet.transaction.service;

import com.hottea.ewallet.transaction.dto.Request.DashboardStatRequest;
import com.hottea.ewallet.transaction.dto.Respond.DashboardStatRespond;
import com.hottea.ewallet.transaction.dto.Respond.ResponseData;
import com.hottea.ewallet.transaction.dto.Respond.TransactionStatSendRespond;

import java.util.List;

public interface TransactionDashboardService {
    ResponseData<DashboardStatRespond> getDashboardMetrics(DashboardStatRequest request);
}
