package farraplayer.players;

/**
 * @author Ivan Zaera
 */
public enum Output {
	HDMI("hdmi"), ANALOG("local");

	private final String value;

	private Output(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
