import java.io.IOException;
import java.util.Scanner;

/**
* Classe principal.
* Execució del joc i utilització de totes les classes.
* @author Elsa Mena, Abel Millán i Nil Casabella / Mòdul 13, Escola PIA Santa Anna, Mataró
* @version 0.1, 2022/11/06
*/

public class Joc {
	/**
	 * @param fjoc boolean que comprova al final del joc, si està true vol dir que s'ha acabat, en cas contrari, el joc continua. 
	 * @param pujar boolean que indica si el jugador té permes pujar la palanca de comandament o no.
	 * @param pujat boolean indica si s'ha pujat la palanca.
	 * @param joc boolean comprova si la partida a finalitzat.
	 */
	static boolean fjoc=false, pujar=false, pujat=false, joc=false;

	Scanner e=new Scanner(System.in);
	/**
	 * Inicialitza la classe principal.
	 * Creen el mètode joc per evitar el static
	 */
	public static void main(String[] args) {
		while(!joc){	
			fjoc=false;
			pujar=false;
			pujat=false;
			Joc j = new Joc();
			j.principal();
		}
	}
	/**
	 * És el mètode principal que inicia la partida
	 * Inicia els diferents objectes necessaris per fer funcionar la partida. També trobem el bucle que fa funcionar la partida mentres aquesta sigui activa.
	 * @param rand és per obtenir una posició aleatoria on s'ubicarà la llanterna
	 */
	public void principal() {
		personatge bond=new bond();
		personatge ihall = new ihall();
		personatge alien = new alien();
		personatge carl = new company();

		zona[] mapa=new zona[10];
		mapa[0]=new dormitori("Dormitori",1,2,-1,-1,8);
		mapa[1]=new bany("Bany",2,-1,1,-1,3);
		mapa[2]=new oficina("Oficines",3,4,5,2,6);
		mapa[3]=new taller("Taller",4,-1,3,-1,-1);
		mapa[4]=new comandament("Comandament",5,3,-1,-1,-1);
		mapa[5]=new vestuari("Vestuari",6,-1,7,3,-1);
		mapa[6]=new cuina("Cuina",7,6,-1,8,-1);
		mapa[7]=new menjador("Menjador",8,-1,9,1,7);
		mapa[8]=new salasortida("Sala_Exterior",9,8,10,-1,-1);
		mapa[9]=new propulsor("Propulsors",10,9,-1,-1,-1);
		
		motxilla m = new motxilla();
		
		int rand=(int)(Math.random()*9+1);

		utils llanterna = new utils("llanterna",false,rand, false); 
		historia1();
		while (!fjoc){
			comprovara(alien, bond, mapa, m, carl);
			if(!fjoc){
				decidir(mapa, bond, m, llanterna,ihall, alien, carl);
				movc(carl,mapa);
				mova(alien, mapa);
			}
		}

		System.out.println("HAS FINALITZAT LA TEVA PARTIDA! VOLS CONTINUAR AMB UNA NOVA? [SI]/[NO]");
		String df=e.next();

		if(df.equals("SI")||df.equals("si")){
			joc=false;
		}
		else{
			joc=true;
		}
	}	
	
	
	//HISTORIA
	/*
	 * És un mètode el qual reproduirà la història a l'inici del joc.
	 * El seu objectiu és reproduir el text
	 */
	
	public void historia1(){
		System.out.println("\n\nAny 2120 D.C.\r\n"
				+ "La nau PiaXXII explora l'espai inhòspit en direcció al planeta SUMMEM on es creu que hi poden haver les condicions idònies per arrelar una nova \nvida, que ja no és possible dur a terme en el planeta Pia.");		
		
		sleep(13);
		System.out.println("\nDesprés d'un llarg període d'hivernació provocada pel llarg viatge al planeta SUMMEM, el cap de la tripulació de la PiaXXII es desperta del son \ninduït. L'ordinador de la nau iHall ha detectat una anomalia en el sistema i necessita de la intervenció del cap de la tripulació per resoldre'l.");
		sleep(13);
		System.out.println("-	Que tal ha dormit capità Bond?. Em sap greu destorbar-lo però he detectat una anomalia a la nau. Sembla ser que se'ns gira feina.");
		sleep(5);
		System.out.println("-	Coi de ferralla 'intel·ligent'!");
		sleep(3);
		System.out.println("-       En Bond està altament irritat ja que el somni no podia ser d'allò més excitant i realista...");
		sleep(3);
		System.out.println("- 	Espero que hagi estat indispensable haver-me destorbat el son!. Sinó preparat per que et programi unes quantes sessions de Treball cooperatiu amb un tal Lago...");
		sleep(5);
		System.out.println("-	Li garanteixo Capità Bond que no és una falsa alarma, no m'arriscaria a patir un càstig similar...");
		sleep(5);
		System.out.println("-	Està bé iHall – murmurà el capità ja més despert i calmat – donem l'informe de la situació i anem per feina!, que vull tornar a agafar el son.");
		sleep(13);
		System.out.println("iHall procedeix a explicar detalladament la situació al capità	Resulta que hem xocat amb un petit aeròlit que ha provocat petits danys al sistema de propulsió de la nau que requereixen de la intervenció humana.");
		sleep(13);
		System.out.println("Des dels dormitoris cal arribar a la zona de motors i propulsió. Les diferents zones estan unides per portes automàtiques que cal obrir amb la tarja personal de cada un dels tripulants però en Bond no recorda on l'ha deixat.");
		sleep(15);
		System.out.println("Sense la tarja, depèn al 100% de la comunicació amb iHall perquè li vagi obrin les portes entre zones. Darrerament però iHall està un pel transposat i en moltes ocasions no fa cas a en Bond i el posa a prova donant-li pistes o indicacions falses. Potser pot intentar agafar alguna de les targes dels companys o intentar buscar-la per la nau.");		
		sleep(10);
		System.out.println("Per aconseguir reparar els motors, cal posar-se el vestit d'astronauta que està al vestuari. Sense ell no es podria sobreviure a l'exterior de la nau.");
		sleep(15);
		System.out.println("Compte però que, revivint la famosa saga d'en Ridley Scott, tenim un Alien donant voltes per la nau. Se'l coneix amb el nom de “Cambralien”, i s'ha colat des de la conselleria d'educació a la Nau per intentar sabotejar la missió. En Cambraliennomés té un punt dèbil, li agraden els donuts!.");			
		sleep(10);
		System.out.println("Per tant, si ens trobem amb ell, potser serà l'única manera de poder-lo distreure perquè ens deixi tranquils.");	
		sleep(13);
		System.out.println("Necessitarem una eina especial per poder reparar els motors. L'eina està al taller de la nau. Un cop arreglem els propulsors del motor, haurem de córrer cap a la zona de la sala de comandaments per posar novament en marxa els motors.");		
		sleep(10);
		System.out.println("Podrà en Bond amb en Cambralien?. Podrà reparar els motors i aconseguir prosseguir la seva missió el Pia XXII cap a SUMMEM?.");		
							
	}	
	/*
	 * És un mètode el qual reproduirà la història al final del joc si ha estat amb èxit.
	 * El seu objectiu és reproduir el text.
	 * 
	 */
	public void finalh(){
		System.out.println("Misió completada amb èxit!");
		sleep(5);
		System.out.println("El capità Bond va poder tornar al seu estimat llit on va tornar a la son induïda.");
		sleep(10);
		System.out.println("L'iHall no va poder descansar, ja que va haver de netejar les instal·lacions de la nau (sobretot els banys a olor a orina).");
		sleep(13);
		System.out.println("En Carl abans de tornar-se a dormir, va poder fer una videotrucada amb la seva filla, però la dona d'en Carl el va deixar per el seu professor de gimnàs.");
		sleep(15);
		System.out.println("La nau XXII estava programada per tornar al seu rumb habitual i poder descobrir altres espècies extraterrestres");
	}
	
