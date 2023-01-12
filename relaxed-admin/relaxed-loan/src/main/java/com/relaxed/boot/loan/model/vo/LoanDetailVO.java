package com.relaxed.boot.loan.model.vo;

import com.relaxed.boot.loan.model.entity.Loan;
import com.relaxed.boot.loan.model.entity.OrderCustomer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Yakir
 * @Topic LoanDetailVO
 * @Description
 * @date 2023/1/11 15:25
 * @Version 1.0
 */
@Schema(title = "借据详情")
@Data
public class LoanDetailVO {
    private Loan loan;
    private OrderCustomer orderCustomer;
}
