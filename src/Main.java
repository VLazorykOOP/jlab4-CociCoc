import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть шлях до тексового файлу: ");
        String filePath = scanner.nextLine();

        // Перевірка наявності файлу
        File file = new File(filePath);
        while (!file.exists()) {
            System.out.println("Файл не знайдено. Спробуйте ще раз.");
            System.out.print("Введіть шлях до тексового файлу: ");
            filePath = scanner.nextLine();
            file = new File(filePath);
        }

        // Підрахунок кількості слів
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int wordCount = 0;
            while ((line = reader.readLine()) != null) {
                wordCount += line.trim().split("\\s+").length;
            }
            System.out.println("Кількість слів у файлі " + filePath + " : " + wordCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}