package lee.andyfxq.service;

import java.util.List;
import java.util.Optional;

import lee.andyfxq.model.StockSpot;

public interface StockSpotService {
	
	List<StockSpot> getAll();
	Optional<StockSpot> getById(String id);
	List<StockSpot> getBySymbol(String symbol);
	List<StockSpot> getByCurrency(String currency);
//	StockSpot _save(StockSpot stockSpot);
	List<StockSpot> getByTime(String symbol, long time);
	Optional<StockSpot> getMostRecent(String symbol);
	
	List<StockSpot> generateAll();
	List<StockSpot> generateBySymbol(String symbol);
	
	
}
