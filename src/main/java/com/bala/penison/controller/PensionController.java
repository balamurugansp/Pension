package com.bala.penison.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bala.penison.error.PensionAmountNullException;
import com.bala.penison.error.PensionerNotFoundException;
import com.bala.penison.service.PensionService;

@RestController
@RequestMapping("/pension")
public class PensionController {

	@Autowired
	private PensionService pensionService;

	@GetMapping("/{pensionerName}")
	public ResponseEntity<Double> getPension(@PathVariable String pensionerName) {
		try {
			Double pensionAmount = pensionService.getPensionAmount(pensionerName);
			return ResponseEntity.ok(pensionAmount);
		} catch (PensionerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
		} catch (PensionAmountNullException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 or other appropriate code
		} catch (Exception e) { // Catching other exceptions for incorrect input (e.g., if pensionerName is not
								// a String)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400
		}
	}
}
