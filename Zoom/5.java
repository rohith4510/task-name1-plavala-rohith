package backend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Clean Logic Processing Engine running natively on the Java runtime platform.
 * Establishes an independent connection processing hub over Port 8080.
 */
public class LibraryApplication {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println(" INITIALIZING CORE BACKEND ENGINE SYSTEM INTERFACES");
        System.out.println(" Network Listening State Mode Matrix Active on Port: " + PORT);
        System.out.println("=================================================");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                // Keep block execution listening open to accept incoming stream tunnels
                try (Socket clientSocket = serverSocket.accept()) {
                    handleIncomingStreamRequest(clientSocket);
                } catch (Exception socketException) {
                    System.err.println("Execution Path Request Isolation Error: " + socketException.getMessage());
                }
            }
        } catch (Exception systemException) {
            System.err.println("Fatal Execution Failure: Core Engine Aborted. Details: " + systemException.getMessage());
        }
    }

    private static void handleIncomingStreamRequest(Socket socket) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        
        // Peek at header boundaries
        String directoryRoutingLine = reader.readLine();
        if (directoryRoutingLine == null) return;
        
        System.out.println("Network Frame Signal Capture: " + directoryRoutingLine);

        // Pre-loaded schema record output data dump array format payload matching app.js structure
        String payloadJson = "[" +
            "{\"id\":1001,\"title\":\"Architectural Integrity Vol 1\",\"author\":\"Martina Plantijn\",\"genre\":\"Full Stack Logic\"}," +
            "{\"id\":1002,\"title\":\"Computing Machinery and Logic\",\"author\":\"Alan Turing\",\"genre\":\"Computer Science\"}" +
            "]";

        byte[] payloadBytes = payloadJson.getBytes(StandardCharsets.UTF_8);

        // Formulate standards-compliant HTTP data validation handshake output stream headers
        OutputStream responseBuffer = socket.getOutputStream();
        responseBuffer.write("HTTP/1.1 200 OK\r\n".getBytes(StandardCharsets.UTF_8));
        responseBuffer.write("Content-Type: application/json; charset=UTF-8\r\n".getBytes(StandardCharsets.UTF_8));
        responseBuffer.write(("Content-Length: " + payloadBytes.length + "\r\n").getBytes(StandardCharsets.UTF_8));
        responseBuffer.write("Access-Control-Allow-Origin: *\r\n".getBytes(StandardCharsets.UTF_8)); // Allows seamless Javascript Fetch linkages
        responseBuffer.write("Connection: close\r\n\r\n".getBytes(StandardCharsets.UTF_8));
        
        // Write dataset bytes across network pipe logic execution path
        responseBuffer.write(payloadBytes);
        responseBuffer.flush();
    }
}