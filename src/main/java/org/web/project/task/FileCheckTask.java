package org.web.project.task;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.web.project.domain.BoardAttachVO;
import org.web.project.mapper.BoardAttachMapper;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Log4j
@RequiredArgsConstructor
public class FileCheckTask {

	private final BoardAttachMapper attachMapper;
	
	private String getFolderYesterDay() {
		SimpleDateFormat sdf =
				new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		
		String str = sdf.format(cal.getTime()); 
		// 2022\\11\\27
		return str.replace("-", File.separator);
	}

	@Scheduled(cron = "0 0 2 * * *")
	public void checkFiles() throws Exception {
		log.warn("=======================================");
		log.warn("checkFiles=============================");
		
		// file list in database
		List<BoardAttachVO> fileList
			= attachMapper.getOldFiles();
		
		List<Path> fileListPaths =
		fileList.stream().map(vo -> 
			Paths.get("C:\\picture", vo.getUploadPath(),
					vo.getUuid() + "_" + vo.getFileName())
		).collect(Collectors.toList());
		
		fileList.stream().filter(vo -> vo.isFileType() == true)
		.map(vo -> Paths.get("C:\\picture", vo.getUploadPath(),
					"s_" + vo.getUuid() + "_" + vo.getFileName()))
		.forEach(p -> fileListPaths.add(p));
		
		fileListPaths.forEach(p -> log.warn(p));
		
		// c:\\upload\\2022\\11\\27
		File targetDir = Paths.get("C:\\picture",
				getFolderYesterDay()).toFile();
		
		File[] removeFiles = 
		targetDir.listFiles(file -> 
			fileListPaths.contains(file.toPath()) == false);
		
		for (File file : removeFiles) {
			file.delete();
		}
		
	}
	
	
	
	
	
}





