package com.wl.springboot_pe_2211.controller;


import com.wl.springboot_pe_2211.entity.Checkgroup;
import com.wl.springboot_pe_2211.entity.Setmeal;
import com.wl.springboot_pe_2211.exception.GlobalExceptionEnum;
import com.wl.springboot_pe_2211.exception.MyGlobalException;
import com.wl.springboot_pe_2211.service.ISetmealService;
import com.wl.springboot_pe_2211.utils.CommonConstant;
import com.wl.springboot_pe_2211.utils.PageResult;
import com.wl.springboot_pe_2211.utils.QueryPageBean;
import com.wl.springboot_pe_2211.utils.R;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.wl.springboot_pe_2211.utils.CommonConstant.*;
import static com.wl.springboot_pe_2211.utils.MessageConstant.UPLOAD_SUCCESS;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 追风少年
 * @since 2025-05-14
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {


    @Resource
    private ISetmealService setmealService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 文件上传
     * @param multipartFile
     * @return
     */
    @PostMapping("upload")
    public R uploadFile(@RequestParam("imgFile") MultipartFile multipartFile) {
        //1.获取文件名称
        String filename = multipartFile.getOriginalFilename();
        //2.判断是否上传了文件
        if (filename == null || filename.length() == 0) {
            throw new MyGlobalException(GlobalExceptionEnum.UPLOAD_NOT_NULL);
        }
        //3.判断上传的是否是图片    .jpg  .png
        if (!filename.endsWith(PIC_TYPE_01) && !filename.endsWith(PIC_TYPE_02)) {
            throw new MyGlobalException(GlobalExceptionEnum.UPLOAD_PIC_TYPE_ERROR);
        }

        //4.重新生成图片名称 UUID
        filename = UUID.randomUUID() + filename;
        //5.将图片保存到图片服务器(本地磁盘:D://pePic)
        File path = new File(FILE_PATH);
        //该路径不存在，创建
        if (!path.exists()) {
            path.mkdirs();
        }
        try {
            multipartFile.transferTo(new File(path,filename));
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyGlobalException(GlobalExceptionEnum.PARAMS_ERROR);
        }
        //将图片名称添加到redis的set集合中uploadPic
        stringRedisTemplate.opsForSet().add(SET_MEAL_UPLOAD_PIC_SET_NAME,filename);


        //6.将图片名称返回回去
        return R.success(UPLOAD_SUCCESS,filename);
    }


    /**
     * 添加或者修改套餐
     * @param setmeal
     * @param ids
     * @return
     */
    @PostMapping("/addOrUpdateSetmeal/{checkgroupIds}")
    public R addOrUpdateSetmeal(@RequestBody Setmeal setmeal,@PathVariable(value = "checkgroupIds") Integer[] ids) {
        return setmealService.addOrUpdateSetmeal(setmeal,ids);
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @PostMapping("findPage")
    public R<PageResult<Setmeal>> findPage(@RequestBody QueryPageBean queryPageBean) {
        return setmealService.findPage(queryPageBean);
    }

    /**
     * 删除套餐
     */
    @DeleteMapping("/deleteById/{id}")
    public R deleteById(@PathVariable Integer id) {
        return setmealService.deleteById(id);
    }


    /**
     * 查询所有
     */
    @GetMapping("/findAll")
    public R<List<Setmeal>> findAll() {
        return setmealService.findAll();
    }

    /**
     * 通过id查询套餐信息
     * //http://localhost/setmeal/findSetMealInfoById/12
     */
    @GetMapping("/findSetMealInfoById/{id}")
    public R<Setmeal> findSetMealInfoById(@PathVariable Integer id) {
        return setmealService.findSetMealInfoById(id);
    }
//    /**
//     * 通过id查询套餐基本信息
//     * http://localhost/setmeal/findSetMealInfoById?id=12
//     */
//    @PostMapping("/findInfoById")
//    public R<Setmeal> findInfoById(Integer id) {
//        return setmealService.findSetMealInfoById(id);
//    }




}

