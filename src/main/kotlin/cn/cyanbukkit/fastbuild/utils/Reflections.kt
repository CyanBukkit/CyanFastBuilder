package cn.cyanbukkit.fastbuild.utils

object Reflections {

    fun packetSetValue(obj: Any, name: String, value: Any) {
        try {
            val field = obj::class.java.getDeclaredField(name)
            field.isAccessible = true
            field.set(obj, value)
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    fun packetGetValue(obj: Any, name: String): Any? {
        return try {
            val field = obj::class.java.getDeclaredField(name)
            field.isAccessible = true
            field.get(obj)
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }
    }

}