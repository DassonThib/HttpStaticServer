import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Classes.HttpRequest;
import Classes.HttpResponse;
import Classes.HttpService;
import Thread.ThreadPool;

/**
 * Created by Thibault on 01/06/2016.
 */
public class HttpServer {
    public void run() throws IOException{
        final int port = 81;
        ServerSocket socket = new ServerSocket(port);
        System.out.println("Creating server socket on port " + port);
        ThreadPool pool = new ThreadPool(16);

        while(true){
            Socket clientSocket = socket.accept();
            pool.submit(1, () -> handleRequest(clientSocket));
        }
    }

    public void handleRequest(Socket socket){
        HttpRequest request;
        HttpResponse response;

        try{
            System.out.println(Thread.currentThread().getName());
            request = new HttpRequest(socket);
            response = new HttpResponse(socket);
            response.SetMethod(request.getMethod());
            String[] coukie = request.getCookiesNames();
            for(String aCookieName:coukie){
                response.setCookie(aCookieName, (String) request.getCookie(aCookieName));
            }
            new HttpService().service(request, response);
            socket.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
