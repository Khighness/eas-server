package top.parak.examarrangementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import top.parak.examarrangementsystem.dto.ExamPlaceInvigilatorDTO;
import top.parak.examarrangementsystem.dto.converter.ExamPlaceInvigilatorConverter;
import top.parak.examarrangementsystem.entity.ExamPlace;
import top.parak.examarrangementsystem.entity.ExamPlaceInvigilator;
import top.parak.examarrangementsystem.entity.Role;
import top.parak.examarrangementsystem.mapper.ExamPlaceInvigilatorMapper;
import top.parak.examarrangementsystem.mapper.ExamPlaceMapper;
import top.parak.examarrangementsystem.mapper.RoleMapper;
import top.parak.examarrangementsystem.service.ExamPlaceInvigilatorService;
import org.springframework.stereotype.Service;
import top.parak.examarrangementsystem.util.EncryptUtils;
import top.parak.examarrangementsystem.util.SnowFlakeIDUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service.impl </p>
 * <p> FileName: ExamPlaceInvigilatorServiceImpl <p>
 * <p> Description: 考务人员业务层接口实现 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Service
public class ExamPlaceInvigilatorServiceImpl implements ExamPlaceInvigilatorService {

    @Autowired
    private ExamPlaceInvigilatorMapper examPlaceInvigilatorMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ExamPlaceMapper examPlaceMapper;

    @Value("${default.examPlaceInvigilator.avatar}")
    private String examPlaceInvigilatorDefaultAvatar;

    @Value("${default.examPlaceInvigilator.name}")
    private String examPlaceInvigilatorDefaultName;

    @Value("${default.examPlaceInvigilator.roleId}")
    private Integer examPlaceInvigilatorDefaultRoleId;

    @Override
    public int saveExamPlaceInvigilator(ExamPlaceInvigilator examPlaceInvigilator) {
        return examPlaceInvigilatorMapper.insert(examPlaceInvigilator);
    }

    @Override
    public int saveExamPlaceInvigilator(String email, String password) {
        ExamPlaceInvigilator examPlaceInvigilator = ExamPlaceInvigilator.builder()
                .email(email)
                .password(EncryptUtils.encryptByMD5(password))
                .avatar(examPlaceInvigilatorDefaultAvatar)
                .name(examPlaceInvigilatorDefaultName)
                .roleId(examPlaceInvigilatorDefaultRoleId)
                .build();
        int result = examPlaceInvigilatorMapper.insert(examPlaceInvigilator);
        return result;
    }

    @Override
    public int countOfExamPlaceInvigilator() {
        return examPlaceInvigilatorMapper.selectCount(null);
    }

    @Override
    public ExamPlaceInvigilatorDTO getExamPlaceInvigilatorById(long id) {
        QueryWrapper<ExamPlaceInvigilator> examPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        examPlaceInvigilatorQueryWrapper.eq("id", id);
        ExamPlaceInvigilator examPlaceInvigilator = examPlaceInvigilatorMapper.selectOne(examPlaceInvigilatorQueryWrapper);
        return transformEntityIntoDTO(examPlaceInvigilator);
    }

    @Override
    public ExamPlaceInvigilatorDTO getExamPlaceInvigilatorByEmail(String email) {
        QueryWrapper<ExamPlaceInvigilator> examPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        examPlaceInvigilatorQueryWrapper.eq("email", email);
        ExamPlaceInvigilator examPlaceInvigilator = examPlaceInvigilatorMapper.selectOne(examPlaceInvigilatorQueryWrapper);
        return transformEntityIntoDTO(examPlaceInvigilator);
    }

    @Override
    public ExamPlaceInvigilatorDTO getExamPlaceInvigilatorByEmailAndPassword(String email, String password) {
        QueryWrapper<ExamPlaceInvigilator> examPlaceInvigilatorQueryWrapper = new QueryWrapper<>();
        examPlaceInvigilatorQueryWrapper.eq("email", email);
        examPlaceInvigilatorQueryWrapper.eq("password", EncryptUtils.encryptByMD5(password));
        ExamPlaceInvigilator examPlaceInvigilator = examPlaceInvigilatorMapper.selectOne(examPlaceInvigilatorQueryWrapper);
        return transformEntityIntoDTO(examPlaceInvigilator);
    }

    @Override
    public List<ExamPlaceInvigilatorDTO> getExamPlaceInvigilatorList() {
        List<ExamPlaceInvigilatorDTO> examPlaceInvigilatorDTOList = new LinkedList<>();
        examPlaceInvigilatorMapper.selectList(null).stream().forEach( e -> { examPlaceInvigilatorDTOList.add(transformEntityIntoDTO(e)); });
        return examPlaceInvigilatorDTOList;
    }

    @Override
    public List<ExamPlaceInvigilatorDTO> getExamPlaceInvigilatorByPage(int current, int size) {
        List<ExamPlaceInvigilatorDTO> examPlaceInvigilatorDTOList = new LinkedList<>();
        Page<ExamPlaceInvigilator> examPlaceInvigilatorPage = new Page<>(current, size);
        examPlaceInvigilatorMapper.selectPage(examPlaceInvigilatorPage, null);
        examPlaceInvigilatorPage.getRecords().stream().forEach( e -> { examPlaceInvigilatorDTOList.add(transformEntityIntoDTO(e)); });
        return examPlaceInvigilatorDTOList;
    }

    @Override
    public int updateExamPlaceInvigilatorPasswordById(long id, String password) {
        ExamPlaceInvigilator examPlaceInvigilator = ExamPlaceInvigilator.builder()
                .id(id)
                .password(EncryptUtils.encryptByMD5(password))
                .build();
        int result = examPlaceInvigilatorMapper.updateById(examPlaceInvigilator);
        return result;
    }

    @Override
    public int updateExamPlaceInvigilatorInfoById(ExamPlaceInvigilator examPlaceInvigilator) {
        int result = examPlaceInvigilatorMapper.updateById(examPlaceInvigilator);
        return result;
    }

    @Override
    public int deleteExamPlaceInvigilatorById(Long id) {
        int result = examPlaceInvigilatorMapper.deleteById(id);
        return result;
    }

    @Override
    public int deleteBatchExamPlaceInvigilatorById(List<Long> idList) {
        int result = examPlaceInvigilatorMapper.deleteBatchIds(idList);
        return result;
    }

    /**
     * <p>po -> dto</p>
     * @param examPlaceInvigilator
     * @return examPlaceInvigilatorDTO
     */
    private ExamPlaceInvigilatorDTO transformEntityIntoDTO(ExamPlaceInvigilator examPlaceInvigilator) {
        if (ObjectUtils.isEmpty(examPlaceInvigilator) || ObjectUtils.isEmpty(examPlaceInvigilator.getRoleId())) {
            return null;
        }
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.eq("id", examPlaceInvigilator.getRoleId());
        Role role = roleMapper.selectOne(roleQueryWrapper);
        QueryWrapper<ExamPlace> examPlaceQueryWrapper = new QueryWrapper<>();
        examPlaceQueryWrapper.eq("id", examPlaceInvigilator.getExamPlaceId());
        ExamPlace examPlace = examPlaceMapper.selectOne(examPlaceQueryWrapper);
        return ExamPlaceInvigilatorConverter.INSTANCE.entityToDTO(examPlaceInvigilator, role, examPlace);
    }

}
