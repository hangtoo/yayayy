package com.hangtoo.common.squirrel;

import org.squirrelframework.foundation.fsm.Visitor;

public interface CheckDotVisitor extends Visitor {
    /**
     * Create dot file
     * @param filename name of dot file
     */
    void convertDotFile(String filename);
}
