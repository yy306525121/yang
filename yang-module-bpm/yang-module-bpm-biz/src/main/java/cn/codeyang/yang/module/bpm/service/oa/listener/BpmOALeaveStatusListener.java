package cn.codeyang.yang.module.bpm.service.oa.listener;

import cn.codeyang.yang.module.bpm.event.BpmProcessInstanceStatusEvent;
import cn.codeyang.yang.module.bpm.event.BpmProcessInstanceStatusEventListener;
import cn.codeyang.yang.module.bpm.service.oa.BpmOALeaveService;
import cn.codeyang.yang.module.bpm.service.oa.BpmOALeaveServiceImpl;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * OA 请假单的结果的监听器实现类
 *
 * @author 芋道源码
 */
@Component
public class BpmOALeaveStatusListener extends BpmProcessInstanceStatusEventListener {

    @Resource
    private BpmOALeaveService leaveService;

    @Override
    protected String getProcessDefinitionKey() {
        return BpmOALeaveServiceImpl.PROCESS_KEY;
    }

    @Override
    protected void onEvent(BpmProcessInstanceStatusEvent event) {
        leaveService.updateLeaveStatus(Long.parseLong(event.getBusinessKey()), event.getStatus());
    }

}
