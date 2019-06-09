package trzy_watki_i_wiecej;

public class Main {

    //Obowiazujace zasady w bibliotece:
    //Jednoczesnie moze cyztac dowolna liczba czytaczy, jezeli w bibliotece nie ma pisarza.
    //Jednoczesnie mzoe pisac tylko jeden pisarz i jest on sam w bibliotece(bez psiarzy i czytaczy).


    //Najwieksza roznice w tym programie znajduje sie w implementacji biblioteki, ktora teraz pozwala obsluzyc wiecej niz jednego pisarza i czytacza.
    //Zmieniono warunek while w pisarzu zeby nie doszlo do pisania przez dwoch pisarzy w tym samym momencie.



    static int liczbaPisarzy = 3;
    static int liczbaCzytaczy = 10;


    public static void main(String[] args) {


        Biblioteka biblioteka = new Biblioteka();

        //zarowno czytacz jak i pisarz musze odwolywac sie do tego samego zasobu
        //towrzymy okreslona liczbe pisarzy
        Pisarz[] pisarze = new Pisarz[liczbaPisarzy];
        Czytacz[] czyatcze = new Czytacz[liczbaCzytaczy];

        for(int i=0 ;i<liczbaPisarzy; i++){
            pisarze[i]=new Pisarz(biblioteka,i);
        }
        for (int i=0; i<liczbaCzytaczy; i++){
            czyatcze[i]=new Czytacz(biblioteka,i);
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
