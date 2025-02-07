package net.detrovv.active_enchantments_lib.util;

import net.detrovv.active_enchantments_lib.ActiveEnchantmentsLib;
import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags
{
    public static class Enchantments
    {
        public static final TagKey<Enchantment> INVENTORY_TICKING_ENCHANTMENTS =
                createTag("inventory_ticking_enchantments");

        private static TagKey<Enchantment> createTag(String name)
        {
            return TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(ActiveEnchantmentsLib.MOD_ID, name));
        }
    }

    public static class Components
    {
        public static final TagKey<ComponentType<?>> DATA_COMPONENT_UPDATE_ANIMATION_SKIP =
                createTag("data_component_update_animation_skip");

        public static final TagKey<ComponentType<?>> DATA_COMPONENT_UPDATE_BLOCK_BREAKING_PROGRESS_RESET_SKIP =
                createTag("data_component_update_block_breaking_progress_reset_skip");

        private static TagKey<ComponentType<?>> createTag(String name)
        {
            return TagKey.of(RegistryKeys.DATA_COMPONENT_TYPE, Identifier.of(ActiveEnchantmentsLib.MOD_ID, name));
        }
    }
}
