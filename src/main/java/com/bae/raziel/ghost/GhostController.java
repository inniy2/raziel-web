package com.bae.raziel.ghost;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ghost")
public class GhostController {

	@Autowired
	GhostService ghostService;
	
	
	
	@PostMapping("/dryrun")
	public GhostDto dryRun(@Valid @RequestBody GhostDto ghostDto){
		return ghostService.dryRun(ghostDto);
	}
	
	
	@PostMapping("/execute")
	public void execute(@Valid @RequestBody GhostDto ghostDto){
		ghostService.execute(ghostDto);
	}
	

	@PostMapping("/findAll")
	public List<GhostDto> findAll(@Valid @RequestBody GhostDto ghostDto){
		return ghostService.findAll(ghostDto);
	}
	

	@PostMapping("/findAllByProgress")
	public List<GhostDto> findAllByProgress(@Valid @RequestBody GhostDto ghostDto){
		return ghostService.findAllByProgress(ghostDto);
	}
	
	@PostMapping("/findGHCByProgress")
	public GhostDto findGHCByProgress(@Valid @RequestBody GhostDto ghostDto){
		return ghostService.findGHCByProgress(ghostDto);
	}
	
}
