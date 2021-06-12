package com.dilip.kinesis;

import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.firehose.FirehoseClient;
import software.amazon.awssdk.services.firehose.model.PutRecordRequest;
import software.amazon.awssdk.services.firehose.model.PutRecordResponse;
import software.amazon.awssdk.services.firehose.model.Record;

@SpringBootApplication
public class SpringBootKinesisFirehoseApplication implements CommandLineRunner {

	@Value("${accessKey}")
	private String awsAccessKey;

	@Value("${secretKey}")
	private String awsSecretKey;

	@Value("${deliveryStreamName}")
	private String fireHoseDeliveryStreamName;

	private static final Logger logger = LoggerFactory.getLogger(SpringBootKinesisFirehoseApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKinesisFirehoseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(awsAccessKey, awsSecretKey);

		FirehoseClient firehoseClient = FirehoseClient.builder().region(Region.US_EAST_1)
				.credentialsProvider(StaticCredentialsProvider.create(awsCredentials)).build();

		JSONObject messageJson = new JSONObject();
		messageJson.put("ticker_symbol", "Steel");
		messageJson.put("sector", "Manufacture");
		messageJson.put("change", 89.92);
		messageJson.put("price", 662.57);
		messageJson.put("loss", 76);
		messageJson.put("nested", new JSONObject().put("name", "Dilipan"));

		logger.info("Message delivering to Firehose: " + messageJson.toString());

		SdkBytes sdkBytes = SdkBytes.fromByteArray(messageJson.toString().getBytes());
		Record record = Record.builder().data(sdkBytes).build();

		PutRecordRequest recordRequest = PutRecordRequest.builder().deliveryStreamName(fireHoseDeliveryStreamName)
				.record(record).build();

		PutRecordResponse recordResponse = firehoseClient.putRecord(recordRequest);

		logger.info("Message delivered record ID: " + recordResponse.recordId());
	}

}
