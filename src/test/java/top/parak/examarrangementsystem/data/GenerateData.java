package top.parak.examarrangementsystem.data;

import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.parak.examarrangementsystem.dto.ExamPlaceInvigilatorDTO;
import top.parak.examarrangementsystem.dto.RecordExamDTO;
import top.parak.examarrangementsystem.dto.RecordSubmitExamPlaceInvigilatorDTO;
import top.parak.examarrangementsystem.entity.*;
import top.parak.examarrangementsystem.generator.IdentityNumberGenerator;
import top.parak.examarrangementsystem.generator.RandomInfoGenerator;
import top.parak.examarrangementsystem.mapper.RecordSubmitExamPlaceInvigilatorMapper;
import top.parak.examarrangementsystem.service.*;
import top.parak.examarrangementsystem.util.EncryptUtils;
import top.parak.examarrangementsystem.util.SnowFlakeIDUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p> Project: examArrangementSystem </p>
 * <p> Package: top.parak.examarrangementsystem.data </p>
 * <p> FileName: GenerateData <p>
 * <p> Description: <p>
 * <p> Created By IntelliJ IDEA </p>
 *
 * @author KHighness
 * @since 2021/1/5
 */

@SpringBootTest
public class GenerateData {

    @Autowired
    private RecruitAdminService recruitAdminService;

    @Autowired
    private RecruitApproverService recruitApproverService;

    @Autowired
    private ExamPlaceService examPlaceService;

    @Autowired
    private ExamPlaceAdminService examPlaceAdminService;

    @Autowired
    private ExamPlaceInvigilatorService examPlaceInvigilatorService;

    @Autowired
    private ExamPlaceRoomService examPlaceRoomService;

    @Autowired
    private RecordExamService recordExamService;

    @Autowired
    private RecordTaskExamPlaceService recordTaskExamPlaceService;

    @Autowired
    private RecordExamPlaceInvigilatorService recordExamPlaceInvigilatorService;

    @Autowired
    private RecordInvigilatorService recordInvigilatorService;

    @Autowired
    private RecordHistoricalViolationService recordHistoricalViolationService;

    @Autowired
    private RecordSubmitExamPlaceInvigilatorService recordSubmitExamPlaceInvigilatorService;

    @Autowired
    private RecordSubmitExamPlaceRoomService recordSubmitExamPlaceRoomService;

    @Autowired
    private RecordSubmitExamPlaceInvigilatorMapper recordSubmitExamPlaceInvigilatorMapper;

    @Autowired
    private RecordExamSubjectInvigilatorGroupService recordExamSubjectInvigilatorGroupService;

    @Autowired
    private RecordTaskExamInvigilatorService recordTaskExamInvigilatorService;

    @Autowired
    private RecordExamSubjectService recordExamSubjectService;
    @Test
    void generateRecruitAdminData() {
        recruitAdminService.saveRecruitAdmin("182367672@qq.com", "CZK666");
        recruitAdminService.saveRecruitAdmin("1102174906@qq.com", "WFS666");
        recruitAdminService.saveRecruitAdmin("2298148442@qq.com", "HXX666");
        recruitAdminService.saveRecruitAdmin("3119864980@qq.com", "CHZ666");
        recruitAdminService.saveRecruitAdmin("1783122089@qq.com", "QKY666");
    }

    @Test
    void generateRecruitApproverData() {
        recruitApproverService.saveRecruitApprover(RecruitApprover.builder().email("182367672@gmail.com").password("CZK666").name("CZK").gender("男").build());
        recruitApproverService.saveRecruitApprover(RecruitApprover.builder().email("1102174906@gmail.com").password("WFS666").name("WFS").gender("男").build());
        recruitApproverService.saveRecruitApprover(RecruitApprover.builder().email("2298148442@gmail.com").password("HXX666").name("HX").gender("男").build());
        recruitApproverService.saveRecruitApprover(RecruitApprover.builder().email("3119864980@gmail.com").password("CHZ666").name("CHX").gender("男").build());
        recruitApproverService.saveRecruitApprover(RecruitApprover.builder().email("1783122089@gmail.com").password("QKY666").name("QKY").gender("男").build());
    }

