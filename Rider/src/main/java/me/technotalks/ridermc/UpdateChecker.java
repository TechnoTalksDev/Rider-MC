package me.technotalks.ridermc;

import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class UpdateChecker {

    private JavaPlugin plugin;
    private int resourceId;
    private Main main;

    public String getLatestVersion() throws IOException {
        URL url = new URL("https://api.spigotmc.org/legacy/update.php?resource=93807");
        InputStream inputStream = url.openStream();
        Scanner scanner = new Scanner(inputStream);
        String string = scanner.nextLine();
        return string;
    }
}
