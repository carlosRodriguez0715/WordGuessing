package appFiles;

public class Word {

	/**
	 * name = word's spelling
	 * classification = category of the word (verb, adjective, etc)
	 * meaning = attached meaning. */
	private String name, classification, meaning;
	
	//Default constructor
	public Word() {
		this.name = null;
		this.classification = null;
		this.meaning = null;
	}

	//Passing arguments for constructor
	public Word(String name, String classification, String meaning) {
		this.name = name;
		this.classification = classification;
		this.meaning = meaning;
	}
	
	//Getters
	public String getName() {
		return name;
	}

	public String getClassification() {
		return classification;
	}

	public String getMeaning() {
		return meaning;
	}

	//Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	
	//toString
	@Override
	public String toString() {
		return this.name + "(" + this.classification + "): " + this.meaning;
	}
	
	//equals
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Word)) {return false;}
		if(obj == this) {return true;}
		Word wordObj = (Word) obj;
		return this.name.equals(wordObj.name) && this.classification.equals(wordObj.classification) && this.meaning.equals(wordObj.meaning);
	}
}
