package factory.settings;

import org.openqa.selenium.remote.AbstractDriverOptions;

public interface IBrowserSettings<T extends AbstractDriverOptions<?>> {
  
  T getSettings();
}