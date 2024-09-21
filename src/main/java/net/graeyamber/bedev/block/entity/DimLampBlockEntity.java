package net.graeyamber.bedev.block.entity;

import net.graeyamber.bedev.BEDev;
import net.graeyamber.bedev.block.DimLampBlock;
import net.graeyamber.bedev.block.entity.util.ITickableBlockEntity;
import net.graeyamber.bedev.registry.BlockEntityRegistry;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SmokeParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

/*
* tier 1
* this blockentity allows for ticking
* in this case it will power on/off every 4 seconds
* notice the use of ITickableBlockEntity
* it has no persistent stored data
* */

public class DimLampBlockEntity extends BlockEntity implements ITickableBlockEntity  {

    // this does not store data on world exit
    // but is unique for every placed BE
    // useful for calculations or temporarily storing data
    private int ticks;
    private Boolean lit;

    public DimLampBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistry.DIM_LAMP_BLOCK_ENTITY.get(), pos, blockState);
        ticks = 0;
        lit = false;
    }

    // I wanted to use the blockstate but I need to inspect that further
    // check level.sendblockupdated
    @Override
    public void tick() {
        if (this.level == null || this.level.isClientSide())
            return;

        if(this.ticks++ % 80 == 0) {
            if(lit) {
                BEDev.LOGGER.info("UNLIT");
            }
            else {
                BEDev.LOGGER.info("LIT!!");
            }
            lit = !lit;

            //. setChanged(this.level, this.worldPosition, this.getBlockState());
            //. level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState().setValue(DimLampBlock.LIT, lit), DimLampBlock.UPDATE_ALL);
        }

        if(lit) {
            var pos = this.getBlockPos();
            ((ServerLevel)level).sendParticles(ParticleTypes.SMOKE, pos.getX() + .5, pos.getY() + 1.5, pos.getZ() + .5, 10, 0, 0, 0, 0.15);
        }
    }
}
