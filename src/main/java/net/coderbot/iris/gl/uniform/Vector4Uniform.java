package net.coderbot.iris.gl.uniform;

import java.util.function.Supplier;
import net.minecraft.util.math.Vector4f;
import org.lwjgl.opengl.GL21;

public class Vector4Uniform extends Uniform {
	private final Vector4f cachedValue;
	private final Supplier<Vector4f> value;

	Vector4Uniform(int location, Supplier<Vector4f> value) {
		this(location, value, null);
	}

	Vector4Uniform(int location, Supplier<Vector4f> value, ValueUpdateNotifier notifier) {
		super(location, notifier);

		this.cachedValue = new Vector4f();
		this.value = value;
	}

	@Override
	public void update() {
		updateValue();

		if (notifier != null) {
			notifier.setListener(this::updateValue);
		}
	}

	private void updateValue() {
		Vector4f newValue = value.get();

		if (!newValue.equals(cachedValue)) {
			cachedValue.set(newValue.getX(), newValue.getY(), newValue.getZ(), newValue.getW());
			GL21.glUniform4f(location, cachedValue.getX(), cachedValue.getY(), cachedValue.getZ(), cachedValue.getW());
		}
	}
}
