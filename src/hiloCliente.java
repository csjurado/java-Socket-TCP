import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class hiloCliente implements Runnable {

    private Socket clienteSocket;
    private servidorTCL servidor;

    public hiloCliente(Socket clienteSocket, servidorTCL servidor) {
        this.clienteSocket = clienteSocket;
        this.servidor = servidor;
    }
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true);

            // Comunicacion con el cliente
            preguntas pregunta = servidor.obtenerSiguientePregunta();
            int puntaje = 0;

            while (pregunta != null) {
                // Enviar la pregunta al cliente
                salida.println(pregunta.getEnunciado());

                // Recibir la respuesta del cliente
                String respuestaCliente = entrada.readLine();

                // Evaluar la respuesta y enviar el resultado al cliente
                if (respuestaCliente.equalsIgnoreCase(pregunta.getRespuestaCorrecta())) {
                    salida.println("Correcto");
                    puntaje = puntaje + 10 ;
                } else {
                    salida.println("Incorrecto");
                }

                // Obtener la siguiente pregunta
                pregunta = servidor.obtenerSiguientePregunta();
            }

            // Informar al cliente sobre el fin del juego y cerrar la conexiÃ³n
            salida.println("Fin del juego. Puntuacion: "+puntaje + " puntos / sobre 100");
            clienteSocket.close();
            System.out.println("Cliente Desconectado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
