/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package farraplayer;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ivan Zaera
 */
public class Settings {

	static Settings instance = new Settings();

	public static Settings get() {
		return instance;
	}

	public final File rootDir = new File("/Users/Ivan/Sync/Musica");

	public final Set<String> mediaExtensions = new HashSet<String>(Arrays.asList(
		"mp3", "avi"
	));

}
