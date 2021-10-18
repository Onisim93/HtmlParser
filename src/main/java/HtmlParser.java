import java.io.IOException;
import java.nio.file.Path;
import org.apache.log4j.Logger;

public class HtmlParser {
    private static final Logger log = Logger.getLogger(HtmlParser.class);

    public static void main(String[] args) {
        HtmlParser parser = new HtmlParser();
        parser.init();
    }


    public String inputUrl() throws HtmlParserException {
        ConsoleHelper.writeMessage("Введите URL адрес html страницы в формате http://site.ru/");
        String urlAddress = ConsoleHelper.readLine();

        //Проверка на валидность введенного адреса
        HtmlWorker.checkUrl(urlAddress);

        return urlAddress;
    }

    public void init() {
        do {
            try {
                Path htmlPage = HtmlWorker.saveToFile(inputUrl());
                WordWorker.wordSearch(htmlPage);
            }
            catch (HtmlParserException | IOException e) {
                ConsoleHelper.writeMessage("ERROR: " + e.getMessage());
                log.error(e.toString());
            }
            ConsoleHelper.writeMessage("Продолжить ввод? y/n");
        }
        while (!ConsoleHelper.readLine().equals("n"));
    }

}
