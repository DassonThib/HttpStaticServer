package Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Thibault on 31/05/2016.
 */
public class ThreadPool {

    private static int maxThread = 5;

    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(maxThread);

    }

}
