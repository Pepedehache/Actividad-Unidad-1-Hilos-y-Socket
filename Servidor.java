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
            // Aceptamos una nueva conexi�n
            Socket cliente = servidor.accept();

            // Creamos un hilo para atender la conexi�n
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
            salida.println("2. Adi�s");
            salida.println("3. �C�mo est�s?");
            salida.println("4. �Qu� tal el tiempo?");
            salida.println("5. �Cu�l es tu nombre?");

            // Bucle infinito para recibir mensajes del cliente
            while (true) {
                // Recibimos el mensaje del cliente
                String mensaje = entrada.readLine();

                // Procesamos el mensaje del cliente
                switch (mensaje) {
                case "1":
                    salida.println("Hola a ti tambi�n");
                    break;
                case "2":
                    salida.println("Adi�s");
                    break;
                case "3":
                    salida.println("Estoy bien, �y t�?");
                    break;
                case "4":
                    salida.println("El tiempo est� bien, �no?");
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
