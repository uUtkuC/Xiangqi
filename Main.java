import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game g = new Game("Gandalf the Grey", "Beethoven"); // A ve B adında iki oyuncu için oyunu başlatır.
        g.getBoard().print(); // tahtanın o anki anını tahtaya bastırır.




        // İKİ KERE HATALI HAREKET BAIYOR ÇÖZ
/*
        g.play("a1","b1");
        g.getBoard().print();
        g.play("j1","i1");
        g.getBoard().print();
        g.play("b1","b6");
        g.getBoard().print();
        g.play("h2","h5");
        g.getBoard().print();
        g.play("b6","j6");
        g.getBoard().print();
        g.play("i1","i2");
        g.getBoard().print();
        */


          // code to test via keyboard

        Scanner sc = new Scanner(System.in);
        System.out.println("You will play this game until you enter 00");
        System.out.println("valid input is entered in form: \na1 b1\nFor example this means: move k at a1 to b1");
            while (true) {
                try {
                    String input = sc.nextLine();
                    if (input.equals("00")) break;
                    g.play(input.substring(0, 2), input.substring(3, 5));

                    g.board.print();

                }catch (Exception e){
                    System.out.println("You probably misentered the command string.\nPlease try again");
                }
            }






        // betulden 2
/*
        g.getBoard().print();
        g.play("a5", "b5");
        g.play("g1", "f1");
        g.play("d1", "e1");
        g.play("j9", "i9");
        g.play("e1", "f1");
        g.play("j1", "f1");
        g.getBoard().print();
        g.play("b5", "c5");
        g.play("f1", "d1");
        g.play("a4", "b5");
        g.getBoard().print();
        g.play("g3", "f3");
        g.play("c2", "j2");
        g.getBoard().print();
        g.play("d1", "a1");
        g.getBoard().print();
        g.play("a9", "b9");
        g.getBoard().print();
        g.play("a1", "a2");
        g.getBoard().print();
        g.play("b9", "a9");
        g.getBoard().print();
        g.play("i9", "j9");
        g.getBoard().print();
        g.play("c8", "j8");
        g.getBoard().print();
        g.play("a2", "b2");
        g.getBoard().print();
        g.play("a7", "c9");
        g.getBoard().print();
        g.play("b2", "b5");
        g.getBoard().print();
        g.play("c5", "c6");
        g.getBoard().print();
        g.play("b5", "b3");
        g.getBoard().print();
        g.play("a3", "c5");
        g.getBoard().print();
        g.play("g9", "f9");
        g.getBoard().print();
        g.play("d3", "e3");
        g.getBoard().print();
        g.play("f9", "e9");
        g.play("d9", "e9");
        g.getBoard().print();
        g.play("j9", "e9");
        g.play("c9", "a7");
        g.getBoard().print();
        g.play("e9", "a9");
        g.getBoard().print();
        g.play("e3", "f3");
        g.getBoard().print();
        g.play("a9", "a8");
        g.play("f3", "g3");
        g.play("a8", "a7");
        g.getBoard().print();
        g.play("g3", "h3");
        g.play("a7", "a9");
        g.play("h3", "i3");
        g.getBoard().print();
        g.play("a9", "c9");
        g.getBoard().print();
        System.out.println( g.black.isim+" "+g.black.puan +"ikinci oyuncuydu. Ilk oyuncu:" +g.red.puan+" "+g.red.isim);*/
        //g.save_binary("betulden");
       // g.save_text("betulden.txt");
















        //betulden1
