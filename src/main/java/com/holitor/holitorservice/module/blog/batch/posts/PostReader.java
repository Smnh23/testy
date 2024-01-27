package com.holitor.holitorservice.module.blog.batch.posts;

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
public class PostReader extends MultiResourceItemReader<PostData>{
	
	public PostReader(String repositoryPath) throws FileNotFoundException {
    log.info("Starting batch reader in repository : " + FilenameUtils.getName(repositoryPath));
		this.setResources(getInputResources(repositoryPath));
		this.setDelegate(readOneFile());
	}
	
	private FileSystemResource[] getInputResources(String repositoryPath) throws FileNotFoundException {
		List<FileSystemResource> inputResources = new ArrayList<FileSystemResource>();
		File repository = ResourceUtils.getFile("classpath:"+repositoryPath);
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
	
	private FlatFileItemReader<PostData> readOneFile() {	
		FlatFileItemReader<PostData> reader = new FlatFileItemReader<PostData>();
		reader.setLinesToSkip(1);
    reader.setLineMapper(lineMapper());
		return reader;
	}
	
  public LineMapper<PostData> lineMapper() {
    DefaultLineMapper<PostData> lineMapper = new DefaultLineMapper<PostData>();
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    lineTokenizer.setNames(new String[] { "id", "category", "categoryImage", "categoryDescription", "title", "description", "content", "image" });
    lineTokenizer.setIncludedFields(new int[] { 0, 1, 2, 3, 4, 5, 6, 7 });
    lineTokenizer.setDelimiter(";");
    BeanWrapperFieldSetMapper<PostData> fieldSetMapper = new BeanWrapperFieldSetMapper<PostData>();
    fieldSetMapper.setTargetType(PostData.class);
    lineMapper.setLineTokenizer(lineTokenizer);
    lineMapper.setFieldSetMapper(fieldSetMapper);
    return lineMapper;
  }

}