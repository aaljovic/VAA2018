import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;

public class Node
{
    private int id;
    private String ipAddress;
    private int port;

    public Node(int id, String ipAddress, int port)
    {
        this.id = id;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    protected String read(String inputParameter)
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
            System.out.println("Ergebnis:" + idInLine + ipAddress);

        } catch (FileNotFoundException fnfe) {

        }
        catch (IOException ioe){

        }
        return ipAddress;
    }

    protected void setIpAddress(String ipAddress)
    {
        String ip = ipAddress.substring(0, ipAddress.indexOf(":"));
        if (ipAddress.contains(":")) {
            String[] parts = ipAddress.split(":");
            this.ipAddress = parts[0];
            this.port = Integer.parseInt(parts[1]);
        } else {
            throw new IllegalArgumentException("String " + ipAddress + " does not contain :");
        }
    }

    protected void setId(Integer id)
    {
        this.id = id;
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

    protected void getNeighborIds()
    {

    }

}
