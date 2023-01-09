package br.ufjf.model;

import br.ufjf.interfaces.Rule;

import java.util.List;

public class PasswordValidator {

    List<Rule> rules;

    public PasswordValidator(List<Rule> rules) {
        this.rules = rules;
    }

    public String validate(String senha) {
        try {
            for (Rule rule : rules) {
                rule.runRule(senha);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return "Senha válida.";
    }
}