package cn.codeyang.yang.module.system.dal.mysql.dept;

import cn.codeyang.yang.framework.common.pojo.PageResult;
import cn.codeyang.yang.framework.mybatis.core.mapper.BaseMapperX;
import cn.codeyang.yang.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.codeyang.yang.module.system.controller.admin.dept.vo.post.PostPageReqVO;
import cn.codeyang.yang.module.system.dal.dataobject.dept.PostDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface PostMapper extends BaseMapperX<PostDO> {

    default List<PostDO> selectList(Collection<Long> ids, Collection<Integer> statuses) {
        return selectList(new LambdaQueryWrapperX<PostDO>()
                .inIfPresent(PostDO::getId, ids)
                .inIfPresent(PostDO::getStatus, statuses));
    }

    default PageResult<PostDO> selectPage(PostPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PostDO>()
                .likeIfPresent(PostDO::getCode, reqVO.getCode())
                .likeIfPresent(PostDO::getName, reqVO.getName())
                .eqIfPresent(PostDO::getStatus, reqVO.getStatus())
                .orderByDesc(PostDO::getId));
    }

    default PostDO selectByName(String name) {
        return selectOne(PostDO::getName, name);
    }

    default PostDO selectByCode(String code) {
        return selectOne(PostDO::getCode, code);
    }

}
