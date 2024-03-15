public class Sah extends Item{// no point . can only move within the palace. two generals can not face each other without pieces in between.


    public Sah(String s, String posit){
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
    public Sah cloneMe(){
        Sah itemo = new Sah(name,getPosition());
        return itemo;

    }
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
              //  System.out.println("n suan bu:" + itemArrayIndex);
                break; // break is to make iteration faster.
            }
        }
// saga sola yukarı asagı gidebilir palace icide doldugu surece
        boolean isDost = false; boolean isEmpty = true; boolean ToYaGiderseSahlarBakisiyorMu = true;
        String locationOfEnemeySah = "";
        if(  ("xx".equals(to) == false) && ("xx".equals(from)==false)&& (Integer.parseInt(to.substring(1))==4 | Integer.parseInt(to.substring(1))==5 | Integer.parseInt(to.substring(1))==6)
                && ((Integer.parseInt(to.substring(1))>= 4 &&Integer.parseInt(to.substring(1))<=6)
                && (Integer.parseInt(from.substring(1))>= 4 &&Integer.parseInt(from.substring(1))<=6)) && (((to.charAt(0) == from.charAt(0) +1) || (to.charAt(0) == from.charAt(0) -1))
                && Math.abs(Integer.parseInt(to.substring(1))-Integer.parseInt(from.substring(1))) == 0)
                || (to.charAt(0) == from.charAt(0) && Math.abs(Integer.parseInt(to.substring(1))-Integer.parseInt(from.substring(1))) == 1)
                && (Integer.parseInt(to.substring(1))==4 | Integer.parseInt(to.substring(1))==5 | Integer.parseInt(to.substring(1))==6))
        {
            if ( (to.charAt(0)== 'j' | to.charAt(0)=='i' | to.charAt(0) =='h') && board.getItems()[itemArrayIndex].getName().equals(board.getItems()[itemArrayIndex].getName().toUpperCase()) ) { // upside player
                for (int n = 0; n < board.getItems().length; n++) {
                    if (board.getItems()[n].getName().equals("ş")) { // we now know the index of enemy sah
                        locationOfEnemeySah = board.getItems()[n].getPosition();
                        break;
                    }
                }
                for (int n = 0; n < board.getItems().length; n++) {
                    if (board.getItems()[n].getPosition().substring(1).equals(locationOfEnemeySah.substring(1))
                            && (board.getItems()[n].getName().equals("ş") == false) &&(board.getItems()[n].getName().equals("Ş") == false) ) {
                        ToYaGiderseSahlarBakisiyorMu = false;
                        break;
                    }
                }
                if (to.substring(1).equals(locationOfEnemeySah.substring(1)) && ToYaGiderseSahlarBakisiyorMu == true) {
                   // System.out.println("hatali hareket");//System.out.println("Sahs can not face each other");
                } else {
                    for (int n = 0; n < board.getItems().length; n++) {
                        if (board.getItems()[n].getPosition().equals(to)) {
                            isEmpty = false;
                        }
                        if (board.getItems()[n].getPosition().equals(to) && board.getItems()[n].getName().equals(board.getItems()[n].getName().toUpperCase())) {
                            isDost = true;
                        }
                    }
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
            } else if ((to.charAt(0)== 'a' | to.charAt(0)=='b' | to.charAt(0) =='c')&& board.getItems()[itemArrayIndex].getName().equals(board.getItems()[itemArrayIndex].getName().toLowerCase())) { // down side player
                for (int n = 0; n < board.getItems().length; n++) {
                    if (board.getItems()[n].getName().equals("Ş")) { // we now know the index of enemy sah
                        locationOfEnemeySah = board.getItems()[n].getPosition();
                        break;
                    }
                }
                for (int n = 0; n < board.getItems().length; n++) {
                    if (board.getItems()[n].getPosition().substring(1).equals(locationOfEnemeySah.substring(1))
                            && (board.getItems()[n].getName().equals("ş") == false) &&(board.getItems()[n].getName().equals("Ş") == false)) {
                        ToYaGiderseSahlarBakisiyorMu = false;
                        break;
                    }
                }
                if (to.substring(1).equals(locationOfEnemeySah.substring(1)) && ToYaGiderseSahlarBakisiyorMu == true) {
                    // //System.out.println("hatali hareket");//System.out.println("Sahs can not face each other");
                } else {
                    for (int n = 0; n < board.getItems().length; n++) {
                        if (board.getItems()[n].getPosition().equals(to)) {
                            isEmpty = false;
                        }
                        if (board.getItems()[n].getPosition().equals(to) && board.getItems()[n].getName().equals(board.getItems()[n].getName().toLowerCase())) {
                            isDost = true;
                        }
                    }
                    if (isDost == true) {
                        // //  System.out.println("hatali hareket");//System.out.println("ILLEGAL MOVE. FRIENDLY PIECE AHEAD");
                        return;
                    } else if (isDost == false && isEmpty == false) {
                        board.tasYe(from, to);
                        // System.out.println("Yedim abi ");
                    }

                    board.getItems()[itemArrayIndex].setPosition(to);
                    board.emptyGivenIndex(from,to); Valid = true;
                }
            }
        }
        else {}  //System.out.println("hatali hareket");//System.out.println("Wrong input");


    }
}
