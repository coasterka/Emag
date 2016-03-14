package dao;

import java.sql.Connection;

public abstract class AbstractDAO {
	private final Connection con = DBConnection.getInstance().getCon();

	public Connection getCon() {
		return con;
	}
	
}
