package cibertec;

import java.io.*;
import java.net.*;

public class ClientStream {
    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PUERTO)) {
            System.out.println("Conectado al servidor: " + HOST + ":" + PUERTO);

            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            salida.println("Hola desde el cliente!");

            String respuesta = entrada.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
