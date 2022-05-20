package net.kaupenjoe.mccourse.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeverBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ModLeverBlock extends LeverBlock {

    public static final BooleanProperty POWERED = BooleanProperty.of("powered");

    public ModLeverBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        boolean powered = state.get(POWERED);

        if(!world.isClient){
            if(!player.isCreative() && powered) {
                player.sendMessage(new TranslatableText("message.operation.number"), false);
                return ActionResult.PASS;
            }
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

}
