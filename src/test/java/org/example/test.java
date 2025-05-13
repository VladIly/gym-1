package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GymTest {

    @Test
    void testAddVisitor() {
        // Arrange
        Gym gym = new Gym();
        Membership membership = new Membership("Standard", new java.util.Date(), new java.util.Date());
        Visitor visitor = new Visitor("Alice", 25, membership);

        // Act - Додаємо відвідувача до гімназії
        gym.addVisitor(visitor);

        // Assert - Перевіряємо, чи є відвідувач у списку
        assertEquals(1, gym.getVisitors().size(), "There should be 1 visitor in the gym.");
        assertTrue(gym.getVisitors().contains(visitor), "Gym should contain the visitor Alice.");
    }
}