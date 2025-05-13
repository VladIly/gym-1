package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trainer {
    private String name;
    private String specialization;
    private final List<Visitor> clients = new ArrayList<>();

    public Trainer() {} // Для Jackson

    public Trainer(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    // Бізнес-логіка: Записати відвідувача на тренування
    public void scheduleSession(Visitor visitor) {
        clients.add(visitor);
    }

    // Геттери
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public List<Visitor> getClients() { return clients; }

    // equals/hashCode для пошуку та порівняння
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trainer)) return false;
        Trainer trainer = (Trainer) o;
        return Objects.equals(name, trainer.name) &&
                Objects.equals(specialization, trainer.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, specialization);
    }

    @Override
    public String toString() {
        return name + " (" + specialization + ")";
    }
}