package com.techwording.scs;

import java.util.concurrent.CountDownLatch;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.TestSupportBinderAutoConfiguration;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EmbeddedKafkaLatchTest.App.class, EmbeddedKafkaLatchTest.Producer.class, EmbeddedKafkaLatchTest.Consumer.class })
@EnableBinding(Source.class)
public class EmbeddedKafkaLatchTest {

	@SpringBootApplication(exclude = TestSupportBinderAutoConfiguration.class)
	static class App {

	}

	@EnableBinding(Sink.class)
	static class Consumer {

		private String recievedMessage;

		@StreamListener(target = Sink.INPUT)
		public void consume(String message) {

			System.out.println("recieved a string message : " + message);
			recievedMessage = message;
			latch.countDown();

		}

		public String getRecievedMessage() {

			return recievedMessage;
		}

	}

	@EnableBinding(Source.class)
	static class Producer {

		private Source mySource;

		public Producer(Source mySource) {

			super();
			this.mySource = mySource;
		}

		public Source getMysource() {

			return mySource;
		}

		public void setMysource(Source mysource) {

			mySource = mysource;
		}

	}

	private static final String TOPIC1 = "test-topic-1";

	@ClassRule
	public static EmbeddedKafkaRule embeddedKafka = new EmbeddedKafkaRule(1, true, TOPIC1);

	private static CountDownLatch latch = new CountDownLatch(1);

	@BeforeClass
	public static void setup() {

		System.setProperty("spring.cloud.stream.kafka.binder.brokers",
			embeddedKafka.getEmbeddedKafka()
				.getBrokersAsString());
		System.setProperty("spring.cloud.stream.bindings.input.destination", TOPIC1);
		System.setProperty("spring.cloud.stream.bindings.input.content-type", "text/plain");
		System.setProperty("spring.cloud.stream.bindings.input.group", "input-group-1");
		System.setProperty("spring.cloud.stream.bindings.output.destination", TOPIC1);
		System.setProperty("spring.cloud.stream.bindings.output.content-type", "text/plain");
		System.setProperty("spring.cloud.stream.bindings.output.group", "output-group-1");

	}

	@Autowired
	private EmbeddedKafkaLatchTest.Producer producer;

	@Autowired
	private EmbeddedKafkaLatchTest.Consumer consumer;

	@Test
	public void testMessageSendRecieve() throws InterruptedException {

		System.out.println("count_latch: " + latch.getCount());
		producer.getMysource()
			.output()
			.send(MessageBuilder.withPayload("payload")
				.setHeader("type", "string")
				.build());

		latch.await();
		assertThat(consumer.getRecievedMessage()).isEqualTo("payload");
	}
}
