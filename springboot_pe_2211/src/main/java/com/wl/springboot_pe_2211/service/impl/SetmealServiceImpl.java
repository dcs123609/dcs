package com.wl.springboot_pe_2211.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wl.springboot_pe_2211.entity.*;
import com.wl.springboot_pe_2211.mapper.*;
import com.wl.springboot_pe_2211.service.ISetmealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wl.springboot_pe_2211.utils.MessageConstant;
import com.wl.springboot_pe_2211.utils.PageResult;
import com.wl.springboot_pe_2211.utils.QueryPageBean;
import com.wl.springboot_pe_2211.utils.R;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wl.springboot_pe_2211.utils.CommonConstant.SET_MEAL_UPLOAD_DB_PIC_SET_NAME;
import static com.wl.springboot_pe_2211.utils.MessageConstant.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-14
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements ISetmealService {

    @Resource
    private SetmealMapper setmealMapper;
    @Resource
    private SetmealCheckgroupMapper setmealCheckgroupMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private CheckgroupMapper checkgroupMapper;
    @Resource
    private CheckitemMapper checkitemMapper;

    @Transactional
    @Override
    public R addOrUpdateSetmeal(Setmeal setmeal, Integer[] ids) {


        if (setmeal.getId() == null) {//添加套餐
            //套餐信息添加到套餐表
            setmealMapper.insert(setmeal);

        }else {//修改套餐

            //先从数据库中将老的图片名称查询出来
            String oldImg = setmealMapper.selectById(setmeal.getId()).getImg();


            setmealMapper.updateById(setmeal);

            //删除中间表
            LambdaQueryWrapper<SetmealCheckgroup> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SetmealCheckgroup::getSetmealId,setmeal.getId());
            setmealCheckgroupMapper.delete(queryWrapper);

            //redis中删除老的图片名称  添加新的图片名称
            stringRedisTemplate.opsForSet().remove(SET_MEAL_UPLOAD_DB_PIC_SET_NAME,oldImg);
        }
        //将图片名称添加redis中
        stringRedisTemplate.opsForSet().add(SET_MEAL_UPLOAD_DB_PIC_SET_NAME,setmeal.getImg());

        //选中的检查组信息添加到中间表
        for (Integer checkGroupId : ids) {
            SetmealCheckgroup setmealCheckgroup = new SetmealCheckgroup();
            setmealCheckgroup.setSetmealId(setmeal.getId());
            setmealCheckgroup.setCheckgroupId(checkGroupId);
            setmealCheckgroupMapper.insert(setmealCheckgroup);
        }

        return R.success(ADD_OR_UPDATE_SETMEAL_SUCCESS);
    }

    @Override
    public R<PageResult<Setmeal>> findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        //构建分页对象
        IPage<Setmeal> iPage = new Page<>(currentPage,pageSize);
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        //如果传入了条件  拼接条件
        if (queryString != null && queryString.trim().length() > 0) {

            queryWrapper.like(Setmeal::getCode,queryString)
                    .or()
                    .like(Setmeal::getName,queryString)
                    .or()
                    .like(Setmeal::getHelpcode,queryString);
        }
        iPage = setmealMapper.selectPage(iPage,queryWrapper);
        //拿数据
        List<Setmeal> records = iPage.getRecords();
        //拿总条数
        long total = iPage.getTotal();
        PageResult<Setmeal> pageResult = new PageResult<>();
        pageResult.setRows(records);
        pageResult.setTotal(total);

        return R.success(QUERY_CHECKGROUP_SUCCESS,pageResult);
    }

    @Override
    public R deleteById(Integer id) {
        //1.先查中间表t_setmeal_checkgroup
        LambdaQueryWrapper<SetmealCheckgroup> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealCheckgroup::getSetmealId,id);
        int count = setmealCheckgroupMapper.selectCount(queryWrapper);
        if (count > 0) {

            return R.err(MessageConstant.DELETE_NOT_SET_MEAL_01);
        }
        //2.查询order表
        LambdaQueryWrapper<Order> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Order::getSetmealId,id);
        count = orderMapper.selectCount(queryWrapper1);
        if (count > 0) {
            return R.err(MessageConstant.DELETE_NOT_SET_MEAL_02);
        }

        //先通过id查询
        String oldImg = setmealMapper.selectById(id).getImg();

        //3.删除
        count = setmealMapper.deleteById(id);
        if (count == 0) {

            return R.err(DELETE_SET_MEAL_ERROR);
        }

        // //将redis中图片删除掉
        stringRedisTemplate.opsForSet().remove(SET_MEAL_UPLOAD_DB_PIC_SET_NAME,oldImg);
        return R.success(DELETE_SET_MEAL_SUCCESS);
    }

    @Override
    public R<List<Setmeal>> findAll() {
        List<Setmeal> setmealList = setmealMapper.selectList(null);

        return R.success(null,setmealList);
    }

    @Override
    public R<Setmeal> findSetMealInfoById(Integer id) {
        if (id == null) {
            return R.err("参数异常");
        }
        //通过id去查套餐表
        Setmeal setmeal = setmealMapper.selectById(id);
        if (setmeal == null) {
            return R.err("没有该套餐信息");
        }
        //通过套餐id查询到所对应的检查组
        List<Checkgroup> checkgroups = checkgroupMapper.findCheckGroupById(id);
        if (checkgroups != null) {
            for (Checkgroup checkgroup : checkgroups) {
                Integer checkgroupId = checkgroup.getId();
                //通过checkgroupId去查询对应的检查项集合
                List<Checkitem> checkitems = checkitemMapper.findCheckItemById(checkgroupId);
                //在赋值给checkgroup的checkitems属性
                checkgroup.setCheckItems(checkitems);
            }
        }
        setmeal.setCheckGroups(checkgroups);


        return R.success(null,setmeal);
    }

    public static void main(String[] args) {
        //手机号密码  13112345678
        //判断qq号是否合法
        //将字符串反转
        //统计字符串每个字符出现的次数
        test01();
        test02();
        test03();
    }

    public static void test01() {
        String phone = "13112345678";
        String phone1 = phone.substring(0,3) + "****" + phone.substring(7);
        System.out.println("phone1 = " + phone1);
    }

    public static void test02() {
        String phone = "13112345678";
        System.out.println(new StringBuilder(phone).reverse());

    }
    public static void test03() {
        String phone = "aabbcdcdd";
        Map<Character,Integer> map = new HashMap<>();

        for (int i = 0; i <= phone.length() - 1; i++) {
            char c = phone.charAt(i);
            if (map.containsKey(c)) {
                map.put(c,map.get(c) + 1);
            }else {
                map.put(c,1);
            }
        }
        System.out.println("map = " + map);

    }
}




