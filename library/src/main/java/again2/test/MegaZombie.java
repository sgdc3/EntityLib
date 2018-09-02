package again2.test;

import again2.api.AbstractCustomMonster;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

public class MegaZombie extends AbstractCustomMonster {

    public MegaZombie(final Plugin owner) {
        super(new NamespacedKey(owner, ""));
        setSize(0.5F, 0.1F);
    }

    @Override
    public void tick() {

    }
}
