package org.zerock.myapp.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data

@Component("restaurant")
public class Restaurant { //POJO

	@Autowired
	private Chef chef;
}