/*
        g.getBoard().print();
        g.play("c2", "j2");
        g.getBoard().print();
        g.play("j1", "i1");
        g.getBoard().print();
        g.play("c8", "j8");
        g.getBoard().print();
        g.play("j3", "h5");
        g.getBoard().print();
        g.play("g1", "f1");
        g.getBoard().print();
        g.play("d1", "e1");
        g.getBoard().print();
        g.play("f1", "e1");
        g.getBoard().print();
        g.play("a1", "e1");
        g.getBoard().print();
        g.play("g9", "f9");
        g.getBoard().print();
        g.play("e1", "i1");
        g.getBoard().print();
        g.play("f9","e9");
        g.getBoard().print();
        g.play("j2", "i2");
        g.getBoard().print();
        g.play("j4", "i5");
        g.getBoard().print();
        g.play("d9", "e9");
        g.getBoard().print();
        g.play("i5","h6");
        g.getBoard().print();
        g.play("i2", "j2");
        g.getBoard().print();
        g.play("j3", "h1");
        g.getBoard().print();
        g.play("i1", "h1");
        g.getBoard().print();
        g.play("j9", "e9");
        g.getBoard().print();
        g.play("a9", "e9");
        g.getBoard().print();
        g.play("g7", "f7");
        g.getBoard().print();
        g.play("h1", "i1");
        g.getBoard().print();
        g.play("f7", "e7");
        g.getBoard().print();
        g.play("e9", "i9");
        g.getBoard().print();
        g.play("e7", "d7");
        g.getBoard().print();
        g.play("i9","i3");
        g.getBoard().print();
        g.play("d7", "c7");
        g.getBoard().print();
        g.play("i3", "j3");
        g.getBoard().print();
        System.out.println( g.black.isim+" "+g.black.puan +"ikinci oyuncuydu. Ilk oyuncu:" +g.red.puan+" "+g.red.isim);

        System.out.println("RIFKIIIII");
        g.load_text("betulden.txt");
        g.board.print();
        g.play("g5","f5");        System.out.println( g.black.isim+" "+g.black.puan +"ikinci oyuncuydu. Ilk oyuncu:" +g.red.puan+" "+g.red.isim);
*/






/*
        System.out.println("red in adı:"+ g.red.getIsim()+ "blackninAdı:"+ g.black.getIsim());
        System.out.println("Puan red: "+g.red.getPuan()+ " Puan black: "+ g.black.getPuan() +" Sıra: ");
        g.load_text("save.txt");
        g.board.print();
        System.out.println("red in adı:"+ g.red.getIsim()+ "blackninAdı:"+ g.black.getIsim());
        System.out.println("Puan red: "+g.red.getPuan()+ " Puan black: "+ g.black.getPuan() +" Sıra: ");
        g.play("g1","f1");
        g.board.print();
        g.play("f1","g1");
        g.board.print();
        System.out.println("TRALALALA");
        g.save_binary("binarySave");
        g.load_binary("binarySave");
        g.getBoard().print();
        System.out.println("red in adı:"+ g.red.getIsim()+ "blackninAdı:"+ g.black.getIsim());
        System.out.println("Puan red: "+g.red.getPuan()+ " Puan black: "+ g.black.getPuan() +" Sıra: ");
*/
        // g.board.print();
        // ahmetten tets case 2
/*
        g.play("d5","e5");
        g.getBoard().print();
        g.play("g5","f5");
        g.getBoard().print();
        g.play("e5","f5");
        g.getBoard().print();
        g.play("h2","i2");
        g.getBoard().print();
        g.play("f5","f6");
        g.getBoard().print();
        g.play("a8","c7");
        g.getBoard().print();
        g.play("j1","i1");
        g.getBoard().print();
        g.play("c7","d5");
        g.getBoard().print();
        g.play("g9","f9");
        g.getBoard().print();
        g.play("d5","f4");
        g.getBoard().print();
        g.play("i2","i3");
        g.getBoard().print();
        System.out.println("red in adı:"+ g.red.getIsim()+ "blackninAdı:"+ g.black.getIsim());
        System.out.println("Puan red: "+g.red.getPuan()+ " Puan black: "+ g.black.getPuan() +" Sıra: ");
        System.out.println("KAYIT EDILIYORRRR");
        g.save_binary("binarySave");
        g.getBoard().print();
        System.out.println("YUKLENIYORRR");
        g.load_binary("binarySave");
        System.out.println("red in adı:"+ g.red.getIsim()+ "blackninAdı:"+ g.black.getIsim());
        System.out.println("Puan red: "+g.red.getPuan()+ " Puan black: "+ g.black.getPuan() +" Sıra: ");
        g.play("f4","h5");
        g.getBoard().print();
        g.play("g3","f3");
        g.getBoard().print();
        g.play("f5","f6");
        g.getBoard().print();
        g.play("g1","f1");
        g.getBoard().print();
        g.play("a1","b1");
        g.getBoard().print();
        g.play("f1","e1");
        g.getBoard().print();
        g.play("b1","b5");
        g.getBoard().print();
        g.play("e1","d1");
        g.getBoard().print();
        g.play("b5","d5");
        g.getBoard().print();
        g.play("f9","e9");
        g.getBoard().print();
        g.play("c2","c5");
        g.getBoard().print();
        g.play("j8","h7");
        g.getBoard().print();
        g.play("h5","i3");
        g.getBoard().print();
        System.out.println("red in adı:"+ g.red.getIsim()+ "blackninAdı:"+ g.black.getIsim());
        System.out.println("Puan red: "+g.red.getPuan()+ " Puan black: "+ g.black.getPuan() +" Sıra: ");
        System.out.println("KAYIT EDILIYORRRR");
        g.save_binary("binarySave");
        g.getBoard().print();
        System.out.println("YUKLENIYORRR");
        g.load_binary("binarySave");
        System.out.println("red in adı:"+ g.red.getIsim()+ "blackninAdı:"+ g.black.getIsim());
        System.out.println("Puan red: "+g.red.getPuan()+ " Puan black: "+ g.black.getPuan() +" Sıra: ");
        g.getBoard().print();
        g.play("e9","d9"); //hata alınmalı oyun bitmiş ve şah çekilmiş
        g.getBoard().print();
*/



        // Ahmetten test 1
