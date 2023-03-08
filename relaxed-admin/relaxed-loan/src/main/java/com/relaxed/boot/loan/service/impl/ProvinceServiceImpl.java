package com.relaxed.boot.loan.service.impl;

import com.relaxed.boot.loan.model.entity.Province;
import com.relaxed.boot.loan.model.vo.ProvincePageVO;
import com.relaxed.boot.loan.model.qo.ProvinceQO;
import com.relaxed.boot.loan.mapper.ProvinceMapper;
import com.relaxed.boot.loan.service.ProvinceService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.domain.SelectData;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 省市区码表
 *
 * @author Yakir 2023-03-08 10:44:09
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ProvinceServiceImpl extends ExtendServiceImpl<ProvinceMapper, Province> implements ProvinceService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<ProvincePageVO> 分页数据
    */
    @Override
    public PageResult<ProvincePageVO> queryPage(PageParam pageParam, ProvinceQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

    @Override
    public List<SelectData> listByParentCode(Integer parentCode) {
        List<Province> provinceList = baseMapper.selectList(WrappersX.lambdaQueryX(Province.class)
                .eq(Province::getParentCode, parentCode));
        List<SelectData> selectDataList=new ArrayList<>(provinceList.size());
        for (Province province : provinceList) {
            SelectData selectData = new SelectData();
            selectData.setLabel(province.getRegionName());
            selectData.setValue(province.getRegionCode());
            selectData.setType(province.getRegionLevel()+"");
            selectDataList.add(selectData);
        }
        return selectDataList ;
    }

    @Override
    public Map<String, Province> mapByRegionCodes(List<String> regionCodes) {
        List<Province> provinces = baseMapper.selectList(WrappersX.lambdaQueryX(Province.class)
                .in(Province::getRegionCode, regionCodes));
        Map<String,Province> provinceMap=new HashMap<>();
        provinces.forEach(province -> {
            provinceMap.put(province.getRegionCode(),province);
        });
        return provinceMap;
    }
}
