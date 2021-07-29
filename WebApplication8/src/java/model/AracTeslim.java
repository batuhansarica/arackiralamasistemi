package model;
import java.sql.Date;

public class AracTeslim {
	public int id;
	public Arac arac;
	public User user;
	public Date teslimTarihi;
	public AracTeslim(int id, Arac arac, User user, Date teslimTarihi) {
		super();
		this.id = id;
		this.arac = arac;
		this.user = user;
		this.teslimTarihi = teslimTarihi;
	}
	public AracTeslim(Arac arac, User user, Date teslimTarihi) {
		super();
		this.arac = arac;
		this.user = user;
		this.teslimTarihi = teslimTarihi;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Arac getArac() {
		return arac;
	}
	public void setArac(Arac arac) {
		this.arac = arac;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getTeslimTarihi() {
		return teslimTarihi;
	}
	public void setTeslimTarihi(Date teslimTarihi) {
		this.teslimTarihi = teslimTarihi;
	}

}
