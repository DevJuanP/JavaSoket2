package cibertec;

import java.io.*;
import java.net.*;

// Cliente TCP simple: se conecta al servidor, envía un saludo y muestra la respuesta.
public class ClientStream {
    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        // Se conecta al servidor (falla si el servidor no está encendido).
        try (Socket socket = new Socket(HOST, PUERTO)) {
            System.out.println("Conectado al servidor: " + HOST + ":" + PUERTO);

            // Canales para enviar texto y leer la respuesta.
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            // Envía el saludo y espera la respuesta del servidor.
            salida.println("Hola desde el cliente!");

            String respuesta = entrada.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

            socket.close();
        } catch (IOException e) {
            // Si el servidor está apagado o el puerto es incorrecto, muestra el error.
            e.printStackTrace();
        }
    }
}
