package services;

import org.jongo.Jongo;
import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import testHelpers.TestModule;

/**
 * Created by eq on 08/03/16.
 */
@RunWith(JukitoRunner.class)
@UseModules(TestModule.class)
public class LoanServiceTest extends Assert {
    private final String LOANS = "loans";
    private final String CUSTOMERS = "customers";
    private final String BLACKLIST = "blacklist";

    @Before
    public void cleanDB(Jongo jongo){
        jongo.getCollection(LOANS).drop();
        jongo.getCollection(CUSTOMERS).drop();
        jongo.getCollection(BLACKLIST).drop();
    }

    @Test
    public void injectionTest(){
        assertEquals("foobar", "foobar");
    }
}
