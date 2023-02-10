package web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.openqa.selenium.By.xpath;
import static web.LoginPage.login;
import static web.MainPage.*;
import static web.Page2.clickBtnPrevPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MyPostsTests {

    @BeforeAll
    static void setUp() {
        //Configuration.holdBrowserOpen = true;
        Configuration.headless = true;
    }

    @DisplayName("Авторизация")
    @BeforeEach
    void authorization() {
        login();
    }

    @DisplayName("1. Создание поста с заголовком, описанием и картинкой")
    @Order(1)
    @Test
    void createNewPost() {
        clickBtnCreateNewPost()
                .inputTitle("My Post")
                .inputDescription("This is new post")
                .inputImage("C:\\java\\test-stand_gb_web_selenide\\src\\main\\resources\\Cake.jpg")
                .clickBtnSave();
        assertThat("Title post should be 'My Post'", $(postCardTitle).getText(), is(equalTo("My Post")));
        $(xpath("//h1[.='My Post']")).shouldBe(visible);
    }

    @DisplayName("2. Проверка наличия у поста заголовка, описания и картинки")
    @Order(2)
    @Test
    void checkPostTitleDescriptionImage() {
        assertAll(
                () -> $(postTitle).shouldHave(text("My Post")),
                () -> $(postDescription).shouldBe(text("This is new post")),
                () -> $(postImage).shouldBe(visible)
        );
    }

    @DisplayName("3. Создание и удаление поста")
    @Order(3)
    @Test
    void createAndDeletePost() {
        clickBtnCreateNewPost()
                .inputTitle("Post for delete")
                .inputDescription("This is new post")
                .inputImage("C:\\java\\test-stand_gb_web_selenide\\src\\main\\resources\\Cake.jpg")
                .clickBtnSave()
                .clickDeletePost();
        $(xpath("//h1[.='Post for delete']")).shouldBe(not(exist));
    }

    @SneakyThrows
    @DisplayName("4. Создание, редактирование и удаление поста")
    @Order(4)
    @Test
    void createEditAndDeletePost() {
        clickBtnCreateNewPost()
                .inputTitle("Post for edit")
                .inputDescription("This is new post")
                .inputImage("C:\\java\\test-stand_gb_web_selenide\\src\\main\\resources\\Cake.jpg")
                .clickBtnSave()
                .clickBtnEditPost()
                .inputUpdateTitle("Post edited")
                .clickBtnSaveUpdatePost()
                .clickDeletePost();
        $(xpath("//h1[.='Post edited']")).shouldBe(not(exist));
    }

    @SneakyThrows
    @DisplayName("5. Количество постов на одной странице")
    @Order(5)
    @Test
    void countPostCards() {
        $(cardPost).shouldHave(exist);
        ElementsCollection postCards = $$(postCard);
        if (postCards.size() < 4) {
            for (int i = 0; i < 5; i++) {
                clickBtnCreateNewPost()
                        .inputTitle("My Post")
                        .inputDescription("This is new post")
                        .inputImage("C:\\java\\test-stand_gb_web_selenide\\src\\main\\resources\\Cake.jpg")
                        .clickBtnSave()
                        .sleep()
                        .clickBtnHomePage();
            }
        }
        $$(postCard).shouldHave(size(4), Duration.ofSeconds(10));
    }

    @DisplayName("6. Проверка перехода на следующую страницу")
    @Order(6)
    @Test
    void goToTheNextPage() {
        clickBtnNextPage();
        webdriver().shouldHave(url(urlNextPage));
    }

    @DisplayName("7. Проверка перехода на следующую страницу и возврат на предыдущую")
    @Order(7)
    @Test
    void goToThePrevPage() {
        clickBtnNextPage();
        clickBtnPrevPage();
        webdriver().shouldHave(url(urlPrevPage));
    }

    @DisplayName("8. Проверка сортировки постов от новых к старым")
    @Order(8)
    @Test
    void sortingPostsByDateDESC() {
        clickBtnOrderDESC();
        webdriver().shouldHave(url(urlDESC));
    }

    @DisplayName("9. Проверка сортировки постов от старых к новым")
    @Order(9)
    @Test
    void sortingPostsByDateASC() {
        clickBtnOrderDESC();
        clickBtnOrderASC();
        webdriver().shouldHave(url(urlASC));
    }

}
