package lee.andyfxq.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lee.andyfxq.model.StockSpot;
import lee.andyfxq.service.StockSpotService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/STOCK/spot")
public class StockSpotController {
	
	@Autowired
	@Qualifier("spotService")
	StockSpotService spotService;
	
	@GetMapping("/{symbol}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public Optional<StockSpot> requestStockSpot(@PathVariable String symbol) {
		return spotService.getMostRecent(symbol);
	}
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<StockSpot> requestStockSpot() {
		return spotService.getAll();
	}
	
	@GetMapping("id/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public Optional<StockSpot> requestStockSpotById(@PathVariable String id) {
		return spotService.getById(id);
	}
	
	@GetMapping("/symbol/{symbol}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<StockSpot> requestStockSpotBySymbol(@PathVariable String symbol) {
		return spotService.getBySymbol(symbol);
	}
	
	@GetMapping("/currency/{currency}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<StockSpot> requestStockSpotByCurrency(@PathVariable String currency) {
		return spotService.getByCurrency(currency);
	}
	
	@GetMapping("/symbol/{symbol}/time/{time}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<StockSpot> requestStockSpotBySymbolAndTime(@PathVariable String symbol, @PathVariable long time) {
		return spotService.getByTime(symbol, time);
	}
	
	@GetMapping("/generate")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<StockSpot> generateStockSpot() {
		return spotService.generateAll();
	}
	
	@GetMapping("/generate/{symbol}")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<StockSpot> generateStockSpot(@PathVariable String symbol) {
		return spotService.generateBySymbol(symbol);
	}
	
	
}
