package tests;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class WikiTests extends TestBase {

    @Test
    @Tag("search")
    @Owner("malyginms")
    @DisplayName("Check a search list results")
    @Description("Search in Wikipedia. Check a search list results")
    @Feature("search")
    public void searchTest() {
        step("Type Java language in a search input", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("Java language");
        });
        step("Verify content found in a list results", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Tag("search")
    @Owner("malyginms")
    @DisplayName("Check a search result in a web page")
    @Description("Search in Wikipedia. Check a search result in a web page")
    @Feature("search")
    public void searchInPageTest() {

        step("Type Java language in a search input", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("Java language");
        });
        step("Verify content found in a list results", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
        step("Open a java page in a search results", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/fragment_feed_header")).first().click());
        step("Verify content found in a list results", () -> {
            $(AppiumBy.className("android.webkit.WebView"))
                    .$$(AppiumBy.className("android.widget.TextView"))
                    .first()
                    .shouldHave(Condition.text("Java"));
        });
    }

    @Test
    @Tag("onboarding")
    @Owner("malyginms")
    @DisplayName("Pass an onboarding page")
    @Description("Pass all pages in an onboarding page in Wikipedia")
    @Feature("onboarding")
    public void onboardingPageTests() {

        step("Check the first onboarding page", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("The Free Encyclopedia\n…in over 300 languages"));

            $(AppiumBy.id("org.wikipedia.alpha:id/option_label"))
                    .shouldHave(text("1.  English"));

            $(AppiumBy.id("org.wikipedia.alpha:id/addLangContainer"));
        });

        step("Check the second onboarding page", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();

            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("New ways to explore"));
        });

        step("Check the third onboarding page", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();

            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Reading lists with sync"));
        });

        step("Check the fourth onboarding page", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();

            $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(text("Send anonymous data"));

            $(AppiumBy.id("org.wikipedia.alpha:id/switchView"));

            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_done_button"));
        });

        step("Get started page", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_done_button")).click();

            $(AppiumBy.id("org.wikipedia.alpha:id/main_toolbar_wordmark"));
            $(AppiumBy.accessibilityId("Search Wikipedia"));
        });
    }

    @Test
    @Tag("language")
    @Owner("malyginms")
    @DisplayName("Add a language")
    @Description("Add an extra language in Wikipedia")
    @Feature("language")
    public void addLanguageTests() {

        step("add language", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/addLangContainer")).click();
            $$(AppiumBy.id("org.wikipedia.alpha:id/wiki_language_title")).last().click();
            $$(AppiumBy.id("org.wikipedia.alpha:id/language_subtitle")).findBy(text("Russian")).click();
        });

        step("check a new language is added", () -> {
            $$(AppiumBy.id("org.wikipedia.alpha:id/wiki_language_title"))
                    .shouldHave(texts("English", "Русский", "ADD LANGUAGE"));

        });
    }
}