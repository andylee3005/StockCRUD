package lee.andyfxq.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import lee.andyfxq.model.StockSpot;

@Component
public class StockSpotRate {
	
	private ArrayList<String> symbolList = new ArrayList<>();
	private ArrayList<BigDecimal> priceList = new ArrayList<>();
	private HashMap<String, String> currencyMap = new HashMap<>();
	private int size = 0;
	
	public StockSpotRate() {
		symbolList.add("A"); priceList.add(new BigDecimal("11.8972")); currencyMap.put("A", "USD");
		symbolList.add("B"); priceList.add(new BigDecimal("0.0358")); currencyMap.put("B", "USD");
		symbolList.add("C"); priceList.add(new BigDecimal("3.7830")); currencyMap.put("C", "CAD");
		symbolList.add("D"); priceList.add(new BigDecimal("8.7741")); currencyMap.put("D", "EUR");
		symbolList.add("E"); priceList.add(new BigDecimal("360.8695")); currencyMap.put("E", "JPY");
		
		this.size = symbolList.size();
	}
	
	public StockSpot generateStockSpot(String... spotParams) {
		int index = spotParams.length > 0 ? symbolList.indexOf(spotParams[0]) : (int) (Math.random() * size);
		
		StockSpot spot = new StockSpot();
		spot.setSymbol(symbolList.get(index));
		spot.setCurrency(currencyMap.get(symbolList.get(index)));
		int pulse = (int) (Math.random() * 2);
		int range = (int) (Math.random() * 400);
		BigDecimal price = priceList.get(index);
		BigDecimal value = price.multiply(new BigDecimal(range * 0.000001));
		spot.setPrice( (pulse == 0 ? price.subtract(value) : price.add(value)).setScale(8, BigDecimal.ROUND_DOWN) );
		
		priceList.remove(index);
		priceList.add(index, spot.getPx());
		
		return spot;
	}
	
	public List<StockSpot> getStockSpotList(int qNum, String... params) {
		List<StockSpot> spotList = new ArrayList<>();
		StockSpot spot = null;
		
		for (int i=0; i<qNum; i++) {
			switch (params.length) {
			case 0: spot = generateStockSpot(); break;
			case 1: spot = generateStockSpot(params[0]); break;
			default: spot = generateStockSpot();
			}
			StockSpot aSpot = new StockSpot(spot.getSymbol(), spot.getCurrency(), spot.getPx());
			spotList.add(aSpot);
		}
		return spotList;
	}
}
