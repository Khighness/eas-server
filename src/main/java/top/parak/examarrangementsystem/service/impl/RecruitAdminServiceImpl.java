package top.parak.examarrangementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import top.parak.examarrangementsystem.dto.RecruitAdminDTO;
import top.parak.examarrangementsystem.dto.converter.RecruitAdminConverter;
import top.parak.examarrangementsystem.entity.RecruitAdmin;
import top.parak.examarrangementsystem.entity.Role;
import top.parak.examarrangementsystem.mapper.RecruitAdminMapper;
import top.parak.examarrangementsystem.mapper.RoleMapper;
import top.parak.examarrangementsystem.service.RecruitAdminService;
import org.springframework.stereotype.Service;
import top.parak.examarrangementsystem.util.EncryptUtils;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service.impl </p>
 * <p> FileName: RecruitAdminServiceImpl <p>
 * <p> Description: 角色业务层接口实现 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Service
public class RecruitAdminServiceImpl implements RecruitAdminService {

    @Autowired
    private RecruitAdminMapper recruitAdminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Value("${default.recruitAdmin.avatar}")
    private String recruitAdminDefaultAvatar;

    @Value("${default.recruitAdmin.name}")
    private String recruitAdminDefaultName;

    @Value("${default.recruitAdmin.roleId}")
    private Integer recruitAdminDefaultRoleId;

    @Override
    public int saveRecruitAdmin(String email, String password) {
        RecruitAdmin recruitAdmin = RecruitAdmin.builder()
                .email(email)
                .password(EncryptUtils.encryptByMD5(password))
                .avatar(recruitAdminDefaultAvatar)
                .name(recruitAdminDefaultName)
                .roleId(recruitAdminDefaultRoleId)
                .build();
        int result = recruitAdminMapper.insert(recruitAdmin);
        return result;
    }

    @Override
    public RecruitAdminDTO getRecruitAdminById(Long id) {
        QueryWrapper<RecruitAdmin> recruitAdminQueryWrapper = new QueryWrapper<>();
        recruitAdminQueryWrapper.eq("id", id);
        RecruitAdmin recruitAdmin = recruitAdminMapper.selectOne(recruitAdminQueryWrapper);
        return transformEntityIntoDTO(recruitAdmin);
    }

    @Override
    public RecruitAdminDTO getRecruitAdminByEmail(String email) {
        QueryWrapper<RecruitAdmin> recruitAdminQueryWrapper = new QueryWrapper<>();
        recruitAdminQueryWrapper.eq("email", email);
        RecruitAdmin recruitAdmin = recruitAdminMapper.selectOne(recruitAdminQueryWrapper);
        return transformEntityIntoDTO(recruitAdmin);
    }

    @Override
    public RecruitAdminDTO getRecruitAdminByEmailAndPassword(String email, String password) {
        QueryWrapper<RecruitAdmin> recruitAdminQueryWrapper = new QueryWrapper<>();
        recruitAdminQueryWrapper.eq("email", email);
        recruitAdminQueryWrapper.eq("password", EncryptUtils.encryptByMD5(password));
        RecruitAdmin recruitAdmin = recruitAdminMapper.selectOne(recruitAdminQueryWrapper);
        return transformEntityIntoDTO(recruitAdmin);
    }

    @Override
    public int updateRecruitAdminPasswordById(Long id, String password) {
        RecruitAdmin recruitAdmin = RecruitAdmin.builder()
                .id(id)
                .password(EncryptUtils.encryptByMD5(password))
                .build();
        int result = recruitAdminMapper.updateById(recruitAdmin);
        return result;
    }

    @Override
    public int updateRecruitAdminInfoById(RecruitAdmin recruitAdmin) {
        int result = recruitAdminMapper.updateById(recruitAdmin);
        return result;
    }

    /**
     * <p>po -> dto</p>
     * @param recruitAdmin
     * @return recruitAdminDTO
     */
    private RecruitAdminDTO transformEntityIntoDTO(RecruitAdmin recruitAdmin) {
        if (ObjectUtils.isEmpty(recruitAdmin) || ObjectUtils.isEmpty(recruitAdmin.getRoleId())) {
            return null;
        }
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.eq("id", recruitAdmin.getRoleId());
        Role role = roleMapper.selectOne(roleQueryWrapper);
        return RecruitAdminConverter.INSTANCE.entityToDTO(recruitAdmin, role);
    }


}
