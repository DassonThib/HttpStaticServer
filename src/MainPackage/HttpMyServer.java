package MainPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Classes.HttpRequest;
import Classes.HttpResponse;
import Classes.HttpService;
import Classes.Proxy;
import Thread.ThreadPool;

/**
 * Created by Thibault on 01/06/2016.
 */
public class HttpMyServer {
    private int port ;

    private Proxy myProxy;

    public HttpMyServer(int port){
        this.port = port;
    }
    public ThreadPool pool;
    public void run() throws IOException{
        myProxy = new Proxy();

        ServerSocket socket = new ServerSocket(port);
        System.out.println("Creating server socket on port " + port);
        pool = new ThreadPool(16);

        while(true){
            Socket clientSocket = socket.accept();
            System.out.println(myProxy.getPosition());
            myProxy.getServer(myProxy.getPosition()).pool.submit(1, () -> handleRequest(clientSocket));
        }
    }

    public void startAnnexesServer()throws IOException{
        ServerSocket socket = new ServerSocket(port);
        System.out.println("Creating server socket on port " + port);
        pool = new ThreadPool(16);
    }

    public void handleRequest(Socket socket){
        HttpRequest request;
        HttpResponse response;
        System.out.println("serveur sur le port   ==> " + this.port);
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
