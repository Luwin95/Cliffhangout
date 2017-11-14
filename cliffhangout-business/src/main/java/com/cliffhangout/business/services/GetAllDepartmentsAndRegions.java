package com.cliffhangout.business.services;

import com.cliffhangout.beans.Departement;
import com.cliffhangout.beans.Region;
import com.cliffhangout.consumer.impl.dao.DaoException;
import com.cliffhangout.consumer.impl.dao.DaoFactory;
import com.cliffhangout.consumer.contract.dao.DepartementDao;
import com.cliffhangout.consumer.contract.dao.RegionDao;

import java.util.ArrayList;
import java.util.List;

public class GetAllDepartmentsAndRegions {
    private DepartementDao departementDao;
    private RegionDao regionDao;

    public GetAllDepartmentsAndRegions()
    {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.departementDao = daoFactory.getDepartementDao();
        this.regionDao = daoFactory.getRegionDao();
    }

    public List<Object> displayAllDepartmentsAndRegions()
    {
        List<Object> entities = new ArrayList<>();
        List<Departement> departements = new ArrayList<>();
        List<Region> regions = new ArrayList<>();

        try{
            departements = departementDao.findAll();
            regions = regionDao.findAll();
        }catch(DaoException e)
        {
            e.printStackTrace();
        }

        entities.add(departements);
        entities.add(regions);
        return entities;
    }
}
