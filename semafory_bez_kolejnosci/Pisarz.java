package semafory_bez_kolejnosci;

import java.util.concurrent.Semaphore;

public class Pisarz extends Thread {

    char nowaKsiazkaPisarza = 'a';
    Biblioteka biblioteka;
    int ID;
    Semaphore semaphore;

    public Pisarz(Biblioteka biblioteka, int ID, Semaphore semaphore) {
        this.biblioteka = biblioteka;
        this.semaphore = semaphore;
        this.ID = ID;
    }

    @Override
    public void run() {
        while (true) {

            try {
                semaphore.acquire(); //zajecie semafora. Od tego momentu zaczyna sie sekcja krytyczna.
                //Jezeli semafor zostal wczesniej podniesionty przez inny watek, pisarz czeka az semafor zostanie zwolniony.
                System.out.println("Pisarz "+ID+": semaphore.acquire();");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Pisarz "+ID+":  biblioteka.piszKsiazke(nowaKsiazkaPisarza)-> "+nowaKsiazkaPisarza+String.valueOf(ID));
            biblioteka.piszKsiazke(nowaKsiazkaPisarza+String.valueOf(ID)); //Zapisujemy do biblioteki nowa ksiazke wraz z ID pisarza

            semaphore.release(); //zwolnienei semafora. W tym momencie konczy sie sekcja krytyczna.
            System.out.println("Pisarz "+ID+": semaphore.release()();");


            System.out.println("Pisarz "+ID+":  nowaKsiazkaPisarza++;");
            if (nowaKsiazkaPisarza == 'z') nowaKsiazkaPisarza = 'a';
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
