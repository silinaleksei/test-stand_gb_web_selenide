package web;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class Page2 {
    static final String urlPage2 = "https://test-stand.gb.ru/?page=2";

    @FindBy(how = How.CSS, using = "a[href='/?page=1']")
    private static SelenideElement btnPrevPage;

    public static MainPage clickBtnPrevPage() {
        btnPrevPage.click();
        return page(MainPage.class);
    }

}
