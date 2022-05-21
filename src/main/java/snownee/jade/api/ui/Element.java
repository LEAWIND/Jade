package snownee.jade.api.ui;

import org.jetbrains.annotations.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;

/**
 * A general implementation of {@link IElement}
 *
 * @author Snownee
 */
public abstract class Element implements IElement {

	protected Align align = Align.LEFT;
	protected Vec2 translation = Vec2.ZERO;
	protected ResourceLocation tag;
	protected Vec2 size;
	private static final Component DEFAULT_MESSAGE = TextComponent.EMPTY.copy();
	protected Component message = DEFAULT_MESSAGE;

	@Override
	public IElement size(Vec2 size) {
		this.size = size;
		return this;
	}

	@Override
	public Vec2 getCachedSize() {
		if (size == null)
			size = getSize();
		return size;
	}

	@Override
	public IElement align(Align align) {
		this.align = align;
		return this;
	}

	@Override
	public Align getAlignment() {
		return align;
	}

	@Override
	public IElement translate(Vec2 translation) {
		this.translation = translation;
		return this;
	}

	@Override
	public Vec2 getTranslation() {
		return translation;
	}

	@Override
	public IElement tag(ResourceLocation tag) {
		this.tag = tag;
		return this;
	}

	@Override
	public ResourceLocation getTag() {
		return tag;
	}

	@Override
	public @Nullable Component getCachedMessage() {
		if (message == DEFAULT_MESSAGE) {
			message = getMessage();
		}
		return message;
	}

	@Override
	public IElement message(@Nullable Component message) {
		this.message = message;
		return this;
	}

}