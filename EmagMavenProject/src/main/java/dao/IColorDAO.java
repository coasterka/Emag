package dao;

import engine.Color;
import exceptions.ColorDAOException;
import exceptions.EmagInvalidArgumentException;

public interface IColorDAO {

	int addColor(Color color) throws ColorDAOException;

	Color getColorById(int colorId) throws ColorDAOException, EmagInvalidArgumentException;

}