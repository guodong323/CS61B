package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE

    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> lista = new AListNoResizing<>();
        BuggyAList<Integer> listb = new BuggyAList<>();

        lista.addLast(4);
        lista.addLast(5);
        lista.addLast(6);

        listb.addLast(4);
        listb.addLast(5);
        listb.addLast(6);

        assertEquals(lista.size(), listb.size());

        assertEquals(lista.removeLast(), listb.removeLast());
        assertEquals(lista.removeLast(), listb.removeLast());
        assertEquals(lista.removeLast(), listb.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {

                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                broken.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                assertEquals(correct.size(), broken.size());
            } else if (correct.size() == 0) {
                continue;
            } else if (operationNumber == 2) {
                assertEquals(correct.getLast(), broken.getLast());
            } else if (operationNumber == 2) {
                assertEquals(correct.removeLast(), broken.removeLast());
            }
        }
    }
}
