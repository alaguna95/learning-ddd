package org.alaguna.learningddd.input_data.training.domain;

import org.alaguna.learningddd.shared.domain.AggregateRoot;
import org.alaguna.learningddd.shared.domain.bus.DomainEvent;
import org.alaguna.learningddd.shared.domain.training.TrainingCreatedDomainEvent;

public class Training extends AggregateRoot {

    private TrainingId id;
    private TrainingPeriod period;

    public Training(TrainingId id, TrainingPeriod period){
        this.id = id;
        this.period = period;
    }

    public static Training create(TrainingId id, TrainingPeriod period){
        Training training = new Training(id, period);

        training.record(new TrainingCreatedDomainEvent(id.value()));

        return training;
    }

    public TrainingId getId() {
        return id;
    }

    public TrainingPeriod getPeriod() {
        return period;
    }
}