    @Test
    void generateExamPlaceData() {
        examPlaceService.saveExamPlace("合肥工业大学附中", "安徽省示范性高级中学", "合肥市包河区宁国南路103号");
        examPlaceService.saveExamPlace("合肥一六八中学", "安徽省示范普通高中", "安徽省合肥市蜀山区始信路179号");
        examPlaceService.saveExamPlace("合肥市第一中学", "安徽省重点中学、联合国教科文组织俱乐部成员、安徽省示范性普通高级中学", "安徽省合肥市滨湖新区西藏路2356号");
        examPlaceService.saveExamPlace("合肥市第二中学", "安徽省示范高中", "安徽省合肥市包河区曙光路60号");
        examPlaceService.saveExamPlace("合肥市第三中学", "安徽省省级示范高中", "合肥市庐阳区宿州路196号");
        examPlaceService.saveExamPlace("合肥市第四中学", "安徽省省级示范高中", "安徽省合肥市天津路与迎淮路交汇处西南角");
        examPlaceService.saveExamPlace("合肥市第五中学", "安徽省示范性高中", "安徽省合肥市瑶海区长江东路789号");
        examPlaceService.saveExamPlace("合肥市第六中学", "安徽省重点中学", "安徽省合肥市庐阳区寿春路252号");
        examPlaceService.saveExamPlace("合肥市第七中学", "安徽省示范性普通高中", "合肥市蜀山区望江西路898号");
        examPlaceService.saveExamPlace("合肥市第八中学", "安徽省重点中学", "安徽省合肥市蜀山区政务文化新区习友路1688号");
        examPlaceService.saveExamPlace("合肥市第九中学", "安徽省语言文字示范单位", "安徽省合肥市庐阳区长江中路89号");
        examPlaceService.saveExamPlace("合肥市第十中学", "安徽省教育工会工作先进集体", "安徽省合肥市瑶海区新安江路与王岗路交口东北角");
        examPlaceService.saveExamPlace("合肥市第十一中学", "安徽省示范性普通高级中学", "安徽省合肥市合肥市瑶海区凤阳路470号");
        examPlaceService.saveExamPlace("合肥第三十二中学", "安徽省示范性普通高级中学", "安徽省合肥市包河区青年路43号");
        examPlaceService.saveExamPlace("合肥市庐阳高级中学", "安徽省示范性普通高级中学", "安徽省合肥市亳州路280号");
    }

    @Test
    void generateExamPlaceAdminData() {
        for (int i = 1; i <= 18; i++) {
            String email = i % 3 != 0 ? RandomInfoGenerator.getQQEmail(9, 11) : RandomInfoGenerator.getGmailOr163OrSinaEmail(9, 11);
            String[] sexAndChineseName =  RandomInfoGenerator.getSexAndChineseName();
            Date birth = RandomInfoGenerator.getBirth(1970, 1995);
            String phone = RandomInfoGenerator.getTelephone();
            Long examPlaceId = Long.parseLong(String.valueOf(i));
            ExamPlaceAdmin examPlaceAdmin = ExamPlaceAdmin.builder()
                    .email(email)
                    .password("123456")
                    .gender(sexAndChineseName[0])
                    .name(sexAndChineseName[1])
                    .birth(birth)
                    .phone(phone)
                    .examPlaceId(examPlaceId)
                    .build();
            examPlaceAdminService.saveExamPlaceAdmin(examPlaceAdmin);
        }
    }

