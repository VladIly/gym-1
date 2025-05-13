package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Gym {
    private final List<Visitor> visitors = new ArrayList<>();
    private final List<Trainer> trainers = new ArrayList<>();

    public void addVisitor(Visitor visitor) { visitors.add(visitor); }
    public void removeVisitor(Visitor visitor) { visitors.remove(visitor); }
    public List<Visitor> getVisitors() { return visitors; }

    public void addTrainer(Trainer trainer) { trainers.add(trainer); }
    public void removeTrainer(Trainer trainer) { trainers.remove(trainer); }
    public List<Trainer> getTrainers() { return trainers; }

    // Бізнес-логіка: експорт у файл
    public void exportVisitors(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        visitors.sort(Comparator.comparing(Visitor::getName));
        mapper.writeValue(new File(filename), visitors);
    }

    // Бізнес-логіка: імпорт з файлу
    public void importVisitors(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Visitor> imported = mapper.readValue(new File(filename), new TypeReference<>() {});
        visitors.clear();
        visitors.addAll(imported);
    }
}