// Paquete donde vive esta clase (carpeta cibertec)
package cibertec;

// Importamos herramientas para leer/escribir texto (io)
// y para la comunicación por red/sockets (net)
import java.io.*;
import java.net.*;

// Esta clase es el CLIENTE: se conecta al servidor, saluda y escucha la respuesta
public class ClientStream {
    // HOST = dirección del servidor. "localhost" significa "mi propia computadora".
    private static final String HOST = "localhost";
    // PUERTO = la "puerta" donde escucha el servidor. Debe ser el mismo número que en ServerStream.
    private static final int PUERTO = 5000;

    // Punto de entrada: aquí empieza el programa
    public static void main(String[] args) {
        // try-with-resources: abre la conexión y la cierra sola al terminar.
        // new Socket(HOST, PUERTO) = "llamo a la puerta 5000 del servidor".
        // Si el servidor no está encendido, aquí falla y salta al catch.
        try (Socket socket = new Socket(HOST, PUERTO)) {
            System.out.println("Conectado al servidor: " + HOST + ":" + PUERTO);

            // SALIDA: tubería para ESCRIBIR al servidor.
            // PrintWriter con println() envía líneas de texto. El "true" = enviar de inmediato (auto-flush).
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            // ENTRADA: tubería para LEER lo que responde el servidor.
            // getInputStream = datos crudos -> InputStreamReader = a texto -> BufferedReader = leer por líneas.
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            // Enviamos nuestro saludo al servidor (una línea de texto).
            salida.println("Hola desde el cliente!");

            // readLine() = leer UNA línea de respuesta del servidor.
            // Se queda esperando hasta que el servidor conteste.
            String respuesta = entrada.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

            // Cerramos la conexión (el try también la cerraría solo, esto es un refuerzo).
            socket.close();
        } catch (IOException e) {
            // Si algo falla en la red (servidor apagado, puerto equivocado...), mostramos el error.
            e.printStackTrace();
        }
    }
}
