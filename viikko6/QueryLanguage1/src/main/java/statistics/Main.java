package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));
          
        Matcher m = new And( new HasFewerThan(10, "goals"),
                             new HasFewerThan(25, "assists")
        );
        
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }

        Rakentaja rakentaja = new Rakentaja();

        Matcher m2 = rakentaja.playsIn("NYR")
                .hasAtLeast(10, "goals")
                .hasFewerThan(25, "assists").build();

        System.out.println("\nRakentajan testi:");
        for (Player player : stats.matches(m2)) {
            System.out.println(player);
        }
    }
}
