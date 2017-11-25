package com.cliffhangout.webapp.actions;

import com.cliffhangout.webapp.AbstractAction;

public class TopoBorrowing extends AbstractAction {
    private String idTopo;

    public String getIdTopo() {
        return idTopo;
    }

    public void setIdTopo(String idTopo) {
        this.idTopo = idTopo;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
