package com.flx.xs.common.util;

import java.io.File;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Attributes.Name;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class ReadManifest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final File f = new File(args[0]);
		String seperator = args[1];
		if (!f.canRead()) {
			System.err.println(f.getAbsolutePath() + ": no such file");
			System.exit(-1);
		}
		try {
			JarFile jar = new JarFile(f);
			final Manifest manifest = jar.getManifest();
			final Attributes mattr = manifest.getMainAttributes();
			//System.out.println(f.getAbsolutePath());
			//System.out.println("Main attrs: ");
			StringBuffer classpath = new StringBuffer();
			for (Object a : mattr.keySet()) {
				if(a instanceof Name)
					if(((Name) a).toString().equals("Class-Path")){
						String[] items = mattr.getValue((Name) a).split(" ");
						for(int i=0;i<items.length;i++) {
							String item = items[i];
							if(i==0)
								classpath.append(item);
							else
								classpath.append(seperator+item);
						}
//						System.out.println("\\t " + a + ": "
//						+ mattr.getValue((Name) a));
						System.out.println(classpath.toString());
					} else if(((Name) a).toString().equals("Main-Class"))
						System.out.println("\\t " + a + ": "
								+ mattr.getValue((Name) a));
			}
			//System.out.println("\\nReading other attrs:\\n");

			final Map<String, Attributes> attrs = manifest.getEntries();
			for (String name : attrs.keySet()) {
				final Attributes attr = attrs.get(name);
				System.out.println(name + ": \\n");
				for (Object a : attr.keySet()) {
					System.out.println("\\t " + a + ": "
							+ attr.getValue((Name) a));
				}
			}
		} catch (Exception x) {
			System.err.println("Failed to read manifest for "
					+ f.getAbsolutePath() + ": " + x);
		}
	}
}
