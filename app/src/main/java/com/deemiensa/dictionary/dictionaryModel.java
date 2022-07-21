package com.deemiensa.dictionary;

public class dictionaryModel {

    private String word,type,definition;
    private int Id;

    public dictionaryModel(int id, String word, String type, String definition) {
        this.word = word;
        this.definition = definition;
        this.type = type;
        this.Id = id;
    }

    public int getId(){
        return Id;
    }

    public String getWord() {
        return word;
    }

    public String getType() {
        return type;
    }

    public String getDefinition() {
        return definition;
    }
}
