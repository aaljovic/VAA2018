import java.util.Scanner;

public class Initiator
{
    static final String SELECTION_MENU = "Menü" + "\n" +
            "1. Einen bestimmten Knoten starten" + "\n" +
            "2. Alle Knoten starten " + "\n" +
            "3. Sende Nachricht" + "\n";


    public static void main(String[] args)
    {
        System.out.print(SELECTION_MENU);
        System.out.println("Treffen Sie eine Eingabe");
        Scanner sc = new Scanner(System.in);

        String input = sc.next();
        System.out.println("Sie haben " + input + " eingegeben.");
        switch(input)
        {
            case "1": Node.read(chooseNodeId());
                break;
            case "2":
                break;
            case "3": sendMessageTo();
                break;
            default: System.out.println("Eingabe unzutreffend");
        }
    }

    private static String chooseNodeId()
    {
        System.out.println("Geben Sie die ID des Knotens an: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        return input;
    }

    private static void sendMessageTo()
    {
        System.out.println("An welche ID soll die Nachricht gesendet werden?");
        Scanner sc = new Scanner(System.in);
        String id = sc.next();
        System.out.println("Wie lautet die Nachricht?");
        String message = sc.next();
        Node.sendMessage(id, message);
    }
}
