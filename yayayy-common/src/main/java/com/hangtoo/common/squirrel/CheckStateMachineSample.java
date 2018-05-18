package com.hangtoo.common.squirrel;

import org.squirrelframework.foundation.component.SquirrelProvider;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;
import org.squirrelframework.foundation.fsm.annotation.State;
import org.squirrelframework.foundation.fsm.annotation.StateMachineParameters;
import org.squirrelframework.foundation.fsm.annotation.States;
import org.squirrelframework.foundation.fsm.annotation.Transit;
import org.squirrelframework.foundation.fsm.annotation.Transitions;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;

public class CheckStateMachineSample {
	// 1. Define State Machine Event

	// 2. Define State
	
	@StateMachineParameters(stateType = FlowState.class, eventType = FlowEvent.class,
			// StateMachineContext 自定义上下文，用来传递数据
			contextType = CheckStateMachineContext.class)
	@States({ @State(name = "DRAFT", initialState = true), @State(name = "APPLYING"), @State(name = "REJECTED"),
			@State(name = "DEPARTMENT_APPROVED"), @State(name = "PASSED") })
	
	@Transitions({ @Transit(from = "DRAFT", to = "APPLYING", on = "APPLY", callMethod = "doSomething"),
			@Transit(from = "APPLYING", to = "DEPARTMENT_APPROVED", on = "DEPARTMENT_APPROVE", callMethod = "doSomething"),
			@Transit(from = "DEPARTMENT_APPROVED", to = "PASSED", on = "HR_APPROVE", callMethod = "doSomething"),
			
			@Transit(from = "APPLYING", to = "REJECTED", on = "REJECT", callMethod = "doSomething"),
			@Transit(from = "DEPARTMENT_APPROVED", to = "REJECTED", on = "REJECT", callMethod = "doSomething")
	
		    })
	static class CheckStateMachine extends AbstractUntypedStateMachine {
		public void doSomething(FlowState fromState, FlowState toState, FlowEvent event,
				CheckStateMachineContext stateMachineContext) {
			System.out.println(String.format("do %s from %s to %s  with context is %s",
					event.toString(), fromState.toString(),toState.toString(),stateMachineContext.get()));
		}

	}

	public static void main(String[] args) {
		// 3. Build State Transitions
		UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create(CheckStateMachine.class);
		// builder.externalTransition().from("PENDING").to("CONFIRMING").on(FlowEvent.APPLY_CONFIRM).callMethod("doSomething");

		// 4. Use State Machine
		UntypedStateMachine fsm = builder.newStateMachine(FlowState.DRAFT);
		CheckStateMachineContext context=new CheckStateMachineContext();
		context.set("{a:1,b:2}");
		fsm.fire(FlowEvent.APPLY,context);

		System.out.println("Current state is " + fsm.getCurrentState());
		
		fsm.fire(FlowEvent.DEPARTMENT_APPROVE,context);
		System.out.println("Current state is " + fsm.getCurrentState());

		fsm.fire(FlowEvent.HR_APPROVE,context);
		System.out.println("Current state is " + fsm.getCurrentState());
		
		
		CheckDotVisitor visitor = SquirrelProvider.getInstance().newInstance(CheckDotVisitor.class);
		fsm.accept(visitor);
		visitor.convertDotFile("UntypedStateMachine");
	}
}
