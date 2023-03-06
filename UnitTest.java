import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class UnitTest {
    @Test
    public void testRegenerateBoard() {
        Panel panel = new Panel();
        panel.regenerateBoard();

        // Check if starting node is reset
        assertFalse(panel.startingNode.isWall());
        assertFalse(panel.startingNode.isChecked());
        assertNull(panel.startingNode.parentNode);

        // Check if goal node is reset
        assertFalse(panel.goalNode.isWall());
        assertFalse(panel.goalNode.isChecked());
        assertNull(panel.goalNode.parentNode);

        // Check if all nodes are reset
        for (int column = 0; column < Panel.maxColumn; column++) {
            for (int row = 0; row < Panel.maxRow; row++) {
                Node node = panel.position[column][row];
                assertFalse(node.isChecked());
                assertNull(node.parentNode);
            }
        }
    }

    @Test
    public void testGetRandomCoordinate() {
        // Test with maximum and minimum values
        assertEquals(1, Panel.getRandomCoordinate(2, 1));
        assertEquals(2, Panel.getRandomCoordinate(2, 2));

        // Test with larger range
        for (int i = 0; i < 100; i++) {
            int random = Panel.getRandomCoordinate(10, 1);
            assertTrue(random >= 1 && random <= 10);
        }
    }
}
