package snownee.jade.addon.core;

import com.google.common.base.Strings;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.Identifiers;
import snownee.jade.api.TooltipPosition;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.impl.WailaClientRegistration;
import snownee.jade.util.ModIdentification;

public enum ModNameProvider implements IBlockComponentProvider, IEntityComponentProvider {

	INSTANCE;

	@Override
	public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
		String modName = null;
		if (accessor.isFakeBlock()) {
			ItemStack fakeBlock = accessor.getFakeBlock();
			if (fakeBlock.hasTag() && fakeBlock.getTag().contains("id")) {
				ResourceLocation id = ResourceLocation.tryParse(fakeBlock.getTag().getString("id"));
				if (id != null) {
					modName = ModIdentification.getModName(id);
				}
			}
		}
		if (modName == null && WailaClientRegistration.INSTANCE.shouldPick(accessor.getBlockState())) {
			ItemStack pick = accessor.getPickedResult();
			if (!pick.isEmpty())
				modName = ModIdentification.getModName(pick);
		}
		if (modName == null)
			modName = ModIdentification.getModName(accessor.getBlock());

		if (!Strings.isNullOrEmpty(modName)) {
			modName = String.format(config.getWailaConfig().getFormatting().getModName(), modName);
			tooltip.add(new TextComponent(modName));
		}
	}

	@Override
	public void appendTooltip(ITooltip tooltip, EntityAccessor accessor, IPluginConfig config) {
		tooltip.add(new TextComponent(String.format(config.getWailaConfig().getFormatting().getModName(), ModIdentification.getModName(accessor.getEntity()))));
	}

	@Override
	public ResourceLocation getUid() {
		return Identifiers.CORE_MOD_NAME;
	}

	@Override
	public int getDefaultPriority() {
		return TooltipPosition.TAIL;
	}

}