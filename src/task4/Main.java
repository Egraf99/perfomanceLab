package task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // собираем числа в массиве
        ArrayList<Integer> numbers = getNumbers(args[0]);
        // считаем количество повторяющихся чисел в массиве
        HashMap<Integer, Integer> countNumbers = getMapCountNumbers(numbers);
        // находим число, повторяющееся чаще других
        Map.Entry<Integer, Integer> maxCount = countNumbers.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue()).get();
        int result = 0;
        int startPoint;
        if (maxCount.getValue() == 1) {
            // количество чисел в массиве одинаково - в качестве точки отсчета берем медиану
            startPoint = getMedian(numbers);
        } else {
            // есть число в массиве, повторяющееся больше остальных - в качестве точки отсчета берем это число
            startPoint = maxCount.getKey();
        }
        for (int n : numbers) {
            result += Math.abs(n - startPoint);
        }
        System.out.println(result);
    }

    private static HashMap<Integer, Integer> getMapCountNumbers(ArrayList<Integer> numbers) {
        HashMap<Integer, Integer> mapCount = new HashMap<>();
        for (int n: numbers) {
            int value = mapCount.getOrDefault(n, -1);
            if (value == -1) {
                mapCount.put(n, 1);
            } else {
                mapCount.put(n, value+1);
            }
        }
        return mapCount;
    }

    private static Integer getMedian(ArrayList<Integer> numbers) {
        int sum = 0;
        for (int n: numbers) {
            sum += n;
        }
        return sum/numbers.size();
    }

    private static ArrayList<Integer> getNumbers(String fileName) {
        ArrayList<Integer> numbers = new ArrayList<>();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fr);

            // проходим по файлу, добавляя все числа в ArrayList
            String line = reader.readLine();
            while (line != null) {
                numbers.add(Integer.parseInt(line));
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers;
    }
}