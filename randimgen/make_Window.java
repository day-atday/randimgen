package randimgen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.image.BufferedImage;



public class make_Window {

    public void MainWindow() {

        JFrame frame = new JFrame("randimgen");
        frame.setSize(1280, 720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel_image = new JPanel(new BorderLayout());

        // PLACEHOLDER
        JLabel placeholder = new JLabel("", SwingConstants.CENTER);

        panel_image.add(placeholder, BorderLayout.CENTER);

        JPanel panel_button = new JPanel();

        JButton button = new JButton("Generate a picture");

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    create_File.getOutDataToFile();

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

        panel_button.add(button);

        frame.add(panel_image, BorderLayout.CENTER);
        frame.add(panel_button, BorderLayout.SOUTH);

        // OUTPUT WINDOW
        frame.setVisible(true);
    }
}
