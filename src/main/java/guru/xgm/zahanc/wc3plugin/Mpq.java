package guru.xgm.zahanc.wc3plugin;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.bundling.AbstractArchiveTask;
import org.gradle.api.internal.file.copy.CopyAction;

public class Mpq extends AbstractArchiveTask {
	public static final String MPQ_EXTENSION = "mpq";
	
	public Mpq() {
		setExtension(MPQ_EXTENSION);
	}
	
	@Override
	protected CopyAction createCopyAction() {
		return new MpqCopyAction(getArchivePath(), getSource());
	}
}