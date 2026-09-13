package cibertec;

import java.io.*;
import java.net.*;

public class ServerStream {
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor escuchando en puerto " + PUERTO);

            while (true) {
                Socket cliente = serverSocket.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress());

                BufferedReader entrada = new BufferedReader(
                        new InputStreamReader(cliente.getInputStream())
                );
                PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

                String mensaje = entrada.readLine();
                System.out.println("Mensaje recibido: " + mensaje);

                salida.println("Echo: " + mensaje);

                cliente.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
