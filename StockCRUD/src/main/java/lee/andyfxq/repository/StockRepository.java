package lee.andyfxq.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import lee.andyfxq.model.Stock;

public interface StockRepository extends MongoRepository<Stock, String> {
	
}