/*
        g.play("a6","b5");
        g.getBoard().print();
        g.play("g9","f9");
        g.getBoard().print();
        g.play("b5","c4");
        g.getBoard().print();
        g.play("g1","f1");
        g.getBoard().print();
        g.play("a1","b1");
        g.getBoard().print();
        g.play("g3","f3");
        g.getBoard().print();
        g.play("b1","b5");
        g.getBoard().print();
        g.play("g5","f5");
        g.getBoard().print();
        g.play("a5","a6");
        g.getBoard().print();
        g.play("g7","f7");
        g.getBoard().print();
        g.play("b5","a5");
        g.getBoard().print();
        g.play("f1","e1");
        g.getBoard().print();
        g.play("c8","c6");
        g.getBoard().print();
        g.play("h2","h6"); //şah çekildi sadece topun zıplaması gereken taş kurtarabşlir
        g.getBoard().print();

        System.out.println("KAYIT EDILIYORRRR");
        g.save_binary("binarySave");
        g.getBoard().print();
        System.out.println("YUKLENIYORRR");
        g.load_binary("binarySave");
        g.getBoard().print();
        g.play("c6","c5");
        g.getBoard().print();
*/








        // test regarding Sahs Can not face each other. piyon, top movements. Perfect
/*
        g.play("j1","g1");
        g.getBoard().print();
        g.play("a1","b1");
        g.getBoard().print();

/*
        g.play("g1","f1");
        g.getBoard().print();
        g.play("c2","f2");
        g.getBoard().print();
        g.play("f1","e1");
        g.getBoard().print();
        g.play("f2","f3");
        g.getBoard().print();
        g.play("e1","d1");
        g.getBoard().print();
        g.play("c8","f8");
        g.getBoard().print();
        g.play("g3","f3");
        g.getBoard().print();*/

        // load game test
