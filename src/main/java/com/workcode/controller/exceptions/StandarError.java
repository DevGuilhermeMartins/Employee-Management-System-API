package com.workcode.controller.exceptions;

import java.time.Instant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StandarError {

	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
}
