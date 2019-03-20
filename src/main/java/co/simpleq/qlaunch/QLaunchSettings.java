package co.simpleq.qlaunch;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import javax.annotation.Nullable;

/**
 * Persistent global settings object for the Lombok plugin.
 */
@State(
  name = "QLaunchSettings",
  storages = @Storage("q-launch-plugin.xml")
)
public class QLaunchSettings implements PersistentStateComponent<QLaunchPluginState> {

  /**
   * Get the instance of this service.
   *
   * @return the unique {@link QLaunchSettings} instance.
   */
  public static QLaunchSettings getInstance() {
    return ServiceManager.getService(QLaunchSettings.class);
  }

  private QLaunchPluginState myState = new QLaunchPluginState();

  @Nullable
  @Override
  public QLaunchPluginState getState() {
    return myState;
  }

  @Override
  public void loadState(QLaunchPluginState element) {
    myState = element;
  }

  public String getVersion() {
    return myState.getPluginVersion();
  }

  public void setVersion(String version) {
    myState.setPluginVersion(version);
  }

}