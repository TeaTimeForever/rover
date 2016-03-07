package rover.utils;

import com.google.inject.Singleton;
import rover.services.GeoService;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eq on 06/03/16.
 */
@Singleton
public class CountryFrequencyFilter implements Filter{

    private final GeoService geoService;

    private Map<String, Date> dictionary;

    @Inject
    public CountryFrequencyFilter(GeoService geoService){
        this.geoService = geoService;
        dictionary = new HashMap<>();
    }

    @Override public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("===========FILTER==========");
        System.out.println(servletRequest.getRemoteAddr());
        System.out.println(servletRequest.getContentType());
        System.out.println(servletRequest.getDispatcherType());
        System.out.println(servletRequest.getProtocol());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override public void destroy() {}
}
