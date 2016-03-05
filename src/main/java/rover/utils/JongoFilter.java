package rover.utils;

import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by eq on 05/03/16.
 */
public class JongoFilter {
    private Map<String, Set<Object>> filter;
    private String query;
    private Object[] args;

    private JongoFilter(){
        filter = new HashMap<>();
    }

    public String getQuery() {
        return query;
    }

    public Object[] getArgs() {
        return args;
    }

    public JongoFilter addParam(String k, Object v) {
        return addParam(k, v, false);
    }

    public JongoFilter addParam(String k, Object v, boolean nullable) {
        if (nullable || v != null ) {
            Set<Object> val = filter.get(k);
            if(val == null) {
                val = new HashSet<>();
                filter.put(k, val);
            }
            val.add(v);
        }
        return this;
    }

    public static JongoFilter get(){
        return new JongoFilter();
    }

    public JongoFilter buildQuery(){
        StringBuilder query = new StringBuilder();
        List<Object> args = new ArrayList<>();
        filter.entrySet().forEach(e -> {
            query.append(", " + e.getKey() + ": {$in: #}");
            args.add(e.getValue());
        });
        if(filter.size() > 0) {
            query.replace(0,1,"{");
        } else {
            query.append("{");
        }
        query.append("}");

        this.query = query.toString();
        this.args = args.toArray();
        return this;
    }
}
