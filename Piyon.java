public class Piyon extends Item{
    // 1 before crossing river, 2 after crossing river
    // before corssing can only capture front
    // after crossing can go left right forward
    // cant upgrade to anything
    public Piyon(String s, String posit){
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
    public Piyon cloneMe(){
        Piyon itemo = new Piyon(name,getPosition());
        return itemo;

    }

    int puan = 1;
    @Override
    public void move(String destination) {
        super.move(destination);
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
        // check if uppercase or not. // if river is passed can move right left forward.
            if (board.getItems()[itemArrayIndex].getName().equals(board.getItems()[itemArrayIndex].getName().toUpperCase())) {
                // then this piece belong to the upper player
                boolean isDost = false; boolean isEmpty = true;
                if(from.charAt(0)+1== to.charAt(0) ){
                   // System.out.println("hatali hareket");// System.out.println(" CAN NOT GO BACKWARS"); return;
                }
                // System.out.println("sWAOW");
                if ((to.charAt(0) != from.charAt(0)) && Math.abs(to.charAt(0)-from.charAt(0))==1 && Math.abs(Integer.parseInt(to.substring(1)) - Integer.parseInt(from.substring(1)))==0) {// y axis movement
                    //System.out.println("Skao");
                    if (from.charAt(0) > 'e') /* kopruyu gecemedi*/ {// System.out.println("sgfssgsg");
                        if (from.charAt(0) > to.charAt(0)) {
                            for (int n = 0; n < board.getItems().length; n++) {
                                if (board.getItems()[n].getPosition().equals(to)) {
                                    isEmpty = false;
                                }
                                if (board.getItems()[n].getPosition().equals(to) && board.getItems()[n].getName().equals(board.getItems()[n].getName().toUpperCase())) {
                                    isDost = true;
                                }
                            }   // if yes then this is friendly
                            if (isDost == true) {
                                //System.out.println("hatali hareket");//  System.out.println("ILLEGAL MOVE. FRIENDLY PIECE AHEAD");
                                return;
                            } else if (isDost == false && isEmpty == false) {
                                board.tasYe(from, to);
                                // System.out.println("Yedim abi ");
                            }
                            board.getItems()[itemArrayIndex].setPosition(to);
                            board.emptyGivenIndex(from,to);Valid = true;
                            // System.out.println("Burada from:" + from + " to:" + to);
                        }} // correct
                    else if (from.charAt(0) <= 'e') { // kopruyu gecti
                        // gidilecek yerde dost tas varsa gitme. Dusman tas varsa ye. Yoksa git
                        //System.out.println("AHMETA2");
                        for (int n = 0; n < board.getItems().length; n++) {
                            if (board.getItems()[n].getPosition().equals(to)) {
                                isEmpty = false;
                            }
                            if (board.getItems()[n].getPosition().equals(to) && board.getItems()[n].getName().equals(board.getItems()[n].getName().toUpperCase())) {
                                isDost = true; break;
                            }
                        }   // if yes then this is friendly
                        if (isDost == true) {
                           // System.out.println("hatali hareket");// System.out.println("ILLEGAL MOVE. FRIEDNLY PIECE AHEAD");
                            return;
                        } else if (isDost == false && isEmpty == false) {
                            board.tasYe(from, to);
                        }
                        // if it hasn't entered the if clause then the place is empty
                        board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to);Valid = true;
                    }
                    else {
                        //System.out.println("hatali hareket");// System.out.println("Cant move backwards");
                        return;
                    }
                } else if(to.charAt(0) == from.charAt(0) && Math.abs(Integer.parseInt(to.substring(1)) - Integer.parseInt(from.substring(1)))==1 && from.charAt(0)<='e'){ // is moving side ways
                    //System.out.println("İsmet");
                    if(Integer.parseInt(to.substring(1)) > Integer.parseInt(from.substring(1)) == true && 9>=Integer.parseInt(to.substring(1))  ){ // east e gider
                        for (int n = 0; n < board.getItems().length; n++) {
                            if (board.getItems()[n].getPosition().equals(to)) {
                                isEmpty = false;
                            }
                            if (board.getItems()[n].getPosition().equals(to) && board.getItems()[n].getName().equals(board.getItems()[n].getName().toUpperCase())) {
                                isDost = true;
                            }}if (isDost == true) {
                           // System.out.println("hatali hareket");//  System.out.println("ILLEGAL MOVE. FRIENDLY PIECE AHEAD");
                            return;
                        } else if (isDost == false && isEmpty == false) {
                            board.tasYe(from, to);
                            //System.out.println("Yedim abi ");
                        }
                        board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to);Valid = true;
                    } // check wether it is 0< or 1< from.substring(1)
                    else if(Integer.parseInt(to.substring(1)) > Integer.parseInt(from.substring(1)) == false && 0<Integer.parseInt(from.substring(1) ) && from.charAt(0)<='e'){ //goes west
                       // System.out.println("Kismet");
                        for (int n = 0; n < board.getItems().length; n++) {
                            if (board.getItems()[n].getPosition().equals(to)) {
                                isEmpty = false;
                            }
                            if (board.getItems()[n].getPosition().equals(to) && board.getItems()[n].getName().equals(board.getItems()[n].getName().toUpperCase())) {
                                isDost = true;
                            }}
                        if (isDost == true) {
                           // System.out.println("hatali hareket");// System.out.println("ILLEGAL MOVE. FRIENDLY PIECE AHEAD");
                            return;
                        }
                        else if (isDost == false && isEmpty == false) {
                            board.tasYe(from, to);
                        }
                        board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to);Valid = true;

                    }
                    else{  //System.out.println("hatali hareket");// System.out.println("Trying to go to the edge. error");
                    }
                }

            } // start of second
            else if (board.getItems()[itemArrayIndex].getPosition().equals(board.getItems()[itemArrayIndex].getPosition().toLowerCase())) { // belong to the down side player
                boolean isDost = false; boolean isEmpty = true;
                if(from.charAt(0)== to.charAt(0)+1 ){
                   // System.out.println("hatali hareket");// System.out.println(" CAN NOT GO BACKWARS");
                    return;
                }
                // System.out.println("sWAOW");
                if ((to.charAt(0) != from.charAt(0)) && Math.abs(to.charAt(0)-from.charAt(0))==1&& Math.abs(Integer.parseInt(to.substring(1)) - Integer.parseInt(from.substring(1)))==0) {// y axis movement
                    //System.out.println("Skao");
                    if (from.charAt(0) <= 'e') /* kopruyu gecemedi*/ {// System.out.println("sgfssgsg");
                        if (from.charAt(0) < to.charAt(0)) {
                            for (int n = 0; n < board.getItems().length; n++) {
                                if (board.getItems()[n].getPosition().equals(to)) {
                                    isEmpty = false;
                                }
                                if (board.getItems()[n].getPosition().equals(to) && board.getItems()[n].getName().equals(board.getItems()[n].getName().toLowerCase())) {
                                    isDost = true;
                                }
                            }   // if yes then this is friendly
                            if (isDost == true) {
                               // System.out.println("hatali hareket");// System.out.println("ILLEGAL MOVE. FRIENDLY PIECE AHEAD");
                                return;
                            } else if (isDost == false && isEmpty == false) {
                                board.tasYe(from, to);
                                // System.out.println("Yedim abi ");
                            }
                            board.getItems()[itemArrayIndex].setPosition(to);
                            board.emptyGivenIndex(from,to);Valid = true;
                            // System.out.println("Burada from:" + from + " to:" + to);
                        }} // correct
                    else if (from.charAt(0) > 'e') { // kopruyu gecti
                        // gidilecek yerde dost tas varsa gitme. Dusman tas varsa ye. Yoksa git
                       // System.out.println("AHMETA2");
                        for (int n = 0; n < board.getItems().length; n++) {
                            if (board.getItems()[n].getPosition().equals(to)) {
                                isEmpty = false;
                            }
                            if (board.getItems()[n].getPosition().equals(to) && board.getItems()[n].getName().equals(board.getItems()[n].getName().toLowerCase())) {
                                isDost = true; break;
                            }
                        }   // if yes then this is friendly
                        if (isDost == true) {
                          //  System.out.println("hatali hareket");//System.out.println("ILLEGAL MOVE. FRIEDNLY PIECE AHEAD");
                            return;
                        } else if (isDost == false && isEmpty == false) {
                            board.tasYe(from, to);
                        }
                        // if it hasn't entered the if clause then the place is empty
                        board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to);Valid = true;
                    }
                    else {
                        //System.out.println("hatali hareket");//System.out.println("Cant move backwards");
                        return;
                    }
                } else if(to.charAt(0) == from.charAt(0) && Math.abs(Integer.parseInt(to.substring(1)) - Integer.parseInt(from.substring(1)))==1 && from.charAt(0)>='f'){ // is moving side ways
                   // System.out.println("İsmet");
                    if(Integer.parseInt(to.substring(1)) > Integer.parseInt(from.substring(1)) == true && 9>=Integer.parseInt(to.substring(1))  ){ // east e gider
                        for (int n = 0; n < board.getItems().length; n++) {
                            if (board.getItems()[n].getPosition().equals(to)) {
                                isEmpty = false;
                            }
                            if (board.getItems()[n].getPosition().equals(to) && board.getItems()[n].getName().equals(board.getItems()[n].getName().toLowerCase())) {
                                isDost = true;
                            }}if (isDost == true) {
                          //  System.out.println("hatali hareket");//System.out.println("ILLEGAL MOVE. FRIENDLY PIECE AHEAD");
                            return;
                        } else if (isDost == false && isEmpty == false) {
                            board.tasYe(from, to);
                            //System.out.println("Yedim abi ");
                        }
                        board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to); Valid = true;
                    }
                    else if(Integer.parseInt(to.substring(1)) > Integer.parseInt(from.substring(1)) == false && 0<=Integer.parseInt(from.substring(1)) ){ //goes west
                       // System.out.println("Kismet");
                        for (int n = 0; n < board.getItems().length; n++) {
                            if (board.getItems()[n].getPosition().equals(to)) {
                                isEmpty = false;
                            }
                            if (board.getItems()[n].getPosition().equals(to) && board.getItems()[n].getName().equals(board.getItems()[n].getName().toLowerCase())) {
                                isDost = true;
                            }}
                        if (isDost == true) {
                           // System.out.println("hatali hareket");//System.out.println("ILLEGAL MOVE. FRIENDLY PIECE AHEAD");
                            return;
                        } else if (isDost == false && isEmpty == false) {
                            board.tasYe(from, to);
                            // System.out.println("Yedim abi ");
                        }
                        board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to); Valid = true;
                    }
                    else{ // System.out.println("hatali hareket");//System.out.println("Trying to go to the edge. error");
                    }
                }

            }

    }
}
