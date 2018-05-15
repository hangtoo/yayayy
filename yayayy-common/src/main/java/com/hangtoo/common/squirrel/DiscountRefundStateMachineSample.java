package com.hangtoo.common.squirrel;

import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.StateMachineContext;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;
import org.squirrelframework.foundation.fsm.annotation.State;
import org.squirrelframework.foundation.fsm.annotation.StateMachineParameters;
import org.squirrelframework.foundation.fsm.annotation.States;
import org.squirrelframework.foundation.fsm.annotation.Transit;
import org.squirrelframework.foundation.fsm.annotation.Transitions;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;


public class DiscountRefundStateMachineSample {
	 // 1. Define State Machine Event
    enum RefundEvent {
    		APPLY_CONFIRM, REJECT, APPLY_APPROVED, REFUND_APPROVED, REFUND_FINISH_CONFIRM
    }
	@StateMachineParameters(stateType = String.class, eventType = RefundEvent.class,
		    //StateMachineContext 自定义上下文，用来传递数据
		    contextType = StateMachineContext.class)
		@States({
		    @State(name = "PENDING", initialState = true),
		    @State(name = "CONFIRMING"),
		    @State(name = "REJECTED"),
		    @State(name = "REFUND_APPROVING"),
		    @State(name = "REFUND_APPROVED"),
		    @State(name = "REFUND_FINISHED")
		})
		@Transitions({
		    @Transit(from = "PENDING", to = "CONFIRMING", on = "APPLY_CONFIRM",
		        callMethod = "doSomething"),
		    @Transit(from = "CONFIRMING", to = "REJECTED", on = "REJECT"),
		    @Transit(from = "CONFIRMING", to = "REFUND_APPROVING", on = "APPLY_APPROVED"),
		    @Transit(from = "REFUND_APPROVING", to = "REFUND_APPROVED", on = "REFUND_APPROVED"),
		    @Transit(from = "REFUND_APPROVED", to = "REFUND_FINISHED", on = "REFUND_FINISH_CONFIRM")
		})
		static class DiscountRefundStateMachine extends AbstractUntypedStateMachine {
		   
		    
		    public void doSomething(String fromState, String toState, RefundEvent event,
		                         StateMachineContext stateMachineContext) {
		         System.out.println("doSomething");
		    }
		    
		    
		}
	
	public static void main(String[] args) {
        // 3. Build State Transitions
        UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create(DiscountRefundStateMachine.class);
        //builder.externalTransition().from("PENDING").to("CONFIRMING").on(RefundEvent.APPLY_CONFIRM).callMethod("doSomething");
        
        // 4. Use State Machine
        UntypedStateMachine fsm = builder.newStateMachine("PENDING");
        fsm.fire(RefundEvent.APPLY_CONFIRM);
        
        System.out.println("Current state is "+fsm.getCurrentState());
    }
}
