package com.cliffhangout.business.impl.manager;

import com.cliffhangout.beans.Departement;
import com.cliffhangout.beans.Region;
import com.cliffhangout.business.contract.manager.DepartementRegionManager;

import java.util.ArrayList;
import java.util.List;

public class DepartementRegionManagerImpl extends AbstractManagerImpl implements DepartementRegionManager {
    public List<Object> displayAllDepartmentsAndRegions()
    {
        List<Object> entities = new ArrayList<>();
        List<Departement> departements = new ArrayList<>();
        List<Region> regions = new ArrayList<>();
        departements = getDaoFactory().getDepartementDao().findAll();
        regions = getDaoFactory().getRegionDao().findAll();
        entities.add(departements);
        entities.add(regions);
        return entities;
    }
}
