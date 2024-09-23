package dev.graeyamber.bedev.block.entity;

import dev.graeyamber.bedev.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

import javax.annotation.Nullable;

/*
* tier 3
* screen + ticking, lets you choose a redstone level to emit, opens a GUI
* */

public class RedstoneClockBlockEntity extends BlockEntity implements MenuProvider {

    private final int INV = 1, SLOT_INPUT = 0;
    private final String NBT_ITEMS = "Inventory";
    private final ItemStackHandler items = createItemHandler();
    private final Lazy<IItemHandler> itemHandler = Lazy.of(() -> items);

    public RedstoneClockBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistry.REDSTONE_CLOCK_BLOCK_ENTITY.get(), pos, blockState);
    }


    /// creating the inventory

    private ItemStackHandler createItemHandler() {
        return new ItemStackHandler(INV) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged(); /// this has to be called on every inventory change
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }
        };
    }

    public IItemHandler getItemHandler() { return itemHandler.get(); }


    /// saving data, in this case Items

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        saveClientData(tag, provider);
    }

    private void saveClientData(CompoundTag tag, HolderLookup.Provider provider) {
        tag.put(NBT_ITEMS, items.serializeNBT(provider));
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        loadClientData(tag, provider);
    }

    private void loadClientData(CompoundTag tag, HolderLookup.Provider provider) {
        if (tag.contains(NBT_ITEMS)) {
            items.deserializeNBT(provider, tag.getCompound(NBT_ITEMS));
        }
    }


    // The getUpdateTag()/handleUpdateTag() pair is called whenever the client receives a new chunk
    // it hasn't seen before. i.e. the chunk is loaded
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        CompoundTag tag = super.getUpdateTag(provider);
        saveClientData(tag, provider);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider provider) {
        if (tag != null) {
            loadClientData(tag, provider);
        }
    }

    // The getUpdatePacket()/onDataPacket() pair is used when a block update happens on the client
    // (a blockstate change or an explicit notification of a block update from the server). It's
    // easiest to implement them based on getUpdateTag()/handleUpdateTag()
    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }


    // BE behavior logic

    public void tickServer() {
        if (level.getGameTime() % 10 == 0) {
            ItemStack stack = items.getStackInSlot(SLOT_INPUT);
            BlockPos pos = worldPosition.offset(0, 5, 0);
            if (!stack.isEmpty()) {
                // Drop on item on that position
                ejectItem(pos);
            }
            else {
                ((ServerLevel)level).sendParticles(ParticleTypes.COMPOSTER, pos.getX() + .5, pos.getY() + 1.5, pos.getZ() + .5, 10, 0, 0, 0, 0.15);
            }
        }
    }

    private void ejectItem(BlockPos pos) {
        Block.popResource(level, pos, items.extractItem(SLOT_INPUT, 1, false));
    }

    //: adds items to inventory only if there's enough space
    public boolean placeItem(@Nullable Entity entity, ItemStack entityStack) {
        ItemStack invStack = this.items.getStackInSlot(SLOT_INPUT);
        if (invStack.isEmpty() ||
            invStack.getItem().equals(entityStack.getItem()) && invStack.getCount() + entityStack.getCount() <= entityStack.getMaxStackSize()) {
            var newStack = entityStack.consumeAndReturn(entityStack.getCount(), (LivingEntity) entity);
            newStack.setCount(newStack.getCount() + invStack.getCount());
            this.items.setStackInSlot(SLOT_INPUT, newStack);

            this.level.gameEvent(GameEvent.BLOCK_CHANGE, this.getBlockPos(), GameEvent.Context.of(entity, this.getBlockState()));
            this.markUpdated();
            return true;
        }

        return false;
    }

    private void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }


    // BE Menu Logic

    @Override
    public Component getDisplayName() {
        return null;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return null; // new RedstoneClockMenu(null, containerId);
    }
}