	//MENU DECISIONS
	/*
	 * Mètode al qual ensenya les diferents comandes que podrà utilitzar l'usuari.
	 * Envia la comanda en un altre mètode per tal d'executar l'acció.
	 */
	public void decidir(zona[] mapa, personatge bond, motxilla m, utils llanterna,personatge ihall, personatge alien, personatge company) {
		System.out.println("Que hauria de fer acontinuació...");
		if(llanterna.pos!=0) {
			System.out.println("Seria bo tenir llanterna, esta tot bastant fosc. L'ihall sabrá on està");
		}
		System.out.println("ANAR      ENCENDRE    USAR");
		System.out.println("DEIXAR    APAGAR      PARLAR");
		System.out.println("AGAFAR    OBRIR       PUJAR");
		String decidir = e.next();
		opcions(decidir, mapa, bond, m, llanterna, ihall, alien, company);
	}

	/*
	 * Mètode al qual conté un switch amb diverses opcions que haurà escollit el jugador.
	 * Deriva cada acció al seu mètode corresponent.
	 */
	
	public void opcions(String decisio, zona[] mapa, personatge bond, motxilla m, utils llanterna, personatge ihall, personatge alien, personatge company) {
		switch(decisio) {
		case "ANAR": 
		case "anar":
			anar(mapa, bond, alien);
			break;
		case "ENCENDRE": 
		case "encendre":
			encendre(llanterna, alien, bond);
			break;
		case "USAR":
		case "usar":
			usar(mapa, bond, m, alien,company);
			break;
		case "DEIXAR": 
		case "deixar":
			if(bond.posa()!=10){
				deixar(m, mapa,bond, llanterna, alien);
			}
			else{
				System.out.println("No puc deixar cosas a l'exterior");
			}
			break;
		case "APAGAR": 
		case "apagar":
			apagar(llanterna, alien, bond);
			break;
		case "PARLAR":  
		case "parlar":
			parlar(llanterna, ihall, alien,company);
			break;
		case "AGAFAR": 
		case "agafar":
			if(bond.posa()!=10){
				agafar(mapa,bond, m, llanterna, alien);		
			}
			else{
				System.out.println("No puc agafar cosas de l'exterior");
			}	
			break;
		case "OBRIR":	
		case "obrir":
			obrir(mapa, bond, m, llanterna, alien);
			break;
		case "PUJAR": 
		case "pujar":
			if(bond.posa()==5){
				if(pujar){
					System.out.println("Anem a pujar la palanca.");
					System.out.println("¡Guah, guah…! Després de tant de temps esta bastant dura la palanca");
					System.out.println("PLUM! Activada la refrigeració");
					System.out.println("Ara ja simplement anar a la habitació a dormir, potser primer li demano un plat a l'ihall");
					pujat=true;
				}
			}
			break;
		default:
			System.out.println("Haig de seguir dormit...");
			posm(alien, bond);
		}
	}
	
	
	//DECISIONS
	/*
	 * Mètode per encendre la llanterna
	 * Encendre la llanterna 
	 */
	public void encendre(utils llanterna,personatge alien, personatge bond) {
		if(llanterna.pos==0) {
			if(!llanterna.utilitzar) {
				System.out.println("CLICK");
				System.out.println("Llanterna encesa!");
				llanterna.utilitzar=true;
			}else {
				System.out.println("Actualment esta encesa");
				posm(alien, bond);
			}
		}
		else {
			System.out.println("No tinc la llanterna!!!");
			posm(alien,bond);
		}
	}
	/*
	 * Mètode per apagar la llanterna
	 * Apagar la llanterna 
	 */
	public void apagar(utils llanterna,personatge alien, personatge bond) {
		if(llanterna.pos==0) {
			if(llanterna.utilitzar) {
				System.out.println("CLICK");
				System.out.println("Llanterna apagada!");
				llanterna.utilitzar=true;
			}else {
				System.out.println("Actualment esta apagada");
				posm(alien,bond);
			}
		}
		else {
			System.out.println("No tinc la llanterna!!!");
			posm(alien,bond);
		}
	}
	/*
	 * Mètode per fer moure el personatge
	 * Depenen de la desició del usuari portarà els diferents mètodes de direccions.
	 */
	public void anar(zona[] mapa, personatge bond, personatge alien) {
		String decidir;
		System.out.println("On vols anar?");
		System.out.println("AMUNT  ABAIX  ESQUERRA  DRETA");
		decidir=e.next();
		int posa=bond.posa();
		if(decidir.equals("AMUNT") || decidir.equals("amunt")) {
			int nord=mapa[posa-1].nord(posa);
			if(mapa[4] instanceof comandament){
				comandament cm=(comandament) mapa[4];
				if(posa==3 && !cm.identificador.utilitzar){
					nord=-1;
					System.out.println("La porta esta tancada. Aquest taller és on treballa en Carl, necessitaré trobar una identificació seva per entrar");
				}
			}
			if(nord!=-1){
				System.out.println("Porta oberta");
				bond.setPos(nord);
			}
			else {
				System.out.println("No puc anar en aquesta direcció");
			}
		}
		else if(decidir.equals("ABAIX") || decidir.equals("abaix")) {
			int sud=mapa[posa-1].sud(posa);
			if(mapa[2] instanceof oficina && mapa[5] instanceof vestuari){
				vestuari v= (vestuari) mapa[5];
				oficina o =(oficina) mapa[2];
				if(posa==3 && !o.targeta.utilitzar){
					sud=-1;
					System.out.println("La porta esta tancada. Per entrar a la sala de comandament necessitaré utilitzar la meva targeta de la nau");
					if(!o.targeta.trobat){
						System.out.println("On la deixaria?");
					}
				}
				else if(posa==9 && !v.vestimenta.utilitzar){
					sud=-1;
					System.out.println("No puc sortir sense tenir posada la vestimenta espacial, seria massa arriscat.");
				}
			}
			if(sud!=-1){
				System.out.println("Porta oberta");
				bond.setPos(sud);;
				if(bond.posa()==1 && pujat){
					fjoc=true;
					finalh();
				}
			}
			else {
				System.out.println("No puc anar en aquesta direcció");
			}
		}
		else if(decidir.equals("ESQUERRA") || decidir.equals("esquerra")) {
			int oest=mapa[posa-1].oest(posa);
			if(oest!=-1){
				System.out.println("Porta oberta");
				bond.setPos(oest);
			}
			else {
				System.out.println("No puc anar en aquesta direcció");
			}
		}
		else if(decidir.equals("DRETA") || decidir.equals("dreta")) {
			int est=mapa[posa-1].est(posa);
			if(est!=-1){
				System.out.println("Porta oberta");
				bond.setPos(est);
				if(bond.posa()==1 && pujat){
					fjoc=true;
					finalh();
				}
			}
			else {
				System.out.println("No puc anar en aquesta direcció");
			}
		}
		else {
			System.out.println("Haig de seguir dormit...");
		}
		if (bond.posa()!=posa){
			mapa[posa-1].descripcions(bond.posa());
		}
		else{
			posm(alien,bond);
		}
		
		System.out.println(bond.posa());
	}
	
