package kronaegit.plugure;

import kronaegit.plugure.useful.PlugurePlugin;

public class PlugureLoader extends PlugurePlugin {
    public PlugureLoader() {
        super("&7[&a&lPlugure&7*&r]&f ", "&7[&a&lPlugure&7*&r]&f ");
    }

    @Override
    public void enabled() {
        message().log("Plugure library is enabled successfully.");
        message().log("https://github.com/KRonaeGit/Plugure << Visit to learn about Plugure library..!");
    }

    @Override
    public void disabling() {
        message().log("Plugure Library is disabled.");
    }

    public static void main(String[] args) {
        System.out.println("Plugure Library has every essential functions to create your own Paper plugin.");
        System.out.println("Please put this .jar file into the plugins directory in the server main directory.");
        System.out.println("And add 'softdepend: [\"Plugure\"]' line to your 'plugins.yml' resource of your plugin.");
        System.out.println("Add dependency of this .jar file or some code to load same version Plugure.");
        System.out.println("Then you can code with Plugure. Now your code became easier.");
        System.out.println();
        System.out.println("More useful/helpful description/usage is on Github: https://github.com/KRonaeGit/Plugure << Visit and learn!");
        System.out.println("Thanks for using this library! Have a good day. -Text by. KRonae");
        System.out.println("(EasterEgg1 : Execute Plugure .jar file and see this message)");
    }
}
