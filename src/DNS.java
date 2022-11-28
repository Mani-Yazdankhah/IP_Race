import java.util.ArrayList;
import java.util.Random;

public class DNS {
    private final Random r = new Random();

    public ArrayList<String> lookUp(String domain) {
        System.out.println("Looking up addresses for: " + domain);
        ArrayList<String> ipList = new ArrayList<>();
        String ip;
        // Randomly choose the number of IPs to return (min:1, max:15)
        for (int i = 0; i < r.nextInt(1, 15); i++) {
            // Randomly choose between returning IPV4 and IPV6 addresses
            if (r.nextBoolean()) {
                // IPV4 format
                ip = r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
            } else {
                // IPV6 format
                ip = Integer.toHexString(r.nextInt(65536)) + ":" + Integer.toHexString(r.nextInt(65536)) + ":" +
                        Integer.toHexString(r.nextInt(65536)) + ":" + Integer.toHexString(r.nextInt(65536)) + ":" +
                        Integer.toHexString(r.nextInt(65536)) + ":" + Integer.toHexString(r.nextInt(65536)) + ":" +
                        Integer.toHexString(r.nextInt(65536)) + ":" + Integer.toHexString(r.nextInt(65536));
            }
            ipList.add(ip);
        }
        return ipList;
    }
}