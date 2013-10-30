/*
 * Copyright 2012 Robert 'Bobby' Zenz. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list
 * of conditions and the following disclaimer in the documentation and/or other materials
 * provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY Robert 'Bobby' Zenz ''AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL Robert 'Bobby' Zenz OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied, of Robert 'Bobby' Zenz.
 */
package org.bonsaimind.minecraftmiddleknife;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads, parses, manipulates and saves the .minecraft/options.txt-file.
 */
public class OptionsFile {

	public static final String SEPARATOR = ":";
	public static final String FILENAME = "options.txt";
	List<String> keys = new ArrayList<String>();
	List<String> values = new ArrayList<String>();

	public OptionsFile() {
	}

	/**
	 * Returns the value to the given key. Returns null if it failed.
	 * @param key The key you want.
	 * @return
	 */
	public String getOption(String key) {
		if (keys.contains(key)) {
			return values.get(keys.indexOf(key));
		}

		return null;
	}

	/**
	 * Check if the options.txt was (successfully) read.
	 * @return
	 */
	public boolean isRead() {
		return !keys.isEmpty() && !values.isEmpty();
	}

	/**
	 * Read the contents of the given file.
	 * @param fileOrPath
	 * @throws IOException
	 */
	public void read(String fileOrPath) throws IOException {
		File file = makeFile(fileOrPath);

		keys.clear();
		values.clear();

		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line;
		while ((line = reader.readLine()) != null) {
			String[] keyValue = line.split(SEPARATOR);
			keys.add(keyValue[0]);
			if (keyValue.length > 1) {
				values.add(keyValue[1]);
			} else {
				values.add("");
			}
		}

		reader.close();
	}

	/**
	 * Set the given option with the given value.
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setOption(String key, String value) {
		if (keys.indexOf(key) >= 0) {
			values.set(keys.indexOf(key), value);
			return true;
		}

		return false;
	}

	/**
	 * Set options from options-pairs. Every pair looks like this: "key:value".
	 * @param options An array of options with key separated from value by a colon.
	 */
	public void setOptions(Iterable<String> options) {
		if (options == null) {
			return;
		}

		for (String option : options) {
			int splitIdx = option.indexOf(":");
			if (splitIdx > 0) { // We don't want not-named options.
				setOption(option.substring(0, splitIdx), option.substring(splitIdx + 1));
			}
		}
	}

	/**
	 * Write to the given file.
	 */
	public void write(String fileOrPath) throws IOException {
		File file = makeFile(fileOrPath);

		BufferedWriter writer = new BufferedWriter(new FileWriter(file));

		for (int idx = 0; idx < keys.size(); idx++) {
			writer.write(keys.get(idx) + SEPARATOR + values.get(idx));
			writer.newLine();
		}

		writer.close();
	}

	private File makeFile(String pathOrFile) {
		File file = new File(pathOrFile);
		if (file.isDirectory()) {
			file = new File(file.getAbsolutePath(), FILENAME);
		}
		file = file.getAbsoluteFile();
		return file;
	}
}
