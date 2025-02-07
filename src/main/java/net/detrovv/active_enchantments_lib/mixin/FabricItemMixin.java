package net.detrovv.active_enchantments_lib.mixin;

import net.detrovv.active_enchantments_lib.util.ModTags;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;

@Mixin(FabricItem.class)
public interface FabricItemMixin
{
    @Inject(at = @At("HEAD"), method = "allowComponentsUpdateAnimation", cancellable = true)
    private void allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack,
                                                ItemStack newStack, CallbackInfoReturnable<Boolean> info)
    {
        if (changedOnlyTaggedDataComponents(oldStack, newStack,
                ModTags.Components.DATA_COMPONENT_UPDATE_ANIMATION_SKIP))
        {
            info.setReturnValue(false);
        }
    }

    @Inject(at = @At("HEAD"), method = "allowContinuingBlockBreaking", cancellable = true)
    private void allowContinuingBlockBreaking(PlayerEntity player, ItemStack oldStack, ItemStack newStack,
                                              CallbackInfoReturnable<Boolean> info)
    {
        if (changedOnlyTaggedDataComponents(oldStack, newStack,
                ModTags.Components.DATA_COMPONENT_UPDATE_BLOCK_BREAKING_PROGRESS_RESET_SKIP))
        {
            info.setReturnValue(true);
        }
    }

    private static boolean changedOnlyTaggedDataComponents(ItemStack oldStack, ItemStack newStack,
                                                           TagKey<ComponentType<?>> tag)
    {
        var oldComponents = oldStack.getComponents();
        var newComponents = newStack.getComponents();

        HashSet<ComponentType<?>> allComponentTypes = new HashSet<>();

        allComponentTypes.addAll(oldComponents.getTypes());
        allComponentTypes.addAll(newComponents.getTypes());

        for (var componentType : allComponentTypes)
        {
            if (oldStack.get(componentType) != newStack.get(componentType) &&
                    componentType != DataComponentTypes.ENCHANTMENTS &&
                    !Registries.DATA_COMPONENT_TYPE.getEntry(componentType).isIn(tag))
            {
                return false;
            }
        }
        return true;
    }
}