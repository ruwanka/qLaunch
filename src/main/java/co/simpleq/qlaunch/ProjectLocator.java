package co.simpleq.qlaunch;

//import com.intellij.openapi.diagnostic.Logger;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 20/03/2019
 *
 * @author Ruwanka
 */
public class ProjectLocator extends SimpleFileVisitor<Path> {

//    private static final Logger logger = Logger.getInstance(ProjectLocator.class);

    private final QLaunchPluginState state;

    public ProjectLocator(QLaunchPluginState state) {
        this.state = state;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
        throws IOException {
        if (dir.toFile().getName().equals(".idea")) {
            this.state.addProject(dir.getParent().toFile().getName(), dir.getParent().toString());
//            logger.info("indexing project: " + dir.getParent().toFile().getName());
            System.out.println(dir.getParent().getFileName());
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        // todo make preVisitDirectory skip unreadable directories
        return FileVisitResult.SKIP_SUBTREE;
    }

    public static void main(String[] args) {
        final File[] files = File.listRoots();
        for(File file : files){
            try {
                Files.walkFileTree(file.toPath(), new ProjectLocator(new QLaunchPluginState()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
