package top.parak.examarrangementsystem.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.generator </p>
 * <p> FileName: ParakCodeGenerator <p>
 * <p> Description: MP基础代码生成器 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2020/12/29
 */

public class MybatisPlusCodeGenerator {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        // 设置作者名
        globalConfig.setAuthor("KHighness");
        // 操作完成是否打开资源管理器
        globalConfig.setOpen(false);
        // 是否覆盖原有文件
        globalConfig.setFileOverride(true);
        // 去Service的I前缀
        globalConfig.setServiceName("%sService");
        // 分配ID
        globalConfig.setIdType(IdType.ASSIGN_ID);
        globalConfig.setDateType(DateType.ONLY_DATE);
        autoGenerator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/exam_arrangement_system?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8&useSSL=false");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("KAG1823");
        autoGenerator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("top.parak.examarrangementsystem");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setController("controller");
        autoGenerator.setPackageInfo(packageConfig);

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        // 映射表名，可有多个
        strategyConfig.setInclude("record_exam_subject_invigilator_group", "record_task_exam_invigilator");
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        // Lombok实体类
        strategyConfig.setEntityLombokModel(true);
        // 驼峰命名
        strategyConfig.setRestControllerStyle(true);
        // 逻辑删除
        strategyConfig.setLogicDeleteFieldName("deleted");
        // 创建时间
        TableFill gmt_create = new TableFill("gmt_create", FieldFill.INSERT);
        // 更新时间
        TableFill gmt_modify = new TableFill("gmt_update", FieldFill.INSERT_UPDATE);
        List<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmt_create);
        tableFills.add(gmt_modify);
        strategyConfig.setTableFillList(tableFills);
        // 乐观锁
        strategyConfig.setVersionFieldName("version");
        strategyConfig.setControllerMappingHyphenStyle(true);
        autoGenerator.setStrategy(strategyConfig);

        // 自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        // 执行生成
        autoGenerator.execute();
    }

}
