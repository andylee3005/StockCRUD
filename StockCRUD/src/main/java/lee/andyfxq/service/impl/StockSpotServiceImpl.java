package lee.andyfxq.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lee.andyfxq.model.StockSpot;
import lee.andyfxq.repository.StockSpotRepository;
import lee.andyfxq.service.StockSpotService;
import lee.andyfxq.utils.StockSpotRate;

@Service("spotService")
public class StockSpotServiceImpl implements StockSpotService {
	
	private final static Logger logger = LoggerFactory.getLogger(StockSpotServiceImpl.class );

	@Autowired
	StockSpotRate spotRate;
	
	@Autowired
	StockSpotRepository spotRepo;
	
	private int randseed = 550;
	static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
	
	@Override
	public List<StockSpot> getAll() {
		return spotRepo.findAll();
	}

	@Override
	public Optional<StockSpot> getById(String id) {
		return spotRepo.findById(id);
	}
	
	@Override
	public List<StockSpot> getBySymbol(String symbol) {
		return spotRepo.findBySymbol(symbol);
	}

	@Override
	public List<StockSpot> getByCurrency(String currency) {
		return spotRepo.findByCurrency(currency);
	}

//	@Override
//	public StockSpot _save(StockSpot stockSpot) {
//		return spotRepo.save(stockSpot);
//	}

	@Override
	public List<StockSpot> getByTime(String symbol, long time) {
		List<StockSpot> symbList = spotRepo.findBySymbol(symbol);
		List<StockSpot> retList = new ArrayList<>();
		
		symbList.stream().forEach( spot -> {
			if (spot.getCreatedTime() >= time) retList.add(spot);
		});;
		
		return retList;
	}


	@Override
	public List<StockSpot> generateAll() {
		int num = (int) (Math.random() * randseed);
		List<StockSpot> retList = spotRate.getStockSpotList(num);
		retList.stream().forEach( spot -> {spotRepo.save(spot); logger.info(spot.toString());} );
		return retList;
	}

	@Override
	public List<StockSpot> generateBySymbol(String symbol) {
		int num = (int) (Math.random() * randseed);
		List<StockSpot> retList = spotRate.getStockSpotList(num, symbol);
		retList.stream().forEach( spot -> {spotRepo.save(spot);  logger.info(spot.toString());} );
		return retList;
	}

	@Override
	public Optional<StockSpot> getMostRecent(String symbol) {
		return Optional.of(spotRepo.findFirstBySymbolOrderByCreatedTimeDesc(symbol));
	}


	

}
