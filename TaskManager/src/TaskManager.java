// TaskManager.java
import java.io.*;
import java.util.*;

public class TaskManager {
    private static final String FILE_NAME = "tasks.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasks();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== TASK MANAGER =====");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Done");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addTask(sc);
                case 2 -> viewTasks();
                case 3 -> markTaskDone(sc);
                case 4 -> deleteTask(sc);
                case 5 -> { saveTasks(); System.exit(0); }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void addTask(Scanner sc) {
        System.out.print("Enter task title: ");
        String title = sc.nextLine();
        tasks.add(new Task(title));
        System.out.println("Task added!");
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void markTaskDone(Scanner sc) {
        viewTasks();
        System.out.print("Enter task number to mark done: ");
        int num = sc.nextInt();
        if (num > 0 && num <= tasks.size()) {
            tasks.get(num - 1).markDone();
            System.out.println("Task marked as done!");
        } else System.out.println("Invalid task number.");
    }

    private static void deleteTask(Scanner sc) {
        viewTasks();
        System.out.print("Enter task number to delete: ");
        int num = sc.nextInt();
        if (num > 0 && num <= tasks.size()) {
            tasks.remove(num - 1);
            System.out.println("Task deleted!");
        } else System.out.println("Invalid task number.");
    }

    private static void saveTasks() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                bw.write(task.getTitle() + ";" + task.isDone());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static void loadTasks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                Task t = new Task(data[0]);
                if (Boolean.parseBoolean(data[1])) t.markDone();
                tasks.add(t);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
}
