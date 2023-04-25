package com.relaxed.boot.loan.manage;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.relaxed.boot.loan.enums.FileTypeEnum;
import com.relaxed.boot.loan.job.StampHandleJob;
import com.relaxed.boot.loan.model.bo.RiskReqBO;
import com.relaxed.boot.loan.model.bo.RiskResBO;
import com.relaxed.boot.loan.model.entity.Loan;
import com.relaxed.boot.loan.service.LoanService;
import com.relaxed.boot.web.RelaxedWebApplication;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

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

	@Test
	void testRisk() {

		String body = new RiskReqBO().setGuid("D8A0B53B276E4071AF73145212DDE940")
				.setReqId(IdUtil.getSnowflakeNextIdStr()).put("partnerBizNo", "123456888").put("applyAmount", 1200)
				.put("createdTime", System.currentTimeMillis()).put("realName", "张三丰").put("idCard", "1234555555844")
				.put("created_time", "1605339018000").put("user_id", 4)
				// .put("mobile_no", "13939395454")
				.put("event_id", "13")

				.toJson();
		String response = HttpUtil.post("http://localhost:9401/risk/services/v1/upload", body);
		RiskResBO riskResBO = JSONUtil.toBean(response, RiskResBO.class);
		Integer code = riskResBO.getCode();
		JSONObject data = riskResBO.getData();

		if (code == 200) {
			RiskResBO.Risk risk = data.getByPath("report.activations.login_rule", RiskResBO.Risk.class);
			Map<String, Object> riskRuleMap = data.getByPath("report.activations.hitRuleMap.login_rule", Map.class);
			String ruleList = data.getByPath("report.activations.hitRuleList.login_rule", String.class);
			List<RiskResBO.RiskRule> riskRuleList = JSONUtil.toBean(ruleList,
					new TypeReference<List<RiskResBO.RiskRule>>() {
						@Override
						public Type getType() {
							return super.getType();
						}
					}, false);
			System.out.println(riskResBO);
		}
		else if (code == 20003) {
			// 错误map

		}
		else {
			// 异常message
		}
		System.out.println(riskResBO);
	}

}
