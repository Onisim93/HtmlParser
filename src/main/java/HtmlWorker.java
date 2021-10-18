import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlWorker {

    // Метод сохраняет html страницу в новый файл с названием URL адреса в формате txt
    public static Path saveToFile(String urlAddress) throws IOException, HtmlParserException {
        String htmlPage = readUrl(urlAddress);

        String name = urlAddress.split("://")[1];
        if (name.startsWith("www")) name = name.split("\\.")[1] + ".txt";
        else name = name.split("\\.")[0] + ".txt";


        Path path = Paths.get(new File(name).getAbsolutePath());
        try (FileWriter fileWriter = new FileWriter(path.toFile())) {
            fileWriter.write(htmlPage);
            ConsoleHelper.writeMessage("Данные с сайтa " + urlAddress + " успешно считаны и записаны в файл " + path.getFileName());
        }

        return path.getFileName();
    }

    //Метод считывает URL и возвращает HTML страницу в виде String
    public static String readUrl(String urlAddress) throws IOException, HtmlParserException {
        StringBuilder htmlPage = new StringBuilder();
        URL url = new URL(urlAddress);
        HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
        httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(httpcon.getInputStream()))) {
            while (br.ready()) {
                htmlPage.append(br.readLine()).append("\n");
            }
        }
        if (htmlPage.toString().isEmpty()) {
            throw new HtmlParserException("Не удалось получить доступ к адресу, попробуйте другой URL");
        }
        return htmlPage.toString().trim();
    }

    //Метод проверяет URL адрес на валидность
    public static void checkUrl(String urlAddress) throws HtmlParserException {
        Pattern pattern = Pattern.compile("(http|https)://(www\\.)?.{2,}\\..{2,}/?.*");
        Matcher matcher = pattern.matcher(urlAddress);
        if (!matcher.find()) {
            throw new HtmlParserException("Неверный формат URL адреса");
        }
    }


}
