package me.alrxz.swords.Items.Weapons;

import com.google.inject.Inject;
import me.alrxz.swords.Swords;
import me.alrxz.swords.Utils.ItemBuilder;
import me.alrxz.swords.Utils.RecipeInitializer;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FrostSword implements Listener, RecipeInitializer {

    private final ItemStack item;
    private final Swords swords;

    @Inject
    public FrostSword(final @NotNull Swords swords) {
        this.swords = swords;
        this.item = new ItemBuilder(Material.DIAMOND_SWORD)
                .setName("§bFrost Sword")
                .setLore(List.of("§8Do you wanna build a snowman", "", "§fAbility - §bFrost §7(Attack)§f:", "§7The damaged player starts freezing and damage increases over time (max: 1.75x)"))
                .addEnchantment(Enchantment.DAMAGE_ALL, 6, true)
                .build();

    }



    @Inject
    @Override
    public void initRecipe(@NotNull Swords swords) {

        ShapedRecipe recipe = new ShapedRecipe(item);


    }
}
