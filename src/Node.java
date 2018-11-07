import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;


public class Node
{
    static final int LENGTH_NODE_ARRAY = 1000;

    private int id;
    private String ipAddress;
    private int port;

    public Node(int id, String ipAddress, int port)
    {
        this.id = id;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    protected static Node read(String inputParameter)
    {
        String line = "";
        String idInLine = "";
        String ipAddress = "";

        try {
            FileReader fr = new FileReader("D:\\GitHub Projekte\\VAA2018\\inputFiles\\inputTextFile");
            BufferedReader br = new BufferedReader(fr);

            while (((line = br.readLine()) != null) && (!idInLine.equals(inputParameter)))
            {
                idInLine = line.substring(0, line.indexOf(" "));
            }
            ipAddress = line.substring(line.indexOf(" "));
        } catch (FileNotFoundException fnfe) {

        }
        catch (IOException ioe){

        }
        String[] parts = ipAddress.split(":");
        Node node = new Node(Integer.parseInt(idInLine), parts[0], Integer.parseInt(parts[1]));
        System.out.println(node.id + node.ipAddress + node.port);
        return node;
    }

    public static Node[] readAll()
    {
        String line = "";
        String idInLine = "";
        String ipAddress = "";
        Node[] nodeArray = new Node[LENGTH_NODE_ARRAY];
        int i = 0;

        try {
            FileReader fr = new FileReader("D:\\GitHub Projekte\\VAA2018\\inputFiles\\inputTextFile");
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null)
            {
                idInLine = line.substring(0, line.indexOf(" "));
                ipAddress = line.substring(line.indexOf(" "));
                String[] parts = ipAddress.split(":");
                nodeArray[i] = new Node(Integer.parseInt(idInLine), parts[0], Integer.parseInt(parts[1]));
                nodeArray[i].listenToPort(nodeArray[i].getPort());
                System.out.println(nodeArray[i].id + nodeArray[i].ipAddress + nodeArray[i].port);
                i++;
            }

        } catch (FileNotFoundException fnfe) {

        }
        catch (IOException ioe){

        }
        return nodeArray;
    }

    protected int getPort()
    {
        return this.port;
    }

    protected void listenToPort(int port)
    {
        try
        {
            ServerSocket server = new ServerSocket(port);
        }
        catch(IOException ioe)
        {

        }
    }
}
