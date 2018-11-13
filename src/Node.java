import com.sun.security.ntlm.Server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Node
{

    public static void main(String[] args)
    {
        if (args.length == 1)
        {
            Node node = read(args[0]);
            node.listenToPort(node.getPort());
            node.setRandomNeighbours(node);
        }
        else
        {
            System.out.println("Ungültige Eingabe." + "\n" + "Starten Sie das Programm neu mit der gewünschten Knoten ID.");
        }


        /*
        Node.readAll();
        String ipAddress = "";

        System.out.println("Your input: " + args[0]);

        Node node = new Node(0, "localhost",1001);
        node = node.read(args[0]);
        node.listenToPort(node.getPort());
        node = node.read(args[1]);
        node = node.read(args[2]);
        node = node.read(args[3]);
        */
    }

    static final int LENGTH_NODE_ARRAY = 1000;

    private int id;
    private String ipAddress;
    private int port;
    private String[] neighbourNodes;

    public Node(int id, String ipAddress, int port, String[] neighbourNodes)
    {
        this.id = id;
        this.ipAddress = ipAddress;
        this.port = port;
        this.neighbourNodes = neighbourNodes;
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
        }
        catch (FileNotFoundException fnfe)
        {
            System.err.println("Datei nicht gefunden" + fnfe);
        }
        catch (IOException ioe)
        {
            System.err.println("Fehler" + ioe);
        }
        catch (NullPointerException npe)
        {
            System.err.println("Die Eingegebene ID existiert nicht. Nullpointer Exception: " + npe);
        }
        String[] parts = ipAddress.split(":");
        Node node = new Node(Integer.parseInt(idInLine), parts[0], Integer.parseInt(parts[1]), null);
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
                nodeArray[i] = new Node(Integer.parseInt(idInLine), parts[0], Integer.parseInt(parts[1]), null);
                nodeArray[i].listenToPort(nodeArray[i].getPort());
                System.out.println(nodeArray[i].id + nodeArray[i].ipAddress + nodeArray[i].port);
                i++;
            }
        }
        catch (FileNotFoundException fnfe)
        {
            System.err.println("Datei nicht gefunden" + fnfe);
        }
        catch (IOException ioe)
        {
            System.err.println("Fehler" + ioe);
        }
        return nodeArray;
    }

    protected int getPort()
    {
        return this.port;
    }

    protected String getIpAddress()
    {
        return this.ipAddress;
    }

    protected ServerSocket listenToPort(int port)
    {
        ServerSocket server = null;
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server hört zu...");
            Socket socket = server.accept();

            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            System.out.println(message);
        }
        catch(IOException ioe)
        {
            System.err.println("Fehler" + ioe);
        }
        return server;
    }

    protected static void sendMessage(String id, String message)
    {
        try
        {
            Socket clientSocket = new Socket(InetAddress.getLocalHost(), read(id).getPort());

            OutputStream os = clientSocket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date());
            message = timeStamp + "\t" + message;
            bw.write(message);
            bw.flush();
            clientSocket.close();

        }
        catch(UnknownHostException uhe)
        {
            System.err.println("Host ist unbekannt: " + uhe);
        }
        catch (IOException ioe)
        {
            System.err.println("Fehler" + ioe);
        }
    }

    protected void sendIdToNeighbours()
    {
        for (int i= 0; i<this.neighbourNodes.length; i++)
        {

        }

    }

    protected String[] setRandomNeighbours(Node node)
    {
        String line = "";
        String idInLine = "";
        String[] idAllLines = new String[LENGTH_NODE_ARRAY];
        String[] randomNeighbours = new String[3];
        int numberOfId = 0;
        int randomIndex = 0;
        Random generator = new Random();
        List<Integer> assignedNodes = new ArrayList<>();

        try {
            FileReader fr = new FileReader("D:\\GitHub Projekte\\VAA2018\\inputFiles\\inputTextFile");
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null)
            {
                idInLine = line.substring(0, line.indexOf(" "));
                idAllLines[numberOfId] = idInLine;
                numberOfId++;
            }
        }
        catch (FileNotFoundException fnfe)
        {
            System.err.println("Datei nicht gefunden" + fnfe);
        }
        catch (IOException ioe)
        {
            System.err.println("Fehler" + ioe);
        }
        String[] existingIdArray = Arrays.copyOfRange(idAllLines, 0, numberOfId);
        assignedNodes.add(node.id);
        for (int j=0; j<3; j++)
        {
            randomIndex = generator.nextInt(existingIdArray.length);

            if (assignedNodes.contains(randomIndex))
            {
                j--;
            }
            else
            {
                randomNeighbours[j] = existingIdArray[randomIndex];
                System.out.println(randomNeighbours[j]);
                assignedNodes.add(randomIndex);
            }
        }
        return randomNeighbours;
    }

}
