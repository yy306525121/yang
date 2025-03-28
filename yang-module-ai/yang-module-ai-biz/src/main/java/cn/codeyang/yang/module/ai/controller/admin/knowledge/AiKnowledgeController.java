package cn.codeyang.yang.module.ai.controller.admin.knowledge;

import cn.codeyang.yang.framework.common.pojo.CommonResult;
import cn.codeyang.yang.framework.common.pojo.PageResult;
import cn.codeyang.yang.framework.common.util.object.BeanUtils;
import cn.codeyang.yang.module.ai.controller.admin.knowledge.vo.knowledge.AiKnowledgeCreateReqVO;
import cn.codeyang.yang.module.ai.controller.admin.knowledge.vo.knowledge.AiKnowledgePageReqVO;
import cn.codeyang.yang.module.ai.controller.admin.knowledge.vo.knowledge.AiKnowledgeRespVO;
import cn.codeyang.yang.module.ai.controller.admin.knowledge.vo.knowledge.AiKnowledgeUpdateReqVO;
import cn.codeyang.yang.module.ai.dal.dataobject.knowledge.AiKnowledgeDO;
import cn.codeyang.yang.module.ai.service.knowledge.AiKnowledgeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.codeyang.yang.framework.common.pojo.CommonResult.success;
import static cn.codeyang.yang.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "管理后台 - AI 知识库")
@RestController
@RequestMapping("/ai/knowledge")
@Validated
public class AiKnowledgeController {

    @Resource
    private AiKnowledgeService knowledgeService;

    @GetMapping("/page")
    @Operation(summary = "获取知识库分页")
    public CommonResult<PageResult<AiKnowledgeRespVO>> getKnowledgePage(@Valid AiKnowledgePageReqVO pageReqVO) {
        PageResult<AiKnowledgeDO> pageResult = knowledgeService.getKnowledgePage(getLoginUserId(), pageReqVO);
        return success(BeanUtils.toBean(pageResult, AiKnowledgeRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建知识库")
    public CommonResult<Long> createKnowledge(@RequestBody @Valid AiKnowledgeCreateReqVO createReqVO) {
        return success(knowledgeService.createKnowledge(createReqVO, getLoginUserId()));
    }

    @PutMapping("/update")
    @Operation(summary = "更新知识库")
    public CommonResult<Boolean> updateKnowledge(@RequestBody @Valid AiKnowledgeUpdateReqVO updateReqVO) {
        knowledgeService.updateKnowledge(updateReqVO, getLoginUserId());
        return success(true);
    }
}
