package dao;

import engine.Category;
import exceptions.CategoryDAOException;
import exceptions.EmagInvalidArgumentException;

public interface ICategoryDAO {

	int addCategory(Category category) throws CategoryDAOException;

	Category getCategoryById(int categoryId) throws CategoryDAOException, EmagInvalidArgumentException;

}