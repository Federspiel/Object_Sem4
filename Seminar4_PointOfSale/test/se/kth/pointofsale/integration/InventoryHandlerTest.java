package se.kth.pointofsale.integration;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.pointofsale.exceptions.DatabaseFailureException;
import se.kth.pointofsale.exceptions.ItemNotFoundException;

public class InventoryHandlerTest {
    private InventoryHandler inventoryHandler;

    @Before
    public void setUp() throws Exception {
        inventoryHandler = new InventoryHandler();

    }

    @After
    public void tearDown() throws Exception {
        inventoryHandler = null;

    }

    @Test
    public void testGetItemInfo() throws Exception {
        ItemDTO testItem1 = inventoryHandler.getItemInfo(1);
        ItemDTO testItem1Clone = inventoryHandler.getItemInfo(1);
        ItemDTO testItem2 = inventoryHandler.getItemInfo(2);

        boolean item1EqualWithClone = testItem1.equals(testItem1Clone);
        assertTrue("Item fetched with identical ids are not the same!", item1EqualWithClone);

        boolean item1EqualWithItem2 = testItem1.equals(testItem2);
        assertFalse("Item fetched with different ids are the same!", item1EqualWithItem2);

    }
    
    @Test(expected = DatabaseFailureException.class)
    public void testDatabaseFailureException() throws DatabaseFailureException, ItemNotFoundException{
    	inventoryHandler.getItemInfo(7);
    }
    
    @Test(expected = ItemNotFoundException.class)
    public void testItemNotFoundException() throws DatabaseFailureException, ItemNotFoundException{
    	inventoryHandler.getItemInfo(15);
    }

    @Test
    public void testRemoveFromStock() throws Exception {
        boolean remove5FromItem1 = inventoryHandler.removeFromStock(1, 5);
        assertTrue("Inventory does not start with the correct amount of stock!", remove5FromItem1);

        boolean remove10FromItem1 = inventoryHandler.removeFromStock(1, 10);
        assertFalse("Inventory can remove more stock than what exists!", remove10FromItem1);

        boolean remove5FromItem1AfterFailedRemove = inventoryHandler.removeFromStock(1, 5);
        assertTrue("Inventory does not remove stock correctly!", remove5FromItem1AfterFailedRemove);
    }
}