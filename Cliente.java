package javaserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) throws IOException {
        // Creamos un socket cliente
        Socket cliente = new Socket("localhost", 9999);

        // Creamos un flujo de entrada para leer del servidor
        BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

        // Creamos un flujo de salida para escribir al servidor
        PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

        // Recibimos las opciones del servidor
        String mensaje = entrada.readLine();
        System.out.println(mensaje);

        // Bucle infinito para enviar mensajes al servidor
        while (true) {
            // Solicitamos al usuario una opción
            System.out.println("Introduce una opción:");
            int opcion = Integer.parseInt(entrada.readLine());

            // Enviamos la opción al servidor
            salida.println(opcion);

            // Recibimos la respuesta del servidor
            mensaje = entrada.readLine();
            System.out.println(mensaje);
        }
    }
}
