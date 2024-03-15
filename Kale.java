public class Kale extends  Item{
    // 9
    public Kale(String s, String posit){
        name = s;
        this.setPosition(posit);
    }

    Board board;
    public void setboard(Board board){
        this.board = board;
    }
    boolean Valid = false;
    public boolean isMoveValid(){
        return Valid;
    }
    public void resetIsMoveValid(){
        Valid = false;
    }
    public Kale cloneMe(){
        Kale itemo = new Kale(name,getPosition());
        return itemo;

    }
    int puan = 9;
    @Override
    public void move(String destination) {
        super.move(destination);
        boolean isThereAPieceBetweenTargetAndSelf = false; // ONEMLİ. BUNU IMPLEMENT ETMEDIGIN ICIN HATALI OLUYOR
        // check if uppercase or not. // if river is passed can move right left forward.
        String from = getPosition();
        String to = destination;
        int itemArrayIndex = -1;
        for (int n = 0; n < board.getItems().length; n++) {
            if (board.getItems()[n].getPosition().equals(from)) {
                itemArrayIndex = n;
                //System.out.println("n suan bu:" + itemArrayIndex);
                break; // break is to make iteration faster.
            }
        }
        // arada tas varken de yedi kontrolu yap şimdi. Önemli.
        if (to.charAt(0) == from.charAt(0)) { // yatay hareket
            if (Integer.parseInt(from.substring(1)) == Integer.parseInt(to.substring(1)))
            {} // System.out.println("hatali hareket"); //System.out.println("ILLEGAL MOVE. YOU CAN'T STAY AT THE SAME PLACE");
                // now I need check if there is a peice blocking its movement.
            else if (Integer.parseInt(from.substring(1)) > Integer.parseInt(to.substring(1))) { // It is going west
                for (int n = 0; n < board.getItems().length; n++) {
                    if (board.getItems()[n].getPosition().equals("xx")==false && n != itemArrayIndex && Integer.parseInt(board.getItems()[n].getPosition().substring(1)) < Integer.parseInt(from.substring(1)) &&
                            Integer.parseInt(board.getItems()[n].getPosition().substring(1))> Integer.parseInt(to.substring(1))
                            && board.getItems()[n].getPosition().charAt(0)== to.charAt(0)) {
                       // System.out.println("hatali hareket");//System.out.println("Illegal MOVE");
                        return; // ends method
                    }
                } for (int n = 0; n < board.getItems().length; n++) { // if both capital cant eat. if both low case cant eat. // first i check wether both items are upper case. then i check whether they are both lower case
                    if((to.equals(board.getItems()[n].getPosition()))) if(board.getItems()[n].getPosition().equals("xx")==false &&(((board.getItems()[itemArrayIndex].getName().toUpperCase().equals(board.getItems()[itemArrayIndex].getName()))
                            && (board.getItems()[n].getName().equals(board.getItems()[n].getName().toUpperCase()))) == false) && (((board.getItems()[itemArrayIndex].getName().toLowerCase().equals(board.getItems()[itemArrayIndex].getName()))
                            && (board.getItems()[n].getName().equals(board.getItems()[n].getName().toLowerCase()))) == false)){

                        for(int m=0; m<board.getItems().length; m++){
                            if(board.getItems()[m].getPosition().equals("xx")==false &&Integer.parseInt(board.getItems()[m].getPosition().substring(1))<Integer.parseInt(to.substring(1))
                                    &&Integer.parseInt(board.getItems()[m].getPosition().substring(1))>Integer.parseInt(from.substring(1))
                                    && board.getItems()[m].getPosition().charAt(0) == to.charAt(0))
                                isThereAPieceBetweenTargetAndSelf = true;
                        }

                        if(isThereAPieceBetweenTargetAndSelf == false)
                        board.tasYe(from,to);
                    }
                    else{//System.out.println("hatali hareket");//System.out.println("ILLEGAL MOVE CANT EAT YOUR OWN PIECE");
                        return;}
                }
                if(isThereAPieceBetweenTargetAndSelf == false){
                board.getItems()[itemArrayIndex].setPosition(to);
                board.emptyGivenIndex(from, to); Valid = true;
            }}
            else if(Integer.parseInt(from.substring(1)) < Integer.parseInt(to.substring(1))){ // goes east
                for (int n = 0; n < board.getItems().length; n++) {
                    if ( board.getItems()[n].getPosition().equals("xx")==false && n != itemArrayIndex && Integer.parseInt(board.getItems()[n].getPosition().substring(1)) > Integer.parseInt(from.substring(1))
                            && Integer.parseInt(board.getItems()[n].getPosition().substring(1)) < Integer.parseInt(to.substring(1)) && board.getItems()[n].getPosition().charAt(0) == to.charAt(0)) {
                        //System.out.println("hatali hareket");//System.out.println("Illegal MOVE");
                        return; // ends method
                    }
                }
                for (int n = 0; n < board.getItems().length; n++) { // if both capital cant eat. if both low case cant eat. // first i check wether both items are upper case. then i check whether they are both lower case
                    if((to.equals(board.getItems()[n].getPosition()))) if(board.getItems()[n].getPosition().equals("xx")==false &&(((board.getItems()[itemArrayIndex].getName().toUpperCase().equals(board.getItems()[itemArrayIndex].getName()))
                            && (board.getItems()[n].getName().equals(board.getItems()[n].getName().toUpperCase()))) == false) && (((board.getItems()[itemArrayIndex].getName().toLowerCase().equals(board.getItems()[itemArrayIndex].getName()))
                            && (board.getItems()[n].getName().equals(board.getItems()[n].getName().toLowerCase()))) == false)){
                        for(int m=0; m<board.getItems().length; m++){
                            if(board.getItems()[m].getPosition().equals("xx")==false &&Integer.parseInt(board.getItems()[m].getPosition().substring(1))<Integer.parseInt(to.substring(1))
                                    &&Integer.parseInt(board.getItems()[m].getPosition().substring(1))>Integer.parseInt(from.substring(1))
                                    && board.getItems()[m].getPosition().charAt(0) == to.charAt(0))
                                isThereAPieceBetweenTargetAndSelf = true;
                        }
                        if(isThereAPieceBetweenTargetAndSelf == false)
                        board.tasYe(from,to);}
                    else{//System.out.println("hatali hareket");//System.out.println("ILLEGAL MOVE CANT EAT YOUR OWN PIECE");
                        return;}
                }
                if(isThereAPieceBetweenTargetAndSelf == false){
                board.getItems()[itemArrayIndex].setPosition(to);
                board.emptyGivenIndex(from,to);Valid = true;}
            }
        } else if (board.getItems()[itemArrayIndex].getPosition().equals("xx")==false &&Integer.parseInt(from.substring(1)) == Integer.parseInt(to.substring(1))) { // same y axis.
            if(from.charAt(0)> to.charAt(0)){ // piece should go down

                for (int n = 0; n < board.getItems().length; n++) {
                    if (board.getItems()[n].getPosition().equals("xx")==false && n != itemArrayIndex && board.getItems()[n].getPosition().charAt(0)< from.charAt(0)
                            && Integer.parseInt(board.getItems()[n].getPosition().substring(1)) == Integer.parseInt(from.substring(1))
                            && board.getItems()[n].getPosition().charAt(0)> to.charAt(0)) {
                        //System.out.println("hatali hareket");//System.out.println("Illegal MOVE");
                        return;
                    } // if it continues then there is no illegal move.
                }for (int n = 0; n < board.getItems().length; n++) { // if both capital cant eat. if both low case cant eat. // first i check wether both items are upper case. then i check whether they are both lower case
                    if((to.equals(board.getItems()[n].getPosition()))) if(board.getItems()[n].getPosition().equals("xx")==false &&(((board.getItems()[itemArrayIndex].getName().toUpperCase().equals(board.getItems()[itemArrayIndex].getName()))
                            && (board.getItems()[n].getName().equals(board.getItems()[n].getName().toUpperCase()))) == false) && (((board.getItems()[itemArrayIndex].getName().toLowerCase().equals(board.getItems()[itemArrayIndex].getName()))
                            && (board.getItems()[n].getName().equals(board.getItems()[n].getName().toLowerCase()))) == false)){
                        for(int m=0; m<board.getItems().length; m++){
                            if(board.getItems()[m].getPosition().equals("xx")==false &&Integer.parseInt(board.getItems()[m].getPosition().substring(1))==Integer.parseInt(to.substring(1))
                                    &&Integer.parseInt(board.getItems()[m].getPosition().substring(1))==Integer.parseInt(from.substring(1))
                                    && board.getItems()[m].getPosition().charAt(0) > to.charAt(0) & board.getItems()[m].getPosition().charAt(0) < from.charAt(0))

                                isThereAPieceBetweenTargetAndSelf = true;
                        }
                        if(isThereAPieceBetweenTargetAndSelf==false)
                        board.tasYe(from,to);}
                    else{///System.out.println("hatali hareket");//System.out.println("ILLEGAL MOVE CANT EAT YOUR OWN PIECE");
                        return;}
                }
                if(isThereAPieceBetweenTargetAndSelf==false){
                board.getItems()[itemArrayIndex].setPosition(to);
                board.emptyGivenIndex(from,to); Valid = true;}}
            else if(from.charAt(0)<to.charAt(0)){ // piece is going up

                for (int n = 0; n < board.getItems().length; n++) {
                    if (board.getItems()[n].getPosition().equals("xx")==false &&n != itemArrayIndex && board.getItems()[n].getPosition().charAt(0)> from.charAt(0) &&
                            Integer.parseInt(board.getItems()[n].getPosition().substring(1)) == Integer.parseInt(from.substring(1))
                            && board.getItems()[n].getPosition().charAt(0)<to.charAt(0)) {
                        //System.out.println("hatali hareket");//System.out.println("Illegal MOVE");
                        return;
                    } // if it continues then there is no illegal move.
                }for (int n = 0; n < board.getItems().length; n++) { // if both capital cant eat. if both low case cant eat. // first i check wether both items are upper case. then i check whether they are both lower case
                    if((to.equals(board.getItems()[n].getPosition()))) if(board.getItems()[n].getPosition().equals("xx")==false &&(((board.getItems()[itemArrayIndex].getName().toUpperCase().equals(board.getItems()[itemArrayIndex].getName()))
                            && (board.getItems()[n].getName().equals(board.getItems()[n].getName().toUpperCase()))) == false) && (((board.getItems()[itemArrayIndex].getName().toLowerCase().equals(board.getItems()[itemArrayIndex].getName()))
                            && (board.getItems()[n].getName().equals(board.getItems()[n].getName().toLowerCase()))) == false)){
                        for(int m=0; m<board.getItems().length; m++){
                            if(board.getItems()[m].getPosition().equals("xx")==false &&Integer.parseInt(board.getItems()[m].getPosition().substring(1))==Integer.parseInt(to.substring(1))
                                    &&Integer.parseInt(board.getItems()[m].getPosition().substring(1))==Integer.parseInt(from.substring(1))
                                    && board.getItems()[m].getPosition().charAt(0) < to.charAt(0) & board.getItems()[m].getPosition().charAt(0) > from.charAt(0))
                                isThereAPieceBetweenTargetAndSelf = true;
                        }
                        if(isThereAPieceBetweenTargetAndSelf==false)
                        board.tasYe(from,to); /*System.out.println("Girdi");*/}
                    else{ //System.out.println("hatali hareket");//System.out.println("ILLEGAL MOVE CANT EAT YOUR OWN PIECE");
                        return;}
                }
                if(isThereAPieceBetweenTargetAndSelf== false){
                board.getItems()[itemArrayIndex].setPosition(to);
                board.emptyGivenIndex(from,to); Valid = true;}}// else they are equal.
            else {} //System.out.println("hatali hareket");//System.out.println("ILLEGAL MOVE. YOU CAN'T STAY AT THE SAME PLACE");


        } else {
            //System.out.println("hatali hareket");//System.out.println("ILLEGAL MOVE");
        }

    }
}