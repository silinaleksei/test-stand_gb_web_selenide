package web;

import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class CreatePostPage {

    static final String urlCreatePostPage = "https://test-stand.gb.ru/posts/create";

    @FindBy(how = How.XPATH, using = "//*[@id='create-item']/div/div/div[1]/div//input")
    private static SelenideElement inputTitle;

    @FindBy(how = How.XPATH, using = "//*[@id=\"update-post-item\"]/div/div/div[1]/div/label/input")
    private static SelenideElement inputUpdateTitle;

    @FindBy(how = How.XPATH, using = "//*[@id='create-item']//div/div[2]/div//span/textarea")
    private static SelenideElement inputDescription;

    @FindBy(how = How.XPATH, using = "//*[@id='create-item']//div/div[6]/div//label/input")
    private static SelenideElement inputImage;

    @FindBy(how = How.XPATH, using = "//*[@id='create-item']//div/div[7]//button")
    private static SelenideElement btnSave;

    @FindBy(how = How.XPATH, using = "//*[@id=\"update-post-item\"]//div/div[7]/div//span")
    private static SelenideElement btnSaveUpdatePost;

    @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/main/div/div[1]/div/div[1]/div[1]/button[1]")
    private static SelenideElement btnEditPost;

    @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/main//div[1]/div/div[1]/div[1]/button[2]")
    private static SelenideElement btnDeletePost;

    @FindBy(how = How.XPATH, using = "//*[@id=\"app\"]//nav/a/span")
    private static SelenideElement btnHomePage;


    public CreatePostPage inputTitle(String title) {
        inputTitle.shouldBe(editable).setValue(title);
        return this;
    }

    @SneakyThrows
    public CreatePostPage inputUpdateTitle(String title) {
        inputUpdateTitle.shouldBe(editable).setValue(title);
        return this;
    }

    public CreatePostPage inputDescription(String description) {
        inputDescription.shouldBe(editable).setValue(description);
        return this;
    }

    public CreatePostPage inputImage(String image) {
        inputImage.shouldBe(editable).setValue(image);
        return this;
    }

    public CreatePostPage clickBtnSave() {
        btnSave.shouldBe(enabled).click();
        return this;
    }

    public CreatePostPage clickBtnSaveUpdatePost() {
        btnSaveUpdatePost.shouldBe(enabled).click();
        return this;
    }

    public CreatePostPage clickBtnEditPost() {
        btnEditPost.shouldBe(exist).click();
        return this;
    }

    public CreatePostPage clickDeletePost() {
        btnDeletePost.shouldBe(exist).click();
        return this;
    }
    public MainPage clickBtnHomePage() {
        btnHomePage.shouldBe(visible, Duration.ofSeconds(10)).click();
        return page(MainPage.class);
    }

    @SneakyThrows
    public CreatePostPage sleep() {
        Thread.sleep(5000);
        return this;
    }
}
