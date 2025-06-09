package com.wl.springboot_pe_2211.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wl.springboot_pe_2211.entity.Checkitem;
import com.wl.springboot_pe_2211.mapper.CheckitemMapper;
import com.wl.springboot_pe_2211.service.ICheckitemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wl.springboot_pe_2211.utils.PageResult;
import com.wl.springboot_pe_2211.utils.QueryPageBean;
import com.wl.springboot_pe_2211.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
public class CheckitemServiceImpl extends ServiceImpl<CheckitemMapper, Checkitem> implements ICheckitemService {

    @Resource
    private CheckitemMapper checkitemMapper;

    @Override
    public R addCheckItem(Checkitem checkitem) {
        int i = checkitemMapper.insert(checkitem);
        if (i > 0) {
            //添加成功
           return  R.success(ADD_CHECKITEM_SUCCESS);
        }
        //添加失败
        return R.err(ADD_CHECKITEM_FAIL);
    }

    @Override
    public R<PageResult<Checkitem>> findPage(QueryPageBean queryPageBean) {
        //1.取出当前页  每页显示的条数  条件
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        //2.创建分页对象  是mp 提供的分页对象
        IPage<Checkitem> iPage = new Page<>(currentPage,pageSize);
        // //select * from t_checkitem where name like ? or code like ?  limit ?,?
        //3.构建条件对象
        LambdaQueryWrapper<Checkitem> queryWrapper = new LambdaQueryWrapper<>();
        //条件不为空  加like   条件为空 不加like

        queryWrapper.like(queryString != null && queryString.trim().length() > 0,
                Checkitem::getName,queryString)
                .or()
                .like(queryString != null && queryString.trim().length() > 0,
                        Checkitem::getCode,queryString);

        //4.执行方法进行分页查询
        iPage = checkitemMapper.selectPage(iPage, queryWrapper);
        //5.从iPage对象中取出总条数和数据
        long total = iPage.getTotal();
        List<Checkitem> records = iPage.getRecords();
        //6.封装PageResult中
        PageResult<Checkitem> pageResult = new PageResult<>(total,records);

        return R.success(QUERY_CHECKITEM_SUCCESS,pageResult);
    }

    @Override
    public R deleteById(Integer id) {

        int i = checkitemMapper.deleteById(id);
        if (i > 0)
            return R.success(DELETE_CHECKITEM_SUCCESS);
        return R.err(DELETE_CHECKITEM_FAIL);
    }

    @Override
    public R updateCheckItem(Checkitem checkitem) {

        int i = checkitemMapper.updateById(checkitem);
        if (i > 0) {
            return R.success(EDIT_CHECKITEM_SUCCESS);
        }
        return R.err(EDIT_CHECKITEM_FAIL);
    }


    @Override
    public R findAll() {
        List<Checkitem> checkitemList = checkitemMapper.selectList(null);
        return R.success(null,checkitemList);
    }
}
