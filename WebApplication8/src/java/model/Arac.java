package model;

public class Arac {
	public int id;
	public String plaka;
	public String model;
	public String marka;
        public String renk;
	public Arac(int id, String plaka, String model, String marka, String renk) {
		super();
		this.id = id;
		this.plaka = plaka;
		this.model = model;
		this.marka = marka;
		this.renk = renk;
	}
	public Arac(String plaka, String model, String marka, String renk) {
		super();
		this.plaka = plaka;
		this.model = model;
		this.marka = marka;
		this.renk = renk;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlaka() {
		return plaka;
	}
	public void setPlaka(String plaka) {
		this.plaka = plaka;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMarka() {
		return marka;
	}
	public void setMarka(String marka) {
		this.marka = marka;
	}
	public String getRenk() {
		return renk;
	}
	public void setRenk(String renk) {
		this.renk = renk;
	}


}
