package com.holitor.holitorservice.batch;

import com.holitor.holitorservice.module.user.batch.users.UserData;
import com.holitor.holitorservice.module.user.batch.users.UserProcessor;
import com.holitor.holitorservice.module.user.batch.users.UserWriter;
import com.holitor.holitorservice.module.user.batch.users.UserReader;
import com.holitor.holitorservice.module.user.model.User;
import com.holitor.holitorservice.module.stock.batch.products.ProductData;
import com.holitor.holitorservice.module.stock.batch.products.ProductProcessor;
import com.holitor.holitorservice.module.stock.batch.products.ProductReader;
import com.holitor.holitorservice.module.stock.batch.products.ProductWriter;
import com.holitor.holitorservice.module.stock.model.Product;
import com.holitor.holitorservice.module.blog.batch.posts.PostData;
import com.holitor.holitorservice.module.blog.batch.posts.PostProcessor;
import com.holitor.holitorservice.module.blog.batch.posts.PostReader;
import com.holitor.holitorservice.module.blog.batch.posts.PostWriter;
import com.holitor.holitorservice.module.blog.model.Post;
import com.holitor.holitorservice.module.farm.batch.vegetables.VegetableData;
import com.holitor.holitorservice.module.farm.batch.vegetables.VegetableProcessor;
import com.holitor.holitorservice.module.farm.batch.vegetables.VegetableReader;
import com.holitor.holitorservice.module.farm.batch.vegetables.VegetableWriter;
import com.holitor.holitorservice.module.farm.model.Vegetable;
import com.holitor.holitorservice.module.shop.batch.salePoints.SalePointData;
import com.holitor.holitorservice.module.shop.batch.salePoints.SalePointProcessor;
import com.holitor.holitorservice.module.shop.batch.salePoints.SalePointReader;
import com.holitor.holitorservice.module.shop.batch.salePoints.SalePointWriter;
import com.holitor.holitorservice.module.shop.model.SalePoint;
import com.holitor.holitorservice.module.farm.batch.varieties.VarietyData;
import com.holitor.holitorservice.module.farm.batch.varieties.VarietyProcessor;
import com.holitor.holitorservice.module.farm.batch.varieties.VarietyReader;
import com.holitor.holitorservice.module.farm.batch.botanicalFamilies.BotanicalFamilyData;
import com.holitor.holitorservice.module.farm.batch.botanicalFamilies.BotanicalFamilyProcessor;
import com.holitor.holitorservice.module.farm.batch.botanicalFamilies.BotanicalFamilyReader;
import com.holitor.holitorservice.module.farm.batch.botanicalFamilies.BotanicalFamilyWriter;
import com.holitor.holitorservice.module.farm.batch.cropCoefficients.CropCoefficientData;
import com.holitor.holitorservice.module.farm.batch.cropCoefficients.CropCoefficientProcessor;
import com.holitor.holitorservice.module.farm.batch.cropCoefficients.CropCoefficientReader;
import com.holitor.holitorservice.module.farm.model.BotanicalFamily;
import com.holitor.holitorservice.module.farm.batch.taskTypes.TaskTypeData;
import com.holitor.holitorservice.module.farm.batch.taskTypes.TaskTypeProcessor;
import com.holitor.holitorservice.module.farm.batch.taskTypes.TaskTypeReader;
import com.holitor.holitorservice.module.farm.batch.taskTypes.TaskTypeWriter;
import com.holitor.holitorservice.module.farm.model.TaskType;
import com.holitor.holitorservice.module.farm.batch.fertilizers.FertilizerData;
import com.holitor.holitorservice.module.farm.batch.fertilizers.FertilizerProcessor;
import com.holitor.holitorservice.module.farm.batch.fertilizers.FertilizerReader;
import com.holitor.holitorservice.module.farm.batch.fertilizers.FertilizerWriter;
import com.holitor.holitorservice.module.farm.model.Fertilizer;
import com.holitor.holitorservice.module.farm.batch.pesticides.PesticideData;
import com.holitor.holitorservice.module.farm.batch.pesticides.PesticideProcessor;
import com.holitor.holitorservice.module.farm.batch.pesticides.PesticideReader;
import com.holitor.holitorservice.module.farm.batch.pesticides.PesticideWriter;
import com.holitor.holitorservice.module.farm.model.Pesticide;
import com.holitor.holitorservice.module.farm.batch.tools.ToolData;
import com.holitor.holitorservice.module.farm.batch.tools.ToolProcessor;
import com.holitor.holitorservice.module.farm.batch.tools.ToolReader;
import com.holitor.holitorservice.module.farm.batch.tools.ToolWriter;
import com.holitor.holitorservice.module.farm.model.Tool;

