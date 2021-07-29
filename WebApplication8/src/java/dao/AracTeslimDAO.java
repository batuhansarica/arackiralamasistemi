package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Arac;
import model.AracTeslim;
import model.User;

public class AracTeslimDAO {
	private final String jdbcURL ="jdbc:mysql://localhost:3306/arackiralama?zeroDateTimeBehavior=convertToNull";
	private final String jdbcUsername = "root";
	private final String jdbcPassword = "batuhan";
	private static final String INSERT_ARACTESLIM_SQL = "INSERT INTO aracTeslim" + "  (arac, user,teslimTarihi) VALUES "
			+ " (?, ?,?);";
	private static final String SELECT_ARACTESLIM_BY_ID = "select id,arac,user,teslimTarihi from aracTeslim where id =?";
	private static final String SELECT_ALL_ARACTESLIM = "select * from aracTeslim";
	private static final String DELETE_ARACTESLIM_SQL = "delete from aracTeslim where id = ?;";
        private static final String DELETE_ALLARACTESLIM_SQL = "delete from aracTeslim";
	private static final String UPDATE_ARACTESLIM_SQL = "update aracTeslim set arac = ?,user= ?,teslimTarihi= ? where id = ?;";

	public AracTeslimDAO() {
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

	public void insertAracTeslim(AracTeslim aracTeslim) throws SQLException {
		System.out.println(INSERT_ARACTESLIM_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ARACTESLIM_SQL)) {
			preparedStatement.setInt(1, aracTeslim.getArac().getId());
			preparedStatement.setInt(2, aracTeslim.getUser().getId());
			preparedStatement.setDate(3,java.sql.Date.valueOf(java.time.LocalDate.now()));
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public AracTeslim selectAracTeslim(int id) {
		AracTeslim aracTeslim = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ARACTESLIM_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Integer aracId = rs.getInt("arac");
				Integer userId = rs.getInt("user");
				Date teslimTarihi = rs.getDate("teslimTarihi");
				Arac arac = new AracDAO().selectArac(aracId);
				User user = new UserDAO().selectUser(userId);
				aracTeslim = new AracTeslim(id,arac, user,teslimTarihi);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return aracTeslim;
	}

	public List<AracTeslim> ButunAraclariGoster() {
		List<AracTeslim> aracTeslimList = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ARACTESLIM);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				Integer aracId = rs.getInt("arac");
				Integer userId = rs.getInt("user");
				Date teslimTarihi = rs.getDate("teslimTarihi");
				Arac arac = new AracDAO().selectArac(aracId);
				User user = new UserDAO().selectUser(userId);
				aracTeslimList.add(new AracTeslim(id, arac, user,teslimTarihi));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return aracTeslimList;
	}

        public boolean deleteAllAracTeslim() throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ALLARACTESLIM_SQL);) {
			
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	} 
        
	public boolean deleteAracTeslim(int id) throws SQLException {
		boolean rowDeletedd;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ARACTESLIM_SQL);) {
			statement.setInt(1, id);
			rowDeletedd = statement.executeUpdate() > 0;
		}
		return rowDeletedd;
	}
	public boolean updateAracTeslim(AracTeslim aracTeslim) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ARACTESLIM_SQL);) {
			statement.setInt(1, aracTeslim.getArac().getId());
			statement.setInt(2, aracTeslim.getUser().getId());
			statement.setDate(3, aracTeslim.getTeslimTarihi());
			statement.setInt(4, aracTeslim.getId());
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
