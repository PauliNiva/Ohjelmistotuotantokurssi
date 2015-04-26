package statistics.matcher;

import statistics.Player;

public class Or implements Matcher {

    private Matcher[] matchers;

    public Or(Matcher... matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Player p) {
        int matcherCalc = 0;
        for (Matcher matcher : matchers) {
            if (matcher.matches(p)) {
                matcherCalc++;
            }
        }
        if (matcherCalc == 0) return false;
        return true;
    }
}
