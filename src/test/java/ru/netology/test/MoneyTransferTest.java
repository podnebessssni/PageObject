package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {
//    @Test
//    void shouldTransferMoneyBetweenOwnCardsV1() {
//      open("http://localhost:9999");
//      val loginPage = new LoginPageV1();
////    val loginPage = open("http://localhost:9999", LoginPageV1.class);
//      val authInfo = DataHelper.getAuthInfo();
//      val verificationPage = loginPage.validLogin(authInfo);
//      val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
//      verificationPage.validVerify(verificationCode);
//    }

  @Test
  void shouldTransferMoneyBetweenOwnCardsV2() {
    open("http://localhost:9999");
    val loginPage = new LoginPage();
//    val loginPage = open("http://localhost:9999", LoginPageV2.class);
    val authInfo = DataHelper.getAuthInfo();
    val verificationPage = loginPage.validLogin(authInfo);
    val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    verificationPage.validVerify(verificationCode);
  }

//  @Test
//  void shouldTransferMoneyBetweenOwnCardsV3() {
//    val loginPage = open("http://localhost:9999", LoginPageV3.class);
//    val authInfo = DataHelper.getAuthInfo();
//    val verificationPage = loginPage.validLogin(authInfo);
//    val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
//    verificationPage.validVerify(verificationCode);
//  }
}

