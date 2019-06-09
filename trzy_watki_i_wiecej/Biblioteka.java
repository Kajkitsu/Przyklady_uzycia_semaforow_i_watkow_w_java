package trzy_watki_i_wiecej;

public class Biblioteka {
    String ksiazka="a";
    int ilePisarzyPisze=0;
    int ileCzytaczyCzyta=0;

    public String czytajKsiazke() {
        return ksiazka;
    }

    public void piszKsiazke(String ksiazka) {
        this.ksiazka = ksiazka;
    }

    public boolean czyPisarzPisze() {
        if(ilePisarzyPisze>0){
            return true;
        }
        else {
            return false;
        }
    }

    public void ustawCzyPisarzPisze(boolean czyPisarzPisze) {
        if(czyPisarzPisze==true){
            ilePisarzyPisze++;
        }
        else{
            ilePisarzyPisze--;
        }
    }

    public boolean czyCzytaczCzyta() {
        if(ileCzytaczyCzyta>0){
            return true;
        }
        else {
            return false;
        }
    }

    public void ustawCzyCzytaczCzyta(boolean czyCzytaczCzyta) {
        if(czyCzytaczCzyta==true){
            ileCzytaczyCzyta++;
        }
        else{
            ileCzytaczyCzyta--;
        }
    }
}
