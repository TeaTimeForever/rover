package utils;

import org.jukito.JukitoRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import rover.utils.CountryFrequencyFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by eq on 08/03/16.
 */
@RunWith(JukitoRunner.class)
public class FrequencyFilterTest extends Assert {
    private final String ERROR_JSON = "{\"error\": \"YOU SHALL NOT PASS [try later]\"}";

    @Test
    public void filterPassedAndDelegated(CountryFrequencyFilter filter) throws IOException, ServletException {
        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse res = Mockito.mock(HttpServletResponse.class);
        FilterChain chain = Mockito.mock(FilterChain.class);
        StringWriter sw = new StringWriter();
        Mockito.when(res.getWriter()).thenReturn(new PrintWriter(sw));

        Mockito.when(req.getMethod()).thenReturn("POST");
        Mockito.when(req.getRemoteAddr()).thenReturn("83.99.135.214");
        filter.doFilter(req, res, chain);
        assertEquals("", sw.getBuffer().toString());
    }

    @Test
    public void filterDidntPassed(CountryFrequencyFilter filter) throws IOException, ServletException {
        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse res = Mockito.mock(HttpServletResponse.class);
        FilterChain chain = Mockito.mock(FilterChain.class);
        StringWriter sw = new StringWriter();
        Mockito.when(res.getWriter()).thenReturn(new PrintWriter(sw));

        Mockito.when(req.getMethod()).thenReturn("POST");
        Mockito.when(req.getRemoteAddr()).thenReturn("83.99.135.214");
        filter.doFilter(req, res, chain);
        filter.doFilter(req, res, chain);
        assertEquals(ERROR_JSON, sw.getBuffer().toString());
    }

    @Test
    public void filterCanWorkAgain(CountryFrequencyFilter filter) throws Exception {
        HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse res = Mockito.mock(HttpServletResponse.class);
        FilterChain chain = Mockito.mock(FilterChain.class);
        StringWriter sw = new StringWriter();
        Mockito.when(res.getWriter()).thenReturn(new PrintWriter(sw));

        Mockito.when(req.getMethod()).thenReturn("POST");
        Mockito.when(req.getRemoteAddr()).thenReturn("83.99.135.214");
        filter.doFilter(req, res, chain);
        Thread.sleep(6000);
        filter.doFilter(req, res, chain);
        assertEquals("", sw.getBuffer().toString());
    }
}
