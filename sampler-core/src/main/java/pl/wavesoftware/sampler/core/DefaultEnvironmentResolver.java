package pl.wavesoftware.sampler.core;

import pl.wavesoftware.sampler.api.EnvironmentResolver;

import java.util.Optional;
import java.util.function.Supplier;

public class DefaultEnvironmentResolver implements EnvironmentResolver {
  @Override
  public String resolveProperty(String property, Supplier<String> defaultValueSupplier) {
    String[] parts = property.toLowerCase().split("[-_.]");
    property = String.join(".", parts);
    String envKey = String.join("_", parts).toUpperCase();
    String fallback = Optional.ofNullable(System.getProperty(property))
      .orElseGet(defaultValueSupplier);
    return Optional.ofNullable(System.getenv(envKey))
      .orElse(fallback);
  }
}