    @Test
    void generateExamPlaceInvigilatorData() {
        for (int i = 1; i <= 15; i++) {
            for (int j = 1; j <= 100; j++) {
                String email = j % 3 != 0 ? RandomInfoGenerator.getQQEmail(9, 11) : RandomInfoGenerator.getGmailOr163OrSinaEmail(9, 11);
                String password = RandomInfoGenerator.getPassword(6, 16);
                String[] sexAndChineseName =  RandomInfoGenerator.getSexAndChineseName();
                Date birth = RandomInfoGenerator.getBirth(1970, 1995);
                String phone = RandomInfoGenerator.getTelephone();
                Long examPlaceId = Long.parseLong(String.valueOf(i));
                ExamPlaceInvigilator examPlaceInvigilator = ExamPlaceInvigilator.builder()
                        .email(email)
                        .password(EncryptUtils.encryptByMD5("123456"))
                        .gender(sexAndChineseName[0])
                        .name(sexAndChineseName[1])
                        .birth(birth)
                        .phone(phone)
                        .roleId(4)
                        .examPlaceId(examPlaceId)
                        .build();
                examPlaceInvigilatorService.saveExamPlaceInvigilator(examPlaceInvigilator);
            }
        }
    }

    @Test
    void generateExamPlaceRoomData() {
        String[] building = new String[]{"一教", "二教", "三教", "四教", "五教", "六教"};
        String[] classroom = new String[]{"01", "02", "03", "04", "05"};
        for (int i = 1; i <= 15; i++) {
            for (int j = 0; j < building.length; j++) {
                for (int k = 1; k <= 5; k++) {
                    for (int x = 0; x < classroom.length; x++) {
                        Long id = Long.parseLong(String.valueOf(i));
                        String name = building[j] + k + classroom[x];
                        examPlaceRoomService.saveExamPlaceRoom(name, 1, id);
                    }
                }
            }
        }
    }

