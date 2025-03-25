package cn.codeyang.yang.module.statistics.service.product;

import cn.codeyang.yang.framework.common.pojo.PageResult;
import cn.codeyang.yang.framework.common.pojo.SortablePageParam;
import cn.codeyang.yang.module.statistics.controller.admin.common.vo.DataComparisonRespVO;
import cn.codeyang.yang.module.statistics.controller.admin.product.vo.ProductStatisticsReqVO;
import cn.codeyang.yang.module.statistics.controller.admin.product.vo.ProductStatisticsRespVO;
import cn.codeyang.yang.module.statistics.dal.dataobject.product.ProductStatisticsDO;

import java.util.List;

/**
 * 商品统计 Service 接口
 *
 * @author owen
 */
public interface ProductStatisticsService {

    /**
     * 获得商品统计排行榜分页
     *
     * @param reqVO     查询条件
     * @param pageParam 分页排序查询
     * @return 商品统计分页
     */
    PageResult<ProductStatisticsDO> getProductStatisticsRankPage(ProductStatisticsReqVO reqVO, SortablePageParam pageParam);

    /**
     * 获得商品状况统计分析
     *
     * @param reqVO 查询条件
     * @return 统计数据对照
     */
    DataComparisonRespVO<ProductStatisticsRespVO> getProductStatisticsAnalyse(ProductStatisticsReqVO reqVO);

    /**
     * 获得商品状况明细
     *
     * @param reqVO 查询条件
     * @return 统计数据对照
     */
    List<ProductStatisticsDO> getProductStatisticsList(ProductStatisticsReqVO reqVO);

    /**
     * 统计指定天数的商品数据
     *
     * @return 统计结果
     */
    String statisticsProduct(Integer days);

}