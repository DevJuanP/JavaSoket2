package cibertec;

import java.io.*;
import java.net.*;

// Servidor TCP simple: escucha en un puerto y responde a cada mensaje recibido.
public class ServerStream {
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        // Abrimos el puerto y nos quedamos escuchando (se cierra solo al salir del try).
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor escuchando en puerto " + PUERTO);

            // Atiende clientes uno tras otro, sin detenerse.
            while (true) {
                // Espera un cliente y prepara los canales de lectura/escritura.
                Socket cliente = serverSocket.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress());

                BufferedReader entrada = new BufferedReader(
                        new InputStreamReader(cliente.getInputStream())
                );
                PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

                // Lee el mensaje, lo muestra y responde confirmando su recepción.
                String mensaje = entrada.readLine();
                System.out.println("Mensaje recibido: " + mensaje);

                salida.println("Hola cliente, procesare tu mensaje: '" + mensaje + "'");

                cliente.close();
            }
        } catch (Exception e) {
            // Si el puerto está ocupado o se corta una conexión, muestra el error.
            System.out.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
