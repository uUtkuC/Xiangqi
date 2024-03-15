public class Vezir extends Item{
    // 2 caprazda 1 hareket yapabilir
    public Vezir(String s, String posit){
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
    public Vezir cloneMe(){
        Vezir itemo = new Vezir(name,getPosition());
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
               // System.out.println("n suan bu:" + itemArrayIndex);
                break; // break is to make iteration faster.
            }
        }
        // diagonal olarak a4 a6 c4 c6 b5 veya j4 j6 h4 h6 ı5 gidebilir.Kareler bu mu, dost mu bos mu, kontrolu yap yeterli.
        if(board.getItems()[itemArrayIndex].getName().equals(board.getItems()[itemArrayIndex].getName().toUpperCase())){// belong to upside player. j4 j6 h4 h6 ı5 valid palces to move
            boolean isDost = false; boolean isEmpty = true;
            if((from.equals("j4") ||from.equals("j6") || from.equals("h4") ||from.equals("h6")) && to.equals("i5")){
                for (int n = 0; n < board.getItems().length; n++) {
                    if (board.getItems()[n].getPosition().equals(to)) {
                        isEmpty = false;
                    }
                    if (board.getItems()[n].getPosition().equals(to) && board.getItems()[n].getName().equals(board.getItems()[n].getName().toUpperCase())) {
                        isDost = true;
                    }}
                if (isDost == true) {
                    //System.out.println("hatali hareket");// System.out.println("ILLEGAL MOVE. FRIENDLY PIECE AHEAD");
                    return;
                } else if (isDost == false && isEmpty == false) {
                    board.tasYe(from, to);
                    // System.out.println("Yedim abi ");
                }
                board.getItems()[itemArrayIndex].setPosition(to);
                board.emptyGivenIndex(from,to);Valid = true;
            }
            else if (from.equals("i5") && ((to.equals("j4") ||to.equals("j6") || to.equals("h6") ||to.equals("h4")))){
                for (int n = 0; n < board.getItems().length; n++) {
                    if (board.getItems()[n].getPosition().equals(to)) {
                        isEmpty = false;
                    }
                    if (board.getItems()[n].getPosition().equals(to) && board.getItems()[n].getName().equals(board.getItems()[n].getName().toUpperCase())) {
                        isDost = true;
                    }}
                if (isDost == true) {
                    //System.out.println("hatali hareket");// System.out.println("ILLEGAL MOVE. FRIENDLY PIECE AHEAD");
                    return;
                } else if (isDost == false && isEmpty == false) {
                    board.tasYe(from, to);
                    // System.out.println("Yedim abi ");
                }
                board.getItems()[itemArrayIndex].setPosition(to);
                board.emptyGivenIndex(from,to);Valid = true;
            }
            else  {}  //System.out.println("hatali hareket");// System.out.println(" Piece cannot move out of the palace or move forward, sideways or backwars. only diagonal movemeent is valid");
        }
        else if(board.getItems()[itemArrayIndex].getName().equals(board.getItems()[itemArrayIndex].getName().toLowerCase())){ // belong to down side player. a4 a6 c4 c6 b5  are valid.
            boolean isDost = false; boolean isEmpty = true;
            if((from.equals("a4") ||from.equals("a6") || from.equals("c4") ||from.equals("c6")) && to.equals("b5")){
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
                board.emptyGivenIndex(from,to);Valid = true;
            }
            else if (from.equals("b5") && ((to.equals("a4") ||to.equals("a6") || to.equals("c6") ||to.equals("c4")))){
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
                board.emptyGivenIndex(from,to);Valid = true;
            }
            else {} // System.out.println("hatali hareket");// System.out.println(" Piece cannot move out of the palace or move forward, sideways or backwars. only diagonal movemeent is valid");

        }
        else {} // System.out.println("hatali hareket");// System.out.println("GREAT ERROR NAME BUGGG");


    }
}
