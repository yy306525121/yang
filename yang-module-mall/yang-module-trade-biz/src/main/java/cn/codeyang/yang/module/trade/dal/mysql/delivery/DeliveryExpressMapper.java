package cn.codeyang.yang.module.trade.dal.mysql.delivery;

import cn.codeyang.yang.framework.common.pojo.PageResult;
import cn.codeyang.yang.framework.mybatis.core.mapper.BaseMapperX;
import cn.codeyang.yang.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.codeyang.yang.module.trade.controller.admin.delivery.vo.express.DeliveryExpressExportReqVO;
import cn.codeyang.yang.module.trade.controller.admin.delivery.vo.express.DeliveryExpressPageReqVO;
import cn.codeyang.yang.module.trade.dal.dataobject.delivery.DeliveryExpressDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeliveryExpressMapper extends BaseMapperX<DeliveryExpressDO> {

    default PageResult<DeliveryExpressDO> selectPage(DeliveryExpressPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeliveryExpressDO>()
                .likeIfPresent(DeliveryExpressDO::getCode, reqVO.getCode())
                .likeIfPresent(DeliveryExpressDO::getName, reqVO.getName())
                .eqIfPresent(DeliveryExpressDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(DeliveryExpressDO::getCreateTime, reqVO.getCreateTime())
                .orderByAsc(DeliveryExpressDO::getSort));
    }

    default List<DeliveryExpressDO> selectList(DeliveryExpressExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DeliveryExpressDO>()
                .likeIfPresent(DeliveryExpressDO::getCode, reqVO.getCode())
                .likeIfPresent(DeliveryExpressDO::getName, reqVO.getName())
                .eqIfPresent(DeliveryExpressDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(DeliveryExpressDO::getCreateTime, reqVO.getCreateTime())
                .orderByAsc(DeliveryExpressDO::getSort));
    }

    default DeliveryExpressDO selectByCode(String code) {
        return selectOne(new LambdaQueryWrapper<DeliveryExpressDO>()
                .eq(DeliveryExpressDO::getCode, code));
    }

    default List<DeliveryExpressDO> selectListByStatus(Integer status) {
        return selectList(DeliveryExpressDO::getStatus, status);
    }

}




