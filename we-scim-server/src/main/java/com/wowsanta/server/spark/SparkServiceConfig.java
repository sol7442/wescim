package com.wowsanta.server.spark;

import java.util.ArrayList;
import java.util.List;


import lombok.Data;

@Data
public class SparkServiceConfig {
	private String name;
	private List<SparkService> serviceList = new ArrayList<SparkService>();
}
