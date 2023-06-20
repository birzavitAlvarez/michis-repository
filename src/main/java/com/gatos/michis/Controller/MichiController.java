package com.gatos.michis.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gatos.michis.Model.Michi;
import com.gatos.michis.Repository.MichiRepository;

import org.springframework.web.bind.annotation.PathVariable;

import java.lang.String;
import java.util.List;
import java.util.Map;


@Controller	
@RequestMapping(path="/gato")
public class MichiController {

	@Autowired
	private MichiRepository michiRepository;

	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addMichi (@RequestParam String name, 
                                          @RequestParam String raza,
                                          @RequestParam String duenio,
                                          @RequestParam String fecha) {
		Michi m = new Michi();
		m.setName(name);
		m.setRaza(raza);
        m.setDuenio(duenio);
        m.setFecha(fecha);
		michiRepository.save(m);
		return "Saved";
	}

	@DeleteMapping(path="/del")
	public @ResponseBody String delMichi (@RequestParam Integer id) {
		Michi m = new Michi();
		m.setId(id);
		michiRepository.delete(m);
		return "Deleted";
	}

	@PutMapping(path="/edit")
	public @ResponseBody String editMichi (@RequestParam Integer id, 
                                           @RequestParam String name, 
                                           @RequestParam String raza,
                                           @RequestParam String duenio,
                                           @RequestParam String fecha) {
		Michi m = new Michi();
		m.setId(id);
		m.setName(name);
		m.setRaza(raza);
        m.setDuenio(duenio);
        m.setFecha(fecha);
		michiRepository.save(m);
		return "Updated";
	}


	@GetMapping(path="/all")
	public @ResponseBody Iterable<Michi> getAllMichis() {
		return michiRepository.findAll();
	}

	@GetMapping(path="/get/{id}")
	public @ResponseBody Michi getMichi(@PathVariable Integer id) {
		return michiRepository.findById(id).orElse(null);
	}


    @Autowired
  	private JdbcTemplate jdbcTemplate;

	@GetMapping(path="/get/report")
	public @ResponseBody List<Map<String, Object>> getReport() {
		List<Map<String, Object>> queryResult = jdbcTemplate.queryForList("SELECT CONCAT(name, '=>', raza, '=>', duenio, '=>', fecha ) as mycol FROM michi");
		return queryResult;
	}

}