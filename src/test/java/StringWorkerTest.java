/**
 * Created by esipeng on 5/20/2016.
 */
import org.junit.Test;

public class StringWorkerTest {

    @Test
    public void stringWorkerNormalWorking() {
        StringWorker worker = new StringWorker();
        worker.start();


        worker.post("abc");
        worker.post("def");

        try{
            Thread.sleep(2000);
        } catch (InterruptedException e)    {

        }

        worker.stop();
        worker.join();
    }
}
