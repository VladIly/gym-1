package org.example;

import java.util.*;

public class Visitor {
    private String name;
    private int age;
    private Membership membership;
    private final List<String> visitHistory = new ArrayList<>();

    public Visitor() {} // Для Jackson


    public Visitor(String name, int age, Membership membership) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
        this.name = name;
        this.age = age;
        this.membership = membership;
    }

    public void visitGym() {
        visitHistory.add(new Date().toString());
    }

    public List<String> getVisitHistory() {
        return visitHistory;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public Membership getMembership() { return membership; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setMembership(Membership membership) { this.membership = membership; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Visitor)) return false;
        Visitor visitor = (Visitor) o;
        return age == visitor.age && Objects.equals(name, visitor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        String membershipInfo = (membership != null) ? membership.getType() : "відсутній";
        return name + ", вік: " + age + ", абонемент: " + membershipInfo;
    }
}