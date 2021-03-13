package lee.andyfxq.service;

import java.util.List;
import java.util.Optional;

import lee.andyfxq.model.Stock;

public interface StockService {
	
	List<Stock> getAll();
	Optional<Stock> getById(String id);
	Stock _save(Stock stock);
	void removeById(String id);
	
}
