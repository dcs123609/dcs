package com.wl.springboot_pe_2211.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wl.springboot_pe_2211.entity.CheckgroupCheckitem;
import com.wl.springboot_pe_2211.mapper.CheckgroupCheckitemMapper;
import com.wl.springboot_pe_2211.service.ICheckgroupCheckitemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wl.springboot_pe_2211.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-07
 */
@Service
public class CheckgroupCheckitemServiceImpl extends ServiceImpl<CheckgroupCheckitemMapper, CheckgroupCheckitem> implements ICheckgroupCheckitemService {

    @Resource
    private CheckgroupCheckitemMapper checkgroupCheckitemMapper;


    @Override
    public R<List<Integer>> findCheckItemIdByCheckGroupId(Integer id) {
        LambdaQueryWrapper<CheckgroupCheckitem> queryWrapper
                = new LambdaQueryWrapper<>();
        queryWrapper.select(CheckgroupCheckitem::getCheckitemId);
        queryWrapper.eq(CheckgroupCheckitem::getCheckgroupId,id);
        List<CheckgroupCheckitem> checkgroupCheckitemList
                = checkgroupCheckitemMapper.selectList(queryWrapper);

        /*List<Integer> checkItemIdList = new ArrayList<>();
        for (CheckgroupCheckitem checkgroupCheckitem : checkgroupCheckitemList) {
            checkItemIdList.add(checkgroupCheckitem.getCheckitemId());
        }*/
        List<Integer> collect = checkgroupCheckitemList.stream()
                .map(CheckgroupCheckitem::getCheckitemId)
                .collect(Collectors.toList());



        return R.success(null,collect);
    }
}
