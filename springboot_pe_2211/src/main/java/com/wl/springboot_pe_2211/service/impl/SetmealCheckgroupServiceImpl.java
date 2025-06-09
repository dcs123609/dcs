package com.wl.springboot_pe_2211.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wl.springboot_pe_2211.entity.SetmealCheckgroup;
import com.wl.springboot_pe_2211.mapper.SetmealCheckgroupMapper;
import com.wl.springboot_pe_2211.service.ISetmealCheckgroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wl.springboot_pe_2211.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-14
 */
@Service
public class SetmealCheckgroupServiceImpl extends ServiceImpl<SetmealCheckgroupMapper, SetmealCheckgroup> implements ISetmealCheckgroupService {

    @Resource
    private SetmealCheckgroupMapper setmealCheckgroupMapper;

    @Override
    public R findCheckGroupBySetMealId(Integer id) {
        LambdaQueryWrapper<SetmealCheckgroup> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(SetmealCheckgroup::getCheckgroupId);
        queryWrapper.eq(SetmealCheckgroup::getSetmealId,id);
        List<Object> objects = setmealCheckgroupMapper.selectObjs(queryWrapper);
        List<Integer> collect = objects.stream().map(obj -> (Integer) obj).collect(Collectors.toList());

        return R.success(null,collect);
    }
}
