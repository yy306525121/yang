package cn.codeyang.yang.module.erp.dal.mysql.product;

import cn.codeyang.yang.framework.common.pojo.PageResult;
import cn.codeyang.yang.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.codeyang.yang.framework.mybatis.core.mapper.BaseMapperX;
import cn.codeyang.yang.module.erp.controller.admin.product.vo.unit.ErpProductUnitPageReqVO;
import cn.codeyang.yang.module.erp.dal.dataobject.product.ErpProductUnitDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ERP 产品单位 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ErpProductUnitMapper extends BaseMapperX<ErpProductUnitDO> {

    default PageResult<ErpProductUnitDO> selectPage(ErpProductUnitPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ErpProductUnitDO>()
                .likeIfPresent(ErpProductUnitDO::getName, reqVO.getName())
                .eqIfPresent(ErpProductUnitDO::getStatus, reqVO.getStatus())
                .orderByDesc(ErpProductUnitDO::getId));
    }

    default ErpProductUnitDO selectByName(String name) {
        return selectOne(ErpProductUnitDO::getName, name);
    }

    default List<ErpProductUnitDO> selectListByStatus(Integer status) {
        return selectList(ErpProductUnitDO::getStatus, status);
    }

}