/*
        System.out.println("red in adı:"+ g.red.getIsim()+ "blackninAdı:"+ g.black.getIsim());
        System.out.println("Puan red: "+g.red.getPuan()+ " Puan black: "+ g.black.getPuan());
        g.load_text("save.txt");
        g.getBoard().print();
        System.out.println("red in adı:"+ g.red.getIsim()+ "blackninAdı:"+ g.black.getIsim());
        System.out.println("Puan red: "+g.red.getPuan()+ " Puan black: "+ g.black.getPuan());
        g.load_binary("savebinary.txt");
        g.getBoard().print();
        System.out.println("red in adı:"+ g.red.getIsim()+ "blackninAdı:"+ g.black.getIsim());
        System.out.println("Puan red: "+g.red.getPuan()+ " Puan black: "+ g.black.getPuan());
*/
//Sah mat perfect
/*
        g.play("a3","c1");
        g.getBoard().print();
        g.play("g5","f5");
        g.getBoard().print();
        g.play("a1","b1");
        g.getBoard().print();
        g.play("f5","e5");
        g.getBoard().print();
        g.play("b1","a1");
        g.getBoard().print();
        g.play("e5","d5");
        g.getBoard().print();
        g.play("a5","b5");
        //g.getBoard().print();
       //g.play("e5","d5");
       // g.getBoard().print();
      //  System.out.println("KAYIT EDILIYORRRR");
      ///  System.out.println("Puan red: "+g.red.getPuan()+ " Puan black: "+ g.black.getPuan());
      //  g.save_text("save.txt");
       // g.getBoard().print();
      //  System.out.println("YUKLENIYORRR");
      //  System.out.println("Txtden load ettimm hata var mı asagya bak");
      //  System.out.println("Puan red: "+ g.red.getIsim()+" "+g.red.getPuan()+" "+ g.black.getIsim()+ " Puan black: "+ g.black.getPuan());
       // System.out.println("Puan red: "+g.red.getPuan()+ " Puan black: "+ g.black.getPuan());
       //g.load_text("save.txt");
       // System.out.println("Texten okudum dogru mu okudum");
       // System.out.println("Puan red: "+ g.red.getIsim()+" "+g.red.getPuan()+" "+ g.black.getIsim()+ " Puan black: "+ g.black.getPuan());
        g.getBoard().print();
        g.play("j1","i1");
        g.getBoard().print();
        g.play("b5","b6");
        g.getBoard().print();
        g.play("i1","j1");
        g.getBoard().print();
        g.play("a1","b1");
        g.getBoard().print();
        g.play("d5","d6");

        g.getBoard().print();
        g.play("b1","a1");
        g.getBoard().print();
        g.play("j1","i1");
        g.getBoard().print();
        System.out.println("\n\n\n\n12121212");
        g.play("b6","b5");
        g.getBoard().print();

        g.play("c2","c5");
        g.getBoard().print();
        g.play("i1","j1");
        g.getBoard().print();
        g.play("c5","c6");

        g.getBoard().print();
        g.play("j1","i1");
        g.getBoard().print();
        g.play("d1","e1");
        g.getBoard().print();
        g.play("d6","d5");
        g.getBoard().print();
        g.play("b6","b5");
        g.getBoard().print();
        g.play("i1","i5");
        g.getBoard().print();
        g.play("c6","c5");
        g.getBoard().print();
        g.play("d5","d6");
        g.getBoard().print();
        g.play("h8","h5");
        g.getBoard().print();
        g.play("e1","f1");
        g.getBoard().print();
        g.play("d5","d6");
        g.getBoard().print();

        g.play("c5","c6");
        g.getBoard().print();
        g.play("h5","h6");
        g.getBoard().print();
        g.play("b5","b6");
        g.getBoard().print();

        g.play("i5","a5"); // hata alıyorum.
        g.getBoard().print();
        g.play("a1","b1");
        g.getBoard().print();
        g.play("a5","a4");
        g.getBoard().print();
        g.play("b1","a1");
        g.getBoard().print();
        g.play("a4","b4");
        g.getBoard().print();
        g.play("a6","b5");
        g.getBoard().print();
        g.play("b4","b5");
        g.getBoard().print();
        g.play("b6","a6");
        g.getBoard().print();
        g.play("b5","a5");
        g.getBoard().print();
        g.play("a6","b6");
        g.getBoard().print();
        g.play("a5","a4");
        g.getBoard().print();
        g.play("a9","b9");
        g.getBoard().print();
        g.play("a4","b4");
        g.getBoard().print();
        g.play("b6","a6");
        g.getBoard().print();
        g.play("h6","h7");
        g.getBoard().print();
        g.play("b9","a9");
        g.getBoard().print();
        g.play("d6","c6");
        g.getBoard().print();
        g.play("a9","b9");
        g.getBoard().print();
        g.play("b4","a4");
        g.getBoard().print();
        System.out.println("HAREKETLER TAMAM OYUNA BITITIOO\n\n");
        g.play("a1","a2");
        g.save_text("save.txt");
        System.out.println("Puan red: "+g.red.getPuan()+ " Puan black: "+ g.black.getPuan());
        g.getBoard().print();
        System.out.println("Puan red: "+g.red.getPuan()+ " Puan black: "+ g.black.getPuan());
        g.load_text("save.txt");
        g.getBoard().print();
        System.out.println("********************\n\n");
        g.save_binary("savebinary.txt");
        System.out.println("Puan red: "+g.red.getPuan()+ " Puan black: "+ g.black.getPuan());
        g.getBoard().print();
        g.load_binary("savebinary.txt");// load edince puanlari farkli geliyor. Neden?
        System.out.println("Puan red: "+g.red.getPuan()+ " Puan black: "+ g.black.getPuan());
        g.getBoard().print();
        System.out.println("Puan red: "+g.red.getPuan()+ " Puan black: "+ g.black.getPuan());
        */


        //System.out.println("AHMET NURI AKAYAYAYA\n\n\n")
        //g.play("a6","b6");
        //g.getBoard().print();
        //g.play("a6","a5");
        //g.getBoard().print();*/
        // test cases for at Upper works fine
        /*
        g.play("j2","i4");
        g.getBoard().print();
        g.play("j2","h3");
        g.getBoard().print();
        g.play("h3","i5");
        g.getBoard().print();
        g.play("i5","g4");
        g.getBoard().print();
        */

        //test cases for lower Top
        /*
        g.play("c2","f2");
        g.getBoard().print();
        g.play("f2","f1");
        g.getBoard().print();
        g.play("f1","j1");
        g.getBoard().print();
        g.play("j1","j2");
        g.getBoard().print();
        g.play("j1","j4");
        g.getBoard().print();
        g.play("j1","j3");
        g.getBoard().print();
*/
        // test cases for Upper Top
