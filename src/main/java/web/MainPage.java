package web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class MainPage {

    static final String urlMainPage = "https://test-stand.gb.ru/";
    public static final String urlDESC = "https://test-stand.gb.ru/?sort=createdAt&order=DESC";
    public static final String urlASC = "https://test-stand.gb.ru/?sort=createdAt&order=ASC";
    public static final String urlNextPage = "https://test-stand.gb.ru/?page=2";
    public static final String urlPrevPage = "https://test-stand.gb.ru/?page=1";
    public static By postCardTitle = By.cssSelector("h1.svelte-tv8alb");
    public static By postCard = By.cssSelector(".post.svelte-127jg4t");

    @FindBy(how = How.XPATH, using = "//*[@id='app']//nav//li[3]/a")
    private static SelenideElement btnMenuUsername;

    @FindBy(how = How.CSS, using = "a[href='/?page=2']")
    private static SelenideElement btnNextPage;

    @FindBy(how = How.CSS, using = "#create-btn")
    private static SelenideElement btnCreateNewPost;

    @FindBy(how = How.CSS, using = ".post.svelte-127jg4t")
    public static SelenideElement cardPost;

    @FindBy(how = How.CSS, using = "h2.svelte-127jg4t")
    public static SelenideElement postTitle;

    @FindBy(how = How.CSS, using = ".description.svelte-127jg4t")
    public static SelenideElement postDescription;

    @FindBy(how = How.CSS, using = "img.svelte-127jg4t")
    public static SelenideElement postImage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]//div/div[2]/div[2]/div[2]//span")
    private static SelenideElement btnNotMyPosts;

    @FindBy(how = How.XPATH, using = "//*[@id='app']//div/div[2]/div[2]/div[1]//i[2]")
    private static SelenideElement btnOrderDESC;

    @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]//div/div[2]/div[2]/div[1]//i[1]")
    private static SelenideElement btnOrderASC;

    public static CreatePostPage clickBtnCreateNewPost() {
        btnCreateNewPost.shouldBe(enabled, Duration.ofSeconds(10)).click();
        return page(CreatePostPage.class);
    }

    public static Page2 clickBtnNextPage() {
        btnNextPage.shouldBe(enabled).click();
        return page(Page2.class);
    }

    public static NotMyPostsPage clickBtnNotMyPosts() {
        btnNotMyPosts.shouldBe(visible).click();
        return page(NotMyPostsPage.class);
    }

    public static void clickBtnOrderDESC() {
        btnOrderDESC.shouldBe(Condition.enabled).click();
    }

    public static void clickBtnOrderASC() {
        btnOrderASC.shouldBe(Condition.enabled).click();
    }
}
