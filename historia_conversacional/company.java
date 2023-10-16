/**
* Herència de personatge.
* Desa els atributs del company.
* @author Elsa Mena, Abel Millán i Nil Casabella / Mòdul 13, Escola PIA Santa Anna, Mataró
* @version 0.1, 2022/11/06
*/

public class company extends personatge{

    int pos = 1;
    boolean despert=false;
    int cont=-2;

    @Override
    public int posa() {
        return this.pos;        
    }
    @Override
	public void setPos(int pos){
		this.pos = pos;
	}
}
