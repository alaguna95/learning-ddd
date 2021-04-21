package org.alaguna.learningddd.unit;

import org.alaguna.learningddd.input_data.training.application.TrainingCreateCommand;
import org.alaguna.learningddd.input_data.training.application.TrainingCreator;
import org.alaguna.learningddd.input_data.training.domain.Training;
import org.alaguna.learningddd.input_data.training.domain.TrainingRepository;
import org.alaguna.learningddd.objects.TrainingCreateCommandBuilder;
import org.alaguna.learningddd.objects.TrainingObjectMother;
import org.alaguna.learningddd.shared.domain.bus.DomainEvent;
import org.alaguna.learningddd.shared.domain.bus.EventBus;
import org.alaguna.learningddd.shared.domain.training.TrainingCreatedDomainEvent;
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
        trainingCreator = new TrainingCreator( trainingRepository,eventBus);
    }

    @Test
    public void throwExceptionWhenIdIsNull(){

        assertThrows(NullPointerException.class , () -> trainingCreator.createTraining(
                new TrainingCreateCommandBuilder().withId(null).build()));

    }

    @Test
    public void throwExceptionWhenIdIsNotValid(){
        assertThrows(IllegalArgumentException.class , () -> trainingCreator.createTraining(
                new TrainingCreateCommandBuilder().withId("1").build()));

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

        verify(trainingRepository, times(1)).createTraining(training);
        verify(eventBus, times(1)).publish(Arrays.asList(event));
    }


}
