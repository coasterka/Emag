package dao;

import engine.Brand;
import exceptions.BrandDAOException;
import exceptions.EmagInvalidArgumentException;

public interface IBrandDAO {

	int addBrand(Brand brand) throws BrandDAOException;

	Brand getBrandById(int brandId) throws BrandDAOException, EmagInvalidArgumentException;
	
	public int getBrandByName(String brand) throws BrandDAOException;

}