package cn.codeyang.yang.module.bpm.framework.flowable.core.event;

import cn.codeyang.yang.module.bpm.event.BpmProcessInstanceStatusEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * {@link BpmProcessInstanceStatusEvent} 的生产者
 *
 * @author 芋道源码
 */
@AllArgsConstructor
@Validated
public class BpmProcessInstanceEventPublisher {

    private final ApplicationEventPublisher publisher;

    public void sendProcessInstanceResultEvent(@Valid BpmProcessInstanceStatusEvent event) {
        publisher.publishEvent(event);
    }

}
