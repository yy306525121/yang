package cn.codeyang.yang.module.member.convert.level;

import cn.codeyang.yang.framework.common.pojo.PageResult;
import cn.codeyang.yang.module.member.controller.admin.level.vo.experience.MemberExperienceRecordRespVO;
import cn.codeyang.yang.module.member.controller.app.level.vo.experience.AppMemberExperienceRecordRespVO;
import cn.codeyang.yang.module.member.dal.dataobject.level.MemberExperienceRecordDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 会员经验记录 Convert
 *
 * @author owen
 */
@Mapper
public interface MemberExperienceRecordConvert {

    MemberExperienceRecordConvert INSTANCE = Mappers.getMapper(MemberExperienceRecordConvert.class);

    MemberExperienceRecordRespVO convert(MemberExperienceRecordDO bean);

    List<MemberExperienceRecordRespVO> convertList(List<MemberExperienceRecordDO> list);

    PageResult<MemberExperienceRecordRespVO> convertPage(PageResult<MemberExperienceRecordDO> page);

    MemberExperienceRecordDO convert(Long userId, Integer experience, Integer totalExperience,
                                     String bizId, Integer bizType,
                                     String title, String description);

    PageResult<AppMemberExperienceRecordRespVO> convertPage02(PageResult<MemberExperienceRecordDO> page);

}
