package org.alaguna.input_data.unit;

import org.alaguna.input_data.training.application.TrainingCreateCommand;
import org.alaguna.input_data.training.application.TrainingCreator;
import org.alaguna.input_data.training.application.TrainingValidator;
import org.alaguna.input_data.training.domain.Training;
import org.alaguna.input_data.training.domain.TrainingRepository;
import org.alaguna.input_data.objects.TrainingCreateCommandBuilder;
import org.alaguna.input_data.objects.TrainingObjectMother;
import org.alaguna.shared.bus.DomainEvent;
import org.alaguna.shared.bus.EventBus;
import org.alaguna.shared.events.TrainingCreatedDomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TrainingCreatorTest {

    private TrainingCreator trainingCreator;

    private TrainingRepository trainingRepository;

    private EventBus eventBus;

    @BeforeEach
    public void setUp(){
        trainingRepository = mock(TrainingRepository.class);
        eventBus = mock(EventBus.class);
        TrainingValidator trainingValidator = new TrainingValidator(trainingRepository);
        trainingCreator = new TrainingCreator( trainingRepository,trainingValidator, eventBus);
    }

    @Test
    public void throwExceptionWhenIdIsNull(){

        assertThrows(NullPointerException.class , () -> trainingCreator.createTraining(
                new TrainingCreateCommandBuilder().withId(null).build()));

    }

    @Test
    public void throwExceptionWhenIdIsNotUUID(){
        assertThrows(IllegalArgumentException.class , () -> trainingCreator.createTraining(
                new TrainingCreateCommandBuilder().withId("12121AS").build()));

    }

    @Test
    public void throwExceptionWhenStartIsNull(){
        assertThrows(IllegalArgumentException.class , () -> trainingCreator.createTraining(
                new TrainingCreateCommandBuilder().withStart(null).build()));

    }

    @Test
    public void throwExceptionWhenEndIsNull(){
        assertThrows(IllegalArgumentException.class , () -> trainingCreator.createTraining(
                new TrainingCreateCommandBuilder().withEnd(null).build()));

    }

    @Test
    public void throwExceptionWhenStartEqualsEnd(){
        assertThrows(IllegalArgumentException.class , () -> trainingCreator.createTraining(
                new TrainingCreateCommandBuilder().withStart(LocalDateTime.of(2020,1,1,17,30))
                        .withEnd(LocalDateTime.of(2020,1,1,17,30)).build()));

    }


    @Test
    public void throwExceptionWhenStartAfterEnd(){
        assertThrows(IllegalArgumentException.class , () -> trainingCreator.createTraining(
                new TrainingCreateCommandBuilder().withStart(LocalDateTime.of(2020,1,1,18,30))
                        .withEnd(LocalDateTime.of(2020,1,1,17,30)).build()));

    }


    @Test
    public void throwExceptionWhenExistTrainingInPeriod(){
        TrainingCreateCommand command =  new TrainingCreateCommandBuilder().build();

        when(trainingRepository.existSomeTrainingInThisPeriod(command.getStart(), command.getFinish())).thenReturn(true);

        assertThrows(IllegalArgumentException.class , () -> trainingCreator.createTraining(command));
    }

    @Test
    public void saveTrainingAndPublishEvent(){

        Training training = TrainingObjectMother.randomTraining();

        TrainingCreateCommand command =  new TrainingCreateCommand(
                training.getId().value(),
                training.getPeriod().getStart().value(),
                training.getPeriod().getFinish().value());
        DomainEvent event = new TrainingCreatedDomainEvent(training.getId().value());

        when(trainingRepository.existSomeTrainingInThisPeriod(command.getStart(), command.getFinish())).thenReturn(false);

        trainingCreator.createTraining(command);

        verify(trainingRepository, times(1)).createTraining(training);
        verify(eventBus, times(1)).publish(Arrays.asList(event));

    }


}
