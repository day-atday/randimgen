package randimgen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class make_Window {
    static String userString;

    public void MainWindow() {
    // WINDOW CODE GENERATED FROM CHATGPT, EDITED AND REVIEWED MANUALLY
        JFrame frame = new JFrame("randimgen");
        frame.setSize(1280, 720);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel_image = new JPanel(new BorderLayout());

        // PLACEHOLDER
        JLabel placeholder = new JLabel("", SwingConstants.CENTER);

        panel_image.add(placeholder, BorderLayout.CENTER);

        JPanel panel_button = new JPanel();

        JButton buttonGenerate = new JButton("Generate a picture");

        JButton buttonTextToFile = new JButton("Insert text into the picture");

        buttonGenerate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    create_File.getOutDataToFile(false);

                    File file = new File("randimg.bmp");

                    if (file.exists()) {

                        BufferedImage image = ImageIO.read(file);

                        JLabel label = new JLabel(new ImageIcon(image));

                        panel_image.removeAll();
                        panel_image.add(label, BorderLayout.CENTER);

                        panel_image.revalidate();
                        panel_image.repaint();

                    } else {

                        JOptionPane.showMessageDialog(
                            frame,
                            "Can't find the image:\n" +
                            file.getAbsolutePath()
                        );
                    }

                } catch (Exception error) {

                    JOptionPane.showMessageDialog(
                        frame,
                        "[ERROR] " + error
                    );
                }
            }
        });

        buttonTextToFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                try{
                    JFrame frameTextToFile = new JFrame("Insert text");
                    frameTextToFile.setLocationRelativeTo(null);
                    frameTextToFile.setSize(640, 150);
                    frameTextToFile.setResizable(false);

                    JTextField textField = new JTextField(10);

                    JButton buttonGenerateTextToPic = new JButton("Generate");

                    buttonGenerateTextToPic.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // NEEDS A TRY CATCH HERE
                            setterString(textField.getText());

                            try {
                                create_File.getOutDataToFile(true);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                            JOptionPane.showMessageDialog(frame,"File created");
                        }
                    });

                    frameTextToFile.add(textField, BorderLayout.CENTER);
                    frameTextToFile.add(buttonGenerateTextToPic, BorderLayout.SOUTH);

                    frameTextToFile.setVisible(true);

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        panel_button.add(buttonGenerate);
        panel_button.add(buttonTextToFile);


        frame.add(panel_image, BorderLayout.CENTER);
        frame.add(panel_button, BorderLayout.SOUTH);

        // OUTPUT WINDOW
        frame.setVisible(true);
    }

    public void setterString(String userString){
        make_Window.userString = userString;
    }

    public static String getterString(){
        return userString;
    }
}
