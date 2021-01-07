package com.devsuperior.dsdeliver.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderService serice;

	@GetMapping
	public ResponseEntity<List<OrderDTO>> findAll() {

		List<OrderDTO> list = serice.findAll();
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO orderDTO) {

		orderDTO = serice.insert(orderDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(orderDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(orderDTO);
	}
	@PutMapping(value = "/{id}/delivered")
	public ResponseEntity<OrderDTO> insert(@PathVariable Long id) {
		OrderDTO dto = serice.setDelivered(id);
		return ResponseEntity.ok().body(dto);
	}
}
