package co.simpleq.qlaunch;

import com.intellij.codeInsight.hint.HintManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import org.jetbrains.annotations.NotNull;

/**
 * 20/03/2019
 *
 * @author Ruwanka
 */
public class QLaunchProjectOpenAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (e.getProject() == null) {
            showError(editor, "failed to identify project!");
        }

        final QLaunchSettings qLaunchSettings = QLaunchSettings.getInstance();
        if (qLaunchSettings != null) {
            final QLaunchPluginState state = qLaunchSettings.getState();
            if (state != null && !state.getIdeaProjects().isEmpty()) {
                final QLaunchProjectListPopupStep step = new QLaunchProjectListPopupStep(
                    "Open Project", state.getIdeaProjects().keySet().toArray(new String[0]));
                JBPopupFactory.getInstance().createListPopup(step, 10)
                    .showCenteredInCurrentWindow(e.getProject());
            } else {
                showError(editor,
                    "Please run Locate Projects (ctrl + alt + Q) or Tools -> Locate Projects!");
            }
        } else {
            showError(editor, "failed to load qLaunch settings");
        }
    }

    private void showError(Editor editor, String message) {
        HintManager.getInstance().showErrorHint(editor, message, HintManager.RIGHT_UNDER);
    }
}
