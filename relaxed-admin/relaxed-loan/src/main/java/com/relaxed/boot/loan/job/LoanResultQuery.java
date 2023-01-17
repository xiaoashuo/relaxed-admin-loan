package com.relaxed.boot.loan.job;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.loan.enums.LoanEnum;
import com.relaxed.boot.loan.enums.OrderEnum;
import com.relaxed.boot.loan.enums.TradeStatusEnum;
import com.relaxed.boot.loan.manage.PayManage;
import com.relaxed.boot.loan.model.entity.Loan;
import com.relaxed.boot.loan.model.entity.Trade;
import com.relaxed.boot.loan.service.LoanService;
import com.relaxed.boot.loan.service.TradeService;
import com.relaxed.boot.loan.util.LogFormatUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Scheduled(fixedRate = 60*1000)
    public void loanResultQuery(){
        log.info(LogFormatUtil.format("放款结果查询","开始",LocalDateTime.now()));
        List<Trade> tradeList = tradeService.list(Wrappers.lambdaQuery(Trade.class).in(Trade::getTradeStatus,
                TradeStatusEnum.CREATE.getCode(), TradeStatusEnum.WAIT_PAY.getCode()));
        if (CollectionUtil.isEmpty(tradeList)){
            log.info(LogFormatUtil.format("放款结果查询","效验",LocalDateTime.now(),"待查数据为空"));
            return;
        }
        for (Trade trade : tradeList) {
            String partnerBizNo = trade.getPartnerBizNo();
            BigDecimal splitLine = BigDecimal.valueOf(2500);
            BigDecimal tradeAmount = trade.getTradeAmount();
            try {
                Loan loan = loanService.getOne(Wrappers.lambdaQuery(Loan.class).eq(Loan::getPartnerBizNo, partnerBizNo)
                        .eq(Loan::getTradeId, trade.getTradeId()));
                if (splitLine.compareTo(tradeAmount)<=0){
                    payManage.loadSuccessHandle(trade,loan);
                }else{
                    //小于等于1200 失败
                    payManage.loanFailHandle(trade,loan);
                }
            } catch (Exception e) {
                log.error(LogFormatUtil.format("放款结果查询","异常",LocalDateTime.now(), StrUtil.format("订单{}",partnerBizNo)),e);
            }
        }
        log.info(LogFormatUtil.format("放款结果查询","结束",LocalDateTime.now()));

    }
}
