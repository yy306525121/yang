package cn.codeyang.yang.module.mp.convert.account;

import cn.codeyang.yang.framework.common.pojo.PageResult;
import cn.codeyang.yang.module.mp.controller.admin.account.vo.MpAccountCreateReqVO;
import cn.codeyang.yang.module.mp.controller.admin.account.vo.MpAccountRespVO;
import cn.codeyang.yang.module.mp.controller.admin.account.vo.MpAccountSimpleRespVO;
import cn.codeyang.yang.module.mp.controller.admin.account.vo.MpAccountUpdateReqVO;
import cn.codeyang.yang.module.mp.dal.dataobject.account.MpAccountDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MpAccountConvert {

    MpAccountConvert INSTANCE = Mappers.getMapper(MpAccountConvert.class);

    MpAccountDO convert(MpAccountCreateReqVO bean);

    MpAccountDO convert(MpAccountUpdateReqVO bean);

    MpAccountRespVO convert(MpAccountDO bean);

    List<MpAccountRespVO> convertList(List<MpAccountDO> list);

    PageResult<MpAccountRespVO> convertPage(PageResult<MpAccountDO> page);

    List<MpAccountSimpleRespVO> convertList02(List<MpAccountDO> list);

}
