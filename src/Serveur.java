import Classes.HttpRequest;
import Classes.HttpResponse;
import Classes.HttpService;

import java.io.*;
import java.net.*;

/**
 * Created by Thibault on 30/05/2016.
 */
public class Serveur {
    public static void main(String[] args) throws IOException{
        final int portNumber = 81;
        System.out.println("Creating server socket on port " + portNumber);
        ServerSocket serverSocket = new ServerSocket(portNumber);
        while (true) {
            Socket socket = serverSocket.accept();
            HttpRequest request = new HttpRequest(socket);
            HttpResponse response = new HttpResponse(socket);

            new HttpService().service(request, response);


            /*OutputStream os = response.getOutputStream();
            PrintWriter pw = response.getPrintWriter();

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));*/


            /*OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println("What's your name?");

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String str = br.readLine();

            pw.println("Hello, " + str);
            pw.close();
            socket.close();

            System.out.println("Just said hello to:" + str);*/
        }
    }
}
