package com.cliffhangout.services;

import com.cliffhangout.beans.Departement;
import com.cliffhangout.beans.Region;
import com.cliffhangout.dao.DaoException;
import com.cliffhangout.dao.DaoFactory;
import com.cliffhangout.dao.DepartementDao;
import com.cliffhangout.dao.RegionDao;

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
