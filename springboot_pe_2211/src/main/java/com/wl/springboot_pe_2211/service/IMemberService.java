package com.wl.springboot_pe_2211.service;

import com.wl.springboot_pe_2211.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wl.springboot_pe_2211.utils.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-29
 */
public interface IMemberService extends IService<Member> {

    R getMemberReport();


}
