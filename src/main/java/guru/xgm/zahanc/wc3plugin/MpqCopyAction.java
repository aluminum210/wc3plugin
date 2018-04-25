package guru.xgm.zahanc.wc3plugin;
                                  
import java.io.File;
import java.io.IOException;

import org.gradle.api.GradleException;
import org.gradle.api.internal.file.copy.CopyAction;
import org.gradle.api.internal.file.copy.CopyActionProcessingStream;
import org.gradle.api.tasks.WorkResult;
import org.gradle.api.tasks.WorkResults;
import org.gradle.api.file.FileCollection;

import systems.crigges.jmpq3.JMpqEditor;
import systems.crigges.jmpq3.MPQOpenOption;

public class MpqCopyAction implements CopyAction {
	private final File archivePath;
	private final FileCollection source;
	
	public MpqCopyAction(File archivePath, FileCollection source) {
		this.archivePath = archivePath;
		this.source = source;
	}
	
	public WorkResult execute(final CopyActionProcessingStream stream) {
		boolean doBackup = true;
				// Automatically rebuilds mpq after use if not in readonly mode
		try (JMpqEditor editor = new JMpqEditor(archivePath, MPQOpenOption.FORCE_V0)){
			source.getFiles().stream()
			.forEach(f -> {try {editor.insertFile(f.getName(), f, doBackup);} catch (Exception e0) {throw new RuntimeException(e0);}});
				/*e.hasFile("filename"); //Checks if the file exists
				e.extractFile("filename", new File("target location")); //Extracts a specific file out of the mpq to the target location
				if (e.isCanWrite()) {
					e.deleteFile("filename"); //Deletes a specific file out of the mpq
					e.insertFile("filename", new File("file to add"), true); //Inserts a specific into the mpq from the target location
					e.extractAllFiles(new File("target folder")); //Extracts all files inside the mpq to the target folder. If a proper listfile exists,
					e.getFileNames(); //Get the listfile as java HashSet<String>
				}
			}*/		
		} catch (Exception e) {
			throw new GradleException("Failed to execute MPQ copy action.", e);
		}
		return WorkResults.didWork(true);
	}
}