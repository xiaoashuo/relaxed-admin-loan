package com.relaxed.boot.loan.model.bo;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yakir
 * @Topic RiskDto
 * @Description
 * @date 2023/3/14 13:38
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class RiskReqBO {

    /**
     * 模板id
     */
    private String guid;

    /**
     * 请求id 唯一标识
     */
    private String reqId;

    /**
     * 请求信息
     */
    private Map<String,Object> jsonInfo;


    public RiskReqBO put(String key,Object value){
        if (jsonInfo == null){
            jsonInfo=new HashMap<>(6);
        }
        jsonInfo.put(key,value);

        return this;
    }


    public String toJson() {
        return JSONUtil.toJsonStr(this);
    }
}
