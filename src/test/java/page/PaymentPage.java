package page;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    List<SelenideElement> input = $$(".input__control");
    SelenideElement cardNumber = input.get(0);
    SelenideElement month = input.get(1);
    SelenideElement year = input.get(2);
    SelenideElement cardOwner = input.get(3);
    SelenideElement cvcOrCvvNumber = input.get(4);

//    public PaymentPage buyForOwnMoney() {
//        $$(".button__content").find(exactText("Купить")).click();
//        $$(".heading_theme_alfa-on-white").find(exactText("Оплата по карте")).shouldBe(visible);
//    }

    public PaymentPage checkMessageSuccess() {
        $$(".notification__title").find(exactText("Успешно")).waitUntil(visible, 15000);
        return this;
    }

    public PaymentPage checkMessageError() {
        $$(".notification__title").find(exactText("Ошибка")).waitUntil(visible, 15000);
        return this;
    }

    public PaymentPage checkMessageWrongFormat() {
        $$(".input__sub").find(exactText("Неверный формат")).shouldBe(visible);
        return this;
    }

    public PaymentPage checkMessageWrongDate() {
        $$(".input__sub").find(exactText("Неверно указан срок действия карты")).shouldBe(visible);
        return this;
    }

    public PaymentPage checkMessageOverDate() {
        $$(".input__sub").find(exactText("Истёк срок действия карты")).shouldBe(visible);
        return this;
    }

    public PaymentPage checkMessageRequiredField() {
        $$(".input__sub").find(exactText("Поле обязательно для заполнения")).shouldBe(visible);
        return this;
    }

    public PaymentPage setCardNumber(String cNumber) {
        cardNumber.setValue(cNumber);
        return this;
    }

    public PaymentPage setCardMonth(String cMonth) {
        month.setValue(cMonth);
        return this;
    }

    public PaymentPage setCardYear(String cYear) {
        year.setValue(cYear);
        return this;
    }

    public PaymentPage setCardOwner(String cOwner) {
        cardOwner.setValue(cOwner);
        return this;
    }

    public PaymentPage setCardCVV(String cCvv) {
        cvcOrCvvNumber.setValue(cCvv);
        return this;
    }

    public PaymentPage pushContinueButton() {
        $$(".button__content").find(exactText("Продолжить")).click();
        return this;
    }
}