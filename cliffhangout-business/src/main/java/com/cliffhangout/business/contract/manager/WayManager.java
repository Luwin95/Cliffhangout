package com.cliffhangout.business.contract.manager;

import com.cliffhangout.beans.Way;

import java.util.List;
import java.util.Map;

public interface WayManager {
    void configWay(Way way, double heightWay, List<String> pointsNb, Map<String,Object> session, String idSector);
    void configEditWay(Way way, double heightWay, List<String> pointsNb, Map<String,Object> session, String idSector, String idWay);
    void configWayLength(Way way, int wayPointNb, List<String> pointsNb);
    void deleteWay(Way way);
    void updateWay(Way way);
    void addWay(Way way);

}
