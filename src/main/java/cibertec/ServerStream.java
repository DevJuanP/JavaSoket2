// Paquete donde vive esta clase (carpeta cibertec)
package cibertec;

// Importamos herramientas para leer/escribir texto (io)
// y para la comunicación por red/sockets (net)
import java.io.*;
import java.net.*;

// Esta clase es el SERVIDOR: escucha conexiones y responde
public class ServerStream {
    // PUERTO = la "puerta" donde el servidor espera.
    // Cliente y servidor deben usar el mismo número.
    private static final int PUERTO = 5000;

    // Punto de entrada: aquí empieza el programa
    public static void main(String[] args) {
        // try-with-resources: crea el ServerSocket y lo cierra solo al terminar.
        // ServerSocket(PUERTO) = "abro la puerta 5000 para escuchar".
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor escuchando en puerto " + PUERTO);

            // Bucle infinito: el servidor trabaja sin detenerse,
            // atendiendo un cliente tras otro.
            while (true) {
                // accept() = esperar quieto hasta que un cliente toque la puerta.
                // Cuando llega, nos devuelve un Socket (el "tubo" de comunicación).
                Socket cliente = serverSocket.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress());

                // ENTRADA: tubería para LEER lo que manda el cliente.
                // getInputStream = datos crudos -> InputStreamReader = a texto -> BufferedReader = leer por líneas.
                BufferedReader entrada = new BufferedReader(
                        new InputStreamReader(cliente.getInputStream())
                );
                // SALIDA: tubería para ESCRIBIR respuesta al cliente.
                // PrintWriter con println() envía líneas de texto. El "true" = enviar de inmediato (auto-flush).
                PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

                // readLine() = leer UNA línea de texto que envió el cliente.
                // Se queda esperando hasta que el cliente escriba algo.
                String mensaje = entrada.readLine();
                System.out.println("Mensaje recibido: " + mensaje);

                // Respondemos al cliente con el prefijo "Echo: " (eco = devolver lo mismo).
                salida.println("Echo: " + mensaje);

                // Cerramos la conexión con ESTE cliente para liberar recursos.
                // El servidor sigue vivo y vuelve al accept() a esperar al siguiente.
                cliente.close();
            }
        } catch (IOException e) {
            // Si algo falla en la red (puerto ocupado, conexión cortada...), mostramos el error.
            e.printStackTrace();
        }
    }
}
