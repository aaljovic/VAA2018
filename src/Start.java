public class Start
{
    public static void main(String[] args)
    {
        String ipAddress = "";

        System.out.println("Your input" + args[0]);

        Node node = new Node(0, "localhost",1001);
        ipAddress = node.read(args[0]);
        node.setId(Integer.parseInt(args[0]));
        node.setIpAddress(ipAddress);
        node.listenToPort(node.getPort());
    }
}
