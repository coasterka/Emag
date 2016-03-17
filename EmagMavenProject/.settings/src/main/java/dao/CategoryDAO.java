package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import engine.Catalog;
import engine.Category;
import exceptions.BrandDAOException;
import exceptions.CategoryDAOException;
import exceptions.EmagInvalidArgumentException;


public class CategoryDAO extends AbstractDAO implements ICategoryDAO {
	
	private static final String INSERT_NEW_CATEGORY_SQL = "INSERT INTO categories VALUES (null, 1, ?);";
	private static final String FIND_CATEGORY_BY_ID_SQL = "SELECT * FROM categories WHERE category_id = ?";
	private static final String SELECT_FROM_CATEGORIES_WHERE_CATEGORY_NAME = "SELECT * FROM categories WHERE name = ?";
	
	@Override
	public int addCategory(Category category) throws CategoryDAOException {
		if (category != null) {

			try {
				PreparedStatement ps = getCon().prepareStatement(INSERT_NEW_CATEGORY_SQL,
						PreparedStatement.RETURN_GENERATED_KEYS);

				ps.setString(1, category.getName());

				ps.executeUpdate();

				ResultSet result = ps.getGeneratedKeys();
				result.next();
				return result.getInt(1);

			} catch (SQLException e) {
				e.printStackTrace();
				throw new CategoryDAOException("The category cannot be added right now. Thank you.", e);
			}
		} else {
			throw new CategoryDAOException("No such category!");
		}
	}
	
	@Override
	public Category getCategoryById(int categoryId) throws CategoryDAOException, EmagInvalidArgumentException{
		Category wantedCategory = null;

		try {
			PreparedStatement ps = getCon().prepareStatement(FIND_CATEGORY_BY_ID_SQL);
			ps.setInt(1, categoryId);
			ResultSet result = ps.executeQuery();
			result.next();

			
			wantedCategory = new Category(result.getString(3));
			wantedCategory.setCatalog(Catalog.createCatalog());
			
			return wantedCategory;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new CategoryDAOException("The category with id " + categoryId + " cannot be found . Thank you.", e);
		}
	}
	
	public int getCatgoryByName(String categoryName) throws BrandDAOException{
		int id;

		try {
			PreparedStatement ps = getCon().prepareStatement(SELECT_FROM_CATEGORIES_WHERE_CATEGORY_NAME);
			ps.setString(1, categoryName);
			ResultSet result = ps.executeQuery();
			result.next();

			id = result.getInt(1);
					
			
			return id;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BrandDAOException("The category with name " + categoryName + " cannot be found . Thank you.", e);
		}
	}
	
	

}
