package tests;

import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class AndroidTests extends TestBase {

    @Test
    @Tag("search")
    void searchTest() {
        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("Java language");
        });
        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Tag("search")
    void searchInPageTest() {

        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("Java language");
        });
        step("Verify content found", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
        step("Open java page", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/fragment_feed_header")).first().click());
        step("Verify content found", () -> {
            $(AppiumBy.className("android.webkit.WebView"))
                    .$$(AppiumBy.className("android.widget.TextView"))
                    .first()
                    .shouldHave(Condition.text("Java"));
        });
    }

    @Test
    @Tag("onboarding")
    void onboardingPageTests() {

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

        step("get started page", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_done_button")).click();

            $(AppiumBy.id("org.wikipedia.alpha:id/main_toolbar_wordmark"));
            $(AppiumBy.accessibilityId("Search Wikipedia"));
        });
    }

    @Test
    @Tag("language")
    void addLanguageTests() {

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