package semafory_bez_kolejnosci;

public class Biblioteka {
    String ksiazka="0";
    boolean czyPisarzPisze=false;
    boolean czyCzytaczCzyta=false;

    public String czytajKsiazke() {
        return ksiazka;
    }

    public void piszKsiazke(String ksiazka) {
        this.ksiazka = ksiazka;
    }

    public boolean czyPisarzPisze() {
        return czyPisarzPisze;
    }

    public void ustawCzyPisarzPisze(boolean czyPisarzPisze) {
        this.czyPisarzPisze = czyPisarzPisze;
    }

    public boolean czyCzytaczCzyta() {
        return czyCzytaczCzyta;
    }

    public void ustawCzyCzytaczCzyta(boolean czyCzytaczCzyta) {
        this.czyCzytaczCzyta = czyCzytaczCzyta;
    }

}
