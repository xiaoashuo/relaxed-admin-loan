package com.relaxed.boot.loan.util.seal;
import lombok.Builder;
import lombok.Data;
/**
 * @author Yakir
 * @Topic SealCircle
 * @Description
 * @date 2022/12/13 9:55
 * @Version 1.0
 */
@Builder
@Data
public class SealCircle {
    private Integer line;
    private Integer width;
    private Integer height;
}
