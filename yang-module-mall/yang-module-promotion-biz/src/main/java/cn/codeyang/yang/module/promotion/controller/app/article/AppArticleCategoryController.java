package cn.codeyang.yang.module.promotion.controller.app.article;

import cn.codeyang.yang.framework.common.enums.CommonStatusEnum;
import cn.codeyang.yang.framework.common.pojo.CommonResult;
import cn.codeyang.yang.module.promotion.controller.app.article.vo.category.AppArticleCategoryRespVO;
import cn.codeyang.yang.module.promotion.convert.article.ArticleCategoryConvert;
import cn.codeyang.yang.module.promotion.dal.dataobject.article.ArticleCategoryDO;
import cn.codeyang.yang.module.promotion.service.article.ArticleCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.Comparator;
import java.util.List;

import static cn.codeyang.yang.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 APP - 文章分类")
@RestController
@RequestMapping("/promotion/article-category")
@Validated
public class AppArticleCategoryController {

    @Resource
    private ArticleCategoryService articleCategoryService;

    @RequestMapping("/list")
    @Operation(summary = "获得文章分类列表")
    public CommonResult<List<AppArticleCategoryRespVO>> getArticleCategoryList() {
        List<ArticleCategoryDO> categoryList = articleCategoryService.getArticleCategoryListByStatus(
                CommonStatusEnum.ENABLE.getStatus());
        categoryList.sort(Comparator.comparing(ArticleCategoryDO::getSort)); // 按 sort 降序排列
        return success(ArticleCategoryConvert.INSTANCE.convertList04(categoryList));
    }

}