/*
        g.play("h2","i2");
        g.getBoard().print();
        g.play("i2","i1");
        g.getBoard().print();
        g.play("i1","i2");
        g.getBoard().print();
        g.play("i2","i8");
        g.getBoard().print();
        g.play("i8","c8");
        g.getBoard().print();
        g.play("h8","a8");
        g.getBoard().print();
        g.play("a8","a6");
        g.getBoard().print();
        g.play("a6","a9");
        g.getBoard().print();
        g.play("a9","a7");
        g.getBoard().print();
        g.play("a9","a4");
        g.getBoard().print();
        g.play("a9","a5");
        g.getBoard().print();
        g.play("a5","b4");
        g.getBoard().print();
       // g.getBoard().print();

 */

        // test cases for Down Sah

        /*
        g.play("a5","b5");
        g.getBoard().print();
        g.play("b5","b4");
        g.getBoard().print();
        g.play("b4","c4");
        g.getBoard().print();
        g.play("c4","c3");
        g.getBoard().print();
        g.play("c4","c5");
*/
        // test cases for Up Sah
        /*
        g.play("j5","i5");
        g.getBoard().print();
        g.play("i5","h4");
        g.getBoard().print();
        g.play("i5","i4");
        g.getBoard().print();
        g.play("i4","h4");
        g.getBoard().print();
        g.play("h4","h5");
        g.getBoard().print();
        g.play("h5","i5");
        g.getBoard().print();
        g.play("i5","h4");
        g.getBoard().print();
*/
        // additional test case for vezier down left
