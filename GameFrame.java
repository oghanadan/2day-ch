import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class GameFrame extends JFrame{
    GameFrame(){
        this.setTitle("Game UI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 720);

        // Icon : https://www.flaticon.com/free-icons/car
        ImageIcon icon = new ImageIcon("images/icon.png");
        this.setIconImage(icon.getImage());
    }


}