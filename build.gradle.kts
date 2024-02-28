// 26
plugins {
    kotlin("jvm") version "1.9.20"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("com.github.johnrengelman.shadow") version ("7.1.2")
}

version = 1.0

repositories {
    maven("https://maven.elmakers.com/repository")
    maven("https://nexus.cyanbukkit.cn/repository/maven-public")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    compileOnly("org.spigotmc:minecraft-server:1.8.8-SNAPSHOT")
    compileOnly("org.bukkit:craftbukkit:1.8.8-R0.1-SNAPSHOT")
    compileOnly("com.comphenix.protocol:ProtocolLib:5.0.0")
    // PAPI
    compileOnly("me.clip:placeholderapi:2.11.3")
    implementation(kotlin("stdlib-jdk8"))
}


bukkit {
    main = "cn.cyanbukkit.fastbuild.CyanFastBuilder"
    name = "CyanFastBuilder"
    version = project.version.toString()
    description = "§6别看了吧！就是一个可爱的插件宝宝 CyanBukkit生的"
    website = "https://cyanbukkit.net"
    depend = listOf("ProtocolLib", "CYANKOTLINLOADER")
}

kotlin {
    jvmToolchain(8)
}


tasks {
    compileJava {
        options.encoding = "UTF-8"
    }

    shadowJar {
        archiveFileName.set("CyanFastBuilder-${project.version}.jar")
    }

}
