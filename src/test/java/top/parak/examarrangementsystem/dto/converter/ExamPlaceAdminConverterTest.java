package top.parak.examarrangementsystem.dto.converter;

import org.junit.jupiter.api.Test;
import top.parak.examarrangementsystem.dto.ExamPlaceAdminDTO;
import top.parak.examarrangementsystem.entity.ExamPlace;
import top.parak.examarrangementsystem.entity.ExamPlaceAdmin;
import top.parak.examarrangementsystem.entity.Role;

import java.util.Date;

class ExamPlaceAdminConverterTest {

    @Test
    void entityToDTO() {
        ExamPlaceAdmin examPlaceAdmin = ExamPlaceAdmin.builder()
                .id(1L)
                .email("1823676372@qq.com")
                .avatar("avatar.jpg")
                .name("KHighness")
                .gender("男")
                .birth(new Date())
                .signature("不读书")
                .phone("13311119999")
                .gmtCreate(new Date())
                .gmtUpdate(new Date())
                .build();
        Role role = Role.builder()
                .id(3)
                .name("考点管理员")
                .build();
        ExamPlace examPlace = ExamPlace.builder()
                .id(1L)
                .name("天子一中")
                .build();
        ExamPlaceAdminDTO examPlaceAdminDTO = ExamPlaceAdminConverter.INSTANCE.entityToDTO(examPlaceAdmin, role, examPlace);
        System.out.println(examPlaceAdminDTO);
    }

}
