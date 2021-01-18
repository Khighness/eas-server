package top.parak.examarrangementsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import top.parak.examarrangementsystem.dto.RecruitApproverDTO;
import top.parak.examarrangementsystem.dto.converter.RecruitApproverConverter;
import top.parak.examarrangementsystem.entity.RecruitApprover;
import top.parak.examarrangementsystem.entity.Role;
import top.parak.examarrangementsystem.mapper.RecruitApproverMapper;
import top.parak.examarrangementsystem.mapper.RoleMapper;
import top.parak.examarrangementsystem.service.RecruitApproverService;
import org.springframework.stereotype.Service;
import top.parak.examarrangementsystem.util.EncryptUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.service.impl </p>
 * <p> FileName: RecruitApproverServiceImpl <p>
 * <p> Description: 招办审批员业务层接口实现 <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/1
 */

@Service
public class RecruitApproverServiceImpl implements RecruitApproverService {

    @Autowired
    private RecruitApproverMapper recruitApproverMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Value("${default.recruitApprover.avatar}")
    private String recruitApproverDefaultAvatar;

    @Value("${default.recruitApprover.name}")
    private String recruitApproverDefaultName;

    @Value("${default.recruitApprover.roleId}")
    private Integer recruitApproverDefaultRoleId;

    @Override
    public int saveRecruitApprover(RecruitApprover recruitApprover) {
        recruitApprover.setPassword(EncryptUtils.encryptByMD5(recruitApprover.getPassword()));
        recruitApprover.setAvatar(ObjectUtils.isEmpty(recruitApprover.getAvatar()) ? recruitApproverDefaultAvatar : recruitApprover.getAvatar());
        recruitApprover.setName(ObjectUtils.isEmpty(recruitApprover.getAvatar()) ? recruitApproverDefaultName : recruitApprover.getName());
        recruitApprover.setRoleId(recruitApproverDefaultRoleId);
        int result = recruitApproverMapper.insert(recruitApprover);
        return result;
    }

    @Override
    public int countOfRecruitApprover() {
        return recruitApproverMapper.selectCount(null);
    }

    @Override
    public RecruitApproverDTO getRecruitApproverById(Long id) {
        QueryWrapper<RecruitApprover> recruitApproverQueryWrapper = new QueryWrapper<>();
        recruitApproverQueryWrapper.eq("id", id);
        RecruitApprover recruitApprover = recruitApproverMapper.selectOne(recruitApproverQueryWrapper);
        return transformEntityIntoDTO(recruitApprover);
    }

    @Override
    public RecruitApproverDTO getRecruitApproverByEmail(String email) {
        QueryWrapper<RecruitApprover> recruitApproverQueryWrapper = new QueryWrapper<>();
        recruitApproverQueryWrapper.eq("email", email);
        RecruitApprover recruitApprover = recruitApproverMapper.selectOne(recruitApproverQueryWrapper);
        return transformEntityIntoDTO(recruitApprover);
    }

    @Override
    public RecruitApproverDTO getRecruitApproverByEmailAndPassword(String email, String password) {
        QueryWrapper<RecruitApprover> recruitApproverQueryWrapper = new QueryWrapper<>();
        recruitApproverQueryWrapper.eq("email", email);
        recruitApproverQueryWrapper.eq("password", EncryptUtils.encryptByMD5(password));
        RecruitApprover recruitApprover = recruitApproverMapper.selectOne(recruitApproverQueryWrapper);
        return transformEntityIntoDTO(recruitApprover);
    }

    @Override
    public List<RecruitApproverDTO> getRecruitApproverList() {
        List<RecruitApproverDTO> recruitApproverDTOList = new LinkedList<>();
        recruitApproverMapper.selectList(null).stream().forEach( e -> { recruitApproverDTOList.add(transformEntityIntoDTO(e)); });
        return recruitApproverDTOList;
    }

    @Override
    public List<RecruitApproverDTO> getRecruitApproverByPage(int current, int size) {
        List<RecruitApproverDTO> recruitApproverDTOList = new LinkedList<>();
        Page<RecruitApprover> recruitApproverPage = new Page<>(current, size);
        recruitApproverPage = recruitApproverMapper.selectPage(recruitApproverPage, null);
        recruitApproverPage.getRecords().stream().forEach( e -> { recruitApproverDTOList.add(transformEntityIntoDTO(e)); });
        return recruitApproverDTOList;
    }

    @Override
    public int updateRecruitApproverPasswordById(Long id, String password) {
        RecruitApprover recruitApprover = RecruitApprover.builder()
                .id(id)
                .password(EncryptUtils.encryptByMD5(password))
                .build();
        int result = recruitApproverMapper.updateById(recruitApprover);
        return result;
    }

    @Override
    public int updateRecruitApproverInfoById(RecruitApprover recruitApprover) {
        int result = recruitApproverMapper.updateById(recruitApprover);
        return result;
    }

    @Override
    public int deleteRecruitApproverById(Long id) {
        int result = recruitApproverMapper.deleteById(id);
        return result;
    }

    /**
     * <p>po -> dto</p>
     * @param recruitApprover
     * @return recruitApproverDTO
     */
    private RecruitApproverDTO transformEntityIntoDTO(RecruitApprover recruitApprover) {
        if (ObjectUtils.isEmpty(recruitApprover) || ObjectUtils.isEmpty(recruitApprover.getRoleId())) {
            return null;
        }
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.eq("id", recruitApprover.getRoleId());
        Role role = roleMapper.selectOne(roleQueryWrapper);
        return RecruitApproverConverter.INSTANCE.entityToDTO(recruitApprover, role);
    }
}
