package com.wowsanta.client;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;

import com.wowsanta.handler.DomainEntityHandler;
import com.wowsanta.handler.ScimEntityHandler;
import com.wowsanta.handler.ServiceHandler;
import com.wowsanta.scim.ScimException;
import com.wowsanta.service.ServiceStructure;
import com.wowsanta.service.ServiceStructureBuilder;
import com.wowsanta.util.log.LOGGER;


public class ClientBuilder {
	private static ClientBuilder instance;
	
	private SSLConnectionSocketFactory sslConnectionSocketFactory;
	private PoolingHttpClientConnectionManager poolManager;
	public static ClientBuilder getInstance() {
		if(instance == null) {
			instance = new ClientBuilder();
		}
		return instance;
	}
	
	public void initialize() throws ScimException {
		try {
			if(poolManager == null && sslConnectionSocketFactory == null) {
				SSLContextBuilder builder = new SSLContextBuilder();
		    	builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		    	sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build(),SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		    	Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
		    	        .register("http", new PlainConnectionSocketFactory())
		    	        .register("https", sslConnectionSocketFactory)
		    	        .build();
		    	
		    	poolManager = new PoolingHttpClientConnectionManager(registry);
		    	poolManager.setMaxTotal(2000);	
			}else {
				LOGGER.system.debug("already initialized : ");
			}
		}catch (Exception e) {
			throw new ScimException(e);
		}
	}
	
	public ScimClient build() {
		
		ScimClient client = new ScimClient();
		client.setDomain(domain);
		client.setUrl(url);
		client.setEntity(entity);
		
		return client;
	}
	public CloseableHttpClient get() {
    	return HttpClients.custom()
	    .setSSLSocketFactory(sslConnectionSocketFactory)
	    .setConnectionManager(poolManager)
	    .build();
	}
}
