package org.example;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Gym gym = new Gym();

        while (true) {
            System.out.println("\n--- Меню ---");
            System.out.println("1. Додати відвідувача");
            System.out.println("2. Додати тренера");
            System.out.println("3. Відвідати зал");
            System.out.println("4. Запис на тренування");
            System.out.println("5. Показати всіх відвідувачів");
            System.out.println("6. Експорт відвідувачів у файл");
            System.out.println("7. Імпорт відвідувачів з файлу");
            System.out.println("0. Вихід");
            System.out.print("Ваш вибір: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Ім’я: ");
                    String name = scanner.nextLine();
                    System.out.print("Вік: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    Membership m = new Membership("Стандарт", new Date(), new Date());
                    gym.addVisitor(new Visitor(name, age, m));
                    System.out.println("Відвідувач доданий.");
                }
                case 2 -> {
                    System.out.print("Ім’я тренера: ");
                    String tname = scanner.nextLine();
                    System.out.print("Спеціалізація: ");
                    String spec = scanner.nextLine();
                    gym.addTrainer(new Trainer(tname, spec));
                    System.out.println("Тренера додано.");
                }
                case 3 -> {
                    System.out.print("Ім’я відвідувача: ");
                    String vname = scanner.nextLine();
                    gym.getVisitors().stream()
                            .filter(v -> v.getName().equals(vname))
                            .findFirst()
                            .ifPresentOrElse(Visitor::visitGym,
                                    () -> System.out.println("Відвідувач не знайдений."));
                }
                case 4 -> {
                    System.out.print("Ім’я тренера: ");
                    String trainerName = scanner.nextLine();
                    System.out.print("Ім’я клієнта: ");
                    String clientName = scanner.nextLine();
                    Trainer trainer = gym.getTrainers().stream()
                            .filter(t -> t.getName().equals(trainerName))
                            .findFirst().orElse(null);
                    Visitor visitor = gym.getVisitors().stream()
                            .filter(v -> v.getName().equals(clientName))
                            .findFirst().orElse(null);
                    if (trainer != null && visitor != null) {
                        trainer.scheduleSession(visitor);
                        System.out.println("Клієнта записано.");
                    } else {
                        System.out.println("Не знайдено тренера або клієнта.");
                    }
                }
                case 5 -> gym.getVisitors().forEach(System.out::println);
                case 6 -> {
                    gym.exportVisitors("visitors.json");
                    System.out.println("Експорт завершено.");
                }
                case 7 -> {
                    gym.importVisitors("visitors.json");
                    System.out.println("Імпорт завершено.");
                }
                case 0 -> {
                    System.out.println("Завершення роботи.");
                    return;
                }
                default -> System.out.println("Невірна опція.");
            }
        }
    }
}