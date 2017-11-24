package com.cliffhangout.business.contract.manager;

import com.cliffhangout.beans.Topo;
import com.cliffhangout.beans.User;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface TopoManager {
    void addTopo(Topo topo, File upload, String uploadFileName, String uploadContentType, Map<String, Object> session);
    Topo displayTopo(int id);
    List<Topo> displayUserTopo(User user);
    void buildTopoDependencies(Topo topo);
    void deleteTopo(int id);
    void deleteTopoDependencies(Topo topo);
    void editTopo(Topo topo, Topo topoToEdit, Map<String, Object> session);
    void editTopo(Topo topo, Topo topoToEdit, File upload, String uploadFileName, String uploadContentType, Map<String, Object> session);
}
