package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class Gym {
    private final List<Visitor> visitors = new ArrayList<>();
    private final List<Trainer> trainers = new ArrayList<>();

    public void addVisitor(Visitor visitor) {
        if (!visitors.contains(visitor)) {
            visitors.add(visitor);
        }
    }

    public void addTrainer(Trainer trainer) {
        if (!trainers.contains(trainer)) {
            trainers.add(trainer);
        }
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void exportVisitors(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filename), visitors);
    }

    public void importVisitors(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Visitor> imported = mapper.readValue(new File(filename), new TypeReference<List<Visitor>>() {});
        for (Visitor v : imported) {
            addVisitor(v);
        }
    }
}