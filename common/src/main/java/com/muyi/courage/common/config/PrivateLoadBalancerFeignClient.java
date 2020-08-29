package com.muyi.courage.common.config;

import feign.Client;
import feign.Request;
import feign.Response;
import feign.httpclient.ApacheHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPOutputStream;

import static feign.Util.*;
import static feign.Util.CONTENT_LENGTH;
import static java.lang.String.format;

@Slf4j
public class PrivateLoadBalancerFeignClient extends LoadBalancerFeignClient {


	private Client delegate;
	private CachingSpringLoadBalancerFactory lbClientFactory;
	private SpringClientFactory clientFactory;

	public PrivateLoadBalancerFeignClient(Client delegate,
										  CachingSpringLoadBalancerFactory lbClientFactory,
										  SpringClientFactory clientFactory) {
		super(delegate, lbClientFactory, clientFactory);
		this.delegate = delegate;
		this.lbClientFactory = lbClientFactory;
		this.clientFactory = clientFactory;

	}

	@Override
	public Response execute(Request request, Request.Options options) throws IOException{
		try {
			String url = request.url();
			log.info("[FeignUrl]"+url);
			URI uri = URI.create(url);
			String clientName = uri.getHost();
			log.info("[clientName] "+clientName);
			//String ip = BalanceForShangHai.getBalanceIPByGroupName(clientName);
//			if (ip!=null){
//				// 重新构建请求 URL
//				url = url.replaceFirst(clientName, ip);
//
//			}
			//重新构建 request　对象
			Request newRequest = Request.create(request.httpMethod(),url,request.headers(),request.body(),request.charset(),request.requestTemplate());

			//httpClient
			return ((ApacheHttpClient) delegate).execute(newRequest,options);

			//HTTPURL
			//HttpURLConnection connection = convertAndSend(newRequest, options);
			//return convertResponse(connection, newRequest);

		}catch (Exception e){
			log.error(e.getMessage(),e);
			return super.execute(request, options);
		}

	}

	HttpURLConnection convertAndSend(Request request, Request.Options options) throws IOException {
		final URL url = new URL(request.url());
		final HttpURLConnection connection = this.getConnection(url);

		connection.setConnectTimeout(options.connectTimeoutMillis());
		connection.setReadTimeout(options.readTimeoutMillis());
		connection.setAllowUserInteraction(false);
		connection.setInstanceFollowRedirects(options.isFollowRedirects());
		connection.setRequestMethod(request.httpMethod().name());

		Collection<String> contentEncodingValues = request.headers().get(CONTENT_ENCODING);
		boolean gzipEncodedRequest =
				contentEncodingValues != null && contentEncodingValues.contains(ENCODING_GZIP);
		boolean deflateEncodedRequest =
				contentEncodingValues != null && contentEncodingValues.contains(ENCODING_DEFLATE);

		boolean hasAcceptHeader = false;
		Integer contentLength = null;
		for (String field : request.headers().keySet()) {
			if (field.equalsIgnoreCase("Accept")) {
				hasAcceptHeader = true;
			}
			for (String value : request.headers().get(field)) {
				if (field.equals(CONTENT_LENGTH)) {
					if (!gzipEncodedRequest && !deflateEncodedRequest) {
						contentLength = Integer.valueOf(value);
						connection.addRequestProperty(field, value);
					}
				} else {
					connection.addRequestProperty(field, value);
				}
			}
		}
		// Some servers choke on the default accept string.
		if (!hasAcceptHeader) {
			connection.addRequestProperty("Accept", "*/*");
		}

		if (request.body() != null) {
			if (true) {
				if (contentLength != null) {
					connection.setFixedLengthStreamingMode(request.body().length);
				} else {
					connection.setChunkedStreamingMode(8196);
				}
			}
			connection.setDoOutput(true);
			OutputStream out = connection.getOutputStream();
			if (gzipEncodedRequest) {
				out = new GZIPOutputStream(out);
			} else if (deflateEncodedRequest) {
				out = new DeflaterOutputStream(out);
			}
			try {
				out.write(request.body());
			} finally {
				try {
					out.close();
				} catch (IOException suppressed) { // NOPMD
				}
			}
		}
		return connection;
	}
	public HttpURLConnection getConnection(final URL url) throws IOException {
		return (HttpURLConnection) url.openConnection();
	}
	Response convertResponse(HttpURLConnection connection, Request request) throws IOException {
		int status = connection.getResponseCode();
		String reason = connection.getResponseMessage();

		if (status < 0) {
			throw new IOException(format("Invalid status(%s) executing %s %s", status,
					connection.getRequestMethod(), connection.getURL()));
		}

		Map<String, Collection<String>> headers = new LinkedHashMap<>();
		for (Map.Entry<String, List<String>> field : connection.getHeaderFields().entrySet()) {
			// response message
			if (field.getKey() != null) {
				headers.put(field.getKey(), field.getValue());
			}
		}

		Integer length = connection.getContentLength();
		if (length == -1) {
			length = null;
		}
		InputStream stream;
		if (status >= 400) {
			stream = connection.getErrorStream();
		} else {
			stream = connection.getInputStream();
		}
		return Response.builder()
				.status(status)
				.reason(reason)
				.headers(headers)
				.request(request)
				.body(stream, length)
				.build();
	}

}
