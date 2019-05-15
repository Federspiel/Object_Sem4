package se.kth.pointofsale.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.kth.pointofsale.controller.Controller;
import se.kth.pointofsale.integration.InventoryHandler;
import se.kth.pointofsale.integration.ItemDTO;

public class ControllerTest {
	private InventoryHandler inventory;
    private Controller controller;

    @Before
    public void setUp() throws Exception {
        inventory = new InventoryHandler();
        controller = new Controller();
        controller.startSale();
    }

    @After
    public void tearDown() throws Exception {
        inventory = null;
        
        controller = null;
    }

    @Test
    public void testEnterItem() throws Exception {
        ItemDTO expectedItem = inventory.getItemInfo(1);

        ItemDTO item = controller.enterItem(1, 2);
        assertEquals("Doesn't return the correct item", expectedItem, item);
    }
    
}
