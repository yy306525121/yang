package cn.codeyang.yang.module.system.api.mail;

import cn.codeyang.yang.module.system.api.mail.dto.MailSendSingleToUserReqDTO;
import cn.codeyang.yang.module.system.service.mail.MailSendService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

/**
 * 邮件发送 API 实现类
 *
 * @author wangjingyi
 */
@Service
@Validated
public class MailSendApiImpl implements MailSendApi {

    @Resource
    private MailSendService mailSendService;

    @Override
    public Long sendSingleMailToAdmin(MailSendSingleToUserReqDTO reqDTO) {
        return mailSendService.sendSingleMailToAdmin(reqDTO.getMail(), reqDTO.getUserId(),
                reqDTO.getTemplateCode(), reqDTO.getTemplateParams());
    }

    @Override
    public Long sendSingleMailToMember(MailSendSingleToUserReqDTO reqDTO) {
        return mailSendService.sendSingleMailToMember(reqDTO.getMail(), reqDTO.getUserId(),
                reqDTO.getTemplateCode(), reqDTO.getTemplateParams());
    }

}
