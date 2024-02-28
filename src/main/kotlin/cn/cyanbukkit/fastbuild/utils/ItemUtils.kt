package cn.cyanbukkit.fastbuild.utils


import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.enchantments.EnchantmentTarget
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.potion.PotionEffect
import java.util.*


object ItemUtils {

    fun item(
        mat: Material,
        name: String,
        st: Int,
        data: Int,
        glow: Boolean,
        unBreak: Boolean,
        flags: MutableList<ItemFlag>,
        each: MutableList<Enchantment>,
        lvl: IntArray,
        lore: MutableList<String>
    ): ItemStack {
        val itemStack = if (data == -1) {
            ItemStack(mat, st)
        } else {
            ItemStack(mat, st, data.toShort())
        }

        val meta = itemStack.itemMeta
        name.let {
            meta.displayName = (if (glow) "§r" else "") + it
        }
        if (glow) {
            meta.addEnchant(Glow(70), 1, true)
        }
        if (unBreak) {
            meta.spigot().isUnbreakable = true
        }
        flags.let {
            meta.addItemFlags(*it.toTypedArray())
        }
        lore.let {
            meta.lore = it
        }
        each.let {
            for (i in it.indices) {
                meta.addEnchant(it[i], lvl[i] ?: 0, true)
            }
        }
        itemStack.itemMeta = meta
        return itemStack
    }


    fun potion(
        display: String,
        effect: PotionEffect,
        amount: Int,
        data: Int,
        hideEffect: Boolean,
        lore: List<String>
    ): ItemStack {
        val itemStack = ItemStack(Material.POTION, amount, data.toShort())
        val itemMeta = itemStack.itemMeta as PotionMeta
        display.let {
            itemMeta.displayName = it
        }
        if (hideEffect) {
            itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        }
        effect.let {
            itemMeta.addCustomEffect(it, true)
        }
        lore.let {
            itemMeta.lore = it
        }
        itemStack.itemMeta = itemMeta
        return itemStack
    }


    fun skull(
        mat: Material,
        name: String,
        st: Int,
        data: Int,
        glow: Boolean,
        lore: List<String>,
        url: String
    ): ItemStack {
        val itemStack = if (data == -1) {
            ItemStack(mat, st)
        } else {
            ItemStack(mat, st, data.toShort())
        }
        val itemMeta = itemStack.itemMeta as SkullMeta
        if (url.length > 16) {
            val gameProfile = GameProfile(UUID.randomUUID(), null)
            gameProfile.properties.put("textures", Property("textures", url))
            Reflections.packetSetValue(itemMeta, "profile", gameProfile)
        } else {
            itemMeta.owner = url
        }

        itemMeta.displayName = (if (glow) "§r" else "") + name
        if (glow) {
            itemMeta.addEnchant(Glow(70), 1, true)
        }
        lore?.let {
            itemMeta.lore = it
        }
        itemStack.itemMeta = itemMeta
        return itemStack
    }



    fun setLore(itemStack: ItemStack, lore: List<String>): ItemStack {
        val meta = itemStack.itemMeta
        meta.lore = lore
        itemStack.itemMeta = meta
        return itemStack
    }

    fun glow(itemStack: ItemStack): ItemStack {
        val meta = itemStack.itemMeta
        meta.addEnchant(Glow(70), 1, true)
        itemStack.itemMeta = meta
        return itemStack
    }

}


class Glow(i: Int): Enchantment(i) {
    override fun getName(): String? {
        return null
    }

    override fun getMaxLevel(): Int {
        return 0
    }

    override fun getStartLevel(): Int {
        return 0
    }

    override fun getItemTarget(): EnchantmentTarget? {
        return null
    }

    override fun conflictsWith(p0: Enchantment?): Boolean {
        return false
    }

    override fun canEnchantItem(p0: ItemStack?): Boolean {
        return false
    }

}