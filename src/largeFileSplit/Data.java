package largeFileSplit;

public class Data {

	private String siren;
	private String version;
	
	public Data() {
	}
	
	public Data(String siren, String version) {
		super();
		this.siren = siren;
		this.version = version;
	}

	public String getSiren() {
		return siren;
	}
	public void setSiren(String siren) {
		this.siren = siren;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	@Override
	public String toString() {
		return siren+","+version;
	}
}
