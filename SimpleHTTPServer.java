import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Only limitation of this server is that it can serve one client at a time
 * This problem can be solved by using threads or Java NIO no blocking selectors and channels
 */
public class SimpleHTTPServer {
    public static void main(String[] args) throws Exception {
        final ServerSocket server = new ServerSocket(8080);
        System.out.println("listening for connection on port 8080");
        while (true) {
            /*
            // spin forever
            final Socket client = server.accept();
            // 1. Read HTTP request from the client socket
            // 2. Prepare an HTTP response
            // 3. Send HTTP response to the client
            // 4. Close the socket
            InputStreamReader isr = new InputStreamReader(client.getInputStream());
            // Because browser send
            BufferedReader reader = new BufferedReader(isr);
            String line = reader.readLine();
            while (!line.isEmpty()) {
                System.out.println(line);
                line = reader.readLine();
            }
             */
            // To send response, need to get the output stream from socket and then we will write HTTP response
            try (Socket socket = server.accept()) {
                Date today = new Date();
                String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
                socket.getOutputStream().write(httpResponse.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}
