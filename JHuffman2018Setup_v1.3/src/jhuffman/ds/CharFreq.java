package jhuffman.ds;

public class CharFreq {
	private Character character;
	private Integer frequency;
	
	public CharFreq(Character character, Integer frequency) {
		this.character = character;
		this.frequency = frequency;
	}
	public Character getCharacter() {
		return character;
	}
	public void setCharacter(Character character) {
		this.character = character;
	}
	public Integer getFrequency() {
		return frequency;
	}
	public void incrementFrequency() {
		frequency++;
	}
}

