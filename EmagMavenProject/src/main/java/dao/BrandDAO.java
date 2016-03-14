package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import engine.Brand;
import exceptions.BrandDAOException;
import exceptions.EmagInvalidArgumentException;


public class BrandDAO extends AbstractDAO implements IBrandDAO {
	
	private static final String INSERT_NEW_BRAND_SQL = "INSERT INTO brands VALUES (null, ?);";
	private static final String FIND_BRAND_BY_ID_SQL = "SELECT * FROM brands WHERE brand_id = ?";
	
	@Override
	public int addBrand(Brand brand) throws BrandDAOException {
		if (brand != null) {

			try {
				PreparedStatement ps = getCon().prepareStatement(INSERT_NEW_BRAND_SQL,
						PreparedStatement.RETURN_GENERATED_KEYS);
				
				String brandToString = brand.name();
				ps.setString(1, brandToString);
				
				ps.executeUpdate();

				ResultSet result = ps.getGeneratedKeys();
				result.next();
				return result.getInt(1);

			} catch (SQLException e) {
				e.printStackTrace();
				throw new BrandDAOException("The brand cannot be added right now. Thank you.", e);
			}
		} else {
			throw new BrandDAOException("No such brand!");
		}
	}
	
	@Override
	public Brand getBrandById(int brandId) throws BrandDAOException, EmagInvalidArgumentException{
		Brand wantedBrand = null;

		try {
			PreparedStatement ps = getCon().prepareStatement(FIND_BRAND_BY_ID_SQL);
			ps.setInt(1, brandId);
			ResultSet result = ps.executeQuery();
			result.next();

			String brandName = result.getString(2);
			wantedBrand = Brand.valueOf(brandName);			
			
			return wantedBrand;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BrandDAOException("The brand with id " + brandId + " cannot be found . Thank you.", e);
		}
	}
}
