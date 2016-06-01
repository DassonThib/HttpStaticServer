package Classes;

import MainPackage.HttpMyServer;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

/**
 * Created by 626 on 01/06/2016.
 */

public class Proxy {

    List<Socket> serverList;
    int compteur = 0;

    public Proxy() throws IOException {
        serverList = new Vector<>();
        init();
    }

    private void init() throws IOException {


    }

    public HttpMyServer getServer(int position){
        return null;
    }
    public int getPosition() {
        compteur++;
        if (compteur == 6) {
            compteur = 0;
            return compteur;
        }
        return compteur - 1;
    }
}
