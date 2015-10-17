package ar.com.pagofacil.batch.model;

public abstract class AbstractTextLine implements Identificable {

	private String line;
	
	public void setLine(String line) {
		this.line = line;
	}
	
	public String getLine() {
		return this.line;
	}

}
