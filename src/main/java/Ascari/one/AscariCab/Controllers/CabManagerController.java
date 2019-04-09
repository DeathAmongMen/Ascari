package Ascari.one.AscariCab.Controllers;


import java.util.List;


import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import database.CabManagerDb;

import Entity.CabManager;
import Exceptions.ResourceNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/api/cab/manager")
public class CabManagerController 
{

	Logger logger = LogManager.getLogger(CabManagerController.class);

	CabManagerDb cabmandb = new CabManagerDb();

	@GetMapping("/all")
	public List<CabManager> getAll()
	{
		return cabmandb.selectAll();			
	}                           
	                           
	@PostMapping("/add")           
	public CabManager create(@Valid @RequestBody CabManager item)
	{                          
		 cabmandb.add(item);
		 return item;
	}                          
	                    
	                
	@GetMapping("/{id}")
	public CabManager get(@PathVariable(value="id")  int id) 
	{
		CabManager item = new CabManager();
		item = cabmandb.get(id);
		try 
		{
			if (item != null )	
			{
				logger.info("item exits");
			}			
			return item;
		}
		catch(ResourceNotFoundException e ) 
		{
			e.printStackTrace();
			return null;
		}
		
					
	}
/*
	@PutMapping("/{id}")
	public CabManager update(@PathVariable(value = "id") int id,@Valid @RequestBody CabManager details) 
	{
		CabManager cabman = cabmandb.get(id);
		try
		{
			
			 if (cabman != null)
			 {				
				 cabman.setEmail(details.getEmail());
				 cabman.setPassword(details.getPassword());				 
				 cabmandb.add(cabman);			 
			 }	 
			 return cabman;  
		}
		catch(ResourceNotFoundException e ) 
			{
			e.printStackTrace();
			return null;
			}
		
	}
	
	*/

@PutMapping("/{id}")
public CabManager update(@PathVariable(value = "id") int id,@Valid @RequestBody CabManager details) 
{
	
	try
	{				
		cabmandb.update(details, id);			
		return details;
	}
	catch(ResourceNotFoundException e ) 
		{
		e.printStackTrace();
		return null;
		}

}


	

@DeleteMapping("/{id}")
public ResponseEntity<?> delete(@PathVariable(value = "id") int id) 
{
	CabManager cabman = cabmandb.get(id);
           
			try
			{				
				 if (cabman != null)
				 {
					 cabmandb.delete(id);
				 } 	
				 return ResponseEntity.ok().build();
			}
	catch(ResourceNotFoundException e )
	{
		e.printStackTrace();
		return null;
	}
    
}

	}

