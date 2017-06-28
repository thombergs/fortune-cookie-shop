package de.adesso.cookies.quotes;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CookiesDBTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void paginationPos() {

      CookiesDB cookiesDB = new CookiesDB();

      assertTrue(cookiesDB.getList(0,20).size() == 20);
      assertTrue(cookiesDB.getList(20,20).size() == 10);
      assertTrue(cookiesDB.getList(5,20).size() == 20);


    }
}
