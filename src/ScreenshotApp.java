import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class ScreenshotApp {
    public static void main(String[] args) {
        // Create the main application window (JFrame)
        JFrame frame = new JFrame("Screenshot App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 100);

        // Create the button that will trigger the screenshot
        JButton screenshotButton = new JButton("Take Screenshot");
        screenshotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                takeScreenshot(); // Call the screenshot method when the button is clicked
            }
        });

        // Add the button to the frame and make the frame visible
        frame.getContentPane().add(screenshotButton, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static void takeScreenshot() {
        try {
            // Create a Robot instance for capturing the screen
            Robot robot = new Robot();
            String format = "png";

            // Ensure the screenshots directory exists
            File directory = new File("screenshots");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Create a file name with the current timestamp
            String fileName = "screenshots/Screenshot_" + System.currentTimeMillis() + "." + format;

            // Capture the screen
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

            // Save the captured image to a file
            ImageIO.write(screenFullImage, format, new File(fileName));

            System.out.println("Click!");
        } catch (AWTException | IOException ex) {
            // Handle any exceptions
            System.err.println(ex);
        }
    }
}
