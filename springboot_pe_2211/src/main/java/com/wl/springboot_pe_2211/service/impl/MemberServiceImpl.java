package com.wl.springboot_pe_2211.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wl.springboot_pe_2211.entity.Member;
import com.wl.springboot_pe_2211.mapper.MemberMapper;
import com.wl.springboot_pe_2211.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wl.springboot_pe_2211.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-29
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Resource
    private MemberMapper memberMapper;

    /**
     * 1、页面导入项目 导入到哪里
     * 2、echarts.js 在哪  怎么在页面引用
     * 3、前台需要什么样的数据模型
     * 4、请求的controller是重新创建还是直接使用memberController
     * 5、怎么获取获取12月
     * 6、循环查询
     * @return
     */
    @Override
    public R getMemberReport() {
        //获取过去12个月
        List<String> oneYearMonth = getOneYearMonth();
        LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
        List<Integer> countList = new ArrayList<>();
        //需要月份对应的会员数量
        for (String date : oneYearMonth) {
            //date  yyyy-MM
            //select count(*) from t_member where regTime like 'yyyy-MM%'

            queryWrapper.likeRight(Member::getRegtime,date);

            Integer count = memberMapper.selectCount(queryWrapper);
            countList.add(count);
            queryWrapper.clear();
        }

        //返回数据
        /**
         *  {
         *      months:[],
         *      memberCount:[]
         *  }
         */
        Map<String,List> map = new HashMap<>();
        map.put("months",oneYearMonth);
        map.put("memberCount",countList);


        return R.success("查询成功",map);
    }



    //获取前12个月的时间
    private List<String> getOneYearMonth() {
        LocalDate now = LocalDate.now();
        List<String> dateList = new ArrayList<>();
        //yyyy-MM
        for (int i = 12; i >= 1; i--) {
            LocalDate localDate = now.minusMonths(i);
            //将localDate转成yyyy-MM格式的字符串
            String format = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
            dateList.add(format);
        }

        return dateList;
    }


}
