package appFiles;

public class Word {

	/**
	 * name = word's spelling
	 * classification = category of the word (verb, adjective, etc)
	 * meaning = attached meaning. */
	private Object word, partOfSpeech, definition;
	
	//Default constructor
	public Word() {
		this.word = null;
		this.partOfSpeech = null;
		this.definition = null;
	}

	//Passing arguments for constructor
	public Word(String name, String classification, String definition) {
		this.word = name;
		this.partOfSpeech = classification;
		this.definition = definition;
	}
	
	//Getters
	public String getName() {
		return word.toString();
	}

	public String getPartOfSpeech() {
		return partOfSpeech.toString();
	}

	public String getDefinition() {
		return definition.toString();
	}

	//Setters
	public void setName(String name) {
		this.word = name;
	}

	public void setPartOfSpeech(String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	//toString
	@Override
	public String toString() {
		return this.word + "(" + this.partOfSpeech + "): " + this.definition;
	}
	
	//equals
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Word)) {return false;}
		if(obj == this) {return true;}
		Word wordObj = (Word) obj;
		return this.word.equals(wordObj.word) && this.partOfSpeech.equals(wordObj.partOfSpeech) && this.definition.equals(wordObj.definition);
	}
}
