/**
* Classe pare de totes les classes de zones.
* Emmagatzema les diferents zones del mapa individualment.
* @author Elsa Mena, Abel Millán i Nil Casabella / Mòdul 13, Escola PIA Santa Anna, Mataró
* @version 0.1, 2022/11/06
*/

public class zona {
		
	private int num;
	
	private String nom;
	
	private int nord;
	
	private int sud;

	private int est;

	private int oest;

	
	public zona(String nom, int num, int nord, int sud, int est, int oest) {
		this.num = num;
		this.nom=nom;
		this.nord=nord;
		this.sud=sud;
		this.est=est;
		this.oest=oest;
	}

	
	public int nord(int bpos) {
		int n = nord;
		return n;
	}
	
	public int sud(int bpos) {
		int s = sud;
		return s;
	}
	
	public int est(int bpos) {
		int e = est;
		return e;
	}

	public int oest(int bpos) {
		int o = oest;
		return o;
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
	
	public void descripcions(int pos) {
		if(pos==1) {
			System.out.println("Em trobo a la sala de descans, és a dir, el dormitori. Puc observar que en el dormitori hi ha dos lliteres les quals tenen les seves respectives tauletes de nit. En la zona d'amunt on em trobo situat actualment podré accedir al bany, en canvi, si vaig a l'esquerra em trobo al menjador.");
		}
		else if(pos==2){
			System.out.println("He entrat al bany. De primeres oloro una forta pudor a orina. La pica està mig trencada i davant meu veig un armari-mirall brut. La tassa del vàter està oxidada. Estic pensant en ordenar al iHall a que  netegi aquest desastre... Al sud trobo el dormitori i a l'oest les oficines.");
		}
		else if(pos==3){
			System.out.println("Al entrar oloro a pols, és un espai molt tancat i amb ple de paperassa damunt de cada escriptori. Crec que em trobo a les oficines, ja que hi ha ple de documentació de la nau i de la missió encomanada. Si vull accedir a la part d'adalt em trobaré el taller, en canvi, si vaig a la zona contrària del taller aniré a la sala de comandament. A l'oest trobo el vestuari i a l'est al bany");
		}
		else if(pos==4){
			System.out.println("Aquesta sala és molt fosca, ja que al centre de la habitació hi ha una única bombeta penjada del sostre. La bombeta únicament enfoca la taula de treball. Des d'aquesta sala puc tirar només cap enrrere, és a dir, a les oficines.");
		}
		else if(pos==5){
			System.out.println("És una sala amb molts botons i tres cadires. També veig una estanteria amb manuals de la nau i uns objectes extranys. Tinc bons records de quan vam haver d'atrevesar un forat de cuc per primer cop... Únicament puc accedir a la zona d'adalt, és a dir, a les oficines. ");
		}
		else if(pos==6){
			System.out.println("És una zona molt humida, veig bancs amb taquilles per cada membre de la tripulació, ja que cada taquilla té una enganxina amb el nom del tripulant. També trobem les dutxes. Puc anar a la part dreta on veig les oficines o baixar a la cuina.");
		}
		else if(pos==7){
			System.out.println("Al entrar a la cuina em vé la olor a donuts recent fets en el forn. De les poques qualitats que posseix aquest tros de ferralla...Els donuts estan al marbre de la cuina. Per accedir al vestuari haig d'anar a la zona nord, en canvi, per anar a l'est puc accedir al menjador.");
		}
		else if(pos==8){
			System.out.println("He vist una sala molt gran amb una taula rodona al centre i les seves respectives cadires de fusta. Una d'elles té una bossa de mà de un membre de la tripulació.De qui serà..? Si vull anar a la cuina haig d'accedir a la esquerra, en canvi, per la dreta m'aniria al dormitori. I si vull anar a la sala de la sortida exterior haig d'anar a la zona sud. ");
		}
		else if(pos==9){
			System.out.println("La sala de descontaminació. Aquesta zona és molt petita i només trobo una taquilla i una cadira. Què hi haurà dins de la taquilla? Si vull anar a la zona de propulsors haig d'accedir a la part de baix, en canvi, a la zona nord vaig al menjador.");
		}
		else if(pos==10){
			System.out.println("Al entrar fa molta calor. L'iHall tenia raó, sembla que ha hagut una bona averia. Si vull sortir d'aquesta zona, haig d'anar amunt, és a dir, entrar a la sala de sortida exterior.");
		}
	}
	
}
