package com.cliffhangout.business.contract.manager;

import com.cliffhangout.beans.Way;

import java.util.List;
import java.util.Map;

public interface WayManager {
    void configWay(Way way, double heightWay, List<String> pointsNb, Map<String,Object> session, String idSector);
    void deleteWay(Way way);
}
