package ar.com.pagofacil.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import ar.com.pagofacil.batch.model.Vendedor;

@Component(value="itemProcessor")
public class CustomItemProcessor implements ItemProcessor<Vendedor, Vendedor> {

	public Vendedor process(Vendedor item) throws Exception {
		System.out.println("Processing..." + item);
		return item;
	}

}