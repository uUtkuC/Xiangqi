import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game extends AbstractGame{
    private int SiraKimde = 0; // if, even it is kirmizi's turn. if odd it is black's turn.

    private boolean KirmizininSirasi(){
        if(SiraKimde%2==0) return true;
        else return false;
    }

    boolean isSahMatOlduVeOyunBittiMi = false; // if sah mat yazdırılır ise oyunu bitir.
    public Game(String player1,String player2){ // assign to instance variables so that it can be reached further on
        red = new Player(player1); // down Since we have no info regarding the points, or how the output should be i leave it blank
        black = new Player(player2); // up player
        super.board = new Board();

    }

    public Board getBoard(){

        return board;
    }// alttaki metotun isim yanlis oldu da iste ters dönüyo adıyla
    boolean kurtarirMi = false; // sah mata karar verir
    boolean gidecekYerVarMi = true;
    boolean hatali= false; // sah matı engellemek mümkünken, sahı tehlikedeki oyuncu yanlıs hareket yaparsa belirt.
    boolean KurtaranHareketVardevam = true;
    private boolean SahlarBakisiyorMu(String from, String to){ // secilen tas from dan to ya giderse Sah tehlike altında olur mu?
    String locationOfSah1 = "";
    String locationOfSah2 = "";
    boolean AradaBirTasVarMi = false;
    boolean AradaBirdenCokTasVarMi = false;
    boolean OynanaTasSahlarArasindakiMi = false;
        //System.out.println("BEN BUNU GORMEKTEIM");
       // board.print();
        //System.out.println("USTTE");
        int itemArrayIndex = -1;
        for (int n = 0; n < board.getItems().length; n++) {
            if (board.getItems()[n].getPosition().equals(from)) {
                itemArrayIndex = n;
               // System.out.println("n suan bu:"+ itemArrayIndex);
                break; // break is to make iteration faster.
            }
        }
        for (int n = 0; n < board.getItems().length; n++) {
            if (board.getItems()[n].getName().equals("ş")) { // we now know the index of enemy sah
                locationOfSah1 = board.getItems()[n].getPosition();
                break;
            }
        }
        for (int n = 0; n < board.getItems().length; n++) {
            if (board.getItems()[n].getName().equals("Ş")) { // we now know the index of enemy sah
                locationOfSah2 = board.getItems()[n].getPosition();
                break;
            }
        }if(locationOfSah1.equals(from)){
            locationOfSah1 = to;
        }
        else if(locationOfSah2.equals(from)){
            locationOfSah2 = to;
        }
        //System.out.println(locationOfSah1 +" bu 1 idi bu ise 2 "+locationOfSah2);
        if(locationOfSah1.substring(1).equals(locationOfSah2.substring(1)) == false) return  true; // ikisi arasında birden cok tas varsa true dön. tek bir tas varsa ve bu tas yatayda hareket ediyorsa false dön.
        for(int n=0;n<board.getItems().length ;n++){
            if(board.getItems()[n].getPosition().substring(1).equals(locationOfSah1.substring(1))
                    && board.getItems()[n].getPosition().charAt(0)<locationOfSah2.charAt(0)
                    &&board.getItems()[n].getPosition().charAt(0)>locationOfSah1.charAt(0) ){
               // System.out.println("BU "+board.getItems()[n].getPosition());
                if(AradaBirTasVarMi == true){AradaBirdenCokTasVarMi = true;}
                AradaBirTasVarMi = true;
                if(board.getItems()[n].getPosition().equals(from) && board.getItems()[n] instanceof Sah == false){OynanaTasSahlarArasindakiMi = true;}
               // System.out.println("Suan burda. tas bu "+ board.getItems()[n].getName()+" "+ board.getItems()[n].getPosition());
            }
        }
        if(AradaBirdenCokTasVarMi == true){ //System.out.println("yes");
            return true;}
        if(OynanaTasSahlarArasindakiMi == false && AradaBirTasVarMi == true){return true;}
        else if(AradaBirTasVarMi){ if(to.substring(1).equals(locationOfSah1.substring(1))) return true; } // if there is a single peice and it is moving vertically it is okay.

        return false;
    }
    // if true, tas cannot be played hatali ahreket yazır.
    private boolean isTasPinned(String from, String to, Board board, Item[] items ) { // secili tas oynatilir ise sah olur mu. // at, fil, kale, top, piyon yerlerine bakmalı.
        // oynatılan tasın dik yatayında kale var mı ve sahı tehdit eder mi?
        // bir atın iki dikey bir yan veya iki yan bir dikeyinde at var mı ve tas atın alakli dikey yahut yatayında mı oynatılacak tasimiz. ve tasimiz atı yiyor mu bak.
        // yeni bir board objesi ve item array yarat.
        // strateji move valid ise, tahtatadi dusman olan tüm tasları sahın konumuna oynatmaya calis ve geri al. eger sahin konumuma gidebilen var ise tas pinneddir.
        Board kontrolBoard = new Board();
        kontrolBoard.clearboard();

        // kontrolBoard.items = board.items; // I need to do this in deep copy
        for(int n=0;n<items.length ;n++){
            if(board.getItems()[n] instanceof At) {
                kontrolBoard.getItems()[n] = (At)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Top) {
                kontrolBoard.getItems()[n] = (Top)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Vezir) {
                kontrolBoard.getItems()[n] = (Vezir)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Sah) {
                kontrolBoard.getItems()[n] = (Sah)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Fil) {
                kontrolBoard.getItems()[n] = (Fil)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Kale) {
                kontrolBoard.getItems()[n] = (Kale)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Piyon) {
                kontrolBoard.getItems()[n] = (Piyon)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }


        }
        kontrolBoard.placeItems();
        int itemArrayIndexi = -1;
        for (int n = 0; n < kontrolBoard.getItems().length; n++) {
            if (kontrolBoard.getItems()[n].getPosition().equals(from)) {
                itemArrayIndexi = n;
                //   System.out.println("n suan bu:"+ itemArrayIndex);
                break; // break is to make iteration faster.
            }
        }
       // System.out.println("Kontrol board basılıyor");
       // kontrolBoard.print();
       // System.out.println("Kontrol board basıldı");
        // boardı bu objede set etmedim. edeceğim şimdi
        // piece belong to the enemy
        if(board.getItems()[itemArrayIndexi] instanceof At){ ((At) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);  ((At) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
        if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Top) { ((Top) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);   ((Top) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
        if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Vezir) {((Vezir) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);    ((Vezir) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
        if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Sah) { ((Sah) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);   ((Sah) kontrolBoard.getItems()[itemArrayIndexi]).move(to); }
        if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Fil) { ((Fil) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);   ((Fil) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
        if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Kale) {  ((Kale) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);     ((Kale) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
        if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Piyon) {((Piyon) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);((Piyon) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}


        // getting sahs locations
        String locationOfSah1 = "";
        String locationOfSah2 = "";
        for (int n = 0; n < kontrolBoard.getItems().length; n++) {
            if (kontrolBoard.getItems()[n].getName().equals("ş")) { // we now know the index of enemy sah
                locationOfSah1 = kontrolBoard.getItems()[n].getPosition();
                break;
            }
        }
        for (int n = 0; n < kontrolBoard.getItems().length; n++) {
            if (kontrolBoard.getItems()[n].getName().equals("Ş")) { // we now know the index of enemy sah
                locationOfSah2 = kontrolBoard.getItems()[n].getPosition();
                break;
            }
        } // her bir tas icin setboard yapacaksın
        if (kontrolBoard.getItems()[itemArrayIndexi].getName().equals(kontrolBoard.getItems()[itemArrayIndexi].getName().toUpperCase())) { // Buyuk  OYuncu ise
            for (int n=0; n< items.length; n++){
                 kontrolBoard = new Board();
                kontrolBoard.clearboard();

                // kontrolBoard.items = board.items; // I need to do this in deep copy
                for(int k=0;k<items.length ;k++){
                    if(board.getItems()[k] instanceof At) {
                        kontrolBoard.getItems()[k] = (At)board.getItems()[k].cloneMe(); // creating new items with the same type.
                    }
                    if(board.getItems()[k] instanceof Top) {
                        kontrolBoard.getItems()[k] = (Top)board.getItems()[k].cloneMe(); // creating new items with the same type.
                    }
                    if(board.getItems()[k] instanceof Vezir) {
                        kontrolBoard.getItems()[k] = (Vezir)board.getItems()[k].cloneMe(); // creating new items with the same type.
                    }
                    if(board.getItems()[k] instanceof Sah) {
                        kontrolBoard.getItems()[k] = (Sah)board.getItems()[k].cloneMe(); // creating new items with the same type.
                    }
                    if(board.getItems()[k] instanceof Fil) {
                        kontrolBoard.getItems()[k] = (Fil)board.getItems()[k].cloneMe(); // creating new items with the same type.
                    }
                    if(board.getItems()[k] instanceof Kale) {
                        kontrolBoard.getItems()[k] = (Kale)board.getItems()[k].cloneMe(); // creating new items with the same type.
                    }
                    if(board.getItems()[k] instanceof Piyon) {
                        kontrolBoard.getItems()[k] = (Piyon)board.getItems()[k].cloneMe(); // creating new items with the same type.
                    }


                }
                kontrolBoard.placeItems();
                 itemArrayIndexi = -1;
                for (int m = 0; m < kontrolBoard.getItems().length; m++) {
                    if (kontrolBoard.getItems()[m].getPosition().equals(from)) {
                        itemArrayIndexi = m;
                        //   System.out.println("n suan bu:"+ itemArrayIndex);
                        break; // break is to make iteration faster.
                    }
                }
               // System.out.println("Kontrol board basılıyor");
               // kontrolBoard.print();
               //System.out.println("Kontrol board basıldı");
                // boardı bu objede set etmedim. edeceğim şimdi
                // piece belong to the enemy
                if(board.getItems()[itemArrayIndexi] instanceof At){ ((At) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);  ((At) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Top) { ((Top) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);   ((Top) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Vezir) {((Vezir) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);    ((Vezir) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Sah) { ((Sah) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);   ((Sah) kontrolBoard.getItems()[itemArrayIndexi]).move(to); }
                if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Fil) { ((Fil) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);   ((Fil) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Kale) {  ((Kale) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);     ((Kale) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Piyon) {((Piyon) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);((Piyon) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                if(board.getItems()[n] instanceof At){ ((At) kontrolBoard.getItems()[n]).setboard(kontrolBoard);  }
                if(kontrolBoard.getItems()[n] instanceof Top) { ((Top) kontrolBoard.getItems()[n]).setboard(kontrolBoard);   }
                if(kontrolBoard.getItems()[n] instanceof Vezir) {((Vezir) kontrolBoard.getItems()[n]).setboard(kontrolBoard);    }
                if(kontrolBoard.getItems()[n] instanceof Sah) { ((Sah) kontrolBoard.getItems()[n]).setboard(kontrolBoard);    }
                if(kontrolBoard.getItems()[n] instanceof Fil) { ((Fil) kontrolBoard.getItems()[n]).setboard(kontrolBoard);   }
                if(kontrolBoard.getItems()[n] instanceof Kale) {  ((Kale) kontrolBoard.getItems()[n]).setboard(kontrolBoard);     }
                if(kontrolBoard.getItems()[n] instanceof Piyon) {((Piyon) kontrolBoard.getItems()[n]).setboard(kontrolBoard);}
                if(kontrolBoard.getItems()[n].getName().toLowerCase().equals(kontrolBoard.getItems()[n].getName())){ // piece belong to the enemy

                    if(kontrolBoard.getItems()[n] instanceof At){
                        kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                        if(((At)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((At)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;
                        }
                    }
                    if(kontrolBoard.getItems()[n] instanceof Top) {
                        kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                        if(((Top)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((Top)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;
                        }
                    }
                    if(kontrolBoard.getItems()[n] instanceof Vezir) {
                        kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                        if(((Vezir)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((Vezir)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;
                        }
                    }
                    if(kontrolBoard.getItems()[n] instanceof Sah) {
                        kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                        if(((Sah)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((Sah)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;
                        }                    }
                    if(kontrolBoard.getItems()[n] instanceof Fil) {
                        kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                        if(((Fil)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((Fil)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;
                        }                    }
                    if(kontrolBoard.getItems()[n] instanceof Kale) {
                        kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                        if(((Kale)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((Kale)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;
                        }                    }
                    if(kontrolBoard.getItems()[n] instanceof Piyon) {
                        kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                        if(((Piyon)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((Piyon)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;
                        }                    }
                }
            }
        }
        else if(kontrolBoard.getItems()[itemArrayIndexi].getName().equals(kontrolBoard.getItems()[itemArrayIndexi].getName().toLowerCase())){ // kucuk oyuncu ise
            for (int n=0; n< items.length; n++){

                kontrolBoard = new Board();
                kontrolBoard.clearboard();

                // kontrolBoard.items = board.items; // I need to do this in deep copy
                for(int k=0;k<items.length ;k++){
                    if(board.getItems()[k] instanceof At) {
                        kontrolBoard.getItems()[k] = (At)board.getItems()[k].cloneMe(); // creating new items with the same type.
                    }
                    if(board.getItems()[k] instanceof Top) {
                        kontrolBoard.getItems()[k] = (Top)board.getItems()[k].cloneMe(); // creating new items with the same type.
                    }
                    if(board.getItems()[k] instanceof Vezir) {
                        kontrolBoard.getItems()[k] = (Vezir)board.getItems()[k].cloneMe(); // creating new items with the same type.
                    }
                    if(board.getItems()[k] instanceof Sah) {
                        kontrolBoard.getItems()[k] = (Sah)board.getItems()[k].cloneMe(); // creating new items with the same type.
                    }
                    if(board.getItems()[k] instanceof Fil) {
                        kontrolBoard.getItems()[k] = (Fil)board.getItems()[k].cloneMe(); // creating new items with the same type.
                    }
                    if(board.getItems()[k] instanceof Kale) {
                        kontrolBoard.getItems()[k] = (Kale)board.getItems()[k].cloneMe(); // creating new items with the same type.
                    }
                    if(board.getItems()[k] instanceof Piyon) {
                        kontrolBoard.getItems()[k] = (Piyon)board.getItems()[k].cloneMe(); // creating new items with the same type.
                    }


                }
                kontrolBoard.placeItems();
                itemArrayIndexi = -1;
                for (int m = 0; m < kontrolBoard.getItems().length; m++) {
                    if (kontrolBoard.getItems()[m].getPosition().equals(from)) {
                        itemArrayIndexi = m;
                        //   System.out.println("n suan bu:"+ itemArrayIndex);
                        break; // break is to make iteration faster.
                    }
                }
               // System.out.println("Kontrol board basılıyor");
                //kontrolBoard.print();
                //System.out.println("Kontrol board basıldı");
                // boardı bu objede set etmedim. edeceğim şimdi
                // piece belong to the enemy
                if(board.getItems()[itemArrayIndexi] instanceof At){ ((At) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);  ((At) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                else if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Top) { ((Top) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);   ((Top) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                else   if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Vezir) {((Vezir) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);    ((Vezir) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                else  if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Sah) { ((Sah) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);   ((Sah) kontrolBoard.getItems()[itemArrayIndexi]).move(to); }
                else  if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Fil) { ((Fil) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);   ((Fil) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                else if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Kale) {  ((Kale) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);     ((Kale) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                else  if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Piyon) {((Piyon) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);((Piyon) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                if(board.getItems()[n] instanceof At){ ((At) kontrolBoard.getItems()[n]).setboard(kontrolBoard);  }
                else if(kontrolBoard.getItems()[n] instanceof Top) { ((Top) kontrolBoard.getItems()[n]).setboard(kontrolBoard);   }
                else if(kontrolBoard.getItems()[n] instanceof Vezir) {((Vezir) kontrolBoard.getItems()[n]).setboard(kontrolBoard);    }
                else  if(kontrolBoard.getItems()[n] instanceof Sah) { ((Sah) kontrolBoard.getItems()[n]).setboard(kontrolBoard);    }
                else  if(kontrolBoard.getItems()[n] instanceof Fil) { ((Fil) kontrolBoard.getItems()[n]).setboard(kontrolBoard);   }
                else   if(kontrolBoard.getItems()[n] instanceof Kale) {  ((Kale) kontrolBoard.getItems()[n]).setboard(kontrolBoard);     }
                else if(kontrolBoard.getItems()[n] instanceof Piyon) {((Piyon) kontrolBoard.getItems()[n]).setboard(kontrolBoard);}
               /* if(board.getItems()[n] instanceof At){ ((At) kontrolBoard.getItems()[n]).setboard(kontrolBoard);  }
                if(kontrolBoard.getItems()[n] instanceof Top) { ((Top) kontrolBoard.getItems()[n]).setboard(kontrolBoard);   }
                if(kontrolBoard.getItems()[n] instanceof Vezir) {((Vezir) kontrolBoard.getItems()[n]).setboard(kontrolBoard);    }
                if(kontrolBoard.getItems()[n] instanceof Sah) { ((Sah) kontrolBoard.getItems()[n]).setboard(kontrolBoard);    }
                if(kontrolBoard.getItems()[n] instanceof Fil) { ((Fil) kontrolBoard.getItems()[n]).setboard(kontrolBoard);   }
                if(kontrolBoard.getItems()[n] instanceof Kale) {  ((Kale) kontrolBoard.getItems()[n]).setboard(kontrolBoard);     }
                if(kontrolBoard.getItems()[n] instanceof Piyon) {((Piyon) kontrolBoard.getItems()[n]).setboard(kontrolBoard);}*/
                if(kontrolBoard.getItems()[n].getName().toUpperCase().equals(kontrolBoard.getItems()[n].getName())){ // piece belong to the enemy
                    if(board.getItems()[n] instanceof At){
                        kontrolBoard.getItems()[n].move(locationOfSah1) ; // oynatabiliyorsak
                        if(((At)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((At)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;
                        }
                    }
                    if(kontrolBoard.getItems()[n] instanceof Top) {
                        kontrolBoard.getItems()[n].move(locationOfSah1) ; // oynatabiliyorsak
                        if(((Top)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((Top)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;
                        }
                    }
                    if(kontrolBoard.getItems()[n] instanceof Vezir) {
                        kontrolBoard.getItems()[n].move(locationOfSah1) ; // oynatabiliyorsak
                        if(((Vezir)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((Vezir)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;
                        }
                    }
                    if(kontrolBoard.getItems()[n] instanceof Sah) {
                        kontrolBoard.getItems()[n].move(locationOfSah1) ; // oynatabiliyorsak
                        if(((Sah)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((Sah)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;
                        }                    }
                    if(kontrolBoard.getItems()[n] instanceof Fil) {
                        kontrolBoard.getItems()[n].move(locationOfSah1) ; // oynatabiliyorsak
                        if(((Fil)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((Fil)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;
                        }                    }
                    if(kontrolBoard.getItems()[n] instanceof Kale) {
                        kontrolBoard.getItems()[n].move(locationOfSah1) ; // oynatabiliyorsak
                        if(((Kale)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((Kale)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;
                        }                    }
                    if(kontrolBoard.getItems()[n] instanceof Piyon) {
                        kontrolBoard.getItems()[n].move(locationOfSah1) ; // oynatabiliyorsak
                        if(((Piyon)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((Piyon)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;
                        }                    }
                }
            }
        }
        //kontrolBoard.print(); debug icin dene
        return false;

    }
    private boolean UpperPlayereSahCekiliyorMu(Board givenBoard, String locationOfSah2){ // burada kontrol board degisiyor. onu engellemek lazım !!!

         for (int n=0; n< givenBoard.getItems().length; n++){
            Board   kontrolBoard = new Board();
            kontrolBoard.clearboard();

            // kontrolBoard.items = board.items; // I need to do this in deep copy
            for(int k=0;k<kontrolBoard.getItems().length ;k++){
                if(givenBoard.getItems()[k] instanceof At) {
                    kontrolBoard.getItems()[k] = (At)givenBoard.getItems()[k].cloneMe(); // creating new items with the same type.
                }
                else  if(givenBoard.getItems()[k] instanceof Top) {
                    kontrolBoard.getItems()[k] = (Top)givenBoard.getItems()[k].cloneMe(); // creating new items with the same type.
                }
                else   if(givenBoard.getItems()[k] instanceof Vezir) {
                    kontrolBoard.getItems()[k] = (Vezir)givenBoard.getItems()[k].cloneMe(); // creating new items with the same type.
                }
                else   if(givenBoard.getItems()[k] instanceof Sah) {
                    kontrolBoard.getItems()[k] = (Sah)givenBoard.getItems()[k].cloneMe(); // creating new items with the same type.
                }
                else   if(givenBoard.getItems()[k] instanceof Fil) {
                    kontrolBoard.getItems()[k] = (Fil)givenBoard.getItems()[k].cloneMe(); // creating new items with the same type.
                }
                else    if(givenBoard.getItems()[k] instanceof Kale) {
                    kontrolBoard.getItems()[k] = (Kale)givenBoard.getItems()[k].cloneMe(); // creating new items with the same type.
                }
                else   if(givenBoard.getItems()[k] instanceof Piyon) {
                    kontrolBoard.getItems()[k] = (Piyon)givenBoard.getItems()[k].cloneMe(); // creating new items with the same type.
                }
            }
            kontrolBoard.placeItems();
            if(kontrolBoard.getItems()[n] instanceof At){ ((At) kontrolBoard.getItems()[n]).setboard(kontrolBoard);  }
            else    if(kontrolBoard.getItems()[n] instanceof Top) { ((Top) kontrolBoard.getItems()[n]).setboard(kontrolBoard);   }
            else   if(kontrolBoard.getItems()[n] instanceof Vezir) {((Vezir) kontrolBoard.getItems()[n]).setboard(kontrolBoard);    }
            else   if(kontrolBoard.getItems()[n] instanceof Sah) { ((Sah) kontrolBoard.getItems()[n]).setboard(kontrolBoard);    }
            else   if(kontrolBoard.getItems()[n] instanceof Fil) { ((Fil) kontrolBoard.getItems()[n]).setboard(kontrolBoard);   }
            else   if(kontrolBoard.getItems()[n] instanceof Kale) {  ((Kale) kontrolBoard.getItems()[n]).setboard(kontrolBoard);     }
            else   if(kontrolBoard.getItems()[n] instanceof Piyon) {((Piyon) kontrolBoard.getItems()[n]).setboard(kontrolBoard);}
            if(kontrolBoard.getItems()[n].getName().toLowerCase().equals(kontrolBoard.getItems()[n].getName())){ // piece belong to the enemy

                if(kontrolBoard.getItems()[n] instanceof At){
                    kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                    if(((At)kontrolBoard.getItems()[n]).isMoveValid()){
                        ((At)kontrolBoard.getItems()[n]).resetIsMoveValid();KurtaranHareketVardevam = false;
                        return true;
                    }
                }
                else   if(kontrolBoard.getItems()[n] instanceof Top) {
                    kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                    if(((Top)kontrolBoard.getItems()[n]).isMoveValid()){
                        ((Top)kontrolBoard.getItems()[n]).resetIsMoveValid();KurtaranHareketVardevam = false;
                        return true;
                    }
                }
                else     if(kontrolBoard.getItems()[n] instanceof Vezir) {
                    kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                    if(((Vezir)kontrolBoard.getItems()[n]).isMoveValid()){
                        ((Vezir)kontrolBoard.getItems()[n]).resetIsMoveValid();KurtaranHareketVardevam = false;
                        return true;
                    }
                }
                else   if(kontrolBoard.getItems()[n] instanceof Sah) {
                    kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                    if(((Sah)kontrolBoard.getItems()[n]).isMoveValid()){
                        ((Sah)kontrolBoard.getItems()[n]).resetIsMoveValid();KurtaranHareketVardevam = false;
                        return true;
                    }                    }
                else    if(kontrolBoard.getItems()[n] instanceof Fil) {
                    kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                    if(((Fil)kontrolBoard.getItems()[n]).isMoveValid()){
                        ((Fil)kontrolBoard.getItems()[n]).resetIsMoveValid();KurtaranHareketVardevam = false;
                        return true;
                    }                    }
                else    if(kontrolBoard.getItems()[n] instanceof Kale) {
                    kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                    if(((Kale)kontrolBoard.getItems()[n]).isMoveValid()){
                        ((Kale)kontrolBoard.getItems()[n]).resetIsMoveValid();KurtaranHareketVardevam = false;
                        return true;
                    }                    }
                else    if(kontrolBoard.getItems()[n] instanceof Piyon) {
                    kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                    if(((Piyon)kontrolBoard.getItems()[n]).isMoveValid()){
                        ((Piyon)kontrolBoard.getItems()[n]).resetIsMoveValid();KurtaranHareketVardevam = false;
                        return true;
                    }                    }
            }
        }
        return false;
    }
    private  boolean isGidecekYerVarMi(Board board,String locationOfSah1, String locationOfSah2, boolean ilkiIseTrue){
        Board kontrolBoard = new Board();
        kontrolBoard.clearboard();
        for(int n=0;n<board.getItems().length ;n++){
            if(board.getItems()[n] instanceof At) {
                kontrolBoard.getItems()[n] = (At)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Top) {
                kontrolBoard.getItems()[n] = (Top)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Vezir) {
                kontrolBoard.getItems()[n] = (Vezir)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Sah) {
                kontrolBoard.getItems()[n] = (Sah)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Fil) {
                kontrolBoard.getItems()[n] = (Fil)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Kale) {
                kontrolBoard.getItems()[n] = (Kale)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Piyon) {
                kontrolBoard.getItems()[n] = (Piyon)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            kontrolBoard.placeItems();

        }
        if(ilkiIseTrue == false){
            boolean halaSahCekiliyorMu = true;
            for(int n=0;n<kontrolBoard.getItems().length ;n++){
                if(kontrolBoard.getItems()[n].getName().equals(kontrolBoard.getItems()[n].getName().toUpperCase()))
            { // dost bir tas. bu tasa valid olan her hareketi yaptır. Sonra sah mi kontrol et. eger sah olmuyor ise boolean kurtarirMi yi true set et break et.
                String positionString = "";
                for (int x = 1; x < 10; x++) {
                    for (int y = 0; y < 19; y += 2) {
                        switch (y) {
                            case 18:
                                positionString = "" + 'a';
                                break;
                            case 16:
                                positionString = "" + 'b';
                                break;
                            case 14:
                                positionString = "" + 'c';
                                break;
                            case 12:
                                positionString = "" + 'd';
                                break;
                            case 10:
                                positionString = "" + 'e';
                                break;
                            case 8:
                                positionString = "" + 'f';
                                break;
                            case 6:
                                positionString = "" + 'g';
                                break;
                            case 4:
                                positionString = "" + 'h';
                                break;
                            case 2:
                                positionString = "" + 'i';
                                break;
                            case 0:
                                positionString = "" + 'j';
                                break;
                        }
                        positionString += "" + x; // positionu belirledik. simdi hareket valid ise tasi oraya goturup, konterol edip sonrasında geri getir devam et. boolean true olursa break et.
                        if (kontrolBoard.getItems()[n] instanceof At) {
                            ((At) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        } else if (kontrolBoard.getItems()[n] instanceof Top) {
                            ((Top) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        } else if (kontrolBoard.getItems()[n] instanceof Vezir) {
                            ((Vezir) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        } else if (kontrolBoard.getItems()[n] instanceof Sah) {
                            ((Sah) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        } else if (kontrolBoard.getItems()[n] instanceof Fil) {
                            ((Fil) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        } else if (kontrolBoard.getItems()[n] instanceof Kale) {
                            ((Kale) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        } else if (kontrolBoard.getItems()[n] instanceof Piyon) {
                            ((Piyon) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        }
                        String geldigiYer = kontrolBoard.getItems()[n].getPosition();
                        if (kontrolBoard.getItems()[n] instanceof At) {
                            kontrolBoard.getItems()[n].move(locationOfSah2); // oynatabiliyorsak
                            if (((At) kontrolBoard.getItems()[n]).isMoveValid()) {
                                halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2);
                                ((At) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems(); halaSahCekiliyorMu= false;
                            }
                        }
                        if (kontrolBoard.getItems()[n] instanceof Top) {
                            kontrolBoard.getItems()[n].move(locationOfSah2); // oynatabiliyorsak
                            if (((Top) kontrolBoard.getItems()[n]).isMoveValid() ) {
                                halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2);
                                ((Top) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems(); halaSahCekiliyorMu= false;
                            }
                        }
                        if (kontrolBoard.getItems()[n] instanceof Vezir) {
                            kontrolBoard.getItems()[n].move(locationOfSah2); // oynatabiliyorsak
                            if (((Vezir) kontrolBoard.getItems()[n]).isMoveValid()) {
                                halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2);
                                ((Vezir) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems(); halaSahCekiliyorMu= false;
                            }
                        }
                        if (kontrolBoard.getItems()[n] instanceof Sah) {
                            kontrolBoard.getItems()[n].move(locationOfSah2); // oynatabiliyorsak
                            if (((Sah) kontrolBoard.getItems()[n]).isMoveValid()) {
                                halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2);
                                ((Sah) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems();halaSahCekiliyorMu= false;
                            }
                        }
                        if (kontrolBoard.getItems()[n] instanceof Fil) {
                            kontrolBoard.getItems()[n].move(locationOfSah2); // oynatabiliyorsak
                            if (((Fil) kontrolBoard.getItems()[n]).isMoveValid()) {
                                halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2);
                                ((Fil) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems(); halaSahCekiliyorMu= false;
                            }
                        }
                        if (kontrolBoard.getItems()[n] instanceof Kale) {
                            kontrolBoard.getItems()[n].move(locationOfSah2); // oynatabiliyorsak
                            if (((Kale) kontrolBoard.getItems()[n]).isMoveValid()) {
                                halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2);
                                ((Kale) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems();
                                halaSahCekiliyorMu= false;
                            }
                        }
                        if (kontrolBoard.getItems()[n] instanceof Piyon) {
                            kontrolBoard.getItems()[n].move(locationOfSah2); // oynatabiliyorsak
                            if (((Piyon) kontrolBoard.getItems()[n]).isMoveValid()) {
                                halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2);
                                ((Piyon) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems();
                                halaSahCekiliyorMu= false;
                            }
                        }
                        if (halaSahCekiliyorMu == false) {
                            return true;
                           // kontrolBoard.print();
                            // System.out.println("Ustteki kurtarayor be");

                        }
                    }
                }
            }
            }
        }
        else{
            boolean halaSahCekiliyorMu = false;
            for(int n=0;n<kontrolBoard.getItems().length ;n++){
                if(kontrolBoard.getItems()[n].getName().equals(kontrolBoard.getItems()[n].getName().toLowerCase()) && kontrolBoard.getItems()[n].getName().equals("xx")==false) { // dost bir tas. bu tasa valid olan her hareketi yaptır. Sonra sah mi kontrol et. eger sah olmuyor ise boolean kurtarirMi yi true set et break et.
                    String positionString = "";
                    for (int x = 1; x < 10; x++) {
                        for (int y = 0; y < 19; y += 2) {
                            switch (y) {
                                case 18:
                                    positionString = "" + 'a';
                                    break;
                                case 16:
                                    positionString = "" + 'b';
                                    break;
                                case 14:
                                    positionString = "" + 'c';
                                    break;
                                case 12:
                                    positionString = "" + 'd';
                                    break;
                                case 10:
                                    positionString = "" + 'e';
                                    break;
                                case 8:
                                    positionString = "" + 'f';
                                    break;
                                case 6:
                                    positionString = "" + 'g';
                                    break;
                                case 4:
                                    positionString = "" + 'h';
                                    break;
                                case 2:
                                    positionString = "" + 'i';
                                    break;
                                case 0:
                                    positionString = "" + 'j';
                                    break;
                            }
                            positionString += "" + x; // positionu belirledik. simdi hareket valid ise tasi oraya goturup, konterol edip sonrasında geri getir devam et. boolean true olursa break et.
                            if (kontrolBoard.getItems()[n] instanceof At) {
                                ((At) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                            } else if (kontrolBoard.getItems()[n] instanceof Top) {
                                ((Top) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                            } else if (kontrolBoard.getItems()[n] instanceof Vezir) {
                                ((Vezir) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                            } else if (kontrolBoard.getItems()[n] instanceof Sah) {
                                ((Sah) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                            } else if (kontrolBoard.getItems()[n] instanceof Fil) {
                                ((Fil) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                            } else if (kontrolBoard.getItems()[n] instanceof Kale) {
                                ((Kale) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                            } else if (kontrolBoard.getItems()[n] instanceof Piyon) {
                                ((Piyon) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                            }
                            String geldigiYer = kontrolBoard.getItems()[n].getPosition();
                            if (kontrolBoard.getItems()[n] instanceof At) {
                                kontrolBoard.getItems()[n].move(locationOfSah1); // oynatabiliyorsak
                                if (((At) kontrolBoard.getItems()[n]).isMoveValid()) {
                                    halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard,locationOfSah1,locationOfSah2);
                                    ((At) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                    kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                    kontrolBoard.clearboard();
                                    kontrolBoard.placeItems();halaSahCekiliyorMu= false;
                                }
                            }
                            if (kontrolBoard.getItems()[n] instanceof Top) {
                                kontrolBoard.getItems()[n].move(locationOfSah1); // oynatabiliyorsak
                                if (((Top) kontrolBoard.getItems()[n]).isMoveValid()) {
                                    halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard, locationOfSah1,locationOfSah2);
                                    ((Top) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                    kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                    kontrolBoard.clearboard();
                                    kontrolBoard.placeItems();halaSahCekiliyorMu= false;
                                }
                            }
                            if (kontrolBoard.getItems()[n] instanceof Vezir) {
                                kontrolBoard.getItems()[n].move(locationOfSah1); // oynatabiliyorsak
                                if (((Vezir) kontrolBoard.getItems()[n]).isMoveValid()) {
                                    halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard, locationOfSah1,locationOfSah2);
                                    ((Vezir) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                    kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                    kontrolBoard.clearboard();
                                    kontrolBoard.placeItems();halaSahCekiliyorMu= false;
                                }
                            }
                            if (kontrolBoard.getItems()[n] instanceof Sah) {
                                kontrolBoard.getItems()[n].move(locationOfSah1); // oynatabiliyorsak
                                if (((Sah) kontrolBoard.getItems()[n]).isMoveValid()) {
                                    halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard, locationOfSah1,locationOfSah2);
                                    ((Sah) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                    kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                    kontrolBoard.clearboard();
                                    kontrolBoard.placeItems();halaSahCekiliyorMu= false;
                                }
                            }
                            if (kontrolBoard.getItems()[n] instanceof Fil) {
                                kontrolBoard.getItems()[n].move(locationOfSah1); // oynatabiliyorsak
                                if (((Fil) kontrolBoard.getItems()[n]).isMoveValid()) {
                                    halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard, locationOfSah1,locationOfSah2);
                                    ((Fil) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                    kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                    kontrolBoard.clearboard();
                                    kontrolBoard.placeItems();halaSahCekiliyorMu= false;
                                }
                            }
                            if (kontrolBoard.getItems()[n] instanceof Kale) {
                                kontrolBoard.getItems()[n].move(locationOfSah1); // oynatabiliyorsak
                                if (((Kale) kontrolBoard.getItems()[n]).isMoveValid()) {
                                    halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard, locationOfSah1,locationOfSah2);
                                    ((Kale) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                    kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                    kontrolBoard.clearboard();
                                    kontrolBoard.placeItems();halaSahCekiliyorMu= false;
                                }
                            }
                            if (kontrolBoard.getItems()[n] instanceof Piyon) {
                                kontrolBoard.getItems()[n].move(locationOfSah1); // oynatabiliyorsak
                                if (((Piyon) kontrolBoard.getItems()[n]).isMoveValid()) {
                                    halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard, locationOfSah1,locationOfSah2);
                                    ((Piyon) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                    kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                    kontrolBoard.clearboard();
                                    kontrolBoard.placeItems();halaSahCekiliyorMu= false;
                                }
                            }
                            if (halaSahCekiliyorMu == false) {
                                return true;
                            }
                        }
                    }
                }
            }

        }
    return false;
    }
    private boolean LowerPlayereSahCekiliyorMu(Board givenBoard, String locationOfSah1, String locationOfSah2){ // burada kontrol board degisiyor. onu engellemek lazım !!!
        for (int n=0; n< givenBoard.getItems().length; n++){
          Board   kontrolBoard = new Board();
                   kontrolBoard.clearboard();

            // kontrolBoard.items = board.items; // I need to do this in deep copy
            for(int k=0;k<givenBoard.getItems().length ;k++){
                if(givenBoard.getItems()[k] instanceof At) {
                    kontrolBoard.getItems()[k] = (At)givenBoard.getItems()[k].cloneMe(); // creating new items with the same type.
                }
                else  if(givenBoard.getItems()[k] instanceof Top) {
                    kontrolBoard.getItems()[k] = (Top)givenBoard.getItems()[k].cloneMe(); // creating new items with the same type.
                }
                else   if(givenBoard.getItems()[k] instanceof Vezir) {
                    kontrolBoard.getItems()[k] = (Vezir)givenBoard.getItems()[k].cloneMe(); // creating new items with the same type.
                }
                else   if(givenBoard.getItems()[k] instanceof Sah) {
                    kontrolBoard.getItems()[k] = (Sah)givenBoard.getItems()[k].cloneMe(); // creating new items with the same type.
                }
                else   if(givenBoard.getItems()[k] instanceof Fil) {
                    kontrolBoard.getItems()[k] = (Fil)givenBoard.getItems()[k].cloneMe(); // creating new items with the same type.
                }
                else    if(givenBoard.getItems()[k] instanceof Kale) {
                    kontrolBoard.getItems()[k] = (Kale)givenBoard.getItems()[k].cloneMe(); // creating new items with the same type.
                }
                else   if(givenBoard.getItems()[k] instanceof Piyon) {
                    kontrolBoard.getItems()[k] = (Piyon)givenBoard.getItems()[k].cloneMe(); // creating new items with the same type.
                }
            }
            kontrolBoard.placeItems();

            if(kontrolBoard.getItems()[n] instanceof At){ ((At) kontrolBoard.getItems()[n]).setboard(kontrolBoard);  }
            else    if(kontrolBoard.getItems()[n] instanceof Top) { ((Top) kontrolBoard.getItems()[n]).setboard(kontrolBoard);   }
            else   if(kontrolBoard.getItems()[n] instanceof Vezir) {((Vezir) kontrolBoard.getItems()[n]).setboard(kontrolBoard);    }
            else   if(kontrolBoard.getItems()[n] instanceof Sah) { ((Sah) kontrolBoard.getItems()[n]).setboard(kontrolBoard);    }
            else   if(kontrolBoard.getItems()[n] instanceof Fil) { ((Fil) kontrolBoard.getItems()[n]).setboard(kontrolBoard);   }
            else   if(kontrolBoard.getItems()[n] instanceof Kale) {  ((Kale) kontrolBoard.getItems()[n]).setboard(kontrolBoard);     }
            else   if(kontrolBoard.getItems()[n] instanceof Piyon) {((Piyon) kontrolBoard.getItems()[n]).setboard(kontrolBoard);}

            if(kontrolBoard.getItems()[n].getName().toUpperCase().equals(kontrolBoard.getItems()[n].getName())){ // piece belong to the enemy
                if(kontrolBoard.getItems()[n] instanceof At){
                    kontrolBoard.getItems()[n].move(locationOfSah1) ; // oynatabiliyorsak
                    if(((At)kontrolBoard.getItems()[n]).isMoveValid()){
                        ((At)kontrolBoard.getItems()[n]).resetIsMoveValid();

                        return true;
                    }
                }
                else   if(kontrolBoard.getItems()[n] instanceof Top) {
                    kontrolBoard.getItems()[n].move(locationOfSah1) ; // oynatabiliyorsak
                    if(((Top)kontrolBoard.getItems()[n]).isMoveValid()){
                        ((Top)kontrolBoard.getItems()[n]).resetIsMoveValid();
                        //System.out.println(kontrolBoard.getItems()[n].getPosition()+ " ad: "+kontrolBoard.getItems()[n].getPosition());
                        KurtaranHareketVardevam = false;

                        return true;
                    }
                }
                else     if(kontrolBoard.getItems()[n] instanceof Vezir) {
                    kontrolBoard.getItems()[n].move(locationOfSah1) ; // oynatabiliyorsak
                    if(((Vezir)kontrolBoard.getItems()[n]).isMoveValid()){
                        ((Vezir)kontrolBoard.getItems()[n]).resetIsMoveValid();
                        KurtaranHareketVardevam = false;
                        return true;
                    }
                }
                else   if(kontrolBoard.getItems()[n] instanceof Sah) {
                    kontrolBoard.getItems()[n].move(locationOfSah1) ; // oynatabiliyorsak
                    if(((Sah)kontrolBoard.getItems()[n]).isMoveValid()){
                        ((Sah)kontrolBoard.getItems()[n]).resetIsMoveValid();
                        KurtaranHareketVardevam = false;
                        return true;
                    }                    }
                else    if(kontrolBoard.getItems()[n] instanceof Fil) {
                    kontrolBoard.getItems()[n].move(locationOfSah1) ; // oynatabiliyorsak
                    if(((Fil)kontrolBoard.getItems()[n]).isMoveValid()){
                        ((Fil)kontrolBoard.getItems()[n]).resetIsMoveValid();KurtaranHareketVardevam = false;
                        return true;
                    }                    }
                else    if(kontrolBoard.getItems()[n] instanceof Kale) {
                    kontrolBoard.getItems()[n].move(locationOfSah1) ; // oynatabiliyorsak
                    if(((Kale)kontrolBoard.getItems()[n]).isMoveValid()){
                        ((Kale)kontrolBoard.getItems()[n]).resetIsMoveValid();KurtaranHareketVardevam = false;

                        return true;
                    }                    }
                else    if(kontrolBoard.getItems()[n] instanceof Piyon) {
                    kontrolBoard.getItems()[n].move(locationOfSah1) ; // oynatabiliyorsak
                    if(((Piyon)kontrolBoard.getItems()[n]).isMoveValid()){
                        ((Piyon)kontrolBoard.getItems()[n]).resetIsMoveValid();KurtaranHareketVardevam = false;
                        return true;
                    }                    }
            }
        }
        return false;
    }
    private boolean hareketHataliMi(Board kontrolBoard, String to, String from){

        for (int n=0; n< kontrolBoard.getItems().length; n++) {
            kontrolBoard = new Board();
            kontrolBoard.clearboard();

            // kontrolBoard.items = board.items; // I need to do this in deep copy
            for (int k = 0; k < kontrolBoard.getItems().length; k++) {
                if (board.getItems()[k] instanceof At) {
                    kontrolBoard.getItems()[k] = (At) board.getItems()[k].cloneMe(); // creating new items with the same type.
                } else if (board.getItems()[k] instanceof Top) {
                    kontrolBoard.getItems()[k] = (Top) board.getItems()[k].cloneMe(); // creating new items with the same type.
                } else if (board.getItems()[k] instanceof Vezir) {
                    kontrolBoard.getItems()[k] = (Vezir) board.getItems()[k].cloneMe(); // creating new items with the same type.
                } else if (board.getItems()[k] instanceof Sah) {
                    kontrolBoard.getItems()[k] = (Sah) board.getItems()[k].cloneMe(); // creating new items with the same type.
                } else if (board.getItems()[k] instanceof Fil) {
                    kontrolBoard.getItems()[k] = (Fil) board.getItems()[k].cloneMe(); // creating new items with the same type.
                } else if (board.getItems()[k] instanceof Kale) {
                    kontrolBoard.getItems()[k] = (Kale) board.getItems()[k].cloneMe(); // creating new items with the same type.
                } else if (board.getItems()[k] instanceof Piyon) {
                    kontrolBoard.getItems()[k] = (Piyon) board.getItems()[k].cloneMe(); // creating new items with the same type.
                }
            }
            kontrolBoard.placeItems();
            if (kontrolBoard.getItems()[n] instanceof At) {
                ((At) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
            } else if (kontrolBoard.getItems()[n] instanceof Top) {
                ((Top) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
            } else if (kontrolBoard.getItems()[n] instanceof Vezir) {
                ((Vezir) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
            } else if (kontrolBoard.getItems()[n] instanceof Sah) {
                ((Sah) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
            } else if (kontrolBoard.getItems()[n] instanceof Fil) {
                ((Fil) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
            } else if (kontrolBoard.getItems()[n] instanceof Kale) {
                ((Kale) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
            } else if (kontrolBoard.getItems()[n] instanceof Piyon) {
                ((Piyon) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
            }
        } // copied board. copied items. simdi fromdaki tasi to ya gotur ve sah oluyor mu diye bak.
        int itemArrayIndexi = -1;
        for (int n = 0; n < kontrolBoard.getItems().length; n++) {
            if (kontrolBoard.getItems()[n].getPosition().equals(from)) {
                itemArrayIndexi = n;
                // System.out.println("n suan bu:"+ itemArrayIndex);
                break; // break is to make iteration faster.
            }
        }
        String locationOfSah1 = "";
        String locationOfSah2 = "";
        for (int n = 0; n < kontrolBoard.getItems().length; n++) {
            if (kontrolBoard.getItems()[n].getName().equals("ş")) { // we now know the index of enemy sah
                locationOfSah1 = kontrolBoard.getItems()[n].getPosition();
                break;
            }
        }
        for (int n = 0; n < kontrolBoard.getItems().length; n++) {
            if (kontrolBoard.getItems()[n].getName().equals("Ş")) { // we now know the index of enemy sah
                locationOfSah2 = kontrolBoard.getItems()[n].getPosition();
                break;
            }
        }
        if (kontrolBoard.getItems()[itemArrayIndexi] instanceof At) {
            ((At) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);
            ((At) kontrolBoard.getItems()[itemArrayIndexi]).move(to);
            ((At) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);
        } else if (kontrolBoard.getItems()[itemArrayIndexi] instanceof Top) {
            ((Top) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);
            ((Top) kontrolBoard.getItems()[itemArrayIndexi]).move(to);
            ((Top) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);
        } else if (kontrolBoard.getItems()[itemArrayIndexi] instanceof Vezir) {
            ((Vezir) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);
            ((Vezir) kontrolBoard.getItems()[itemArrayIndexi]).move(to);
            ((Vezir) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);
        } else if (kontrolBoard.getItems()[itemArrayIndexi] instanceof Sah) {
            ((Sah) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);
            ((Sah) kontrolBoard.getItems()[itemArrayIndexi]).move(to);
            ((Sah) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);
        } else if (kontrolBoard.getItems()[itemArrayIndexi] instanceof Fil) {
            ((Fil) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);
            ((Fil) kontrolBoard.getItems()[itemArrayIndexi]).move(to);
            ((Fil) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);
        } else if (kontrolBoard.getItems()[itemArrayIndexi] instanceof Kale) {
            ((Kale) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);
            ((Kale) kontrolBoard.getItems()[itemArrayIndexi]).move(to);
            ((Kale) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);
        } else if (kontrolBoard.getItems()[itemArrayIndexi] instanceof Piyon ) {
            ((Piyon) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);
            ((Piyon) kontrolBoard.getItems()[itemArrayIndexi]).move(to);
            ((Piyon) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);
        } // tasimiz oynandı. simdi sah mi degil mi diye bakalım. sah degil ise tamamdır. sah ise hareket hatalidir. kurtarır ve hareket hatali ise hatali hareket bastır!!!!

        kontrolBoard.clearboard();
        kontrolBoard.placeItems();

        if(kontrolBoard.getItems()[itemArrayIndexi].getName().equals(kontrolBoard.getItems()[itemArrayIndexi].getName().toUpperCase())){ // ustteki adamındır
            if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Sah)
                hatali = UpperPlayereSahCekiliyorMu(kontrolBoard,to); // eger sah hareket etmiyor ise
            else
                hatali = UpperPlayereSahCekiliyorMu(kontrolBoard,locationOfSah2); // eger sah hareket etmiyor ise
          //  System.out.println("Abii");
          //  System.out.println("Upere verdigim tablo altta");
          //  kontrolBoard.print();
            //System.out.println("Uperee verdigim tablo ustte");


        }
        else { // alttaki adamındır
            if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Sah)
            hatali = LowerPlayereSahCekiliyorMu(kontrolBoard,to,locationOfSah2); // eger sah hareket etmiyor ise
            else
            hatali = LowerPlayereSahCekiliyorMu(kontrolBoard,locationOfSah1,locationOfSah2); // eger sah hareket etmiyor ise
           //System.out.println("Ablaa");
         //  System.out.println("Lowere verdigim tablo altta");
          //  kontrolBoard.print();
          //  System.out.println("Lowere verdigim tablo ustte");
        }
        return hatali;
    }
    private boolean isSahCekilmekteMi(String from, String to, Board board, Item[] items ){ // sah cekilmekte ise bu durumu engellemeyecek hareketler engellenmeli.
        // koyulan tas, sahı tehdit ediyor ise 1 Sah ceken tas yenmeli. 2 tasın sahı tehdit etmesi engellenmeli. Yolu kesilmeli. 3 sah oynanmalı
        // tüm tasların move metotunu cagır. kendi indexleri ve dusman sahjın indexini ver eger dogru dönerse, sah cekiliyordur.
        // play de yapılan hareket bu sah ceken taslari engellemiyor ise engelle. Yapılacak hicbir hareket yok ise sah mat yaz
        // strateji: boardı ve item arrayini kopyalamak sureti ile, hamleyi yap. bu kopyalanan hareket durumunda, Sah çekiliyor ise yani herhangi bir dusman ,dost sahi yiyebiliyor ise false don.
        Board kontrolBoard = new Board();
        kontrolBoard.clearboard();
        kurtarirMi = false;
        hatali = false;
        boolean isBeginningteSahCekilmekte = false;
        // kontrolBoard.items = board.items; // I need to do this in deep copy
        for(int n=0;n<items.length ;n++){
            if(board.getItems()[n] instanceof At) {
                kontrolBoard.getItems()[n] = (At)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Top) {
                kontrolBoard.getItems()[n] = (Top)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Vezir) {
                kontrolBoard.getItems()[n] = (Vezir)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Sah) {
                kontrolBoard.getItems()[n] = (Sah)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Fil) {
                kontrolBoard.getItems()[n] = (Fil)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Kale) {
                kontrolBoard.getItems()[n] = (Kale)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Piyon) {
                kontrolBoard.getItems()[n] = (Piyon)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }


        }
        kontrolBoard.placeItems();
        int itemArrayIndexi = -1;
        for (int n = 0; n < kontrolBoard.getItems().length; n++) {
            if (kontrolBoard.getItems()[n].getPosition().equals(from)) {
                itemArrayIndexi = n;
                //   System.out.println("n suan bu:"+ itemArrayIndex);
                break; // break is to make iteration faster.
            }
        }
        String locationOfSah1 = "";
        String locationOfSah2 = "";
        for (int n = 0; n < kontrolBoard.getItems().length; n++) {
            if (kontrolBoard.getItems()[n].getName().equals("ş")) { // we now know the index of enemy sah
                locationOfSah1 = kontrolBoard.getItems()[n].getPosition();
                break;
            }
        }
        for (int n = 0; n < kontrolBoard.getItems().length; n++) {
            if (kontrolBoard.getItems()[n].getName().equals("Ş")) { // we now know the index of enemy sah
                locationOfSah2 = kontrolBoard.getItems()[n].getPosition();
                break;
            }
        } // itemler yerlestirildi. Tahtaya dizildi. Simdi to ya hareket olmamak kaydı ile, yani baslangıcta, dost sah yenilebiliyor ise, to ya giden hareket engeller mi bak. Eger enemy sah, fromdaki tas to ya gidince yenebiliyor ise Sah Mat kısmına git.

        if(board.getItems()[itemArrayIndexi] instanceof At){ ((At) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);  }
        else if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Top) { ((Top) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);   }
        else if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Vezir) {((Vezir) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);    }
        else  if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Sah) { ((Sah) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);    }
        else if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Fil) { ((Fil) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);   }
        else if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Kale) {  ((Kale) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);    }
        else if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Piyon) {((Piyon) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);}

        if(kontrolBoard.getItems()[itemArrayIndexi].getName().equals(kontrolBoard.getItems()[itemArrayIndexi].getName().toUpperCase())){ // buyuk oyuncu ise
            isBeginningteSahCekilmekte = UpperPlayereSahCekiliyorMu(kontrolBoard,locationOfSah2);

            if(isBeginningteSahCekilmekte == false){ return false;}
            else if(isBeginningteSahCekilmekte == true){ // secili tas to ya gidince hepsini oynat. Senin sah hala yenilebiliyor ise, kendi tasinin oyunucu isic sah mat de bitir.
                // secili tasi oynattim. simdi for loop icinde hala aynı sah yenebiliyor mu bakacagim. yeniliyorse sah mat. yoksa hareket sorunu cözdü
                // once, dosttaki tüm taslara, gidebilecekleri tüm hareketleri yaptırınca da sah cekiliyor ise sah mat yaz.
                // from dan to ya goturunce sah durumu kalkıyor mu once ona bakalım.

                boolean halaSahCekiliyorMu = false;
                for(int n=0;n<kontrolBoard.getItems().length ;n++){
                    if(kontrolBoard.getItems()[n].getName().equals(kontrolBoard.getItems()[n].getName().toUpperCase())) { // dost bir tas. bu tasa valid olan her hareketi yaptır. Sonra sah mi kontrol et. eger sah olmuyor ise boolean kurtarirMi yi true set et break et.
                        String positionString = "";
                        for (int x = 1; x < 10; x++) {
                            for (int y = 0; y < 19; y += 2) {
                                switch (y) {
                                    case 18:
                                        positionString = "" + 'a';
                                        break;
                                    case 16:
                                        positionString = "" + 'b';
                                        break;
                                    case 14:
                                        positionString = "" + 'c';
                                        break;
                                    case 12:
                                        positionString = "" + 'd';
                                        break;
                                    case 10:
                                        positionString = "" + 'e';
                                        break;
                                    case 8:
                                        positionString = "" + 'f';
                                        break;
                                    case 6:
                                        positionString = "" + 'g';
                                        break;
                                    case 4:
                                        positionString = "" + 'h';
                                        break;
                                    case 2:
                                        positionString = "" + 'i';
                                        break;
                                    case 0:
                                        positionString = "" + 'j';
                                        break;
                                }
                                positionString += "" + x; // positionu belirledik. simdi hareket valid ise tasi oraya goturup, konterol edip sonrasında geri getir devam et. boolean true olursa break et.
                                if (kontrolBoard.getItems()[n] instanceof At) {
                                    ((At) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                                } else if (kontrolBoard.getItems()[n] instanceof Top) {
                                    ((Top) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                                } else if (kontrolBoard.getItems()[n] instanceof Vezir) {
                                    ((Vezir) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                                } else if (kontrolBoard.getItems()[n] instanceof Sah) {
                                    ((Sah) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                                } else if (kontrolBoard.getItems()[n] instanceof Fil) {
                                    ((Fil) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                                } else if (kontrolBoard.getItems()[n] instanceof Kale) {
                                    ((Kale) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                                } else if (kontrolBoard.getItems()[n] instanceof Piyon) {
                                    ((Piyon) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                                }
                                String geldigiYer = kontrolBoard.getItems()[n].getPosition();
                                if (kontrolBoard.getItems()[n] instanceof At) {
                                    kontrolBoard.getItems()[n].move(locationOfSah2); // oynatabiliyorsak
                                    if (((At) kontrolBoard.getItems()[n]).isMoveValid()) {
                                        halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2);
                                        ((At) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                        kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                        kontrolBoard.clearboard();
                                        kontrolBoard.placeItems();
                                    }
                                }
                                if (kontrolBoard.getItems()[n] instanceof Top) {
                                    kontrolBoard.getItems()[n].move(locationOfSah2); // oynatabiliyorsak
                                    if (((Top) kontrolBoard.getItems()[n]).isMoveValid() ) {
                                        halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2);
                                        ((Top) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                        kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                        kontrolBoard.clearboard();
                                        kontrolBoard.placeItems();
                                    }
                                }
                                if (kontrolBoard.getItems()[n] instanceof Vezir) {
                                    kontrolBoard.getItems()[n].move(locationOfSah2); // oynatabiliyorsak
                                    if (((Vezir) kontrolBoard.getItems()[n]).isMoveValid()) {
                                        halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2);
                                        ((Vezir) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                        kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                        kontrolBoard.clearboard();
                                        kontrolBoard.placeItems();
                                    }
                                }
                                if (kontrolBoard.getItems()[n] instanceof Sah) {
                                    kontrolBoard.getItems()[n].move(locationOfSah2); // oynatabiliyorsak
                                    if (((Sah) kontrolBoard.getItems()[n]).isMoveValid()) {
                                        halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2);
                                        ((Sah) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                        kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                        kontrolBoard.clearboard();
                                        kontrolBoard.placeItems();
                                    }
                                }
                                if (kontrolBoard.getItems()[n] instanceof Fil) {
                                    kontrolBoard.getItems()[n].move(locationOfSah2); // oynatabiliyorsak
                                    if (((Fil) kontrolBoard.getItems()[n]).isMoveValid()) {
                                        halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2);
                                        ((Fil) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                        kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                        kontrolBoard.clearboard();
                                        kontrolBoard.placeItems();
                                    }
                                }
                                if (kontrolBoard.getItems()[n] instanceof Kale) {
                                    kontrolBoard.getItems()[n].move(locationOfSah2); // oynatabiliyorsak
                                    if (((Kale) kontrolBoard.getItems()[n]).isMoveValid()) {
                                        halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2);
                                        ((Kale) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                        kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                        kontrolBoard.clearboard();
                                        kontrolBoard.placeItems();
                                    }
                                }
                                if (kontrolBoard.getItems()[n] instanceof Piyon) {
                                    kontrolBoard.getItems()[n].move(locationOfSah2); // oynatabiliyorsak
                                    if (((Piyon) kontrolBoard.getItems()[n]).isMoveValid()) {
                                        halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2);
                                        ((Piyon) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                        kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                        kontrolBoard.clearboard();
                                        kontrolBoard.placeItems();
                                    }
                                }
                                if (halaSahCekiliyorMu == false) {
                                   // System.out.println("Alttakii kurtarayor be");
                                    kontrolBoard.print();
                                   // System.out.println("Ustteki kurtarayor be");

                                }
                            }
                        }
                    }
                }
                // alttaki loopta to ya gidis hatali oldugu icin sah cekiliyor ise return dondurerek hatali hareket dondurecegim.
                if(kurtarirMi==true){
                if(board.getItems()[itemArrayIndexi] instanceof At){ ((At) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);  ((At) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                else if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Top) { ((Top) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);   ((Top) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                else   if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Vezir) {((Vezir) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);    ((Vezir) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                else  if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Sah) { ((Sah) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);   ((Sah) kontrolBoard.getItems()[itemArrayIndexi]).move(to); }
                else  if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Fil) { ((Fil) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);   ((Fil) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                else if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Kale) {  ((Kale) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);     ((Kale) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                else  if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Piyon) {((Piyon) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);((Piyon) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}

                for(int n=0;n<kontrolBoard.getItems().length ;n++){//  eger hareket yanlis oldugu icin dost yeniliyor ise ekrana hatali hareket yaz. Eger kucuk sahin yapacagi hicbir hareket engellemiyor ise ekrana sah mat yaz.
                    if(board.getItems()[n] instanceof At){ ((At) kontrolBoard.getItems()[n]).setboard(kontrolBoard);  }
                    else if(kontrolBoard.getItems()[n] instanceof Top) { ((Top) kontrolBoard.getItems()[n]).setboard(kontrolBoard);   }
                    else if(kontrolBoard.getItems()[n] instanceof Vezir) {((Vezir) kontrolBoard.getItems()[n]).setboard(kontrolBoard);    }
                    else if(kontrolBoard.getItems()[n] instanceof Sah) { ((Sah) kontrolBoard.getItems()[n]).setboard(kontrolBoard);    }
                    else  if(kontrolBoard.getItems()[n] instanceof Fil) { ((Fil) kontrolBoard.getItems()[n]).setboard(kontrolBoard);   }
                    else if(kontrolBoard.getItems()[n] instanceof Kale) {  ((Kale) kontrolBoard.getItems()[n]).setboard(kontrolBoard);     }
                    else  if(kontrolBoard.getItems()[n] instanceof Piyon) {((Piyon) kontrolBoard.getItems()[n]).setboard(kontrolBoard);}

                    if(board.getItems()[n] instanceof At){
                        kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                        if(((At)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((At)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;
                        }
                    }
                    if(kontrolBoard.getItems()[n] instanceof Top) {
                        kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                        if(((Top)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((Top)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;
                        }
                    }
                    if(kontrolBoard.getItems()[n] instanceof Vezir) {
                        kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                        if(((Vezir)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((Vezir)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;                        }
                    }
                    if(kontrolBoard.getItems()[n] instanceof Sah) {
                        kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                        if(((Sah)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((Sah)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;                        }                    }
                    if(kontrolBoard.getItems()[n] instanceof Fil) {
                        kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                        if(((Fil)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((Fil)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;                        }                    }
                    if(kontrolBoard.getItems()[n] instanceof Kale) {
                        kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                        if(((Kale)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((Kale)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;                        }                    }
                    if(kontrolBoard.getItems()[n] instanceof Piyon) {
                        kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                        if(((Piyon)kontrolBoard.getItems()[n]).isMoveValid()){
                            ((Piyon)kontrolBoard.getItems()[n]).resetIsMoveValid();
                            return true;
                        }
                    }
                }
            } else if(kurtarirMi == false){} // Sah Mat oldugunu belirt. play metotunda
            }
        }
        else if(kontrolBoard.getItems()[itemArrayIndexi].getName().equals(kontrolBoard.getItems()[itemArrayIndexi].getName().toLowerCase())){ //kucuk oyuncu ise
            // buyuk oyuncu ise
            isBeginningteSahCekilmekte = LowerPlayereSahCekiliyorMu(kontrolBoard,locationOfSah1,locationOfSah2); // UNUTMA ALTTAKIELRE BAK + SAH 1 DIYE DUZELT. UPPERLPAYER METOTU MU VAR BAK

            boolean halaSahCekiliyorMu = false;
            if(isBeginningteSahCekilmekte == false){ return false;}
            else if(isBeginningteSahCekilmekte == true){ // secili tas to ya gidince hepsini oynat. Senin sah hala yenilebiliyor ise, kendi tasinin oyunucu isic sah mat de bitir.
                // secili tasi oynattim. simdi for loop icinde hala aynı sah yenebiliyor mu bakacagim. yeniliyorse sah mat. yoksa hareket sorunu cözdü
                // once, dosttaki tüm taslara, gidebilecekleri tüm hareketleri yaptırınca da sah cekiliyor ise sah mat yaz.
                for(int n=0;n<kontrolBoard.getItems().length ;n++){
                    if(kontrolBoard.getItems()[n].getName().equals(kontrolBoard.getItems()[n].getName().toLowerCase()) && kontrolBoard.getItems()[n].getName().equals("xx")==false) { // dost bir tas. bu tasa valid olan her hareketi yaptır. Sonra sah mi kontrol et. eger sah olmuyor ise boolean kurtarirMi yi true set et break et.
                        String positionString = "";
                        for (int x = 1; x < 10; x++) {
                            for (int y = 0; y < 19; y += 2) {
                                switch (y) {
                                    case 18:
                                        positionString = "" + 'a';
                                        break;
                                    case 16:
                                        positionString = "" + 'b';
                                        break;
                                    case 14:
                                        positionString = "" + 'c';
                                        break;
                                    case 12:
                                        positionString = "" + 'd';
                                        break;
                                    case 10:
                                        positionString = "" + 'e';
                                        break;
                                    case 8:
                                        positionString = "" + 'f';
                                        break;
                                    case 6:
                                        positionString = "" + 'g';
                                        break;
                                    case 4:
                                        positionString = "" + 'h';
                                        break;
                                    case 2:
                                        positionString = "" + 'i';
                                        break;
                                    case 0:
                                        positionString = "" + 'j';
                                        break;
                                }
                                positionString += "" + x; // positionu belirledik. simdi hareket valid ise tasi oraya goturup, konterol edip sonrasında geri getir devam et. boolean true olursa break et.
                                if (kontrolBoard.getItems()[n] instanceof At) {
                                    ((At) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                                } else if (kontrolBoard.getItems()[n] instanceof Top) {
                                    ((Top) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                                } else if (kontrolBoard.getItems()[n] instanceof Vezir) {
                                    ((Vezir) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                                } else if (kontrolBoard.getItems()[n] instanceof Sah) {
                                    ((Sah) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                                } else if (kontrolBoard.getItems()[n] instanceof Fil) {
                                    ((Fil) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                                } else if (kontrolBoard.getItems()[n] instanceof Kale) {
                                    ((Kale) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                                } else if (kontrolBoard.getItems()[n] instanceof Piyon) {
                                    ((Piyon) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                                }
                                String geldigiYer = kontrolBoard.getItems()[n].getPosition();
                                if (kontrolBoard.getItems()[n] instanceof At) {
                                    kontrolBoard.getItems()[n].move(locationOfSah1); // oynatabiliyorsak
                                    if (((At) kontrolBoard.getItems()[n]).isMoveValid()) {
                                        halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard,locationOfSah1,locationOfSah2);
                                        ((At) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                        kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                        kontrolBoard.clearboard();
                                        kontrolBoard.placeItems();
                                    }
                                }
                                if (kontrolBoard.getItems()[n] instanceof Top) {
                                    kontrolBoard.getItems()[n].move(locationOfSah1); // oynatabiliyorsak
                                    if (((Top) kontrolBoard.getItems()[n]).isMoveValid()) {
                                        halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard, locationOfSah1,locationOfSah2);
                                        ((Top) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                        kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                        kontrolBoard.clearboard();
                                        kontrolBoard.placeItems();
                                    }
                                }
                                if (kontrolBoard.getItems()[n] instanceof Vezir) {
                                    kontrolBoard.getItems()[n].move(locationOfSah1); // oynatabiliyorsak
                                    if (((Vezir) kontrolBoard.getItems()[n]).isMoveValid()) {
                                        halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard, locationOfSah1,locationOfSah2);
                                        ((Vezir) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                        kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                        kontrolBoard.clearboard();
                                        kontrolBoard.placeItems();
                                    }
                                }
                                if (kontrolBoard.getItems()[n] instanceof Sah) {
                                    kontrolBoard.getItems()[n].move(locationOfSah1); // oynatabiliyorsak
                                    if (((Sah) kontrolBoard.getItems()[n]).isMoveValid()) {
                                        halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard, locationOfSah1,locationOfSah2);
                                        ((Sah) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                        kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                        kontrolBoard.clearboard();
                                        kontrolBoard.placeItems();
                                    }
                                }
                                if (kontrolBoard.getItems()[n] instanceof Fil) {
                                    kontrolBoard.getItems()[n].move(locationOfSah1); // oynatabiliyorsak
                                    if (((Fil) kontrolBoard.getItems()[n]).isMoveValid()) {
                                        halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard, locationOfSah1,locationOfSah2);
                                        ((Fil) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                        kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                        kontrolBoard.clearboard();
                                        kontrolBoard.placeItems();
                                    }
                                }
                                if (kontrolBoard.getItems()[n] instanceof Kale) {
                                    kontrolBoard.getItems()[n].move(locationOfSah1); // oynatabiliyorsak
                                    if (((Kale) kontrolBoard.getItems()[n]).isMoveValid()) {
                                        halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard, locationOfSah1,locationOfSah2);
                                        ((Kale) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                        kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                        kontrolBoard.clearboard();
                                        kontrolBoard.placeItems();
                                    }
                                }
                                if (kontrolBoard.getItems()[n] instanceof Piyon) {
                                    kontrolBoard.getItems()[n].move(locationOfSah1); // oynatabiliyorsak
                                    if (((Piyon) kontrolBoard.getItems()[n]).isMoveValid()) {
                                        halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard, locationOfSah1,locationOfSah2);
                                        ((Piyon) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                        kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                        kontrolBoard.clearboard();
                                        kontrolBoard.placeItems();
                                    }
                                }
                                if (halaSahCekiliyorMu == false) {

                                    kurtarirMi = true;
                                    break;
                                }
                            }
                        }
                    }
                }
                // alttaki loopta to ya gidis hatali oldugu icin sah cekiliyor ise return dondurerek hatali hareket dondurecegim.
                if(kurtarirMi==true){
                    if(board.getItems()[itemArrayIndexi] instanceof At){ ((At) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);  ((At) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                    else if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Top) { ((Top) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);   ((Top) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                    else   if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Vezir) {((Vezir) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);    ((Vezir) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                    else  if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Sah) { ((Sah) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);   ((Sah) kontrolBoard.getItems()[itemArrayIndexi]).move(to); }
                    else  if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Fil) { ((Fil) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);   ((Fil) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                    else if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Kale) {  ((Kale) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);     ((Kale) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}
                    else  if(kontrolBoard.getItems()[itemArrayIndexi] instanceof Piyon) {((Piyon) kontrolBoard.getItems()[itemArrayIndexi]).setboard(kontrolBoard);((Piyon) kontrolBoard.getItems()[itemArrayIndexi]).move(to);}

                    for(int n=0;n<kontrolBoard.getItems().length ;n++){//  eger hareket yanlis oldugu icin dost yeniliyor ise ekrana hatali hareket yaz. Eger kucuk sahin yapacagi hicbir hareket engellemiyor ise ekrana sah mat yaz.
                        if(board.getItems()[n] instanceof At){ ((At) kontrolBoard.getItems()[n]).setboard(kontrolBoard);  }
                        else if(kontrolBoard.getItems()[n] instanceof Top) { ((Top) kontrolBoard.getItems()[n]).setboard(kontrolBoard);   }
                        else if(kontrolBoard.getItems()[n] instanceof Vezir) {((Vezir) kontrolBoard.getItems()[n]).setboard(kontrolBoard);    }
                        else if(kontrolBoard.getItems()[n] instanceof Sah) { ((Sah) kontrolBoard.getItems()[n]).setboard(kontrolBoard);    }
                        else  if(kontrolBoard.getItems()[n] instanceof Fil) { ((Fil) kontrolBoard.getItems()[n]).setboard(kontrolBoard);   }
                        else if(kontrolBoard.getItems()[n] instanceof Kale) {  ((Kale) kontrolBoard.getItems()[n]).setboard(kontrolBoard);     }
                        else  if(kontrolBoard.getItems()[n] instanceof Piyon) {((Piyon) kontrolBoard.getItems()[n]).setboard(kontrolBoard);}

                        if(board.getItems()[n] instanceof At){
                            kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                            if(((At)kontrolBoard.getItems()[n]).isMoveValid()){
                                ((At)kontrolBoard.getItems()[n]).resetIsMoveValid();
                                hatali = true;
                                return true;
                            }
                        }
                        if(kontrolBoard.getItems()[n] instanceof Top) {
                            kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                            if(((Top)kontrolBoard.getItems()[n]).isMoveValid()){
                                ((Top)kontrolBoard.getItems()[n]).resetIsMoveValid();
                                return true;
                            }
                        }
                        if(kontrolBoard.getItems()[n] instanceof Vezir) {
                            kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                            if(((Vezir)kontrolBoard.getItems()[n]).isMoveValid()){
                                ((Vezir)kontrolBoard.getItems()[n]).resetIsMoveValid();
                                return true;                        }
                        }
                        if(kontrolBoard.getItems()[n] instanceof Sah) {
                            kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                            if(((Sah)kontrolBoard.getItems()[n]).isMoveValid()){
                                ((Sah)kontrolBoard.getItems()[n]).resetIsMoveValid();
                                return true;                        }                    }
                        if(kontrolBoard.getItems()[n] instanceof Fil) {
                            kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                            if(((Fil)kontrolBoard.getItems()[n]).isMoveValid()){
                                ((Fil)kontrolBoard.getItems()[n]).resetIsMoveValid();
                                return true;                        }                    }
                        if(kontrolBoard.getItems()[n] instanceof Kale) {
                            kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                            if(((Kale)kontrolBoard.getItems()[n]).isMoveValid()){
                                ((Kale)kontrolBoard.getItems()[n]).resetIsMoveValid();
                                return true;                        }                    }
                        if(kontrolBoard.getItems()[n] instanceof Piyon) {
                            kontrolBoard.getItems()[n].move(locationOfSah2) ; // oynatabiliyorsak
                            if(((Piyon)kontrolBoard.getItems()[n]).isMoveValid()){
                                ((Piyon)kontrolBoard.getItems()[n]).resetIsMoveValid();
                                return true;
                            }
                        }
                    }
                }  // Sah Mat oldugunu belirt. play metotunda
            }
        }
        return false;
    }
    private boolean isOyunSonundaBuyukSahinGidecekYeriVarMi(Board board, Item[] items, String locationOfSah2){
        Board kontrolBoard = new Board();
        kontrolBoard.clearboard();

        boolean isBeginningteSahCekilmekte = false;
        // kontrolBoard.items = board.items; // I need to do this in deep copy
        for(int n=0;n<items.length ;n++){
            if(board.getItems()[n] instanceof At) {
                kontrolBoard.getItems()[n] = (At)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Top) {
                kontrolBoard.getItems()[n] = (Top)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Vezir) {
                kontrolBoard.getItems()[n] = (Vezir)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Sah) {
                kontrolBoard.getItems()[n] = (Sah)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Fil) {
                kontrolBoard.getItems()[n] = (Fil)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Kale) {
                kontrolBoard.getItems()[n] = (Kale)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Piyon) {
                kontrolBoard.getItems()[n] = (Piyon)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
        }
        kontrolBoard.placeItems();
        boolean halaSahCekiliyorMu = true;
        for(int n=0;n<kontrolBoard.getItems().length ;n++){
            if(kontrolBoard.getItems()[n].getName().equals(kontrolBoard.getItems()[n].getName().toUpperCase())) { // dost bir tas. bu tasa valid olan her hareketi yaptır. Sonra sah mi kontrol et. eger sah olmuyor ise boolean kurtarirMi yi true set et break et.
                String positionString = "";
                for (int x = 1; x < 10; x++) {
                    for (int y = 0; y < 19; y += 2) {
                        switch (y) {
                            case 18:
                                positionString = "" + 'a';
                                break;
                            case 16:
                                positionString = "" + 'b';
                                break;
                            case 14:
                                positionString = "" + 'c';
                                break;
                            case 12:
                                positionString = "" + 'd';
                                break;
                            case 10:
                                positionString = "" + 'e';
                                break;
                            case 8:
                                positionString = "" + 'f';
                                break;
                            case 6:
                                positionString = "" + 'g';
                                break;
                            case 4:
                                positionString = "" + 'h';
                                break;
                            case 2:
                                positionString = "" + 'i';
                                break;
                            case 0:
                                positionString = "" + 'j';
                                break;
                        }
                        positionString += "" + x; // positionu belirledik. simdi hareket valid ise tasi oraya goturup, konterol edip sonrasında geri getir devam et. boolean true olursa break et.

                        if (kontrolBoard.getItems()[n] instanceof At) {
                            ((At) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        } else if (kontrolBoard.getItems()[n] instanceof Top) {
                            ((Top) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        } else if (kontrolBoard.getItems()[n] instanceof Vezir) {
                            ((Vezir) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        } else if (kontrolBoard.getItems()[n] instanceof Sah) {
                            ((Sah) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        } else if (kontrolBoard.getItems()[n] instanceof Fil) {
                            ((Fil) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        } else if (kontrolBoard.getItems()[n] instanceof Kale) {
                            ((Kale) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        } else if (kontrolBoard.getItems()[n] instanceof Piyon) {
                            ((Piyon) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        }
                        String geldigiYer = kontrolBoard.getItems()[n].getPosition();
                        if (kontrolBoard.getItems()[n] instanceof At) {
                            kontrolBoard.getItems()[n].move(positionString); // oynatabiliyorsak
                            if (((At) kontrolBoard.getItems()[n]).isMoveValid()) {
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems();
                                halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2);
                                ((At) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems();
                            }
                        }
                        if (kontrolBoard.getItems()[n] instanceof Top) {
                            kontrolBoard.getItems()[n].move(positionString); // oynatabiliyorsak
                            if (((Top) kontrolBoard.getItems()[n]).isMoveValid() ) {     kontrolBoard.clearboard();     kontrolBoard.placeItems();
                                halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2);
                                ((Top) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems();
                            }
                        }
                        if (kontrolBoard.getItems()[n] instanceof Vezir) {
                            kontrolBoard.getItems()[n].move(positionString); // oynatabiliyorsak
                            if (((Vezir) kontrolBoard.getItems()[n]).isMoveValid()) {     kontrolBoard.clearboard();     kontrolBoard.placeItems();
                                halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2);
                                ((Vezir) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems();
                            }
                        }
                        if (kontrolBoard.getItems()[n] instanceof Sah) {
                            kontrolBoard.getItems()[n].move(positionString); // oynatabiliyorsak
                            if (((Sah) kontrolBoard.getItems()[n]).isMoveValid()) {      kontrolBoard.clearboard();    kontrolBoard.placeItems();
                                halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, positionString);
                                ((Sah) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems();
                            }
                        }
                        if (kontrolBoard.getItems()[n] instanceof Fil) {
                            kontrolBoard.getItems()[n].move(positionString); // oynatabiliyorsak
                            if (((Fil) kontrolBoard.getItems()[n]).isMoveValid()) {      kontrolBoard.clearboard();    kontrolBoard.placeItems();
                                halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2);                                ((Fil) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems();
                            }
                        }
                        if (kontrolBoard.getItems()[n] instanceof Kale) {
                            kontrolBoard.getItems()[n].move(positionString); // oynatabiliyorsak
                            if (((Kale) kontrolBoard.getItems()[n]).isMoveValid()) {       kontrolBoard.clearboard();   kontrolBoard.placeItems();
                                halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2);                                ((Kale) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems();
                            }
                        }
                        if (kontrolBoard.getItems()[n] instanceof Piyon) {
                            kontrolBoard.getItems()[n].move(positionString); // oynatabiliyorsak
                            if (((Piyon) kontrolBoard.getItems()[n]).isMoveValid()) {  kontrolBoard.clearboard();
                                kontrolBoard.placeItems();
                                halaSahCekiliyorMu = UpperPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2);
                                ((Piyon) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems();
                            }
                        }
                        if(halaSahCekiliyorMu == false){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    private Boolean isOyunSonundaKucukSahinGidecekYeriVarMi(Board board, Item[] items, String locationOfSah2){
        Board kontrolBoard = new Board();
        kontrolBoard.clearboard();

        boolean isBeginningteSahCekilmekte = false;
        // kontrolBoard.items = board.items; // I need to do this in deep copy
        for(int n=0;n<items.length ;n++){
            if(board.getItems()[n] instanceof At) {
                kontrolBoard.getItems()[n] = (At)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Top) {
                kontrolBoard.getItems()[n] = (Top)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Vezir) {
                kontrolBoard.getItems()[n] = (Vezir)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Sah) {
                kontrolBoard.getItems()[n] = (Sah)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Fil) {
                kontrolBoard.getItems()[n] = (Fil)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Kale) {
                kontrolBoard.getItems()[n] = (Kale)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
            if(board.getItems()[n] instanceof Piyon) {
                kontrolBoard.getItems()[n] = (Piyon)board.getItems()[n].cloneMe(); // creating new items with the same type.
            }
        }
        kontrolBoard.placeItems();
        boolean halaSahCekiliyorMu = true;
        for(int n=0;n<kontrolBoard.getItems().length ;n++){
            if(kontrolBoard.getItems()[n].getName().equals(kontrolBoard.getItems()[n].getName().toLowerCase())) { // dost bir tas. bu tasa valid olan her hareketi yaptır. Sonra sah mi kontrol et. eger sah olmuyor ise boolean kurtarirMi yi true set et break et.
                String positionString = "";
                for (int x = 1; x < 10; x++) {
                    for (int y = 0; y < 19; y += 2) {
                        switch (y) {
                            case 18:
                                positionString = "" + 'a';
                                break;
                            case 16:
                                positionString = "" + 'b';
                                break;
                            case 14:
                                positionString = "" + 'c';
                                break;
                            case 12:
                                positionString = "" + 'd';
                                break;
                            case 10:
                                positionString = "" + 'e';
                                break;
                            case 8:
                                positionString = "" + 'f';
                                break;
                            case 6:
                                positionString = "" + 'g';
                                break;
                            case 4:
                                positionString = "" + 'h';
                                break;
                            case 2:
                                positionString = "" + 'i';
                                break;
                            case 0:
                                positionString = "" + 'j';
                                break;
                        }
                        positionString += "" + x; // positionu belirledik. simdi hareket valid ise tasi oraya goturup, konterol edip sonrasında geri getir devam et. boolean true olursa break et.

                        if (kontrolBoard.getItems()[n] instanceof At) {
                            ((At) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        } else if (kontrolBoard.getItems()[n] instanceof Top) {
                            ((Top) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        } else if (kontrolBoard.getItems()[n] instanceof Vezir) {
                            ((Vezir) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        } else if (kontrolBoard.getItems()[n] instanceof Sah) {
                            ((Sah) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        } else if (kontrolBoard.getItems()[n] instanceof Fil) {
                            ((Fil) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        } else if (kontrolBoard.getItems()[n] instanceof Kale) {
                            ((Kale) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        } else if (kontrolBoard.getItems()[n] instanceof Piyon) {
                            ((Piyon) kontrolBoard.getItems()[n]).setboard(kontrolBoard);
                        }
                        String geldigiYer = kontrolBoard.getItems()[n].getPosition();
                        if (kontrolBoard.getItems()[n] instanceof At) {
                            kontrolBoard.getItems()[n].move(positionString); // oynatabiliyorsak
                            if (((At) kontrolBoard.getItems()[n]).isMoveValid()) {
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems();
                                halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2, "");
                                ((At) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems();
                            }
                        }
                        if (kontrolBoard.getItems()[n] instanceof Top) {
                            kontrolBoard.getItems()[n].move(positionString); // oynatabiliyorsak
                            if (((Top) kontrolBoard.getItems()[n]).isMoveValid() ) {     kontrolBoard.clearboard();     kontrolBoard.placeItems();
                                halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2, "");
                                ((Top) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems();
                            }
                        }
                        if (kontrolBoard.getItems()[n] instanceof Vezir) {
                            kontrolBoard.getItems()[n].move(positionString); // oynatabiliyorsak
                            if (((Vezir) kontrolBoard.getItems()[n]).isMoveValid()) {     kontrolBoard.clearboard();     kontrolBoard.placeItems();
                                halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2, "");
                                ((Vezir) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems();
                            }
                        }
                        if (kontrolBoard.getItems()[n] instanceof Sah) {
                            kontrolBoard.getItems()[n].move(positionString); // oynatabiliyorsak
                            if (((Sah) kontrolBoard.getItems()[n]).isMoveValid()) {      kontrolBoard.clearboard();    kontrolBoard.placeItems();
                                halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard, positionString, "");
                                ((Sah) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems();
                            }
                        }
                        if (kontrolBoard.getItems()[n] instanceof Fil) {
                            kontrolBoard.getItems()[n].move(positionString); // oynatabiliyorsak
                            if (((Fil) kontrolBoard.getItems()[n]).isMoveValid()) {      kontrolBoard.clearboard();    kontrolBoard.placeItems();
                                halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2, "");
                                ((Fil) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems();
                            }
                        }
                        if (kontrolBoard.getItems()[n] instanceof Kale) {
                            kontrolBoard.getItems()[n].move(positionString); // oynatabiliyorsak
                            if (((Kale) kontrolBoard.getItems()[n]).isMoveValid()) {       kontrolBoard.clearboard();   kontrolBoard.placeItems();
                                halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2, "");
                                ((Kale) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems();
                            }
                        }
                        if (kontrolBoard.getItems()[n] instanceof Piyon) {
                            kontrolBoard.getItems()[n].move(positionString); // oynatabiliyorsak
                            if (((Piyon) kontrolBoard.getItems()[n]).isMoveValid()) {  kontrolBoard.clearboard();
                                kontrolBoard.placeItems();
                                halaSahCekiliyorMu = LowerPlayereSahCekiliyorMu(kontrolBoard, locationOfSah2, "");
                                ((Piyon) kontrolBoard.getItems()[n]).resetIsMoveValid();
                                kontrolBoard.getItems()[n].setPosition(geldigiYer);
                                kontrolBoard.clearboard();
                                kontrolBoard.placeItems();
                            }
                        }
                        if(halaSahCekiliyorMu == false){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }





    // kuycuksahin gideicek yeri var mi, guncelle upper player için.
    @Override
    void play(String from, String to) {  // get the index of item that has the same piece with the from location. decide the type of object. Decide wether the move is valid.
        try {
            if (isSahMatOlduVeOyunBittiMi == false) {
                String locationOfSah1 = "";
                String locationOfSah2 = "";
                for (int n = 0; n < board.getItems().length; n++) {
                    if (board.getItems()[n].getName().equals("ş")) { // we now know the index of enemy sah
                        locationOfSah1 = board.getItems()[n].getPosition();
                        break;
                    }
                }
                for (int n = 0; n < board.getItems().length; n++) {
                    if (board.getItems()[n].getName().equals("Ş")) { // we now know the index of enemy sah
                        locationOfSah2 = board.getItems()[n].getPosition();
                        break;
                    }
                }
                if (to != null) {
                    //  System.out.println(board.getItems()[22].getPosition()+board.getItems()[22].getName());
                    //  System.out.println(board.getItems()[23].getPosition()+board.getItems()[23].getName());
                    //   System.out.println(board.getItems()[24].getPosition()+board.getItems()[24].getName());

                    int itemArrayIndex = -1;
                    for (int n = 0; n < board.getItems().length; n++) {
                        if (board.getItems()[n].getPosition().equals(from)) {
                            itemArrayIndex = n;
                            //   System.out.println("n suan bu:"+ itemArrayIndex);
                            break; // break is to make iteration faster.
                        }
                    }
                    //System.out.println(board.getItems()[itemArrayIndex].getPosition());
                    if (itemArrayIndex == -1) {
                        // System.out.println(board.getItems()[22].getPosition()+ board.getItems()[22].getName());
                        System.out.println("hatali hareket");
                        return;
                    }
                    // kirmizi buyuk harf ise
                    // amac sırası gelen oyuncuyu oynatmak. sira onde degilse hata bastirmak
                    else if (KirmizininSirasi() == false && board.getItems()[itemArrayIndex].getName().equals(board.getItems()[itemArrayIndex].getName().toUpperCase()) == false) {
                        System.out.println("hatali hareket");
                    } else if (KirmizininSirasi() == true && board.getItems()[itemArrayIndex].getName().equals(board.getItems()[itemArrayIndex].getName().toUpperCase()) == true) {
                        System.out.println("hatali hareket");
                    }
                    // sahlar karsilikli olamaz
                    else if (from.equals(to)) {
                        System.out.println("hatali hareket");
                    } // cant stay at the same location
                    else if (SahlarBakisiyorMu(from, to) == false) {
                        System.out.println("hatali hareket");
                    } else if (board.getItems()[itemArrayIndex] instanceof Kale) { // kaladeki degisiklikleri digerlerine uygula
                        ((Kale) board.getItems()[itemArrayIndex]).setboard(board);
                        if (isTasPinned(from, to, board, getBoard().getItems()) == true) {
                            System.out.println("hatali hareket");
                        } else {
                            board.getItems()[itemArrayIndex].move(to); // calling is move valid after the moving action because i need to set it to true first. then set it to false again.
                            if (to.equals(board.getItems()[itemArrayIndex].getPosition()) == false)
                                System.out.println("hatali hareket");
                            else if (((Kale) board.getItems()[itemArrayIndex]).isMoveValid() == true) {
                                SiraKimde++;
                                ((Kale) (board.getItems()[itemArrayIndex])).resetIsMoveValid();
                            } // if move is valid then the other player should play.
                        }
                    } else if (board.getItems()[itemArrayIndex] instanceof At) {
                        ((At) board.getItems()[itemArrayIndex]).setboard(board);
                        if (isTasPinned(from, to, board, getBoard().getItems()) == true) {
                            System.out.println("hatali hareket");
                        } else {
                            board.getItems()[itemArrayIndex].move(to);
                            if (to.equals(board.getItems()[itemArrayIndex].getPosition()) == false)
                                System.out.println("hatali hareket");
                            else if (((At) board.getItems()[itemArrayIndex]).isMoveValid() == true) {
                                SiraKimde++;
                                ((At) (board.getItems()[itemArrayIndex])).resetIsMoveValid();
                            } // if move is valid then the other player should play.
                        }
                    } else if (board.getItems()[itemArrayIndex] instanceof Fil) {
                        ((Fil) board.getItems()[itemArrayIndex]).setboard(board);
                        if (isTasPinned(from, to, board, getBoard().getItems()) == true) {
                            System.out.println("hatali hareket");
                        } else {
                            board.getItems()[itemArrayIndex].move(to);
                            if (to.equals(board.getItems()[itemArrayIndex].getPosition()) == false)
                                System.out.println("hatali hareket");
                            else if (((Fil) board.getItems()[itemArrayIndex]).isMoveValid() == true) {
                                SiraKimde++;
                                ((Fil) (board.getItems()[itemArrayIndex])).resetIsMoveValid();
                            } // if move is valid then the other player should play.
                        }
                    } else if (board.getItems()[itemArrayIndex] instanceof Vezir) {
                        ((Vezir) board.getItems()[itemArrayIndex]).setboard(board);
                        if (isTasPinned(from, to, board, getBoard().getItems()) == true) {
                            System.out.println("hatali hareket");
                        } else {
                            board.getItems()[itemArrayIndex].move(to);
                            if (to.equals(board.getItems()[itemArrayIndex].getPosition()) == false)
                                System.out.println("hatali hareket");
                            else if (((Vezir) board.getItems()[itemArrayIndex]).isMoveValid() == true) {
                                SiraKimde++;
                                ((Vezir) (board.getItems()[itemArrayIndex])).resetIsMoveValid();
                            } // if move is valid then the other player should play.

                        }
                    } else if (board.getItems()[itemArrayIndex] instanceof Sah) {

                        ((Sah) board.getItems()[itemArrayIndex]).setboard(board);
                        if (isTasPinned(from, to, board, getBoard().getItems()) == true) {
                            System.out.println("hatali hareket");
                        } else {
                            board.getItems()[itemArrayIndex].move(to);
                            if (to.equals(board.getItems()[itemArrayIndex].getPosition()) == false)
                                System.out.println("hatali hareket");
                            else if (((Sah) board.getItems()[itemArrayIndex]).isMoveValid() == true) {
                                SiraKimde++;
                                ((Sah) (board.getItems()[itemArrayIndex])).resetIsMoveValid();
                            } // if move is valid then the other player should play.
                        }
                    } else if (board.getItems()[itemArrayIndex] instanceof Top) {

                        ((Top) board.getItems()[itemArrayIndex]).setboard(board);
                        if (isTasPinned(from, to, board, getBoard().getItems()) == true) {
                            System.out.println("hatali hareket");
                        } else {
                            board.getItems()[itemArrayIndex].move(to);
                            if (to.equals(board.getItems()[itemArrayIndex].getPosition()) == false)
                                System.out.println("hatali hareket");
                            else if (((Top) board.getItems()[itemArrayIndex]).isMoveValid() == true) {
                                SiraKimde++;
                                ((Top) (board.getItems()[itemArrayIndex])).resetIsMoveValid();
                            } // if move is valid then the other player should play.
                        }
                    } else if (board.getItems()[itemArrayIndex] instanceof Piyon) {
                        // System.out.println("Hey");
                        ((Piyon) board.getItems()[itemArrayIndex]).setboard(board);
                        if (isTasPinned(from, to, board, getBoard().getItems()) == true) {
                            System.out.println("hatali hareket");
                        } else {
                            board.getItems()[itemArrayIndex].move(to);
                            if (to.equals(board.getItems()[itemArrayIndex].getPosition()) == false)
                                System.out.println("hatali hareket");
                            else if (((Piyon) board.getItems()[itemArrayIndex]).isMoveValid() == true) {
                                SiraKimde++;
                                ((Piyon) (board.getItems()[itemArrayIndex])).resetIsMoveValid();
                            } // if move is valid then the other player should play.
                        }
                    }
                    //System.out.println(board.getItems()[itemArrayIndex].getPosition());
                } else {
                    System.out.println("hatali hareket");
                }
                // yeni bir metot yaz. öyle ki hamle sonunda, düsman sah bir yere gidemiyor ise mat desin.
                for (int n = 0; n < board.getItems().length; n++) {
                    if (board.getItems()[n].getName().equals("ş")) { // we now know the index of enemy sah
                        locationOfSah1 = board.getItems()[n].getPosition();
                        break;
                    }
                }
                for (int n = 0; n < board.getItems().length; n++) {
                    if (board.getItems()[n].getName().equals("Ş")) { // we now know the index of enemy sah
                        locationOfSah2 = board.getItems()[n].getPosition();
                        break;
                    }
                }
                // sah matta sorun var buraya tekrar bak. ikisinde de öncex var
                if (isOyunSonundaBuyukSahinGidecekYeriVarMi(board, board.items, locationOfSah2) == false) {
                    System.out.println("Şah MAT! " + red.getIsim() + " oyunu kazandı. " + red.getIsim() + "'in puanı: " + red.getPuan() + ", " + black.getIsim() + "'nin puanı: " + black.getPuan()); // bosluk stringlere puan sisteminin kurunca hallederim.
                    isSahMatOlduVeOyunBittiMi = true;
                }
                if (isOyunSonundaKucukSahinGidecekYeriVarMi(board, board.items, locationOfSah1) == false) {
                    isSahMatOlduVeOyunBittiMi = true;
                    System.out.println("Şah MAT! " + black.getIsim() + " oyunu kazandı. " + red.getIsim() + "'in puanı: " + red.getPuan() + ", " + black.getIsim() + "'nin puanı: " + black.getPuan()); // bosluk stringlere puan sisteminin kurunca hallederim.

                }
                KurtaranHareketVardevam = true;


                // puanları dagitalim. mat olursa diye.
                // reset all points. then add points again.
                //black.setPuan(black.getPuan()+board.getItems()[n].puan); // red.setPuan(red.getPuan()+board.getItems()[n].puan);

                red.setPuan(0);
                black.setPuan(0);
                for (int n = 0; n < board.getItems().length; n++) { // add points
                    // System.out.println("******");
                    // System.out.print(n);
                    // System.out.println("******");
                    if (board.getItems()[n].getName().equals(board.getItems()[n].getName().toLowerCase())) { // tas buyuk oyuncunun. Eger position xx ise, yenmis demektir. dusman adama ekle bunu.
                        if (board.getItems()[n].getPosition().equals("xx")) {
                            if (board.getItems()[n] instanceof Fil) {
                                black.add(((Fil) (board.getItems()[n])).puan);
                            } else if (board.getItems()[n] instanceof At) {
                                black.add(((At) (board.getItems()[n])).puan);
                            } else if (board.getItems()[n] instanceof Kale) {
                                black.add(((Kale) (board.getItems()[n])).puan);
                            } else if (board.getItems()[n] instanceof Piyon) {
                                black.add(((Piyon) (board.getItems()[n])).puan);
                            } else if (board.getItems()[n] instanceof Vezir) {
                                black.add(((Vezir) (board.getItems()[n])).puan);
                            } else if (board.getItems()[n] instanceof Sah) {
                                black.add(((Sah) (board.getItems()[n])).puan);
                            } else if (board.getItems()[n] instanceof Top) {
                                black.add(((Top) (board.getItems()[n])).puan);
                            }
                        }
                    } else {
                        if (board.getItems()[n].getPosition().equals("xx")) {
                            // System.out.println(board.getItems()[n].getName());
                            if (board.getItems()[n] instanceof Fil) {
                                red.add(((Fil) (board.getItems()[n])).puan);
                            } else if (board.getItems()[n] instanceof At) {
                                red.add(((At) (board.getItems()[n])).puan);
                            } else if (board.getItems()[n] instanceof Kale) {
                                red.add(((Kale) (board.getItems()[n])).puan);
                            } else if (board.getItems()[n] instanceof Piyon) {
                                red.add(((Piyon) (board.getItems()[n])).puan);
                            } else if (board.getItems()[n] instanceof Vezir) {
                                red.add(((Vezir) (board.getItems()[n])).puan);
                            } else if (board.getItems()[n] instanceof Sah) {
                                red.add(((Sah) (board.getItems()[n])).puan);
                            } else if (board.getItems()[n] instanceof Top) {
                                red.add(((Top) (board.getItems()[n])).puan);
                            }
                        }
                    }
                }
                // unutmamak adına dursun
/*{
                     if(KurtaranHareketVardevam == false) kurtarirMi = false;
                     // System.out.println("HERE");
                     if(hatali == true && kurtarirMi == true){
                         System.out.println("AChatali hareket");
                     }
                     else if(kurtarirMi == false){// "Sah MAT! X oyunu kazandı. X'in puanı: A, Y'nin puanı: B" yazar. X ve Y oyuncuların ismidir. A ve B aldıkları puanlardır.
                         if(KirmizininSirasi() == true)
                             System.out.println("Sah MAT! Kirmizi oyunu kazandı."+ red.getIsim() +"'in puanı: "+ red.getPuan()+" " + black.getIsim()+"'nin puanı: "+ black.getPuan()); // bosluk stringlere puan sisteminin kurunca hallederim.
                         else { // kirmizinin sirasi degil. demek ki siyah kaybetti
                             System.out.println("Sah MAT! Siyah oyunu kazandı."+ red.getIsim() +"'in puanı: "+ red.getPuan()+" " + black.getIsim()+"'nin puanı: "+ black.getPuan()); // bosluk stringlere puan sisteminin kurunca hallederim.
                         }
                     }

            }*/
            } else
                System.out.println("hatali hareket"); // sah mat olduktan sonra oyuna devam etmeye calisilir ise hatali hareket diyorum.
        }catch (Exception e){
            System.out.println("hatali hareket");
        }
    }

    @Override
    void load_text(String address) { // bir piyonu yutuyor aq
        try{
        board.clearboard();
        Scanner sc = null;
        try{
            sc = new Scanner(new FileInputStream(address));
        }catch (Exception e){

        }
        ArrayList<String> konumlar= new ArrayList<>();
        String line = "";
        boolean isSomeOnePresent = false;
        // reset positions of items.
        for( int n =0;n<board.getItems().length ;n++){
            if(board.getItems()[n].getPosition().equals("xx")==false) // if off game do not change.
             board.getItems()[n].setPosition("");
        } // strateji: yerleri bosalt. birine yaz. isimler ayni ise, objelerden "" olani ise yaz.
        for(int m=0;m<board.getItems().length ;m++) {
            line = sc.nextLine();
            konumlar.add(line);
            //System.out.println(line);
            //System.out.println(line);

        }//arraylistimdeki konumları itemler ile eslestirecegim
       // System.out.println("\n\n\n");
        for(int k=0;k<konumlar.size() ;k++) {
            for (int n = 0; n < board.getItems().length; n++) {
                if((konumlar.get(k).charAt(0)+"").equals(board.getItems()[n].getName())
                        && board.getItems()[n].getPosition().equals("xx")==false){
                    board.getItems()[n].setPosition(konumlar.get(k).substring(1));
                    //System.out.println(konumlar.get(k));
                    konumlar.remove(k); // remove that element so that i dont count again.
                }
                else konumlar.remove(k);
            }
        }
        board.placeItems();
       // for (int n = 0; n < board.getItems().length; n++) { // for debug purposes
        //    System.out.println(board.getItems()[n]);
        //     }
        // now ı need to get the names.
        sc.useDelimiter("isimBitti"); // isim hep tek olacak gibi dusundun yanlis. puani yazmaya gerek yok. cikar onu.
        red.setIsim(sc.next());
        //System.out.println(red.getIsim());
        black.setIsim(sc.next());
        this.SiraKimde = Integer.parseInt(sc.next().replaceAll(" ",""));
        //System.out.println("SIRA BUNDA::: "+ SiraKimde);
        //System.out.println(red.getIsim() );
        //System.out.println(black.getIsim());
        // following code updates the points.
        red.setPuan(0);
        black.setPuan(0);
        for(int n=0;n<board.getItems().length ;n++){ // add points

             //System.out.println(board.getItems()[n]);

            if(board.getItems()[n].getName().equals(board.getItems()[n].getName().toLowerCase())){ // tas buyuk oyuncunun. Eger position xx ise, yenmis demektir. dusman adama ekle bunu.
                if(board.getItems()[n].getPosition().equals("xx")){
                    if(board.getItems()[n] instanceof Fil){
                        black.add(((Fil)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof At){
                        black.add(((At)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof Kale){
                        black.add(((Kale)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof Piyon){
                        black.add(((Piyon)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof Vezir){
                        black.add(((Vezir)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof Sah){
                        black.add(((Sah)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof Top){
                        black.add(((Top)(board.getItems()[n])).puan);
                    }
                }
            }
            else{
                if(board.getItems()[n].getPosition().equals("xx")){
                    // System.out.println(board.getItems()[n].getName());
                    if(board.getItems()[n] instanceof Fil){
                        red.add(((Fil)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof At){
                        red.add(((At)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof Kale){
                        red.add(((Kale)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof Piyon){
                        red.add(((Piyon)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof Vezir){
                        red.add(((Vezir)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof Sah){
                        red.add(((Sah)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof Top){
                        red.add(((Top)(board.getItems()[n])).puan);
                    }
                }
            }
        }
        }catch (Exception e){

        }
    }
    @Override
    void load_binary(String address) { // en son hepsini hatali hareket try cahtchine al
        // puanları hesaplama tamam. konumları yazma tamam. Suanki sorun, oyuncuların isimlerini okumakta. hepsinden sorna
        //isim bitti diyorum ki bu dogru. Ancak sc.use delimiter tarzı bir sey olmadigi icin sorun cikiyoır.
        // cozum olarak line a hep ekle line eger isimBitti olarak sondan substringe ayrılabiliyor ise nitir. son kismi cikar.
        // sonraki isimi oku.
        // ona da aynısnı yap. sonra sirakimde yi al.
        try{
        board.clearboard();
        ArrayList<String> konumlar= new ArrayList<>();
        String line = "";
        // reset positions of items.
        for( int n =0;n<board.getItems().length ;n++){
            if(board.getItems()[n].getPosition().equals("xx")==false) // if off game do not change.
                board.getItems()[n].setPosition("");
        }
        ObjectInputStream i = null;
        try{
            i = new ObjectInputStream(new FileInputStream(address));
        }catch (Exception e){
        }
        for(int n=0;n<board.getItems().length ;n++){
            try{
                for(int m=0;m<3 ;m++) {

                    line += "" + i.readChar();
                }
                konumlar.add(line);
                line = "";
            }catch (Exception e){
            }
        }//arraylistimdeki konumları itemler ile eslestirecegim
        // arraylist dogru mu kontrolu:
      //  for(int n=0;n<konumlar.size() ;n++){
            //System.out.println(konumlar.get(n));
       // }
       // System.out.println("***** ARraylist üstteydi. altta basılacaklar item arayi");
        for(int k=0;k<konumlar.size() ;k++) {
            for (int n = 0; n < board.getItems().length; n++) {
                if((konumlar.get(k).charAt(0)+"").equals(board.getItems()[n].getName()) && board.getItems()[n].getPosition().equals("")
                        && board.getItems()[n].getPosition().equals("xx")==false){
                    board.getItems()[n].setPosition(konumlar.get(k).substring(1));
                 //   System.out.println(konumlar.get(k));
                    konumlar.remove(k); // remove that element so that i dont count again.
                }else konumlar.remove(k);
            }
        }
       // System.out.println("Place ediyoruım bianrdeyimn");
        board.placeItems();
      //  for (int n = 0; n < board.getItems().length; n++) {
      //      System.out.println(board.getItems()[n]);
       // }
        // setting points at the end.
        red.setPuan(0);
        black.setPuan(0);
        for(int n=0;n<board.getItems().length ;n++){ // add points
            //System.out.println(board.getItems()[n]);
            if(board.getItems()[n].getName().equals(board.getItems()[n].getName().toLowerCase())){ // tas buyuk oyuncunun. Eger position xx ise, yenmis demektir. dusman adama ekle bunu.
                if(board.getItems()[n].getPosition().equals("xx")){
                    if(board.getItems()[n] instanceof Fil){
                        black.add(((Fil)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof At){
                        black.add(((At)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof Kale){
                        black.add(((Kale)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof Piyon){
                        black.add(((Piyon)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof Vezir){
                        black.add(((Vezir)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof Sah){
                        black.add(((Sah)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof Top){
                        black.add(((Top)(board.getItems()[n])).puan);
                    }
                }
            }
            else{
                if(board.getItems()[n].getPosition().equals("xx")){
                    // System.out.println(board.getItems()[n].getName());
                    if(board.getItems()[n] instanceof Fil){
                        red.add(((Fil)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof At){
                        red.add(((At)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof Kale){
                        red.add(((Kale)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof Piyon){
                        red.add(((Piyon)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof Vezir){
                        red.add(((Vezir)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof Sah){
                        red.add(((Sah)(board.getItems()[n])).puan);
                    } else if(board.getItems()[n] instanceof Top){
                        red.add(((Top)(board.getItems()[n])).puan);
                    }
                }
            }
        }
        try{
           // System.out.println("Simdi isimleri sirakimde yi cekegeim");
            red.setIsim("sıfırlandı1");
            black.setIsim("sıfırlandı2");
            //System.out.println(red.getIsim()+"  "+black.getIsim());
            String isim1 = i.readUTF();
             red.setIsim(isim1.replaceAll("isimBitti",""));
           // System.out.println("*****");
            String isim2 = i.readUTF();
            black.setIsim(isim2.replace("isimBitti",""));
            //System.out.println("Abi ben hallettim 1.nin adı="+ red.getIsim()+"2.nin adı:"+ black.getIsim());
            this.SiraKimde = Integer.parseInt(i.readUTF());
            //System.out.println("SIRA BUNDA::::: "+ SiraKimde);
        }catch (Exception e){
        }
        try{
            i.close();
        }catch (Exception e){
        }
        }catch (Exception E){

        }
    }
    @Override
    void save_text(String address) {
        try{
        PrintWriter pw = null;
        try{
            pw=new PrintWriter(new FileOutputStream(address));
        }catch (Exception e){

        }// itemleri aralarında bosluk ile kaydet. isimposition seklinde. Sonra boslukl. sonrasında red puan1 black paun 2 de. Yani 32 tane item. var okurken unutma

        for(int n=0;n<board.getItems().length ;n++){
            pw.println(board.getItems()[n]);
        }
        pw.print(red.getIsim() +"isimBitti");
        pw.print(black.getIsim()+"isimBitti");
        pw.print(SiraKimde);
        pw.close();
    }catch (Exception e){

        }
    }
    @Override
    void save_binary(String address) {
        try{
        ObjectOutputStream o = null;
        try{
            o = new ObjectOutputStream(new FileOutputStream(address));
            }catch (Exception e){
            }
        for(int n=0;n<board.getItems().length ;n++){
            try {
                o.writeChar(board.getItems()[n].getName().charAt(0)); // all names consist of 1 letter.
                if(board.getItems()[n].getPosition().equals("xx")) {o.writeChar('x'); o.writeChar('x');}
                else { o.writeChar(board.getItems()[n].getPosition().charAt(0));
                    o.writeChar(board.getItems()[n].getPosition().charAt(1));
                    o.writeChar(board.getItems()[n].getPosition().charAt(2));
                }

            } catch ( Exception e){
            }
        }
        try{
            o.writeUTF(red.getIsim()+"isimBitti");
            o.writeUTF(black.getIsim()+ "isimBitti");
            o.writeUTF(""+SiraKimde);
        }catch (Exception e){
        }
        try {
            o.close();
        }catch (Exception e){
        }
    }catch (Exception e){

        }
    }


}
