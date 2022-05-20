package net.kaupenjoe.mccourse;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.kaupenjoe.mccourse.item.ModItems;
import net.kaupenjoe.mccourse.util.ModModelPredicateProvider;
import net.kaupenjoe.mccourse.util.ModRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MCCourseMod implements ModInitializer {

	public static final String MOD_ID = "mccourse";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlock();

		ModRegistries.registerModStuffs();
		ModModelPredicateProvider.registerModModels();

		PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {

			if(!world.isClient() &&
					!player.isCreative() &&
					state.getBlock().getClass().getName().contains("ClickNumberBlock")){
				return false;
			}
			return true;
		});
	}


}
