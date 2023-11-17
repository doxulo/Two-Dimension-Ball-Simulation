package ballsimulation;

import ballsimulation.model.Ball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BallSimulation extends JPanel implements ActionListener {
    private final List<Ball> balls = new ArrayList<>();

    public BallSimulation() {
        // Set up the window
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);

        // Create some balls with random properties for demonstration purposes
        balls.add(new Ball(100, 100, 20, 5, 3, 1.0, Color.RED));
        balls.add(new Ball(200, 100, 25, -4, 4, 1.5, Color.BLUE));
        balls.add(new Ball(300, 200, 15, 3, -3, 1.2, Color.GREEN));
        balls.add(new Ball(400, 300, 30, -3, 2, 1.8, Color.YELLOW));
        balls.add(new Ball(500, 400, 10, 4, -4, 0.8, Color.PINK));
        balls.add(new Ball(600, 500, 18, -5, 5, 2.0, Color.ORANGE));
        balls.add(new Ball(700, 100, 22, 5, -5, 1.3, Color.CYAN));
        balls.add(new Ball(100, 500, 28, -5, 3, 2.2, Color.MAGENTA));
        balls.add(new Ball(200, 400, 12, 5, 2, 0.9, Color.GRAY));
        balls.add(new Ball(300, 300, 16, -4, -4, 1.1, Color.DARK_GRAY));

        // Set up a timer to refresh the display and update the ball positions
        Timer timer = new Timer(16, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update the state of each ball and repaint the window
        updateBalls();
        checkCollisions();
        repaint();
    }

    private void updateBalls() {
        int width = getWidth();
        int height = getHeight();
        for (Ball ball : balls) {
            ball.update(width, height); // Now passing the dimensions for boundary checking
        }
    }

    private void checkCollisions() {
        // Check for collisions between each pair of balls
        for (int i = 0; i < balls.size(); i++) {
            for (int j = i + 1; j < balls.size(); j++) {
                Ball ball1 = balls.get(i);
                Ball ball2 = balls.get(j);
                double dx = ball2.getX() - ball1.getX();
                double dy = ball2.getY() - ball1.getY();
                double distance = Math.sqrt(dx * dx + dy * dy);
                double minimumDistance = ball1.getRadius() + ball2.getRadius();
                if (distance < minimumDistance) {
                    // Move balls apart
                    double overlap = (minimumDistance - distance) / 2;
                    ball1.setX(ball1.getX() - overlap * (dx / distance));
                    ball1.setY(ball1.getY() - overlap * (dy / distance));
                    ball2.setX(ball2.getX() + overlap * (dx / distance));
                    ball2.setY(ball2.getY() + overlap * (dy / distance));
                    // Handle collision
                    ball1.collideWith(ball2);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Ball ball : balls) {
            ball.draw(g);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ball Simulation");
            BallSimulation simulation = new BallSimulation();
            frame.add(simulation);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
