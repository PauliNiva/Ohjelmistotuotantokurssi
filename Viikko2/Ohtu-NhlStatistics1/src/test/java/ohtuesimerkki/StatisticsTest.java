package test.java.ohtuesimerkki;

import main.java.ohtuesimerkki.Player;
import main.java.ohtuesimerkki.Reader;
import main.java.ohtuesimerkki.ReaderStub;
import main.java.ohtuesimerkki.Statistics;

import java.util.List;

import static org.junit.Assert.*;

public class StatisticsTest {

    Statistics statistics;
    Reader readerStub = new ReaderStub();

    @org.junit.Before
    public void setUp() throws Exception {
        statistics = new Statistics(readerStub);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testSearch() throws Exception {
        assertEquals("Yzerman", statistics.search("Yzerman").getName());
        Player player = statistics.search("Howe");
        assertNull(player);
    }

    @org.junit.Test
    public void testTeam() throws Exception {
        assertEquals(3, statistics.team("EDM").size());
        assertEquals(0, statistics.team("HIFK").size());
    }

    @org.junit.Test
    public void testTopScorers() throws Exception {
        List<Player> topScorers = statistics.topScorers(2);
        assertEquals("Gretzky", topScorers.get(0).getName());
        assertEquals("Lemieux", topScorers.get(1).getName());
    }

    @org.junit.Test
    public void testTopScorersMaara() {
        List<Player> topScorers = statistics.topScorers(2);
        assertEquals(2, topScorers.size());
    }
}