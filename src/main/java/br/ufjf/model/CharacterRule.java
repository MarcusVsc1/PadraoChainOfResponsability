package br.ufjf.model;

import br.ufjf.exceptions.BrokenRuleException;
import br.ufjf.interfaces.Rule;
import org.passay.EnglishCharacterData;

public class CharacterRule implements Rule {

    Integer quantidade;
    EnglishCharacterData caracter;

    public CharacterRule(EnglishCharacterData characterData, Integer quantidade) {
        super();
        this.caracter = characterData;
        this.quantidade = quantidade;
    }

    @Override
    public void runRule(String password) throws BrokenRuleException {
        String ruleChars = caracter.getCharacters();
        if(!isInRule(ruleChars, password))
            throw new BrokenRuleException
                    ("Senha não passou na política de tipos de caracteres");
    }

    private boolean isInRule(String ruleChars, String password) {
        int charsNecessarios = 0;
        for (char c : password.toCharArray()) {
            if(ruleChars.contains(String.valueOf(c)))
                charsNecessarios++;
        }
        return charsNecessarios >= quantidade;
    }


}