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
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Zaera
 */
public class Playlist {

	static Playlist instance = new Playlist();
	static Player player = Player.get();
	static Settings settings = Settings.get();
	static Util util = Util.get();

	public static Playlist get() {
		return instance;
	}

	List<File> played = new ArrayList<File>();
	List<File> toPlay = new ArrayList<File>();
	boolean stopped = false;
	Runnable onEndCallback = new Runnable() {
		@Override
		public void run() {
			onSongFinished();
		}
	};

	public List<File> getPlayed() {
		return played;
	}

	public List<File> getToPlay() {
		return toPlay;
	}

	public void prev() {
		stop();
		toPlay.add(0, played.remove(played.size() - 1));
		toPlay.add(0, played.remove(played.size() - 1));
		play();
	}

	public void next() {
		stop();
		play();
	}

	public void add(File file) {
		insert(toPlay.size(), file);
	}

	public void playNext(File file) {
		insert(1, file);
	}

	public void playNow(File file) {
		insert(0, file);
	}

	public void up(int index) {
		if (index == 0) {
			return;
		}

		File prev = toPlay.get(index - 1);
		File file = toPlay.get(index);

		toPlay.set(index, prev);
		toPlay.set(index - 1, file);
	}

	public void down(int index) {
		if (index >= toPlay.size()-1) {
			return;
		}

		File file = toPlay.get(index);
		File next = toPlay.get(index + 1);

		toPlay.set(index, next);
		toPlay.set(index + 1, file);
	}

	public void delete(int index) {
		toPlay.remove(index);
	}

	public void play() {
		stopped = false;
		if (toPlay.size()>0) {
			player.play(toPlay.get(0), onEndCallback);
		}
	}

	public void pause() {
		player.pause();
	}

	public void stop() {
		stopped = true;
		player.stop();
	}

	public void voldown() {
		player.voldown();
	}

	public void volup() {
		player.volup();
	}

	private void onSongFinished() {
		played.add(toPlay.remove(0));
		if (!stopped) {
			play();
		}
	}

	private void insert(int index, File file) {
		List<File> files = new ArrayList<File>();

		if (file.isDirectory()) {
			enumerate(file, files);
		}
		else {
			files.add(file);
		}

		boolean play = false;
		if (toPlay.size()==0) {
			play = true;
		}

		toPlay.addAll(index, files);

		if (play) {
			play();
		}
	}

	private void enumerate(File dir, List<File> files) {
		File[] dirFiles = dir.listFiles();
		for (File dirFile : dirFiles) {
			if (dirFile.isDirectory()){
				enumerate(dirFile, files);
			}
			else {
				if (settings.mediaExtensions.contains(util.extension(dirFile))) {
					files.add(dirFile);
				}
			}
		}
	}

}
