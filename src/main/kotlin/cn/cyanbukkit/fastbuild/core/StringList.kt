package cn.cyanbukkit.fastbuild.core

import cn.cyanbukkit.fastbuild.CyanFastBuilder


object StringList {

    val typename = CyanFastBuilder.instance.config.getStringList("typename")

    val MenuTitle = CyanFastBuilder.instance.config.getString("MenuTitle")

    var min = CyanFastBuilder.instance.config.getString("min")

    var max = CyanFastBuilder.instance.config.getString("max")

    var FallDelayLore = CyanFastBuilder.instance.config.getStringList("FallDelayLore")

    var nomapsavaliable = CyanFastBuilder.instance.config.getString("nomapsavaliable")

    var cancelled = CyanFastBuilder.instance.config.getString("cancelled")


}