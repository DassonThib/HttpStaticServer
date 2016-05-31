package Classes;

import Interface.IHttpRequest;
import Interface.IHttpResponse;
import Interface.IHttpService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Thibault on 30/05/2016.
 */
public class HttpService implements IHttpService {
    @Override
    public void service(IHttpRequest request, IHttpResponse response) {
        String path = request.getAbsolutePath();
        //System.out.println("Absolute path : "+path+"index.html");
        PrintWriter writer = response.getPrintWriter();
        StringBuilder sb = new StringBuilder();
        if(Files.exists(Paths.get(path))) {
            writer.println("HTTP/1.1 200");
            String[] param = request.getParametersNames();
            for(int i = 0; i<param.length; i++){
                writer.println(request.getParameter(param[i]));
            }
            writer.println("");
            if (Files.exists(Paths.get(path + "index.html"))) {
                try {
                    Files.lines(Paths.get(path + "index.html")).forEach(writer::println);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        writer.close();
    }
}
