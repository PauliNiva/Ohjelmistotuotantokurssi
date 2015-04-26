package statistics.matcher;

import java.util.ArrayList;
import java.util.List;

public class Rakentaja {

    List<Matcher> ehdot = new ArrayList<Matcher>();

    public Rakentaja hasFewerThan(int value, String field) {
        ehdot.add(new HasFewerThan(value, field));
        return this;
    }

    public Rakentaja hasAtLeast(int value, String field) {
        ehdot.add(new HasAtLeast(value, field));
        return this;
    }

    public Rakentaja playsIn(String s) {
        ehdot.add(new PlaysIn(s));
        return this;
    }

    public Rakentaja oneOf(Matcher... matchers) {
        ehdot.add(new Or(matchers));
        return this;
    }

    public Matcher build() {
        Matcher[] matchers = {};
        matchers = ehdot.toArray(matchers);
        Matcher query = new And(matchers);
        this.ehdot = new ArrayList<Matcher>();
        return query;
    }
}
