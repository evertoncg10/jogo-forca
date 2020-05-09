package br.com.everton.jogoforca.core;

import java.lang.reflect.Constructor;

public abstract class Dictionary {

	private static final String PACKAGE_URL = "br.com.everton.jogoforca.core.";
	private static Dictionary instance;

	public static Dictionary getInstance() {
		if (instance == null) {

			try {
				String dictionaryClassName = PACKAGE_URL + Config.get("dictionaryClassName");
				Class<?> classInstance = Class.forName(dictionaryClassName);
				Constructor<?> constructor = classInstance.getConstructor();
				instance = (Dictionary) constructor.newInstance();

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		return instance;
	}

	public abstract Word nextWord();

	public abstract String getName();

}
