package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Arac;

public class AracDAO {
	private final String jdbcURL ="jdbc:mysql://localhost:3306/arackiralama?zeroDateTimeBehavior=convertToNull";
	private final String jdbcUsername = "root";
	private final String jdbcPassword = "batuhan";
	private static final String INSERT_ARAC_SQL = "INSERT INTO arac" + "  (plakaNo, model,marka,renk) VALUES "
			+ " (?, ?,?,?);";
	private static final String SELECT_ARAC_BY_ID = "select id,plakaNo,model,marka,renk from arac where id =?";
	private static final String SELECT_ALL_ARAC = "select * from arac";
	private static final String DELETE_ARAC_SQL = "delete from arac where id = ?;";
	private static final String UPDATE_ARAC_SQL = "update arac set plakaNo = ?,model= ?,marka= ?,renk= ? where id = ?;";
	public AracDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void insertArac(Arac arac) throws SQLException {
		System.out.println(INSERT_ARAC_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ARAC_SQL)) {
			preparedStatement.setString(1, arac.getPlaka());
			preparedStatement.setString(2, arac.getModel());
			preparedStatement.setString(3, arac.getMarka());
			preparedStatement.setString(4, arac.getRenk());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Arac selectArac(int id) {
		Arac arac = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ARAC_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String plakaNo = rs.getString("plakaNo");
				String model = rs.getString("model");
				String marka = rs.getString("marka");
				String renk = rs.getString("renk");
				arac = new Arac(id, plakaNo, model,marka,renk);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return arac;
	}

	public List<Arac> selectAllArac() {
		List<Arac> aracList = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ARAC);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String plakaNo = rs.getString("plakaNo");
				String model = rs.getString("model");
				String marka = rs.getString("marka");
				String renk = rs.getString("renk");
				aracList.add(new Arac(id, plakaNo, model,marka,renk));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return aracList;
	}

	public boolean deleteArac(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ARAC_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateArac(Arac arac) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ARAC_SQL);) {
			statement.setString(1, arac.getPlaka());
			statement.setString(2, arac.getModel());
			statement.setString(3, arac.getMarka());
			statement.setString(4, arac.getRenk());
			statement.setInt(5, arac.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