	/*
	 * Mètode per obrir els diferents elements del entorn
	 * Interactua amb els diferents elements del entorn o si conté elements al inventari del personatge, et mostrarà cadascun d'ells envian-te un mètode especific per obrir motxilla.
	 */
	public void obrir(zona[] mapa, personatge bond, motxilla m, utils llanterna, personatge alien){
		System.out.println("Que hauria d'obrir?");
		int posa=bond.posa();
		if(posa == 1) {
			System.out.println("CALAIXOS   MOTXILLA");
		
			String decisio =e.next();
			if(decisio.equals("MOTXILLA")||decisio.equals("motxilla")) {
				motxilla(m);
			}
			else if(decisio.equals("calaixos")||decisio.equals("CALAIXOS")) {
				System.out.println("Quin calaix hauria d'obrir?");
				System.out.println("BOND   CARL");
				String dcalaix = e.next();
				
				if(dcalaix.equals("BOND")||dcalaix.equals("bond")) {
					
					if(mapa[0] instanceof dormitori){
						dormitori d = (dormitori) mapa[0];
						if(!d.clau.trobat){
							System.out.println("És veritat, vaig guardar la clau del meu escriptori aquí");
							m.inventari.add(d.clau.nom);
							System.out.println("clau escriptori guardada en l'inventari!");
							d.clau.pos=0;
							d.clau.trobat=true;	
						}
						else {
							System.out.println("Només queden llibres en el meu calaix.");
							posm(alien,bond);
						}
					}
				}
				else if(dcalaix.equals("CARL")||dcalaix.equals("carl")) {
					System.out.println("Només hi ha un parell de consoles portàtils");
					System.out.println("Sempre li ha agradat aquestes coses, sobre tot minecraft...(Java)");
					if(llanterna.pos == 1 && !llanterna.trobat){
						System.out.println("Vajaaa, en el fons també esta la llanterna. Serà imprescindible utilitzar-la");
						m.inventari.add("llanterna");
						llanterna.pos=0;
						System.out.println("llanterna guardada en l'inventari");
						llanterna.trobat=true;
					}
				}
				else {
					System.out.println("Haig de seguir dormit...");
					posm(alien,bond);
				}
			}
		}
		else if(posa==2){
			System.out.println("ARMARI   MOTXILLA");
		
			String decisio =e.next();
			if(decisio.equals("MOTXILLA")||decisio.equals("motxilla")) {
				motxilla(m);
			}
			else if(decisio.equals("armari")||decisio.equals("ARMARI")) {
				if(mapa[1] instanceof bany){	
					bany b= (bany) mapa[1];
					if(!b.contrasenya.trobat){
						System.out.println("Clac");
						System.out.println("Anem a veure que trobo per aquí, mai vaig arribar a obrir aquest armari");
						System.out.println("Veig diferents medicines, entre elles destaca un pot amb el nom d'en Carl. No sabia que tenia alguna enfermetat, vaig a tafanejar.");
						System.out.println("Vaja quina sorpresa, dintre he trobat un paper amb uns números '3572', que voldran dir?");
						
						m.inventari.add(b.contrasenya.nom);
						b.contrasenya.pos=0;
						System.out.println("paper amb numeros guardat en l'inventari!");	
						b.contrasenya.trobat=true;

						if(llanterna.pos == 2 && !llanterna.trobat){
							System.out.println("Vajaaa, en el fons també esta la llanterna. Serà imprescindible utilitzar-la");
							m.inventari.add("llanterna");
							llanterna.pos=0;
							System.out.println("llanterna guardada en l'inventari");
							llanterna.trobat=true;
						}
					}
					else{
						System.out.println("Simplement quedan medicines varias, ho tindré en compte per si les necesitem");
						posm(alien,bond);
					}
				}
			}
			else {
					System.out.println("Haig de seguir dormit...");
					posm(alien,bond);
			}
		}
		else if(posa==3){
			System.out.println("CALAIX   MOTXILLA");
		
			String decisio =e.next();
			if(decisio.equals("MOTXILLA")||decisio.equals("motxilla")) {
				motxilla(m);
			}
			else if(decisio.equals("CALAIX")||decisio.equals("calaix")){
				if(mapa[0] instanceof dormitori){	
					dormitori d= (dormitori) mapa[0];
					if(d.clau.utilitzar){
						if(mapa[2] instanceof oficina){	
							oficina o= (oficina) mapa[2];
							if(!o.targeta.trobat){
								System.out.println("A ver que vaig guardar...");
								System.out.println("AHHH, la targeta de la nau!!!");
								m.inventari.add(o.targeta.nom);
								o.targeta.pos=0;
								o.targeta.trobat=true;

								System.out.println("targeta bond guardada en l'inventari!");	
								if(llanterna.pos == 3 && !llanterna.trobat){
									System.out.println("Vajaaa, en el fons també esta la llanterna. Serà imprescindible utilitzar-la");
									m.inventari.add("llanterna");
									llanterna.pos=0;
									System.out.println("llanterna guardada en l'inventari");
									llanterna.trobat=true;
								}
							}
							else{
								System.out.println("Només queden papers i diferents estris d'oficina");
								posm(alien,bond);	
							}
						}
					}
					else{
						System.out.println("Esta tancat amb CLAU. No puc el puc obrir.");
						posm(alien,bond);
					}
				}			
			}
			
			else {
				System.out.println("Haig de seguir dormit...");
				posm(alien,bond);
			}
		}
		else if(posa==4){
			System.out.println("ARMARI   MOTXILLA");
		
			String decisio =e.next();
			if(decisio.equals("MOTXILLA")||decisio.equals("motxilla")) {
				motxilla(m);
			}
			else if(decisio.equals("ARMARI")||decisio.equals("armari")){
				if(!llanterna.utilitzar){
					System.out.println("Sé que esta l'armari, però és massa fosc per veure qualsevol cosa. Necessitaré més llum");
				}
				else{
					if(mapa[3] instanceof taller){	
						taller t= (taller) mapa[3];
						if(!t.eina.trobat){
							System.out.println("CHIIIR");
							System.out.println("Veig que tenim un bon sumnistrament d'eines. M'emportaré aquesta, estic segur que la necessitaré.");
							m.inventari.add("eina");
							System.out.println("eina guardada en l'inventari");
							t.eina.trobat=true;
							t.eina.pos=0;
						}
						else{
							System.out.println("Hi ha un munt d'eines, ara mateix no les necessito");
							posm(alien,bond);
						}	
					}
				}
			}
			else {
				System.out.println("Haig de seguir dormit...");
				posm(alien,bond);
			}
		}
		else if(posa==5){
			System.out.println("MOTXILLA");
		
			String decisio =e.next();
			if(decisio.equals("MOTXILLA")||decisio.equals("motxilla")) {
				motxilla(m);
			}
			else {
				System.out.println("Haig de seguir dormit...");
				posm(alien,bond);
			}
		}
		else if(posa==6){
			System.out.println("MOTXILLA   TAQUILLA");
		
			String decisio =e.next();
			if(decisio.equals("MOTXILLA")||decisio.equals("motxilla")) {
				motxilla(m);
			}
			else if(decisio.equals("TAQUILLA")||decisio.equals("taquilla")){
				if(mapa[5] instanceof vestuari){	
					vestuari v= (vestuari) mapa[5];
					if(!v.vestimenta.trobat){
						System.out.println("CLANK");
						System.out.println("S'ha obert la taquilla");
						System.out.println("Quant de temps fa que no veia aquests vestits espacials, agafaré el meu. Espero que l'ihall el rentès l'últim cop...");
						m.inventari.add("vestimenta");
						v.vestimenta.trobat=true;
						v.vestimenta.pos=0;
						System.out.println("vestimenta guardada en l'inventari");
						if(llanterna.pos == 6 && !llanterna.trobat){
							System.out.println("Vajaaa, en el fons també esta la llanterna. Serà imprescindible utilitzar-la");
							m.inventari.add("llanterna");
							llanterna.pos=0;
							System.out.println("llanterna guardada en l'inventari");
							llanterna.trobat=true;
						}
					}
					else{
						System.out.println("Esta buida");
						posm(alien,bond);
					}
				}
			}
			else {
				System.out.println("Haig de seguir dormit...");
				posm(alien,bond);
			}
		}
		else if(posa==7){
			System.out.println("MOTXILLA");
		
			String decisio =e.next();
			if(decisio.equals("MOTXILLA")||decisio.equals("motxilla")) {
				motxilla(m);
			}
			else {
				System.out.println("Haig de seguir dormit...");
				posm(alien,bond);
			}
		}
		else if(posa==8){
			if(mapa[7] instanceof menjador){
			menjador men = (menjador) mapa[7];
			System.out.print("MOTXILLA   BOSSA");
			if(men.caixa.trobat){
				System.out.println("   JOIER");
			}
			else{
				System.out.println();
			}
			String decisio =e.next();
			if(decisio.equals("MOTXILLA")||decisio.equals("motxilla")) {
				motxilla(m);
			}
			else if(decisio.equals("BOSSA")||decisio.equals("bossa")){
				
					if(!men.caixa.trobat){
						System.out.println("En la cadira on seu en Carl hi ha una bossa. Vaig a tafanejar");
						System.out.println("TRRRR");
						System.out.println("Anem a veure...");
						System.out.println("Hi ha un joier. Sembla que per obrir-ho necessitaré una contrasenya");
						System.out.println("La deixaré en la taula mentres, la OBRIRé més tard. Vaig a seguir mirant la bossa");
						men.caixa.trobat=true;
						if(llanterna.pos == 8 && !llanterna.trobat){
							System.out.println("Vajaaa, en el fons també esta la llanterna. Serà imprescindible utilitzar-la");
							m.inventari.add("llanterna");
							llanterna.pos=0;
							System.out.println("llanterna guardada en l'inventari");
							llanterna.trobat=true;
						}
					}
					else{
						System.out.println("Sembla que no queda res més");
						posm(alien,bond);
					}	
				}
				else if(decisio.equals("JOIER")||decisio.equals("joier")&& men.caixa.trobat && !men.caixa.utilitzar && !men.flor.trobat){
					System.out.println("Bé, anem a provar amb la contrasenya, sembla que són 4 digits");
					String password = e.next();
					if(password.equals("3572")){
						System.out.println("CLICK");
						System.out.println("S'ha obert, es pot sentir una melodia.");
						System.out.println("Dintre hi ha una fotografia d'en Carl amb la seva familia");
						System.out.println("Ara que ho penso... Aquesta contrasenya significa...");
						System.out.println("1-.,=!	2-abc	3-def\n4-ghi	5- jkl	6-mno\n7-pqrs	8-tuv	9-wxyz");
						System.out.println("Si no recordo malament la seva filla es diu 'Elsa' i si s'utilitza de manera correcte el teclat alfanúmeric surt el seu nom. M'encanta aquest tio!");
						System.out.println("Sembla que darrera de la foto hi ha una cosa enganxada...");
						System.out.println("Sembla una flor, una flor de cirerer, m'el guardaré en la motxilla per si un cas");
						men.flor.trobat=true;
						men.flor.pos=0;
						m.inventari.add("flor");
					}
					else{
						System.out.println("No s'obre... Sembla que m'he confòs");
					}
				}
				else {
					System.out.println("Haig de seguir dormit...");
					posm(alien,bond);
				}
			}
		}
		else if(posa==9){
			System.out.println("MOTXILLA   TAQUILLA");
		
			String decisio =e.next();
			if(decisio.equals("MOTXILLA")||decisio.equals("motxilla")) {
				motxilla(m);
			}
			else if(decisio.equals("TAQUILLA")||decisio.equals("taquilla")){
				if(llanterna.pos == 9 && !llanterna.trobat){
					System.out.println("Vajaaa, en el fons també esta la llanterna. Serà imprescindible utilitzar-la");
					m.inventari.add("llanterna");
					llanterna.pos=0;
					System.out.println("llanterna guardada en l'inventari");
					llanterna.trobat=true;
				}
				else{
					System.out.println("Esta buida...");
					posm(alien,bond);
				}
			}
			else {
				System.out.println("Haig de seguir dormit...");
				posm(alien,bond);
			}
		}
		else if(posa==10){
			System.out.println("MOTXILLA");
		
			String decisio =e.next();
			if(decisio.equals("MOTXILLA")||decisio.equals("motxilla")) {
				motxilla(m);
			}
			else {
				System.out.println("Haig de seguir dormit...");
				posm(alien,bond);
			}
		}		
	}
	/*
	 * Mètode que permet interactuar amb els diferents elements del entorn o de la motxilla
	 * Permet utilitzar els diferents objectes que hi ha al mapa o els diferents elements que tingui el jugador en l'inventari.
	 */
	public void usar(zona[] mapa, personatge bond, motxilla m, personatge alien, personatge company){
		System.out.println("Que hauria d'utilitzar?");
		int posa=bond.posa();
		String decisio;
		if(posa == 1) {	
			System.out.println("Millor només utilitzar coses de la motxilla, no vull molestar a ningú");
			if(m.inventari.size()>0){
				motxilla(m);	
				System.out.println("Quin objecte hauria d'utilitzar?");
				decisio=e.next();
				if(decisio.equals("vestimenta")||decisio.equals("VESTIMENTA")){
					vestimenta(mapa, alien, bond);
				}
				else if((decisio.equals("flor")||decisio.equals("FLOR")) && m.inventari.contains("flor")){
					flor(mapa, alien, bond);
				}
				else{
					System.out.println("No té sentit utilitzar això en aquests moments");
					posm(alien,bond);
				}
			}
			else{
				System.out.println("No tinc cap objecte per utilitzar");
				posm(alien,bond);
			}
		}
		else if(posa == 2) {
			System.out.println("MOTXILLA   AIXETA   VATER");
		
			decisio =e.next();
			if(decisio.equals("MOTXILLA")||decisio.equals("motxilla")) {
				if(m.inventari.size()>0){
					motxilla(m);	
					System.out.println("Quin objecte hauria d'utilitzar?");
					decisio=e.next();
					if(decisio.equals("vestimenta")||decisio.equals("VESTIMENTA")){
						vestimenta(mapa, alien,bond);
					}
					else if((decisio.equals("flor")||decisio.equals("FLOR")) && m.inventari.contains("flor")){
						flor(mapa, alien, bond);
					}
					else{
						System.out.println("No té sentit utilitzar això en aquests moments");
						posm(alien,bond);
					}
				}
				else{
					System.out.println("No tinc cap objecte per utilitzar");
					posm(alien,bond);
				}
			}
			else if(decisio.equals("AIXETA")||decisio.equals("aixeta")){
				System.out.println("Té mala pinta utilitzar l'aixeta amb la pica trencada. Per rentarme les mans la necessitaria, vaig a obrirla i espero no crear un desastre...");
				System.out.println("Ploc");
				System.out.println("Quin fàstic... La poca aigua que surt és marró, millor no em rento las mans, a saber que tindra aquesta aigua. Esperaré a que l'ihall s'encarregui.");
			}
			else if(decisio.equals("VATER")||decisio.equals("vater")){
				System.out.println("Em fa cosa fer les meves necessitats aqui, però simplement esta una mica oxidat.");
				System.out.println("PSSSS");
				System.out.println("CLACK");
				System.out.println("SWOOSH");
				System.out.println("Ja em sento molt millor, continuem amb la tasca");
			}
			else{
				System.out.println("No té sentit");
				posm(alien,bond);
			}
		}
		else if(posa == 3){
			System.out.println("Només hauria d'utlitzar els objectes de la motxilla, no vull liarla amb tants papers per aqui");
			motxilla(m);
			System.out.println("Quin objecte hauria d'utilitzar?");
			decisio=e.next();
			if(m.inventari.size()>0){
				if(mapa[0] instanceof dormitori && mapa[4] instanceof comandament && mapa[2] instanceof oficina && company instanceof company){	
					dormitori d= (dormitori) mapa[0];
					comandament cm = (comandament) mapa[4];
					company comp=(company) company;
					oficina o = (oficina) mapa[2];
					if((decisio.equals("clau")||decisio.equals("CLAU")) && d.clau.utilitzar == false && d.clau.trobat){
						System.out.println("Vaig a utilitzar la clau en el meu escriptori");
						System.out.println("CLICK");
						System.out.println("S'ha desbloquejat sense problemes.");
						d.clau.utilitzar = true;
					}
					else if((decisio.equals("identificador")||decisio.equals("IDENTIFICADOR")) && cm.identificador.utilitzar == false && cm.identificador.trobat){
						System.out.println("-	Si actives l'identificador d'en carl el despertaras, segur que vols utilizar-ho?");
						String option=e.next();
						if(option.equals("si")||option.equals("SI")){
							System.out.println("BIP");
							System.out.println("Sembla que s'ha desbloquejat la porta del taller");
							cm.identificador.utilitzar=true;
							comp.despert=true;
							comp.cont++;
						}
					}
					else if((decisio.equals("targeta")||decisio.equals("TARGETA")) && o.targeta.utilitzar == false && o.targeta.trobat){
						System.out.println("BIP");
						System.out.println("Sembla que s'ha desbloquejat la porta de la sala de comandament");
						o.targeta.utilitzar=true;
					}
					else if((decisio.equals("flor")||decisio.equals("FLOR")) && m.inventari.contains("flor")){
						flor(mapa, alien, bond);
					}
					else if(decisio.equals("vestimenta")||decisio.equals("VESTIMENTA")){
						vestimenta(mapa, alien,bond);
					}
					else{
						System.out.println("No té sentit utilitzar això aqui!");
						posm(alien,bond);
					}
				}
			}
			else{
				System.out.println("No tinc cap objecte per utilitzar");
				posm(alien,bond);
			}
		}
		else if(posa == 4){
			System.out.println("En carl hauria de plantejar-se posar més llum, seria poc pràctic utilitzar coses aqui, millor en un altre lloc");
			posm(alien,bond);
		}
		else if(posa==5){
			System.out.println("Millor només utilitzar coses de la motxilla, haig d'acabar la missió abans de fer funcionar la nau");
			if(m.inventari.size()>0){
				motxilla(m);	
				System.out.println("Quin objecte hauria d'utilitzar?");
				decisio=e.next();
				if(decisio.equals("vestimenta")||decisio.equals("VESTIMENTA")){
					vestimenta(mapa,alien,bond);
				}
				else if((decisio.equals("flor")||decisio.equals("FLOR")) && m.inventari.contains("flor")){
					flor(mapa, alien, bond);
				}
				else{
					System.out.println("No té sentit utilitzar això en aquests moments");
					posm(alien,bond);
				}
			}
			else{
				System.out.println("No tinc cap objecte per utilitzar");
				posm(alien,bond);
			}
		}
		else if(posa==6){
			System.out.println("MOTXILLA   DUTXA");
		
			decisio =e.next();
			if(decisio.equals("MOTXILLA")||decisio.equals("motxilla")) {
				if(m.inventari.size()>0){
					motxilla(m);	
					System.out.println("Quin objecte hauria d'utilitzar?");
					decisio=e.next();
					if(decisio.equals("vestimenta")||decisio.equals("VESTIMENTA")){
						vestimenta(mapa,alien,bond);
					}
					else if((decisio.equals("flor")||decisio.equals("FLOR")) && m.inventari.contains("flor")){
						flor(mapa, alien, bond);
					}
					else{
						System.out.println("No té sentit utilitzar això en aquests moments");
						posm(alien,bond);
					}
				}
				else{
					System.out.println("No tinc cap objecte per utilitzar");
					posm(alien,bond);
				}
			}
			else if(decisio.equals("DUTXA")||decisio.equals("dutxa")){
				System.out.println("Crec que no és un bon moment per utilitzar les dutxes");
				System.out.println("A més l'aigua esta molt freda i no m'agrada dutxar-me recent llevat");
			}
			else{
				System.out.println("No té sentit");
				posm(alien,bond);
			}
		}
		else if(posa==7){
			System.out.println("MOTXILLA   FORN");
			decisio =e.next();
			if(decisio.equals("MOTXILLA")||decisio.equals("motxilla")) {
				if(m.inventari.size()>0){
					motxilla(m);	
					System.out.println("Quin objecte hauria d'utilitzar?");
					decisio=e.next();
					if(decisio.equals("vestimenta")||decisio.equals("VESTIMENTA")){
						vestimenta(mapa,alien,bond);
					}
					else if((decisio.equals("flor")||decisio.equals("FLOR")) && m.inventari.contains("flor")){
						flor(mapa, alien, bond);
					}
					else{
						System.out.println("No té sentit utilitzar això en aquests moments");
						posm(alien,bond);
					}
				}
				else{
					System.out.println("No tinc cap objecte per utilitzar");
					posm(alien,bond);
				}
			}
			else if(decisio.equals("FORN")||decisio.equals("forn")){
				System.out.println("No sé cuinar però l'ihall pot fer coses impresionants, de les poques virtuts d'aquest tros de ferralla.");
				System.out.println("Li diré que em prepari alguna cosa abans de dormir un altre cop");
			}
			else{
				System.out.println("No té sentit");
				posm(alien,bond);
			}
		}
		else if(posa==8){
			System.out.println("MOTXILLA   PULSADOR");
		
			decisio =e.next();
			if(decisio.equals("MOTXILLA")||decisio.equals("motxilla")) {
				if(m.inventari.size()>0){
					motxilla(m);	
					System.out.println("Quin objecte hauria d'utilitzar?");
					decisio=e.next();
					if(decisio.equals("vestimenta")||decisio.equals("VESTIMENTA")){
						vestimenta(mapa,alien,bond);
					}
					else if((decisio.equals("flor")||decisio.equals("FLOR")) && m.inventari.contains("flor")){
						flor(mapa, alien, bond);
					}
					else{
						System.out.println("No té sentit utilitzar això en aquests moments");
						posm(alien,bond);
					}
				}
				else{
					System.out.println("No tinc cap objecte per utilitzar");
					posm(alien,bond);
				}
			}
			else if(decisio.equals("PULSADOR")||decisio.equals("pulsador")){
				System.out.println("En mig de la taula rodona puc veure el pulsador");
				System.out.println("Si el pulso sortirà una bola de discoteca i sonarà una cançó, crec que vaig deixar posada la meva preferida");
				System.out.println("Hauria de pulsar?");
				String musica=e.next();
				if(musica.equals("SI")||musica.equals("si")){
					music();
				}
				else{
					System.out.println("millor en un altre moment");
				}
				System.out.println("També puc posar una canço mitjançant l'ihall, hauria de fer-ho?");
				musica=e.next();
				if(musica.equals("SI")||musica.equals("si")){
					System.out.println("L'ihall només necessita l'enllaç de l'app Youtube:");
					String enllaç=e.next();
					music2(enllaç);
				}
				else{
					System.out.println("millor en un altre moment");
					posm(alien,bond);
				}
			}
			else{
				System.out.println("No té sentit");
				posm(alien,bond);
			}
		}
		else if(posa==9){
			System.out.println("Aqui només puc utilitzar coses de la motxilla, és la sala de descontaminació per sortir a l'exterior");
			if(m.inventari.size()>0){
				motxilla(m);	
				System.out.println("Quin objecte hauria d'utilitzar?");
				decisio=e.next();
				if(decisio.equals("vestimenta")||decisio.equals("VESTIMENTA")){
					vestimenta(mapa,alien,bond);
				}
				else if((decisio.equals("flor")||decisio.equals("FLOR")) && m.inventari.contains("flor")){
					flor(mapa, alien, bond);
				}
				else{
					System.out.println("No té sentit utilitzar això en aquests moments");
					posm(alien,bond);
				}
			}
			else{
				System.out.println("No tinc cap objecte per utilitzar");
				posm(alien,bond);
			}
		}
		else if(posa==10){
			System.out.println("Aqui només puc utilitzar coses de la motxilla, no vull utilitzar coses desconegudes de l'exterior");
			if(m.inventari.size()>0){
				motxilla(m);	
				System.out.println("Quin objecte hauria d'utilitzar?");
				decisio=e.next();
				if(mapa[3] instanceof taller){	
					taller t= (taller) mapa[3];
					if(decisio.equals("vestimenta")||decisio.equals("VESTIMENTA")){
						System.out.println("No em puc treure la vestimenta en l'exterior");
					}
					else if((decisio.equals("flor")||decisio.equals("FLOR")) && m.inventari.contains("flor")){
						flor(mapa, alien, bond);
					}
					else if((decisio.equals("eina")||decisio.equals("EINA"))&& t.eina.trobat && t.eina.pos==0){
						System.out.println("Esta tot molt calent, però veig que principalment hi ha un desgast dels encaixos de la nau. Si apreto els cargols crec que ja serà suficient per poder activar la refrigeració de la nau, PUJANT la palanca de ventilació en la sala de COMANDAMENT");
						System.out.println("Crec que ja està, sembla un volcà a punt de petar, haig d'anar ja a activar la refrigeració.");
						pujar=true;
					}
				}
			}
			else{
				System.out.println("No tinc cap objecte per utilitzar");
				posm(alien,bond);
			}
		}
	}
	/*
	 * Mètode que permet deixar els objectes de l'inventari.
	 * Si el jugador té objectes a l'inventari pregunta a l'usuari quin vol deixar i es queda a la posició actual del jugador
	 */

