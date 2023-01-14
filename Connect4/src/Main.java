public class Main {
    public static void main(String[] args) {
        // A class for everything means cleaner code overall
        Connect4Game game = new Connect4Game("X", "O");
        game.startGame();
    }
}