package javacourse.net;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 8; i++) {
            SimpleClient sc = new SimpleClient(i);
            sc.start();
        }
    }
}

class SimpleClient extends Thread {

    private final static String[] COMMAND = {
            "HELLO", "MORNING", "DAY", "EVENING"
    };

    private int cmdNumber;

    public SimpleClient(int cmdNumber) {
        this.cmdNumber = cmdNumber;
    }

    @Override
    public void run() {
        try {
//            System.out.println("Started: " + LocalDateTime.now());
            Socket socket = new Socket("localhost", 25225);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String command = COMMAND[cmdNumber % COMMAND.length];
            String sb = command + " " + "Noname";
            bw.write(sb);
            bw.newLine();
            bw.flush();

            String answer = br.readLine();
            System.out.println(answer);

            br.close();
            bw.close();

            socket.close();
//            System.out.println("Finished: " + LocalDateTime.now());
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
