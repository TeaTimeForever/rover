package utils;

import com.google.api.client.util.Sets;
import org.junit.Assert;
import org.junit.Test;
import rover.utils.JongoFilter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by eq on 08/03/16.
 */
public class JongoFilterTest extends Assert {

    @Test
    public void OneSimpleParam(){
        JongoFilter f = JongoFilter.get();
        f.addParam("aaa", 123);
        f.buildQuery();

        Set<Object> a = new HashSet<>();
        a.add(123);
        Object[] args = Arrays.asList(a).toArray();

        assertEquals("{ aaa: {$in: #}}", f.getQuery());
        assertArrayEquals(args, f.getArgs());
    }


}
