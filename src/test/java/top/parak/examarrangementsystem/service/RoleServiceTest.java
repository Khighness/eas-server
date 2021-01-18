package top.parak.examarrangementsystem.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.parak.examarrangementsystem.entity.Role;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    void saveRole() {
        List<String> roleList = Arrays.asList("招办审批员", "考点管理员", "考务人员");
        roleList.stream().forEach(r -> { roleService.saveRole(r); });
    }


    @Test
    void countOfRole() {
        log.info("角色数量：{}", roleService.countOfRole());
    }

    @Test
    void getRoleList() {
        roleService.getRoleList().stream().forEach(r -> { log.info(r.toString()); });
    }

    @Test
    void updateRole() {
        int res = roleService.updateRole(Role.builder().id(1).name("招办管理员").build());
        assertEquals(1, res);
    }

}
