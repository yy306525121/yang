package cn.codeyang.yang.module.infra.dal.mysql.demo.demo03;

import cn.codeyang.yang.framework.common.pojo.PageResult;
import cn.codeyang.yang.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.codeyang.yang.framework.mybatis.core.mapper.BaseMapperX;
import cn.codeyang.yang.module.infra.dal.dataobject.demo.demo03.Demo03StudentDO;
import org.apache.ibatis.annotations.Mapper;
import cn.codeyang.yang.module.infra.controller.admin.demo.demo03.vo.*;

/**
 * 学生 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface Demo03StudentMapper extends BaseMapperX<Demo03StudentDO> {

    default PageResult<Demo03StudentDO> selectPage(Demo03StudentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<Demo03StudentDO>()
                .likeIfPresent(Demo03StudentDO::getName, reqVO.getName())
                .eqIfPresent(Demo03StudentDO::getSex, reqVO.getSex())
                .eqIfPresent(Demo03StudentDO::getDescription, reqVO.getDescription())
                .betweenIfPresent(Demo03StudentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(Demo03StudentDO::getId));
    }

}