/**
* Herència de personatge.
* Guarda atributs del personatge principal.
* @author Elsa Mena, Abel Millán i Nil Casabella / Mòdul 13, Escola PIA Santa Anna, Mataró
* @version 0.1, 2022/11/06
*/

public class bond extends personatge{
     int pos=1;
	 boolean infested=false;
	 int temps=8;
	@Override
	public int posa() {
		return this.pos;
	}

	@Override
	public void setPos(int pos){
		this.pos = pos;
	}
}
