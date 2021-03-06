package com.hangtoo.common.squirrel;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import org.squirrelframework.foundation.fsm.HistoryType;
import org.squirrelframework.foundation.fsm.ImmutableState;
import org.squirrelframework.foundation.fsm.ImmutableTransition;
import org.squirrelframework.foundation.fsm.StateMachine;

class CheckDotVisitorImpl implements CheckDotVisitor {

    protected final StringBuilder transBuf = new StringBuilder();
    
    protected FlowState currentstate;
    
    protected String currentcolor;
    
    @Override
    public void visitOnEntry(StateMachine<?, ?, ?, ?> visitable) {
    		currentstate=FlowState.valueOf(visitable.getCurrentState().toString());
        writeLine("digraph {\ncompound=true;");
        writeLine("subgraph cluster_StateMachine {\nlabel=\"流程图\";");
    }

    @Override
    public void visitOnExit(StateMachine<?, ?, ?, ?> visitable) {
        buffer.append(transBuf);
        writeLine("}}");
    }

    @Override
    public void visitOnEntry(ImmutableState<?, ?, ?, ?> visitable) {
        String stateId = visitable.getStateId().toString();
        if(visitable.hasChildStates()) {
            writeLine("subgraph cluster_"+stateId+" {\nlabel=\""+stateId+"\";");
            if(visitable.getHistoryType()==HistoryType.DEEP) {
                writeLine(stateId+"History"+" [label=\"\"];");
            } else if (visitable.getHistoryType()==HistoryType.SHALLOW) {
                writeLine(stateId+"History"+" [label=\"\"];");
            }
        } else {
        		FlowState flowState=FlowState.valueOf(visitable.getStateId().toString());
        		if(currentstate.equals(flowState)) {
        			currentcolor="yellowgreen";
        		}else {
        			currentcolor="black";
        		}
        		
            writeLine(stateId+" [label=\""+	flowState.name+"\" , color=\""+currentcolor+"\"];");
        }
    }

    @Override
    public void visitOnExit(ImmutableState<?, ?, ?, ?> visitable) {
        if(visitable.hasChildStates()) {
            writeLine("}");
        }
    }

    @Override
    public void visitOnEntry(ImmutableTransition<?, ?, ?, ?> visitable) {
        ImmutableState<?, ?, ?, ?> sourceState = visitable.getSourceState();
        ImmutableState<?, ?, ?, ?> targetState = visitable.getTargetState();
        String sourceStateId = sourceState.getStateId().toString();
        String targetStateId = targetState.getStateId().toString();
        boolean sourceIsCluster=sourceState.hasChildStates();
        boolean targetIsCluster=targetState.hasChildStates();
        String source=(sourceIsCluster)?"cluster_"+sourceStateId:null;
        String target=(targetIsCluster)?"cluster_"+targetStateId:null;
        String realStart=(sourceIsCluster)? getSimpleChildOf(sourceState).getStateId().toString():sourceStateId;
        String realEnd=(targetIsCluster)? getSimpleChildOf(targetState).getStateId().toString():targetStateId;
        String edgeLabel = FlowEvent.valueOf(visitable.getEvent().toString()).name;
        String ltail=(source!=null)?"ltail=\""+source+"\"":null;
        String lhead=(target!=null)?"lhead=\""+target+"\"":null;
        transBuf.append("\n"+realStart+" -> "+realEnd+" ["+((ltail!=null)?ltail+",":"")+((lhead!=null)?lhead+",":"")+" label=\""+edgeLabel+"\"];");
    }
    
    public ImmutableState<?, ?, ?, ?> getSimpleChildOf(ImmutableState<?, ?, ?, ?> sourceState) {
        Queue<ImmutableState<?, ?, ?, ?>> list=new LinkedList<ImmutableState<?, ?, ?, ?>>();
        list.add(sourceState);
        while(!list.isEmpty()) {
            ImmutableState<?, ?, ?, ?> x=list.poll();
            int l=x.getChildStates().size();
            for (int i=0; i<l; i++) {
                ImmutableState<?, ?, ?, ?> c = x.getChildStates().get(i);
                if (c.hasChildStates()) list.add(c);
                else return c;
            }
        }
        return sourceState;
    }

    @Override
    public void visitOnExit(ImmutableTransition<?, ?, ?, ?> visitable) {
    }

    @Override
    public void convertDotFile(String filename) {
        saveFile(filename+".dot", buffer.toString());
    }
    
    ///////////
    protected final StringBuilder buffer = new StringBuilder();
    
    protected void writeLine(final String msg) {
        buffer.append(msg).append("\n");
    }
    
    protected String quoteName(final String id) {
        return "\"" + id + "\"";
    }
    
    protected void saveFile(final String filename, String content) {
        try {
            FileWriter file = new FileWriter(filename);
            file.write(content);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}