import org.example.BST_Vus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BSTTest {

    private BST_Vus<Integer, String> bst;

    @BeforeEach
    void setUp() {
        bst = new BST_Vus<>();
        bst.put(5, "П'ять");
        bst.put(3, "Три");
        bst.put(7, "Сім");
        bst.put(2, "Два");
        bst.put(4, "Чотири");
        bst.put(6, "Шість");
        bst.put(8, "Вісім");
    }

    @Test
    void testGet() {
        assertEquals("П'ять", bst.get(5));
        assertEquals("Два", bst.get(2));
        assertEquals("Вісім", bst.get(8));
        assertNull(bst.get(10));
    }

    @Test
    void testPutOverridesValue() {
        bst.put(3, "Три");
        assertEquals("Три", bst.get(3));
        assertEquals(7, bst.size());
    }

    @Test
    void testSize() {
        assertEquals(7, bst.size());
        bst.put(10, "Десять");
        assertEquals(8, bst.size());
        bst.delete(10);
        assertEquals(7, bst.size());
    }

    @Test
    void testMinAndMax() {
        assertEquals(2, bst.min());
        assertEquals(8, bst.max());
    }

    @Test
    void testFloor() {
        assertEquals(5, bst.floor(5));
        assertEquals(3, bst.floor(3));
        assertNull(bst.floor(1));
    }

    @Test
    void testCeiling() {
        assertEquals(5, bst.ceiling(5));
        assertEquals(2, bst.ceiling(1));
        assertNull(bst.ceiling(9));
    }

    @Test
    void testRank() {
        assertEquals(0, bst.rank(2));
        assertEquals(3, bst.rank(5));
        assertEquals(7, bst.rank(9));
    }

    @Test
    void testDelete() {
        bst.delete(2);
        assertNull(bst.get(2));
        assertEquals(6, bst.size());
    }

    @Test
    void testDeleteMin() {
        bst.deleteMin();
        assertNull(bst.get(2));
        assertEquals(6, bst.size());
        assertEquals(3, bst.min());
    }

    @Test
    void testDeleteMax() {
        bst.deleteMax();
        assertNull(bst.get(8));
        assertEquals(6, bst.size());
        assertEquals(7, bst.max());
    }
}