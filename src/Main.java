import java.util.ArrayList;

public class Main {
    private static Socket connectionSocket = new Socket();

    public static void main(String[] args) throws InterruptedException {
        final ThreadGroup threadGroup = new ThreadGroup("connections");
        ArrayList<Thread> threads = new ArrayList<>();
        DNS dns = new DNS();
        // Lookup the addresses for the desired domain
        for (String ip : dns.lookUp("website.com")) {
            // For each returned address, create a thread and try to establish a connection
            Thread thread = new Thread(threadGroup, () -> {
                Socket soc = new Socket();
                if (soc.connect(ip)) {
                    synchronized (connectionSocket) {
                        // If no prior connection has been established, set the current socket to be the socket
                        // used for future communications
                        if (connectionSocket.ip == null) {
                            connectionSocket = soc;
                        } else {
                            // If a prior connection exists, close the current socket and exit
                            soc.disconnect();
                        }
                    }
                }
            });
            thread.start();
            threads.add(thread);
        }
        // Wait until either all connections have timed out, or a successful connection has been established
        while (threadGroup.activeCount() > 0 && connectionSocket.ip == null) ;
        // If all connection attempts failed, print out a message and exit
        if (connectionSocket.ip == null) {
            System.out.println("Failed to establish a connection!");
        } else {
            // If a connection was made successfully, transmit and receive some data before closing the connection
            System.out.println("Connected established to: " + connectionSocket.ip);
            connectionSocket.send("Hello world!");
            connectionSocket.receive();
            connectionSocket.disconnect();
        }
        // Wait for all connections to close
        for (Thread t : threads) {
            t.join();
        }
    }
}