package com.wl.springboot_pe_2211.controller;


import com.wl.springboot_pe_2211.entity.Ordersetting;
import com.wl.springboot_pe_2211.service.IOrdersettingService;
import com.wl.springboot_pe_2211.utils.POIUtils;
import com.wl.springboot_pe_2211.utils.R;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-22
 */
@RestController
@RequestMapping("/ordersetting")
public class OrdersettingController {


    @Resource
    private IOrdersettingService ordersettingService;

    /**
     * 上传套餐预约
     * @return
     */
    @PostMapping("uploadTemplate")
    public R uploadTemplate(@RequestParam("excelFile") MultipartFile excelFile) throws IOException {
        //通过poi工具类获取excel中的数据
        List<String[]> stringList = POIUtils.readExcel(excelFile);

        return ordersettingService.addOrderSetting(stringList);
    }

    /**
     * 页面显示预约信息
     */
    @GetMapping("/findByYearAndMonth/{orderDate}")
    public R findByYearAndMonth(@PathVariable String orderDate) {
        return ordersettingService.findByYearAndMonth(orderDate);
    }

    /**
     * updateNumberByOrderDate
     * 日历的预约信息设定
     */
    @PutMapping("/updateNumberByOrderDate")
    public R updateNumberByOrderDate(Ordersetting ordersetting) {
        return ordersettingService.updateNumberByOrderDate(ordersetting);

    }
}

