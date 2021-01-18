package top.parak.examarrangementsystem.dto.converter;

import org.junit.jupiter.api.Test;
import top.parak.examarrangementsystem.dto.RecruitAdminDTO;
import top.parak.examarrangementsystem.entity.RecruitAdmin;
import top.parak.examarrangementsystem.entity.RecruitApprover;
import top.parak.examarrangementsystem.entity.Role;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RecruitAdminConverterTest {

    @Test
    void entityToDTO() {
        RecruitAdmin recruitAdmin = RecruitAdmin.builder()
                .id(1L)
                .email("1823676372@qq.com")
                .avatar("avatar.jpg")
                .name("KHighness")
                .gender("男")
                .birth(new Date())
                .signature("不读书")
                .phone("13311119999")
                .province("安徽")
                .gmtCreate(new Date())
                .gmtUpdate(new Date())
                .build();
        Role role = Role.builder()
                .id(1)
                .name("招办管理员员")
                .build();
        RecruitAdminDTO recruitAdminDTO = RecruitAdminConverter.INSTANCE.entityToDTO(recruitAdmin, role);
        System.out.println(recruitAdminDTO);
    }

}
