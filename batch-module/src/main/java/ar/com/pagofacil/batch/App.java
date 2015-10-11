package ar.com.pagofacil.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {

		String[] springConfig  = 
			{	
				"spring/batch/jobs/job-procesamiento-archivo.xml" 
			};
		
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(springConfig)) {
			JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
			Job job = (Job) context.getBean("procearArchivoJob");

			try {
				JobParameters parameters = new JobParametersBuilder()
						.addString("inputDataFile", "file:c:\\FE.txt")
						.toJobParameters();
				
				JobExecution execution = jobLauncher.run(job, parameters);
				System.out.println("Exit Status : " + execution.getStatus());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("Done");
		}
	}
}
