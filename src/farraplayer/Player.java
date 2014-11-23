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

import farraplayer.players.FfmpegPlayer;

import java.io.File;

/**
 * @author Ivan Zaera
 */
public abstract class Player {

	private static Player instance = new FfmpegPlayer();

	public static Player get() {
		return instance;
	}

	public abstract void play(File file, final Runnable onEndCallback);
	public abstract void stop();
	public abstract void pause();
	public abstract void voldown();
	public abstract void volup();

}
