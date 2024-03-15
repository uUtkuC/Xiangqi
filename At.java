public class At extends Item{
    public At(String s, String posit){
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
    public At cloneMe(){
        At itemo = new At(name,getPosition());
        return itemo;

    }
    int puan = 4;
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
               // System.out.println("n suan bu:" + itemArrayIndex);
                break; // break is to make iteration faster.
            }
        }

        boolean isTargetDost = false; boolean isTargetEmpty = true; boolean ilkHamleBosMu = true;
        if(board.getItems()[itemArrayIndex].getName().equals(board.getItems()[itemArrayIndex].getName().toUpperCase())) { // either yAxis difference is 2 and x axis is 1 or xAxis is 2 and yaxis is 1
            if((Math.abs(from.charAt(0)-to.charAt(0))==2 && Math.abs(Integer.parseInt(to.substring(1))-Integer.parseInt(from.substring(1)))==1)){ // check if up or down Yaxis is empty. then the desired location
                if(from.charAt(0)>to.charAt(0)){ // going down check if from-1 th sapce is empty
                    for (int m = 0; m < board.getItems().length; m++) {
                        if (board.getItems()[m].getPosition().charAt(0) == (from.charAt(0)+to.charAt(0))/2 && board.getItems()[m].getPosition().substring(1).equals(from.substring(1))){
                            ilkHamleBosMu = false;

                        }
                    }
                    for (int m = 0; m < board.getItems().length; m++) {
                        if (board.getItems()[m].getPosition().equals(to)){
                            isTargetEmpty = false;
                            if(board.getItems()[m].getName().toUpperCase().equals(board.getItems()[m].getName())){
                                isTargetDost = true;
                            }
                        }
                    }
                    if(isTargetDost == true){} // target is friend cant eat.
                    else if(ilkHamleBosMu == false){} // cant move
                    else if(isTargetEmpty){ board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to); Valid = true;}
                    else if( isTargetEmpty == false && isTargetDost == false){ board.tasYe(from, to);board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to); Valid = true;}
                }
                else if(from.charAt(0)<to.charAt(0)){
                    for (int m = 0; m < board.getItems().length; m++) {
                        if (board.getItems()[m].getPosition().charAt(0) == (from.charAt(0)+to.charAt(0))/2 && board.getItems()[m].getPosition().substring(1).equals(from.substring(1))){
                            ilkHamleBosMu = false;
                        }
                    }
                    for (int m = 0; m < board.getItems().length; m++) {
                        if (board.getItems()[m].getPosition().equals(to)){
                            isTargetEmpty = false;
                            if(board.getItems()[m].getName().toUpperCase().equals(board.getItems()[m].getName())){
                                isTargetDost = true;
                            }
                        }
                    }
                    if(isTargetDost == true){} // target is friend cant eat.
                    else if(ilkHamleBosMu == false){} // cant move
                    else if(isTargetEmpty){ board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to);Valid = true;}
                    else if( isTargetEmpty == false && isTargetDost == false){ board.tasYe(from, to);board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to);Valid = true;}
                }

            }// following line. Horizontal move first.
            else if(Math.abs(from.charAt(0)-to.charAt(0))==1 && Math.abs(Integer.parseInt(to.substring(1))-Integer.parseInt(from.substring(1)))==2){
                if(Integer.parseInt(to.substring(1))>Integer.parseInt(from.substring(1))){ // goes east
                    for (int m = 0; m < board.getItems().length; m++) {
                        if (Integer.parseInt(board.getItems()[m].getPosition().substring(1)) ==(Integer.parseInt(to.substring(1))+Integer.parseInt(from.substring(1)))/2
                                && board.getItems()[m].getPosition().charAt(0)== from.charAt(0)){
                            ilkHamleBosMu = false;

                        }
                    }
                    for (int m = 0; m < board.getItems().length; m++) {
                        if (board.getItems()[m].getPosition().equals(to)){
                            isTargetEmpty = false;
                            if(board.getItems()[m].getName().toUpperCase().equals(board.getItems()[m].getName())){
                                isTargetDost = true;
                            }
                        }
                    }
                    if(isTargetDost == true){} // target is friend cant eat.
                    else if(ilkHamleBosMu == false){} // cant move
                    else if(isTargetEmpty){ board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to);Valid = true;}
                    else if( isTargetEmpty == false && isTargetDost == false){ board.tasYe(from, to);board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to);Valid = true;}
                }
                else if(Integer.parseInt(to.substring(1))<Integer.parseInt(from.substring(1))){// goes west
                    for (int m = 0; m < board.getItems().length; m++) {
                        if (Integer.parseInt(board.getItems()[m].getPosition().substring(1)) ==(Integer.parseInt(to.substring(1))+Integer.parseInt(from.substring(1)))/2
                                && board.getItems()[m].getPosition().charAt(0)== from.charAt(0)){
                            ilkHamleBosMu = false;
                        }
                    }
                    for (int m = 0; m < board.getItems().length; m++) {
                        if (board.getItems()[m].getPosition().equals(to)){
                            isTargetEmpty = false;
                            if(board.getItems()[m].getName().toUpperCase().equals(board.getItems()[m].getName())){
                                isTargetDost = true;
                            }
                        }
                    }
                    if(isTargetDost == true){} // target is friend cant eat.
                    else if(ilkHamleBosMu == false){} // cant move
                    else if(isTargetEmpty){ board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to);Valid = true;}
                    else if( isTargetEmpty == false && isTargetDost == false){ board.tasYe(from, to);board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to);Valid = true;}
                }
            }
        }
        else if(board.getItems()[itemArrayIndex].getName().equals(board.getItems()[itemArrayIndex].getName().toLowerCase())){
            if((Math.abs(from.charAt(0)-to.charAt(0))==2 && Math.abs(Integer.parseInt(to.substring(1))-Integer.parseInt(from.substring(1)))==1)){ // check if up or down Yaxis is empty. then the desired location
                if(from.charAt(0)>to.charAt(0)){ // going down check if from-1 th sapce is empty
                    for (int m = 0; m < board.getItems().length; m++) {
                        if (board.getItems()[m].getPosition().charAt(0) == (from.charAt(0)+to.charAt(0))/2 && board.getItems()[m].getPosition().substring(1).equals(from.substring(1))){
                            ilkHamleBosMu = false;

                        }
                    }
                    for (int m = 0; m < board.getItems().length; m++) {
                        if (board.getItems()[m].getPosition().equals(to)){
                            isTargetEmpty = false;
                            if(board.getItems()[m].getName().toLowerCase().equals(board.getItems()[m].getName())){
                                isTargetDost = true;
                            }
                        }
                    }
                    if(isTargetDost == true){} // target is friend cant eat.
                    else if(ilkHamleBosMu == false){} // cant move
                    else if(isTargetEmpty){ board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to);Valid = true;}
                    else if( isTargetEmpty == false && isTargetDost == false){ board.tasYe(from, to);board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to);Valid = true;}
                }
                else if(from.charAt(0)<to.charAt(0)){
                    for (int m = 0; m < board.getItems().length; m++) {
                        if (board.getItems()[m].getPosition().charAt(0) == (from.charAt(0)+to.charAt(0))/2 && board.getItems()[m].getPosition().substring(1).equals(from.substring(1))){
                            ilkHamleBosMu = false;
                        }
                    }
                    for (int m = 0; m < board.getItems().length; m++) {
                        if (board.getItems()[m].getPosition().equals(to)){
                            isTargetEmpty = false;
                            if(board.getItems()[m].getName().toLowerCase().equals(board.getItems()[m].getName())){
                                isTargetDost = true;
                            }
                        }
                    }
                    if(isTargetDost == true){} // target is friend cant eat.
                    else if(ilkHamleBosMu == false){} // cant move
                    else if(isTargetEmpty){ board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to);Valid = true;}
                    else if( isTargetEmpty == false && isTargetDost == false){ board.tasYe(from, to);board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to);Valid = true;}
                }

            }// following line. Horizontal move first.
            else if(Math.abs(from.charAt(0)-to.charAt(0))==1 && Math.abs(Integer.parseInt(to.substring(1))-Integer.parseInt(from.substring(1)))==2){
                if(Integer.parseInt(to.substring(1))>Integer.parseInt(from.substring(1))){ // goes east
                    for (int m = 0; m < board.getItems().length; m++) {
                        if (board.getItems()[m].getPosition().equals("xx")==false&&Integer.parseInt(board.getItems()[m].getPosition().substring(1)) ==(Integer.parseInt(to.substring(1))+Integer.parseInt(from.substring(1)))/2
                                && board.getItems()[m].getPosition().charAt(0)== from.charAt(0)){
                            ilkHamleBosMu = false;
                        }
                    }
                    for (int m = 0; m < board.getItems().length; m++) {
                        if (board.getItems()[m].getPosition().equals(to)){
                            isTargetEmpty = false;
                            if(board.getItems()[m].getName().toLowerCase().equals(board.getItems()[m].getName())){
                                isTargetDost = true;
                            }
                        }
                    }
                    if(isTargetDost == true){} // target is friend cant eat.
                    else if(ilkHamleBosMu == false){} // cant move
                    else if(isTargetEmpty){ board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to);Valid = true;}
                    else if( isTargetEmpty == false && isTargetDost == false){ board.tasYe(from, to);board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to);Valid = true;}
                }
                else if(Integer.parseInt(to.substring(1))<Integer.parseInt(from.substring(1))){// goes west
                    for (int m = 0; m < board.getItems().length; m++) {
                        if (board.getItems()[m].getPosition().equals("xx")==false&& Integer.parseInt(board.getItems()[m].getPosition().substring(1)) ==(Integer.parseInt(to.substring(1))+Integer.parseInt(from.substring(1)))/2
                                && board.getItems()[m].getPosition().charAt(0)== from.charAt(0) ){
                            ilkHamleBosMu = false;
                        }
                    }
                    for (int m = 0; m < board.getItems().length; m++) {
                        if (board.getItems()[m].getPosition().equals(to)){
                            isTargetEmpty = false;
                            if(board.getItems()[m].getName().toLowerCase().equals(board.getItems()[m].getName())){
                                isTargetDost = true;
                            }
                        }
                    }
                    if(isTargetDost == true){} // target is friend cant eat.
                    else if(ilkHamleBosMu == false){} // cant move
                    else if(isTargetEmpty){ board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to);Valid = true;}
                    else if( isTargetEmpty == false && isTargetDost == false){ board.tasYe(from, to);board.getItems()[itemArrayIndex].setPosition(to);
                        board.emptyGivenIndex(from,to);Valid = true;}
                }
            }

        }

    }
}
