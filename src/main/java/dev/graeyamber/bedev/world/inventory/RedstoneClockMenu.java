package dev.graeyamber.bedev.world.inventory;

import dev.graeyamber.bedev.BEDev;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class RedstoneClockMenu extends AbstractContainerMenu {

    private final Container redstoneClock = new SimpleContainer(0);

    public RedstoneClockMenu(int containerId) {
        super(null, containerId);
        /// WIP: not working at all
        BEDev.LOGGER.info("hello from menu class");
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.redstoneClock.stillValid(player);
        // probably "return true;" works too
    }
}
