

package cn.cyanbukkit.fastbuild.score;

import java.util.Collection;

public interface BoardInstance {


    void update(String title,Collection<String> lines);

    void delete();
}
