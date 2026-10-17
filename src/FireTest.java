import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.CollectionUtils;

import java.util.List;
import java.util.ArrayList;
public class FireTest {
    @Test
    public void testTimeToBurnExample() {
        char[][] forest = {
            {'t','.','.','t','t','t','t','.','t'},
            {'.','.','t','t','.','.','.','.','t'},
            {'.','.','t','t','t','t','t','t','t'},
            {'t','t','t','t','.','.','.','.','.'}
        };

        int matchR = 2;
        int matchC = 6;

        int expected = 8;
        int actual = Fire.timeToBurn(forest, matchR, matchC);

        assertEquals(expected, actual);
    }

    @Test
    public void burnableTestAllBurnable() {
        char[][] forest = {
            {'t','t','t'},
            {'t','t','t'},
            {'t','t','t'}
        };

        int row = 1;
        int col = 1;
        boolean[][] burned = new boolean[forest.length][forest[0].length];

        List<int[]> expected = new ArrayList<>();
        expected.add(new int[]{2,1});
        expected.add(new int[]{0,1});
        expected.add(new int[]{1,2});
        expected.add(new int[]{1,0});

        List<int[]> actual = Fire.burnable(forest, row, col, burned);

        assertTrue(compareListIntArr(expected, actual));
    }

    private static boolean compareListIntArr(List<int[]> list1, List<int[]> list2) {
        String output1 = "";
        String output2 = "";
        for (int[] pair : list1) {
            output1 += "{" + pair[0] + "," + pair[1] + "}";
        }
        for (int[] pair : list2) {
            output2 += "{" + pair[0] + "," + pair[1] + "}";
        }
        return output1.equals(output2);
    }
}
