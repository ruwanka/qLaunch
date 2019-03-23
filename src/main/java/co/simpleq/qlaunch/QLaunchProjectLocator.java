package co.simpleq.qlaunch;

import com.intellij.openapi.diagnostic.Logger;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 20/03/2019
 *
 * @author Ruwanka
 */
public class QLaunchProjectLocator extends SimpleFileVisitor<Path> {

    private static final Logger logger = Logger.getInstance(QLaunchProjectLocator.class);

    private final QLaunchPluginState state;

    QLaunchProjectLocator(QLaunchPluginState state) {
        this.state = state;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        if (dir.toFile().getName().equals(".idea")) {
            this.state.addProject(dir.getParent().toFile().getName(), dir.getParent().toString());
            logger.info("indexing project: " + dir.getParent().toFile().getName());
            System.out.println(dir.getParent().getFileName());
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        // todo make preVisitDirectory skip unreadable directories
        return FileVisitResult.SKIP_SUBTREE;
    }
}
