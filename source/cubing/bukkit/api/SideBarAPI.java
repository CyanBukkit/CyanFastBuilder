package cubing.bukkit.api;

import org.bukkit.scoreboard.*;

public class SideBarAPI
{
    public SideBarAPI() {
        super();
    }
    
    public static Scoreboard sidebarTeams(final Scoreboard scoreboard, final String[][] strings, final int[] scores) {
        final Objective objective = scoreboard.getObjective(DisplaySlot.SIDEBAR);
        for (int i = 0; i < scores.length; ++i) {
            if (strings[i].length == 1) {
                objective.getScore(strings[i][0]).setScore(scores[i]);
            }
            else if (strings[i].length == 3) {
                final Team x = scoreboard.registerNewTeam(strings[i][1]);
                x.addEntry(strings[i][1]);
                x.setPrefix(strings[i][0]);
                x.setSuffix(strings[i][2]);
                objective.getScore(strings[i][1]).setScore(scores[i]);
            }
            else if (strings[i].length == 2) {
                final Team x = scoreboard.registerNewTeam(strings[i][0]);
                x.addEntry(strings[i][0]);
                final String[] str = prefixSuffixSplit(strings[i][1]);
                x.setPrefix(str[0]);
                x.setSuffix(str[1]);
                objective.getScore(strings[i][0]).setScore(scores[i]);
            }
        }
        return scoreboard;
    }
    
    public static String[] prefixSuffixSplit(final String str) {
        String suffix = "";
        String prefix;
        if (str.length() <= 16) {
            prefix = str;
        }
        else if (str.charAt(15) == '¡ì') {
            if (str.charAt(16) < 'k') {
                prefix = str.substring(0, 15);
                suffix = str.substring(15);
            }
            else {
                prefix = str.substring(0, 15);
                boolean b = true;
                for (int j = 13; j >= 0; --j) {
                    if (str.charAt(j) == '¡ì' && str.charAt(j + 1) < 'k') {
                        b = false;
                        suffix = "¡ì" + str.charAt(j + 1) + str.substring(15);
                    }
                }
                if (b) {
                    suffix = str.substring(15);
                }
            }
        }
        else {
            prefix = str.substring(0, 16);
            boolean b = true;
            for (int j = 14; j >= 0; --j) {
                if (str.charAt(j) == '¡ì' && str.charAt(j + 1) < 'k') {
                    suffix = "¡ì" + str.charAt(j + 1) + str.substring(16);
                    b = false;
                    break;
                }
            }
            if (b) {
                suffix = str.substring(16);
            }
        }
        return new String[] { prefix, suffix };
    }
}
