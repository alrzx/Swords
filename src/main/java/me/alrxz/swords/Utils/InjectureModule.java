package me.alrxz.swords.Utils;

import com.google.inject.AbstractModule;
import me.alrxz.swords.Swords;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class InjectureModule extends AbstractModule {

    private final Swords plugin;
    private final NamespacedKey key;

    public InjectureModule(final @NotNull Swords plugin, final @NotNull NamespacedKey key) {
        this.plugin = plugin;
        this.key = key;
    }

    @Override
    protected void configure() {
        bind(Swords.class).toInstance(plugin);
        bind(NamespacedKey.class).toInstance(key);
    }

}
