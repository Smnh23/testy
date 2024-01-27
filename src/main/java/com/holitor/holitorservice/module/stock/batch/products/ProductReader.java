package com.holitor.holitorservice.module.stock.batch.products;

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
public class ProductReader extends MultiResourceItemReader<ProductData>{
	
	public ProductReader(String repositoryPath) throws FileNotFoundException {
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
	
	private FlatFileItemReader<ProductData> readOneFile() {	
		FlatFileItemReader<ProductData> reader = new FlatFileItemReader<ProductData>();
		reader.setLinesToSkip(1);
    reader.setLineMapper(lineMapper());
		return reader;
	}
	
  public LineMapper<ProductData> lineMapper() {
    DefaultLineMapper<ProductData> lineMapper = new DefaultLineMapper<ProductData>();
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    lineTokenizer.setNames(new String[] { "id", "idVariety", "description", "price", "unitFactor", "unitName", "image" });
    lineTokenizer.setIncludedFields(new int[] { 0, 1, 2, 3, 4, 5, 6 });
    lineTokenizer.setDelimiter(";");
    BeanWrapperFieldSetMapper<ProductData> fieldSetMapper = new BeanWrapperFieldSetMapper<ProductData>();
    fieldSetMapper.setTargetType(ProductData.class);
    lineMapper.setLineTokenizer(lineTokenizer);
    lineMapper.setFieldSetMapper(fieldSetMapper);
    return lineMapper;
  }

}