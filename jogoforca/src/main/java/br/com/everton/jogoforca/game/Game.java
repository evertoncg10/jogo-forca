package br.com.everton.jogoforca.game;

import java.util.HashSet;
import java.util.Set;

import br.com.everton.jogoforca.core.Config;
import br.com.everton.jogoforca.core.Dictionary;
import br.com.everton.jogoforca.core.InvalidCharacterException;
import br.com.everton.jogoforca.core.Word;
import br.com.everton.jogoforca.ui.UI;

public class Game {

	public void start() {
		UI.print("Bem vindo ao Jogo da Forca!");

		Dictionary dictionary = Dictionary.getInstance();

		UI.print("Dicion�rio usado: " + dictionary.getName());

		Word word = dictionary.nextWord();

		UI.print("A palavra tem " + word.size() + " letras");

		Set<Character> usedChars = new HashSet<>();
		int errorCount = 0;

		int maxErrors = Integer.parseInt(Config.get("maxErrors"));
		UI.print("Voc� pode errar no m�ximo " + maxErrors + " vez(es)");

		while (true) {
			UI.print(word);
			UI.printNewLine();

			char c;
			try {
				c = UI.readChar("Digite uma letra:");

				if (usedChars.contains(c)) {
					throw new InvalidCharacterException("Esta letra j� foi utilizada");
				}

				usedChars.add(c);

				if (word.hasChar(c)) {
					UI.print("Voc� acertou uma letra!");
				} else {
					errorCount++;

					if (errorCount < maxErrors) {
						UI.print("Voc� errou! Voc� ainda tem " + (maxErrors - errorCount) + " chance(s)");
					}
				}

				UI.printNewLine();

				if (word.discovered()) {
					UI.print("PARAB�NS! Voc� acertou a palavra correta: " + word.getOriginalWord());
					UI.print("Fim do Jogo!");
					break;
				}

				if (errorCount == maxErrors) {
					UI.print("Voc� perdeu o jogo! A palavra correta era: " + word.getOriginalWord());
					UI.print("Fim do Jogo!");
					break;
				}

			} catch (InvalidCharacterException e) {
				UI.print("Erro: " + e.getMessage());
				UI.printNewLine();
			}
		}

	}
}
