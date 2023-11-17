
# 2D Ball Simulation

## Overview

This project is a simple 2D ball simulation written in Java. It demonstrates basic principles of object-oriented programming, graphical user interfaces (GUI) using Swing, and simple physics concepts like collision detection and response.

### Features

- **2D Ball Movement:** Balls move in a two-dimensional space with basic physics for motion.
- **Collision Detection:** Balls detect collisions with each other and the boundaries of the window.
- **Collision Response:** Upon collision, balls bounce off each other and the walls, realistically considering their velocity, direction, and mass.
- **Graphical Representation:** The simulation is visually represented using Java Swing, showcasing moving balls within a window.

## Setup

### Prerequisites

- Java Development Kit (JDK) - To compile and run the Java application.
- An Integrated Development Environment (IDE) like IntelliJ IDEA, Eclipse, or NetBeans (optional but recommended).

### Installation

1. **Clone the Repository:**
   ```bash
   git clone [URL of the repository]
   ```

2. **Navigate to the Project Directory:**
   ```bash
   cd 2D-Ball-Simulation
   ```

3. **Compile the Java Files:**
   - If using an IDE, import the project and build it using the IDE's build tools.
   - If using the command line:
     ```bash
     javac Ball.java BallSimulation.java
     ```

## Running the Simulation

- **Run the Program:**
  - If using an IDE, run the project from the IDE.
  - If using the command line:
    ```bash
    java BallSimulation
    ```

- **Interact with the Simulation:**
  - Currently, the simulation runs with a pre-defined set of balls. Modifications can be made in the `BallSimulation` class to change the number, size, color, and velocity of the balls.

## Contributing

Feel free to fork the repository and submit pull requests with enhancements or bug fixes.

## License

This project is open source and available under the [MIT License](LICENSE).

## Collision Calculations in 2D Ball Simulation

### Physics Behind the Collision

In the 2D Ball Simulation, when two balls collide, we consider the collision to be perfectly elastic. In an elastic collision, both momentum and kinetic energy are conserved.

#### Elastic Collision Formulas

The velocity components of each ball after an elastic collision are computed based on their masses and initial velocities. The formulas for the final velocities (\(v_{1x}^\prime\), \(v_{1y}^\prime\), \(v_{2x}^\prime\), \(v_{2y}^\prime\)) in the x and y directions are as follows:

For ball 1:

$$\[
v_{1x}^\prime = rac{(v_{1x}(m_1 - m_2) + 2m_2v_{2x})}{m_1 + m_2}
\]$$

$$\[
v_{1y}^\prime = rac{(v_{1y}(m_1 - m_2) + 2m_2v_{2y})}{m_1 + m_2}
\]$$

For ball 2:

$$\[
v_{2x}^\prime = rac{(v_{2x}(m_2 - m_1) + 2m_1v_{1x})}{m_1 + m_2}
\]$$

$$\[
v_{2y}^\prime = rac{(v_{2y}(m_2 - m_1) + 2m_1v_{1y})}{m_1 + m_2}
\]$$

Where:
- $$\( v_{1x}^\prime \), \( v_{1y}^\prime \)$$ are the new x and y velocities of ball 1 after the collision.
- $$\( v_{2x}^\prime \), \( v_{2y}^\prime \)$$ are the new x and y velocities of ball 2 after the collision.
- $$\( m_1 \) and \( m_2 \)$$ are the masses of ball 1 and ball 2, respectively.
- $$\( v_{1x} \), \( v_{1y} \), \( v_{2x} \), and \( v_{2y} \)$$ are the original velocities of ball 1 and ball 2 in the x and y directions, respectively.

### Implementation in Java

This physics model is implemented in the `collideWith` method of the `Ball` class. When a collision is detected between two balls, their velocities are updated according to the above formulas, taking into account their velocities and masses. 
