package com.wl.springboot_pe_2211.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wl.springboot_pe_2211.entity.Member;
import com.wl.springboot_pe_2211.entity.Order;
import com.wl.springboot_pe_2211.entity.Ordersetting;
import com.wl.springboot_pe_2211.entity.dto.OrderInfo;
import com.wl.springboot_pe_2211.entity.dto.ReportSetmealDto;
import com.wl.springboot_pe_2211.mapper.MemberMapper;
import com.wl.springboot_pe_2211.mapper.OrderMapper;
import com.wl.springboot_pe_2211.mapper.OrdersettingMapper;
import com.wl.springboot_pe_2211.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wl.springboot_pe_2211.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-16
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrdersettingMapper ordersettingMapper;
    @Resource
    private MemberMapper memberMapper;



    @Transactional
    @Override
    public R submitOrder(OrderInfo orderInfo) {
        //拿到预约日期
        LocalDate orderDate = orderInfo.getOrderDate();
        //判断预约日期的合法性
        if (orderDate.isBefore(LocalDate.now().plusDays(1)) || orderDate.isAfter(LocalDate.now().plusDays(30))) {
            return R.err("预约日期超出了设定范围");
        }
        //查询当前日期是否开放了预约
        LambdaQueryWrapper<Ordersetting> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ordersetting::getOrderdate,orderDate);
        Ordersetting ordersetting = ordersettingMapper.selectOne(queryWrapper);
        if (ordersetting == null) {
            return R.err("当前日期没有开放预约");
        }
        //判断该天预约人数是否已满
        if (ordersetting.getNumber() == ordersetting.getReservations()) {
            return R.err("当前日期预约人数已满");
        }
        //判断该体检人是否是会员
        LambdaQueryWrapper<Member> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Member::getPhonenumber,orderInfo.getTelephone())
                .eq(Member::getIdcard,orderInfo.getIdCard());
        Member member = memberMapper.selectOne(queryWrapper1);
        if (member == null) {
            //不是会员   直接注册
            member = new Member();
            member.setName(orderInfo.getName());
            member.setSex(orderInfo.getSex());
            member.setIdcard(orderInfo.getIdCard());
            member.setPhonenumber(orderInfo.getTelephone());
            member.setRegtime(LocalDate.now());
            memberMapper.insert(member);
        }else { //是会员
            //判断该体检人是否重复预约
            LambdaQueryWrapper<Order> queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.eq(Order::getMemberId,member.getId())
                    .eq(Order::getOrderdate,orderDate)
                    .eq(Order::getSetmealId,orderInfo.getSetmealId());
            Order order = orderMapper.selectOne(queryWrapper2);
            if (order != null) {
                //说明当前日期，当前套餐，当前体检人已经预约过
                return R.err("当前日期，当前套餐，当前体检人已经预约过,请勿重复预约");
            }

        }

        //开始预约
        Order order = new Order();
        order.setMemberId(member.getId());
        order.setOrderdate(orderDate);
        order.setOrdertype("微信预约");
        order.setOrderstatus("未到诊");
        order.setSetmealId(orderInfo.getSetmealId());
        orderMapper.insert(order);

        //更新预约表让已经预约的人数+1
        ordersetting.setReservations(ordersetting.getReservations() + 1);
        ordersettingMapper.updateById(ordersetting);

        return R.success();
    }

    /**
     * 查询每个套餐的预约占比
     * @return
     * //需要每个套餐的名字和对应的预约数量
     * SELECT s.name,COUNT(*) AS 套餐数量 FROM t_order o INNER JOIN t_setmeal s
     * ON s.id = o.setmeal_id GROUP BY s.name
     *
     *
     *  [
     *         { value: 1048, name: 'Search Engine' },
     *         { value: 735, name: 'Direct' },
     *         { value: 580, name: 'Email' },
     *         { value: 484, name: 'Union Ads' },
     *         { value: 300, name: 'Video Ads' }
     *       ]
     */
    @Override
    public R getSetmealReport() {
        List<ReportSetmealDto> reportSetmealDtoList =  orderMapper.getSetmealReport();
        return R.success(null,reportSetmealDtoList);
    }
}
