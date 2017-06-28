package de.adesso.cookies.quotes;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore // will not work every time because of the failing services
public class ProductServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testWithCacheHits() {

		HystrixRequestContext context = HystrixRequestContext.initializeContext();

		try {
			FortuneCookieDao select1a = new FortuneCookieDao(0, 20);
			FortuneCookieDao select2 = new FortuneCookieDao(20, 20);
			FortuneCookieDao select1b = new FortuneCookieDao(0, 20);

			assertTrue(select1a.execute().size() == 20);
			// this is the first time we've executed this command
			// with offset 0 and limit 20, so it should not be from cache
			assertFalse(select1a.isResponseFromCache());

			assertTrue(select2.execute().size() == 10);
			// this is the first time we've executed this command
			// with offset 20 and limit 20, so it should not be from cache
			assertFalse(select2.isResponseFromCache());

			assertTrue(select1b.execute().size() == 20);
			// this is the second time we've executed this command with
			// the same value so it should return from cache
			assertTrue(select1b.isResponseFromCache());

		} finally {
			context.shutdown();
		}

		// start a new request context
		context = HystrixRequestContext.initializeContext();

		try {
			FortuneCookieDao select3 = new FortuneCookieDao(0, 20);

			assertTrue(select3.execute().size() == 20);
			// this is a new request context so this
			// should not come from cache
			assertFalse(select3.isResponseFromCache());
		} finally {
			context.shutdown();
		}
	}

}
