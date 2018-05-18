package com.hangtoo.common.squirrel;

public class CheckStateMachineContext {
    
    private static ThreadLocal<String> contextContainer = new ThreadLocal<String>() {
        protected String initialValue() {
            return "{}";
        }
    };
    
    public static void set(String json) {
    		contextContainer.set(json);
    }
    
    public static String get() {
    	return	contextContainer.get();
    }
    
}