    @Test
    void generateRecordExamData() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String startTme = "-06-07";
//        String endTme = "-06-08";
//        for (int i = 2012; i <= 2021; i++) {
//            RecordExam recordExam = RecordExam.builder()
//                    .serialNumber(String.valueOf(SnowFlakeIDUtils.nextID()))
//                    .name(i + "年普通高等学校招生全国统一考试")
//                    .sponsor(RandomInfoGenerator.getSexAndChineseName()[1])
//                    .startTime(simpleDateFormat.parse(i + startTme))
//                    .endTime(simpleDateFormat.parse(i + endTme))
//                    .scienceExamineeNumber(20000)
//                    .liberalArtsExamineeNumber(5000)
//                    .scienceRoomNumber(1000)
//                    .liberalArtsRoomNumber(250)
//                    .arranged(true)
//                    .build();
//            recordExamService.saveRecordExam(recordExam);
//        }
        String[] name = new String[] {"皖南八校联考", "合肥一模", "合肥二模", "合肥三模", "2021年普通高等学校招生全国统一考试"};
        String[][] date = new String[][] { {"2021-03-15", "2021-03-16"}, {"2021-04-01", "2021-04-02"}, {"2021-04-15", "2021-04-16"}, {"2021-05-02", "2021-05-03"}, {"2021-6-07", "2021-06-08"}};
        for (int i = 0; i < 5; i++) {
            RecordExam recordExam = RecordExam.builder()
                    .serialNumber(String.valueOf(SnowFlakeIDUtils.nextID()))
                    .name(name[i])
                    .sponsor(RandomInfoGenerator.getSexAndChineseName()[1])
                    .startTime(simpleDateFormat.parse(date[i][0]))
                    .endTime(simpleDateFormat.parse(date[i][1]))
                    .scienceExamineeNumber(20000)
                    .liberalArtsExamineeNumber(5000)
                    .scienceRoomNumber(6667)
                    .liberalArtsRoomNumber(167)
                    .build();
            recordExamService.saveRecordExam(recordExam);
        }
    }

    @Test
    void generateRecordExamSubjectData() {
        SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        recordExamService.getRecordExamList().stream().forEach(
            exam -> {
                Long examId = exam.getId();
                String startTime = null, endTime = null;
                try {
                    startTime = dateSdf.format(dateSdf.parse(exam.getStartTime()));
                    endTime = dateSdf.format(dateSdf.parse(exam.getEndTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    RecordExamSubject chinese = RecordExamSubject.builder()
                            .serialNumber(String.valueOf(SnowFlakeIDUtils.nextID()))
                            .name("语文")
                            .startTime(timeSdf.parse(startTime + " 09:00:00"))
                            .endTime(timeSdf.parse(startTime + " 11:30:00"))
                            .recordExamId(examId)
                            .build();
                    RecordExamSubject mathematics = RecordExamSubject.builder()
                            .serialNumber(String.valueOf(SnowFlakeIDUtils.nextID()))
                            .name("数学")
                            .startTime(timeSdf.parse(startTime + " 15:00:00"))
                            .endTime(timeSdf.parse(startTime + " 17:00:00"))
                            .recordExamId(examId)
                            .build();
                    RecordExamSubject english = RecordExamSubject.builder()
                            .serialNumber(String.valueOf(SnowFlakeIDUtils.nextID()))
                            .name("英语")
                            .startTime(timeSdf.parse(endTime + " 15:00:00"))
                            .endTime(timeSdf.parse(endTime + " 17:00:00"))
                            .recordExamId(examId)
                            .build();
                    RecordExamSubject comprehensiveLiberalArts = RecordExamSubject.builder()
                            .serialNumber(String.valueOf(SnowFlakeIDUtils.nextID()))
                            .name("文综")
                            .startTime(timeSdf.parse(endTime + " 09:00:00"))
                            .endTime(timeSdf.parse(endTime + " 11:30:00"))
                            .recordExamId(examId)
                            .build();
                    RecordExamSubject comprehensiveScience = RecordExamSubject.builder()
                            .serialNumber(String.valueOf(SnowFlakeIDUtils.nextID()))
                            .name("理综")
                            .startTime(timeSdf.parse(endTime + " 09:00:00"))
                            .endTime(timeSdf.parse(endTime + " 11:30:00"))
                            .recordExamId(examId)
                            .build();
                    recordExamSubjectService.saveRecordExamSubject(chinese);
                    recordExamSubjectService.saveRecordExamSubject(mathematics);
                    recordExamSubjectService.saveRecordExamSubject(english);
                    recordExamSubjectService.saveRecordExamSubject(comprehensiveLiberalArts);
                    recordExamSubjectService.saveRecordExamSubject(comprehensiveScience);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        );
    }

    @Test
    void generateRecordTaskExamPlaceData() {
        SimpleDateFormat timeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        recordExamService.getRecordExamList().stream().forEach(
            exam -> {
                examPlaceService.getExamPlaceList().stream().forEach(
                    place -> {
                        Long id = place.getId();
                        Integer year = Integer.parseInt(exam.getStartTime().substring(0, 4));
                        Boolean readed = year >= 2020 ? false : true;
                        Boolean finished = year >= 2020 ? false : true;
                        try {
                            RecordTaskExamPlace recordTaskExamPlace = RecordTaskExamPlace.builder()
                                    .serialNumber(String.valueOf(SnowFlakeIDUtils.nextID()))
                                    .type(RandomInfoGenerator.getNum(0, 100) < 75 ? "理科" : "文科")
                                    .examRoomNumber(20)
                                    .minExamRoomSerialNumber((Integer.parseInt(String.valueOf(id)) - 1) * 20 + 1)
                                    .invigilatorNumber(60)
                                    .mainInvigilatorNumber(20)
                                    .maleInvigilatorNumber(30)
                                    .femaleInvigilatorNumber(30)
                                    .deadline(timeSdf.parse(year + "-05-01 00:00:00"))
                                    .readed(readed)
                                    .finished(finished)
                                    .examPlaceId(place.getId())
                                    .recordExamId(exam.getId())
                                    .build();
                            recordTaskExamPlaceService.saveRecordTaskExamPlace(recordTaskExamPlace);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                );
            }
        );
    }

    @Test
    void generateRecordInvigilatorData() throws ParseException {
        String[][] subjectDates = new String[][]{{"语文", "2020-07-07"}, {"数学", "2020-07-07"}, {"英语", "2020-07-08"}, {"文综", "2020-07-08"}, {"理综", "2020-07-08"}};
        String[] remarks = new String[]{"监考玩手机", "未及时收卷", "四处走动", "放屁过多",};
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 1; i <= 1500; i++) {
            Boolean violated = i % 43 == 0;
            String[] subjectDate = subjectDates[RandomInfoGenerator.getNum(0, 4)];
            if (violated) {
                RecordInvigilator recordInvigilator = RecordInvigilator.builder()
                        .serialNumber(String.valueOf(SnowFlakeIDUtils.nextID()))
                        .examPlaceInvigilatorId(Long.parseLong(String.valueOf(i)))
                        .examName("2020年普通高等学校招生全国统一考试")
                        .examSubject(subjectDate[0])
                        .examDate(simpleDateFormat.parse(subjectDate[1]))
                        .main(RandomInfoGenerator.getNum(0, 8) < 3)
                        .finished(RandomInfoGenerator.getNum(0, 100) > 1)
                        .violated(violated)
                        .remark(remarks[RandomInfoGenerator.getNum(0, 3)])
                        .build();
                recordInvigilatorService.saveRecordInvigilator(recordInvigilator);
            } else {
                RecordInvigilator recordInvigilator = RecordInvigilator.builder()
                        .serialNumber(String.valueOf(SnowFlakeIDUtils.nextID()))
                        .examPlaceInvigilatorId(Long.parseLong(String.valueOf(i)))
                        .examName("2020年普通高等学校招生全国统一考试")
                        .examSubject(subjectDate[0])
                        .examDate(simpleDateFormat.parse(subjectDate[1]))
                        .main(RandomInfoGenerator.getNum(0, 8) < 3)
                        .finished(RandomInfoGenerator.getNum(0, 100) > 1)
                        .violated(violated)
                        .build();
                recordInvigilatorService.saveRecordInvigilator(recordInvigilator);
            }
        }
    }

    @Test
    void generateRecordExamPlaceInvigilator() {
        examPlaceInvigilatorService.getExamPlaceInvigilatorList().stream().forEach(
            invigilator -> {
                String[] grades = new String[]{"高一", "高二", "高三"};
                String grade = grades[RandomInfoGenerator.getNum(0, 2)];
                Boolean passed = true;
                if (grade.equals("高三")) {
                    passed = false;
                }
                RecordExamPlaceInvigilator recordExamPlaceInvigilator = RecordExamPlaceInvigilator.builder()
                        .serialNumber(String.valueOf(SnowFlakeIDUtils.nextID()))
                        .identityNumber(IdentityNumberGenerator.generate())
                        .photo(invigilator.getAvatar())
                        .teachingGrade(grade)
                        .invigilateExperience(RandomInfoGenerator.getNum(0, 5))
                        .remark("无")
                        .reviewed(true)
                        .passed(passed)
                        .examPlaceInvigilatorId(invigilator.getId())
                        .recordExamId(1L)
                        .build();
                recordExamPlaceInvigilatorService.saveRecordExamPlaceInvigilator(recordExamPlaceInvigilator);
            }
        );
    }

    @Test
    void generateRecordHistoricalViolation() throws ParseException {
        String examName = "2018年普通高等学校招生全国统一考试";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String[][] subjectDates = new String[][]{{"语文", "2018-06-07"}, {"数学", "2018-06-07"}, {"英语", "2018-06-08"}, {"文综", "2018-06-08"}, {"理综", "2018-06-08"}};
        String[] violations = new String[]{"延时收卷", "四处走动", "提前撕试卷密封袋", "考场内吸烟", "放屁过多"};
        List<ExamPlaceInvigilatorDTO> examPlaceInvigilatorList = examPlaceInvigilatorService.getExamPlaceInvigilatorList();
        for (ExamPlaceInvigilatorDTO examPlaceInvigilatorDTO : examPlaceInvigilatorList) {
            if (examPlaceInvigilatorDTO.getId() % 17 == 0) {
                String[] subjectDate = subjectDates[RandomInfoGenerator.getNum(0, 4)];
                RecordHistoricalViolation recordHistoricalViolation = RecordHistoricalViolation.builder()
                        .name(examPlaceInvigilatorDTO.getName())
                        .identityNumber(IdentityNumberGenerator.generate())
                        .examName(examName)
                        .examSubject(subjectDate[0])
                        .examDate(simpleDateFormat.parse(subjectDate[1]))
                        .violation(violations[RandomInfoGenerator.getNum(0, 4)])
                        .build();
                recordHistoricalViolationService.saveRecordHistoricalViolation(recordHistoricalViolation);
            }
        }
    }

    @Test
    void generateRecordRoomAndInvigilator() {
        RecordExamDTO recordExam = recordExamService.getRecordExamById(1L);
        for (int i = 1; i <= 20; i++) {
            RecordSubmitExamPlaceRoom recordSubmitExamPlaceRoom = RecordSubmitExamPlaceRoom.builder()
                    .id(Long.parseLong(String.valueOf(i)))
                    .examPlaceRoomId(Long.parseLong(String.valueOf(i)))
                    .recordTaskExamPlaceId(1L)
                    .examRoomSerialNumber(Integer.parseInt("2020") + 1000 + i)
                    .remark("无")
                    .reviewed(true)
                    .passed(true)
                    .reason(" ")
                    .build();
            recordSubmitExamPlaceRoomService.saveRecordSubmitExamPlaceRoom(recordSubmitExamPlaceRoom);
        }
//        for (int i = 1; i <= 60; i++) {
//            String serialNumber = String.valueOf(SnowFlakeIDUtils.nextID());
//            RecordSubmitExamPlaceInvigilator recordSubmitExamPlaceInvigilator = RecordSubmitExamPlaceInvigilator.builder()
//                    .recordExamPlaceInvigilatorId(Long.parseLong(String.valueOf(i)))
//                    .recordTaskExamPlaceId(1L)
//                    .serialNumber(serialNumber)
//                    .invigilatorSerialNumber(Integer.parseInt("2020" + serialNumber.substring(13)))
//                    .reviewed(true)
//                    .passed(true)
//                    .main(i % 3 == 0)
//                    .reason(" ")
//                    .build();
//            recordSubmitExamPlaceInvigilatorMapper.insert(recordSubmitExamPlaceInvigilator);
//        }
//        for (int i = 1; i <= 5; i++) {
//            for (int j = 1; j <= 20; j++) {
//                RecordExamSubjectInvigilatorGroup recordExamSubjectInvigilatorGroup = RecordExamSubjectInvigilatorGroup.builder()
//                        .remark("无")
//                        .recordExamSubjectId(Long.parseLong(String.valueOf(i)))
//                        .recordSubmitExamPlaceRoomId(Long.parseLong(String.valueOf(j)))
//                        .build();
//                recordExamSubjectInvigilatorGroupService.saveRecordExamSubjectInvigilatorGroup(recordExamSubjectInvigilatorGroup);
//            }
//        }
//        List<RecordSubmitExamPlaceInvigilatorDTO> passedRecordSubmitExamPlaceInvigilatorList = recordSubmitExamPlaceInvigilatorService.getPassedRecordSubmitExamPlaceInvigilatorList(1L);
//        // 1
//        int gid = 81;
//        int a = 0;
//        for (int i = 3; i <= 62; i++) {
//            if (a==3) {
//                gid++;
//                a = 0;
//            }
//            Long id = Long.parseLong(String.valueOf(i - 2));
//            RecordTaskExamInvigilator recordTaskExamInvigilator = RecordTaskExamInvigilator.builder()
//                    .finished(false)
//                    .reviewed(false)
//                    .submitted(false)
//                    .saved(false)
//                    .violated(false)
//                    .remark("无")
//                    .recordSubmitExamPlaceInvigilatorId(Long.parseLong(String.valueOf(i)))
//                    .recordExamSubjectInvigilatorGroupId(Long.parseLong(String.valueOf(gid)))
//                    .build();
//            recordTaskExamInvigilatorService.saveRecordTaskExamInvigilator(recordTaskExamInvigilator);
//            a++;
//        }

    }

}
