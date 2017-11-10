package com.binea.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.OnTransitionEnd;
import org.springframework.statemachine.annotation.OnTransitionStart;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * Created by binea
 * Date: 10/11/2017
 * TIME: 9:21 PM
 */
@WithStateMachine
public class EventConfig {
    private Logger LOGGER = LoggerFactory.getLogger(EventConfig.class.getCanonicalName());

    @OnTransition(target = "UNPAID")
    public void create() {
        LOGGER.info("Order has created, wait to pay");
    }

    @OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void pay() {
        LOGGER.info("have paid, wait to receive the goods");
    }

    @OnTransitionStart(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void payStart() {
        LOGGER.info("have paid, wait to receive the goods: start");
    }

    @OnTransitionEnd(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void payEnd() {
        LOGGER.info("have paid, wait to receive the goods: end");
    }

    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
    public void receive() {
        LOGGER.info("have received goods, order have done");
    }


}
