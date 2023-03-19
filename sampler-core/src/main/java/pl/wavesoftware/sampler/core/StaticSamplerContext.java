package pl.wavesoftware.sampler.core;

import io.vavr.collection.Map;
import pl.wavesoftware.eid.exceptions.EidIllegalArgumentException;
import pl.wavesoftware.sampler.api.Sampler;

public class StaticSamplerContext extends AbstractSamplerContext {

  private final Map<Class<?>, Sampler<?>> samplers;

  public StaticSamplerContext(Map<Class<?>, Sampler<?>> samplers) {
    this.samplers = samplers;
  }

  @Override
  @SuppressWarnings("unchecked")
  protected <T> Sampler<T> getSampler(Class<? extends Sampler<T>> spec) {
    return (Sampler<T>) samplers.get(spec).getOrElseThrow(() ->
      new EidIllegalArgumentException("20230319:173914", "No sampler for " + spec)
    );
  }
}
