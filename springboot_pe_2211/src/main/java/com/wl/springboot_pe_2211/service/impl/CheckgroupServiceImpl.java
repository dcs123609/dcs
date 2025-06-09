package com.wl.springboot_pe_2211.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wl.springboot_pe_2211.entity.Checkgroup;
import com.wl.springboot_pe_2211.entity.CheckgroupCheckitem;
import com.wl.springboot_pe_2211.entity.SetmealCheckgroup;
import com.wl.springboot_pe_2211.exception.GlobalExceptionEnum;
import com.wl.springboot_pe_2211.exception.MyGlobalException;
import com.wl.springboot_pe_2211.mapper.CheckgroupCheckitemMapper;
import com.wl.springboot_pe_2211.mapper.CheckgroupMapper;
import com.wl.springboot_pe_2211.mapper.SetmealCheckgroupMapper;
import com.wl.springboot_pe_2211.service.ICheckgroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wl.springboot_pe_2211.utils.PageResult;
import com.wl.springboot_pe_2211.utils.QueryPageBean;
import com.wl.springboot_pe_2211.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

import static com.wl.springboot_pe_2211.utils.MessageConstant.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-07
 */
@Service
public class CheckgroupServiceImpl extends ServiceImpl<CheckgroupMapper, Checkgroup> implements ICheckgroupService {

    @Resource
    private CheckgroupMapper checkgroupMapper;
    @Resource
    private CheckgroupCheckitemMapper checkgroupCheckitemMapper;
    @Resource
    private SetmealCheckgroupMapper setmealCheckgroupMapper;

    @Override
    public R addCheckGroup(Checkgroup checkgroup, Integer[] checkitemIds) {

        //1. 将checkgroup添加到t_checkgroup表中  拿到该条数据的主键
        checkgroupMapper.insert(checkgroup);
        Integer checkGroupId = checkgroup.getId();//拿到返回数据的主键
        //2.遍历checkitemIds  添加到中间表中
        if (checkitemIds == null || checkitemIds.length == 0) {
            return R.success(ADD_CHECKGROUP_SUCCESS);
        }

        for (Integer checkitemId : checkitemIds) {
            CheckgroupCheckitem checkgroupCheckitem = new CheckgroupCheckitem();
            checkgroupCheckitem.setCheckgroupId(checkGroupId);
            checkgroupCheckitem.setCheckitemId(checkitemId);
            checkgroupCheckitemMapper.insert(checkgroupCheckitem);
        }

        return R.success(ADD_CHECKGROUP_SUCCESS);
    }

    @Override
    public R<PageResult<Checkgroup>> findPage(QueryPageBean queryPageBean) {

        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        //构建分页对象
        IPage<Checkgroup> iPage = new Page<>(currentPage,pageSize);
        LambdaQueryWrapper<Checkgroup> queryWrapper = new LambdaQueryWrapper<>();
        //如果传入了条件  拼接条件
        if (queryString != null && queryString.trim().length() > 0) {

            queryWrapper.like(Checkgroup::getCode,queryString)
                    .or()
                    .like(Checkgroup::getName,queryString)
                    .or()
                    .like(Checkgroup::getHelpcode,queryString);
        }
        iPage = checkgroupMapper.selectPage(iPage,queryWrapper);
        //拿数据
        List<Checkgroup> records = iPage.getRecords();
        //拿总条数
        long total = iPage.getTotal();
        PageResult<Checkgroup> pageResult = new PageResult<>();
        pageResult.setRows(records);
        pageResult.setTotal(total);

        return R.success(QUERY_CHECKGROUP_SUCCESS,pageResult);
    }


    @Transactional //处理事务
    @Override
    public R deleteById(Integer id) {
        //1、删除中间表t_checkgroup_checkitem的数据
        LambdaQueryWrapper<CheckgroupCheckitem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CheckgroupCheckitem::getCheckgroupId,id);
        checkgroupCheckitemMapper.delete(queryWrapper);


        //2、删除中间表t_setmeal_checkgroup的数据
        LambdaQueryWrapper<SetmealCheckgroup> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(SetmealCheckgroup::getCheckgroupId,id);
        setmealCheckgroupMapper.delete(queryWrapper1);

        //3、删除checkgroup表中的数据
        checkgroupMapper.deleteById(id);

        return R.success(DELETE_CHECKGROUP_SUCCESS);
    }

    @Transactional
    @Override
    public R updateCheckGroup(Checkgroup checkgroup, Integer[] checkitemIds) {

        //参数非空判断
        if (checkgroup == null || checkgroup.getId() == null) {
            throw new MyGlobalException(GlobalExceptionEnum.PARAMS_ERROR);
        }
        //1.修改checkgroup表中数据
        checkgroupMapper.updateById(checkgroup);
        //2.删除中间表数据
        LambdaQueryWrapper<CheckgroupCheckitem> queryWrapper
                = new LambdaQueryWrapper<>();
        Integer checkgroupId = checkgroup.getId();
        queryWrapper.eq(CheckgroupCheckitem::getCheckgroupId,checkgroupId);
        checkgroupCheckitemMapper.delete(queryWrapper);

        //3.添加中间表
        for (Integer checkitemId : checkitemIds) {
            CheckgroupCheckitem checkgroupCheckitem = new CheckgroupCheckitem();
            checkgroupCheckitem.setCheckgroupId(checkgroupId);
            checkgroupCheckitem.setCheckitemId(checkitemId);
            checkgroupCheckitemMapper.insert(checkgroupCheckitem);
        }

        /*Arrays.stream(checkitemIds)
                .forEach(checkitemId -> checkgroupCheckitemMapper
                                .insert(new CheckgroupCheckitem(checkgroupId,checkitemId)));*/




        return R.success(EDIT_CHECKGROUP_SUCCESS);
    }

    @Override
    public R findAllCheckGroup() {
        List<Checkgroup> checkgroupList = checkgroupMapper.selectList(null);
        return R.success(null,checkgroupList);
    }
}
