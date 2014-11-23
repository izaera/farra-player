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

package farraplayer.players;

import farraplayer.Player;

import java.io.File;
import java.io.IOException;

/**
 * @author Ivan Zaera
 */
public class OmxplayerPlayer extends Player {

	private static Runtime runtime = Runtime.getRuntime();

	private int volume = 0;
	private Output output = Output.ANALOG;
	private Process process;
	private File lastFile;
	private Runnable lastOnEndCallback;

	public void play(File file, final Runnable onEndCallback) {
		stop();

		try {
			process = runtime.exec(new String[] {"omxplayer", "-o", output.getValue(), file.getAbsolutePath()});

			this.lastFile = file;
			this.lastOnEndCallback = onEndCallback;

			new Thread() {
				@Override
				public void run() {
					try {
						process.waitFor();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					onEndCallback.run();
				}
			}.start();

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (volume < 0) {
			for (int i = 0; i < -volume; i++) {
				send("-");
			}
		} else {
			for (int i = 0; i < volume; i++) {
				send("+");
			}
		}
	}

	public void stop() {
		if (isProcessRunning()) {
			process.destroy();
		}
	}

	public void pause() {
		send("p");
	}

	public void voldown() {
		if (volume > -10) {
			volume--;
			send("-");
		}
	}

	public void volup() {
		if (volume < 10) {
			volume++;
			send("+");
		}
	}

	public void output(Output output) {
		this.output = output;
		stop();
		play(lastFile, lastOnEndCallback);
	}

	private boolean isProcessRunning() {
		if (process == null) {
			return false;
		}

		try {
			process.exitValue();
			return false;
		}
		catch(IllegalThreadStateException e) {
			return true;
		}
	}

	private void send(String keys) {
		if (isProcessRunning()) {
			try {
				process.getOutputStream().write("p".getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
