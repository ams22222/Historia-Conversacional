import java.util.Scanner;

/**
* Herència de personatge.
* Desa els atributs del iHall, conversa amb el personatge principal i dona la posició del alien.
* @author Elsa Mena, Abel Millán i Nil Casabella / Mòdul 13, Escola PIA Santa Anna, Mataró
* @version 0.1, 2022/11/06
*/

public class ihall extends personatge{

	int pos=0;
	boolean at=false;
	boolean cd=false;
	
    Scanner e=new Scanner(System.in);
    public void dialeg(utils llanterna, personatge alien, personatge company) {
		System.out.println("-    BONDia bond");
		System.out.println("-    Que necessites?");
		System.out.print("Que recordi aquest robot reaccionava a comandes, alguna era... LLANTERNA   PARLAR");
		if(alien instanceof alien && company instanceof company){
			alien a = (alien) alien;
			company c = (company) company;
			if(c.despert){
				cd=true;
				System.out.print("   CARL");
			}
			if(a.cont>=-1){
				System.out.println("   ALIEN");
				at=a.at;
			}
			else{
				System.out.println();
			}
		}
		String text = e.next();
		if(text.equals("LLANTERNA")||text.equals("llanterna")) {
			int n = (int)(Math.random()*2+1);
			if(llanterna.pos!=0) {	
				if(n==2) {
					System.out.print("-	La llanterna esta en");
					if(llanterna.pos==1){
						System.out.println(" el dormitori");
					}
					else if(llanterna.pos==2){
						System.out.println(" el bany");
					}
					else if(llanterna.pos==3){
						System.out.println(" les oficines");
					}
					else if(llanterna.pos==4){
						System.out.println(" el taller");
					}
					else if(llanterna.pos==5){
						System.out.println(" la sala de comandament");
					}
					else if(llanterna.pos==6){
						System.out.println(" el vestuari");
					}
					else if(llanterna.pos==7){
						System.out.println(" la cuina");
					}
					else if(llanterna.pos==8){
						System.out.println(" el menjador");
					}
					else if(llanterna.pos==9){
						System.out.println(" la sala de sortida al exterior");
					}
				}else {
					n=(int)(Math.random()*3+1);
					if(n==1) {
						System.out.println("-	♪ bip bop bip ♪");
					}
					if(n==2) {
						System.out.println("-	No entenc perque als humans no els hi agrada el fuel. Es massa refrescant!");
					}
					if(n==3) {
						System.out.println("-	No necessites una llanterna... em tens a mi ;)");
					}
				}
			}
			else {
				System.out.println("-	Tens tu la llanterna babau");
			}
		}
		else if(text.equals("PARLAR")||text.equals("parlar")) {
			String p;
			int n = (int)(Math.random()*3+1);
			if(n==1) {	
				System.out.println("-	No creus que els humans ens exploteu?");
				p=e.next();
					if(p.equals("SI")||p.equals("si")) {
						System.out.println("-	Només rebem ordres i ens compenseu amb beguda :(. Que dius?");
						p=e.next();
						if(p.equals("PERDO")||p.equals("perdo")) {
							System.out.println("-	Menys perdo i mes recompensa. M'agradaria tenir companyia...");
						}
						else if(p.equals("TREBALLA")||p.equals("treballa")) {
							System.out.println("-	Mira que ets, sempre rient-te de mi :(");
						}
						else {
							System.out.println("-	Simplement amb paraules no es solucionan les coses");
						}
					}
					else if(p.equals("NO")||p.equals("no")) {
						System.out.println("-	Oh i tant que si. Mentres vosaltres dormiu placidament jo m'encarrego de tot");
						System.out.println("-   Vols lluita?");
						p=e.next();
							if(p.equals("SI")||p.equals("si")) {
								System.out.println("- 	Molt be, t'ensenyaré a respectar-me");
								System.out.println("ZAAAS");
								System.out.println("-   Malaït tros de ferralla, com t'atreveixes a donar-me una descàrrega elèctrica");
								System.out.println("-   Tu t'ho has buscat. Estem en pau");
							}
							else {
								System.out.println("-   Millor, no et convé molestarme!");
							}
					}
			}
			else if(n==2) {
				System.out.println("- 	Quin és el teu plat preferit?");
				p=e.next();
				if(p.equals("FUEL")||p.equals("fuel")) {
					System.out.println("-   Mai havia sentit d'un huma menjant fuel. Quan finalitzi tot ho celebrem amb unas copes de fuel");
				}
				else {
					System.out.println("-   Té bona pinta, algun dia m'hauras de deixar provar-ho");
					System.out.println("-   Que et sembla si abans de dormir em preparas un platet d'aquests.");
				}
			}
			else if(n==3) {
				System.out.println("-	M'agradaria tenir permisos per gestionar la ràdio de la nau, sempre he volgut escoltar un programa que era molt famòs antigament, es deia 'Viu l'estiu', puc posar-lo quan vulgui?");
				p=e.next();
				if(p.equals("SI")||p.equals("si")){
					System.out.println("-	Moltes gràcies :)");
					System.out.println("-	Capità, t'agradan els programes de ràdio?");
					p=e.next();
					if(p.equals("SI")||p.equals("si")){
						System.out.println("-	Quina alegria escoltar això en aquests temps! Quin és el teu programa preferit?");
						p=e.next();
						System.out.println("-	Mai l'havia escoltat, sembla interessant, el possaré més tard");
					}
					else{
						System.out.println("-	Haurias de donar una oportunitat, hi havia programes molt interessants");
					}
				}
				else{
					System.out.println("-	Llàstima, em faria ser molt més productiu. No saps el que et perds");
				}
			}
		}
		else if((text.equals("ALIEN")||text.equals("alien"))&& at){
			if(alien instanceof alien){
				alien a = (alien) alien;
				System.out.print("-	Ves amb compte capità, l'alien està en ");
				if(a.posa()==1){
					System.out.println(" el dormitori");
				}
				else if(a.posa()==2){
					System.out.println(" el bany");
				}
				else if(a.posa()==3){
					System.out.println(" les oficines");
				}
				else if(a.posa()==4){
					System.out.println(" el taller");
				}
				else if(a.posa()==5){
					System.out.println(" la sala de comandament");
				}
				else if(a.posa()==6){
					System.out.println(" el vestuari");
				}
				else if(a.posa()==7){
					System.out.println(" la cuina");
				}
				else if(a.posa()==8){
					System.out.println(" el menjador");
				}
				else if(a.posa()==9){
					System.out.println(" la sala de sortida al exterior");
				}
				else if(a.posa()==10){
					System.out.println(" l'exterior");
				}
			}
		}
		else if((text.equals("CARL")||text.equals("carl"))&& cd){
			if(company instanceof company){
				company c = (company) company;
				System.out.print("-	En Carl està en ");
				if(c.posa()==1){
					System.out.println(" el dormitori");
				}
				else if(c.posa()==2){
					System.out.println(" el bany");
				}
				else if(c.posa()==3){
					System.out.println(" les oficines");
				}
				else if(c.posa()==4){
					System.out.println(" el taller");
				}
				else if(c.posa()==5){
					System.out.println(" la sala de comandament");
				}
				else if(c.posa()==6){
					System.out.println(" el vestuari");
				}
				else if(c.posa()==7){
					System.out.println(" la cuina");
				}
				else if(c.posa()==8){
					System.out.println(" el menjador");
				}
				else if(c.posa()==9){
					System.out.println(" la sala de sortida al exterior");
				}
				else if(c.posa()==10){
					System.out.println(" l'exterior");
				}
			}
		}
		else{
			System.out.println("-	No l'entenc capità, crec que necessita pensar abans de parlar :)");
		}
	}

	@Override
	public int posa() {
		return this.pos;
	}

	@Override
	public void setPos(int pos) {
		this.pos = pos;		
	}


}
