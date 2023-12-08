package javaserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private static final int PUERTO = 9999;

    public static void main(String[] args) throws IOException {
        // Creamos un socket servidor
        ServerSocket servidor = new ServerSocket(PUERTO);

        // Bucle infinito para aceptar nuevas conexiones
        while (true) {
            // Aceptamos una nueva conexión
            Socket cliente = servidor.accept();

            // Creamos un hilo para atender la conexión
            Thread hilo = new Thread(new HiloCliente(cliente));
            hilo.start();
        }
    }
}

class HiloCliente implements Runnable {

    private Socket cliente;

    public HiloCliente(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            // Creamos un flujo de entrada para leer del cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

            // Enviamos las opciones al cliente
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
            salida.println("Opciones:");
            salida.println("1. Hola");
            salida.println("2. Adiós");
            salida.println("3. ¿Cómo estás?");
            salida.println("4. ¿Qué tal el tiempo?");
            salida.println("5. ¿Cuál es tu nombre?");

            // Bucle infinito para recibir mensajes del cliente
            while (true) {
                // Recibimos el mensaje del cliente
                String mensaje = entrada.readLine();

                // Procesamos el mensaje del cliente
                switch (mensaje) {
                case "1":
                    salida.println("Hola a ti también");
                    break;
                case "2":
                    salida.println("Adiós");
                    break;
                case "3":
                    salida.println("Estoy bien, ¿y tú?");
                    break;
                case "4":
                    salida.println("El tiempo está bien, ¿no?");
                    break;
                case "5":
                    salida.println("Mi nombre es ChatBot");
                    break;
                default:
                    salida.println("Lo siento, no entiendo tu mensaje");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
