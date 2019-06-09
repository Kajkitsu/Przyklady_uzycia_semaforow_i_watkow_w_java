package dwa_watki;

public class Main {

    public static void main(String[] args) {
	    // write your code here

        Biblioteka biblioteka = new Biblioteka();

        //zarowno czytacz jak i pisarz musze odwolywac sie do tego samego zasobu
        Pisarz pisarz = new Pisarz(biblioteka);
        pisarz.start(); //Ta metoda startuje -> watek wykonuje funkcje z metody run()


        Czytacz czytacz = new Czytacz(biblioteka);
        czytacz.start(); //Ta metoda startuje -> watek wykonuje funkcje z metody run()

        try {
            pisarz.join(); //Dzialanie tej motody polega na czekaniu aż wątek zakonczy swoje dzialanie (w anszym rpzyapdku jednak tak sie nigdy nie zdarzy)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            czytacz.join(); //Dzialanie tej motody polega na czekaniu aż wątek zakonczy swoje dzialanie (w anszym rpzyapdku jednak tak sie nigdy nie zdarzy)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
