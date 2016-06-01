package MainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import Classes.HttpRequest;
import Classes.HttpResponse;
import Classes.HttpService;
import Thread.ThreadPool;

/**
 * Created by Thibault on 01/06/2016.
 */
public class HttpMyServer {
    public int port ;
    ServerSocket socket = null;
    public Socket clientSocket;



    public HttpMyServer() throws IOException {
       // myProxy = new Proxy();
    }

    public HttpMyServer(int port) throws IOException {
        this.port = port;
    }

    public ThreadPool pool;

    public void initProxy() throws IOException {

    }
    public void run() throws IOException{

        socket = new ServerSocket(port);
        System.out.println("Creating server socket on port " + port);
        pool = new ThreadPool(16);

        while(true){
            clientSocket = socket.accept();
            pool.submit(1, () -> {
                try {
                    startForward();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
       }
    }

    public void startForward() throws IOException {

        HttpRequest request;
        HttpResponse response;

        Socket forward = new Socket("localhost",82);
        PrintWriter writer = new PrintWriter(forward.getOutputStream(),true);
        PrintWriter writer2 = new PrintWriter(clientSocket.getOutputStream(),true);
        BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String str,str2;
        while ((str = br.readLine()) != null )
        {
            writer.println(str);
            if (str.equals("") )
                break;
        }
        BufferedReader br2 = new BufferedReader(new InputStreamReader(forward.getInputStream()));

        while ((str = br2.readLine()) != null )
        {
            writer2.println(str);
            if (str.equals("") )
                break;
        }

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
