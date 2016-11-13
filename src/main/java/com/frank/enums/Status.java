package com.frank.enums;

public enum Status {
	
	ENABLE(1,"ÆôÓÃ"),
	DISABLE(0,"½ûÓÃ");
	
	private int key;
	
	private String name;
	
	Status(int key,String name){
		this.key=key;
		this.name=name;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    public static Status stateOf(int index) {
        for (Status s : values()) {
            if (s.getKey() == index) {
                return s;
            }
        }
        return null;
    }
}
