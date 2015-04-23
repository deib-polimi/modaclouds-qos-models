package it.polimi.modaclouds.qos_models.util;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
	private boolean valid;
	private List<String> messages;
	
	public ValidationResult(boolean validity) {
		valid=validity;
	}
	public boolean isValid() {
		return valid;
	}
	
	public List<String> getMessages() {
		return messages;
	}
	public void addMessage(String message) {
		if (messages == null)
			messages = new ArrayList<String>();
		messages.add(message);
	}

}
