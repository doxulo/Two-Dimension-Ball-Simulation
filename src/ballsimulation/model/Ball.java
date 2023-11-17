package ballsimulation.model;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    public double x, y, radius;
    public double velocityX, velocityY, mass;
    private Color color;

    private boolean hasCollided;

    public Ball(int x, int y, int radius, double velocityX, double velocityY, double mass, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.mass = mass; // New property
        this.color = color;
    }
    public void setHasCollided(boolean hasCollided) {
        this.hasCollided = hasCollided;
    }

    public boolean getHasCollided() {
        return hasCollided;
    }

    public void update(int containerWidth, int containerHeight) {
        // Reset collision state at the beginning of each update
        this.hasCollided = false;
        // Update the ball's position
        x += velocityX;
        y += velocityY;

        // Handle wall collisions
        if (x <= radius || x >= containerWidth - radius) {
            velocityX = -velocityX;
        }
        if (y <= radius || y >= containerHeight - radius) {
            velocityY = -velocityY;
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2));
    }

    // Getters for collision detection
    public double getX() { return x; }
    public double getY() { return y; }
    public double getRadius() { return radius; }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    // Method to handle collision with another ball
    public void collideWith(Ball other) {
        double newVelX1 = (this.velocityX * (this.mass - other.mass) + (2 * other.mass * other.velocityX)) / (this.mass + other.mass);
        double newVelY1 = (this.velocityY * (this.mass - other.mass) + (2 * other.mass * other.velocityY)) / (this.mass + other.mass);

        double newVelX2 = (other.velocityX * (other.mass - this.mass) + (2 * this.mass * this.velocityX)) / (this.mass + other.mass);
        double newVelY2 = (other.velocityY * (other.mass - this.mass) + (2 * this.mass * this.velocityY)) / (this.mass + other.mass);

        this.velocityX = newVelX1;
        this.velocityY = newVelY1;
        other.velocityX = newVelX2;
        other.velocityY = newVelY2;
    }


}
