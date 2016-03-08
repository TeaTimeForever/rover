package utils;

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
    public void noParams(){
        JongoFilter f = JongoFilter.get().buildQuery();
        assertEquals("{}", f.getQuery());
        assertArrayEquals(new Object[]{}, f.getArgs());
    }

    @Test
    public void oneSimpleParam(){
        JongoFilter f = JongoFilter.get()
                .addParam("aaa", 123)
                .buildQuery();

        Set<Object> a = new HashSet<>();
        a.add(123);
        Object[] args = Arrays.asList(a).toArray();

        assertEquals("{ aaa: {$in: #}}", f.getQuery());
        assertArrayEquals(args, f.getArgs());
    }

    @Test
    public void manySimpleParams(){
        JongoFilter f = JongoFilter.get()
                .addParam("aaa", 1)
                .addParam("bbb", 2)
                .buildQuery();

        Set<Object> firstParam = new HashSet<>();
        firstParam.add(1);
        Set<Object> secondParam = new HashSet<>();
        secondParam.add(2);
        Object[] args = Arrays.asList(firstParam, secondParam).toArray();

        assertEquals("{ aaa: {$in: #}, bbb: {$in: #}}", f.getQuery());
        assertArrayEquals(args, f.getArgs());
    }

    @Test
    public void complexParams(){
        JongoFilter f = JongoFilter.get()
                .addParam("aaa", 1)
                .addParam("bbb", Arrays.asList("nana"))
                .buildQuery();

        Set<Object> firstParam = new HashSet<>();
        firstParam.add(1);

        Set<Object> secondParam = new HashSet<>();
        secondParam.add(Arrays.asList("nana"));

        Object[] args = Arrays.asList(firstParam, secondParam).toArray();

        assertEquals("{ aaa: {$in: #}, bbb: {$in: #}}", f.getQuery());
        assertArrayEquals(args, f.getArgs());

    }


}
