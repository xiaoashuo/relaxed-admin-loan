package com.relaxed.boot.loan.enums;

import com.relaxed.common.core.exception.BusinessException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Yakir
 * @Topic SealEnum
 * @Description
 * @date 2022/12/15 12:55
 * @Version 1.0
 */
@Getter
public class SealEnum {

    /**
     * 授权类型
     */
    @RequiredArgsConstructor
    @Getter
    public enum SEAL_SOURCE{
        SYSTEM(1,"系统生成"),
        CUSTOM(2,"自定义"),

        ;
        private final Integer val;
        private final String desc;
    }

    /**
     * seal类型
     */
    @RequiredArgsConstructor
    @Getter
    public enum SEAL_TYPE{
        OFFICIAL(1,"公章"),




        ;
        private final Integer val;
        private final String desc;


    }

}
