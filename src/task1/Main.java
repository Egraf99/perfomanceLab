package task1;

public class Main {
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int step = Integer.parseInt(args[1]);

        int current = 1;
        do {
            System.out.print(current);
            current = ((current + step - 1) % size);
            if (current == 0) current = size;
        } while (current != 1);

    }
}