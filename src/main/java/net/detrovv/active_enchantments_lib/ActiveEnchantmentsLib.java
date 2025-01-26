package net.detrovv.active_enchantments_lib;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActiveEnchantmentsLib implements ModInitializer
{
	public static final String MOD_ID = "active_enchantments_lib";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize()
	{
		LOGGER.info("Hello Fabric world!");
	}
}