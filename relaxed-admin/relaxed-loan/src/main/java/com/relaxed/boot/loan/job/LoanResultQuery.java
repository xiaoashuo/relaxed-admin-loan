package com.relaxed.boot.loan.job;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.loan.enums.LoanEnum;
import com.relaxed.boot.loan.enums.OrderEnum;
import com.relaxed.boot.loan.enums.TradeStatusEnum;
import com.relaxed.boot.loan.manage.PayManage;
import com.relaxed.boot.loan.model.entity.Loan;
import com.relaxed.boot.loan.model.entity.Trade;
import com.relaxed.boot.loan.service.LoanService;
import com.relaxed.boot.loan.service.TradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Yakir
 * @Topic LoanResultQuery
 * @Description
 * @date 2023/1/16 18:20
 * @Version 1.0
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class LoanResultQuery {
    private final LoanService loanService;

    private final PayManage payManage;

    private final TradeService tradeService;
    public void loanResultQuery(){
        List<Trade> tradeList = tradeService.list(Wrappers.lambdaQuery(Trade.class).in(Trade::getTradeStatus,
                TradeStatusEnum.CREATE.getCode(), TradeStatusEnum.WAIT_PAY.getCode()));
        if (CollectionUtil.isEmpty(tradeList)){
            log.info("放款结果待查询列表为空");
            return;
        }
        for (Trade trade : tradeList) {
            String partnerBizNo = trade.getPartnerBizNo();
            BigDecimal splitLine = BigDecimal.valueOf(1200);
            BigDecimal tradeAmount = trade.getTradeAmount();
            Loan loan = loanService.getOne(Wrappers.lambdaQuery(Loan.class).eq(Loan::getPartnerBizNo, partnerBizNo)
                    .eq(Loan::getTradeId, trade.getTradeId()));
            if (splitLine.compareTo(tradeAmount)>0){
                payManage.loadSuccessHandle(trade,loan);
            }else{
                //小于等于1200 失败
                payManage.loanFailHandle(trade,loan);
            }
        }
    }
}
