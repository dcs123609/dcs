package com.wl.springsecurity_demo.demos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @Author lss
 * @CreateTime 2023/10/26
 * @Description
 */
public class Generator {

    /**
     * 1、修改dataSource  连接数据库的参数
     * 2、修改代码生成的路径
     * 3、修改要生成的表名
     *
     */
    public static void main(String[] args) {
        //1. 创建代码生成器对象，执行生成代码操作
        AutoGenerator autoGenerator = new AutoGenerator();

        //2. 数据源相关配置：读取数据库中的信息，根据数据库表结构生成代码
        //TODO 第一处修改
        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setDriverName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///securitydemo?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        autoGenerator.setDataSource(dataSource);


        //设置全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        //TODO  第二处修改
        globalConfig.setOutputDir("D:\\software\\ideaworkspace\\springboot_pe_2211\\springsecurity_demo\\src\\main\\java");    //设置代码生成位置
        globalConfig.setOpen(false);    //设置生成完毕后是否打开生成代码所在的目录
        globalConfig.setAuthor("风中少年");    //设置作者
        globalConfig.setFileOverride(true);     //设置是否覆盖原始生成的文件
        globalConfig.setMapperName("%sMapper");    //设置数据层接口名，%s为占位符，指代模块名称
        globalConfig.setIdType(IdType.AUTO);   //设置Id生成策略
        autoGenerator.setGlobalConfig(globalConfig);


        //设置包名相关配置
        PackageConfig packageInfo = new PackageConfig();
        packageInfo.setParent("com.wl.springsecurity_demo");   //设置生成的包名，与代码所在位置不冲突，二者叠加组成完整路径
        packageInfo.setEntity("pojo");    //设置实体类包名
        packageInfo.setMapper("mapper");   //设置数据层包名
        autoGenerator.setPackageInfo(packageInfo);

        //策略设置
        StrategyConfig strategyConfig = new StrategyConfig();
        //TODO 第三处修改
        strategyConfig.setInclude("sys_user","sys_role","sys_user_role","sys_menu","sys_role_menu");  //设置当前参与生成的表名，参数为可变参数
        strategyConfig.setTablePrefix("sys_");  //设置数据库表的前缀名称，模块名 = 数据库表名 - 前缀名  例如： User = tbl_user - tbl_
        strategyConfig.setRestControllerStyle(true);    //设置是否启用Rest风格
        //strategyConfig.setVersionFieldName("version");  //设置乐观锁字段名
        //strategyConfig.setLogicDeleteFieldName("deleted");  //设置逻辑删除字段名
        //字段映射 下划线---小驼峰
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        //小驼峰----下划线
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);  //设置是否启用lombok
        autoGenerator.setStrategy(strategyConfig);
        //3. 执行生成操作
        autoGenerator.execute();
    }
}
