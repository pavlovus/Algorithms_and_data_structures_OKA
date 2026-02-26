import org.example.Percolation_Vus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PercolationTest {

    @Test
    void initialTest(){
        Percolation_Vus p = new Percolation_Vus(3);
        assertNotNull(p);
        assertFalse(p.percolates());
        assertFalse(p.isOpened(0,0));
    }

    @Test
    void openTest(){
        Percolation_Vus p = new Percolation_Vus(3);
        p.open(1, 1);
        assertTrue(p.isOpened(1,1));
        assertEquals(1, p.getOpenedCount());
    }

    @Test
    void percolationTest(){
        Percolation_Vus p1 = new Percolation_Vus(3);
        p1.open(0, 0);
        p1.open(1, 0);
        p1.open(2, 0);
        assertTrue(p1.percolates());
        Percolation_Vus p2 = new Percolation_Vus(3);
        p2.open(0, 0);
        p2.open(1, 1);
        p2.open(2, 2);
        assertFalse(p2.percolates());
        Percolation_Vus p3 = new Percolation_Vus(3);
        p3.open(0, 0);
        p3.open(0, 1);
        p3.open(1, 1);
        p3.open(1, 2);
        p3.open(2, 2);
        assertTrue(p3.percolates());
    }

}
