package web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static web.LoginPage.login;
import static web.MainPage.clickBtnNotMyPosts;
import static web.NotMyPostsPage.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NotMyPostsTest {

    @BeforeAll
    static void setUp() {
        Configuration.headless = true;
        //Configuration.holdBrowserOpen = true;
    }

    @DisplayName("Авторизация и переключение на Ленту чужих постов")
    @BeforeEach
    void authorization() {
        login();
        clickBtnNotMyPosts();
    }

    @DisplayName("1. Проверка открытия Ленты чужих постов")
    @Order(1)
    @Test
    void openFeedNotMyPosts() {
        webdriver().shouldHave(url(urlNotMyPostsPage));
    }

    @DisplayName("2. Проверка количества постов на одной странице")
    @Order(2)
    @Test
    void countPostCards() {
        $$(postCard).shouldHave(size(4));
    }

    @DisplayName("3. Проверка перехода на следующую страницу")
    @Order(3)
    @Test
    void goToTheNextPage() {
        clickBtnNextPage();
        webdriver().shouldHave(url(urlNextPage));
    }

    @DisplayName("4. Проверка перехода на следующую страницу и возврат на предыдущую")
    @Order(4)
    @Test
    void goToThePrevPage() {
        clickBtnNextPage();
        clickBtnPrevPage();
        webdriver().shouldHave(url(urlPrevPage));
    }

    @DisplayName("5. Проверка сортировки постов от новых к старым")
    @Order(5)
    @Test
    void sortingPostsByDateDESC() {
        clickBtnOrderDESC();
        webdriver().shouldHave(url(urlDESC));
    }

    @DisplayName("6. Проверка сортировки постов от старых к новым")
    @Order(6)
    @Test
    void sortingPostsByDateASC() {
        clickBtnOrderDESC();
        clickBtnOrderASC();
        webdriver().shouldHave(url(urlASC));
    }

    @DisplayName("7. Проcмотр поста")
    @Order(7)
    @Test
    void viewingPost() {
        openPost();
        $(postTitle).shouldBe(Condition.visible);
    }
}
