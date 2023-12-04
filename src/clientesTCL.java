import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class clientesTCL {



    public static void main(String[] args) {
        String servidorIP = "localhost";
        int puerto = 12345;

        try {
            Socket socket = new Socket(servidorIP, puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            String pregunta;
            while ((pregunta = entrada.readLine()) != null) {
                System.out.println("Pregunta: " + pregunta);
                System.out.print("Respuesta: ");
                String respuesta = scanner.nextLine();
                salida.println(respuesta);

                String resultado = entrada.readLine();
                System.out.println("Resultado: " + resultado);
            }

            // Fin del juego
            String puntajeFinal = entrada.readLine();
            System.out.println(puntajeFinal);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}