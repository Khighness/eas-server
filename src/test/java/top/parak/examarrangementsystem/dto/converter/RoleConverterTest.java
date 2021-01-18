package top.parak.examarrangementsystem.dto.converter;

import org.junit.jupiter.api.Test;
import top.parak.examarrangementsystem.dto.RoleDTO;
import top.parak.examarrangementsystem.entity.Role;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RoleConverterTest {

    @Test
    void entityToDTO() {
        RoleDTO roleDTO = RoleConverter.INSTANCE.entityToDTO(
                Role.builder().id(1).name("CEO").description("首席执行官").gmtCreate(new Date()).gmtUpdate(new Date()).build()
        );
        System.out.println(roleDTO);
    }

    @Test
    void entityListToDTOList() {
        RoleConverter.INSTANCE.entityListToDTOList(Arrays.asList(
                Role.builder().id(1).name("CEO").description("首席执行官").gmtCreate(new Date()).gmtUpdate(new Date()).build(),
                Role.builder().id(1).name("CAO").description("首席行政官").gmtCreate(new Date()).gmtUpdate(new Date()).build(),
                Role.builder().id(1).name("CBO").description("首席执行官").gmtCreate(new Date()).gmtUpdate(new Date()).build(),
                Role.builder().id(1).name("CCO").description("首席文化官").gmtCreate(new Date()).gmtUpdate(new Date()).build(),
                Role.builder().id(1).name("CDO").description("首席设计官").gmtCreate(new Date()).gmtUpdate(new Date()).build(),
                Role.builder().id(1).name("CGO").description("首席增长官").gmtCreate(new Date()).gmtUpdate(new Date()).build(),
                Role.builder().id(1).name("CHO").description("首席人事官").gmtCreate(new Date()).gmtUpdate(new Date()).build(),
                Role.builder().id(1).name("CKO").description("首席知识官").gmtCreate(new Date()).gmtUpdate(new Date()).build(),
                Role.builder().id(1).name("CLO").description("首席学习官").gmtCreate(new Date()).gmtUpdate(new Date()).build(),
                Role.builder().id(1).name("CNO").description("首席谈判官").gmtCreate(new Date()).gmtUpdate(new Date()).build(),
                Role.builder().id(1).name("CPO").description("首席流程官").gmtCreate(new Date()).gmtUpdate(new Date()).build(),
                Role.builder().id(1).name("CQO").description("首席质量官").gmtCreate(new Date()).gmtUpdate(new Date()).build(),
                Role.builder().id(1).name("CRO").description("首席裁员官").gmtCreate(new Date()).gmtUpdate(new Date()).build(),
                Role.builder().id(1).name("CSO").description("首席战略官").gmtCreate(new Date()).gmtUpdate(new Date()).build(),
                Role.builder().id(1).name("CUO").description("首席联盟官").gmtCreate(new Date()).gmtUpdate(new Date()).build()
        )).stream().forEach(System.out::println);
    }
}
