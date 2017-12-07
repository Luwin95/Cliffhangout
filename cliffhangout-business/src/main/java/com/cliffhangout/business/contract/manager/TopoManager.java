package com.cliffhangout.business.contract.manager;

import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TopoManager {
    void addTopo(Topo topo, File upload, String uploadFileName, String uploadContentType, Map<String, Object> session);
    Topo displayTopo(int id);
    List<Topo> displayUserTopo(User user);
    List<Topo> displayTopoToBorrow(User user);
    List<Topo> displayAllTopo();
    List<Topo> displayAllTopoBySite(Site site);
    void buildTopoDependencies(Topo topo);
    void deleteTopo(int id);
    void deleteTopoDependencies(Topo topo);
    void editTopo(Topo topo, Topo topoToEdit, Map<String, Object> session);
    void editTopo(Topo topo, Topo topoToEdit, File upload, String uploadFileName, String uploadContentType, Map<String, Object> session);
    String borrowTopo (Topo topo, Date startDate, Date endDate, Map<String, Object> session);
}
