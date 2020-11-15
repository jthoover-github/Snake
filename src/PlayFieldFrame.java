import javax.swing.*;

public class PlayFieldFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    PlayFieldFrame() {
        this.setTitle("Snake Byte");
        this.add(new PlayField());
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}