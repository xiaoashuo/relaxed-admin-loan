package com.relaxed.boot.loan.manage;

import com.relaxed.boot.loan.enums.FileTypeEnum;
import com.relaxed.boot.loan.job.StampHandleJob;
import com.relaxed.boot.loan.model.entity.Loan;
import com.relaxed.boot.loan.service.LoanService;
import com.relaxed.boot.web.RelaxedWebApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Yakir
 * @Topic PayManageTest
 * @Description
 * @date 2023/1/28 17:04
 * @Version 1.0
 */
@SpringBootTest(classes = RelaxedWebApplication.class)
class PayManageTest {

    @Autowired
    private PayManage payManage;
    @Autowired
    private LoanService loanService;

    @Autowired
    private StampHandleJob stampHandleJob;

    private StampManage stampManage;
    @Test
    void generateRelatedFile() {
        Loan loan = loanService.getById(13);
        stampManage.generateRelatedFile(loan.getPartnerBizNo(), FileTypeEnum.A9);
    }

    @Test
    void generateSettleFile() {
        stampHandleJob.stampHandleJob();
    }
}
