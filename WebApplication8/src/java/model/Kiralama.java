package model;
import java.sql.Date;

public class Kiralama {
	public int id;
	public Date kiralamaBaslangic;
	public Date kiralamaBitis;
        public Arac arac;
	public Musteri musteri;
	public Kiralama(int id, Arac arac, Musteri musteri, Date kiralamaBaslangic, Date kiralamaBitis) {
		super();
		this.id = id;
		this.arac = arac;
		this.musteri = musteri;
		this.kiralamaBaslangic = kiralamaBaslangic;
		this.kiralamaBitis = kiralamaBitis;
	}
	public Kiralama(Arac arac, Musteri musteri, Date kiralamaBaslangic, Date kiralamaBitis) {
		super();
		this.arac = arac;
		this.musteri = musteri;
		this.kiralamaBaslangic = kiralamaBaslangic;
		this.kiralamaBitis = kiralamaBitis;
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
	public Musteri getMusteri() {
		return musteri;
	}
	public void setMusteri(Musteri musteri) {
		this.musteri = musteri;
	}
	public Date getKiralamaBaslangic() {
		return kiralamaBaslangic;
	}
	public void setKiralamaBaslangic(Date kiralamaBaslangic) {
		this.kiralamaBaslangic = kiralamaBaslangic;
	}
	public Date getKiralamaBitis() {
		return kiralamaBitis;
	}
	public void setKiralamaBitis(Date kiralamaBitis) {
		this.kiralamaBitis = kiralamaBitis;
	}


}
