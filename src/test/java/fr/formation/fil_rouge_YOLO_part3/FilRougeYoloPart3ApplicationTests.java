package fr.formation.fil_rouge_YOLO_part3;

import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@Sql({"/YOLOTEST_creationTables.sql", "/YOLOTEST_dataset.sql"})
@SpringBootTest
class FilRougeYoloPart3ApplicationTests implements CommandLineRunner {

	@Test
	void contextLoads() {
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
