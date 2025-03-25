package cn.codeyang.yang.module.bpm.framework.flowable.core.candidate.strategy.user;

import cn.codeyang.yang.framework.common.util.string.StrUtils;
import cn.codeyang.yang.module.bpm.framework.flowable.core.candidate.BpmTaskCandidateStrategy;
import cn.codeyang.yang.module.bpm.framework.flowable.core.enums.BpmTaskCandidateStrategyEnum;
import cn.codeyang.yang.module.system.api.dept.PostApi;
import cn.codeyang.yang.module.system.api.user.AdminUserApi;
import cn.codeyang.yang.module.system.api.user.dto.AdminUserRespDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static cn.codeyang.yang.framework.common.util.collection.CollectionUtils.convertSet;

/**
 * 岗位 {@link BpmTaskCandidateStrategy} 实现类
 *
 * @author kyle
 */
@Component
public class BpmTaskCandidatePostStrategy implements BpmTaskCandidateStrategy {

    @Resource
    private PostApi postApi;
    @Resource
    private AdminUserApi adminUserApi;

    @Override
    public BpmTaskCandidateStrategyEnum getStrategy() {
        return BpmTaskCandidateStrategyEnum.POST;
    }

    @Override
    public void validateParam(String param) {
        Set<Long> postIds = StrUtils.splitToLongSet(param);
        postApi.validPostList(postIds);
    }

    @Override
    public Set<Long> calculateUsers(String param) {
        Set<Long> postIds = StrUtils.splitToLongSet(param);
        List<AdminUserRespDTO> users = adminUserApi.getUserListByPostIds(postIds);
        return convertSet(users, AdminUserRespDTO::getId);
    }

}