/*
        g.play("a1","b1");
        g.getBoard().print();
        g.play("b1","b2");
        g.getBoard().print();
        g.play("a3","c1");
*/

        /*// test case for vezier down left

        g.play("a3","c1");
        g.getBoard().print();
        g.play("c1","e3");
        g.getBoard().print();
        g.play("e3","c5");
        g.getBoard().print();
        g.play("c5","e7");
        g.getBoard().print();

        // test cases for left fil Up
        *//*
        g.play("j3","h1");
        g.getBoard().print();
        g.play("h1","f3");
        g.getBoard().print();
        g.play("f3","h5");
        g.getBoard().print();
        g.play("h5","f7");
        g.getBoard().print();
        g.play("f7","h9");
        g.getBoard().print();
        g.play("h9","h7");
        g.getBoard().print();
*/
        // test cases for vezier down left
        /*
        g.play("a4","b5");
        g.getBoard().print();
        g.play("b5","a6");
        g.getBoard().print();
        g.play("b5","c4");
        g.getBoard().print();
        g.play("c4","b5");
        g.getBoard().print();
        g.play("b5","c6");
        g.getBoard().print();
        g.play("c6","i5");
        g.getBoard().print();
*/

        // test cases for vezier Up right
        /*
        g.play("j6","i5");
        g.getBoard().print();
        g.play("i5","h6");
        g.getBoard().print();
        g.play("h6","i5");
        g.getBoard().print();
        g.play("i5","h4");
        g.getBoard().print();
        g.play("h4","g3");
        g.getBoard().print();
        g.play("h4","g4");
        g.getBoard().print();
*/

       // test case for west vezier
        /*
        g.play("j4","i5");
        g.getBoard().print();
        g.play("i5","j6");
        g.getBoard().print();
        g.play("i5","h4");
        g.getBoard().print();
        g.play("h4","i5");
        g.getBoard().print();
        g.play("i5","h6");
        g.getBoard().print();
        g.play("h6","i5");
        g.getBoard().print();
        */
        // Test case for Büyük piyon
        /*g.play("g1","f1");
        System.out.println();
        g.getBoard().print();


        g.play("f1","e1");
        System.out.println();
        g.getBoard().print();

        g.play("e1","d1");
        System.out.println();
        g.getBoard().print();

        g.play("d1","d2");
        System.out.println();
        g.getBoard().print();
        g.play("d2","d1");
        System.out.println();
        g.getBoard().print();
        g.play("d1","d2");
        System.out.println();
        g.getBoard().print();
        g.play("d2","d3");
        System.out.println();
        g.getBoard().print();
        g.play("d3","e3");
        System.out.println();
        g.getBoard().print();
*/

        /*System.out.println("Ahmet SPor");
        g.play("d1","d2");
        System.out.println();
        g.getBoard().print();
*/

        /* g.play("d1","e1");
        System.out.println();
        g.getBoard().print();


        g.play("e1","f1");
        System.out.println();
        g.getBoard().print();

        g.play("f1","f2");
        System.out.println();
        g.getBoard().print();
        System.out.println("Ahmet SPor");
        g.play("f2","f1");
        System.out.println();
        g.getBoard().print();*/












        /*
        g.play("j1","i1"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("i1","i4"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("i4","b4"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("b4","b2"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("b2","c2"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        */
        // Test case for kucuk pyion. Sorunsuz calisiyor
/*
       // g.getBoard().print(); // tahtanın o anki anını tahtaya bastırır.
        g.play("d1","e1"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("e1","f1"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("f1","g1"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("g1","h1"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("h1","h2"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("h2","h1"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("h1","h2"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("h2","h3");
        g.getBoard().print();
        g.play("h3","h2");
        g.getBoard().print();
        g.play("h2","g2");
*/


/*
        g.play("g1","f1"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("f1","e1"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("e1","d1"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("d1","c1"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("c1","b1"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("g5","f5"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("f5","e5"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("e5","d5"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("d5","c5"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("c5","b5"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("b5","b4"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("b1","b2");
        g.getBoard().print();
        g.play("b2","b3");
        g.getBoard().print();
        g.play("b5","b6");
        g.getBoard().print();
        g.play("b6","b5");
        g.getBoard().print();
        g.play("d3","e3");
        g.getBoard().print();
        g.play("e3","f3");
        g.getBoard().print();
        g.play("f3","f4");
        g.getBoard().print();
        g.play("f4","f5");
        g.getBoard().print();
        g.play("b5","b6");
        g.getBoard().print();
*/



       // g.play("i1","i2");

        // test regarding the small k
        /*
        g.play("a1","b1"); // a1’deki taşı c1’e taşır.
        g.getBoard().print();
        g.play("b1","b2");
        g.getBoard().print();
        g.play("b2","b4");
        g.getBoard().print();
        g.play("b3","b4");
        g.getBoard().print();
        g.play("b4","j4");
        g.getBoard().print();
        g.play("j1","j2");
        g.getBoard().print();
        g.play("j4","j3");
        g.getBoard().print();
        g.play("j3","j5");
        g.getBoard().print();
        g.play("j5","g5");
        g.getBoard().print();
        g.play("j1","i1"); // buyuk k
        g.getBoard().print();

        g.play("i1","i2");
        g.getBoard().print();
*/

        //g.play("c3","c2");
        //g.getBoard().print();
       // g.play("","");
      //  g.save_binary("a"); // oyunun o halini kaydeder.
       // g.load_binary("a"); // kayıt edilen oyunu yükler
        //g.play("c1", "d1"); // hata mesajı vermesi lazım çünkü aynı oyuncu iki kez üst üste oynayamaz



    }
}
