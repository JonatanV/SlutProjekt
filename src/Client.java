import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author magnus
 */
public class Client {

    public static void main(String[] args) {
        String ip = (String) JOptionPane.showInputDialog(null,"IP?","Connect to..",JOptionPane.QUESTION_MESSAGE);
        int port = Integer.parseInt(JOptionPane.showInputDialog(null,"Port?","Connect to..",JOptionPane.QUESTION_MESSAGE));       ;
        Socket socket = null;

        try {
            socket = new Socket(ip,port);
        } catch (Exception e) {
            System.out.println("Client failed to connect");
            System.exit(0);
        }

        // GO
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(JOptionPane.showInputDialog(null,"Name?","Identify yourself!"));

            String msg = in.readLine();
            JOptionPane.showMessageDialog(null,msg,"Server said",JOptionPane.INFORMATION_MESSAGE);

            String input = " ";
            while (!input.equals("quit")) {

                input=JOptionPane.showInputDialog(null, input,"Say Something",JOptionPane.INFORMATION_MESSAGE);
                System.out.println(input);
                if (input.equals("quit")){
                    in.close();
                    out.close();
                    socket.close();
                    System.out.println("Done!");
                }
            }

            in.close();
            out.close();
            socket.close();
            System.out.println("Done!");
        } catch (Exception e) {
            System.out.println("Client failed to communicate");
        }
    }
}

