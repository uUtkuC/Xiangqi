public class Fil extends  Item{
    // 2 points. caprazlarda 2 birim gidebilir
    // cant cross river
    public Fil(String s, String posit){
        name = s;
        this.setPosition(posit);
    } Board board;
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
    public Fil cloneMe(){
        Fil itemo = new Fil(name,getPosition());
        return itemo;

    }
    int puan = 2;
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

        boolean isDost = false; boolean isEmpty = true; boolean caprazBosMu = true;
        if(board.getItems()[itemArrayIndex].getName().equals(board.getItems()[itemArrayIndex].getName().toUpperCase())){ // if from and to chars have 2 horizontal and 2 verical differences then it is okay
            if(Math.abs(from.charAt(0)- to.charAt(0)) == 2 && Math.abs(Integer.parseInt(from.substring(1))- Integer.parseInt(to.substring(1)))== 2){
                if(from.charAt(0)>= 'f' && to.charAt(0)>= 'f' && from.charAt(0)<= 'j' && to.charAt(0)<= 'j'){
                    if(Integer.parseInt(to.substring(1))<= 9 && Integer.parseInt(to.substring(1))>=1 && Integer.parseInt(to.substring(1))<= 9 && Integer.parseInt(to.substring(1))>=1){
                        String orta = ""+(char) ((from.charAt(0)-'a' + to.charAt(0) -'a')/2+'a') + ((Integer.parseInt(to.substring(1)) +Integer.parseInt(from.substring(1)))/2);
                        //System.out.println(orta);
                        for (int m = 0; m < board.getItems().length; m++) {
                            if (board.getItems()[m].getPosition().equals(orta)) {
                                caprazBosMu = false;
                            }
                        }
                        if(caprazBosMu== true){
                            for (int n = 0; n < board.getItems().length; n++) {
                                if (board.getItems()[n].getPosition().equals(to)) {
                                    isEmpty = false;
                                }
                                if (board.getItems()[n].getPosition().equals(to) && board.getItems()[n].getName().equals(board.getItems()[n].getName().toUpperCase())) {
                                    isDost = true;
                                }}
                            if (isDost == true) {
                                //System.out.println("hatali hareket");//System.out.println("ILLEGAL MOVE. FRIENDLY PIECE AHEAD");
                                return;
                            } else if (isDost == false && isEmpty == false) {
                                board.tasYe(from, to);
                                // System.out.println("Yedim abi ");
                            }
                            board.getItems()[itemArrayIndex].setPosition(to);
                            board.emptyGivenIndex(from,to); Valid = true; // burayi sor
                        }
                        else{}  //System.out.println("hatali hareket");//System.out.println("Friendly peice before target");
                    }
                    else{}////  System.out.println("hatali hareket");//System.out.println("x Axis is not valid");
                }
                else {}// System.out.println("hatali hareket");//System.out.println("y Axis is not valid");
            }
            else {}// System.out.println("hatali hareket");//System.out.println("Wrong input");
        }
        else if(board.getItems()[itemArrayIndex].getName().equals(board.getItems()[itemArrayIndex].getName().toLowerCase())){
            if(Math.abs(from.charAt(0)- to.charAt(0)) == 2 && Math.abs(Integer.parseInt(from.substring(1))- Integer.parseInt(to.substring(1)))== 2){
                if(from.charAt(0)>= 'a' && to.charAt(0)>= 'a' && from.charAt(0)<= 'e' && to.charAt(0)<= 'e'){
                    if(Integer.parseInt(to.substring(1))<= 9 && Integer.parseInt(to.substring(1))>=1 && Integer.parseInt(to.substring(1))<= 9 && Integer.parseInt(to.substring(1))>=1){
                        String orta = ""+(char) ((from.charAt(0)-'a' + to.charAt(0) -'a')/2+'a') + ((Integer.parseInt(to.substring(1)) +Integer.parseInt(from.substring(1)))/2);
                        //System.out.println(orta);
                        for (int m = 0; m < board.getItems().length; m++) {
                            if (board.getItems()[m].getPosition().equals(orta)) {
                                caprazBosMu = false;
                            }
                        }
                        if(caprazBosMu== true){
                            for (int n = 0; n < board.getItems().length; n++) {
                                if (board.getItems()[n].getPosition().equals(to)) {
                                    isEmpty = false;
                                }
                                if (board.getItems()[n].getPosition().equals(to) && board.getItems()[n].getName().equals(board.getItems()[n].getName().toLowerCase())) {
                                    isDost = true;
                                }}
                            if (isDost == true) {
                                // System.out.println("hatali hareket");// System.out.println("ILLEGAL MOVE. FRIENDLY PIECE AHEAD");
                                return;
                            } else if (isDost == false && isEmpty == false) {
                                board.tasYe(from, to);
                                // System.out.println("Yedim abi ");
                            }
                            board.getItems()[itemArrayIndex].setPosition(to);
                            board.emptyGivenIndex(from,to); Valid = true;
                        }
                        else {}//  System.out.println("hatali hareket");//System.out.println("Friendly peice before target");
                    }
                    else {}//  System.out.println("hatali hareket");//System.out.println("x Axis is not valid");
                }
                else  {}// System.out.println("hatali hareket");//System.out.println("y Axis is not valid");
            }
        }
        else {}//  System.out.println("hatali hareket");//System.out.println("Wrong input");
    }
}
