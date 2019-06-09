package trzy_watki_i_wiecej;

public class Pisarz extends Thread {

    char nowaKsiazkaPisarza='a';
    int ID;
    Biblioteka biblioteka;


    public Pisarz(Biblioteka biblioteka, int ID) {
        this.biblioteka=biblioteka;
        this.ID=ID;
    }

    @Override
    public void run() {
        while (true){

            synchronized (biblioteka){  //w nawiasie jest obiekt do ktorego jest synchronizoany zasob, TYLKO w srodku klamry ten obiekt jest monitorem

                //!!!!!!!!!!!!!!!!!! zmienił sie warunek while
                while (biblioteka.czyCzytaczCzyta() || biblioteka.czyPisarzPisze()){ //dopoki czytacz czyta pisarz musi czekac
                    try {
                        System.out.println("Pisarz"+ID+":  biblioteka.wait();");
                        biblioteka.wait();

                        // wait() to metoda ktora powoduje ze watek czeka nieokreslony dlugi czas.
                        // Znajduje sie on w petli while zeby zakonczyl sie wtedy i tylko wtedy kiedy juz czytacz nie czyta
                        // Zawsze wywolujemy go na monitorze.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Pisarz"+ID+":  biblioteka.ustawCzyPisarzPisze(true);");
                biblioteka.ustawCzyPisarzPisze(true); //Ustawiamy zmienna w bibliotece zeby czytacz mogl zobaczyc ze pisarz pisze

                System.out.println("Pisarz"+ID+":  biblioteka.piszKsiazke(nowaKsiazkaPisarza)-> "+nowaKsiazkaPisarza+String.valueOf(ID));
                biblioteka.piszKsiazke(nowaKsiazkaPisarza+String.valueOf(ID)); //Zapisujemy do biblioteki nowa ksiazke wraz z ID pisarza

                System.out.println("Pisarz"+ID+":  biblioteka.ustawCzyPisarzPisze(false);");
                biblioteka.ustawCzyPisarzPisze(false); //Pisarz skonczyl pisac

                System.out.println("Pisarz"+ID+":  biblioteka.notifyAll(); ");
                biblioteka.notifyAll(); //Po tej komedzie wszystkie watki, ktore byly w trakcie czekania na metodzie "wait()" przerwyaja jej działanie
            }
            //tu nie ma juz monitora i sekcji krytycznej, w tym momencie pisarz moze np napsiac nową książkę
            System.out.println("Pisarz"+ID+":  nowaKsiazkaPisarza++;" );
            if (nowaKsiazkaPisarza=='z') nowaKsiazkaPisarza='a';
            else nowaKsiazkaPisarza++;

            //Opozniamy dzialnie zeby za szybko sie nie wypisywalo
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
