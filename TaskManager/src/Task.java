// Task.java
public class Task {
    private String title;
    private boolean isDone;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public String toString() {
        return (isDone ? "[✔] " : "[ ] ") + title;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return isDone;
    }
}
