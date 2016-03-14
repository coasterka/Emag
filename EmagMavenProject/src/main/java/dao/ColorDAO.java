package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import engine.Color;
import exceptions.ColorDAOException;
import exceptions.EmagInvalidArgumentException;

public class ColorDAO extends AbstractDAO implements IColorDAO {

	private static final String INSERT_NEW_COLOR_SQL = "INSERT INTO colors VALUES (null, ?);";
	private static final String FIND_COLOR_BY_ID_SQL = "SELECT * FROM colors WHERE color_id = ?";

	@Override
	public int addColor(Color color) throws ColorDAOException {
		if (color != null) {

			try {
				PreparedStatement ps = getCon().prepareStatement(INSERT_NEW_COLOR_SQL,
						PreparedStatement.RETURN_GENERATED_KEYS);

				String colorToString = color.name();
				ps.setString(1, colorToString);

				ps.executeUpdate();

				ResultSet result = ps.getGeneratedKeys();
				result.next();
				return result.getInt(1);

			} catch (SQLException e) {
				e.printStackTrace();
				throw new ColorDAOException("The color cannot be added right now. Thank you.", e);
			}
		} else {
			throw new ColorDAOException("No such color!");
		}
	}
	
	@Override
	public Color getColorById(int colorId) throws ColorDAOException, EmagInvalidArgumentException{
		Color wantedColor = null;

		try {
			PreparedStatement ps = getCon().prepareStatement(FIND_COLOR_BY_ID_SQL);
			ps.setInt(1, colorId);
			ResultSet result = ps.executeQuery();
			result.next();

			String colorName = result.getString(2);
			wantedColor = Color.valueOf(colorName);			
			
			return wantedColor;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ColorDAOException("The color with id " + colorId + " cannot be found . Thank you.", e);
		}
	}
}
