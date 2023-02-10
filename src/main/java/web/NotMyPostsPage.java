package web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NotMyPostsPage {

    public static final String urlNotMyPostsPage = "https://test-stand.gb.ru/?owner=notMe&sort=createdAt&order=ASC";
    public static final String urlDESC = "https://test-stand.gb.ru/?owner=notMe&sort=createdAt&order=DESC";
    public static final String urlASC = "https://test-stand.gb.ru/?owner=notMe&sort=createdAt&order=ASC";
    public static final String urlNextPage = "https://test-stand.gb.ru/?page=2&owner=notMe";
    public static final String urlPrevPage = "https://test-stand.gb.ru/?page=1&owner=notMe";
    public static By postCard = By.cssSelector(".post.svelte-127jg4t");
    public static By postTitle = By.xpath("//*[@id='app']//div/div[1]/h1");


    @FindBy(how = How.XPATH, using = "//*[@id='app']//div/div[2]/div[2]/div[1]//i[2]")
    private static SelenideElement btnOrderDESC;

    @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]//div/div[2]/div[2]/div[1]//i[1]")
    private static SelenideElement btnOrderASC;

    @FindBy(how = How.CSS, using = "a[href='/?page=2&owner=notMe']")
    private static SelenideElement btnNextPage;

    @FindBy(how = How.CSS, using = "a[href='/?page=1&owner=notMe']")
    private static SelenideElement btnPrevPage;

    @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]//div/div[3]/div[1]/a[1]")
    private static SelenideElement firstCardPost;


    public static void clickBtnNextPage() {
        btnNextPage.shouldBe(Condition.enabled).click();
    }

    public static void clickBtnPrevPage() {
        btnPrevPage.shouldBe(Condition.enabled).click();
    }

    public static void clickBtnOrderDESC() {
        btnOrderDESC.shouldBe(Condition.enabled).click();
    }

    public static void clickBtnOrderASC() {
        btnOrderASC.shouldBe(Condition.enabled).click();
    }

    public static void openPost() {
        firstCardPost.shouldBe(Condition.enabled).click();
    }

}
