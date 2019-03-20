package co.simpleq.qlaunch;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import java.io.IOException;
import org.jdom.JDOMException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 20/03/2019
 *
 * @author Ruwanka
 */
public class QLaunchProjectListPopupStep extends BaseListPopupStep<String> {

    private static final Logger logger = Logger.getInstance(QLaunchProjectLocator.class);

    QLaunchProjectListPopupStep(@Nullable String title,
        @NotNull String... values) {
        super(title, values);
    }

    @Override
    public boolean isSpeedSearchEnabled() {
        return true;
    }

    @Override
    public PopupStep onChosen(String selectedValue, boolean finalChoice) {
        ProjectManager projectManager = ProjectManager.getInstance();
        final QLaunchSettings qLaunchSettings = QLaunchSettings.getInstance();

        if (qLaunchSettings != null) {
            final QLaunchPluginState state = qLaunchSettings.getState();
            if (state != null && !state.getIdeaProjects().isEmpty()) {
                final String projectDir = state.getIdeaProjects().get(selectedValue);
                doFinalStep(() -> {
                    try {
                        projectManager.loadAndOpenProject(projectDir);
                    } catch (
                        IOException | JDOMException e) {
                        logger.error(e);
                    }
                });
            }
        }

        return PopupStep.FINAL_CHOICE;
    }
}
