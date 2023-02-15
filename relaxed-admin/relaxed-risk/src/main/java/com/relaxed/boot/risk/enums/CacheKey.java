package com.relaxed.boot.risk.enums;

/**
 * @author Yakir
 * @Topic CacheKey
 * @Description
 * @date 2021/8/29 10:29
 * @Version 1.0
 */
public class CacheKey {

	/**
	 * ID 映射model
	 */
	private static final String MODEL_PREFIX = "relaxed:model:";

	/**
	 * 映射model guid-> id
	 */
	private static final String MODEL_GUID_PREFIX = "relaxed:model:guid:";

	/**
	 * model 字段前缀
	 */
	private static final String MODEL_FIELD_PREFIX = "relaxed:model:fields:";

	/**
	 * 预处理字段
	 */
	private static final String MODEL_PRE_ITEMS_PREFIX = "relaxed:model:pre:items:";

	/**
	 * 特征提取
	 */
	private static final String MODEL_ABSTRACTION_PREFIX = "relaxed:model:abstraction:";

	/**
	 * 决策处理
	 */
	private static final String MODEL_ACTIVATION_PREFIX = "relaxed:model:activation:";

	/**
	 * 规则列表缓存
	 */
	private static final String MODEL_RULE_PREFIX = "relaxed:model:rule:";

	/**
	 * 数据列表
	 */
	private static final String MODEL_DATA_LIST_PREFIX = "relaxed:model:data:list:";

	/**
	 * 手机信息map
	 */
	private static final String MOBILE_INFO_MAP_PREFIX = "relaxed:mobile:info:map:";

	/**
	 * 获取model缓存key
	 * @author yakir
	 * @date 2021/8/29 10:30
	 * @param id 维护 model id ->model 本地缓存
	 * @return java.lang.String
	 */
	public static String getModelCacheKey(Long id) {
		return MODEL_PREFIX + id;
	}

	/**
	 * 维护model guid 缓存key
	 * @author yakir
	 * @date 2021/9/6 18:28
	 * @param id
	 * @return java.lang.String
	 */
	public static String getModelGuidCacheKey(String id) {
		return MODEL_GUID_PREFIX + id;
	}

	/**
	 * 获取model对应所有字段缓存
	 * @author yakir
	 * @date 2021/8/29 12:33
	 * @param id 维护model id-> list<FieldVO>
	 * @return java.lang.String
	 */
	public static String getModelFieldCacheKey(Long id) {
		return MODEL_FIELD_PREFIX + id;
	}

	/**
	 * 获取model对应的所有预处理项
	 * @author yakir
	 * @date 2021/8/29 14:10
	 * @param id
	 * @return java.lang.String
	 */
	public static String getModelPreItemCacheKey(Long id) {
		return MODEL_PRE_ITEMS_PREFIX + id;
	}

	/**
	 * 获取model对应的所有特征
	 * @author yakir
	 * @date 2021/8/29 19:21
	 * @param id
	 * @return java.lang.String
	 */
	public static String getModelAbstractionCacheKey(Long id) {
		return MODEL_ABSTRACTION_PREFIX + id;
	}

	/**
	 * 获取决策信息
	 * @author yakir
	 * @date 2021/9/22 16:32
	 * @param id
	 * @return java.lang.String
	 */
	public static String getModelActivationCacheKey(Long id) {
		return MODEL_ACTIVATION_PREFIX + id;
	}

	/**
	 * 查询规则列表缓存key
	 * @author yakir
	 * @date 2021/8/31 11:43
	 * @param id 激活key
	 * @return java.lang.String
	 */
	public static String getRuleListCacheKey(Long id) {
		return MODEL_RULE_PREFIX + id;
	}

	/**
	 * 查询黑白名单数据列表缓存key
	 * @author yakir
	 * @date 2021/8/31 17:22
	 * @param id
	 * @return java.lang.String
	 */
	public static String getDataListCacheKey(Long id) {
		return MODEL_DATA_LIST_PREFIX + id;
	}

	/**
	 * 获取mobile info map 缓存key
	 * @author yakir
	 * @date 2021/9/7 10:06
	 * @param id
	 * @return java.lang.String
	 */
	public static String getMobileInfoMapCacheKey(String id) {
		return MOBILE_INFO_MAP_PREFIX + id;
	}

}
