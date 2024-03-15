public class Test {

    public static void main(String[] args) {

        String[][] soldier_moves = {
                {"d3", "e3"}, {"h2", "h3"},
                {"a9", "b9"}, {"h8", "a8"},
                {"b9", "b3"}, {"j8", "h7"},
                {"e3", "f3"}, {"j9", "j8"},
                {"f3", "f4"}
        };

        String[][] soldier_invalid_moves = {
                {"d3", "d4"}
        };

        String[][] horse_moves = {
                {"a2", "c3"}, {"g3", "f3"},
                {"d3", "e3"}, {"f3", "e3"},
                {"c3", "e4"}
        };

        String[][] horse_invalid_moves = {
                {"a2", "b4"}
        };


        String[][] general_moves = {
                {"a5", "b5"}, {"h2", "h5"},
                {"b5", "b4"}, {"h5", "d5"},
                {"b4", "c4"},
        };

        String[][] general_invalid_moves = {
                {"a5", "b5"}, {"h2", "h5"},
                {"b5", "b4"}, {"h5", "d5"},
                {"b4", "b3"},
        };

        String[][] chariot_moves = {
                {"a1", "b1"}, {"h2", "a2"},
                {"b1", "b9"}, {"j2", "h3"},
                {"d9", "e9"}, {"j1", "j2"},
                {"e9", "f9"}, {"j2", "c2"},
                {"f9", "g9"}, {"c2", "c8"},
                {"g9", "g8"}, {"j9", "b9"},
                {"a9", "b9"}, {"h8", "h5"},
                {"b9", "h9"}

        };

        String[][] chariot_invalid_moves = {
                {"a9", "e9"}
        };



        String[][] canon_moves = {
                {"c2", "j2"}
        };

        String[][] canon_invalid_moves = {
                {"c2", "h2"}
        };


        String[][] advisor_moves = {
                {"a4", "b5"}, {"j2", "h3"},
                {"b5", "c4"},
        };

        String[][] advisor_invalid_moves = {
                {"a4", "b5"}, {"j2", "h3"},
                {"b5", "c4"}, {"h8", "h5"},
                {"c4", "b3"}
        };



        String[][] elephant_moves = {
                {"a3", "c5"}, {"h8", "h6"},
                {"c5", "e7"}, {"j8", "h7"},
        };

        String[][] elephant_invalid_moves = {
                {"a3", "c5"}, {"h8", "h6"},
                {"c5", "e7"}, {"j8", "h7"},
                {"e7", "g5"}
        };


        

        String[][] facing_generals = {
                {"d5", "e5"}, {"g5", "f5"},
                {"e5", "f5"}, {"g9", "f9"},
                {"f5", "f4"}
        };

        String[][] repetitive_checks = {
                {"d5", "e5"}, {"g5", "f5"},
                {"e5", "f5"}, {"g9", "f9"},
                {"d1", "e1"}, {"j9", "i9"},
                {"e1", "f1"}, {"i9", "i5"},
                {"f1", "g1"}, {"i5", "g5"},
                {"g1", "h1"}, {"j3", "h5"},
                {"a5", "b5"}, {"g5", "f5"},
                {"b5", "b6"}, {"f5", "f6"},
                {"b6", "b5"}, {"f6", "f5"},
                {"b5", "b6"}, {"f5", "f6"},
                {"b6", "b5"}, {"f6", "f5"}
        };
        
        String[][] checkmate_moves = {
                {"d5", "e5"}, {"h8", "h5"},
                {"c8", "c5"}, {"h5", "e5"},
                {"c5", "g5"}, {"e5", "f5"},
                {"a8", "c7"}, {"j8", "h7"},
                {"a9", "b9"}, {"h7", "g5"},
                {"b9", "b6"}, {"h2", "h5"},
                {"d9", "e9"}, {"g5", "f3"},
                {"e9", "f9"},

        };


        Game g;
        g = new Game("A", "B");
        play_game(g, soldier_moves);
        g.getBoard().print();

        g = new Game("A", "B");
        play_game(g, soldier_invalid_moves);
        g.getBoard().print();

        g = new Game("A", "B");
        play_game(g, horse_moves);
        g.getBoard().print();

        g = new Game("A", "B");
        play_game(g, horse_invalid_moves);
        g.getBoard().print();

        g = new Game("A", "B");
        play_game(g, general_moves);
        g.getBoard().print();

        g = new Game("A", "B");
        play_game(g, general_invalid_moves);
        g.getBoard().print();

        g = new Game("A", "B");
        play_game(g, chariot_moves);
        g.getBoard().print();

        g = new Game("A", "B");
        play_game(g, chariot_invalid_moves);
        g.getBoard().print();

        g = new Game("A", "B");
        play_game(g, canon_moves);
        g.getBoard().print();

        g = new Game("A", "B");
        play_game(g, canon_invalid_moves);
        g.getBoard().print();

        g = new Game("A", "B");
        play_game(g, advisor_moves);
        g.getBoard().print();

        g = new Game("A", "B");
        play_game(g, advisor_invalid_moves);
        g.getBoard().print();

        g = new Game("A", "B");
        play_game(g, elephant_moves);
        g.getBoard().print();

        g = new Game("A", "B");
        play_game(g, elephant_invalid_moves);
        g.getBoard().print();

        g = new Game("A", "B");
        play_game(g, facing_generals);
        g.getBoard().print();

        g = new Game("A", "B");
        play_game(g, repetitive_checks);
        g.getBoard().print();
       // g = new Game("A", "B");
       // System.out.println("\n\n\n +*****************************");
      // play_game2(g, checkmate_moves);
        //g.getBoard().print();




    }
    public static void play_game2(Game game, String[][] moves) {

        for (int i=0; i<moves.length; i++) {
            game.play(moves[i][0], moves[i][1]);
            game.getBoard().print();
        }

    }
    public static void play_game(Game game, String[][] moves) {

        for (int i=0; i<moves.length; i++) {
            game.play(moves[i][0], moves[i][1]);

        }

    }

}
