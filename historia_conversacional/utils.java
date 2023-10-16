/**
* Classe que guarda els diferents objectes del joc.
* Guarda i desa els atributs dels objectes instanciats corresponents.
* @author Elsa Mena, Abel Millán i Nil Casabella / Mòdul 13, Escola PIA Santa Anna, Mataró
* @version 0.1, 2022/11/06
*/

public class utils {

    String nom;
    Boolean utilitzar;
    int pos;
    boolean trobat;

    public utils(String nom, boolean utilitzar, int pos, boolean trobat) {
        this.nom=nom;
        this.utilitzar=utilitzar;
        this.pos = pos;
        this.trobat = trobat;
    }
    
}