import java.io.FileNotFoundException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

  private static final String JOB_NAME = "Job";

  private static final String STEP_USERS_NAME = "StepUsers";
  private static final String STEP_SALE_POINTS_NAME = "StepSalePoints";
  private static final String STEP_PRODUCTS_NAME = "StepProducts";
  private static final String STEP_POSTS_NAME = "StepPosts";
  private static final String STEP_VEGETABLES_NAME = "StepVegetables";
  private static final String STEP_VARIETIES_NAME = "StepVarieties";
  private static final String STEP_BOTANICAL_FAMILIES_NAME = "StepBotanicalFamilies";
  private static final String STEP_CROP_COEFFICIENTS_NAME = "StepCropCoefficient";
  private static final String STEP_TASK_TYPES_NAME = "StepTaskTypes";
  private static final String STEP_FERTILIZERS_NAME = "StepFertilizers";
  private static final String STEP_PESTICIDES_NAME = "StepPesticides";
  private static final String STEP_TOOLS_NAME = "StepTools";

  private static final String PATH_BATCH = "batch/datas";
	private static final String PATH_USERS = PATH_BATCH+"/users";
	private static final String PATH_SALE_POINTS = PATH_BATCH+"/shop/sale-points";
	private static final String PATH_PRODUCTS = PATH_BATCH+"/stock/products";
	private static final String PATH_POSTS = PATH_BATCH+"/blog/posts";
	private static final String PATH_VEGETABLES = PATH_BATCH+"/farm/vegetables";
	private static final String PATH_VARIETIES = PATH_BATCH+"/farm/varieties";
	private static final String PATH_BOTANICAL_FAMILIES = PATH_BATCH+"/farm/botanical-families";
	private static final String PATH_CROP_COEFFICIENTS = PATH_BATCH+"/farm/crop-coefficients";
	private static final String PATH_TASK_TYPES = PATH_BATCH+"/farm/task-types";
	private static final String PATH_FERTILIZERS = PATH_BATCH+"/farm/fertilizers";
	private static final String PATH_PESTICIDES = PATH_BATCH+"/farm/pesticides";
	private static final String PATH_TOOLS = PATH_BATCH+"/farm/tools";

  private @Autowired JobBuilderFactory jobBuilderFactory;
  
  private @Autowired StepBuilderFactory stepBuilderFactory;

  /* ------------------------- Step Users ------------------------- */

  public @Bean UserReader userReader() throws FileNotFoundException { return new UserReader(PATH_USERS); }
  
  public @Bean UserProcessor userProcessor() { return new UserProcessor(); }

  public @Bean UserWriter userWriter() { return new UserWriter(); }

	public @Bean Step stepUsers() throws FileNotFoundException {
		return stepBuilderFactory.get(STEP_USERS_NAME)
			.<UserData, User>chunk(1)
      .reader(userReader())
      .processor(userProcessor())
			.writer(userWriter())
      .build();
	}

  /* ------------------------- Step Point Sales ------------------------- */

  public @Bean SalePointReader salePointReader() throws FileNotFoundException { return new SalePointReader(PATH_SALE_POINTS); }
  
  public @Bean SalePointProcessor salePointProcessor() { return new SalePointProcessor(); }

  public @Bean SalePointWriter salePointWriter() { return new SalePointWriter(); }

	public @Bean Step stepSalePoints() throws FileNotFoundException {
		return stepBuilderFactory.get(STEP_SALE_POINTS_NAME)
			.<SalePointData, SalePoint>chunk(1)
      .reader(salePointReader())
      .processor(salePointProcessor())
      .writer(salePointWriter())
      .build();
	}

  /* ------------------------- Step Products ------------------------- */

  public @Bean ProductReader productReader() throws FileNotFoundException { return new ProductReader(PATH_PRODUCTS); }

  public @Bean ProductProcessor productProcessor() { return new ProductProcessor(); }

  public @Bean ProductWriter productWriter() { return new ProductWriter(); }

	public @Bean Step stepProducts() throws FileNotFoundException {
		return stepBuilderFactory.get(STEP_PRODUCTS_NAME)
			.<ProductData, Product>chunk(1)
      .reader(productReader())
      .processor(productProcessor())
			.writer(productWriter())
      .build();
	}

  /* ------------------------- Step Posts ------------------------- */

  public @Bean PostReader postReader() throws FileNotFoundException { return new PostReader(PATH_POSTS); }
  
  public @Bean PostProcessor postProcessor() { return new PostProcessor(); }
	
  public @Bean PostWriter postWriter() { return new PostWriter(); }

	public @Bean Step stepPosts() throws FileNotFoundException {
		return stepBuilderFactory.get(STEP_POSTS_NAME)
			.<PostData, Post>chunk(1)
      .reader(postReader())
      .processor(postProcessor())
			.writer(postWriter())
      .build();
	}

  /* ------------------------- Step Vegetables ------------------------- */

  public @Bean VegetableReader vegetableReader() throws FileNotFoundException { return new VegetableReader(PATH_VEGETABLES); }
  
  public @Bean VegetableProcessor vegetableProcessor() { return new VegetableProcessor(); }
  
  public @Bean VegetableWriter vegetableWriter() { return new VegetableWriter(); }
  
  public @Bean Step stepVegetables() throws FileNotFoundException {
    return stepBuilderFactory.get(STEP_VEGETABLES_NAME)
      .<VegetableData, Vegetable>chunk(1)
      .reader(vegetableReader())
      .processor(vegetableProcessor())
      .writer(vegetableWriter())  
      .build();
  }

  /* ------------------------- Step Varieties ------------------------- */

  public @Bean VarietyReader varietyReader() throws FileNotFoundException { return new VarietyReader(PATH_VARIETIES); }
  
  public @Bean VarietyProcessor varietyProcessor() { return new VarietyProcessor(); }

	public @Bean Step stepVarieties() throws FileNotFoundException {
		return stepBuilderFactory.get(STEP_VARIETIES_NAME)
			.<VarietyData, Vegetable>chunk(1)
      .reader(varietyReader())
      .processor(varietyProcessor())
			.writer(vegetableWriter())
      .build();
	}

  /* ------------------------- Step Botanical Families ------------------------- */

  public @Bean BotanicalFamilyReader botanicalFamilyReader() throws FileNotFoundException { return new BotanicalFamilyReader(PATH_BOTANICAL_FAMILIES); }
  
  public @Bean BotanicalFamilyProcessor botanicalFamilyProcessor() { return new BotanicalFamilyProcessor(); }
  
  public @Bean BotanicalFamilyWriter botanicalFamilyWriter() { return new BotanicalFamilyWriter(); }
  
  public @Bean Step stepBotanicalFamilies() throws FileNotFoundException {
    return stepBuilderFactory.get(STEP_BOTANICAL_FAMILIES_NAME)
      .<BotanicalFamilyData, BotanicalFamily>chunk(1)
      .reader(botanicalFamilyReader())
      .processor(botanicalFamilyProcessor())
      .writer(botanicalFamilyWriter())  
      .build();
  }

  /* ------------------------- Step CropCoefficients ------------------------- */

  public @Bean CropCoefficientReader cropCoefficientReader() throws FileNotFoundException { return new CropCoefficientReader(PATH_CROP_COEFFICIENTS); }
  
  public @Bean CropCoefficientProcessor cropcoefficientProcessor() { return new CropCoefficientProcessor(); }

	public @Bean Step stepCropCoefficients() throws FileNotFoundException {
		return stepBuilderFactory.get(STEP_CROP_COEFFICIENTS_NAME)
			.<CropCoefficientData, Vegetable>chunk(1)
      .reader(cropCoefficientReader())
      .processor(cropcoefficientProcessor())
			.writer(vegetableWriter())
      .build();
	}

  /* ------------------------- Step Task Types ------------------------- */

  public @Bean TaskTypeReader taskTypeReader() throws FileNotFoundException { return new TaskTypeReader(PATH_TASK_TYPES); }
  
  public @Bean TaskTypeProcessor taskTypeProcessor() { return new TaskTypeProcessor(); }

  public @Bean TaskTypeWriter taskTypeWriter() { return new TaskTypeWriter(); }

	public @Bean Step stepTaskTypes() throws FileNotFoundException {
		return stepBuilderFactory.get(STEP_TASK_TYPES_NAME)
			.<TaskTypeData, TaskType>chunk(1)
      .reader(taskTypeReader())
      .processor(taskTypeProcessor())
			.writer(taskTypeWriter())
      .build();
	}

  /* ------------------------- Step Fertilizers ------------------------- */

  public @Bean FertilizerReader fertilizerReader() throws FileNotFoundException { return new FertilizerReader(PATH_FERTILIZERS); }

  public @Bean FertilizerProcessor fertilizerProcessor() { return new FertilizerProcessor(); }

  public @Bean FertilizerWriter fertilizerWriter() { return new FertilizerWriter(); }

	public @Bean Step stepFertilizers() throws FileNotFoundException {
		return stepBuilderFactory.get(STEP_FERTILIZERS_NAME)
			.<FertilizerData, Fertilizer>chunk(1)
      .reader(fertilizerReader())
      .processor(fertilizerProcessor())
			.writer(fertilizerWriter())
      .build();
	}

  /* ------------------------- Step Pesticides ------------------------- */

  public @Bean PesticideReader pesticideReader() throws FileNotFoundException { return new PesticideReader(PATH_PESTICIDES); }

  public @Bean PesticideProcessor pesticideProcessor() { return new PesticideProcessor(); }

  public @Bean PesticideWriter pesticideWriter() { return new PesticideWriter(); }

	public @Bean Step stepPesticides() throws FileNotFoundException {
		return stepBuilderFactory.get(STEP_PESTICIDES_NAME)
			.<PesticideData, Pesticide>chunk(1)
      .reader(pesticideReader())
      .processor(pesticideProcessor())
			.writer(pesticideWriter())
      .build();
	}

  /* ------------------------- Step Tools ------------------------- */

  public @Bean ToolReader toolReader() throws FileNotFoundException { return new ToolReader(PATH_TOOLS); }
  
  public @Bean ToolProcessor toolProcessor() { return new ToolProcessor(); }

  public @Bean ToolWriter toolWriter() { return new ToolWriter(); }

	public @Bean Step stepTools() throws FileNotFoundException {
		return stepBuilderFactory.get(STEP_TOOLS_NAME)
			.<ToolData, Tool>chunk(1)
      .reader(toolReader())
      .processor(toolProcessor())
			.writer(toolWriter())
      .build();
	}

  /* ------------------------- Job ------------------------- */

  public @Bean Job job() throws Exception {
    return jobBuilderFactory.get(JOB_NAME)
      .start(stepUsers())
      .next(stepSalePoints())
      .next(stepFertilizers())
      .next(stepPesticides())
      .next(stepBotanicalFamilies())
      .next(stepVegetables())
      .next(stepVarieties())
      .next(stepCropCoefficients())
      .next(stepTaskTypes())
      .next(stepProducts())
      .next(stepPosts())
      .next(stepTools())
      .build();
  }

}