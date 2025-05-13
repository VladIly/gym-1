package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class GymTest {

    @Test
    void testAddVisitor() {
        Gym gym = new Gym();
        Membership membership = new Membership("Standard", new Date(), new Date());
        Visitor visitor = new Visitor("Alice", 25, membership);
        gym.addVisitor(visitor);
        assertEquals(1, gym.getVisitors().size());
        assertTrue(gym.getVisitors().contains(visitor));
    }

    @Test
    void testAddTrainer() {
        Gym gym = new Gym();
        Trainer trainer = new Trainer("Mike", "Fitness");
        gym.addTrainer(trainer);
        assertEquals(1, gym.getTrainers().size());
        assertTrue(gym.getTrainers().contains(trainer));
    }

    @Test
    void testEmptyVisitorList() {
        Gym gym = new Gym();
        assertTrue(gym.getVisitors().isEmpty());
    }

    @Test
    void testDuplicateVisitorNotAddedTwice() {
        Gym gym = new Gym();
        Visitor visitor = new Visitor("Bob", 30, null);
        gym.addVisitor(visitor);
        gym.addVisitor(visitor);
        assertEquals(1, gym.getVisitors().size());
    }

    @Test
    void testInvalidVisitorAge() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Visitor("Test", -1, null); // Тестуємо негативний вік
        });
    }
    @Test
    void testExportEmptyVisitors() throws IOException {
        Gym gym = new Gym();
        Path tempFile = Files.createTempFile("empty_visitors", ".json");
        gym.exportVisitors(tempFile.toString());
        assertTrue(tempFile.toFile().exists());
        Files.delete(tempFile);
    }

    @Test
    void testImportFromNonExistentFile() {
        Gym gym = new Gym();
        assertThrows(IOException.class, () -> {
            gym.importVisitors("nonexistent.json");
        });
    }

    @Test
    void testMembershipDates() {
        Date start = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(LocalDate.now().plusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Membership membership = new Membership("Premium", start, end);
        assertEquals("Premium", membership.getType());
        assertTrue(end.after(start));
    }

    @Test
    void testVisitorNameAfterImport() throws IOException {
        Gym gym = new Gym();
        Visitor visitor = new Visitor("Charlie", 27, null);
        gym.addVisitor(visitor);
        Path tempFile = Files.createTempFile("visitor_name", ".json");
        gym.exportVisitors(tempFile.toString());

        Gym importedGym = new Gym();
        importedGym.importVisitors(tempFile.toString());

        assertEquals("Charlie", importedGym.getVisitors().get(0).getName());
        Files.delete(tempFile);
    }
}