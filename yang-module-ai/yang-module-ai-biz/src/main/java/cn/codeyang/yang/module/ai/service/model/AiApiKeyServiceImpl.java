package cn.codeyang.yang.module.ai.service.model;

import cn.codeyang.yang.framework.ai.core.enums.AiPlatformEnum;
import cn.codeyang.yang.framework.ai.core.factory.AiModelFactory;
import cn.codeyang.yang.framework.ai.core.model.midjourney.api.MidjourneyApi;
import cn.codeyang.yang.framework.ai.core.model.suno.api.SunoApi;
import cn.codeyang.yang.framework.common.enums.CommonStatusEnum;
import cn.codeyang.yang.framework.common.pojo.PageResult;
import cn.codeyang.yang.framework.common.util.object.BeanUtils;
import cn.codeyang.yang.module.ai.controller.admin.model.vo.apikey.AiApiKeyPageReqVO;
import cn.codeyang.yang.module.ai.controller.admin.model.vo.apikey.AiApiKeySaveReqVO;
import cn.codeyang.yang.module.ai.dal.dataobject.model.AiApiKeyDO;
import cn.codeyang.yang.module.ai.dal.mysql.model.AiApiKeyMapper;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.codeyang.yang.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.codeyang.yang.module.ai.enums.ErrorCodeConstants.*;

/**
 * AI API 密钥 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AiApiKeyServiceImpl implements AiApiKeyService {

    @Resource
    private AiApiKeyMapper apiKeyMapper;

    @Resource
    private AiModelFactory modelFactory;

    @Override
    public Long createApiKey(AiApiKeySaveReqVO createReqVO) {
        // 插入
        AiApiKeyDO apiKey = BeanUtils.toBean(createReqVO, AiApiKeyDO.class);
        apiKeyMapper.insert(apiKey);
        // 返回
        return apiKey.getId();
    }

    @Override
    public void updateApiKey(AiApiKeySaveReqVO updateReqVO) {
        // 校验存在
        validateApiKeyExists(updateReqVO.getId());
        // 更新
        AiApiKeyDO updateObj = BeanUtils.toBean(updateReqVO, AiApiKeyDO.class);
        apiKeyMapper.updateById(updateObj);
    }

    @Override
    public void deleteApiKey(Long id) {
        // 校验存在
        validateApiKeyExists(id);
        // 删除
        apiKeyMapper.deleteById(id);
    }

    private AiApiKeyDO validateApiKeyExists(Long id) {
        AiApiKeyDO apiKey = apiKeyMapper.selectById(id);
        if (apiKey == null) {
            throw exception(API_KEY_NOT_EXISTS);
        }
        return apiKey;
    }

    @Override
    public AiApiKeyDO getApiKey(Long id) {
        return apiKeyMapper.selectById(id);
    }

    @Override
    public AiApiKeyDO validateApiKey(Long id) {
        AiApiKeyDO apiKey = validateApiKeyExists(id);
        if (CommonStatusEnum.isDisable(apiKey.getStatus())) {
            throw exception(API_KEY_DISABLE);
        }
        return apiKey;
    }

    @Override
    public PageResult<AiApiKeyDO> getApiKeyPage(AiApiKeyPageReqVO pageReqVO) {
        return apiKeyMapper.selectPage(pageReqVO);
    }

    @Override
    public List<AiApiKeyDO> getApiKeyList() {
        return apiKeyMapper.selectList();
    }

    // ========== 与 spring-ai 集成 ==========

    @Override
    public ChatModel getChatModel(Long id) {
        AiApiKeyDO apiKey = validateApiKey(id);
        AiPlatformEnum platform = AiPlatformEnum.validatePlatform(apiKey.getPlatform());
        return modelFactory.getOrCreateChatModel(platform, apiKey.getApiKey(), apiKey.getUrl());
    }

    @Override
    public ImageModel getImageModel(AiPlatformEnum platform) {
        AiApiKeyDO apiKey = apiKeyMapper.selectFirstByPlatformAndStatus(platform.getPlatform(), CommonStatusEnum.ENABLE.getStatus());
        if (apiKey == null) {
            throw exception(API_KEY_IMAGE_NODE_FOUND, platform.getName());
        }
        return modelFactory.getOrCreateImageModel(platform, apiKey.getApiKey(), apiKey.getUrl());
    }

    @Override
    public MidjourneyApi getMidjourneyApi() {
        AiApiKeyDO apiKey = apiKeyMapper.selectFirstByPlatformAndStatus(
                AiPlatformEnum.MIDJOURNEY.getPlatform(), CommonStatusEnum.ENABLE.getStatus());
        if (apiKey == null) {
            throw exception(API_KEY_MIDJOURNEY_NOT_FOUND);
        }
        return modelFactory.getOrCreateMidjourneyApi(apiKey.getApiKey(), apiKey.getUrl());
    }

    @Override
    public SunoApi getSunoApi() {
        AiApiKeyDO apiKey = apiKeyMapper.selectFirstByPlatformAndStatus(
                AiPlatformEnum.SUNO.getPlatform(), CommonStatusEnum.ENABLE.getStatus());
        if (apiKey == null) {
            throw exception(API_KEY_SUNO_NOT_FOUND);
        }
        return modelFactory.getOrCreateSunoApi(apiKey.getApiKey(), apiKey.getUrl());
    }

    @Override
    public EmbeddingModel getEmbeddingModel(Long id) {
        AiApiKeyDO apiKey = validateApiKey(id);
        AiPlatformEnum platform = AiPlatformEnum.validatePlatform(apiKey.getPlatform());
        return modelFactory.getOrCreateEmbeddingModel(platform, apiKey.getApiKey(), apiKey.getUrl());
    }

    @Override
    public VectorStore getOrCreateVectorStore(Long id) {
        AiApiKeyDO apiKey = validateApiKey(id);
        AiPlatformEnum platform = AiPlatformEnum.validatePlatform(apiKey.getPlatform());
        // 创建或获取 VectorStore 对象
        return modelFactory.getOrCreateVectorStore(getEmbeddingModel(id), platform, apiKey.getApiKey(), apiKey.getUrl());
    }

}