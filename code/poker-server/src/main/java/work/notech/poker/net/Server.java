package work.notech.poker.net;

import work.notech.poker.logic.Cards;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server {

    private static final int PORT = 10615;

    private Socket[] sockets = new Socket[3];

    private ExecutorService pool = null;

    private boolean running = true;

    public Server() throws IOException {
        ServerSocket server = new ServerSocket(PORT, 3);
        pool = Executors.newCachedThreadPool();

        for (int i = 0; i < 3; i++) {
            try {
                sockets[i] = server.accept();
                pool.execute(new ClientHandler(sockets[i]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        server.close();
        init();
    }

    class ClientHandler implements Runnable {

        private BufferedReader in = null;

        public ClientHandler(Socket socket) throws IOException {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        @Override
        public void run() {
            String msg = "";
            while (running) {
                try {
                    if ((msg = in.readLine()) != null) {
                        sendMsg(msg);
                        if ("init".equals(msg)) {
                            init();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMsg(String msg) {
        for (Socket socket : sockets) {
            sendMsg(msg, socket);
        }
    }

    private void sendMsg(String msg, Socket client) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
            writer.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shutdownAndAwaitTermination() {
        running = false;
        for (int i = 0; i < 3; i++) {
            if (sockets[i] != null) {
                try {
                    sockets[i].close();
                    sockets[i] = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

    public void init() {
        Cards cards = new Cards();

        sendMsg("0", sockets[0]);
        sendMsg(cards.getCards(0).toString(), sockets[0]);
        sendMsg(cards.getHiddenCards().toString(), sockets[0]);

        sendMsg("1", sockets[1]);
        sendMsg(cards.getCards(1).toString(), sockets[1]);
        sendMsg(cards.getHiddenCards().toString(), sockets[1]);

        sendMsg("2", sockets[2]);
        sendMsg(cards.getCards(2).toString(), sockets[2]);
        sendMsg(cards.getHiddenCards().toString(), sockets[2]);

        sendMsg("start:" + (int) (Math.random() * 3));
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }
}
