package cn.cyanbukkit.fastbuild.core

import cn.cyanbukkit.fastbuild.CyanFastBuilder
import cn.cyanbukkit.fastbuild.utils.ItemUtils
import cn.cyanbukkit.fastbuild.utils.ItemUtils.item
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

object ItemStackList {


    val menu: InventoryHolder = InventoryHolder { null }

    val settings: InventoryHolder = InventoryHolder { null }

    val confirm: InventoryHolder = InventoryHolder { null }


    lateinit var openkit: ItemStack
    lateinit var navigator: ItemStack
    lateinit var pickaxe: ItemStack
    lateinit var Settings: ItemStack
    lateinit var Reset: ItemStack
    lateinit var Blocks: MutableList<ItemStack>
    lateinit var categoryblockeditor: MutableList<ItemStack>
    lateinit var blockeditor: MutableList<ItemStack>
    lateinit var wooleditor: MutableList<ItemStack>
    lateinit var sandstoneeditor: MutableList<ItemStack>
    lateinit var oreblockeditor: MutableList<ItemStack>
    lateinit var oreeditor: MutableList<ItemStack>
    lateinit var quartzeditor: MutableList<ItemStack>
    lateinit var woodeditor: MutableList<ItemStack>
    lateinit var clayeditor: MutableList<ItemStack>
    var blockedit: String = CyanFastBuilder.instance.config.getString("blockedit")
    var sandstoneedit: String = CyanFastBuilder.instance.config.getString("sandstoneedit")
    var wooledit: String = CyanFastBuilder.instance.config.getString("wooledit")
    val oreblockedit: String = CyanFastBuilder.instance.config.getString("oreblockedit")
    val oreedit: String = CyanFastBuilder.instance.config.getString("oreedit")
    val quartzedit: String = CyanFastBuilder.instance.config.getString("quartzedit")
    val woodedit: String = CyanFastBuilder.instance.config.getString("woodedit")
    val clayedit: String = CyanFastBuilder.instance.config.getString("clayedit")
    lateinit var ResetConfirm: Inventory


