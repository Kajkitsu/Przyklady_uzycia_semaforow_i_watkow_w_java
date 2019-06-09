package semafory_bez_kolejnosci;

import java.util.concurrent.Semaphore;

public class Czytacz extends Thread {

    String nowaKsiazkaCzytacza = "0";
    Biblioteka biblioteka;
    int ID;
    Semaphore semaphore;

    public Czytacz(Biblioteka biblioteka,int ID, Semaphore semaphore) {
        this.biblioteka = biblioteka;
        this.ID = ID;
        this.semaphore = semaphore;
    }


    @Override
    public void run() {
        while (true) {

            try {
                semaphore.acquire(); //zajecie semafora. Od tego momentu zaczyna sie sekcja krytyczna.
                //Jezeli semafor zostal wczesniej podniesionty przez inny watek, czytacz czeka az semafor zostanie zwolniony.
                System.out.println("Czytacz "+ID+" : semaphore.acquire();");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Czytacz "+ID+" :  biblioteka.czytajKsiazke()-> " + biblioteka.czytajKsiazke());
            this.nowaKsiazkaCzytacza = biblioteka.czytajKsiazke();

            semaphore.release(); //zwolnienei semafora. W tym momencie konczy sie sekcja krytyczna.
            System.out.println("Czytacz "+ID+" : semaphore.release()();");


            //Opozniamy dzialnie zeby za szybko sie nie wypisywalo
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

}
