import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;


public class WordWorker {

    public static void wordSearch(Path path) throws IOException {
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> wordsList = new HashMap<>();

        // Считываю все строки из файла в StringBuilder
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            while (br.ready()) {
                sb.append(br.readLine());
            }
        }

        //Поиск всех слов в тексте, по условию учитываются только русские слова.
        String[] words = sb.toString().replaceAll("[^а-яА-Я]", " ").split(" ");

        //Перебираем массив и добавляем слова в map, каждое слово проверяем на уникальность.
        //Во избежание дубликатов переводим слово в верхний регистр
        Arrays.asList(words).forEach(w -> addWord(wordsList, w.toUpperCase()));

        for (Map.Entry<String, Integer> entry : wordsList.entrySet()) {
            ConsoleHelper.writeMessage(entry.getKey() + " - " + entry.getValue());
        }

        ConsoleHelper.writeMessage("Анализ html страницы завершен\n" +
                "Всего уникальных слов найдено - " + wordsList.size());


    }

    private static void addWord(Map<String, Integer> map, String word) {
        if (word.isEmpty()) {
            return;
        }
        //Если слово уже содержится в словаре, то инкрементируем кол-во повторений.
        if (map.containsKey(word)) {
            int x = map.get(word);
            map.put(word, x+1);
        }
        //Если слово в словаре не найдено, то добавляем новую запись.
        else {
            map.put(word, 1);
        }
    }
}