    fun load() {
        val plugin = CyanFastBuilder.instance
        val resetconfirm = plugin.config.getString("resetconfirm")
        val breaktool = plugin.config.getString("breaktool")
        val nv = plugin.config.getString("nv")
        val settings = plugin.config.getString("settings")
        val reset = plugin.config.getString("reset")
        val category = plugin.config.getString("category")
        val block = plugin.config.getStringList("block")
        val yes = plugin.config.getString("yess")
        val no = plugin.config.getString("noo")

        navigator = ItemUtils.item(
            Material.COMPASS,
            nv, 1, -1,
            glow = false, unBreak = false, flags = mutableListOf(), each = mutableListOf(), lvl = IntArray(0),
            lore = mutableListOf()
        )
        pickaxe = ItemUtils.item(
            Material.DIAMOND_PICKAXE,
            breaktool, 1, -1, glow = true, unBreak = true,
            mutableListOf(ItemFlag.HIDE_ATTRIBUTES), mutableListOf(), IntArray(0), mutableListOf()
        )
        Settings = ItemUtils.item(
            Material.REDSTONE_COMPARATOR,
            settings, 1, -1, glow = false, unBreak = false,
            mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
        )
        Reset = ItemUtils.item(
            Material.BARRIER,
            reset, 1, -1, glow = false, unBreak = false,
            mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
        )
        openkit = ItemUtils.item(
            Material.SANDSTONE, blockedit,
            1, 2, glow = false, unBreak = false,
            mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
        )
        val inv = Bukkit.createInventory(confirm, 27, resetconfirm)
        inv.setItem(
            11, ItemUtils.item(
                Material.STAINED_CLAY,
                yes, 1, 13, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
            )
        )
        inv.setItem(
            15, ItemUtils.item(
                Material.STAINED_CLAY,
                no, 1, 14, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
            )
        )
        ResetConfirm = inv
        val categoryblockeditor = mutableListOf(
            ItemUtils.item(
                Material.SANDSTONE,
                block[0], 1, -1, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf(category)
            ),
            ItemUtils.item(
                Material.WOOL,
                block[1], 1, -1, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf(category)
            ),
            ItemUtils.item(
                Material.DIAMOND_BLOCK,
                block[2], 1, -1, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf(category)
            ),
            ItemUtils.item(
                Material.DIAMOND_ORE,
                block[3], 1, -1, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf(category)
            ),
            ItemUtils.item(
                Material.QUARTZ_BLOCK,
                block[4], 1, -1, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf(category)
            ),
            ItemUtils.item(
                Material.WOOD,
                block[5], 1, -1, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf(category)
            ),
            ItemUtils.item(
                Material.STAINED_CLAY,
                block[6], 1, -1, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf(category)
            )
        )
        val blockeditor = arrayOf(
            ItemUtils.item(
                Material.MELON_BLOCK,
                "西瓜", 1, -1, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
            ),
            ItemUtils.item(
                Material.SNOW_BLOCK,
                "雪块", 1, -1, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
            ),
            ItemUtils.item(
                Material.OBSIDIAN,
                "黑曜石", 1, -1, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
            ),
            ItemUtils.item(
                Material.ENDER_STONE,
                "§r", 1, -1, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
            )
        )
        val sandstoneeditor = arrayOf(
            ItemUtils.item(
                Material.SANDSTONE,
                "§9", 1, 0, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
            ),
            ItemUtils.item(
                Material.SANDSTONE,
                "§6", 1, 1, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
            ),
            ItemUtils.item(
                Material.SANDSTONE,
                "§96", 1, 2, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
            ),
            ItemUtils.item(
                Material.RED_SANDSTONE,
                "§9", 1, 0, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
            ),
            ItemUtils.item(
                Material.RED_SANDSTONE,
                "§9", 1, 1, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
            ),
            ItemUtils.item(
                Material.RED_SANDSTONE,
                "§9", 1, 2, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
            )
        )
        val wooleditor = arrayOf(
            ItemUtils.item(
                Material.WOOL,
                Material.WOOL.name,
                1, 0, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
            ),
            ItemUtils.item(
                Material.WOOL,
                Material.WOOL.name,
                1, 1, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
            ), ItemUtils.item(
                Material.WOOL,
                Material.WOOL.name,
                1, 2, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
            ), ItemUtils.item(
                Material.WOOL,
                Material.WOOL.name,
                1, 3, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
            ), ItemUtils.item(
                Material.WOOL,
                Material.WOOL.name,
                1, 4, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
            ), ItemUtils.item(
                Material.WOOL,
                Material.WOOL.name,
                1, 5, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
            ), ItemUtils.item(
                Material.WOOL,
                Material.WOOL.name,
                1, 6, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
            ), ItemUtils.item(
                Material.WOOL,
                Material.WOOL.name,
                1, 7, glow = false, unBreak = false,
                mutableListOf(), mutableListOf(), IntArray(0), mutableListOf()
            ), ItemUtils.item(
                Material.WOOL,
                Material.WOOL.name,
                1,
                8,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.WOOL,
                Material.WOOL.name,
                1,
                9,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.WOOL,
                Material.WOOL.name,
                1,
                10,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.WOOL,
                Material.WOOL.name,
                1,
                11,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.WOOL,
                Material.WOOL.name,
                1,
                12,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.WOOL,
                Material.WOOL.name,
                1,
                13,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.WOOL,
                Material.WOOL.name,
                1,
                14,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.WOOL,
                Material.WOOL.name,
                1,
                15,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            )
        )
        val oreblockeditor = arrayOf(
            ItemUtils.item(
                Material.DIAMOND_BLOCK,
                Material.DIAMOND_BLOCK.name,
                1,
                -1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.IRON_BLOCK,
                Material.IRON_BLOCK.name,
                1,
                -1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.REDSTONE_BLOCK,
                Material.REDSTONE_BLOCK.name,
                1,
                -1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.LAPIS_BLOCK,
                Material.LAPIS_BLOCK.name,
                1,
                -1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.EMERALD_BLOCK,
                Material.EMERALD_BLOCK.name,
                1,
                -1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.GOLD_BLOCK,
                Material.GOLD_BLOCK.name,
                1,
                -1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.COAL_BLOCK,
                Material.COAL_BLOCK.name,
                1,
                -1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            )
        )
        val oreeditor = arrayOf(
            ItemUtils.item(
                Material.DIAMOND_ORE,
                Material.DIAMOND_ORE.name,
                1,
                -1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.IRON_ORE,
                Material.IRON_ORE.name,
                1,
                -1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.REDSTONE_ORE,
                Material.REDSTONE_ORE.name,
                1,
                -1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.LAPIS_ORE,
                Material.LAPIS_ORE.name,
                1,
                -1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.EMERALD_ORE,
                Material.EMERALD_ORE.name,
                1,
                -1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.GOLD_ORE,
                Material.GOLD_ORE.name,
                1,
                -1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.COAL_ORE,
                Material.COAL_ORE.name,
                1,
                -1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            )
        )
        val quartzeditor = arrayOf(
            ItemUtils.item(
                Material.QUARTZ_BLOCK,
                Material.QUARTZ_BLOCK.name,
                1,
                0,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.QUARTZ_BLOCK,
                Material.QUARTZ_BLOCK.name,
                1,
                1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ), ItemUtils.item(
                Material.QUARTZ_BLOCK,
                Material.QUARTZ_BLOCK.name,
                1,
                2,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            )
        )
        val woodeditor = arrayOf(
            item(
                Material.WOOD,
                "§6QWQ",
                1,
                0,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOD,
                "§6QWQ",
                1,
                1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOD,
                "§6QWQ",
                1,
                2,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOD,
                "§6QWQ",
                1,
                3,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOD,
                "§6QWQ",
                1,
                4,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOD,
                "§6QWQ",
                1,
                5,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.LOG,
                "§6QWQ",
                1,
                0,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.LOG,
                "§6QWQ",
                1,
                1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.LOG,
                "§6QWQ",
                1,
                2,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.LOG,
                "§6QWQ",
                1,
                3,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.LOG_2,
                "§6QWQ",
                1,
                0,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.LOG_2,
                "§6QWQ",
                1,
                1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            )
        )
        val clayeditor = arrayOf(
            item(
                Material.STAINED_CLAY,
                "§8CLAY",
                1,
                0,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY,
                "§8CLAY",
                1,
                1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY,
                "§8CLAY",
                1,
                2,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY,
                "§8CLAY",
                1,
                3,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY,
                "§8CLAY",
                1,
                4,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY,
                "§8CLAY",
                1,
                5,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY,
                "§8CLAY",
                1,
                6,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY,
                "§8CLAY",
                1,
                7,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY,
                "§8CLAY",
                1,
                8,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY,
                "§8CLAY",
                1,
                9,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY,
                "§8CLAY",
                1,
                10,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY,
                "§8CLAY",
                1,
                11,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY,
                "§8CLAY",
                1,
                12,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY,
                "§8CLAY",
                1,
                13,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY,
                "§8CLAY",
                1,
                14,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY,
                "§8CLAY",
                1,
                15,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.HARD_CLAY,
                "§8CLAY",
                1,
                -1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            )
        )
        val blocks = arrayOf(
            item(
                Material.SANDSTONE,
                "§8STONE",
                64,
                0,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.SANDSTONE,
                "§8STONE",
                64,
                1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.SANDSTONE,
                "§8STONE",
                64,
                2,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.RED_SANDSTONE,
                "§8STONE",
                64,
                0,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.RED_SANDSTONE,
                "§8STONE",
                64,
                1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.RED_SANDSTONE,
                "§8STONE",
                64,
                2,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOL,
                "§9羊毛",
                64,
                0,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOL,
                "§9羊毛",
                64,
                1,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOL,
                "§9羊毛",
                64,
                2,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOL,
                "§9羊毛",
                64,
                3,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOL,
                "§9羊毛",
                64,
                4,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOL,
                "§9羊毛",
                64,
                5,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOL,
                "§9羊毛",
                64,
                6,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOL,
                "§9羊毛",
                64,
                7,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOL,
                "§9羊毛",
                64,
                8,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOL,
                "§9羊毛",
                64,
                9,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOL,
                "§9羊毛",
                64,
                10,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOL,
                "§9羊毛",
                64,
                11,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOL,
                "§9羊毛",
                64,
                12,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOL,
                "§9羊毛",
                64,
                13,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOL,
                "§9羊毛",
                64,
                14,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOL,
                "§9羊毛",
                64,
                15,
                false,
                unBreak = false,
                mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.DIAMOND_BLOCK, "§6方块", 64, -1, false, unBreak = false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.IRON_BLOCK, "§6方块", 64, -1, false, unBreak = false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.REDSTONE_BLOCK, "§6方块", 64, -1, false, unBreak = false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.LAPIS_BLOCK, "§6方块", 64, -1, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.EMERALD_BLOCK, "§6方块", 64, -1, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.GOLD_BLOCK, "§6方块", 64, -1, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.COAL_BLOCK, "§6方块", 64, -1, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.DIAMOND_ORE, "§6方块", 64, -1, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.IRON_ORE, "§6方块", 64, -1, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.REDSTONE_ORE, "§6方块", 64, -1, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.LAPIS_ORE, "§6方块", 64, -1, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.EMERALD_ORE, "§6方块", 64, -1, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.GOLD_ORE, "§6方块", 64, -1, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.COAL_ORE, "§6方块", 64, -1, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.QUARTZ_BLOCK, "§6方块", 64, 0, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.QUARTZ_BLOCK, "§6方块", 64, 1, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.QUARTZ_BLOCK, "§6方块", 64, 2, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOD, "§6方块", 64, 0, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOD, "§6方块", 64, 1, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOD, "§6方块", 64, 2, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOD, "§6方块", 64, 3, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOD, "§6方块", 64, 4, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.WOOD, "§6方块", 64, 5, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.LOG, "§6方块", 64, 0, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.LOG, "§6方块", 64, 1, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.LOG, "§6方块", 64, 2, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.LOG, "§6方块", 64, 3, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.LOG_2, "§6方块", 64, 0, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.LOG_2, "§6方块", 64, 1, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY, "§6方块", 64, 0, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY, "§6方块", 64, 1, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY, "§6方块", 64, 2, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY, "§6方块", 64, 3, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY, "§6方块", 64, 4, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY, "§6方块", 64, 5, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY, "§6方块", 64, 6, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY, "§6方块", 64, 7, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY, "§6方块", 64, 8, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY, "§6方块", 64, 9, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY, "§6方块", 64, 10, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY, "§6方块", 64, 11, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY, "§6方块", 64, 12, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY, "§6方块", 64, 13, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY, "§6方块", 64, 14, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.STAINED_CLAY, "§6方块", 64, 15, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.HARD_CLAY, "§6方块", 64, -1, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.MELON_BLOCK, "§6方块", 64, 2, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.SNOW_BLOCK, "§6方块", 64, -1, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.OBSIDIAN, "§6方块", 64, -1, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            ),
            item(
                Material.ENDER_STONE, "§6方块", 64, -1, false, false, mutableListOf(),
                mutableListOf(),
                IntArray(0),
                mutableListOf()
            )
        )


        for (j in blocks.indices) {
            Blocks[j] = blocks[j]
        }

        for (j in categoryblockeditor.indices) {
            ItemStackList.categoryblockeditor[j] = categoryblockeditor[j]
        }

        for (j in blockeditor.indices) {
            ItemStackList.blockeditor[j] = blockeditor[j]
        }

        for (j in sandstoneeditor.indices) {
            ItemStackList.sandstoneeditor[j] = sandstoneeditor[j]
        }

        for (j in wooleditor.indices) {
            ItemStackList.wooleditor[j] = wooleditor[j]
        }

        for (j in oreblockeditor.indices) {
            ItemStackList.oreblockeditor[j] = oreblockeditor[j]
        }

        for (j in oreeditor.indices) {
            ItemStackList.oreeditor[j] = oreeditor[j]
        }

        for (j in quartzeditor.indices) {
            ItemStackList.quartzeditor[j] = quartzeditor[j]
        }

        for (j in woodeditor.indices) {
            ItemStackList.woodeditor[j] = woodeditor[j]
        }

        for (j in clayeditor.indices) {
            ItemStackList.clayeditor[j] = clayeditor[j]
        }
    }

}
