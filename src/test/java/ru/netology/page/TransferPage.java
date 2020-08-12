package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement sumField = $("[data-test-id='amount'] input");
    private SelenideElement fromField = $("[data-test-id='from'] input");
    private SelenideElement cardField = $("[data-test-id='to'] input");
    private SelenideElement sendButton = $("[data-test-id='action-transfer']");
    private SelenideElement cancelButton = $("[data-test-id='action-cancel']");

    public DashboardPage transfer(int id,DataHelper.Card card, int amount){
        DashboardPage dashboard = new DashboardPage();
        dashboard.getRefillButton().get(id).click();
        sumField.setValue(String.valueOf(amount));
        fromField.setValue(card.getNumber());
        sendButton.click();
        return new DashboardPage();
    }
    public DashboardPage transferCancell(int id,DataHelper.Card card, int amount){
        DashboardPage dashboard = new DashboardPage();
        dashboard.getRefillButton().get(id).click();
        sumField.setValue(String.valueOf(amount));
        fromField.setValue(card.getNumber());
        cancelButton.click();
        return new DashboardPage();
    }

}