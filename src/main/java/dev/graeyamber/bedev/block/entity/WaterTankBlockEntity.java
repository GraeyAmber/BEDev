package dev.graeyamber.bedev.block.entity;

import dev.graeyamber.bedev.BEDev;
import dev.graeyamber.bedev.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nullable;

/*
* Uses a bucket to get/take fluid inside, no rendering
* */

public class WaterTankBlockEntity extends BlockEntity {

    //: private final int INV = 1, SLOT_INPUT = 0;
    //: private final String NBT_ITEMS = "Inventory";
    //: private final ItemStackHandler items = createItemHandler();
    //: private final Lazy<IItemHandler> itemHandler = Lazy.of(() -> items);

    private final int TANK_SIZE = 4000;
    private final String NBT_TANK = "FluidTank";
    private final FluidTank fluidTank = createFluidTank();
    //createFluidTank();
    private final Lazy<FluidTank> fluidHandler = Lazy.of(() -> this.fluidTank);

    public WaterTankBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistry.WATER_TANK_BLOCK_ENTITY.get(), pos, blockState);
    }


    /// creating the inventory

    //: private ItemStackHandler createItemHandler() {
    //:     return new ItemStackHandler(INV) {
    //:         @Override
    //:         protected void onContentsChanged(int slot) {
    //:             super.onContentsChanged(slot);
    //:             setChanged(); /// this has to be called on every inventory change
    //:             level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
    //:         }
    //:     };
    //: }

    //: public IItemHandler getItemHandler() { return itemHandler.get(); }


    /// creating the fluid tank

    private FluidTank createFluidTank() {
        return new FluidTank(TANK_SIZE) {
            @Override
            protected void onContentsChanged() {
                super.onContentsChanged();
                setChanged();
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }
        };
    }

    public FluidTank getTank() { return fluidTank; }


    /// saving data, in this case Items

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        saveClientData(tag, provider);
    }

    private void saveClientData(CompoundTag tag, HolderLookup.Provider provider) {
        //: tag.put(NBT_ITEMS, items.serializeNBT(provider));
        tag.put(NBT_TANK, fluidTank.writeToNBT(provider, new CompoundTag()));
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        loadClientData(tag, provider);
    }

    private void loadClientData(CompoundTag tag, HolderLookup.Provider provider) {
        //: if (tag.contains(NBT_ITEMS)) {
        //:     items.deserializeNBT(provider, tag.getCompound(NBT_ITEMS));
        //: }
        if (tag.contains(NBT_TANK)) {
            fluidTank.readFromNBT(provider, tag.getCompound(NBT_TANK));
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


    /// BE behavior logic

    public void tickServer() {
        if(this.fluidTank.getFluidAmount() >= this.fluidTank.getCapacity()) {
            return;
        }

        // Lazy<IFluidHandlerItem> fluidItem = stack


    }

    //: adds items to inventory only if there's enough space
    //: public boolean placeItem(@Nullable Entity entity, ItemStack entityStack) {
    //:     ItemStack invStack = this.items.getStackInSlot(SLOT_INPUT);
    //:     if (invStack.isEmpty() ||
    //:         invStack.getItem().equals(entityStack.getItem()) && invStack.getCount() + entityStack.getCount() <= entityStack.getMaxStackSize()) {
    //:         var newStack = entityStack.consumeAndReturn(entityStack.getCount(), (LivingEntity) entity);
    //:         newStack.setCount(newStack.getCount() + invStack.getCount());
    //:         this.items.setStackInSlot(SLOT_INPUT, newStack);
//:
    //:         this.level.gameEvent(GameEvent.BLOCK_CHANGE, this.getBlockPos(), GameEvent.Context.of(entity, this.getBlockState()));
    //:         this.markUpdated();
    //:         return true;
    //:     }
//:
    //:     return false;
    //: }

    public boolean fillTank(@Nullable Entity entity, FluidStack fluidStack) {
        FluidStack innerTank = this.fluidTank.getFluid();
        BEDev.LOGGER.info("filling from: " + innerTank.getAmount());
        if(innerTank.getAmount() + fluidStack.getAmount() <= TANK_SIZE) {
            var newFluid = fluidStack.copyAndClear();
            newFluid.setAmount(newFluid.getAmount() + innerTank.getAmount());
            this.fluidTank.setFluid(newFluid);

            this.level.gameEvent(GameEvent.BLOCK_CHANGE, this.getBlockPos(), GameEvent.Context.of(entity, this.getBlockState()));
            this.markUpdated();

            BEDev.LOGGER.info("new amount: " + fluidTank.getFluidAmount());
            return true;
        }
        return false;
    }

    public boolean emptyTank() {
        BEDev.LOGGER.info("emptying");
        this.fluidTank.getFluid().copyAndClear();
        return true;
    }


    private void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }


}
