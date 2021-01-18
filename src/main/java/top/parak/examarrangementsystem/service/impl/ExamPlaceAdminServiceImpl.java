package top.parak.examarrangementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import top.parak.examarrangementsystem.dto.ExamPlaceAdminDTO;
import top.parak.examarrangementsystem.dto.converter.ExamPlaceAdminConverter;
import top.parak.examarrangementsystem.entity.ExamPlace;
import top.parak.examarrangementsystem.entity.ExamPlaceAdmin;
import top.parak.examarrangementsystem.entity.Role;
import top.parak.examarrangementsystem.mapper.ExamPlaceAdminMapper;
import top.parak.examarrangementsystem.mapper.ExamPlaceMapper;
import top.parak.examarrangementsystem.mapper.RoleMapper;
import top.parak.examarrangementsystem.service.ExamPlaceAdminService;
import org.springframework.stereotype.Service;
import top.parak.examarrangementsystem.util.EncryptUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service.impl </p>
 * <p> FileName: ExamPlaceAdminServiceImpl <p>
 * <p> Description: 考点管理员业务层接口实现 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Service
public class ExamPlaceAdminServiceImpl implements ExamPlaceAdminService {

    @Autowired
    private ExamPlaceAdminMapper examPlaceAdminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ExamPlaceMapper examPlaceMapper;

    @Value("${default.examPlaceAdmin.avatar}")
    private String examPlaceAdminDefaultAvatar;

    @Value("${default.examPlaceAdmin.name}")
    private String examPlaceAdminDefaultName;

    @Value("${default.examPlaceAdmin.roleId}")
    private Integer examPlaceAdminDefaultRoleId;

    @Override
    public int saveExamPlaceAdmin(ExamPlaceAdmin examPlaceAdmin) {
        examPlaceAdmin.setPassword(EncryptUtils.encryptByMD5(examPlaceAdmin.getPassword()));
        examPlaceAdmin.setAvatar(ObjectUtils.isEmpty(examPlaceAdmin.getAvatar()) ? examPlaceAdminDefaultAvatar : examPlaceAdmin.getAvatar());
        examPlaceAdmin.setName(ObjectUtils.isEmpty(examPlaceAdmin.getName()) ? examPlaceAdminDefaultName : examPlaceAdmin.getName());
        examPlaceAdmin.setRoleId(examPlaceAdminDefaultRoleId);
        int result = examPlaceAdminMapper.insert(examPlaceAdmin);
        return result;
    }

    @Override
    public int countOfExamPlaceAdmin() {
        return examPlaceAdminMapper.selectCount(null);
    }

    @Override
    public ExamPlaceAdminDTO getExamPlaceAdminById(Long id) {
        QueryWrapper<ExamPlaceAdmin> examPlaceAdminQueryWrapper = new QueryWrapper<>();
        examPlaceAdminQueryWrapper.eq("id", id);
        ExamPlaceAdmin examPlaceAdmin = examPlaceAdminMapper.selectOne(examPlaceAdminQueryWrapper);
        return transformEntityToDTO(examPlaceAdmin);
    }

    @Override
    public ExamPlaceAdminDTO getExamPlaceAdminDTOByExamPlaceId(Long examPlaceId) {
        QueryWrapper<ExamPlaceAdmin> examPlaceAdminQueryWrapper = new QueryWrapper<>();
        examPlaceAdminQueryWrapper.eq("exam_place_id", examPlaceId);
        ExamPlaceAdmin examPlaceAdmin = examPlaceAdminMapper.selectOne(examPlaceAdminQueryWrapper);
        return transformEntityToDTO(examPlaceAdmin);
    }

    @Override
    public ExamPlaceAdminDTO getExamPlaceAdminByEmail(String email) {
        QueryWrapper<ExamPlaceAdmin> examPlaceAdminQueryWrapper = new QueryWrapper<>();
        examPlaceAdminQueryWrapper.eq("email", email);
        ExamPlaceAdmin examPlaceAdmin = examPlaceAdminMapper.selectOne(examPlaceAdminQueryWrapper);
        return transformEntityToDTO(examPlaceAdmin);
    }

    @Override
    public ExamPlaceAdminDTO getExamPlaceAdminByEmailAndPassword(String email, String password) {
        QueryWrapper<ExamPlaceAdmin> examPlaceAdminQueryWrapper = new QueryWrapper<>();
        examPlaceAdminQueryWrapper.eq("email", email);
        examPlaceAdminQueryWrapper.eq("password", EncryptUtils.encryptByMD5(password));
        ExamPlaceAdmin examPlaceAdmin = examPlaceAdminMapper.selectOne(examPlaceAdminQueryWrapper);
        if (ObjectUtils.isEmpty(examPlaceAdmin)) {
            return null;
        } else {
            return transformEntityToDTO(examPlaceAdmin);
        }
    }

    @Override
    public List<ExamPlaceAdminDTO> getExamPlaceAdminList() {
        List<ExamPlaceAdminDTO> examPlaceAdminDTOList = new LinkedList<>();
        examPlaceAdminMapper.selectList(null).stream().forEach( e -> { examPlaceAdminDTOList.add(transformEntityToDTO(e)); });
        return examPlaceAdminDTOList;
    }

    @Override
    public List<ExamPlaceAdminDTO> getExamPlaceAdminByPage(int current, int size) {
        List<ExamPlaceAdminDTO> examPlaceAdminDTOList = new LinkedList<>();
        Page<ExamPlaceAdmin> examPlaceAdminPage = new Page<>(current, size);
        examPlaceAdminPage = examPlaceAdminMapper.selectPage(examPlaceAdminPage, null);
        examPlaceAdminPage.getRecords().stream().forEach( e -> { examPlaceAdminDTOList.add(transformEntityToDTO(e)); });
        return examPlaceAdminDTOList;
    }

    @Override
    public int updateExamPlaceAdminPasswordById(Long id, String password) {
        ExamPlaceAdmin examPlaceAdmin = ExamPlaceAdmin.builder()
                .id(id)
                .password(EncryptUtils.encryptByMD5(password))
                .build();
        int result = examPlaceAdminMapper.updateById(examPlaceAdmin);
        return result;
    }

    @Override
    public int updateExamPlaceAdminInfoById(ExamPlaceAdmin examPlaceAdmin) {
        int result = examPlaceAdminMapper.updateById(examPlaceAdmin);
        return result;
    }

    @Override
    public int deleteExamPlaceAdminById(Long id) {
        int result = examPlaceAdminMapper.deleteById(id);
        return result;
    }

    /**
     * <p>po -> dto</p>
     * @param examPlaceAdmin
     * @return examPlaceAdminDTO
     */
    private ExamPlaceAdminDTO transformEntityToDTO(ExamPlaceAdmin examPlaceAdmin) {
        if (ObjectUtils.isEmpty(examPlaceAdmin) || ObjectUtils.isEmpty(examPlaceAdmin.getRoleId()) || ObjectUtils.isEmpty(examPlaceAdmin.getExamPlaceId())) {
            return null;
        }
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.eq("id", examPlaceAdmin.getRoleId());
        Role role = roleMapper.selectOne(roleQueryWrapper);
        QueryWrapper<ExamPlace> examPlaceQueryWrapper = new QueryWrapper<>();
        examPlaceQueryWrapper.eq("id", examPlaceAdmin.getExamPlaceId());
        ExamPlace examPlace = examPlaceMapper.selectOne(examPlaceQueryWrapper);
        return ExamPlaceAdminConverter.INSTANCE.entityToDTO(examPlaceAdmin, role, examPlace);
    }
}
