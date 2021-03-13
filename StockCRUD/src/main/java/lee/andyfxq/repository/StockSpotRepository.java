package lee.andyfxq.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import lee.andyfxq.model.StockSpot;

public interface StockSpotRepository extends MongoRepository<StockSpot, String> {

	List<StockSpot> findBySymbol(String symbol);
	List<StockSpot> findByCurrency(String currency);
	StockSpot findFirstBySymbolOrderByCreatedTimeDesc(String symbol);
	
}
