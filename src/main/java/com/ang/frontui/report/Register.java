package com.ang.frontui.report;

import java.util.List;

public interface Register {
    String accept(Visitor visitor);

    List<Register> getChild();

    String getCode();
    String getName();
    String getDefault();
    Integer getLevel();
}
