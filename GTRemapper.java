import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * A helper class to map the GregTech.lang file.
 * Map: GT Lang -> Properties
 * Unmap: Properties -> GT Lang
 * It's very simple! Use in your own privilege!
 */
public class GTRemapper {
	public static void main(String[] args) {
		File input = new File(args[0]);
		File output = new File(args[1]);
		boolean mapOrRemap = Boolean.parseBoolean(args[2]); // true map false unmap
		try {
			Scanner scanner = new Scanner(input);
			Writer writer = new OutputStreamWriter(new FileOutputStream(output), StandardCharsets.UTF_8);
            if (!mapOrRemap) {
                writer.write("# Configuration file\n");
                writer.write("\n");
                writer.write("enablelangfile {\n");
                writer.write("    B:UseThisFileAsLanguageFile=false\n");
                writer.write("}\n\n\n");
                writer.write("languagefile {\n");
            }
			while (scanner.hasNext()) {
				if (mapOrRemap) {
					String line = scanner.nextLine();
					if (line.startsWith("    S:"))
						writer.write(line.substring(line.indexOf("    S:") + 6) + "\n");
				} else {
					String line = scanner.nextLine();
					writer.write("    S:" + line + "\n");
				}
			}
            if (!mapOrRemap) {
                writer.write("}\n");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
