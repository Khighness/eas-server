package top.parak.examarrangementsystem.dto.converter;

import org.junit.jupiter.api.Test;
import top.parak.examarrangementsystem.dto.RecruitAdminDTO;
import top.parak.examarrangementsystem.dto.RecruitApproverDTO;
import top.parak.examarrangementsystem.entity.RecruitApprover;
import top.parak.examarrangementsystem.entity.Role;

import java.util.Date;

class RecruitApproverConverterTest {

    @Test
    void entityToDTO() {
        RecruitApprover recruitApprover = RecruitApprover.builder()
                .id(1L)
                .email("1823676372@qq.com")
                .avatar("avatar.jpg")
                .name("KHighness")
                .gender("男")
                .birth(new Date())
                .signature("不读书")
                .phone("13311119999")
                .city("合肥")
                .gmtCreate(new Date())
                .gmtUpdate(new Date())
                .build();
        Role role = Role.builder()
                .id(2)
                .name("招办审批员")
                .build();
        RecruitApproverDTO recruitAdminDTO = RecruitApproverConverter.INSTANCE.entityToDTO(recruitApprover, role);
        System.out.println(recruitAdminDTO);
    }

}
