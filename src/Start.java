public class Start
{
    public static void main(String[] args)
    {
        Node node = new Node(0, "localhost",1001, null);
        node.readAll();
        Node.readAll();
        /*
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
}
