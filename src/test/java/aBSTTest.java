import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class aBSTTest {

    @Test
    void construct() {
        aBST aBST = new aBST(4);
        assertEquals(aBST.Tree.length, 15);
        aBST = new aBST(1);
        assertEquals(aBST.Tree.length, 1);
        aBST = new aBST(2);
        assertEquals(aBST.Tree.length, 3);
        aBST = new aBST(3);
        assertEquals(aBST.Tree.length, 7);
        for (int i = 0; i < aBST.Tree.length; i++) {
            assertNull(aBST.Tree[i]);
        }
    }

    @Test
    void findKeyIndex() {
        aBST aBST = new aBST(4);
        assertEquals(aBST.FindKeyIndex(50), 0);
        aBST.Tree[0] = 50;
        assertEquals(aBST.FindKeyIndex(25), -1);
        assertEquals(aBST.FindKeyIndex(75), -2);
        aBST.Tree[1] = 25;
        aBST.Tree[2] = 75;
        assertEquals(aBST.FindKeyIndex(37), -4);
        assertEquals(aBST.FindKeyIndex(62), -5);
        assertEquals(aBST.FindKeyIndex(84), -6);
        aBST.Tree[4] = 37;
        aBST.Tree[5] = 62;
        aBST.Tree[6] = 84;
        assertEquals(aBST.FindKeyIndex(31), -9);
        assertEquals(aBST.FindKeyIndex(43), -10);
        assertEquals(aBST.FindKeyIndex(55), -11);
        assertEquals(aBST.FindKeyIndex(92), -14);
        aBST.Tree[9] = 31;
        aBST.Tree[10] = 43;
        aBST.Tree[11] = 55;
        aBST.Tree[14] = 92;
        assertNull(aBST.FindKeyIndex(34));
        assertNull(aBST.FindKeyIndex(30));
        assertNull(aBST.FindKeyIndex(40));
        assertNull(aBST.FindKeyIndex(44));
        assertNull(aBST.FindKeyIndex(54));
        assertNull(aBST.FindKeyIndex(56));
        assertNull(aBST.FindKeyIndex(91));
        assertNull(aBST.FindKeyIndex(100));


        assertEquals(aBST.FindKeyIndex(50), 0);
        assertEquals(aBST.FindKeyIndex(25), 1);
        assertEquals(aBST.FindKeyIndex(75), 2);
        assertEquals(aBST.FindKeyIndex(37), 4);
        assertEquals(aBST.FindKeyIndex(62), 5);
        assertEquals(aBST.FindKeyIndex(84), 6);
        assertEquals(aBST.FindKeyIndex(31), 9);
        assertEquals(aBST.FindKeyIndex(43), 10);
        assertEquals(aBST.FindKeyIndex(55), 11);
        assertEquals(aBST.FindKeyIndex(92), 14);

    }

    @Test
    void addKey() {
        aBST aBST = new aBST(4);
        aBST.AddKey(50);
        assertEquals(aBST.FindKeyIndex(50), 0);
        assertEquals(aBST.AddKey(50), 0);

        aBST.AddKey(25);
        assertEquals(aBST.FindKeyIndex(25), 1);
        assertEquals(aBST.AddKey(25), 1);

        aBST.AddKey(75);
        assertEquals(aBST.FindKeyIndex(75), 2);
        assertEquals(aBST.AddKey(75), 2);

        aBST.AddKey(37);
        assertEquals(aBST.FindKeyIndex(37), 4);
        assertEquals(aBST.AddKey(37), 4);

        aBST.AddKey(62);
        assertEquals(aBST.FindKeyIndex(62), 5);
        assertEquals(aBST.AddKey(62), 5);

        aBST.AddKey(84);
        assertEquals(aBST.FindKeyIndex(84), 6);
        assertEquals(aBST.AddKey(84), 6);

        aBST.AddKey(31);
        assertEquals(aBST.FindKeyIndex(31), 9);
        assertEquals(aBST.AddKey(31), 9);

        aBST.AddKey(43);
        assertEquals(aBST.FindKeyIndex(43), 10);
        assertEquals(aBST.AddKey(43), 10);

        aBST.AddKey(55);
        assertEquals(aBST.FindKeyIndex(55), 11);
        assertEquals(aBST.AddKey(55), 11);

        aBST.AddKey(92);
        assertEquals(aBST.FindKeyIndex(92), 14);
        assertEquals(aBST.AddKey(92), 14);

        assertEquals(aBST.AddKey(34), -1);
        assertEquals(aBST.AddKey(30), -1);
        assertEquals(aBST.AddKey(30), -1);
        assertEquals(aBST.AddKey(40), -1);
        assertEquals(aBST.AddKey(44), -1);
        assertEquals(aBST.AddKey(54), -1);
        assertEquals(aBST.AddKey(56), -1);
        assertEquals(aBST.AddKey(91), -1);
        assertEquals(aBST.AddKey(100), -1);

    }
}