package net.kaupenjoe.mccourse.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DowsingRodItem extends Item {

    public DowsingRodItem(Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().isClient){
            PlayerEntity player = context.getPlayer();
            BlockPos positionClicked = context.getBlockPos();
            boolean foundBlock = false;

            for(int i = 0; i<positionClicked.getY();i++){
                Block blockBelow = context.getWorld().getBlockState(positionClicked.down(i)).getBlock();
                if(isValuableBlock(blockBelow)){
                    outputValuableCoordinate(player, positionClicked, blockBelow);
                    foundBlock = true;
                    break;
                }
            }

            if(!foundBlock) {
                player.sendMessage(new TranslatableText("item.mccourse.dowsing_rod.no_valuable"), false);
            }

            player.sendMessage(new LiteralText(this.getMaxDamage() +""), false);
        }

        context.getStack().damage(1, context.getPlayer(),
                (player) -> player.sendToolBreakStatus(player.getActiveHand()));


        return super.useOnBlock(context);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(Screen.hasShiftDown()){
            tooltip.add(new TranslatableText("item.mccourse.dowsing.tooltip.shift"));
        } else{
            tooltip.add(new TranslatableText("item.mccourse.dowsing.tooltip"));
        }
    }

    private void outputValuableCoordinate(PlayerEntity player, BlockPos positionClicked, Block blockBelow) {
        player.sendMessage(
                new LiteralText("Found " + blockBelow.asItem().getName().getString() + " at (" + + positionClicked.getY() + ", "+ positionClicked.getY() + ", " + positionClicked.getZ() +")") ,
                false);
    }

    private boolean isValuableBlock(Block block){
        return block == Blocks.COAL_ORE ||
                block == Blocks.DIAMOND_ORE ||
                block == Blocks.IRON_ORE;
    }

}
