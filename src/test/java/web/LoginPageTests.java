package web;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static web.LoginPage.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginPageTests {

    @BeforeAll
    static void setUp() {
        Configuration.headless = true;
        //Configuration.holdBrowserOpen = true;
    }

    @DisplayName("1. Авторизация существующего пользователя")
    @Order(1)
    @Test
    public void loginExistUser() {
        login(username, password);
        assertThat("Username should be " + username, getTextLinkUser(), is(equalTo("Hello, " + username)));
    }

    @DisplayName("2. Авторизация с невалидным логином и валидным паролем")
    @Order(2)
    @Test
    public void loginInvalidUsernameValidPassword() {
        login("test", password);
        assertThat("Should be a message about an invalid credentials", getTextMessage(),
                is(equalTo("Invalid credentials.")));
    }

    @DisplayName("3. Авторизация с валидным логином и невалидным паролем")
    @Order(3)
    @Test
    public void loginValidUsernameInvalidPassword() {
        login(username, "1234");
        assertThat("Should be a message about an invalid credentials", getTextMessage(),
                is(equalTo("Invalid credentials.")));
    }

    @DisplayName("4. Авторизация с пустыми логином и паролем")
    @Order(4)
    @Test
    public void loginEmptyUsernameEmptyPassword() {
        login("", "");
        assertThat("Should be a message about empty fields", getTextMessage(),
                is(equalTo("Поле не может быть пустым")));
    }

    @DisplayName("5. Проверка поля логин на ввод кириллицы")
    @Order(5)
    @Test
    public void loginUsernameCyrillicCharacters() {
        login("Юзер", password);
        assertThat("Should be a message about invalid characters", getTextMessage(),
                is(equalTo("Неправильный логин. Может состоять только из латинских букв и цифр, без спецсимволов")));
    }

    @DisplayName("6. Проверка поля логин на ввод спецсимволов")
    @Order(6)
    @Test
    public void loginUsernameSpecialCharacters() {
        login("!autotest", password);
        assertThat("Should be a message about invalid characters", getTextMessage(),
                is(equalTo("Неправильный логин. Может состоять только из латинских букв и цифр, без спецсимволов")));
    }

    @DisplayName("7. Проверка поля логин на ввод 2 символов")
    @Order(7)
    @Test
    public void loginUsernameTwoCharacters() {
        login("us", password);
        assertThat("Should be a message about an invalid login length", getTextMessage(),
                is(equalTo("Неправильный логин. Может быть не менее 3 и не более 20 символов")));
    }

    @DisplayName("8. Проверка поля логин на ввод 3 символов")
    @Order(8)
    @Test
    public void loginUsernameThreeCharacters() {
        login("abc", password);
        assertThat("Should be a message about an invalid credentials", getTextMessage(),
                is(equalTo("Invalid credentials.")));
    }

    @DisplayName("9. Проверка поля логин на ввод 20 символов")
    @Order(9)
    @Test
    public void loginUsername20Characters() {
        login("qwertyuiopasdfghjklz", password);
        assertThat("Should be a message about an invalid credentials", getTextMessage(),
                is(equalTo("Invalid credentials.")));
    }


    @DisplayName("10. Проверка поля логин на ввод 21 символа")
    @Order(10)
    @Test
    public void loginUsername21Characters() {
        login("qwertyuiopasdfghjklzx", password);
        assertThat("Should be a message about an invalid login length", getTextMessage(),
                is(equalTo("Неправильный логин. Может быть не менее 3 и не более 20 символов")));
    }

}
