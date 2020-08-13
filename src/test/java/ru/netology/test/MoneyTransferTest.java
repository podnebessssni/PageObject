package ru.netology.test;

import com.codeborne.selenide.Condition;
import lombok.val;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {
  DashboardPage dashboard = new DashboardPage();
  TransferPage transferMoney = new TransferPage();

  @BeforeEach
  void setUp(){
    open("http://localhost:9999");
    val loginPage = new LoginPage();
    val authInfo = DataHelper.getAuthInfo();
    val verificationPage = loginPage.validLogin(authInfo);
    val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    verificationPage.validVerify(verificationCode);
  }

  @Test
  void shouldTransferMoneyFromSecondToFirstCard() {
    int amount = 100 + (int) (Math.random() * 5000);
    val cardBalanceFirst =  dashboard.getFirstCardBalance();
    val cardBalanceSecond =  dashboard.getSecondCardBalance();
    val cardInfo = DataHelper.Card.getSecondCardInfo();
    dashboard.getFirstCardRefillButton().click();
    transferMoney.transfer(cardInfo,amount);
    val cardBalanceAfterSendFirst = DataHelper.Card.cardBalanceAfterGetMoney(cardBalanceFirst,amount);
    val cardBalanceAfterSendSecond = DataHelper.Card.cardBalanceAfterSendMoney(cardBalanceSecond,amount);
    assertEquals(cardBalanceAfterSendFirst, dashboard.getFirstCardBalance());
    assertEquals(cardBalanceAfterSendSecond, dashboard.getSecondCardBalance());
  }

  @Test
  void shouldTransferMoneyFromFirstToSecondCard() {
    int amount = 100 + (int) (Math.random() * 5000);
    val cardBalanceFirst =  dashboard.getFirstCardBalance();
    val cardBalanceSecond =  dashboard.getSecondCardBalance();
    val cardInfo = DataHelper.Card.getFirstCardInfo();
    dashboard.getSecondCardRefillButton().click();
    transferMoney.transfer(cardInfo,amount);
    val cardBalanceAfterSendFirst = DataHelper.Card.cardBalanceAfterSendMoney(cardBalanceFirst,amount);
    val cardBalanceAfterSendSecond = DataHelper.Card.cardBalanceAfterGetMoney(cardBalanceSecond,amount);
    assertEquals(cardBalanceAfterSendFirst, dashboard.getFirstCardBalance());
    assertEquals(cardBalanceAfterSendSecond, dashboard.getSecondCardBalance());
  }

  @Test
  void shouldReturnToTheDashboardPageIfClickCancelButton() {
    int amount = 100 + (int) (Math.random() * 5000);
    val cardBalanceFirst =  dashboard.getFirstCardBalance();
    val cardBalanceSecond =  dashboard.getSecondCardBalance();
    val cardInfo = DataHelper.Card.getFirstCardInfo();
    dashboard.getSecondCardRefillButton().click();
    transferMoney.transferCancell(cardInfo,amount);
    dashboard.returnToDashboard().compareTo("  Личный кабинет");

  }

  @Test
  void shouldShowErrorMessageIfWrongCardNumber() {
    int amount = 100 + (int) (Math.random() * 5000);
    val cardInfo = DataHelper.Card.getWrongCardInfo();
    dashboard.getSecondCardRefillButton().click();
    transferMoney.transfer(cardInfo,amount);
    dashboard.showAlertMessage().compareTo("Ошибка! Произошла ошибка");
  }

  @Test
  void shouldReturnAlertMessageIfSendMoreThanHave() {
    int amount = 15000;
    val cardBalanceFirst =  dashboard.getFirstCardBalance();
    val cardBalanceSecond =  dashboard.getSecondCardBalance();
    val cardInfo = DataHelper.Card.getSecondCardInfo();
    dashboard.getFirstCardRefillButton().click();
    transferMoney.transfer(cardInfo,amount);
    $(withText("У вас недостаточно средств для перевода такой суммы")).shouldBe(Condition.visible);
  }
}

