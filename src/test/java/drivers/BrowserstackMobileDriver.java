package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class, System.getProperties());
        String user = credentialsConfig.user();
        String key = credentialsConfig.key();
        String app = credentialsConfig.app();
        String deviceName = credentialsConfig.deviceName();
        String osVersion = credentialsConfig.osVersion();

        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        mutableCapabilities.setCapability("browserstack.user", user);
        mutableCapabilities.setCapability("browserstack.key", key);

        mutableCapabilities.setCapability("app", app);
        mutableCapabilities.setCapability("device", deviceName);
        mutableCapabilities.setCapability("os_version", osVersion);

        mutableCapabilities.setCapability("project", "Mobile Tests");
        mutableCapabilities.setCapability("build", "browserstack-build-1");
        mutableCapabilities.setCapability("name", "first_test");

        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }

    public static URL getBrowserstackUrl() {
        try {
            return new URL("http://hub.browserstack.com/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


}
