package task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // получаем координаты центра окружности и радиуса
        float[] circle = getCircle(args[0]);
        // получаем список координатов точек
        ArrayList<float[]> points = getPoint(args[1]);

        for (float[] point : points) {
            float left = (circle[0] - point[0]) * (circle[0] - point[0]) + (circle[1] - point[1]) * (circle[1] - point[1]);
            float square_rad = circle[2] * circle[2];
            if (left < square_rad) {
                System.out.println("1 - точка внутри");
            } else if (left == square_rad) {
                System.out.println("0 - точка лежит на окружности");
            } else {
                System.out.println("2 - точка снаружи");
            }
        }
    }

    private static float[] getCircle(String fileName) {
        float[] circle = new float[3];
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fr);

            // строка с координатами окружности
            String[] _circle = reader.readLine().split(" ");
            circle[0] = Float.parseFloat(_circle[0]);
            circle[1] = Float.parseFloat(_circle[1]);

            // строка с радиусом
            circle[2] = Float.parseFloat(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return circle;
    }

    private static ArrayList<float[]> getPoint(String fileName) {
        ArrayList<float[]> points = new ArrayList<>();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fr);

            // проходим по файлу и читаем из него все координаты точек
            String line = reader.readLine();
            while (line != null) {
                // координаты точки
                String[] _circle = line.split(" ");
                float[] point = new float[2];
                point[0] = Float.parseFloat(_circle[0]);
                point[1] = Float.parseFloat(_circle[1]);
                points.add(point);

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }
}