import com.esipeng.util.impl.WorkerImpl;

/**
 * Created by esipeng on 5/20/2016.
 */
public class StringWorker extends WorkerImpl<String> {
    public void executeJob(String job) {
        try{
            Thread.sleep(500);
        } catch (InterruptedException e)    {

        }
        System.out.println(job);
    }
}
