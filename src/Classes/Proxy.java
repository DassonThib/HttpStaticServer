package Classes;

import MainPackage.HttpMyServer;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

/**
 * Created by 626 on 01/06/2016.
 */
public class Proxy {

    List<HttpMyServer> serverList;
    int compteur = 0;

    public Proxy() throws IOException {
        serverList = new Vector<>();
        init();

    }

    private void init() throws IOException {
        for (int i =0; i<5; i++)
            serverList.add(new HttpMyServer(82+i));
        for (int i =0; i<5; i++)
            serverList.get(i).startAnnexesServer();

    }

    public HttpMyServer getServer(int position){
        return serverList.get(position);
    }
    public int getPosition(){
        compteur++;
        if(compteur == 6){
            compteur = 0;
            return  compteur;
        }
        return compteur-1;
    }
}
