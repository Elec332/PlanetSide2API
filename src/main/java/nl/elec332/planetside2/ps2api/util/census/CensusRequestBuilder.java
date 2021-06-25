package nl.elec332.planetside2.ps2api.util.census;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by Elec332 on 25/04/2021
 */
public class CensusRequestBuilder {

    private String request = "";

    public CensusRequestBuilder objects(String name, Collection<?> values) {
        return object(name, values.stream().map(l -> "" + l).collect(Collectors.joining(",")));
    }

    public CensusRequestBuilder object(String name, Number value) {
        return object_(name, value);
    }

    public CensusRequestBuilder object(String name, String value) {
        return object_(name, value);
    }

    private CensusRequestBuilder object_(String name, Object value) {
        return add(name + "=" + value);
    }

    public CensusRequestBuilder treeList(String on, String field) {
        tree(on, field);
        request += "^list:1";
        return this;
    }

    public CensusRequestBuilder show(String... show) {
        return add("c:show=" + String.join(",", show));
    }

    public CensusRequestBuilder tree(String on, String field) {
        return add("c:tree=start:" + on + "^field:" + field);
    }

    public CensusRequestBuilder join(String collection, Consumer<JoinBuilder> joinBuilder) {
        JoinBuilder jb = new JoinBuilder();
        joinBuilder.accept(jb);
        return add("c:join=" + collection + jb.request);
    }

    private CensusRequestBuilder add(String add) {
        if (!request.isEmpty()) {
            request += "&";
        }
        request += add;
        return this;
    }

    public String build() {
        return request;
    }

    public static class JoinBuilder {

        private JoinBuilder() {
        }

        private String request = "";

        public JoinBuilder injectAt(String fieldName) {
            return add("inject_at", fieldName);
        }

        public JoinBuilder on(String fieldName) {
            return add("on", fieldName);
        }

        public JoinBuilder to(String fieldName) {
            return add("to", fieldName);
        }

        public JoinBuilder show(String... show) {
            return add("show", show);
        }

        public JoinBuilder hide(String... hide) {
            return add("hide", hide);
        }

        public JoinBuilder terms(Consumer<BiConsumer<String, Object>> termsBuilder) {
            List<String> terms = new ArrayList<>();
            termsBuilder.accept((s1, s2) -> terms.add(s1 + "=" + s2));
            return add("terms", String.join("'", terms));
        }

        public JoinBuilder terms(String... fullTerms) {
            return add("terms", fullTerms);
        }

        public JoinBuilder asList() {
            return add("list", "1");
        }

        public JoinBuilder and(String collection, Consumer<JoinBuilder> joinBuilder) {
            JoinBuilder jb = new JoinBuilder();
            joinBuilder.accept(jb);
            request += "," + collection + jb.request;
            return this;
        }

        public JoinBuilder nested(String collection, Consumer<JoinBuilder> joinBuilder) {
            JoinBuilder jb = new JoinBuilder();
            joinBuilder.accept(jb);
            request += "(" + collection + jb.request + ")";
            return this;
        }

        private JoinBuilder add(String type, String... values) {
            request += "^" + type + ":" + String.join("'", values);
            return this;
        }

    }

}