	public void deixar(motxilla m, zona[] mapa, personatge bond, utils llanterna,personatge alien){
		if(m.inventari.size()>0){
			motxilla(m);
			System.out.println("Que hauria de deixar?");
			String decisio= e.next();
			if(m.inventari.contains(decisio) || m.inventari.contains(decisio.toLowerCase())){
				if(mapa[0] instanceof dormitori){	
					dormitori d= (dormitori) mapa[0];
					if(decisio.equals(d.clau.nom)){
						d.clau.pos=bond.posa();
						m.inventari.remove("clau");
					}
				}
				if(mapa[1] instanceof bany){	
					bany b= (bany) mapa[1];
					if(decisio.equals(b.contrasenya.nom)){
						b.contrasenya.pos=bond.posa();
						m.inventari.remove("paper");
					}
				}
				if(mapa[2] instanceof oficina){	
					oficina o= (oficina) mapa[2];
					if(decisio.equals(o.targeta.nom)){
						o.targeta.pos=bond.posa();
						m.inventari.remove("targeta");
					}
				}
				if(mapa[3] instanceof taller){	
					taller t= (taller) mapa[3];
					if(decisio.equals(t.eina.nom)){
						t.eina.pos=bond.posa();
						m.inventari.remove("eina");
					}
				}
				if(mapa[4] instanceof comandament){
					comandament c = (comandament) mapa[4];
					if(decisio.equals(c.identificador.nom)){
						c.identificador.pos=bond.posa();
						m.inventari.remove("identificador");
					}
				}
				if(mapa[5] instanceof vestuari){
					vestuari v = (vestuari) mapa[5];
					if(decisio.equals(v.vestimenta.nom)){
						if(v.vestimenta.utilitzar){
							v.vestimenta.pos=bond.posa();
							m.inventari.remove("vestimenta");
						}
						else{
							System.out.println("no puc deixar la vestimenta espacial si la tinc posada");
						}
					}
				}
				if(mapa[6] instanceof cuina){
					cuina c = (cuina) mapa[6];
					if(decisio.equals(c.donuts.nom)){
						c.donuts.pos=bond.posa();
						m.inventari.remove("donuts");
					}
				}
				if(mapa[7] instanceof menjador){
					menjador c= (menjador) mapa[7];
					if(decisio.equals(c.caixa.nom)){
						c.caixa.pos=bond.posa();
						m.inventari.remove("caixa");
					}
					else if(decisio.equals(c.flor.nom)){
						c.flor.pos=bond.posa();
						m.inventari.remove("flor");
					}
				}
				if(decisio.equals("llanterna")){
					llanterna.pos = bond.posa();
					m.inventari.remove("llanterna");
				}
			}
			else{
				System.out.println("No entenc aquest objecte");
				posm(alien,bond);
			}
		}			
		else{
			System.out.println("No tinc cap objecte per deixar");
			posm(alien,bond);
		}
	}
	/*
	 * Mètode al qual permet agafar objectes deixat per l'usuari o objectes als quals es poden agafar directament a l'entorn. 
	 * Si aquella sala conté objectes agafables pregunta a l'usuari si el vol recollir i si és així, el guarda a l'inventari.
	 * 
	 */
	public void agafar(zona[] mapa, personatge bond, motxilla m, utils llanterna, personatge alien){
		String decisio;
		boolean ot=false;
		if(mapa[0] instanceof dormitori && mapa[1] instanceof bany && mapa[2] instanceof oficina  && mapa[3] instanceof taller && mapa[4] instanceof comandament && mapa[5] instanceof vestuari && mapa[6] instanceof cuina && mapa[7] instanceof menjador && mapa[8] instanceof salasortida){	
			dormitori d= (dormitori) mapa[0];
			bany b= (bany) mapa[1];
			oficina o = (oficina) mapa[2];
			taller t = (taller) mapa[3];
			comandament cm = (comandament) mapa[4];
			vestuari v = (vestuari) mapa[5];
			cuina cu = (cuina) mapa[6];
			menjador men = (menjador) mapa[7];
			
			if(d.clau.pos == bond.posa() && d.clau.trobat){
				System.out.println("Aqui vaig deixar la clau del escriptori, hauria d'agafar-la?");
				decisio=e.next();
				if(decisio.equals("SI")||decisio.equals("si")){
					m.inventari.add(d.clau.nom);
					System.out.println("clau escriptori guardada en l'inventari!");
				}
				else{
					System.out.println("Millor en un altre moment");
				}
				ot=true;
			}
			if(b.contrasenya.pos == bond.posa() && b.contrasenya.trobat){
				System.out.println("Aqui vaig deixar el paper amb numeros, hauria d'agafar-ho?");
				decisio=e.next();
				if(decisio.equals("SI")||decisio.equals("si")){
					m.inventari.add(b.contrasenya.nom);
					System.out.println("paper amb numeros guardat en l'inventari!");
				}
				else{
					System.out.println("Millor en un altre moment");
				}
				ot=true;
			}
			if(o.targeta.pos == bond.posa() && o.targeta.trobat){
				System.out.println("Aqui vaig deixar la targeta de la nau, hauria d'agafar-la?");
				decisio=e.next();
				if(decisio.equals("SI")||decisio.equals("si")){
					m.inventari.add(o.targeta.nom);
					System.out.println("targeta guardada en l'inventari!");
				}
				else{
					System.out.println("Millor en un altre moment");
				}
				ot=true;
			}
			if(t.eina.pos == bond.posa() && t.eina.trobat){
				System.out.println("Aqui vaig deixar la eina per reparar la nau, hauria d'agafar-la?");
				decisio=e.next();
				if(decisio.equals("SI")||decisio.equals("si")){
					m.inventari.add(t.eina.nom);
					System.out.println("eina guardada en l'inventari!");
				}
				else{
					System.out.println("Millor en un altre moment");
				}
				ot=true;
			}
			if(cm.identificador.pos == bond.posa() && cm.identificador.trobat){
				System.out.println("Aqui vaig deixar l'identificador d'en Carl, hauria d'agafar-lo?");
				decisio=e.next();
				if(decisio.equals("SI")||decisio.equals("si")){
					m.inventari.add(cm.identificador.nom);
					System.out.println("identificador guardat en l'inventari!");
				}
				else{
					System.out.println("Millor en un altre moment");
				}
				ot=true;
			}
			if(cm.identificador.pos == bond.posa() && !cm.identificador.trobat){
				System.out.println("Vaig a mirar que puc agafar de l'estanteria");
				System.out.println("Aqui esta l'identificador d'en Carl, hauria d'agafar-lo?");
				decisio=e.next();
				if(decisio.equals("SI")||decisio.equals("si")){
					m.inventari.add(cm.identificador.nom);
					System.out.println("identificador guardat en l'inventari!");
					cm.identificador.trobat=true;
					cm.identificador.pos=0;
					if(llanterna.pos == 5 && !llanterna.trobat){
						System.out.println("Vajaaa, en el fons també esta la llanterna. Serà imprescindible utilitzar-la");
						m.inventari.add("llanterna");
						llanterna.pos=0;
						System.out.println("llanterna guardada en l'inventari");
						llanterna.trobat=true;
					}
				}
				else{
					System.out.println("Millor en un altre moment");
				}
				ot=true;
			}
			if(v.vestimenta.pos == bond.posa() && v.vestimenta.trobat){
				System.out.println("Aqui vaig deixar la vestimenta espacial, hauria d'agafar-la?");
				decisio=e.next();
				if(decisio.equals("SI")||decisio.equals("si")){
					m.inventari.add(v.vestimenta.nom);
					System.out.println("vestimenta guardada en l'inventari!");
				}
				else{
					System.out.println("Millor en un altre moment");
				}
				ot=true;
			}
			if(cu.donuts.pos == bond.posa() && cu.donuts.trobat){
				System.out.println("Aqui vaig deixar els donuts de l'ihall, hauria d'agafar-los?");
				decisio=e.next();
				if(decisio.equals("SI")||decisio.equals("si")){
					m.inventari.add(cu.donuts.nom);
					System.out.println("donuts guardats en l'inventari!");
				}
				else{
					System.out.println("Millor en un altre moment");
				}
				ot=true;
			}
			if(cu.donuts.pos == bond.posa() && !cu.donuts.trobat){
				System.out.println("Els donuts de l'ihall, tinc el presentiment de que seran molt importants, hauria d'agafar-los?");
				decisio=e.next();
				if(decisio.equals("SI")||decisio.equals("si")){
					m.inventari.add(cu.donuts.nom);
					System.out.println("donuts guardats en l'inventari!");
					cu.donuts.trobat=true;
					cu.donuts.pos=0;
					if(llanterna.pos == 7 && !llanterna.trobat){
						System.out.println("Vajaaa, en el fons del marbre també esta la llanterna. Serà imprescindible utilitzar-la");
						m.inventari.add("llanterna");
						llanterna.pos=0;
						System.out.println("llanterna guardada en l'inventari");
						llanterna.trobat=true;
					}
				}
				else{
					System.out.println("Millor en un altre moment");
				}
				ot=true;
			} 
			if(men.flor.pos == bond.posa() && men.flor.trobat){
				System.out.println("Aqui vaig deixar la flor d'en Carl, hauria d'agafar-la?");
				decisio=e.next();
				if(decisio.equals("SI")||decisio.equals("si")){
					m.inventari.add(men.flor.nom);
					System.out.println("flor guardada en l'inventari!");
				}
				else{
					System.out.println("Millor en un altre moment");
				}
				ot=true;
			}
			if((llanterna.pos == 4 && llanterna.pos == bond.posa()) && !llanterna.trobat){
				System.out.println("Vajaaa, en el fons de la taula també esta la llanterna. Serà imprescindible utilitzar-la");
						m.inventari.add("llanterna");
						llanterna.pos=0;
						System.out.println("llanterna guardada en l'inventari");
						llanterna.trobat=true;
				ot=true;
			}
			if((llanterna.pos == bond.posa() && llanterna.trobat)){
				System.out.println("Aqui vaig deixar la llanterna, hauria d'agafar-la?");
				decisio=e.next();
				if(decisio.equals("SI")||decisio.equals("si")){
					m.inventari.add(llanterna.nom);
					System.out.println("llanterna guardada en l'inventari!");
					llanterna.pos=0;
				}
				else{
					System.out.println("Millor en un altre moment");
				}
				ot=true;
			}
			if(!ot){
				System.out.println("No he deixat cap objecte aqui!");
				posm(alien,bond);
			}
			ot=false;
		}
	}
	/*
	 * Mètode per parlar amb l'iHall
	 * Utilitza la funció dialeg de la classe del iHall i conversa amb el jugador.
	 */
	public void parlar(utils llanterna, personatge ihall, personatge alien, personatge company){
		if(ihall instanceof ihall && alien instanceof alien){
			ihall i = (ihall) ihall;
			alien a =(alien) alien;
			a.cont--;
			i.dialeg(llanterna, alien, company);
		}

	}

