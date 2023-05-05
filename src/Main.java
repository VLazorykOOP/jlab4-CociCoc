import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введіть ім'я файлу зі списком слів: ");
            String wordListFileName = getExistingFileName(scanner);

            System.out.print("Введіть ім'я файлу з текстом: ");
            String textFileName = getExistingFileName(scanner);

            // Зчитування списку слів з файлу
            Set<String> words = new HashSet<>();
            BufferedReader wordListReader = new BufferedReader(new FileReader(wordListFileName));
            String line;
            while ((line = wordListReader.readLine()) != null) {
                words.add(line.trim());
            }
            wordListReader.close();

            // Пошук слів у тексті та підрахунок входжень
            Map<String, Integer> wordCount = new HashMap<>();
            BufferedReader textReader = new BufferedReader(new FileReader(textFileName));
            while ((line = textReader.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                for (String token : tokens) {
                    if (words.contains(token)) {
                        int count = wordCount.containsKey(token) ? wordCount.get(token) : 0;
                        wordCount.put(token, count + 1);
                    }
                }
            }
            textReader.close();

            // Сортування слів за кількістю входжень та вивід результатів
            List<Map.Entry<String, Integer>> wordCountList = new ArrayList<>(wordCount.entrySet());
            wordCountList.sort(Map.Entry.<String, Integer>comparingByValue().reversed());
            System.out.println("Слова, за якими здійснювався пошук:");
            for (Map.Entry<String, Integer> entry : wordCountList) {
                System.out.printf("%s: %d%n", entry.getKey(), entry.getValue());
            }

            // Запис результатів у файл
            System.out.print("Введіть ім'я файлу для збереження результатів: ");
            String resultFileName = scanner.nextLine();
            PrintWriter resultWriter = new PrintWriter(new FileWriter(resultFileName));
            for (Map.Entry<String, Integer> entry : wordCountList) {
                resultWriter.printf("%s: %d%n", entry.getKey(), entry.getValue());
            }
            resultWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getExistingFileName(Scanner scanner) {
        String fileName = "";
        while (true) {
            fileName = scanner.nextLine();
            File file = new File(fileName);
            if (file.exists()) {
                break;
            } else {
                System.out.println("Файл не знайдено. Введіть інше ім'я файлу:");
            }
        }
        return fileName;
    }
}
