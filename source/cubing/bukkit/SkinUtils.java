package cubing.bukkit;

import com.mojang.authlib.*;
import com.mojang.authlib.properties.*;

public class SkinUtils
{
    public SkinUtils() {
        super();
    }
    
    public static String[] getProfileSkin(final GameProfile profile) {
        final Property property = profile.getProperties().get((Object)"textures").iterator().next();
        return new String[] { property.getValue(), property.getSignature() };
    }
}
