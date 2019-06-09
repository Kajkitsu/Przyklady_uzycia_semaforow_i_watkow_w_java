package dwa_watki;

public class Biblioteka {
    char ksiazka='a';
    boolean czyPisarzPisze=false;
    boolean czyCzytaczCzyta=false;

    public char czytajKsiazke() {
        return ksiazka;
    }

    public void piszKsiazke(char ksiazka) {
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
