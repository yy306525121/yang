package cn.codeyang.yang.module.member.controller.app.signin;

import cn.codeyang.yang.framework.common.pojo.CommonResult;
import cn.codeyang.yang.framework.common.pojo.PageParam;
import cn.codeyang.yang.framework.common.pojo.PageResult;
import cn.codeyang.yang.module.member.controller.app.signin.vo.record.AppMemberSignInRecordRespVO;
import cn.codeyang.yang.module.member.controller.app.signin.vo.record.AppMemberSignInRecordSummaryRespVO;
import cn.codeyang.yang.module.member.convert.signin.MemberSignInRecordConvert;
import cn.codeyang.yang.module.member.dal.dataobject.signin.MemberSignInRecordDO;
import cn.codeyang.yang.module.member.service.signin.MemberSignInRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.codeyang.yang.framework.common.pojo.CommonResult.success;
import static cn.codeyang.yang.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "用户 App - 签到记录")
@RestController
@RequestMapping("/member/sign-in/record")
@Validated
public class AppMemberSignInRecordController {

    @Resource
    private MemberSignInRecordService signInRecordService;

    @GetMapping("/get-summary")
    @Operation(summary = "获得个人签到统计")
    public CommonResult<AppMemberSignInRecordSummaryRespVO> getSignInRecordSummary() {
        return success(signInRecordService.getSignInRecordSummary(getLoginUserId()));
    }

    @PostMapping("/create")
    @Operation(summary = "签到")
    public CommonResult<AppMemberSignInRecordRespVO> createSignInRecord() {
        MemberSignInRecordDO recordDO = signInRecordService.createSignRecord(getLoginUserId());
        return success(MemberSignInRecordConvert.INSTANCE.coverRecordToAppRecordVo(recordDO));
    }

    @GetMapping("/page")
    @Operation(summary = "获得签到记录分页")
    public CommonResult<PageResult<AppMemberSignInRecordRespVO>> getSignRecordPage(PageParam pageParam) {
        PageResult<MemberSignInRecordDO> pageResult = signInRecordService.getSignRecordPage(getLoginUserId(), pageParam);
        return success(MemberSignInRecordConvert.INSTANCE.convertPage02(pageResult));
    }

}
