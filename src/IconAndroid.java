import java.io.File;
import java.io.IOException;
import java.nio.file.*;

/**
 * Created by dema121 on 06/12/14.
 */
public class IconAndroid {
    private String iconName;
    private String category;
    private Path rootDirectory;  //Directory with icons tree: action, alert, ecc.
    private String toIconName = null; //Icon final name
    private Path toRootDirectory; //Final icon directory


    public static enum DPI {
        MDPI ("drawable-mdpi"),
        HDPI ("drawable-hdpi"),
        XHDPI ("drawable-xhdpi"),
        XXHDPI ("drawable-xxhdpi"),
        XXXHDPI ("drawable-xxxhdpi");

        private String exportString;

        DPI(String exportString) {
            this.exportString = exportString;
        }

        public String getExportString() {
            return exportString;
        }


        public static DPI getDPIfromString(String string) {
            if (string.equalsIgnoreCase("mdpi")) {
                return DPI.MDPI;
            } else if (string.equalsIgnoreCase("hdpi")) {
                return DPI.HDPI;
            } else if (string.equalsIgnoreCase("xhdpi")) {
                return DPI.XHDPI;
            } else if (string.equalsIgnoreCase("xxhdpi")) {
                return DPI.XXHDPI;
            } else if (string.equalsIgnoreCase("xxxhdpi")) {
                return DPI.XXXHDPI;
            } else {
                throw new RuntimeException("dpi string is not supported");
            }
        }

        public static DPI[] getDPIfromString(String[] string) {
            DPI[] toRtn = new DPI[string.length];
            for (int i = 0; i < string.length; i++) {
                toRtn[i] = DPI.getDPIfromString(string[i]);
            }
            return toRtn;
        }
    }

    public IconAndroid(String category, String iconName, Path rootDirectory, Path toRootDirectory, String toIconName) {
        this.category = category;
        this.iconName = iconName;
        this.rootDirectory = rootDirectory;
        this.toIconName = toIconName;
        this.toRootDirectory = toRootDirectory;
    }

    public IconAndroid(String category, String iconName, Path rootDirectory, String toIconName) {
        this(category, iconName, rootDirectory, Paths.get(Main.startExecutionDirectoryString + File.separator + "selectedIcons"), toIconName);
    }
    public IconAndroid(String category, String iconName, Path rootDirectory, Path toRootDirectory) {
        this(category, iconName, rootDirectory, toRootDirectory, iconName);
    }
    public IconAndroid(String category, String iconName, Path rootDirectory) {
        this(category, iconName, rootDirectory, iconName);
    }

    public void export(DPI type) {
        File targetDir = new File(toRootDirectory.toFile(), type.getExportString());
        targetDir.mkdirs();
        Path sourceIcon = Paths.get(rootDirectory.toString(), category, type.getExportString(), iconName);
        Path targetIcon = Paths.get(toRootDirectory.toString(), type.getExportString(), toIconName);

        try {
            System.out.println("Exporting " + category + " " + iconName + " " + type);
            Files.copy(sourceIcon, targetIcon, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportAll() {
        for (DPI currentDPI : DPI.values()) {
            export(currentDPI);
        }
    }

    public void export(DPI[] dpis) {
        for (DPI dpi : dpis) {
            export(dpi);
        }
    }

}
