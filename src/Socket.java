import java.util.Random;

public class Socket {
    private final Random r = new Random();
    public String ip;
    private int ping;

    // Sleep for a random duration withing the specified range in mS
    private int sleep(int min, int max) {
        int delay = r.nextInt(min, max);
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < delay) ;
        return delay;
    }

    // Sleep for the specified duration in mS
    private void sleep(int dur) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < dur) ;
    }

    // Check whether the socket is connected or not
    private boolean checkConnection() {
        if (this.ip == null) {
            System.out.println("Error: no active connection");
            return false;
        }
        return true;
    }

    public boolean connect(String ip, int timeOut) {
        System.out.println("Connecting to: " + ip);
        // Define the probability of a successful connection
        double p = 0.6;
        // Randomly choose a round trip time between 15 and 300ms (simulate distance to available servers)
        int delay = sleep(15, 300);
        // Randomly decide whether the connection was successful or not
        if (r.nextDouble() <= p) {
            this.ip = ip;
            this.ping = delay;
            System.out.println("Successfully connected to: " + ip + ", Ping = " + delay);
            return true;
        } else {
            // If the connection was not successful, wait for the timeout period (simulate failed network connection
            // to the specified address)
            sleep(timeOut - delay);
            System.out.println("Request timed out: " + ip);
            return false;
        }
    }

    // Set the default timeout to 1000mS
    public boolean connect(String ip) {
        return connect(ip, 1000);
    }

    // Simulate transmitting data
    public void send(String data) {
        if (checkConnection()) {
            int len = data.length() + 1;
            System.out.println("Transmitting " + len + " bytes to " + this.ip + " ...");
            // Simulate delay for TCP transfer
            sleep((int) (len * ping * 0.15), (int) (len * ping * 0.3));
            System.out.println("Successfully transmitted " + len + " bytes to " + this.ip);
        }
    }

    // Simulate receiving data
    public void receive() {
        if (checkConnection()) {
            int numBytes = r.nextInt(10000000);
            int numPackets = numBytes / 65535;
            if (numPackets % 65535 != 0) {
                numPackets++;
            }
            // Simulate delay for TCP transfer
            sleep((int) (numPackets * ping * 0.15), (int) (numPackets * ping * 0.3));
            System.out.println("Received " + numBytes + " bytes to " + this.ip);
        }
    }

    // Close the connection
    public void disconnect() {
        if (checkConnection()) {
            System.out.println("Disconnected from: " + this.ip);
            this.ip = null;
        }
    }
}