package com.wl.springboot_pe_2211.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wl.springboot_pe_2211.entity.Ordersetting;
import com.wl.springboot_pe_2211.entity.dto.OrdersettingDto;
import com.wl.springboot_pe_2211.exception.GlobalExceptionEnum;
import com.wl.springboot_pe_2211.exception.MyGlobalException;
import com.wl.springboot_pe_2211.mapper.OrdersettingMapper;
import com.wl.springboot_pe_2211.service.IOrdersettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wl.springboot_pe_2211.utils.DateUtils;
import com.wl.springboot_pe_2211.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.wl.springboot_pe_2211.utils.MessageConstant.IMPORT_ORDERSETTING_SUCCESS;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-22
 */
@Service
public class OrdersettingServiceImpl extends ServiceImpl<OrdersettingMapper, Ordersetting> implements IOrdersettingService {

    @Resource
    private OrdersettingMapper ordersettingMapper;

    @Override
    public R addOrderSetting(List<String[]> stringList) {
        //非空判断
        if (stringList == null || stringList.size() == 0) {
            throw new MyGlobalException(GlobalExceptionEnum.PARAMS_ERROR);
        }
        //遍历
        for (String[] arr : stringList) {
            //参数转换  number---Integer    orderDate ---- LocalDate(jdk1.8出现的新的日期类型) 2025/05/27
            LocalDate orderDate = LocalDate.parse(arr[0], DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            int number = Integer.parseInt(arr[1]);
            //判断orderDate是否在当前日期之前
            if (orderDate.isBefore(LocalDate.now())) {
                continue;
            }

            //通过orderDate去数据库查询
            LambdaQueryWrapper<Ordersetting> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Ordersetting::getOrderdate,orderDate);
            Ordersetting ordersetting = ordersettingMapper.selectOne(queryWrapper);
            if (ordersetting == null) {
                //添加操作
                ordersetting = new Ordersetting();
                ordersetting.setNumber(number);
                ordersetting.setOrderdate(orderDate);
                ordersetting.setReservations(0);
                ordersettingMapper.insert(ordersetting);


            }else {
                //更新操作
                /*if (number >= ordersetting.getReservations()) {
                    ordersetting.setNumber(number);
                }else {
                    ordersetting.setNumber(ordersetting.getReservations());
                }*/
                Integer reservations = ordersetting.getReservations();
                ordersetting.setNumber(number >= reservations ? number : reservations);
                ordersettingMapper.updateById(ordersetting);
            }



        }

        return R.success(IMPORT_ORDERSETTING_SUCCESS);
    }

    @Override
    public R findByYearAndMonth(String orderDate) {
        //日期格式判断
        if (!DateUtils.validateOrderDate(orderDate)) {
            throw new MyGlobalException(GlobalExceptionEnum.PARAMS_ERROR);
        }
        //执行sql
        LambdaQueryWrapper<Ordersetting> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.likeRight(Ordersetting::getOrderdate,orderDate);
        List<Ordersetting> ordersettingList = ordersettingMapper.selectList(queryWrapper);
        //List<Ordersetting>---------转成---------List<OrdersettingDto>
        List<OrdersettingDto> ordersettingDtoList = new ArrayList<>();
        if (ordersettingList != null) {
            for (Ordersetting ordersetting : ordersettingList) {
                OrdersettingDto ordersettingDto = new OrdersettingDto();
                ordersettingDto.setNumber(ordersetting.getNumber());
                ordersettingDto.setReservations(ordersetting.getReservations());
                ordersettingDto.setDate(ordersetting.getOrderdate().getDayOfMonth());
                ordersettingDtoList.add(ordersettingDto);
            }
        }

        return R.success(null,ordersettingDtoList);
    }

    @Override
    public R updateNumberByOrderDate(Ordersetting ordersetting) {
        //判断orderDate是否在当前日期之前
        if (ordersetting.getOrderdate().isBefore(LocalDate.now())) {
            throw new MyGlobalException(GlobalExceptionEnum.PARAMS_ERROR);
        }
        //通过orderDate去数据库查询
        LambdaQueryWrapper<Ordersetting> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ordersetting::getOrderdate,ordersetting.getOrderdate());
        Ordersetting ordersettingDB = ordersettingMapper.selectOne(queryWrapper);

        if (ordersettingDB == null) {
            //添加操作
            ordersetting.setReservations(0);
            ordersettingMapper.insert(ordersetting);


        }else {
            //更新操作
            Integer reservations = ordersettingDB.getReservations();
            Integer number = ordersetting.getNumber();
            if (number >= reservations) {
                ordersettingDB.setNumber(number);
            }else {
                ordersettingDB.setNumber(reservations);
            }
            ordersettingMapper.updateById(ordersettingDB);

        }
        return R.success();
    }
}
