import br.ufjf.model.CharacterRule;
import br.ufjf.model.LengthRule;
import br.ufjf.model.PasswordValidator;
import br.ufjf.model.WhitespaceRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.passay.EnglishCharacterData;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PasswordValidatorTest {

    private PasswordValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1),
                new WhitespaceRule()));
    }

    @Test
    public void senhavalidaTest(){
        String senha = "123456*Ae";
        assertEquals("Senha válida.",validator.validate(senha));
    }

    @Test
    public void caracteresInsuficientesTest() {
        String senha = "156*Ae";
        try {
            validator.validate(senha);
            fail();
        } catch (RuntimeException e) {
            assertEquals("A senha não satisfaz a política de senha na seguinte regra: Senha precisa ter entre 8 e 30 caracteres.",e.getMessage());
        }
    }

    @Test
    public void naoTemUpperCaseTest() {
        String senha = "156*dfeifjeifjee";
        try {
            validator.validate(senha);
            fail();
        } catch (RuntimeException e) {
            assertEquals("A senha não satisfaz a política de senha na seguinte regra: Senha não passou na política de tipos de caracteres",e.getMessage());
        }
    }

    @Test
    public void naoTemLowerCaseTest() {
        String senha = "156*RGORGNPNGRU";
        try {
            validator.validate(senha);
            fail();
        } catch (RuntimeException e) {
            assertEquals("A senha não satisfaz a política de senha na seguinte regra: Senha não passou na política de tipos de caracteres",e.getMessage());
        }
    }

    @Test
    public void naoTemCaracterEspecialTest() {
        String senha = "156RGORGNPNGRU";
        try {
            validator.validate(senha);
            fail();
        } catch (RuntimeException e) {
            assertEquals("A senha não satisfaz a política de senha na seguinte regra: Senha não passou na política de tipos de caracteres",e.getMessage());
        }
    }

    @Test
    public void naoTemNumeroTest() {
        String senha = "*RGORGNPNGRU";
        try {
            validator.validate(senha);
            fail();
        } catch (RuntimeException e) {
            assertEquals("A senha não satisfaz a política de senha na seguinte regra: Senha não passou na política de tipos de caracteres",e.getMessage());
        }
    }

    @Test
    public void temEspacoEmBrancoTest() {
        String senha = "123456*Ae ";
        try {
            validator.validate(senha);
            fail();
        } catch (RuntimeException e) {
            assertEquals("A senha não satisfaz a política de senha na seguinte regra: Senha não pode ter espaço em branco",e.getMessage());
        }
    }

}
