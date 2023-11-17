package ballsimulation.model;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    // Ball properties
    private double x, y;
    private double velocityX, velocityY;
    final double mass;
    final int radius;
    final Color color;

    // Constructor
    public Ball(double x, double y, int radius, double velocityX, double velocityY, double mass, Color color) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.mass = mass;
        this.radius = radius;
        this.color = color;
    }

    // Update the ball's position
    public void update(int width, int height) {
        // Update the ball's position
        x += velocityX;
        y += velocityY;

        // Check for collisions with the left and right walls
        if (x - radius < 0 || x + radius > width) {
            velocityX = -velocityX; // Reverse the horizontal velocity
            x = Math.max(radius, Math.min(x, width - radius)); // Correct the position
        }

        // Check for collisions with the top and bottom walls
        if (y - radius < 0 || y + radius > height) {
            velocityY = -velocityY; // Reverse the vertical velocity
            y = Math.max(radius, Math.min(y, height - radius)); // Correct the position
        }
    }

    // Draw the ball
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int)(x - radius), (int)(y - radius), radius * 2, radius * 2);
    }

    // Handle collisions with another ball
    public void collideWith(Ball other) {
        double dx = other.x - this.x;
        double dy = other.y - this.y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Calculate the normal and tangent vectors
        double nx = dx / distance;
        double ny = dy / distance;
        double tx = -ny;
        double ty = nx;

        // Dot product tangent
        double dpTan1 = this.velocityX * tx + this.velocityY * ty;
        double dpTan2 = other.velocityX * tx + other.velocityY * ty;

        // Dot product normal
        double dpNorm1 = this.velocityX * nx + this.velocityY * ny;
        double dpNorm2 = other.velocityX * nx + other.velocityY * ny;

        // Conservation of momentum in 1D
        double m1 = (dpNorm1 * (this.mass - other.mass) + 2.0 * other.mass * dpNorm2) / (this.mass + other.mass);
        double m2 = (dpNorm2 * (other.mass - this.mass) + 2.0 * this.mass * dpNorm1) / (this.mass + other.mass);

        // Update velocities
        this.velocityX = tx * dpTan1 + nx * m1;
        this.velocityY = ty * dpTan1 + ny * m1;
        other.velocityX = tx * dpTan2 + nx * m2;
        other.velocityY = ty * dpTan2 + ny * m2;
    }

    // Accessor methods
    public double getX() { return x; }
    public double getY() { return y; }
    public int getRadius() { return radius; }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
}