	//ALTRES
	/*
	 * Mètode que permet relantitzar els guions corresponents.
	 * Rep per paràmetre els segons als quals creiem necessaris perque l'usuari pugui llegir-ho correctament i para el sistema.
	 */
	static public void sleep(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (Exception e) {
			System.out.println(e);
		}		
	}
	/*
	 * Mètode que permet posar o treure la vestimenta
	 * Comprova si l'usuari té la vestimenta en l'inventari i en cas de tenir-lo posa o treu la vestimenta.
	 */
	public void vestimenta(zona[] mapa,personatge alien,personatge bond){
		String decidir;
		if(mapa[5] instanceof vestuari){
			vestuari v=(vestuari) mapa[5];
			if(v.vestimenta.trobat && v.vestimenta.pos==0){
				if(v.vestimenta.utilitzar){
					System.out.println("Ja tinc la vestimenta espacial posada, l'hauria de treure?");
					decidir=e.next();
					if(decidir.equals("SI")||decidir.equals("si")){
						v.vestimenta.utilitzar=false;
						System.out.println("Vestimenta treta");
					}
					else{
						System.out.println("Millor ho deixo córrer");
						posm(alien,bond);
					}
				}
				else{
					v.vestimenta.utilitzar=true;
					System.out.println("Vestimenta posada");
				}
			}
			else{
				System.out.println("On estarà?");
				posm(alien,bond);
			}
		}
	}
	/*
	 * Mètode que permet utilitzar la flor
	 * Comprova si té la flor a l'inventari i si esta infectat.Si les respostes són positives, permet utilitzar l'objecte.
	 */
	public void flor(zona[] mapa,personatge alien, personatge bond){
		String decidir;
		if(mapa[7] instanceof menjador && bond instanceof bond){
			menjador m=(menjador) mapa[7];
			bond b=(bond) bond;
			if(m.flor.trobat && m.flor.pos==0 && !m.flor.utilitzar){
				if(b.infested){
					System.out.println("Hem sento bastant dèbil, és un risc alt. No se que pasarà... Em menjo la flor?");
					decidir=e.next();
					if(decidir.equals("SI")||decidir.equals("si")){
						m.flor.utilitzar=true;
						System.out.println("GLUP!");
						System.out.println("AGH! Està asquerosa, massa amarga...");
						System.out.println("-	Capità! Els meus sensors indican que la infecció s'ha detingut! Bon treball");
						b.infested=false;
					}
					else{
						System.out.println("Millor ho deixo córrer");
					}
				}
				else{
					System.out.println("No veig com utilitzarla");
				}
			}
			else{
				System.out.println("???");
				posm(alien,bond);
			}
		}
	}
	/*
 	* Mètode que et porta al link de la cançó 
	* Et porta a Youtube, on et mostra la cançó.
 	*/
	public void music(){
		Runtime rt= Runtime.getRuntime();
		String url = "https://www.youtube.com/watch?v=_IazZRcdMxs&ab_channel=robertogallegos";
		try{
			rt.exec("rundll32 url.dll,FileProtocolHandler "+url);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	/*
 	* Mètode que et porta al link de la cançó on l'usuari ha solicitat.
	* Et porta a Youtube, on et mostra la cançó corresponent.
 	*/
	public void music2(String uri){
		Runtime rt= Runtime.getRuntime();
		String url = uri;
		try{
			rt.exec("rundll32 url.dll,FileProtocolHandler "+url);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	/*
	 * Mètode que comprova el contador del alien
	 * En cas de que el contador es compleixi les condicions de dintre de la funció, es mourà l'alien.
	 */
	public void mova(personatge alien, zona[] mapa){
		if(alien instanceof alien){
			alien a =(alien) alien;
			a.cont++;
			if(a.cont==-1){
				System.out.println("PUUUUM!");
				System.out.println("-	Capità he detactat un intrús. Ha entrat pels conductes de ventilació. És un Cambralien... Actualment està en la cuina. Allà estàn els donuts. Haurà entrat per l'olor dels que he fet aquest matí. Per sort no són capaços de menjar-se'ls per ells mateixos. Ves amb compte però, són molt agresius i contagien facilment. Si aconseguim donar-li els donuts es quedarà tranquil, si necessites saber en tot moment on esta només preguta. Es moure pels conductes, aprofita això!");
				a.at=true;
				a.cont++;
			}
			if(a.cont==2){
				int posa=a.posa();
				a.pos=moviments(mapa, posa);
				System.out.println("CLACK CLACK CLACK");	
				a.cont=0;
			}
		}
	}
	/*
	 * Mètode que comprova el contador del company
	 * En cas de que el contador es compleixi les condicions de dintre de la funció, es mourà el company.
	 */
	public void movc(personatge company, zona[] mapa){
		if(company instanceof company){
			company comp=(company) company;
			if(comp.cont==-1){
				System.out.println("BIIIP!");
				System.out.println("-	Capità, en Carl s'ha despertat, espero que no li pasi res dolent");
				comp.cont++;
			}
			if(comp.cont==2){
				int posa=comp.posa();
				comp.pos=moviments(mapa, posa);
				System.out.println("TAP TAP TAP");	
				comp.cont=0;
			}
			if(comp.despert){
				comp.cont++;
			}
		}
	}
	/*
	 * Mètode que mou el personatge corresponent
	 * Segons la posició enviada prèviament on es troba el personatge corresponent, retorna una posició final de manera aleatoria
	 */
	public int moviments(zona[] mapa, int posa){
		boolean am=false;
		int posf=0;
		int random;
		while(!am){
			random=(int)(Math.random()*4+1);
			if(random==1){
				posf= mapa[posa-1].nord(posa);
			}
			else if(random==2){
				posf=mapa[posa-1].sud(posa);
			}
			else if(random==3){
				posf=mapa[posa-1].est(posa);
			}
			else if(random==4){
				posf=mapa[posa-1].oest(posa);
			}
			if(posf!=-1){
				am=true;
			}
		}
		return posf;
	}
	/*
	 * Mètode que realitza accions a l'inici de cada bucle.
	 * Comprova si les condicions són vertaderes, si ho són, realitza les accions corresponents.
	 */
	public void comprovara(personatge alien, personatge bond, zona[] mapa, motxilla m, personatge company){
		if(alien instanceof alien && bond instanceof bond && company instanceof company && mapa[6] instanceof cuina){
			alien a =(alien) alien;
			bond b =(bond) bond;
			company comp=(company) company;
			cuina cui=(cuina) mapa[6];

			if(a.posa()==bond.posa()){
				if(a.dolc){
					System.out.println("Mira que mono, qui em diria que tindria un 'gos'");
				}
				else {
					System.out.println("Merda, m'he hagut de trobar amb la bèstia");
					if(m.inventari.contains("donuts") && !a.dolc){
						System.out.println("-	Capità! Té guardat en l'inventari els donuts que vaig preparar, utilitzils!");
						System.out.println("Hauria d'utilitzar els donuts, ho faig?");
						String decisio=e.next();
						if(decisio.equals("SI")||decisio.equals("si")){
							System.out.println("Vina aqui cambralien bonic...");
							System.out.println("ÑAM ÑAM ÑAM");
							System.out.println("Bon nano! Sembla un gos hahaha. Ara quedat quiet mentres acabo la missió");
							a.dolc=true;
						}
						else if(m.inventari.contains("eina") && !a.dolc){
							System.out.println("-	Capità! utilitzi la eina per lluitar, són bastant dèbils però et contagiaran i se't ficaran per l'estòmac. Tindràs 7 minuts per acabar la missió o trobar una cura!");
							System.out.println("L'ihall m'aconsella utilitzar l'eina per combatre, hauria de fer-ho?");
							decisio=e.next();
							if(decisio.equals("SI")||decisio.equals("si")){
								System.out.println("PIM PAM PUUUUM");
								System.out.println("AHHHHHH");
								System.out.println("Bueno, com ha dit l'ihall... UFFF... He pogut acabar amb ell, però... m'ha entrat en el meu organisme... UFFFF... Haig de donar-me presa");
								b.infested=true;
								a.cont=3;
								a.pos=0;
							}
							else{
								System.out.println("AHHHHHHHHH");
								System.out.println("-	Noooo, capità! Hauré d'informar als superiors de la mort d'en bond i la missió fallida. Ha sigut un plaer");
								fjoc=true;
							}
						}
					}
					else if(m.inventari.contains("eina") && !a.dolc){
						System.out.println("-	Capità! utilitzi la eina per lluitar, són bastant dèbils però et contagiaran i se't ficaran per l'estòmac. Tindràs 7 minuts per acabar la missió o trobar una cura!");
						System.out.println("L'ihall m'aconsella utilitzar l'eina per combatre, hauria de fer-ho?");
						String decisio=e.next();
						if(decisio.equals("SI")||decisio.equals("si")){
							System.out.println("PIM PAM PUUUUM");
							System.out.println("AHHHHHH");
							System.out.println("Bueno, com ha dit l'ihall... UFFF... He pogut acabar amb ell, però... m'ha entrat en el meu organisme... UFFFF... Haig de donar-me presa");
							b.infested=true;
							a.cont=3;
							a.pos=0;
						}
						else{
							System.out.println("AHHHHHHHHH");
							System.out.println("-	Noooo, capità! Hauré d'informar als superiors de la mort d'en bond i la missió fallida. Ha sigut un plaer");
							fjoc=true;
						}
					}
					else{
						System.out.println("AHHHHHHHHH");
						System.out.println("-	Noooo, capità! Hauré d'informar als superiors de la mort d'en bond i la missió fallida. Ha sigut un plaer");
						fjoc=true;
					}
				}
			}
			if(b.infested){
				b.temps--;
				if(b.temps!=0){
					if(m.inventari.contains("flor")){
						System.out.println("-	Capità! En el seu inventari té una flor de cirere que podria curar aquesta infecció, ràpid menjala!");
					}
					System.out.println("-	Capità Bond, li queden "+b.temps+" minuts");
				}
				else{
					System.out.println("AHHHHHHHHH");
						System.out.println("-	Noooo, capità! Hauré d'informar als superiors de la mort d'en bond i la missió fallida. Ha sigut un plaer");
						fjoc=true;
				}
			}
			if(comp.posa()==cui.donuts.pos){
				System.out.println("-	Capità. En Carl s'ha menjat els donuts...");
				cui.donuts.pos=20;
			}
			if(comp.posa()==a.posa() && comp.despert && !a.dolc){
				System.out.println("arghhhhh");
				System.out.println("-	Noooo, Carl! Hauré d'informar als superiors de la mort d'en Carl i la missió fallida.");
				fjoc=true;
			}	
		}
	}
	/*
	 * Mètode amb la finalitat de mostrar els objectes de l'inventari
	 * Recorre una array mitjançant un bucle
	 */
	public void motxilla(motxilla m){
		if(m.inventari.size()>0){
			System.out.println("Actualment tinc:");
			for(int i = 0; i<m.inventari.size();i++){
				System.out.print(m.inventari.get(i));
				if(m.inventari.get(i).equals("paper")){
					System.out.print(" amb numeros '3572'");
				}
				if(i!=m.inventari.size()-1){
					System.out.print(",   ");
				}
			}
			System.out.println();
		}
		else{
			System.out.println("No tinc res en l'inventari!");
		} 
	}
	/*
	 * Mètode que resta el contador dels persontages
	 * En cas de que l'usuari introdueixi una comanda o acció no vàlida, restarà el contador dels personatges.
	 */
	public void posm(personatge alien, personatge bond){
		if(alien instanceof alien && bond instanceof bond){
			alien a=(alien) alien;
			bond b=(bond) bond;
			a.cont--;
			if(b.infested){
				b.temps++;
			}
		}
	}
}

