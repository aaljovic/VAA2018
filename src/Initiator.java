import java.util.Scanner;

public class Initiator
{
    public static void main(String[] args)
    {
        while (true)
        {
            System.out.print(Constants.SELECTION_MENU);
            System.out.println("Treffen Sie eine Eingabe");
            Scanner sc = new Scanner(System.in);

            String input = sc.next();
            System.out.println("Sie haben " + input + " eingegeben.");
            switch (input)
            {
                case "1":
                    sendMessageTo();
                    break;
                case "2":
                    closeNode();
                    break;
                case "3":
                    closeAllNodes();
                    break;
                default:
                    System.out.println("Eingabe unzutreffend");
            }
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
        Node.sendMessage(Integer.parseInt(id), message);
    }

    private static void closeNode()
    {
        System.out.println("Welcher knoten soll beendet werden?");
        Scanner sc = new Scanner(System.in);
        String id = sc.next();
        Node.sendMessage(Integer.parseInt(id), Constants.STOP_MESSAGE);
    }

    private static void closeAllNodes()
    {
        int[] allIDs = Node.getAllIds();
        for (int i=0; i< allIDs.length; i++)
        {
            Node.sendMessage(allIDs[i], Constants.STOP_MESSAGE);
        }
    }
}
