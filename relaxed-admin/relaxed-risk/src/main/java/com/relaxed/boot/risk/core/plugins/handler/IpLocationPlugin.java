//package com.relaxed.boot.risk.core.plugins.handler;
//
//import com.relaxed.common.core.util.SpringUtils;
//import com.relaxed.common.ip.IpSearcher;
//import com.relaxed.common.risk.engine.core.plugins.PluginEnum;
//import com.relaxed.common.risk.engine.core.plugins.PluginMeta;
//import com.relaxed.common.risk.engine.core.plugins.PluginService;
//import com.relaxed.common.risk.model.entity.Location;
//import com.relaxed.common.risk.model.vo.PreItemVO;
//import lombok.extern.slf4j.Slf4j;
//import org.lionsoul.ip2region.DataBlock;
//import org.lionsoul.ip2region.Util;
//
//import java.util.Map;
//
///**
// * @author Yakir
// * @Topic IpLocationPlugin
// * @Description IpLocationPlugin
// * @date 2021/8/29 14:29
// * @Version 1.0
// */
//@Slf4j
//public class IpLocationPlugin implements PluginService {
//
//	private final IpSearcher ipSearcher = SpringUtils.getBean(IpSearcher.class);
//
//	@Override
//	public PluginMeta pluginMeta() {
//		return PluginEnum.IP_LOCATION_PLUGIN;
//	}
//
//	@Override
//	public String pluginName() {
//		return "IP2LOCATION";
//	}
//
//	@Override
//	public Object handle(PreItemVO preItemVO, Map<String, Object> jsonInfo, String[] sourceFields) {
//		Location location;
//		String ip = jsonInfo.get(sourceFields[0]).toString();
//		if (!Util.isIpAddress(ip)) {
//			return null;
//		}
//		try {
//			DataBlock block = ipSearcher.getDelegate().memorySearch(ip);
//			String[] detail = block.getRegion().split("\\|");
//			location = new Location();
//			location.setCountry(detail[0]);
//			location.setRegion(detail[1]);
//			location.setProvince(detail[2]);
//			location.setCity(detail[3]);
//			location.setAddress(detail[4]);
//		}
//		catch (Exception e) {
//			location = new Location();
//			log.error("ip2region error", e);
//		}
//		return location;
//	}
//
//}
