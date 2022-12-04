package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import lib.DBUtils;
import lib.Status;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFormPaymentCredit {
    private static String appURL = System.getProperty("app.url");
    private static String appPORT = System.getProperty("app.port");


    @BeforeEach
    void setUp() {
        open(appURL + ":" + appPORT);
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @SneakyThrows
    @AfterEach
    void clearAll() {
        DBUtils.clearAllData();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @SneakyThrows
    @Test
    @DisplayName("Позитивный, ввод валидных значений в поля «Номер карты», \"expiry date\", «Имя держателя карты» и \"CVV\"")
    void shouldPayByApprovedCard() {
        var cardInfo = DataHelper.Card.generateInfoWithApprovedNumber();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageSuccess();
        assertEquals(Status.APPROVED, DBUtils.checkCreditStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Позитивный, ввод валидных значений в поля «Номер карты», \"expiry date\", «Имя держателя карты» и \"CVV\"")
    void shouldNoPayByDeclinedCard() {
        var cardInfo = DataHelper.Card.generateInfoWithDeclinedNumber();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageError();
        assertEquals(Status.DECLINED, DBUtils.checkCreditStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод граничных значений в поле «Номер карты» и валидных в поля \"expiry date\", «Имя держателя карты» и \"CVV\"")
    void shouldNoPayLongCardNumber() {
        var cardInfo = DataHelper.Card.generateInfoWithLongNumber();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageWrongFormat();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод граничных значений в поле «Номер карты» и валидных в поля \"expiry date\", «Имя держателя карты» и \"CVV\"")
    void shouldNoPayShortCardNumber() {
        var cardInfo = DataHelper.Card.generateInfoWithShortNumber();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageSuccess();
        assertEquals(Status.DECLINED, DBUtils.checkCreditStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод граничных значений в поле «Номер карты» и валидных в поля \"expiry date\", «Имя держателя карты» и \"CVV\"")
    void shouldNoPayEmptyCardNumberField() {
        var cardInfo = DataHelper.Card.generateInfoWithEmptyNumber();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageRequiredField();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле «Номер карты» и валидных в поля \"expiry date\", «Имя держателя карты» и \"CVV\"")
    void shouldNoPayCyrillicCardNumber() {
        var cardInfo = DataHelper.Card.generateInfoWithCyrillicNumber();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageRequiredField();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле «Номер карты» и валидных в поля \"expiry date\", «Имя держателя карты» и \"CVV\"")
    void shouldNoPayLatinCardNumber() {
        var cardInfo = DataHelper.Card.generateInfoWithLatinNumber();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageRequiredField();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле «Номер карты» и валидных в поля \"expiry date\", «Имя держателя карты» и \"CVV\"")
    void shouldNoPaySpecialCharacterCardNumber() {
        var cardInfo = DataHelper.Card.generateInfoWithSpecialsNumber();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageRequiredField();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле «Номер карты» и валидных в поля \"expiry date\", «Имя держателя карты» и \"CVV\"")
    void shouldNoPayZeroValueCardNumber() {
        var cardInfo = DataHelper.Card.generateInfoWithZeroNumber();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageWrongFormat();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод граничных значений в поле \"expiry date\" и валидных в поля «Номер карты», «Имя держателя карты» и \"CVV\"")
    void shouldNoPayExpiredCard() {
        var cardInfo = DataHelper.Card.generateInfoWithPastDate();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageOverDate();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод граничных значений в поле \"expiry date\" и валидных в поля «Номер карты», «Имя держателя карты» и \"CVV\"")
    void shouldNoPayEmptyDateField() {
        var cardInfo = DataHelper.Card.generateInfoWithEmptyDate();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageRequiredField();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле \"expiry date\" и валидных в поля «Номер карты», «Имя держателя карты» и \"CVV\"")
    void shouldNoPayZeroValueDate() {
        var cardInfo = DataHelper.Card.generateInfoWithZeroDate();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageWrongDate();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле \"expiry date\" и валидных в поля «Номер карты», «Имя держателя карты» и \"CVV\"")
    void shouldNoPayCyrillicDate() {
        var cardInfo = DataHelper.Card.generateInfoWithCyrillicDate();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageRequiredField();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле \"expiry date\" и валидных в поля «Номер карты», «Имя держателя карты» и \"CVV\"")
    void shouldNoPayLatinDate() {
        var cardInfo = DataHelper.Card.generateInfoWithLatinDate();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageRequiredField();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле \"expiry date\" и валидных в поля «Номер карты», «Имя держателя карты» и \"CVV\"")
    void shouldNoPaySpecialCharacterDate() {
        var cardInfo = DataHelper.Card.generateInfoWithSpecialsDate();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageRequiredField();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод граничных значений в поле \"CVV\", и валидных в поля «Номер карты», «Имя держателя карты» и \"expiry date\"")
    void shouldNoPayLongCVV() {
        var cardInfo = DataHelper.Card.generateInfoWithLongCvv();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageWrongFormat();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод граничных значений в поле \"CVV\", и валидных в поля «Номер карты», «Имя держателя карты» и \"expiry date\"")
    void shouldNoPayShortCVV() {
        var cardInfo = DataHelper.Card.generateInfoWithShortCvv();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageWrongFormat();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод граничных значений в поле \"CVV\", и валидных в поля «Номер карты», «Имя держателя карты» и \"expiry date\"")
    void shouldNoPayEmptyCVVField() {
        var cardInfo = DataHelper.Card.generateInfoWithEmptyCvv();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageRequiredField();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле \"CVV\", и валидных в поля «Номер карты», «Имя держателя карты» и \"expiry date\"")
    void shouldNoPayZeroValueCVV() {
        var cardInfo = DataHelper.Card.generateInfoWithZeroCvv();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageWrongFormat();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле \"CVV\", и валидных в поля «Номер карты», «Имя держателя карты» и \"expiry date\"")
    void shouldNoPayCyrillicCVV() {
        var cardInfo = DataHelper.Card.generateInfoWithCyrillicCvv();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageRequiredField();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле \"CVV\", и валидных в поля «Номер карты», «Имя держателя карты» и \"expiry date\"")
    void shouldNoPayLatinCVV() {
        var cardInfo = DataHelper.Card.generateInfoWithLatinCvv();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageRequiredField();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле \"CVV\", и валидных в поля «Номер карты», «Имя держателя карты» и \"expiry date\"")
    void shouldNoPaySpecialCharacterCVV() {
        var cardInfo = DataHelper.Card.generateInfoWithSpecialsCvv();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageRequiredField();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод граничных значений в поле «Имя держателя карты» и валидных в поля «Номер карты», \"expiry date\" и \"CVV\"")
    void shouldNoPayLongOwner() {
        var cardInfo = DataHelper.Card.generateInfoWithLongOwner();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageWrongFormat();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод граничных значений в поле «Имя держателя карты» и валидных в поля «Номер карты», \"expiry date\" и \"CVV\"")
    void shouldNoPayEmptyOwner() {
        var cardInfo = DataHelper.Card.generateInfoEmptyOwner();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageRequiredField();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле «Имя держателя карты» и валидных в поля «Номер карты», \"expiry date\" и \"CVV\"")
    void shouldNoPaySpecialCharacterOwner() {
        var cardInfo = DataHelper.Card.generateInfoWithSpecialsOwner();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageRequiredField();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле «Имя держателя карты» и валидных в поля «Номер карты», \"expiry date\" и \"CVV\"")
    void shouldNoPayNumericOwner() {
        var cardInfo = DataHelper.Card.generateInfoNumericOwner();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageRequiredField();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле «Имя держателя карты» и валидных в поля «Номер карты», \"expiry date\" и \"CVV\"")
    void shouldNoPayHyphenAtBeginningOwner() {
        var cardInfo = DataHelper.Card.generateInfoWithHyphenAtStartOwner();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageWrongFormat();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле «Имя держателя карты» и валидных в поля «Номер карты», \"expiry date\" и \"CVV\"")
    void shouldNoPayHyphenAtEndOwner() {
        var cardInfo = DataHelper.Card.generateInfoWithHyphenAtEndOwner();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageWrongFormat();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле «Имя держателя карты» и валидных в поля «Номер карты», \"expiry date\" и \"CVV\"")
    void shouldNoPayHyphenOnlyOwner() {
        var cardInfo = DataHelper.Card.generateInfoOnlyHyphensOwner();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageWrongFormat();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле «Имя держателя карты» и валидных в поля «Номер карты», \"expiry date\" и \"CVV\"")
    void shouldNoPayCyrillicOwner() {
        var cardInfo = DataHelper.Card.generateInfoCyrillicOwner();
        new MainPage().buyOnCredit()
                .setCardNumber(cardInfo.getNumber())
                .setCardMonth(cardInfo.getMonth())
                .setCardYear(cardInfo.getYear())
                .setCardCVV(cardInfo.getCvv())
                .setCardOwner(cardInfo.getOwner())
                .pushContinueButton()
                .checkMessageWrongFormat();
        assertEquals(null, DBUtils.checkCreditEmptyStatus());
    }
}

