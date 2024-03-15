public class Top extends  Item{
    // 4.5 at opening 4 at midgame endgame
    // must jump over any soldier. then get to the place at the target.
    public Top(String s, String posit){
        name = s;
        this.setPosition(posit);
    }    Board board;
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
    public Top cloneMe(){
        Top itemo = new Top(name,getPosition());
        return itemo;

    }
    float puan = (float)4.5;
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
        // can move until a piece located. right left up down. can take a piece if there is a stone inbetween.
        // wether the peice in between is enemy or friend doesn't make a difference. Should only be a single peice inbetween.
        boolean isDost = false; boolean isTargetEmpty = true; boolean isThereAPieceBetweenTargetAndSelf = false; boolean areThereMoreThenOnePiecesInBetween = false;
        if( to.equals("xx") == false &&  from.equals("xx") == false && ((to.charAt(0) == from.charAt(0)) && ( Integer.parseInt(from.substring(1)) == Integer.parseInt(to.substring(1))))== false
                && ((to.charAt(0) != from.charAt(0)) && ( Integer.parseInt(from.substring(1)) != Integer.parseInt(to.substring(1))))== false ) {
            if (board.getItems()[itemArrayIndex].getName().equals(board.getItems()[itemArrayIndex].getName().toUpperCase())) {
               // System.out.println("Fatih");
                if(to.charAt(0)!= from.charAt(0) ){ // yAxis move
                    for(int n=0;n<board.getItems().length ;n++){ // check every letter's same int part. if int part is same with to.
                        if(from.charAt(0)>to.charAt(0)){ // is moving down
                            if(board.getItems()[n].getPosition().charAt(0) < from.charAt(0) && board.getItems()[n].getPosition().charAt(0) > to.charAt(0)
                                    && board.getItems()[n].getPosition().substring(1).equals(to.substring(1)) ){ // then there is a peice in between
                               // System.out.println("Hey piece is: "+ board.getItems()[n].getPosition() +" "+n);
                                if(isThereAPieceBetweenTargetAndSelf ==  true){ areThereMoreThenOnePiecesInBetween = true;
                                 //   System.out.println("Hey piece is: "+ board.getItems()[n].getPosition() +"  "+n);
                                    }
                                isThereAPieceBetweenTargetAndSelf= true;
                              //  System.out.println("Fatih2");
                            }}
                        else if(from.charAt(0)<to.charAt(0)){
                            if(board.getItems()[n].getPosition().charAt(0) > from.charAt(0) && board.getItems()[n].getPosition().charAt(0) < to.charAt(0)
                                    && board.getItems()[n].getPosition().substring(1).equals(to.substring(1))){
                                if(isThereAPieceBetweenTargetAndSelf ==  true){ areThereMoreThenOnePiecesInBetween = true;}
                                isThereAPieceBetweenTargetAndSelf= true;

                            }
                        }
                        if (board.getItems()[n].getPosition().equals(to)){
                            isTargetEmpty = false;
                            if(board.getItems()[n].getName().equals(board.getItems()[n].getName().toUpperCase())) isDost = true;
                        }
                    }
                    if(areThereMoreThenOnePiecesInBetween == true){  //System.out.println("hatali hareket");// System.out.println("Error more than one pieces between targetT");
                    } // road is not empty. cant go to the target.
                    else if (isDost == true)  {} //System.out.println("hatali hareket");// System.out.println("Target is Friendly. Error");
                    else if(isThereAPieceBetweenTargetAndSelf == true ){ // road is not empty may will go to the target.
                        if(isTargetEmpty == false && isDost == false){
                            board.tasYe(from, to); board.getItems()[itemArrayIndex].setPosition(to);
                            board.emptyGivenIndex(from,to);Valid = true;
                        }
                        else if( isTargetEmpty == true)   {} //System.out.println("hatali hareket");// System.out.println("Illegal Move. Target is Empty and There is a unit in between");
                    }
                    else if(isThereAPieceBetweenTargetAndSelf == false){ // road is empty can go there
                        if(isTargetEmpty == true){
                            board.getItems()[itemArrayIndex].setPosition(to);
                            board.emptyGivenIndex(from,to);Valid = true;}
                        else  {} // System.out.println("hatali hareket");// System.out.println("Target not emptyO");
                    }
                }
                else if (Integer.parseInt(from.substring(1)) != Integer.parseInt(to.substring(1))){ // xAxis move
                    for(int n=0;n<board.getItems().length ;n++){ //
                        if(Integer.parseInt(from.substring(1))>Integer.parseInt(to.substring(1))){
                            if(board.getItems()[n].getPosition().charAt(0) == from.charAt(0) && board.getItems()[n].getPosition().charAt(0) == to.charAt(0) &&
                                    Integer.parseInt(board.getItems()[n].getPosition().substring(1))< Integer.parseInt(from.substring(1))
                                    &&Integer.parseInt(board.getItems()[n].getPosition().substring(1))> Integer.parseInt(to.substring(1))  ){
                                if(isThereAPieceBetweenTargetAndSelf ==  true){ areThereMoreThenOnePiecesInBetween = true;}
                                isThereAPieceBetweenTargetAndSelf= true;
                            }
                        }
                        else if(Integer.parseInt(from.substring(1))<Integer.parseInt(to.substring(1))){
                            if(board.getItems()[n].getPosition().charAt(0) == from.charAt(0) && board.getItems()[n].getPosition().charAt(0) == to.charAt(0)&&
                                    Integer.parseInt(board.getItems()[n].getPosition().substring(1))> Integer.parseInt(from.substring(1))
                                    &&Integer.parseInt(board.getItems()[n].getPosition().substring(1))< Integer.parseInt(to.substring(1))){
                                if(isThereAPieceBetweenTargetAndSelf == true){ areThereMoreThenOnePiecesInBetween = true;}
                                isThereAPieceBetweenTargetAndSelf= true;
                            }
                        } if (board.getItems()[n].getPosition().equals(to)){
                            isTargetEmpty = false;
                            if(board.getItems()[n].getName().equals(board.getItems()[n].getName().toUpperCase())) isDost = true;
                        }
                    } if(areThereMoreThenOnePiecesInBetween == true){ // System.out.println("hatali hareket");// System.out.println("Error more than one pieces between targetK");
                    } // road is not empty. cant go to the target.
                    else if (isDost == true)   {} //System.out.println("hatali hareket");// System.out.println("Target is Friendly. Error");
                    else if(isThereAPieceBetweenTargetAndSelf == true ){ // road is not empty may will go to the target.
                        if(isTargetEmpty == false && isDost == false){
                            board.tasYe(from, to); board.getItems()[itemArrayIndex].setPosition(to);
                            board.emptyGivenIndex(from,to);Valid = true;
                           // System.out.println("Here agabey");
                        }
                        else if( isTargetEmpty == true)  {} // System.out.println("hatali hareket");// System.out.println("Illegal Move. Target is Empty and There is a unit in between");
                    }
                    else if(isThereAPieceBetweenTargetAndSelf == false){ // road is empty can go there
                        if(isTargetEmpty == true){
                            board.getItems()[itemArrayIndex].setPosition(to);
                            board.emptyGivenIndex(from,to);Valid = true;}
                        else  {} // System.out.println("hatali hareket");// System.out.println("Target not emptyK");
                    }
                }
                else   {} //System.out.println("hatali hareket");// System.out.println("Unkonwn Error Please report");

            } else if (board.getItems()[itemArrayIndex].getName().equals(board.getItems()[itemArrayIndex].getName().toLowerCase())) {
              //  System.out.println("Fatih");
                if(to.charAt(0)!= from.charAt(0) ){ // yAxis move
                    for(int n=0;n<board.getItems().length ;n++){ // check every letter's same int part. if int part is same with to.
                        if(from.charAt(0)>to.charAt(0)){ // is moving down
                            if(board.getItems()[n].getPosition().charAt(0) < from.charAt(0) && board.getItems()[n].getPosition().charAt(0) > to.charAt(0)
                                    && board.getItems()[n].getPosition().substring(1).equals(to.substring(1)) ){ // then there is a peice in between
                               // System.out.println("Hey piece is: "+ board.getItems()[n].getPosition() +" "+n);
                                if(isThereAPieceBetweenTargetAndSelf ==  true){ areThereMoreThenOnePiecesInBetween = true;
                                    //System.out.println("Hey piece is: "+ board.getItems()[n].getPosition() +"  "+n);
                                    }
                                isThereAPieceBetweenTargetAndSelf= true;
                               // System.out.println("Fatih2");
                            }}
                        else if(from.charAt(0)<to.charAt(0)){
                            if(board.getItems()[n].getPosition().charAt(0) > from.charAt(0) && board.getItems()[n].getPosition().charAt(0) < to.charAt(0)
                                    && board.getItems()[n].getPosition().substring(1).equals(to.substring(1))){
                                if(isThereAPieceBetweenTargetAndSelf ==  true){ areThereMoreThenOnePiecesInBetween = true;}
                                isThereAPieceBetweenTargetAndSelf= true;

                            }
                        }
                        if (board.getItems()[n].getPosition().equals(to)){
                            isTargetEmpty = false;
                            if(board.getItems()[n].getName().equals(board.getItems()[n].getName().toLowerCase())) isDost = true;
                        }
                    }
                    if(areThereMoreThenOnePiecesInBetween == true){ // System.out.println("hatali hareket");// System.out.println("Error more than one pieces between targetT");
                    } // road is not empty. cant go to the target.
                    else if (isDost == true)  {} // System.out.println("hatali hareket");// System.out.println("Target is Friendly. Error");
                    else if(isThereAPieceBetweenTargetAndSelf == true ){ // road is not empty may will go to the target.
                        if(isTargetEmpty == false && isDost == false){
                            board.tasYe(from, to); board.getItems()[itemArrayIndex].setPosition(to);
                            board.emptyGivenIndex(from,to);Valid = true;
                        }
                        else if( isTargetEmpty == true)  {} //System.out.println("hatali hareket");// System.out.println("Illegal Move. Target is Empty and There is a unit in between");
                    }
                    else if(isThereAPieceBetweenTargetAndSelf == false){ // road is empty can go there
                        if(isTargetEmpty == true){
                            board.getItems()[itemArrayIndex].setPosition(to);
                            board.emptyGivenIndex(from,to);Valid = true;}
                        else   {} //System.out.println("hatali hareket");// System.out.println("Target not emptyO");
                    }
                }
                else if (Integer.parseInt(from.substring(1)) != Integer.parseInt(to.substring(1))){ // xAxis move
                    for(int n=0;n<board.getItems().length ;n++){ //
                        if(Integer.parseInt(from.substring(1))>Integer.parseInt(to.substring(1))){
                            if(board.getItems()[n].getPosition().charAt(0) == from.charAt(0) && board.getItems()[n].getPosition().charAt(0) == to.charAt(0) &&
                                    Integer.parseInt(board.getItems()[n].getPosition().substring(1))< Integer.parseInt(from.substring(1))
                                    &&Integer.parseInt(board.getItems()[n].getPosition().substring(1))> Integer.parseInt(to.substring(1))  ){
                                if(isThereAPieceBetweenTargetAndSelf ==  true){ areThereMoreThenOnePiecesInBetween = true;}
                                isThereAPieceBetweenTargetAndSelf= true;
                            }
                        }
                        else if(Integer.parseInt(from.substring(1))<Integer.parseInt(to.substring(1))){
                            if(board.getItems()[n].getPosition().charAt(0) == from.charAt(0) && board.getItems()[n].getPosition().charAt(0) == to.charAt(0)&&
                                    Integer.parseInt(board.getItems()[n].getPosition().substring(1))> Integer.parseInt(from.substring(1))
                                    &&Integer.parseInt(board.getItems()[n].getPosition().substring(1))< Integer.parseInt(to.substring(1))){
                                if(isThereAPieceBetweenTargetAndSelf == true){ areThereMoreThenOnePiecesInBetween = true;}
                                isThereAPieceBetweenTargetAndSelf= true;
                            }
                        } if (board.getItems()[n].getPosition().equals(to)){
                            isTargetEmpty = false;
                            if(board.getItems()[n].getName().equals(board.getItems()[n].getName().toLowerCase())) isDost = true;
                        }
                    } if(areThereMoreThenOnePiecesInBetween == true){  //System.out.println("hatali hareket");// System.out.println("Error more than one pieces between targetK");
                    } // road is not empty. cant go to the target.
                    else if (isDost == true)  {} // System.out.println("hatali hareket");// System.out.println("Target is Friendly. ErrorK");
                    else if(isThereAPieceBetweenTargetAndSelf == true ){ // road is not empty may will go to the target.
                        if(isTargetEmpty == false && isDost == false){
                            board.tasYe(from, to); board.getItems()[itemArrayIndex].setPosition(to);
                            board.emptyGivenIndex(from,to);Valid = true;
                           // System.out.println("Here agabey");
                        }
                        else if( isTargetEmpty == true)  {} // System.out.println("hatali hareket");// System.out.println("Illegal Move. Target is Empty and There is a unit in between");
                    }
                    else if(isThereAPieceBetweenTargetAndSelf == false){ // road is empty can go there
                        if(isTargetEmpty == true){
                            board.getItems()[itemArrayIndex].setPosition(to);
                            board.emptyGivenIndex(from,to);Valid = true;}
                        else  {} // System.out.println("hatali hareket");// System.out.println("Target not emptyK");
                    }
                }
                else  {} // System.out.println("hatali hareket");// System.out.println("Unkonwn Error Please report");

            }
        }
        else  {} // System.out.println("hatali hareket");// System.out.println("Invalid input. Can't stay the same place");
    }

}