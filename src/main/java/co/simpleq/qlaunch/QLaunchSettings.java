package co.simpleq.qlaunch;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import javax.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

/**
 * 20/03/2019
 *
 * @author Ruwanka
 *
 * Persistent global settings object for the qLaunch plugin.
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
  static QLaunchSettings getInstance() {
    return ServiceManager.getService(QLaunchSettings.class);
  }

  private QLaunchPluginState myState = new QLaunchPluginState();

  @Nullable
  @Override
  public QLaunchPluginState getState() {
    return myState;
  }

  @Override
  public void loadState(@NotNull QLaunchPluginState element) {
    myState = element;
  }

}