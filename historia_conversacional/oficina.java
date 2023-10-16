/**
* Herència de zona.
* Desa els atributs de la oficina i instancia un objecte.
* @author Elsa Mena, Abel Millán i Nil Casabella / Mòdul 13, Escola PIA Santa Anna, Mataró
* @version 0.1, 2022/11/06
*/

public class oficina extends zona{
    private int num;
	private String nom;
	private int nord;
	private int sud;
	private int est;
	private	 int oest;
	utils targeta = new utils("targeta",false,3, false);

	 
	public oficina(String nom, int num, int nord, int sud, int est, int oest) {
		super(nom, num, nord, sud, est, oest);
		this.nom=nom;
		this.num=num;
		this.nord=nord;
		this.sud=sud;
		this.est=est;
		this.oest=oest;
	}
		 	
	
	
	 public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getNord() {
		return nord;
	}
	public void setNord(int nord) {
		this.nord = nord;
	}
	public int getSud() {
		return sud;
	}
	public void setSud(int sud) {
		this.sud = sud;
	}
	public int getEst() {
		return est;
	}
	public void setEst(int est) {
		this.est = est;
	}
	public int getOest() {
		return oest;
	}
	public void setOest(int oest) {
		this.oest = oest;
	}	
}
