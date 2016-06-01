package Thread;

/**
 * Created by Thibault on 31/05/2016.
 */
public class Worker extends Thread {

    private Job job;
    private String command;

    public Worker(Job j, String c){
        this.job = j;
        this.command = c;
    }

    @Override
    public void run() {
        if (this.job != null && this.command != null) {
            System.out.println(Thread.currentThread().getName() + " Start. Command = " + this.command);
            job.execute();
            System.out.println(Thread.currentThread().getName() + " end.");
        }
    }

    public Job getJob(){
        return this.job;
    }

    public void setJob(Job j){
        this.job = j;
    }

    public String getCommand(){
        return this.command;
    }

    public void setCommand(String c){
        this.command = c;
    }
}
