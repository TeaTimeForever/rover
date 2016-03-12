package resources;

import com.maxmind.geoip2.record.Country;
import org.jukito.JukitoRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import rover.services.GeoService;
import rover.utils.RequestsQueue;

import javax.servlet.ServletException;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by eq on 08/03/16.
 */
@RunWith(JukitoRunner.class)
public class CapacityTest extends Assert {

    private final int capacity = 4,
                      period= 5000;

    @Test
    public void cyclicality(GeoService gs) throws IOException, ServletException {
        RequestsQueue r = new RequestsQueue(capacity, period);
        try {
            Field head = RequestsQueue.class.getDeclaredField("head");
            Field last = RequestsQueue.class.getDeclaredField("last");
            head.setAccessible(true);
            last.setAccessible(true);
            // 0 elem
            assertEquals(-1, head.get(r));
            assertEquals(0, last.get(r));

            r.push(null);
            //1 elem
            assertEquals(0, head.get(r));
            assertEquals(0, last.get(r));

            r.push(null);
            r.push(null);
            r.push(null);

            // full
            assertEquals(3, head.get(r));
            assertEquals(0, last.get(r));

            r.push(null);
            assertEquals(0, head.get(r));
            assertEquals(1, last.get(r));


        } catch (Exception e ){
            e.printStackTrace();
            Assert.fail("Failed during exception");
        }
    }
}
