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
        HttpServer server = new HttpServer();
        server.run();
    }
}
