package dwa_watki;

public class Czytacz extends Thread {

    char nowaKsiazkaCzytacza='0';
    Biblioteka biblioteka;

    public Czytacz(Biblioteka biblioteka) {
        this.biblioteka=biblioteka;
    }

    @Override
    public void run() {
        while (true){

            synchronized (biblioteka){  //w nawiasie jest obiekt do ktorego jest synchronizoany zasob, TYLKO w srodku klamry ten obiekt jest monitorem

                while (biblioteka.czyPisarzPisze()){ //dopoki pisarz pisze czytacz musi czekac
                    try {
                        System.out.println("Czytacz:  biblioteka.wait();");
                        biblioteka.wait();

                        // wait() to metoda ktora powoduje ze watek czeka nieokreslony dlugi czas.
                        // Znajduje sie on w petli while zeby zakonczyl sie wtedy i tylko wtedy kiedy juz pisarz nie pisze
                        // Zawsze wywolujemy go na monitorze.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Czytacz:  biblioteka.ustawCzyCzytaczCzyta(true);");
                biblioteka.ustawCzyCzytaczCzyta(true); //Ustawiamy zmienna w bibliotece zeby czytacz mogl zobaczyc ze pisarz pisze

                System.out.println("Czytacz:  biblioteka.czytajKsiazke()-> "+biblioteka.czytajKsiazke());
                this.nowaKsiazkaCzytacza=biblioteka.czytajKsiazke();

                System.out.println("Czytacz:  biblioteka.ustawCzyCzytaczCzyta(false);");
                biblioteka.ustawCzyCzytaczCzyta(false); //Czytacz skonczyl pisac

                System.out.println("Czytacz:  biblioteka.notifyAll(); ");
                biblioteka.notifyAll(); //Po tej komedzie wszystkie watki, ktore byly w trakcie czekania na metodzie "wait()" przerwyaja jej działanie
            }
            //Opozniamy dzialnie zeby za szybko sie nie wypisywalo
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
