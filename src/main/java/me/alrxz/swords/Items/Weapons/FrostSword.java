package me.alrxz.swords.Items.Weapons;

import com.google.inject.Inject;
import me.alrxz.swords.Swords;
import me.alrxz.swords.Utils.ItemBuilder;
import me.alrxz.swords.Utils.RecipeInitializer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FrostSword implements Listener, RecipeInitializer {

    private final ItemStack item;
    @Inject
    private final Swords swords;
    private long loggedTime;
    private float hitAmt = 1;


    @Inject
    public FrostSword(final @NotNull Swords swords) {
        this.swords = swords;
        this.item = new ItemBuilder(Material.DIAMOND_SWORD)
                .setName("§bFrost Sword")
                .setLore(List.of("§8Do you wanna build a snowman", "", "§fAbility - §bFrost §7(Attack)§f:", "§7The damaged player starts freezing and damage increases over time (max: 1.75x)"))
                .addEnchantment(Enchantment.DAMAGE_ALL, 6, true)
                .build();
    }



    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {

        if(!(e.getDamager() instanceof Player) && !(e.getEntity() instanceof LivingEntity)) return;

        LivingEntity victim = (LivingEntity) e.getEntity();
        Player player = (Player) e.getDamager();

        if(!player.getActiveItem().hasItemMeta()) return;

        ItemStack item = player.getActiveItem();

        List<Component> lore = item.getItemMeta().lore();
        LegacyComponentSerializer legacySerializer = LegacyComponentSerializer.legacySection();

        boolean found = lore.stream().anyMatch(line ->
                legacySerializer.serialize(line).equals("§7Ability - §8Sonic Boom §7(Right-Click):")
        );

        if(!found) return;


        victim.setFreezeTicks(240);

        if(loggedTime < System.currentTimeMillis() + 5000L) {

            float buff = (float) (hitAmt < 1.75 ? hitAmt + 0.05 : 1.75);

            e.setDamage(e.getDamage() * buff);
            loggedTime = System.currentTimeMillis();

            return;
        }

        hitAmt = 1;
        loggedTime = System.currentTimeMillis();
    }



    @Inject
    @Override
    public void initRecipe(final @NotNull Swords swords) {

        ShapedRecipe recipe = new ShapedRecipe(item);

        recipe.shape("ABA", "ACA", "AUA");

        recipe.setIngredient('A', Material.PACKED_ICE);
        recipe.setIngredient('B', Material.NETHERITE_BLOCK);
        recipe.setIngredient('U', Material.BEACON);
    }
}
