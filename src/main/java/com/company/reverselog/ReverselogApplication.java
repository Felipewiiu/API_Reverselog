package com.company.reverselog;

import com.company.reverselog.domain.produto.Produto;
import com.company.reverselog.domain.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReverselogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReverselogApplication.class, args);
	}


}
