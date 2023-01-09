package br.ufjf.interfaces;

import br.ufjf.exceptions.BrokenRuleException;

public interface Rule {

    public void runRule(String password) throws BrokenRuleException;

}
