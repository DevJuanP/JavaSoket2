package cibertec;

import java.io.*;
import java.net.*;

// Servidor TCP simple: escucha en un puerto y devuelve ("eco") lo que recibe.
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

                // Lee el mensaje, lo muestra y lo devuelve con el prefijo "Echo: ".
                String mensaje = entrada.readLine();
                System.out.println("Mensaje recibido: " + mensaje);

                salida.println("Echo: " + mensaje);

                cliente.close();
            }
        } catch (IOException e) {
            // Si el puerto está ocupado o se corta una conexión, muestra el error.
            e.printStackTrace();
        }
    }
}
