// package com.relaxed.boot.risk.core.plugins.handler;
//
// import cn.hutool.core.util.StrUtil;
// import com.relaxed.common.core.util.SpringUtils;
// import com.relaxed.common.risk.engine.core.plugins.PluginEnum;
// import com.relaxed.common.risk.engine.core.plugins.PluginMeta;
// import com.relaxed.common.risk.engine.core.plugins.PluginService;
// import com.relaxed.common.risk.engine.service.MobileInfoManageService;
// import com.relaxed.common.risk.model.entity.Location;
// import com.relaxed.common.risk.model.vo.MobileInfoVO;
// import com.relaxed.common.risk.model.vo.PreItemVO;
// import lombok.extern.slf4j.Slf4j;
//
// import java.util.Map;
//
/// **
// * @author Yakir
// * @Topic IpLocationPlugin
// * @Description MobileLocationPlugin
// * @date 2021/8/29 14:29
// * @Version 1.0
// */
// @Slf4j
// public class MobileLocationPlugin implements PluginService {
//
// private final MobileInfoManageService mobileInfoManageService =
// SpringUtils.getBean(MobileInfoManageService.class);
//
// @Override
// public PluginMeta pluginMeta() {
// return PluginEnum.MOBILE_LOCATION_PLUGIN;
// }
//
// @Override
// public String pluginName() {
// return "MOBILE2LOCATION";
// }
//
// @Override
// public Object handle(PreItemVO preItemVO, Map<String, Object> jsonInfo, String[]
// sourceFields) {
// String mobile = jsonInfo.get(sourceFields[0]).toString();
// if (StrUtil.isNotEmpty(mobile) && mobile.length() == 11) {
// mobile = mobile.substring(0, 7);
// }
// MobileInfoVO vo = mobileInfoManageService.getByMobile(mobile);
// Location location = new Location();
// if (vo != null) {
// location.setProvince(vo.getProvince());
// location.setCity(vo.getCity());
// location.setCountry("中国");
// }
// return location;
// }
//
// }
