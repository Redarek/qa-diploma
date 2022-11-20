package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lib.DBUtils;
import lib.FormPage;
import lib.Status;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

public class TestFormPaymentCredit {
    private FormPage formPage;

    @BeforeEach
    void setUpPage() {
        formPage = new FormPage();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void clearAll() throws SQLException{
        DBUtils.clearAllData();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Кредит, позитивный, ввод валидных значений в поля «Номер карты», \"expiry date\", «Имя держателя карты» и \"CVV\"")
    void shouldPayByApprovedCard() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardCVV("123");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.pushСontinueButton();
        formPage.checkMessageSuccess();
        DBUtils.checkCreditStatus(Status.APPROVED);
    }

    @Test
    @DisplayName("Позитивный, ввод валидных значений в поля «Номер карты», \"expiry date\", «Имя держателя карты» и \"CVV\"")
    void shouldNoPayByDeclinedCard() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444442");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardCVV("123");
        formPage.setCardOwner("Radmir Ibragimov-Radmir");
        formPage.pushСontinueButton();
        formPage.checkMessageError();
        DBUtils.checkCreditStatus(Status.DECLINED);
    }

    @Test
    @DisplayName("Негативный, ввод граничных значений в поле «Номер карты» и валидных в поля \"expiry date\", «Имя держателя карты» и \"CVV\"")
    void shouldNoPayLongCardNumber() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("44444444444444411111");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardCVV("123");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.pushСontinueButton();
        formPage.checkMessageWrongFormat();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод граничных значений в поле «Номер карты» и валидных в поля \"expiry date\", «Имя держателя карты» и \"CVV\"")
    void shouldNoPayShortCardNumber() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444 4444 4444 444");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.setCardCVV("123");
        formPage.pushСontinueButton();
        formPage.checkMessageSuccess();
        DBUtils.checkCreditStatus(Status.DECLINED);
    }

    @Test
    @DisplayName("Негативный, ввод граничных значений в поле «Номер карты» и валидных в поля \"expiry date\", «Имя держателя карты» и \"CVV\"")
    void shouldNoPayEmptyCardNumberField() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.setCardCVV("123");
        formPage.pushСontinueButton();
        formPage.checkMessageRequiredField();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле «Номер карты» и валидных в поля \"expiry date\", «Имя держателя карты» и \"CVV\"")
    void shouldNoPayCyrillicCardNumber() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("Абв");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.setCardCVV("123");
        formPage.pushСontinueButton();
        formPage.checkMessageRequiredField();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле «Номер карты» и валидных в поля \"expiry date\", «Имя держателя карты» и \"CVV\"")
    void shouldNoPayLatinCardNumber() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("Abc");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.setCardCVV("123");
        formPage.pushСontinueButton();
        formPage.checkMessageRequiredField();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле «Номер карты» и валидных в поля \"expiry date\", «Имя держателя карты» и \"CVV\"")
    void shouldNoPaySpecialCharacterCardNumber() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("#$%&+");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.setCardCVV("123");
        formPage.pushСontinueButton();
        formPage.checkMessageRequiredField();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле «Номер карты» и валидных в поля \"expiry date\", «Имя держателя карты» и \"CVV\"")
    void shouldNoPayZeroValueCardNumber() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("0000 0000 0000 0000");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.setCardCVV("123");
        formPage.pushСontinueButton();
        formPage.checkMessageWrongFormat();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод граничных значений в поле \"expiry date\" и валидных в поля «Номер карты», «Имя держателя карты» и \"CVV\"")
    void shouldNoPayExpiredCard() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("11");
        formPage.setCardYear("21");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.setCardCVV("123");
        formPage.pushСontinueButton();
        formPage.checkMessageOverDate();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод граничных значений в поле \"expiry date\" и валидных в поля «Номер карты», «Имя держателя карты» и \"CVV\"")
    void shouldNoPayEmptyDateField() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("");
        formPage.setCardYear("");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.setCardCVV("123");
        formPage.pushСontinueButton();
        formPage.checkMessageRequiredField();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле \"expiry date\" и валидных в поля «Номер карты», «Имя держателя карты» и \"CVV\"")
    void shouldNoPayZeroValueDate() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("00");
        formPage.setCardYear("00");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.setCardCVV("123");
        formPage.pushСontinueButton();
        formPage.checkMessageWrongDate();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле \"expiry date\" и валидных в поля «Номер карты», «Имя держателя карты» и \"CVV\"")
    void shouldNoPayCyrillicDate() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("Абв");
        formPage.setCardYear("Абв");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.setCardCVV("123");
        formPage.pushСontinueButton();
        formPage.checkMessageRequiredField();
        DBUtils.checkCreditEmptyStatus();
    }


    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле \"expiry date\" и валидных в поля «Номер карты», «Имя держателя карты» и \"CVV\"")
    void shouldNoPayLatinDate()  throws SQLException{
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("Abc");
        formPage.setCardYear("Abc");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.setCardCVV("123");
        formPage.pushСontinueButton();
        formPage.checkMessageRequiredField();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле \"expiry date\" и валидных в поля «Номер карты», «Имя держателя карты» и \"CVV\"")
    void shouldNoPaySpecialCharacterDate() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("#$%&+");
        formPage.setCardYear("#$%&+");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.setCardCVV("123");
        formPage.pushСontinueButton();
        formPage.checkMessageRequiredField();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод граничных значений в поле \"CVV\", и валидных в поля «Номер карты», «Имя держателя карты» и \"expiry date\"")
    void shouldNoPayLongCVV() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.setCardCVV("12345");
        formPage.pushСontinueButton();
        formPage.checkMessageWrongFormat();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод граничных значений в поле \"CVV\", и валидных в поля «Номер карты», «Имя держателя карты» и \"expiry date\"")
    void shouldNoPayShortCVV() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.setCardCVV("12");
        formPage.pushСontinueButton();
        formPage.checkMessageWrongFormat();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод граничных значений в поле \"CVV\", и валидных в поля «Номер карты», «Имя держателя карты» и \"expiry date\"")
    void shouldNoPayEmptyCVVField() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.setCardCVV("");
        formPage.pushСontinueButton();
        formPage.checkMessageRequiredField();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле \"CVV\", и валидных в поля «Номер карты», «Имя держателя карты» и \"expiry date\"")
    void shouldNoPayZeroValueCVV() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.setCardCVV("000");
        formPage.pushСontinueButton();
        formPage.checkMessageWrongFormat();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле \"CVV\", и валидных в поля «Номер карты», «Имя держателя карты» и \"expiry date\"")
    void shouldNoPayCyrillicCVV() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.setCardCVV("Абв");
        formPage.pushСontinueButton();
        formPage.checkMessageRequiredField();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле \"CVV\", и валидных в поля «Номер карты», «Имя держателя карты» и \"expiry date\"")
    void shouldNoPayLatinCVV() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.setCardCVV("Abc");
        formPage.pushСontinueButton();
        formPage.checkMessageRequiredField();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле \"CVV\", и валидных в поля «Номер карты», «Имя держателя карты» и \"expiry date\"")
    void shouldNoPaySpecialCharacterCVV() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("RADMIR IBRAGIMOV-RADMIR");
        formPage.setCardCVV("#$%&+");
        formPage.pushСontinueButton();
        formPage.checkMessageRequiredField();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод граничных значений в поле «Имя держателя карты» и валидных в поля «Номер карты», \"expiry date\" и \"CVV\"")
    void shouldNoPayLongOwner() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("абвгдАбвгдАбвгдАбвгдабвгдАбвгдАбвгдАбвгдабвгдАбвгдАбвгдАбвгдабвгдАбвгдАбвгдАбвгдабвгдАбвгдАбвгдАбвгдабвгдАбвгдАбвгдАбвгд");
        formPage.setCardCVV("123");
        formPage.pushСontinueButton();
        formPage.checkMessageWrongFormat();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод граничных значений в поле «Имя держателя карты» и валидных в поля «Номер карты», \"expiry date\" и \"CVV\"")
    void shouldNoPayEmptyOwner() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("");
        formPage.setCardCVV("123");
        formPage.pushСontinueButton();
        formPage.checkMessageRequiredField();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле «Имя держателя карты» и валидных в поля «Номер карты», \"expiry date\" и \"CVV\"")
    void shouldNoPaySpecialCharacterOwner() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("#$%&+");
        formPage.setCardCVV("123");
        formPage.pushСontinueButton();
        formPage.checkMessageRequiredField();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле «Имя держателя карты» и валидных в поля «Номер карты», \"expiry date\" и \"CVV\"")
    void shouldNoPayNumericOwner() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("123456789");
        formPage.setCardCVV("123");
        formPage.pushСontinueButton();
        formPage.checkMessageRequiredField();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле «Имя держателя карты» и валидных в поля «Номер карты», \"expiry date\" и \"CVV\"")
    void shouldNoPayHyphenAtBeginningOwner() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("-RADMIR IBRAGIMOV-RADMIR");
        formPage.setCardCVV("123");
        formPage.pushСontinueButton();
        formPage.checkMessageWrongFormat();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле «Имя держателя карты» и валидных в поля «Номер карты», \"expiry date\" и \"CVV\"")
    void shouldNoPayHyphenAtEndOwner() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("RADMIR- IBRAGIMOV-RADMIR-");
        formPage.setCardCVV("123");
        formPage.pushСontinueButton();
        formPage.checkMessageWrongFormat();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле «Имя держателя карты» и валидных в поля «Номер карты», \"expiry date\" и \"CVV\"")
    void shouldNoPayHyphenOnlyOwner() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("---");
        formPage.setCardCVV("123");
        formPage.pushСontinueButton();
        formPage.checkMessageWrongFormat();
        DBUtils.checkCreditEmptyStatus();
    }

    @Test
    @DisplayName("Негативный, ввод невалидных значений в поле «Имя держателя карты» и валидных в поля «Номер карты», \"expiry date\" и \"CVV\"")
    void shouldNoPayCyrillicOwner() throws SQLException {
        formPage.buyOnCredit();
        formPage.setCardNumber("4444444444444441");
        formPage.setCardMonth("11");
        formPage.setCardYear("23");
        formPage.setCardOwner("Радмир Ибрагимов-Радмир");
        formPage.setCardCVV("123");
        formPage.pushСontinueButton();
        formPage.checkMessageWrongFormat();
        DBUtils.checkCreditEmptyStatus();
    }
}