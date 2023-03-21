import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Launcher {
    File launcherFile = new File("Launcher.bat");
    static Launcher launcher = null;
    private Launcher() {
        if(!launcherFile.exists()) {
            try {
                launcherFile.createNewFile();
                writeLauncherInstructions(launcherFile);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Launcher singleton() {
        if(launcher == null) {
            return new Launcher();
        }

        return launcher;
    }

    private void writeLauncherInstructions(File launcher) {
        try {
            PrintWriter writer = new PrintWriter(launcher);
            String path = Main.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);

            writer.write("@echo off\n");
            writer.write("java -jar \"" + path + "\"" + "\n");
            writer.write("pause\n");
            writer.write("exit\n");
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Console con = System.console();

        if(con != null) {
            new UserInteraction().getUserInput();
        }
    }
}
