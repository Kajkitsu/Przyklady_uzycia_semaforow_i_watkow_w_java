package semafory_bez_kolejnosci;

import java.util.concurrent.Semaphore;

public class Main {

    //Zasady dzialania. W bibliotece mzoe byc tylko jeden pisarz lub czytacz i nie ma gwarancji ze kazdy czytacz odczyta dana ksiazke.
    //W celu zapewnienai oczytu i zapisu przez wszystkich uzytkownikow trzeba uzyc wiecej niz jednego semafora. PRzypadek bedzie dobrze dzialal dla 1 pisarza i czytacza.


    static int liczbaPisarzy = 2;
    static int liczbaCzytaczy = 5;


    public static void main(String[] args) {


        Biblioteka biblioteka = new Biblioteka();
        Semaphore semaphore = new Semaphore(1); //Stworzenie semaforu, dostep do niego ma tylko jeden watek (dlatego mamy 1 w konstruktorze).

        //zarowno czytacz jak i pisarz musze odwolywac sie do tego samego zasobu i semafora
        //towrzymy okreslona liczbe pisarzy
        Pisarz[] pisarze = new Pisarz[liczbaPisarzy];
        Czytacz[] czyatcze = new Czytacz[liczbaCzytaczy];

        for(int i=0 ;i<liczbaPisarzy; i++){
            pisarze[i]=new Pisarz(biblioteka,i,semaphore);
        }
        for (int i=0; i<liczbaCzytaczy; i++){
            czyatcze[i]=new Czytacz(biblioteka,i,semaphore);
        }


        for(int i=0 ;i<liczbaPisarzy; i++){
            pisarze[i].start();  //Ta metoda startuje -> watek wykonuje funkcje z metody run()
        }
        for (int i=0; i<liczbaCzytaczy; i++){
            czyatcze[i].start();  //Ta metoda startuje -> watek wykonuje funkcje z metody run()
        }


        for(int i=0 ;i<liczbaPisarzy; i++){
            try {
                pisarze[i].join(); //Dzialanie tej motody polega na czekaniu aż wątek zakonczy swoje dzialanie (w anszym rpzyapdku jednak tak sie nigdy nie zdarzy)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i=0; i<liczbaCzytaczy; i++){
            try {
                czyatcze[i].join(); //Dzialanie tej motody polega na czekaniu aż wątek zakonczy swoje dzialanie (w anszym rpzyapdku jednak tak sie nigdy nie zdarzy)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }




    }

}
