package top.parak.examarrangementsystem.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import top.parak.examarrangementsystem.common.ServerResponse;
import org.springframework.web.multipart.MultipartFile;
import top.parak.examarrangementsystem.common.HttpStatus;
import top.parak.examarrangementsystem.config.QiNiuCloudConfigure;
import top.parak.examarrangementsystem.dto.ExamPlaceAdminDTO;
import top.parak.examarrangementsystem.dto.ExamPlaceInvigilatorDTO;
import top.parak.examarrangementsystem.dto.RecruitAdminDTO;
import top.parak.examarrangementsystem.dto.RecruitApproverDTO;
import top.parak.examarrangementsystem.entity.ExamPlaceAdmin;
import top.parak.examarrangementsystem.entity.ExamPlaceInvigilator;
import top.parak.examarrangementsystem.entity.RecruitAdmin;
import top.parak.examarrangementsystem.entity.RecruitApprover;
import top.parak.examarrangementsystem.service.ExamPlaceAdminService;
import top.parak.examarrangementsystem.service.ExamPlaceInvigilatorService;
import top.parak.examarrangementsystem.service.RecruitAdminService;
import top.parak.examarrangementsystem.service.RecruitApproverService;
import top.parak.examarrangementsystem.util.FileUtils;
import top.parak.examarrangementsystem.util.QiNiuUtils;
import top.parak.examarrangementsystem.util.SnowFlakeIDUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.controller </p>
 * <p> FileName: FileController <p>
 * <p> Description: <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/4
 */

@Api("文件")
@Slf4j
@Controller
@RequestMapping("/api")
public class FileController {

    @Autowired
    private QiNiuCloudConfigure qiNiuCloudConfigure;

    @Autowired
    private QiNiuUtils qiNiuUtils;

    @Autowired
    private RecruitAdminService recruitAdminService;

    @Autowired
    private RecruitApproverService recruitApproverService;

    @Autowired
    private ExamPlaceAdminService examPlaceAdminService;

    @Autowired
    private ExamPlaceInvigilatorService examPlaceInvigilatorService;

    /**
     * <p>上传头像</p>
     * 不是默认头像则删除，然后以指定名称上传七牛云
     *
     * @param multipartFile
     * @param request
     * @return
     * @throws IOException
     * @apiNote 头像命名 avatar_uuid 后缀同原文件
     */
    @ApiOperation(value = "上传头像")
    @PostMapping("/avatar/upload")
    @ResponseBody
    public ServerResponse uploadAvatar(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
        /* 文件校验 */
        String suffix = FileUtils.getFileSuffix(multipartFile.getOriginalFilename());
        String mime = FileUtils.getContentType(multipartFile.getOriginalFilename());
        if (!(suffix.equals(".png") || suffix.equals(".jpg") || suffix.equals(".jpeg"))) {
            return ServerResponse.clientErrorResponse("暂不支持" + suffix + "格式文件，请重新选择文件");
        }
        String avatarName = "avatar_" + SnowFlakeIDUtils.nextID() + suffix;
        /* 用户处理 */
        String token = request.getHeader("Authorization");
        DecodedJWT decodedJWT = JWT.decode(token);
        Long id = decodedJWT.getClaim("id").asLong();
        Integer roleId = decodedJWT.getClaim("roleId").asInt();
        int result = 0;
        switch (roleId) {
            case 1:
                RecruitAdminDTO recruitAdminInMySQL = recruitAdminService.getRecruitAdminById(id);
                if (ObjectUtils.isEmpty(recruitAdminInMySQL)) {
                    return ServerResponse.dataResponse(HttpStatus.USER_ACCOUNT_NOT_EXISTS);
                }
                if (recruitAdminInMySQL.getAvatar().startsWith("avatar"))  {
                    qiNiuUtils.removeFile(recruitAdminInMySQL.getAvatar());
                }
                result = recruitAdminService.updateRecruitAdminInfoById(RecruitAdmin.builder().id(id).avatar(avatarName).build());
                break;
            case 2:
                RecruitApproverDTO recruitApproverInMySQL = recruitApproverService.getRecruitApproverById(id);
                if (ObjectUtils.isEmpty(recruitApproverInMySQL)) {
                    return ServerResponse.dataResponse(HttpStatus.USER_ACCOUNT_NOT_EXISTS);
                }
                if (recruitApproverInMySQL.getAvatar().startsWith("avatar")) {
                    qiNiuUtils.removeFile(recruitApproverInMySQL.getAvatar());
                }
                result = recruitApproverService.updateRecruitApproverInfoById(RecruitApprover.builder().id(id).avatar(avatarName).build());
                break;
            case 3:
                ExamPlaceAdminDTO examPlaceAdminInMySQL = examPlaceAdminService.getExamPlaceAdminById(id);
                if (ObjectUtils.isEmpty(examPlaceAdminInMySQL)) {
                    return ServerResponse.dataResponse(HttpStatus.USER_ACCOUNT_NOT_EXISTS);
                }
                if (examPlaceAdminInMySQL.getAvatar().startsWith("avatar")) {
                    qiNiuUtils.removeFile(examPlaceAdminInMySQL.getAvatar());
                }
                result = examPlaceAdminService.updateExamPlaceAdminInfoById(ExamPlaceAdmin.builder().id(id).avatar(avatarName).build());
                break;
            case 4:
                ExamPlaceInvigilatorDTO examPlaceInvigilatorInMySQL = examPlaceInvigilatorService.getExamPlaceInvigilatorById(id);
                if (ObjectUtils.isEmpty(examPlaceInvigilatorInMySQL)) {
                    return ServerResponse.dataResponse(HttpStatus.USER_ACCOUNT_NOT_EXISTS);
                }
                if (examPlaceInvigilatorInMySQL.getAvatar().startsWith("avatar")) {
                    qiNiuUtils.removeFile(examPlaceInvigilatorInMySQL.getAvatar());
                }
                result = examPlaceInvigilatorService.updateExamPlaceInvigilatorInfoById(ExamPlaceInvigilator.builder().id(id).avatar(avatarName).build());
                break;
            case 5:
                return ServerResponse.clientErrorResponse("无效角色ID");
        }
        if (result == 1) {
            try {
                qiNiuUtils.uploadFile(multipartFile.getInputStream(), avatarName, mime);
                return ServerResponse.successResponse(avatarName);
            } catch (IOException e) {
                log.error(e.getMessage());
                return ServerResponse.serverErrorResponse(e.getMessage());
            }
        } else {
            return ServerResponse.serverErrorResponse("修改头像失败");
        }
    }

    /**
     * <p>客户端从七牛云下载文件</p>
     * @param fileName
     * @param response
     * @return
     * @apiNote 目前仅支持pdf和xlsx文件下载
     */
    @ApiOperation(value = "下载文件")
    @GetMapping("/file/download")
    public void downloadFile(@RequestParam("file") String fileName, HttpServletResponse response) {
        String urlStr = "http://" + qiNiuCloudConfigure.getDomain() + "/" + fileName;
        try {
            response.reset();
            response.setContentType(FileUtils.getContentType(fileName));
            response.setCharacterEncoding("UTF-8");
            response.setContentType(FileUtils.getContentType(fileName));
            response.setHeader("Content-Disposition", "filename=" + URLEncoder.encode(fileName, "UTF-8"));

            URL url = new URL(urlStr);
            InputStream inputStream = url.openConnection().getInputStream();
            ServletOutputStream outputStream = response.getOutputStream();

            byte[] bytes = new byte[1024];
            int temp = 0;
            while ((temp = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, temp);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
