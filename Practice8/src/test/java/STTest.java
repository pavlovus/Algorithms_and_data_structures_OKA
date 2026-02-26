import org.example.ST_Vus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

public class STTest {

    private ST_Vus<Integer, String> st;

    @BeforeEach
    void setUp() {
        st = new ST_Vus<>();
        st.put(5, "П'ять");
        st.put(3, "Три");
        st.put(7, "Сім");
        st.put(2, "Два");
        st.put(4, "Чотири");
        st.put(6, "Шість");
        st.put(8, "Вісім");
    }

    @Test
    void testPutAndGet() {
        assertEquals("П'ять", st.get(5));
        assertEquals("Три", st.get(3));
        assertEquals("Вісім", st.get(8));
        assertNull(st.get(10));
    }

    @Test
    void testPutOverridesValue() {
        st.put(3, "ТРИ");
        assertEquals("ТРИ", st.get(3));
        assertEquals(7, st.size());
    }

    @Test
    void testDelete() {
        st.delete(3);
        assertNull(st.get(3));
        assertEquals(6, st.size());
    }

    @Test
    void testMinAndMax() {
        assertEquals(2, st.min());
        assertEquals(8, st.max());
    }

    @Test
    void testDeleteMinAndMax() {
        st.deleteMin();
        assertEquals(3, st.min());
        st.deleteMax();
        assertEquals(7, st.max());
        assertEquals(5, st.size());
    }

    @Test
    void testFloor() {
        assertEquals(5, st.floor(5));
        assertEquals(8, st.floor(9));
        assertNull(st.floor(1));
    }

    @Test
    void testCeiling() {
        assertEquals(5, st.ceiling(5));
        assertEquals(2, st.ceiling(1));
        assertNull(st.ceiling(9));
    }

    @Test
    void testSizeRange() {
        assertEquals(3, st.size(3, 5));
        assertEquals(1, st.size(2, 2));
        assertEquals(0, st.size(10, 20));
    }

    @Test
    void testKeysIteration() {
        Iterator<Integer> it = st.keys().iterator();
        int[] expected = {2, 3, 4, 5, 6, 7, 8};
        int i = 0;
        while (it.hasNext()) {
            assertEquals(expected[i++], it.next());
        }
        assertEquals(expected.length, i);
    }

    @Test
    void testKeysRangeIteration() {
        Iterator<Integer> it = st.keys(3, 6);
        int[] expected = {3, 4, 5, 6};
        int i = 0;
        while (it.hasNext()) {
            assertEquals(expected[i++], it.next());
        }
        assertEquals(expected.length, i);
    }

    @Test
    void testContainsAndIsEmpty() {
        assertTrue(st.contains(5));
        assertFalse(st.contains(10));
        assertFalse(st.isEmpty());
        st = new ST_Vus<>();
        assertTrue(st.isEmpty());
    }

    @Test
    void testSelect() {
        assertEquals(2, st.select(0));
        assertEquals(5, st.select(3));
        assertNull(st.select(-1));
        assertNull(st.select(100));
    }

    @Test
    void testResizeAndPutMany() {
        for (int i = 9; i <= 30; i++) {
            st.put(i, "num" + i);
        }
        assertEquals(29, st.size());
        assertEquals("num24", st.get(24));
    }
}