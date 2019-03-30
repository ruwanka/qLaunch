package co.simpleq.qlaunch;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import java.io.File;
import org.jetbrains.annotations.NotNull;

/**
 * 30/03/2019
 *
 * @author Ruwanka
 */
public class QLaunchPluginVerifyAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        final QLaunchSettings qLaunchSettings = QLaunchSettings.getInstance();

        if (qLaunchSettings != null) {
            final QLaunchPluginState state = qLaunchSettings.getState();
            if (state != null) {
                state.getIdeaProjects().entrySet().removeIf(
                    nextProject -> !isValid(nextProject.getKey(), nextProject.getValue()));
            }
        }
    }

    private boolean isValid(String key, String value) {
        final File file = new File(value);
        return file.exists() && file.isDirectory();
    }
}
