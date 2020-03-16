package com.wowsanta.server.spark;



import java.util.Set;
import java.util.Date;
import java.util.Map.Entry;

import com.tmax.tibero.jdbc.data.charset.SingleByteEncoder;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.entity.EntityInfo;
import com.wowsanta.scim.service.RestfulService;
import com.wowsanta.server.Server;
import com.wowsanta.service.ServiceStructure;
import com.wowsanta.util.log.LOGGER;

import lombok.Data;
import spark.Route;
import spark.Spark;
import spark.route.HttpMethod;
import static spark.Spark.*;

@Data
public class SparkServer implements Server {

	private int port;
	private int maxThread;
	private int minThread;
	private int idleTime;
	
	private String keyStore;
	private String keyStorePw;
	private String trustStore;
	private String trustStorePw;
	
	private String front = "./front";
	
//	public static class SparkServerBuilder extends ConfigurationBuilder{
//		public SparkServer build(String file_name) throws ScimException {
//			return load(SparkServer.class, file_name);
//		}
//	}

	public Configuration save(String file_name) throws ScimException {
		return ConfigurationBuilder.save(this, file_name);
	}
	
	public void registerService(String key, String value) throws ScimException{
		
	}
//	
//	public void build() throws ScimException {
////		Set<Entry<String,String>> service_entris = services.entrySet();
////		for (Entry<String, String> entry : service_entris) {
////			String service_name = entry.getKey();
////			String service_file = entry.getValue();
////			
////			
////			System.out.println("service_name  : " + service_name);
////			System.out.println("service_name  : " + service_file);
////			
////			
////		}
//	}
	
	
	@Override
	public void initialize() throws ScimException{
		LOGGER.system.info(" -- SPARK SERVER INITIALIZE --- : {}", "name");
		LOGGER.system.info("{}",this);
		
		Spark.port(this.port);
		Spark.threadPool(maxThread,minThread,idleTime);
		Spark.staticFiles.externalLocation(this.front);
		
		registryRestFul();
		//Spark.secure(keystoreFile, keystorePassword, truststoreFile, truststorePassword);
	}
	

	private void registryRestFul() {
		SparkServiceBuilder bulder = new SparkServiceBuilder();
		
		Set<Entry<String, EntityInfo>> entity_entry_set = ServiceStructure.getInstance().getEntitySet();
		for (Entry<String, EntityInfo> entity_entry : entity_entry_set) {
			Set<Entry<String, RestfulService>> service_entry_set = entity_entry.getValue().getRestfulServiceSet();
			for (Entry<String, RestfulService> service_entry : service_entry_set) {
				try {
					HttpMethod method = HttpMethod.valueOf(service_entry.getValue().getMethod());
					
					RestfulService service = service_entry.getValue();
					
					switch (method) {
					case before:
						//before(service_entry.getValue().getUrl(),newFilter(control_info.getControlClass()));
						break;
					case after:
						//after(control_info.getPath(),newFilter(control_info.getControlClass()));
						break;
					case post:
						post(service.getUrl(),bulder.build_route(service));
						break;
					case get:
						get(service.getUrl(),bulder.build_route(service));
						break;
					case patch:
						patch(service.getUrl(),bulder.build_route(service));
						break;
					case put:
						put(service.getUrl(),bulder.build_route(service));
						break;
					case delete:
						delete(service.getUrl(),bulder.build_route(service));
						break;
					default:
						break;
					}
				}catch (Exception e) {
					LOGGER.error(e);
				}finally {
					LOGGER.system.info("{} - {} : {} > {}",entity_entry.getKey(),service_entry.getKey(),service_entry.getValue().getMethod(),service_entry.getValue().getUrl() );
				}
			}
		}
	}

	@Override
	public void start() {
		Spark.awaitInitialization();
		LOGGER.system.info("SPARK SERVER STARTED :  " + new Date());
	}

	@Override
	public void stop() {
		Spark.awaitStop();
	}





}
