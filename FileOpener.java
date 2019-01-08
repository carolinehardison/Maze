import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.FileNotFoundException;
/*
 * GUI Swing that opens text file
 */
public class FileOpener {
    private static JFrame frame;
    public static void main(String[] args){
        frame = new JFrame("Maze Runner");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MazePanel panel = new MazePanel();
        frame.getContentPane().add(panel);
        frame.setSize(panel.getSize());
        frame.pack();
        frame.setVisible(true);
    }


    /*
     * Panel that opens text file and displays solution
     */
    public static class MazePanel extends JPanel {
        private GridBagConstraints c;
        private JButton open;

        MazePanel() {

            setLayout(new GridBagLayout());
            c = new GridBagConstraints();
            c.insets = new Insets(10, 10, 10, 10);

            open = new JButton("Open");
            c.gridx = 0;
            c.gridy = 1;
            add(open, c);
            JLabel instructions = new JLabel(
                    "<html>Maze-text files are set up where:<br/>" +
                            "   0 = Wall<br/>" +
                            "   1 = Path<br/><br/>" +
                            "At the top of the file should be [# rows] [#cols]<br/>" +
                            "Mazes start at the top left corner and finish at the bottom right <br/><br/>" +
                            "Sample File:<br/>" +
                            "2 2<br/>" +
                            "1 0<br/>" +
                            "1 1<br/><br/>" +
                            "</html>");
            c.gridx = 0;
            c.gridy = 0;
            add(instructions,c);
            open.addActionListener(e -> {
                        JFileChooser chooser = new JFileChooser();
                        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                        chooser.setFileFilter(filter);
                        if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {

                            try {
                                remove(open);
                                remove(instructions);
                                JLabel guide = new JLabel("<html>0 = Wall<br/>1 = Unchecked Path<br/>2 = Path to Dead-End<br/>3 = Path to Finish<br/></html>");
                                c.gridx = 0;
                                c.gridy = 0;
                                add(guide, c);
                                Maze maze = new Maze(chooser.getSelectedFile());
                                MazeRunner mr = new MazeRunner(maze);
                                JLabel unsolved = new JLabel(mr.getMaze().toString());
                                c.gridx = 0;
                                c.gridy = 1;
                                add(unsolved, c);

                                mr.traverse();
                                String finished = mr.getMaze().toString();
                                JLabel solved = new JLabel(finished);
                                c.gridx = 1;
                                c.gridy = 1;
                                add(solved, c);

                                frame.setSize(getSize());
                                frame.pack();
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }

                        }

                    });
        }
    }
}
