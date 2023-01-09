package br.ufjf.model;

import br.ufjf.exceptions.BrokenRuleException;
import br.ufjf.interfaces.Rule;

public class WhitespaceRule implements Rule {

    @Override
    public void runRule(String password) throws BrokenRuleException {
        if(password.contains(" "))
            throw new BrokenRuleException
                    ("Senha não pode ter espaço em branco");
    }

}