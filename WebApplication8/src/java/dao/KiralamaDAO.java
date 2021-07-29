package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import model.Arac;
import model.Kiralama;
import model.Musteri;

public class KiralamaDAO {
	private final String jdbcURL ="jdbc:mysql://localhost:3306/arackiralama?zeroDateTimeBehavior=convertToNull";
	private final String jdbcUsername = "root";
	private final String jdbcPassword = "batuhan";
	private static final String INSERT_KIRALAMA_SQL = "INSERT INTO kiralama" + "  (arac, musteri,baslangicTarihi,bitisTarihi) VALUES "
			+ " (?, ?,?,?);";
	private static final String SELECT_KIRALAMA_BY_ID = "select id,arac,musteri,baslangicTarihi,bitisTarihi from kiralama where id =?";
	private static final String SELECT_ALL_KIRALAMA = "select * from kiralama";
	private static final String DELETE_KIRALAMA_SQL = "delete from kiralama where id = ?;";
	private static final String UPDATE_KIRALAMA_SQL = "update kiralama set arac = ?,musteri= ?,baslangicTarihi= ?,bitisTarihi= ? where id = ?;";
	private static final String SELECT_KIRAARAC_SQL ="select * from arackiralama.kiralama where arac=? and bitisTarihi>=? ;";

	public KiralamaDAO() {
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

	public void insertKiralama(Kiralama kira) throws SQLException {
		System.out.println(INSERT_KIRALAMA_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_KIRALAMA_SQL)) {
			preparedStatement.setInt(1, kira.getArac().getId());
			preparedStatement.setInt(2, kira.getMusteri().getId());
			preparedStatement.setDate(3, kira.getKiralamaBaslangic());
			preparedStatement.setDate(4, kira.getKiralamaBitis());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Kiralama selectKiralama(int id) {
		Kiralama kiralama = null;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_KIRALAMA_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
			
				Integer aracId = rs.getInt("arac");
				Integer musteriId = rs.getInt("musteri");
				Date baslangicTarihi = rs.getDate("baslangicTarihi");
				Date bitisTarihi = rs.getDate("bitisTarihi");
				Arac arac = new AracDAO().selectArac(aracId);
				Musteri musteri = new MusteriDAO().selectMusteri(musteriId);
				kiralama = new Kiralama(id,arac, musteri,baslangicTarihi,bitisTarihi);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return kiralama;
	}

	public List<Kiralama> selectAllKiralama() {
		List<Kiralama> kiralamaList = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_KIRALAMA);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				Integer aracId = rs.getInt("arac");
				Integer musteriId = rs.getInt("musteri");
				Date baslangicTarihi = rs.getDate("baslangicTarihi");
				Date bitisTarihi = rs.getDate("bitisTarihi");
				Arac arac = new AracDAO().selectArac(aracId);
				Musteri musteri = new MusteriDAO().selectMusteri(musteriId);
				kiralamaList.add(new Kiralama(id, arac, musteri,baslangicTarihi,bitisTarihi));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return kiralamaList;
	}

	public boolean deleteKiralama(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_KIRALAMA_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateKiralama(Kiralama kiralama) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_KIRALAMA_SQL);) {
			statement.setInt(1, kiralama.getArac().getId());
			statement.setInt(2, kiralama.getMusteri().getId());
			statement.setDate(3, kiralama.getKiralamaBaslangic());
			statement.setDate(4, kiralama.getKiralamaBitis());
			statement.setInt(5, kiralama.getId());
			System.out.println(kiralama.kiralamaBaslangic);
			System.out.println(kiralama.kiralamaBitis);
			System.out.println(kiralama.getArac().getId());
			System.out.println("*********///////****************");
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	public List<Kiralama> selectAllKiraArac(int aId,Date bTarihi) {		
		List<Kiralama> kiralamaList = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_KIRAARAC_SQL);) {
			preparedStatement.setInt(1, aId);
			preparedStatement.setDate(2, bTarihi);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				Integer aracId = rs.getInt("arac");
				Integer musteriId = rs.getInt("musteri");

				Date baslangicTarihi = rs.getDate("baslangicTarihi");
				Date bitisTarihi = rs.getDate("bitisTarihi");
				Arac arac = new AracDAO().selectArac(aracId);
				Musteri musteri = new MusteriDAO().selectMusteri(musteriId);
				kiralamaList.add(new Kiralama(id, arac, musteri,baslangicTarihi,bitisTarihi));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return kiralamaList;
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
