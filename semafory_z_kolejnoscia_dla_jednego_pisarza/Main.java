package semafory_z_kolejnoscia_dla_jednego_pisarza;

import java.util.concurrent.Semaphore;

public class Main {

    //Zasady dzialania.
    //Czytacz i pisarz na przemian zapisuaj i odczytuja ksiazke.
    //Pisarz zwalnia semafor czytacza, a czytacz pisarza. Pierwszy z biblioteki zaczyna korzystać pisarz.



    public static void main(String[] args) {


        Biblioteka biblioteka = new Biblioteka();
        Semaphore semaphoreCzytaczy = new Semaphore(1); //Stworzenie semaforu, dostep do niego ma tylko jeden watek (dlatego mamy 1 w konstruktorze).
        Semaphore semaphorePisarza = new Semaphore(1); //Stworzenie semaforu, dostep do niego 10 czytaczy

        try {
            semaphoreCzytaczy.acquire(); //zaczynam od zablokowania semafora czytacza, powoduje to ze czytacz musi czekac az pisarz zwolni jego semafor.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //zarowno czytacz jak i pisarz musze odwolywac sie do tego samego zasobu i semafora
        //towrzymy okreslona liczbe pisarzy
        Pisarz pisarze = new Pisarz(biblioteka,0,semaphorePisarza,semaphoreCzytaczy);
        Czytacz czyatcze = new Czytacz(biblioteka,0,semaphorePisarza,semaphoreCzytaczy);


        pisarze.start();  //Ta metoda startuje -> watek wykonuje funkcje z metody run()
        czyatcze.start();  //Ta metoda startuje -> watek wykonuje funkcje z metody run()


        try {
            pisarze.join(); //Dzialanie tej motody polega na czekaniu aż wątek zakonczy swoje dzialanie (w anszym rpzyapdku jednak tak sie nigdy nie zdarzy)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            czyatcze.join(); //Dzialanie tej motody polega na czekaniu aż wątek zakonczy swoje dzialanie (w anszym rpzyapdku jednak tak sie nigdy nie zdarzy)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
