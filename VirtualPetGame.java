package FFINalProject;

//Main class to start the game
public class VirtualPetGame {
 public static void main(String[] args) {
     GameController controller = new GameController();
     GameView view = new GameView(controller);
     view.interactWithUser();
 }
}