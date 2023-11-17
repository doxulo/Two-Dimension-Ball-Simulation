package ballsimulation;

import ballsimulation.model.Ball;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BallSimulation extends JFrame {

    private List<Ball> balls = new ArrayList<>();
    private JPanel panel;

    public BallSimulation() {
        // Initialize the window
        setTitle("2D Ball Simulation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        balls.add(new Ball(100, 100, 20, 2, 2, 1.0, Color.RED));
        balls.add(new Ball(200, 100, 25, 3, 3, 1.5, Color.BLUE));
        balls.add(new Ball(300, 150, 15, -2, 2.5, 0.8, Color.GREEN));
        balls.add(new Ball(400, 200, 30, 1, -2, 2.0, Color.YELLOW));
        balls.add(new Ball(100, 300, 18, -3, 1, 1.2, Color.ORANGE));
        balls.add(new Ball(200, 350, 22, 2, -1.5, 1.0, Color.MAGENTA));
        balls.add(new Ball(50, 50, 16, 2.5, 2, 0.7, Color.CYAN));
        balls.add(new Ball(450, 100, 20, -2, -3, 1.3, Color.PINK));
        balls.add(new Ball(150, 250, 24, 1.5, 2, 1.1, Color.LIGHT_GRAY));
        balls.add(new Ball(350, 300, 28, -2, -1, 1.6, Color.DARK_GRAY));

        // Set up the panel where balls will be drawn
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Ball ball : balls) {
                    ball.draw(g);
                }
            }
        };
        add(panel);

        // Set up the game loop (using a Swing Timer here)
        new Timer(16, e -> updateAndRepaint()).start();
    }

    private void updateAndRepaint() {
        for (Ball ball : balls) {
            ball.update(panel.getWidth(), panel.getHeight());
        }

        checkCollisions(); // Check for collisions after updating positions

        panel.repaint();
    }


    private void checkCollisions() {
        for (int i = 0; i < balls.size(); i++) {
            Ball ball1 = balls.get(i);
            for (int j = i + 1; j < balls.size(); j++) {
                Ball ball2 = balls.get(j);

                double dx = ball2.getX() - ball1.getX();
                double dy = ball2.getY() - ball1.getY();
                double distance = Math.sqrt(dx * dx + dy * dy);
                double radiusSum = ball1.getRadius() + ball2.getRadius();

                // Check if balls are colliding
                if (distance < radiusSum) {
                    // Move the balls to ensure they are no longer overlapping
                    double overlap = (radiusSum - distance) / 2;
                    ball1.setX(ball1.getX() - overlap * (dx / distance));
                    ball1.setY(ball1.getY() - overlap * (dy / distance));
                    ball2.setX(ball2.getX() + overlap * (dx / distance));
                    ball2.setY(ball2.getY() + overlap * (dy / distance));

                    // Now update their velocities based on the collision
                    ball1.collideWith(ball2);
                }
            }
        }
    }






    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BallSimulation().setVisible(true);
        });
    }
}