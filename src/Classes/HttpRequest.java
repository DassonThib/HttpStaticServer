package Classes;
import Interface.IHttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Thibault on 30/05/2016.
 */
public class HttpRequest implements IHttpRequest {

    private Socket socket;
    private Map<String, String> cookies;
    private Map<String, String> parameters;
    private Map<String, String> params;
    private String method;
    private String relativePath, absolutePath, name;

    public HttpRequest(Socket s) throws IOException{
        this.cookies = new HashMap<>();
        this.parameters = new HashMap<>();
        this.params = new HashMap<>();
        this.socket = s;
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String str = br.readLine();
        if(str != null) {
            String[] tab = str.split(" ");
            this.method = tab[0];
            String val = tab[1];
            String[] vals = val.split("\\?");
            this.relativePath = vals[0];

            //System.out.println(str);

            String[] param;
            do {
                if ((param = str.split(":")).length > 1) {
                    //System.out.println(str);
                    this.parameters.put(param[0], param[1]);
                    //System.out.println(param[0]);
                    if (param[0].equals("Host")){
                        String[] path;
                        if((path = param[2].split("/")).length > 1){
                            this.absolutePath = path[1];
                        } else {
                            this.absolutePath = "";
                        }
                    }
                    //System.out.println("Clé : " + param[0] + " ,Valeur : " + param[1]);
                }
                if (str.equals("")) {
                    break;
                }
            } while ((str = br.readLine()) != null);
        }
    }

    @Override
    public Object getCookie(String name) {
        return this.cookies.get(name);
    }

    @Override
    public String[] getCookiesNames() {
        return new String[0];
    }

    @Override
    public Object getParameter(String name) {
        return this.parameters.get(name);
    }

    @Override
    public String[] getParametersNames() {
        String[] names = new String[this.parameters.size()];
        Iterator it = this.parameters.keySet().iterator();
        int i = 0;
        while(it.hasNext() != false){
            names[i] = (String) it.next();
            i++;
        }

        return names;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public String getRelativePath() {
        return this.relativePath;
    }

    @Override
    public String getAbsolutePath() {
        return "C:/www/monsite"+this.relativePath;
    }
}
