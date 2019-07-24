package org.home.recordings.persistence;

import org.home.recordings.domain.Recording;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = {RecordingRepositoryTest.Initializer.class})
public class RecordingRepositoryTest {

    @Autowired
    private RecordingRepository recordingRepository;

    @ClassRule
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:10");

    @Test
    public void shouldSave() {
        Recording recording = Recording.builder()
                .filename("/jandepora/recording.wav")
                .date(LocalDate.now())
                .beat("4/4")
                .style("acoustic")
                .tempo(120)
                .build();

        recordingRepository.save(recording);

        Optional<Recording> actualRecording = recordingRepository.findById(recording.getFilename());

        assertThat(actualRecording.isPresent()).isTrue();
        assertThat(actualRecording.get()).isEqualTo(recording);
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword())
                    .applyTo(configurableApplicationContext.getEnvironment());
        }

    }

}
