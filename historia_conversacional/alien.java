/**
* Herència de personatge.
* Guarda els atributs i les dades de l'alien.
* @author Elsa Mena, Abel Millan i Nil Casabella / Mòdul 13, Escola PIA Santa Anna, Mataró
* @version 0.1, 2022/11/06
*/

public class alien extends personatge {
    int pos=7;
    int cont =-2;
    boolean dolc=false;
    boolean at=false;

    @Override
    public int posa() {
        return this.pos;
    }
    @Override
	public void setPos(int pos){
		this.pos = pos;
	}
}
