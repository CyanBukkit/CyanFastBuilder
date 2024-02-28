package cn.cyanbukkit.fastbuild.core

import java.util.*


object Core {

    var waiting  = mutableSetOf<UUID>()

    var integerCache = mutableMapOf<UUID, MutableList<Int>>()


    fun msToTime(ms: Int): String {
        return String.format("%.3f", *arrayOf<Any>(ms / 1000.0))
    }

    fun tickToTime(tick: Int): String {
        return String.format("%.3f", *arrayOf<Any>(tick * 0.05))
    }

    fun cal(oldRoom: Int, left: Boolean, b: BooleanArray): Int {
        var oldroom = oldRoom
        val l = b.size
        if (left) {
            while (oldroom != l - 2) {
                if (!b[(if ((oldroom == 0)) 1 else (if ((oldroom % 2 == 0)) -2 else 2)).let { oldroom += it; oldroom }]) continue
                return oldroom
            }
        } else {
            while (oldroom != l - 1) {
                if (!b[(if ((oldroom == 1)) -1 else (if ((oldroom % 2 == 0)) 2 else -2)).let { oldroom += it; oldroom }]) continue
                return oldroom
            }
        }
        return -1
    }


}