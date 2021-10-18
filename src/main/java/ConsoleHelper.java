import java.util.Scanner;

public class ConsoleHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readLine() {
        return scanner.nextLine();
    }

    public static Integer readInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e) {
            ConsoleHelper.writeMessage("Введен некорректный символ. Попробуйте еще раз");
            return readInt();
        }
    }
}
