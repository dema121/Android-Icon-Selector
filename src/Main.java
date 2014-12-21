import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by dema121 on 06/12/14.
 */
public class Main {
    private static BufferedReader inputFileReader;
    private static ArrayList<String> fromIcons;
    private static ArrayList<String> toIcons;
    private static ArrayList<IconAndroid> icons;
    private static boolean blankSpaceFound = false;
    private static String iconsRootDirectoryString;
    private static String iconsToDirectoryString;
    public static String startExecutionDirectoryString;

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Too few arguments to program! Requested: fromIconsDirectory, toIconsDirectory, iconsToSelectFile");
            return;
        }
        //System.out.print(args[0] + " " + args[1]);
        try {
            startExecutionDirectoryString = Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        iconsRootDirectoryString = args[0];
        iconsToDirectoryString = args[1];

        BufferedReader chooseReader = new BufferedReader(new InputStreamReader(System.in));
        fromIcons = new ArrayList<String>();
        toIcons = new ArrayList<String>();
        icons = new ArrayList<IconAndroid>();
        try {
            inputFileReader = new BufferedReader(new FileReader(args[2]));
            String cLine;
            while ((cLine = inputFileReader.readLine()) != null) {
                if (cLine.equals("")) {
                    cLine = inputFileReader.readLine();
                    blankSpaceFound = true;
                }
                if (!blankSpaceFound) {
                    fromIcons.add(cLine);
                } else {
                    toIcons.add(cLine);
                }
            }

            boolean considerDestinationIconName = !(toIcons.size() < fromIcons.size());
            if (!considerDestinationIconName) {
                if (toIcons.size() != 0) {
                    System.out.println("  --> Destination name is IGNORED! Destination name number doesn't correspond to icons number <--  \n");
                }
            }
            for (int i = 0; i < fromIcons.size(); i++) {
                if (!considerDestinationIconName) {
                    icons.add(new IconAndroid(fromIcons.get(i).split(" ")[0], fromIcons.get(i).split(" ")[1], Paths.get(iconsRootDirectoryString),
                                              Paths.get(iconsToDirectoryString)));
                } else {
                    icons.add(new IconAndroid(fromIcons.get(i).split(" ")[0], fromIcons.get(i).split(" ")[1], Paths.get(iconsRootDirectoryString),
                                              Paths.get(iconsToDirectoryString), toIcons.get(i)));
                }
            }

            System.out.println("Type formats you want separated by a space:\n" +
                    "   mdpi hdpi xhdpi xxhdpi xxxhdpi\n" +
                    "Type all if you want all");

            String choose = chooseReader.readLine();
            String[] dpisToUseStr = choose.split(" ");
            IconAndroid.DPI[] dpis = null;
            if (!choose.equalsIgnoreCase("all")) {
                dpis = IconAndroid.DPI.getDPIfromString(dpisToUseStr);
            }
            for (IconAndroid icon : icons) {
                if (choose.equalsIgnoreCase("all")) {
                    icon.exportAll();
                } else {
                    icon.export(dpis);
                }
            }
            System.out.println("Finished!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
