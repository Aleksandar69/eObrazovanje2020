package tseo.eobrazovanje.dto;

public class RegistracijaZahtevDto {
	

	private long id;
	private String ime;
	private String prezime;
	private String jmbg;
	private String brojIndexa;
	private String username;
	private String password;
	private String adresa;
	private String brojTelefona;
	private String role;
	private double stanje;
	private boolean odobren;

	RegistracijaZahtevDto(){}
	
	
	
	public RegistracijaZahtevDto(String ime, String prezime, String jmbg, String brojIndexa, String username,
			String password, String adresa, String brojTelefona, String role, double stanje, boolean odobren) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.brojIndexa = brojIndexa;
		this.username = username;
		this.password = password;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.role = role;
		this.stanje = stanje;
		this.odobren = odobren;
	}


	public double getStanje() {
		return stanje;
	}

	public void setStanje(double stanje) {
		this.stanje = stanje;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public RegistracijaZahtevDto setId(long id) {
		this.id = id;
		return this;
	}

	public String getIme() {
		return ime;
	}

	public RegistracijaZahtevDto setIme(String ime) {
		this.ime = ime;
		return this;
	}

	public String getPrezime() {
		return prezime;
	}

	public RegistracijaZahtevDto setPrezime(String prezime) {
		this.prezime = prezime;
		return this;
	}

	public String getBrojIndexa() {
		return brojIndexa;
	}

	public void setBrojIndexa(String brojIndexa) {
		this.brojIndexa = brojIndexa;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}


	public boolean isOdobren() {
		return odobren;
	}


	public void setOdobren(boolean odobren) {
		this.odobren = odobren;
	}

	
}
