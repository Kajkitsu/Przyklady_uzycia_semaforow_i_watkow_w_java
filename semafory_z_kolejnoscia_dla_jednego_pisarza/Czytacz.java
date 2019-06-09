package semafory_z_kolejnoscia_dla_jednego_pisarza;

import java.util.concurrent.Semaphore;

public class Czytacz extends Thread {

    String nowaKsiazkaCzytacza = "0";
    Biblioteka biblioteka;
    int ID;
    Semaphore semaphorePisarza;
    Semaphore semaphoreCzytaczy;

    public Czytacz(Biblioteka biblioteka, int ID, Semaphore semaphorePisarza,Semaphore semaphoreCzytaczy) {
        this.biblioteka = biblioteka;
        this.semaphorePisarza = semaphorePisarza;
        this.semaphoreCzytaczy = semaphoreCzytaczy;
        this.ID = ID;
    }


    @Override
    public void run() {
        while (true) {

            try {
                semaphoreCzytaczy.acquire(); //zajecie semafora. Od tego momentu zaczyna sie sekcja krytyczna.
                //Jezeli semafor zostal wczesniej podniesionty przez inny watek, czytacz czeka az semafor zostanie zwolniony.
                System.out.println("Czytacz "+ID+" : semaphore.acquire();");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Czytacz "+ID+" :  biblioteka.czytajKsiazke()-> " + biblioteka.czytajKsiazke());
            this.nowaKsiazkaCzytacza = biblioteka.czytajKsiazke();

            semaphorePisarza.release(); //zwolnienei semafora. W tym momencie konczy sie sekcja krytyczna.
            System.out.println("Czytacz "+ID+" : semaphore.release()();");


            //Opozniamy dzialnie zeby za szybko sie nie wypisywalo
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

}
