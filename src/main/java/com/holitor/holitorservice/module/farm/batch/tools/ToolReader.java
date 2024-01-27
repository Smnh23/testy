package com.holitor.holitorservice.module.farm.batch.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.ResourceUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ToolReader extends MultiResourceItemReader<ToolData>{
	
	public ToolReader(String repositoryPath) throws FileNotFoundException {
    log.info("Starting batch reader in repository : " + FilenameUtils.getName(repositoryPath));
		this.setResources(getInputResources(repositoryPath));
		this.setDelegate(readOneFile());
	}
	
	private FileSystemResource[] getInputResources(String repositoryPath) throws FileNotFoundException {
		List<FileSystemResource> inputResources = new ArrayList<FileSystemResource>();
		File repository = ResourceUtils.getFile("classpath:"+repositoryPath);;
		if(repository != null && repository.isDirectory()) {
			File[] files = repository.listFiles();
			if(files != null) {
				for (File file : files) {
					log.info("Reading file : " + file.getName());
					FileSystemResource resource = new FileSystemResource(file);
					inputResources.add(resource);
				}
			}
		}
		return inputResources.toArray(new FileSystemResource[inputResources.size()]);
	}
	
	private FlatFileItemReader<ToolData> readOneFile() {	
		FlatFileItemReader<ToolData> reader = new FlatFileItemReader<ToolData>();
		reader.setLinesToSkip(1);
    reader.setLineMapper(lineMapper());
		return reader;
	}
	
  public LineMapper<ToolData> lineMapper() {
    DefaultLineMapper<ToolData> lineMapper = new DefaultLineMapper<ToolData>();
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    lineTokenizer.setNames(new String[] { "id", "name", "brand", "price", "toolType" });
    lineTokenizer.setIncludedFields(new int[] { 0, 1, 2, 3, 4 });
    lineTokenizer.setDelimiter(";");
    BeanWrapperFieldSetMapper<ToolData> fieldSetMapper = new BeanWrapperFieldSetMapper<ToolData>();
    fieldSetMapper.setTargetType(ToolData.class);
    lineMapper.setLineTokenizer(lineTokenizer);
    lineMapper.setFieldSetMapper(fieldSetMapper);
    return lineMapper;
  }

}