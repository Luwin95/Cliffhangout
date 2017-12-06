package com.cliffhangout.business.contract.manager;

import com.cliffhangout.beans.Sector;

public interface SectorManager {
    void deleteSector(Sector sector);
    void updateSector(Sector sector);
    void addSector(Sector sector);
}
