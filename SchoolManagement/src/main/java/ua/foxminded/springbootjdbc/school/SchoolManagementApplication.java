package ua.foxminded.springbootjdbc.school;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ua.foxminded.springbootjdbc.school.dao.SchoolDataService;
import ua.foxminded.springbootjdbc.school.dao.JdbcSchoolRepository;

@SpringBootApplication
public class SchoolManagementApplication {

  public static void main(String[] args) throws InterruptedException {
    File initialScriptTempFile;

    try {
      InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("initialScript.sql");

      initialScriptTempFile = File.createTempFile(String.valueOf(is.hashCode()), ".tmp");
      initialScriptTempFile.deleteOnExit();

      try (FileOutputStream out = new FileOutputStream(initialScriptTempFile)) {
        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = is.read(buffer)) != -1) {
          out.write(buffer, 0, bytesRead);
        }
      }
      String cmdQuery = "psql -U postgres -h localhost -p 5432 " + "-f " + Paths.get(initialScriptTempFile.toURI());
      String[] envVars = { "PGPASSWORD=1234" };
      Process runInitScript = Runtime.getRuntime().exec(cmdQuery, envVars);
      runInitScript.waitFor();
    } catch (IOException e) {
      throw new IllegalArgumentException("File not found");
    }

    SpringApplication.run(SchoolManagementApplication.class, args);
  }

}
