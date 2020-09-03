import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MainClassTest extends MainClass {
    @Test
    public void testGetLocalNumber() {
        int expected = 14;
        //int actual = getLocalNumber();
        assertTrue("Number " + getLocalNumber() + "!=14", getLocalNumber() == expected);
    }

    @Test
    public void testGetClassNumber() {
        int expected = 45;
        //int actual = getClassNumber();
        assertTrue("ClassNumber " + getClassNumber() + "!>45", expected < getClassNumber());
    }

    @Test
    public void testGetClassString() {
        assertTrue("ClassString doesn't contain Hello or hello",
                this.getClassString().contains("Hello") || this.getClassString().contains("hello"));
    }
}
