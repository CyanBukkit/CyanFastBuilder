package cubing.bukkit;

import org.bukkit.inventory.*;

public class InventoryUtils
{
    public InventoryUtils() {
        super();
    }
    
    public static void fillItem(final Inventory inventory, final int start, final int end, final ItemStack stack) {
        for (int i = start; i < end; ++i) {
            inventory.setItem(i, stack);
        }
    }
}
