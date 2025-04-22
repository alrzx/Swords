package me.alrxz.swords.Utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ItemBuilder {

    private ItemStack itemStack;
    private ItemMeta itemMeta;

    public ItemBuilder(Material mat) {
        this.itemStack = new ItemStack(mat);
    }

    public ItemBuilder setName(String name) {
        itemMeta.displayName(Component.text(name));
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchant, int level, boolean val) {
        itemMeta.addEnchant(enchant, level, val);
        return this;
    }

    public ItemBuilder addLore(String lore) {
        if(itemMeta.hasLore()) {
            itemMeta.lore().add(Component.text(lore));
            return this;
        }

        itemMeta.lore(List.of(Component.text(lore)));
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        itemMeta.lore(lore.stream().map(Component::text).toList());
        return this;
    }

    public ItemBuilder addAttribute(Attribute attribute, AttributeModifier modif) {
        itemMeta.addAttributeModifier(attribute, modif);
        return this;
    }

    public ItemBuilder addFlag(ItemFlag flag) {
        itemMeta.addItemFlags(flag);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
