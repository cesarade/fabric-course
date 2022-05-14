package net.kaupenjoe.mccourse.item;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {

    public static FoodComponent ENCEBOLLADO = new FoodComponent.Builder()
            .hunger(2)
            .saturationModifier(0.3f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600),1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 400),1f)
            .snack()
            .build();


}
