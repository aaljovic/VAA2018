import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

public class Initiator
{
    public static void main(String[] args)
    {
        while (true)
        {
            System.out.print(Constants.SELECTION_MENU);
            Scanner sc = new Scanner(System.in);

            String input = sc.next();
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
        System.out.println(Constants.REQUEST_ID_INPUT);
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        return input;
    }

    private static void sendMessageTo()
    {
        System.out.println(Constants.REQUEST_ID_INPUT);
        Scanner sc = new Scanner(System.in);
        String id = sc.next();
        System.out.println("Wie lautet die Nachricht?");
        JSONObject controlMessage = null;
        try
        {
            controlMessage = new JSONObject(sc.next());
        }
        catch (JSONException jsonE)
        {
            System.err.println(Constants.JSON_ERROR + jsonE);
        }
        Node.sendMessage(Integer.parseInt(id), controlMessage);
    }

    private static void closeNode()
    {
        System.out.println(Constants.REQUEST_ID_INPUT);
        Scanner sc = new Scanner(System.in);
        String id = sc.next();
        JSONObject controlMessage = null;
        try
        {
            controlMessage = new JSONObject(Constants.STOP_MESSAGE);
        }
        catch (JSONException jsonE)
        {
            System.err.println(Constants.JSON_ERROR + jsonE);
        }
        Node.sendMessage(Integer.parseInt(id), controlMessage);
    }

    private static void closeAllNodes()
    {
        int[] allIDs = Node.getAllIds();
        JSONObject controlMessage = null;
        try
        {
            controlMessage = new JSONObject(Constants.STOP_MESSAGE);
        }
        catch (JSONException jsonE)
        {
            System.err.println(Constants.JSON_ERROR + jsonE);
        }
        for (int i=0; i< allIDs.length; i++)
        {
            Node.sendMessage(allIDs[i], controlMessage);
        }
    }
}
