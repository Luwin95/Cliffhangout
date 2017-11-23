package com.cliffhangout.webapp.actions;

import com.cliffhangout.webapp.AbstractAction;

public class RemoveTopoAction extends AbstractAction {
    private String idTopo;

    public String getIdTopo() {
        return idTopo;
    }

    public void setIdTopo(String idTopo) {
        this.idTopo = idTopo;
    }

    @Override
    public String execute(){
        if(!idTopo.equals(""))
        {
            int id = Integer.parseInt(idTopo);
            getManagerFactory().getTopoManager().deleteTopo(id);
            return SUCCESS;
        }else{
            return "home";
        }
    }
}
