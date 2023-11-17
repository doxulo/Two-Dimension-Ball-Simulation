package ballsimulation.model;

import java.awt.*;

public class Ball {
    private int x, y, radius;
    private double velocityX, velocityY;
    private Color color;

    public Ball(int x, int y, int radius, double velocityX, double velocityY, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.color = color;
    }

    public void update() {
        // Update the ball's position
        x += velocityX;
        y += velocityY;

        // Handle wall collisions
        // (You'll need to add more logic here)
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    // Add getters, setters, and collision detection methods
}
