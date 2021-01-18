package top.parak.examarrangementsystem.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.parak.examarrangementsystem.entity.RecruitApprover;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class RecruitApproverServiceTest {

    @Autowired
    private RecruitApproverService recruitApproverService;

    @Test
    void saveRecruitApprover() {
        int result = recruitApproverService.saveRecruitApprover(
                RecruitApprover.builder()
                        .email("1823676372@qq.com")
                        .password("CZK666")
                        .build()
        );
        assertEquals(1, result);
    }

    @Test
    void countOfRecruitApprover() {
        log.info("招办审批员数量：{}", recruitApproverService.countOfRecruitApprover());
    }

    @Test
    void getRecruitApproverById() {
        log.info("ID为[1]的考点管理员信息：{}", recruitApproverService.getRecruitApproverById(1L));
    }

    @Test
    void getRecruitApproverByEmail() {
        log.info("邮箱为[2298148442@qq.com]的考点管理员信息：{}", recruitApproverService.getRecruitApproverByEmail("2298148442@qq.com"));
    }

    @Test
    void getRecruitApproverByEmailAndPassword() {
        log.info("邮箱为[1823676372@qq.com]，密码为[CZK666]的考点管理员信息：{}", recruitApproverService.getRecruitApproverByEmailAndPassword("1823676372@qq.com", "CZK666"));
    }

    @Test
    void getRecruitApproverList() {
        recruitApproverService.getRecruitApproverList().stream().forEach( e -> { log.info(e.toString()); } );
    }

    @Test
    void getRecruitApproverByPage() {
        recruitApproverService.getRecruitApproverByPage(1, 1).stream().forEach( e -> { log.info(e.toString()); } );
    }

    @Test
    void updateRecruitApproverPasswordById() {
        int result = recruitApproverService.updateRecruitApproverPasswordById(1L, "KAG1823");
        assertEquals(1, result);
    }

    @Test
    void updateRecruitApproverInfoById() {
        int result = recruitApproverService.updateRecruitApproverInfoById(
                RecruitApprover.builder()
                        .id(1L)
                        .gender("男")
                        .build()
        );
        assertEquals(1, result);
    }

    @Test
    void deleteRecruitApproverById() {
        int result = recruitApproverService.deleteRecruitApproverById(1L);
        assertEquals(1, result);
    }
}
