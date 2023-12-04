import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class servidorTCL {
    private ServerSocket serverSocket;
    private List<Socket> clientes;
    private List<preguntas> preguntas;
    private int preguntaActual;



    public servidorTCL(int puerto) {
        clientes = new ArrayList<>();
        preguntas = new ArrayList<>();
        // Agregar preguntas al listado
        // preguntas.add(new Pregunta("Â¿Pregunta 1?", "Respuesta 1"));
        // ...
        preguntas.add(new preguntas("1. ¿Cúal es la capital de Ecuador ? : ", "Quito"));
        preguntas.add(new preguntas("2. ¿Cúal es la capital de Peru ? : ", "Lima"));
        preguntas.add(new preguntas("3. ¿Cúal es la capital de Venezuela ? : ", "Caracas"));
        preguntas.add(new preguntas("4. ¿Cúal es la capital de Argentina  ? : ", "Buenos Aires"));
        preguntas.add(new preguntas("5. ¿Cúal es la capital de Brazil ? : ", "Sao Paulo"));
        preguntas.add(new preguntas("6. ¿Cúal es la capital de EEUU ? : ", "Washington"));
        preguntas.add(new preguntas("7. ¿Cúal es la capital de Canada ? : ", "Toronto"));
        preguntas.add(new preguntas("8. ¿Cúal es la capital de Italia ? : ", "Roma"));
        preguntas.add(new preguntas("9. ¿Cúal es la capital de Inglaterra  ? : ", "Londres"));
        preguntas.add(new preguntas("10. ¿Cúal es la capital de Francia ? : ", "Paris"));
        try {
            serverSocket = new ServerSocket(puerto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void iniciar() {
        System.out.println("Servidor iniciado. Esperando clientes...");

        while (true) {
            try {
                Socket clienteSocket = serverSocket.accept();
                clientes.add(clienteSocket);
                System.out.println("Cliente conectado desde " + clienteSocket.getInetAddress());

                // Iniciar un hilo para manejar la conexion con el cliente
                Thread hiloCliente = new Thread(new hiloCliente(clienteSocket, this));
                hiloCliente.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized preguntas obtenerSiguientePregunta() {
        if (preguntaActual < preguntas.size()) {
            return preguntas.get(preguntaActual++);
        } else {
            return null; // Todas las preguntas han sido respondidas
        }
    }

    public static void main(String[] args) {
        int puerto = 12345;
        servidorTCL servidor = new servidorTCL(puerto);
        servidor.iniciar();
    }

}